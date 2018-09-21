/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClientMessageOption;

import java.util.logging.Level;
import java.util.logging.Logger;

//Creates a seperate thread for making the chat more interactive
public class TextFieldShow implements Runnable {
    Thread t;
    TextFieldShow(){
        t=new Thread(this);
        t.start();
    }
    public void run(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(TextFieldShow.class.getName()).log(Level.SEVERE, null, ex);
        }
        ClientMessage.messagingforchat();
    }
    
}
