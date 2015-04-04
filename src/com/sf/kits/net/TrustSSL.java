package com.sf.kits.net;

import java.io.*;
import java.net.*;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.*;

public class TrustSSL
{
    private static class TrustAnyTrustManager implements X509TrustManager
    {
        public void checkClientTrusted( X509Certificate[] chain, String authType ) throws CertificateException
        {
        }

        public void checkServerTrusted( X509Certificate[] chain, String authType ) throws CertificateException
        {
        }

        public X509Certificate[] getAcceptedIssuers()
        {
            return new X509Certificate[] {};
        }
    }

    private static class TrustAnyHostnameVerifier implements HostnameVerifier
    {
        public boolean verify( String hostname, SSLSession session )
        {
            return true;
        }
    }

    @SuppressWarnings( { "deprecation", "null" } )
    public static String requestHttps( String req_url )
    {
        InputStream in = null;
        OutputStream out = null;
        String str_return = "";

        try
        {
            SSLContext sc = SSLContext.getInstance( "SSL" );
            sc.init( null, new TrustManager[] { new TrustAnyTrustManager() }, new java.security.SecureRandom() );
            URL console = new URL( req_url );
            HttpsURLConnection conn = ( HttpsURLConnection ) console.openConnection();
            conn.setSSLSocketFactory( sc.getSocketFactory() );
            conn.setHostnameVerifier( new TrustAnyHostnameVerifier() );
            conn.connect();
            InputStream is = conn.getInputStream();
            DataInputStream indata = new DataInputStream( is );
            String ret = "";
            while ( ret != null )
            {
                ret = indata.readLine();
                if ( ret != null && !ret.trim().equals( "" ) )
                {
                    str_return = str_return + new String( ret.getBytes( "ISO-8859-1" ), "UTF-8" );
                }
            }
            conn.disconnect();
        }
        catch ( ConnectException e )
        {
            System.out.println( "ConnectException" );
            System.out.println( e );
            return "ERROR";
        }
        catch ( IOException e )
        {
            System.out.println( "IOException" );
            System.out.println( e );
            return "ERROR";
        }
        catch ( NoSuchAlgorithmException e )
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "ERROR";
        }
        catch ( KeyManagementException e )
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "ERROR";
        }
        finally
        {
            try
            {
                in.close();
            }
            catch ( Exception e )
            {
            }
            try
            {
                out.close();
            }
            catch ( Exception e )
            {
            }
        }
        return str_return;
    }

}
