package Start;

import com.sf.kits.net.HttpRequestUtil;

public class RequestURL
{
    public static void main( String[] args )
    {
        HttpRequestUtil.httpRequest( "http://localhost:8010/ticket" );
    }
}
