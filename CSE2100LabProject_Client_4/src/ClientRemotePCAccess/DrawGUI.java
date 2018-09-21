/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClientRemotePCAccess;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;


public class DrawGUI {
    public static void drawGUI(){
        //A jframe with only a button creates 
        JFrame frame = new JFrame("Remote Admin");
        JButton button= new JButton("Terminate");
        
        //Setting boundaries and alignment of the button
        frame.setBounds(100,100,150,150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //adding button to the frame
        frame.add(button);
        button.addActionListener( new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        }
      );
      frame.setVisible(true);
    }
}
