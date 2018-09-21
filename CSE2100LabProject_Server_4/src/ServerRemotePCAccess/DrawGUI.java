/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServerRemotePCAccess;

import java.awt.BorderLayout;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;

//Draw the frame where the client's pc is shown

public class DrawGUI {
    public static void drawGUI(JFrame frame,JDesktopPane desktop){
            frame.add(desktop,BorderLayout.CENTER);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            //Show the frame in a maximized state
            frame.setExtendedState(frame.getExtendedState()|JFrame.MAXIMIZED_BOTH);
            frame.setVisible(true);
    }
}
