
package ServerRemotePCAccess;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;

//This is the entry class of the server

public class ServerInitiator extends Thread {
    //Main server frame
    public JFrame frame = new JFrame();
    //JDesktopPane represents the main container that will contain all
    //connected clients' screens
    public JDesktopPane desktop = new JDesktopPane();
    int port;

    
    public ServerInitiator(int p){
        port=p;             //port number for connecting server socket
        
        start();
    }

    //public void initialize(int port){
    @Override
    public void run() {
        try{
            ServerSocket sc = new ServerSocket(port);
            //Show Server GUI
            
            DrawGUI.drawGUI(frame,desktop);
            //Listen to server port and accept clients connections
            while(true){
                Socket client = sc.accept();
                System.out.println("New client Connected to the server");
                //Per each client create a ClientHandler
                new ClientHandler(client,desktop);
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }

    
    }
}
