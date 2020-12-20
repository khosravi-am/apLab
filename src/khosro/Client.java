package khosro;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 * client adapt to send message to Server class.
 */
public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost",5000);
            Scanner scannerSys = new Scanner(System.in);
            Scanner scanner = new Scanner(socket.getInputStream());
            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
            System.out.println("client: start sending");
            String strToSend = "";
            while (true)
            {
                strToSend = scannerSys.nextLine() + "\n";
                outputStream.writeBytes(strToSend);
                if(scanner.hasNext()) {
                    String s=scanner.nextLine();
                    System.out.println(s);
                    if (s.equals("over"))
                        break;

                }

            }
            System.out.println("client: end sending");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

