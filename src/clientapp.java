/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.*;
import java.net.*;
import java.util.Scanner;
/**
 *
 * @author ASUS
 */
public class clientapp {
    public static void main(String[] args)throws IOException{
        Socket client=null;
        InputStream in=null;
        OutputStream out=null;
        byte[] receiveMsg=new byte[64];
        try {
            System.out.print("Alamat Ip Server :");
            Scanner inputIp=new Scanner(System.in);
            String IPSERVER=inputIp.nextLine();
            client=new Socket(IPSERVER,8181);
            in = client.getInputStream();
            out = client.getOutputStream();
        } catch (UnknownHostException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }catch (IOException e){
            System.err.println(e.getMessage());
            System.exit(1);
        }
        String fromServer;
        String fromUser;
        
        do {
            in.read(receiveMsg);
            fromServer=new String(receiveMsg);
            System.out.println("Server: "+fromServer);
            
            System.out.println("Client :");
            Scanner scanner=new Scanner(System.in);
            String fromUser1=scanner.nextLine();
            System.out.println("Kirim Server"+fromUser1);
            out.write(fromUser1.getBytes());
            fromUser=fromUser1;
            
        } while (fromUser!="K");      
        in.read(receiveMsg);
        fromServer=new String(receiveMsg);
        System.out.println("Server: "+fromServer);
        fromUser="Salam dari client";
        System.out.println("Kirim ke server: "+ fromUser);
        out.write(fromUser.getBytes());
        
        fromUser="Ini data dari client";
        System.out.println("Kirim Ke server");
        out.write( fromUser.getBytes());
        
        fromUser="Keluar";
        out.write(fromUser.getBytes());
        System.out.println("Kirim ke server: "+fromUser);
        
        out.close();
        in.close();
        client.close();
    }
    
}
