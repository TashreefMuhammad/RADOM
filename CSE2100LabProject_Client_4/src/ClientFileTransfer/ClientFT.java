package ClientFileTransfer;

import java.net.*;
import java.io.*;
import javax.swing.JOptionPane;

public class ClientFT { 
    public static void ClientFTWork(Socket socket){
        //A variable to take input from the connected socket
        InputStream is=null;
        try {
            //Receiving input from socket
            is = socket.getInputStream();
            
            //Extra variables for cut off unnecessary data
            int bytesRead,currentTot;
            
            //For receiving dat from the connected socket
            DataInputStream din=new DataInputStream(is);
            
            //Assigning the received filesize data to an integer
            int filesize=din.readInt();
            
            //Increasing the size of file as the filesize on disk is greater 
            //than normal file size
            filesize*=3;
            
            //The variable which shall write the file on the client pc
            byte [] bytearray = new byte [filesize];
            
            //Assign data to a string received from the socket
            String st=din.readUTF();
            
            //Initial directory for saving the file
            String s="C:\\Users\\"+System.getProperty("user.name")+"\\Desktop\\"+st;
            
            //Creating a new file
            File f=new File(s);
            
            //Checks if the file already exists in the given location using name
            while(f.exists())
            {
                //Takes new name from user
                st=JOptionPane.showInputDialog("The Requested File name "+st+" is already in use.\nUse a different name",st);
                s="C:\\Users\\"+System.getProperty("user.name")+"\\Desktop\\"+st;
                File temp=new File(s);
                f=temp;
            }
            
            //Variable to write the file on client pc as the given name
            FileOutputStream fos = new FileOutputStream(s);
            
            //Cutting off the unnecessary data from the input stream 
            //and write it to the specific file
            try (BufferedOutputStream bos = new BufferedOutputStream(fos)) {
                bytesRead = is.read(bytearray,0,bytearray.length);
                currentTot = bytesRead;
                do {
                    bytesRead = is.read(bytearray, currentTot, (bytearray.length-currentTot));
                    if(bytesRead >= 0)
                        currentTot += bytesRead;
                } while(bytesRead > -1);
                bos.write(bytearray, 0 , currentTot);
                bos.flush();
            }
            
            //Close the connection
            socket.close();
            
            JOptionPane.showMessageDialog(null,"File Received");
        } catch (IOException ex) {
            System.out.println(ex);
        } finally {
            try {
                is.close();
            } catch (IOException ex) {
                System.out.println(ex);
            }
        }
    }
}