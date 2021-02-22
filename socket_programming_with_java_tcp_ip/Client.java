package socket_programming_with_java_tcp_ip;

import java.net.*;
import java.io.*;

public class Client{
    
    public static void main(String [] args){
        String host = "localhost";
        try {
            InetAddress serverAddress = InetAddress.getByName(host);
            System.out.println("Sever address IP : " + serverAddress.getAddress());
            Socket socket = new Socket(serverAddress,8080);
            PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println(input.readLine());
            out.println("Recieved new client request....");
            input.close();
            out.close();
            socket.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}