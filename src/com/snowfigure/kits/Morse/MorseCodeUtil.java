package com.snowfigure.kits.Morse;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

public class MorseCodeUtil
{
    private static final String ONE_SPACE = " ";
    private static final String TWO_SPACE = "  ";
    private static final String THREE_SPACE = "   ";
    private static final Map<String, String> MORSE_CODE = new HashMap<String, String>();
    static
    {
        MORSE_CODE.put( "A", ".-" );       MORSE_CODE.put( "B", "-..." );
        MORSE_CODE.put( "C", "-.-." );     MORSE_CODE.put( "D", "-.." );
        MORSE_CODE.put( "E", "." );        MORSE_CODE.put( "F", "..-." );
        MORSE_CODE.put( "G", "--." );      MORSE_CODE.put( "H", "...." );
        MORSE_CODE.put( "I", ".." );       MORSE_CODE.put( "J", ".---" );
        MORSE_CODE.put( "K", "-.-" );      MORSE_CODE.put( "L", ".-.." );
        MORSE_CODE.put( "M", "--" );       MORSE_CODE.put( "N", "-." );
        MORSE_CODE.put( "O", "---" );      MORSE_CODE.put( "P", ".--." );
        MORSE_CODE.put( "Q", "--.-" );     MORSE_CODE.put( "R", ".-." );
        MORSE_CODE.put( "S", "..." );      MORSE_CODE.put( "T", "-" );
        MORSE_CODE.put( "U", "..-" );      MORSE_CODE.put( "V", "...-" );
        MORSE_CODE.put( "W", ".--" );      MORSE_CODE.put( "X", "-..-" );
        MORSE_CODE.put( "Y", "-.--" );     MORSE_CODE.put( "Z", "--.." );
        MORSE_CODE.put( "1", ".----" );    MORSE_CODE.put( "2", "..---" );
        MORSE_CODE.put( "3", "...--" );    MORSE_CODE.put( "4", "....-" );
        MORSE_CODE.put( "5", "....." );    MORSE_CODE.put( "6", "-...." );
        MORSE_CODE.put( "7", "--..." );    MORSE_CODE.put( "8", "---.." );
        MORSE_CODE.put( "9", "----." );    MORSE_CODE.put( "0", "-----" );
        MORSE_CODE.put( "?", "..--.." );   MORSE_CODE.put( "/", "-..-." );
        MORSE_CODE.put( "-", "-...-" );    MORSE_CODE.put( ".", ".-.-.-" );
        MORSE_CODE.put( "(", "-.--.-" );   MORSE_CODE.put( ")", "-.--.-" );
        MORSE_CODE.put( ":", "---..." );   MORSE_CODE.put( ",", "--..--" );
        MORSE_CODE.put( ";", "-.-.-." );   MORSE_CODE.put( "=", "-...-" );
        MORSE_CODE.put( "'", ".----." );   MORSE_CODE.put( "!", "-.-.--" );
        MORSE_CODE.put( "$", "...-..-" );
        MORSE_CODE.put( "@", ".--.-." );   MORSE_CODE.put( "&", "...." );
        
    }
    private static final Map<String, String> CHAR_CODE = new HashMap<String, String>();
    static
    {
        CHAR_CODE.put( ".-", "A" );       CHAR_CODE.put( "-...", "B" );
        CHAR_CODE.put( "-.-.", "C" );     CHAR_CODE.put( "-..", "D" );
        CHAR_CODE.put( ".", "E" );        CHAR_CODE.put( "..-.", "F" );
        CHAR_CODE.put( "--.", "G" );      CHAR_CODE.put( "....", "H" );
        CHAR_CODE.put( "..", "I" );       CHAR_CODE.put( ".---", "J" );
        CHAR_CODE.put( "-.-", "K" );      CHAR_CODE.put( ".-..", "L" );
        CHAR_CODE.put( "--", "M" );       CHAR_CODE.put( "-.", "N" );
        CHAR_CODE.put( "---", "O" );      CHAR_CODE.put( ".--.", "P" );
        CHAR_CODE.put( "--.-", "Q" );     CHAR_CODE.put( ".-.", "R" );
        CHAR_CODE.put( "...", "S" );      CHAR_CODE.put( "-", "T" );
        CHAR_CODE.put( "..-", "U" );      CHAR_CODE.put( "...-", "V" );
        CHAR_CODE.put( ".--", "W" );      CHAR_CODE.put( "-..-", "X" );
        CHAR_CODE.put( "-.--", "Y" );     CHAR_CODE.put( "--..", "Z" );
        CHAR_CODE.put( ".----", "1" );    CHAR_CODE.put( "..---", "2" );
        CHAR_CODE.put( "...--", "3" );    CHAR_CODE.put( "....-", "4" );
        CHAR_CODE.put( ".....", "5" );    CHAR_CODE.put( "-....", "6" );
        CHAR_CODE.put( "--...", "7" );    CHAR_CODE.put( "---..", "8" );
        CHAR_CODE.put( "----.", "9" );    CHAR_CODE.put( "-----", "0" );
        CHAR_CODE.put( "..--..", "?" );   CHAR_CODE.put( "-..-.", "/" );
        CHAR_CODE.put( "-...-", "-" );    CHAR_CODE.put( ".-.-.-", "." );
        CHAR_CODE.put( "-.--.-", "(" );  CHAR_CODE.put( "-.--.-", ")" );
    }
    public static String encode( String str )
    {
        if ( StringUtils.isEmpty( str ) )
        {
            return "";
        }
        str = str.toUpperCase();
        StringBuffer buffer = new StringBuffer();
        for ( int i = 0; i < str.length(); i++ )
        {
            char c = str.charAt( i );
            if ( c == ' ' )
            {
                buffer.append( TWO_SPACE );
                continue;
            }
            if ( MORSE_CODE.containsKey( c + "" ) )
            {
                buffer.append( MORSE_CODE.get( c + "" ) + ONE_SPACE );
            }
            else
            {
                return "FIRST ERROR INPUT AT LINE " + i;
            }
        }
        return buffer.toString();
    }

    public static String decode( String str )
    {
        if ( StringUtils.isEmpty( str ) )
        {
            return "";
        }
        StringBuffer buffer = new StringBuffer();
        String[] arr = str.split( THREE_SPACE );// 先按照单词进行分组
        for ( int i = 0; i < arr.length; i++ )
        {
            if ( StringUtils.isEmpty( arr[i] ) )
            {
                continue;
            }
            buffer.append( decodeWord(arr[i]) ).append( ONE_SPACE ) ;
        }

        return buffer.toString();
    }
    private static String decodeWord(String str)
    {
        if ( StringUtils.isEmpty( str ) )
        {
            return "";
        }
        String[] arr = str.split( ONE_SPACE );
        
        StringBuffer buffer = new StringBuffer();
        for ( int i = 0; i < arr.length; i++ )
        {
            if ( StringUtils.isEmpty( arr[i] ) )
            {
                continue;
            }
            if ( CHAR_CODE.containsKey( arr[i].trim() ) )
            {
                buffer.append( CHAR_CODE.get( arr[i].trim() ) );
            }
            
        }
        return buffer.toString();
    }
    
    
    public static void main( String[] args )
    {
        System.out.println(MorseCodeUtil.encode( "I LOVE YOU" ));
        System.out.println("..   .-.. --- ...- .   -.-- --- ..- ");
        System.out.println(MorseCodeUtil.decode( "..   .-.. --- ...- .   -.-- --- ..- " ));
    }
}
