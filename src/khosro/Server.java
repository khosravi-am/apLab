package khosro;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * server side. get a line in each turn , and returns all lines received to the client
 */
public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(5000);
            while (true) {
                Socket socket = serverSocket.accept();
                new Thread(new RunServer(socket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class RunServer implements Runnable
    {

        Socket socket;

        /**
         * simple constructor.
         * @param socket socket that server accepted.
         */
        public RunServer(Socket socket)
        {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
                Scanner scanner = new Scanner(socket.getInputStream());
                String currentString="";
                StringBuilder stringBuilder = new StringBuilder();
                System.out.println("start");
                while (true) {
                    currentString = scanner.nextLine();
                    if (currentString.equals("over")){
                        outputStream.writeBytes("over");
                        break;
                    }
                    stringBuilder.append(currentString);
                    System.out.println("recived");
                    outputStream.writeBytes(stringBuilder.toString() + "\n");
                    System.out.println("send");
                }
                System.out.println("end");
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
