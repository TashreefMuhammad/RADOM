

package ClientRemotePCAccess;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import javax.swing.ImageIcon;

// This class is responsible for sending screen shot every predefined duration

class ScreenSpyer extends Thread {

    Socket socket = null; 
    Robot robot = null; // Used to capture screen
    Rectangle rectangle = null; //Used to represent screen dimensions
    
    public ScreenSpyer(Socket socket, Robot robot,Rectangle rect) {
        this.socket = socket;
        this.robot = robot;
        rectangle = rect;
        start();
    }

    @Override
    public void run(){
        ObjectOutputStream oos = null; //Used to write an object to the streem


        try{
            //Prepare ObjectOutputStream
            oos = new ObjectOutputStream(socket.getOutputStream());
            
            // Send screen size to the server in order to calculate correct mouse
            // location on the server's panel
            
            oos.writeObject(rectangle);
        }catch(IOException ex){
            System.out.println(ex);
        }

       while(true){
            //Capture screen
            BufferedImage image = robot.createScreenCapture(rectangle);
            //Wraped BufferedImage with ImageIcon because BufferedImage class
            //does not implement Serializable interface
            
            ImageIcon imageIcon = new ImageIcon(image);

            //Send captured screen to the server
            try {
                System.out.println("before sending image");
                oos.writeObject(imageIcon);
                oos.reset(); //Clear ObjectOutputStream cache
                System.out.println("New screenshot sent");
            } catch (IOException ex) {
               System.out.println(ex);
            }

            //wait for 100ms to reduce network traffic
            try{
                Thread.sleep(100);
            }catch(InterruptedException e){
                System.out.println(e);
            }
        }

    }

}
