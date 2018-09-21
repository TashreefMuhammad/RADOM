package ServerRemotePCAccess;

import java.awt.BorderLayout;
import java.awt.Rectangle;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;

class ClientHandler extends Thread {

    public JDesktopPane desktop = null;
    public Socket cSocket = null;
    public JInternalFrame interFrame = new JInternalFrame("Client Screen", true, true, true);
    public JPanel cPanel = new JPanel();

    public ClientHandler(Socket cSocket, JDesktopPane desktop) {
        this.cSocket = cSocket;
        this.desktop = desktop;
        start();
    }

    //Draw GUI per each connected client
    public void drawGUI() {
        interFrame.setLayout(new BorderLayout());
        interFrame.getContentPane().add(cPanel, BorderLayout.CENTER);
        interFrame.setSize(100, 100);
        desktop.add(interFrame);
        try {
            //Initially show the internal frame maximized
            interFrame.setMaximum(true);
        } catch (PropertyVetoException ex) {
            System.out.println(ex);
        }
        //this allows to handle KeyListener events
        cPanel.setFocusable(true);
        interFrame.setVisible(true);
    }

    public void run() {

        //used to represent client screen size
        Rectangle clientScreenDim = null;
        //Used to read screenshots and client screen dimension
        ObjectInputStream ois = null;

        try {
            //Read client screen dimension
            ois = new ObjectInputStream(cSocket.getInputStream());
            clientScreenDim = (Rectangle) ois.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println(ex);
        }
        //Start recieveing screenshots
        //new ClientScreenReciever(ois,cPanel);
        new ClientScreenReciever(ois, cPanel);
        //Start sending events to the client
        new ClientCommandsSender(cSocket, cPanel, clientScreenDim);
        drawGUI();
    }

}
