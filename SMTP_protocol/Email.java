package SMTP_protocol;

import java.io.*;
import java.net.*;
import java.util.Scanner;


public class Email{
    private static Socket smtpSocket;
    private static PrintWriter out;
    private static BufferedReader input;
    
    static void CommunicationResponse(String ResponseCode){
        String response;
        try {
            while ((response = input.readLine()) != null){
                System.out.println("SERVER : " + response);
                if (response.indexOf(ResponseCode) != -1){
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public static void main(String [] args){
        /* 
        INITIALIZATION:
        ==============
        Try opening a socekt at port 25 as well as in/ouput streams
        */
        try {
            smtpSocket = new Socket("localhost",25);
            input = new BufferedReader(new InputStreamReader(smtpSocket.getInputStream()));
        } catch (Exception e) {
            System.out.println(e);
        }

        //now write the data we need to the port connection established above
        if (smtpSocket != null && out != null && input != null){
            //Get the greeting from the server
            try {
                String ResponseCode = "220";
                CommunicationResponse(ResponseCode);

                //Client initiates dialog and identifying itself
                out.println("HELO " + InetAddress.getLocalHost().getHostAddress());
                System.out.println("HELO " + InetAddress.getLocalHost().getHostAddress());
                ResponseCode = "250";
                CommunicationResponse(ResponseCode);

                //now client informs the server of the address of origin using MAIL FROM command
                String Sender = "michaelpmagaisa@outlook.com";
                out.println("MAIL From : " + Sender);

                ResponseCode = "250";
                CommunicationResponse(ResponseCode);

                //client specifies receipient
                String Receiver = "michaelpmagaisa@gmail.com";
                out.println("RCPT TO: " + Receiver);
                ResponseCode = "250";
                CommunicationResponse(ResponseCode);

                //send the data
                out.println("DATA");
                ResponseCode = "354";
                CommunicationResponse(ResponseCode);

                //Send out the email
                out.println("From : " + Sender);
                out.println("To : " + Receiver);
                out.println("Subject : Your Application\n");
                
                //get email message from a text file
                try {
                    File EmailMessageFile = new File("email_message.txt");
                    Scanner ReadEmailFile = new Scanner(EmailMessageFile);
                    while(ReadEmailFile.hasNextLine()){
                        String CurrentLine = ReadEmailFile.nextLine();
                        out.println(CurrentLine);
                    }
                    ReadEmailFile.close();
                    out.println("\n.");
                }catch(FileNotFoundException e){
                    System.out.println("Email message file not found....\n\n" + e);
                }

                ResponseCode = "250";
                CommunicationResponse(ResponseCode);

                //send the quit command
                out.println("QUIT");
                ResponseCode = "221";
                CommunicationResponse(ResponseCode);
                System.out.println("Email successfully sent!");

                //close open resources
                out.close();
                input.close();
                smtpSocket.close();

            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}