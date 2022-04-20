
import java.io.*;
import java.net.*;
import java.util.Scanner;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ASUS
 */
public class serverapp {
    public static void main(String[] args) throws IOException{
        ServerSocket server=null;
        Socket client=null;
        byte[] receiveBuf=new byte[64];
        int recvMsgSize;
        try {
            server=new ServerSocket(8181);
            System.out.println("Server started");
            client=server.accept();
            System.out.println("Client connected");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(-1);
        }
        InputStream in=client.getInputStream();
        OutputStream out=client.getOutputStream();
        
        System.out.println("Beri Salam :");
            Scanner scanner=new Scanner(System.in);
            String data=scanner.nextLine();
            out.write(data.getBytes());
        java.util.Arrays.fill(receiveBuf, (byte)0);
     
                while (true) {
            recvMsgSize=in.read(receiveBuf);
            data=new String(receiveBuf);
            if (data.trim().equals("Keluar")) {
                out.write(data.getBytes());
                break;
            }
            java.util.Arrays.fill(receiveBuf, (byte)0);
            System.out.println("Client: "+data);
        }
        out.close();
        in.close();
        client.close();
        server.close();

    }
}
