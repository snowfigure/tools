package com.sf.service.tools;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

//号码混淆器
public class Encrypt_Number_Confuse
{
    private static final Map<String, String[]> NUMBER = new HashMap<String, String[]>();
    static{
        NUMBER.put( "0", new String[]{"0","〇","〇","〇","〇","〇","〇","〇","另"} );
        NUMBER.put( "1", new String[]{"1","一","壹","①","⒈","㈠","⑴","Ⅰ","要"} );
        NUMBER.put( "2", new String[]{"2","二","贰","②","⒉","㈡","⑵","Ⅱ","尔"} );
        NUMBER.put( "3", new String[]{"3","三","叁","③","⒊","㈢","⑶","Ⅲ","伞"} );
        NUMBER.put( "4", new String[]{"4","四","肆","④","⒋","㈣","⑷","Ⅳ","事"} );
        NUMBER.put( "5", new String[]{"5","五","伍","⑤","⒌","㈤","⑸","Ⅴ","伍"} );
        NUMBER.put( "6", new String[]{"6","六","陆","⑥","⒍","㈥","⑹","Ⅵ","溜"} );
        NUMBER.put( "7", new String[]{"7","七","柒","⑦","⒎","㈦","⑺","Ⅶ","期"} );
        NUMBER.put( "8", new String[]{"8","八","捌","⑧","⒏","㈧","⑻","Ⅷ","拔"} );
        NUMBER.put( "9", new String[]{"9","九","玖","⑨","⒐","㈨","⑼","Ⅸ","酒"} );
    }
    public static String convert(String number)
    {
        if(StringUtils.isEmpty( number ))
        {
            return "ERROR INPUT";
        }
        StringBuffer buffer = new StringBuffer();
        for ( int i = 0; i < number.length(); i++ )
        {
            String _char = number.charAt( i ) + "";
            if(NUMBER.containsKey( _char ))
            {
                int rand = ( int ) ( Math.random() * 8 );
                buffer.append( NUMBER.get( _char )[rand] );
            }
            else
            {
                buffer.append( _char );
            }
        }
        return buffer.toString();
    }
    
    public static void main( String[] args )
    {
        System.out.println(Encrypt_Number_Confuse.convert( "470697056" ));
    }
}
