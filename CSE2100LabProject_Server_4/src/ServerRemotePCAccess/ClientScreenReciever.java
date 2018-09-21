
package ServerRemotePCAccess;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.io.ObjectInputStream;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

// ClientScreenReciever is responsible for receiving client screen shot and displaying
// it in the server. Each connected client has a separate object of this class
 
class ClientScreenReciever extends Thread{

    private ObjectInputStream cObjectInputStream = null;
    private JPanel cPanel = null;

    public ClientScreenReciever(ObjectInputStream ois, JPanel p) {
        cObjectInputStream = ois;
        cPanel = p;
        //start the thread and thus call the run method
        start();
    }

    public void run(){
        try {        
            //Read screenshots of the client then draw them
            while(true){
                //Recieve client screenshot and resize it to the current panel size
                ImageIcon imageIcon = (ImageIcon) cObjectInputStream.readObject();
                System.out.println("New image recieved");
                Image image = imageIcon.getImage();
                image = image.getScaledInstance(cPanel.getWidth(),cPanel.getHeight(),Image.SCALE_FAST);
                //Draw the recieved screenshot
                Graphics graphics = cPanel.getGraphics();
                graphics.drawImage(image, 0, 0, cPanel.getWidth(),cPanel.getHeight(),cPanel);
            }
        } catch (IOException | ClassNotFoundException ex) {
                System.out.println(ex);
        }
        
    }
}
