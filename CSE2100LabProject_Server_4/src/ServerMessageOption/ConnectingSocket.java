
package ServerMessageOption;

import java.io.IOException;
import java.net.ServerSocket;
import static ServerMessageOption.ServerMessage.*;

//Whole function is for to open a socket and 
// Creating separate threads
//One takes input from server and shows in textfield
//The other waits for input from socket and displays the client's input
public class ConnectingSocket {
    public static void connectingsocket(){
        try{
            //Opening a server socket with the port number gotten from the Port_Input class
            ss=new ServerSocket(Port_Input.port);
            System.out.println("Waiting for connection");
            s=ss.accept();
            System.out.println("Connection established");
            
            //Creates a seperate thread for making the chat more interactive
            TextFieldShow t=new TextFieldShow();
            
            //After the connecting with the client the frame for messaging pop up
            ServerMessage.main(new String[0]);
        }catch(IOException ex){
            System.out.println(ex);
        }
    }
}
