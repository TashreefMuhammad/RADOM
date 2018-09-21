
package ClientRemotePCAccess;

import java.awt.Robot;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/*
 * Used to recieve server commands then execute them at the client side
 */
class ServerDelegate extends Thread {
     //Connects the socket with server
    Socket socket = null;
    //A variable to send and receive command
    Robot robot = null;

    public ServerDelegate(Socket socket, Robot robot) {
        this.socket = socket;
        this.robot = robot;
        start(); //Start the thread and hence calling run method
    }

    @Override
    public void run(){
        Scanner scanner = null;
        try {
            //prepare Scanner object
            System.out.println("Preparing InputStream");
            scanner = new Scanner(socket.getInputStream());

            while(true){
                //recieve commands and respond accordingly
                System.out.println("Waiting for command");
                int command = scanner.nextInt();
                System.out.println("New command: " + command);
                switch(command){
                    case -1:
                        robot.mousePress(scanner.nextInt());
                    break;
                    case -2:
                        robot.mouseRelease(scanner.nextInt());
                    break;
                    case -3:
                        robot.keyPress(scanner.nextInt());
                    break;
                    case -4:
                        robot.keyRelease(scanner.nextInt());
                    break;
                    case -5:
                        robot.mouseMove(scanner.nextInt(), scanner.nextInt());
                    break;
                }
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

}
