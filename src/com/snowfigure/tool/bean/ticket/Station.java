package com.snowfigure.tool.bean.ticket;

public class Station
{
    private int id;
    private String name;
    private String pinyin_short;
    private String pinyin;
    private String query_station_Upper;
    public int getId()
    {
        return id;
    }
    public void setId( int id )
    {
        this.id = id;
    }
    public String getName()
    {
        return name;
    }
    public void setName( String name )
    {
        this.name = name;
    }
    public String getPinyin_short()
    {
        return pinyin_short;
    }
    public void setPinyin_short( String pinyin_short )
    {
        this.pinyin_short = pinyin_short;
    }
    public String getPinyin()
    {
        return pinyin;
    }
    public void setPinyin( String pinyin )
    {
        this.pinyin = pinyin;
    }
    public String getQuery_station_Upper()
    {
        return query_station_Upper;
    }
    public void setQuery_station_Upper( String query_station_Upper )
    {
        this.query_station_Upper = query_station_Upper;
    }
    
}
