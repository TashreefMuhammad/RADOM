
package ClientRemotePCAccess;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
    

//This class is responsible for connecting to the server
//and starting ScreenSpyer and ServerDelegate classes

public class ClientInitiator extends Thread{
    //The socket variable to connect with the server
    Socket socket = null;
    
    //ip address and port number for connecting socket
    String ip;
    int port;
    
    //Take the instances from the jframe and initialize the ip address and port 
    //of server
    public ClientInitiator(String add,int po){
        ip=add;
        port=po;
        start();
    }

    @Override
    public void run(){

        Robot robot = null; //Used to capture the screen and taking commands
        Rectangle rectangle = null; //Used to represent screen dimensions

        try {
            System.out.println("Connecting to server ..........");
            socket = new Socket(ip, port);
            System.out.println("Connection Established.");

            //Get default screen device
            GraphicsEnvironment gEnv=GraphicsEnvironment.getLocalGraphicsEnvironment();
            GraphicsDevice gDev=gEnv.getDefaultScreenDevice();

            //Get screen dimensions
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            rectangle = new Rectangle(dim);

            //Prepare Robot object
            robot = new Robot(gDev);

            //draw client gui
            DrawGUI.drawGUI();
            //ScreenSpyer sends screenshots of the client screen
            new ScreenSpyer(socket,robot,rectangle);
            //ServerDelegate recieves server commands and execute them
            new ServerDelegate(socket,robot);
        } catch (UnknownHostException ex) {
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println(ex);
        } catch (AWTException ex) {
                System.out.println(ex);
        }
    }
    
}
