package org.rldevelopement.network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ClientManager {

    private static ServerSocket server;
    //socket server port on which it will listen
    private static int port = 9876;

    private static ObjectInputStream ois;
    private static ObjectOutputStream oos;

    public static void waitForClient() throws IOException, ClassNotFoundException {
        //create the socket server object
        server = new ServerSocket(port);
        //keep listens indefinitely until receives 'exit' call or program terminates
        while(true){
            System.out.println("Waiting for the client request");
            //creating socket and waiting for client connection
            Socket socket = server.accept();
            System.out.println("Client connected : " + socket.getInetAddress());
            //read from socket to ObjectInputStream object
            ois = new ObjectInputStream(socket.getInputStream());
            //convert ObjectInputStream object to String
            Object message = ois.readObject();
            System.out.println("Message Received: " + message.toString());
            //create ObjectOutputStream object
            oos = new ObjectOutputStream(socket.getOutputStream());
            //write object to Socket
            if(!message.toString().equalsIgnoreCase("exit")) {
                System.out.println(sendToClient("Hi Client"));
            } else {
                oos.writeObject("Shutting Down Server...");
            }
            //close resources
            /*ois.close();
            oos.close();*/
            //socket.close();
            //terminate the server if client sends exit request
            if(message.toString().equalsIgnoreCase("exit")) break;
        }
        System.out.println("Shutting down Socket server!!");
        //close the ServerSocket object
        server.close();
        //return;
    }

    public static Object sendToClient(Object object) throws IOException {

        oos.writeObject(object);

        Object response = ois.read();

        return response;
    }

}
