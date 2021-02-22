package socket_programming_with_java_tcp_ip;

import java.io.*;
import java.net.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class URLExample 
{
    public static void main(String[] args) throws Exception 
    {
        //URL
        //URLConnection
        /*
            http://example.com
        */
        URL url = new URL("http://finance.yahoo.com/q?s=ORCL");
        URLConnection myURL = url.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(myURL.getInputStream()));
        String inputLine;
        
        String pattern = "<span class=\"Trsdu(0.3s) Fw(b) Fz(36px) Mb(-4px) D(ib)\" data-reactid=\"32\">61.13</span>";
        Pattern r = Pattern.compile(pattern);
        while ((inputLine = in.readLine()) != null) 
        {
            //System.out.println(inputLine);
            if(inputLine.contains("Trsdu(0.3s) Fw(b) Fz(36px) Mb(-4px) D(ib)"))
            {
                Matcher m = r.matcher(inputLine);
                if (m.find( )) {
                    System.out.println(m.group(1));
                }
            }
        }
        in.close();
        
    }
}
