package com.snowfigure.tool.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.snowfigure.kits.json.Json2BeanUtil;
import com.snowfigure.kits.net.HttpRequestUtil;
import com.snowfigure.kits.net.TrustSSL;
import com.snowfigure.model.tool.ticket.Ticket_Stations;
import com.snowfigure.model.tool.ticket.Ticket_Trains;
import com.snowfigure.tool.bean.ticket.QueryLeftNewDTO;
import com.snowfigure.tool.bean.ticket.Station;

public class Ticket_Filter_12306_Service
{
    public static void initStations()
    {
        List<Station> list = getStations();
        for ( Station station : list )
        {
            Map<String, Object> map = new HashMap<String, Object>();
            Ticket_Stations stations = Ticket_Stations.me.findById( station.getId());
            map.put( "station_no", station.getId() );
            map.put( "station_name", station.getName() );
            map.put( "station_telecode", station.getQuery_station_Upper());
            if(stations == null)
            {
                System.out.println(map.get( "station_no" ));
                Ticket_Stations.me.setAttrs( map ).save();
            }
            else
            {
                stations.setAttrs( map ).update();
            }
            
        }
    }
    
    
    private static List<Station> getStations()
    {
        List<Station> list = new ArrayList<Station>();
        //String req_url = "http://localhost:8010/assets/12306_ticket/station_name.js";
        String req_url = "http://localhost:8080/tools/assets/12306_ticket/station_name.js";
        String str = HttpRequestUtil.httpRequest( req_url );
        str = str.replace( "'", "" );
        str = str.replace( ";", "" );
        String[] _stations = str.split( "@" );
        System.out.println(_stations.length);
        for ( int i = 1; i < _stations.length; i++ )
        {
            String[] detail = _stations[i].split( "\\|" );
            Station station = new Station();
            station.setId( new Integer( detail[5] ) );
            station.setName( detail[1] );
            station.setPinyin( detail[3] );
            station.setPinyin_short( detail[0] );
            station.setQuery_station_Upper( detail[2] );
            list.add( station );
        }
        return list;
    }
    public static void initTrains()
    {
        List<Station> list = getStations();
        filterTrain( list, "2015-05-20");
        
    }
    public static void initTrains(int i, int j)
    {
        List<Station> list = getStations();
        filterTrain( list, "2015-05-20",i,j);
    }
    private static void filterTrain(List<Station> list,String time)
    {
        filterTrain(list,time,0,0);
    }
    
    /**
     * 
     * @param list
     * @param time  2015-05-20
     */
    private static void filterTrain(List<Station> list,String time,int i, int j)
    {
//        List<QueryLeftNewDTO> Dtolist = new ArrayList<QueryLeftNewDTO>();
        //leftTicketDTO.from_station=BJP&leftTicketDTO.to_station=SHH
        String _url = "https://kyfw.12306.cn/otn/leftTicket/query?"
                + "leftTicketDTO.train_date=%s&"
                + "leftTicketDTO.from_station=%s&"
                + "leftTicketDTO.to_station=%s&"
                + "purpose_codes=ADULT";
        String req_url = "";
        for ( ; i < list.size(); i++ )
        {
            String from_station = list.get( i ).getQuery_station_Upper();
            for ( ; j < list.size(); j++ )
            {
                
                if(i==j) continue;
                String to_station = list.get( j ).getQuery_station_Upper();
                System.out.println("From:[" +i  + "]:["+ from_station + "]  ---  To:[" + j  + "]:[" + to_station );
                req_url = String.format( _url, time,from_station,to_station );
                String result = request( req_url );
                result = result.split( "\"data\":\\[" )[1];
                result = result.split( "\\],\"messages\"" )[0]+ "";
                if( !result.contains( "queryLeftNewDTO" )) 
                {
                    continue;
                }
                result.replaceAll( "\u0025", "" );
                String[] dtos = result.split( "\"queryLeftNewDTO\":" );
                for ( int k = 1; k < dtos.length; k++ )
                {
                    String dto = dtos[k].split( ",\"secretStr\":" )[0];
                    QueryLeftNewDTO leftNewDTO = 
                            ( QueryLeftNewDTO ) Json2BeanUtil.Json2Bean( 
                                    dto, 
                                    QueryLeftNewDTO.class );
                    if( ! leftNewDTO.getStart_station_name() .equals( leftNewDTO.getFrom_station_name() )  ||
                        ! leftNewDTO.getEnd_station_name() .equals( leftNewDTO.getTo_station_name() ) )
                    {
                        System.err.println("passby...");
                        continue;
                    }
                    System.out.println("not passby...");
                    saveOrUpdate( leftNewDTO );
//                    Dtolist.add( leftNewDTO );
                }
            }
            j=0;
        }
//        return Dtolist;
    }
    private static void saveOrUpdate(QueryLeftNewDTO leftNewDTO)
    {
        Ticket_Trains ticket_Trains = Ticket_Trains.me.findById( leftNewDTO.getTrain_no() );
        List<Ticket_Stations> from_list = 
                Ticket_Stations.me.find( "select * from stations where station_name =?",
                        leftNewDTO.getFrom_station_name());
        
        List<Ticket_Stations> to_list = 
                Ticket_Stations.me.find( "select * from stations where station_name =?",
                        leftNewDTO.getTo_station_name());
        
        if(from_list==null)
        {
            System.err.println("from_list==null");
            return;
        }
        if(to_list==null)
        {
            System.err.println("to_list==null");
            return;
        }
        if(from_list.size()!=1)
        {
            System.err.println("from_list.size()!=1");
            return;
        }
        if(to_list.size()!=1)
        {
            System.err.println("to_list.size()!=1");
            return;
        }
        int from_no = from_list.get( 0 ).getInt( "station_no" );
        int to_no = to_list.get( 0 ).getInt( "station_no" );
        
        Map<String, Object> map = new HashMap<String, Object>();
        map.put( "train_no", leftNewDTO.getTrain_no() );
        map.put( "train_code", leftNewDTO.getStation_train_code() );
        map.put( "start_station_no", from_no );
        map.put( "end_station_no", to_no );
        map.put( "start_time", leftNewDTO.getStart_time() );
        map.put( "arrive_time", leftNewDTO.getArrive_time() );
        map.put( "lishi", leftNewDTO.getLishi() );
        
        if(ticket_Trains == null)
        {
            Ticket_Trains.me.setAttrs( map ).save();
        }
        else
        {
            ticket_Trains.update();
        }
        
    }
    public static String test()
    {
        String req_url = "https://kyfw.12306.cn/otn/leftTicket/query?leftTicketDTO.train_date=2015-05-20&leftTicketDTO.from_station=BJP&leftTicketDTO.to_station=SHH&purpose_codes=ADULT";
        String str = TrustSSL.requestHttps( req_url );
        return str;
    }
    /**
     * 获取查询结果，如果失败，再查询一次，最后根据结果返回成功或者失败
     * @param req_url
     * @return
     */
    private static String request(String req_url)
    {
        String str = TrustSSL.requestHttps( req_url );
        if("ERROR".equals( str ))
        {
            str = TrustSSL.requestHttps( req_url );
        }
        return str;
    }
}
