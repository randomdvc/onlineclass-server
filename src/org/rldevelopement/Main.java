package org.rldevelopement;

import org.rldevelopement.network.ClientManager;
import org.rldevelopement.network.SQLManager;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    private static ServerSocket server;
    //socket server port on which it will listen
    private static int port = 9876;

    private static SQLManager sql;

    public static void main(String args[]) throws IOException, ClassNotFoundException{
        ClientManager.waitForClient();
        connectToDataBase();
    }

    private static void connectToDataBase() {
            sql = new SQLManager("jdbc:mysql://", "localhost", "onlineclasses", "root", "");
            sql.connect();
    }

}
