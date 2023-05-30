import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.*;

import static java.lang.Thread.sleep;

public class MultiEchoServer {
    private static ServerSocket serverSock;
    private static final int PORT = 1234;
    private static int clientNumber = 5 ;
    private static ArrayList<ClientHandler> clientHandler = new ArrayList<ClientHandler>();

    private static List<Message> gotMessagelist = Collections.synchronizedList(new ArrayList<Message>());

    public static void main(String[] args) throws IOException, InterruptedException {

        try {
            //create server socket object
            serverSock = new ServerSocket(PORT);
        } catch (IOException e) {
            System.out.println("Can't listen on " + PORT);
            System.exit(1);
        }
        //create socket object
        Socket client = null;
        System.out.println("Listening for connection...");

        //for each client detect, start the client handler
        for (int i = 0; i < clientNumber; i++) {
            try {
                client = serverSock.accept();
                System.out.println("New client accepted");
                ClientHandler handler = new ClientHandler(client, gotMessagelist);

                clientHandler.add(handler);
                handler.start();
            } catch (IOException e) {
                System.out.println("Accept failed");
                System.exit(1);
            }

            System.out.println("Connection successful");
            System.out.println("Listening for input ...");
        }
        sleep(4000 * clientNumber);
        for (int i = 0; i < clientHandler.size(); i++) {
            Message message = new Message(clientHandler.get(i).getClientName(),
                    clientHandler.get(i).getClientName() + " has joined");
            gotMessagelist.add(message);
            System.out.println(message.getMessage());
        }
    }
}

class ClientHandler extends Thread {
    private Socket client;
    private BufferedReader in;
    private PrintWriter out;
    private String clientName;

    private List<Message> gotMessagelist;

    private int messageNumber = 0;

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public ClientHandler(Socket socket, List<Message> gotMessagelist) {
        // The name of the client is read from the keyboard and sent to the server.
        //save the name to broadcast it later
        client = socket;
        this.gotMessagelist = gotMessagelist;
        //create the mirror for each client
        try {
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            out = new PrintWriter(client.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //receive data from client and send to client
    public void run() {
        //receive data from client
        try {
            clientName = in.readLine();

            String received;
            do {
                //message from the client
                received = in.readLine();
                //if the received message is empty, send message from other client
                if (received.isEmpty()) {
                    //when there is still message to send
                    if (messageNumber < gotMessagelist.size()) {
                        //get the message from the list
                        Message message = gotMessagelist.get(messageNumber);

                        //when the message is from the same client, keep searching the message
                        while (message.getSender().equals(clientName)) {
                            //get the next message
                            messageNumber = messageNumber + 1;
                            if (messageNumber < gotMessagelist.size()){
                                message = gotMessagelist.get(messageNumber);
                            }
                            //no message to search for anymore, break the loo
                            else{
                                message = null;
                                //break the loop
                                break;
                            }
                        }
                        //if it's the same client
                        //then don't print out the message from the client
                        if (message != null) {
                            String realMessage = message.getMessage();
                            out.println(realMessage);
                            messageNumber = messageNumber + 1;
                        }
                        else {
                            out.println("No message to send!");
                        }
                    }
                    //if there are no message left to send then inform the client
                    else {
                        out.println("No message to send!");
                    }
                }
                //if the received message is not empty
                //add the received message to the list
                else {
                    Message message = new Message(clientName, "Message from " + clientName + ": " + received);
                    gotMessagelist.add(message);
                    System.out.println(message.getMessage());
                }

            }
            while (!received.equals("BYE"));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //close connection
            try {
                if (client != null) {
                    System.out.println("Closing down connection...");
                    client.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

