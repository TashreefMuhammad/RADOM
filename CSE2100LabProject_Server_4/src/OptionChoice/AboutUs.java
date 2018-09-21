/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OptionChoice;

import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class AboutUs implements MouseMotionListener{
    public static void main(String args[]) {
        //Strings to take the file locations of the images
        String s1 = System.getProperty("user.dir") + "\\Muhammad.jpg";
        String s2 = System.getProperty("user.dir") + "\\Arafat.jpg";
        String s3 = System.getProperty("user.dir") + "\\background.jpg";
        String s4 = System.getProperty("user.dir") + "\\A_info.jpg";
        String s5 = System.getProperty("user.dir") + "\\M_info.jpg";
        String s6 = System.getProperty("user.dir") + "\\Blank Screen.jpg";
        
        //Setting ImageIcon variables with predeclared file locations
        ImageIcon img1 = new ImageIcon(s1);
        ImageIcon img2 = new ImageIcon(s2);
        ImageIcon img3 = new ImageIcon(s3);
        ImageIcon img4 = new ImageIcon(s4);
        ImageIcon img5 = new ImageIcon(s5);
        ImageIcon img6 = new ImageIcon(s6);
        
        //button to go back to the main frame
        JButton bt = new JButton("Back");
        
        //Buttons to show our images
        JButton Aim = new JButton("");
        JButton Mim = new JButton("");
        //Declaring a jframe and a panel to display
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        
        //Used to control the change of cursor
        JLabel label1 = new JLabel();
        JLabel label2 = new JLabel();
        
        //label for a background image
        JLabel label3 = new JLabel();
        
        //label for a blank screen where the info will show
        JLabel label4 = new JLabel();
        
        //Images with Info about us
        JLabel labelA1 = new JLabel();
        JLabel labelM1 = new JLabel();
        
        panel.setLayout(null);
        
        //Setting the images of the labels
        Mim.setIcon(img1); //Muhammad's image
        Aim.setIcon(img2); //Arafat's image
        labelA1.setIcon(img4); //Arafat's Info image
        labelM1.setIcon(img5); //Muhammad's Info image 
        label4.setIcon(img6); //A blank screen image when the info is not showing
        
        //adding the background label to the panel
        panel.add(label3);
        
        //Adding the button to the panel for mailing to Muhammad and its Function
        panel.add(Mim);
        Mim.addActionListener( new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                        Desktop.getDesktop().mail(new URI("mailto:tashreef.muhammad@gmail.com?subject=TEST"));
                    } catch (URISyntaxException | IOException ex) {
                        System.out.println(ex);
                        }
            }
        }
        );
        
        //Adding the button to the panel for mailing to Arafat and its Function
        panel.add(Aim);
        Aim.addActionListener( new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                        Desktop.getDesktop().mail(new URI("mailto:arafat.uddin2@gmail.com?subject=TEST"));
                    } catch (URISyntaxException | IOException ex) {
                        System.out.println(ex);
                        }
            }
        }
      );
        
        //Adding a back button to the panel
        panel.add(bt);
        
        //Setting boundaries and alignment forthe buttons
        Mim.setBounds(10, 10, 250, 250);
        Aim.setBounds(300, 10, 250, 250);
        bt.setBounds(450,550,100,50);
        
        //Function for back button
        bt.addActionListener( new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                ChooseYourOption.main(new String[0]);
            }
        }
        );
        
        //Adding Info Images of both of us to panel
        panel.add(labelA1);
        panel.add(labelM1);
        
        //Setting boundaries and alignment for the info images
        labelA1.setBounds(100, 350, 380, 180);
        labelM1.setBounds(100, 350, 380, 180);
        
        //Adding the panel along with the labels amd buttons
        frame.add(panel);
        
        //Setting the info images' visibility false
        //So it can be show according to actions
        labelA1.setVisible(false);
        labelM1.setVisible(false);
        
        //Functions of actions to show the info images 
        panel.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                final int inx = e.getX();
                final int iny = e.getY();
                System.out.println(inx+","+iny);
                
                //conditions to show Muhammad's info image
                if (inx >= 5 && inx <= 265 && iny >= 5 && iny <= 265) {
                    System.out.println("found m");
                    labelM1.setVisible(true);
                    
                    //Setting the normal cursor icon to hand cursor
                    Mim.setCursor(new Cursor(Cursor.HAND_CURSOR));
                    label1.addMouseListener(new MouseAdapter() {
                    @Override
                    //Funtion to mail to Muhammad on pressing the mouse button
                    public void mousePressed(MouseEvent e) {
                    try {
                        Desktop.getDesktop().mail(new URI("mailto:tashreef.muhammad@gmail.com?subject=TEST"));
                    } catch (URISyntaxException | IOException ex) {
                        System.out.println(ex);
                        }
                    }
                    });
                    //Condtion for showing Arafat's info image
                } else if (inx >= 295 && inx <= 555 && iny >= 5 && iny <= 265) {
                    System.out.println("found a");
                    labelA1.setVisible(true);
                    //Setting the normal cursor icon to hand cursor
                    Aim.setCursor(new Cursor(Cursor.HAND_CURSOR));
                    label2.addMouseListener(new MouseAdapter() {
                    @Override
                    //Funtion to mail to Arafat on pressing the mouse button
                    public void mousePressed(MouseEvent e) {
                        try {
                            Desktop.getDesktop().mail(new URI("mailto:arafat.uddin2@gmail.com?subject=TEST"));
                        } catch (URISyntaxException | IOException ex) {
                            System.out.println(ex);
                            }
                        }
                    });
                }else{
                    System.out.println("found none");
                    labelA1.setVisible(false);
                    
                    labelM1.setVisible(false);
                    
                }
            }
        });
        //Setting the image of blank screen, and boundaries and alignment of the
        //image and adding it to the panel 
        label4.setIcon(img6);
        label4.setBounds(100,350, 380, 180);
        panel.add(label4);
        
        //Setting the image of background screen, and boundaries and alignment of the
        //image and adding it to the panel 
        label3.setIcon(img3);
        label3.setBounds(0,0,580,700);
        panel.add(label3);
        
        //Adding the title and size of the frame and making it visible on function call 
        
        frame.setTitle("About Us");
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width/2-frame.getSize().width/2-290, dim.height/2-frame.getSize().height/2-350);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(580, 700);
        frame.setVisible(true);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
