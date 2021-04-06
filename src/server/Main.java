package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Main
{
    static void Log(String msg) {
        System.out.println(msg);
    }

    public static void main(String[] args) throws IOException {
        System.in.read();

        Controller controller = new Controller();

        ServerSocket listener = null;

        try {
            listener = new ServerSocket(37152, 1, InetAddress.getByName("127.0.0.1"));
            Log("server is started");
        } catch (Exception e) {
            Log("failed to start server: " + e.getMessage());
            return;
        }

        Log("server is listening");

        Socket talkingPlayer1 = null;
        Socket talkingPlayer2 = null;

        DataInputStream inPlayer1 = null;
        DataOutputStream outPlayer1 = null;

        DataInputStream inPlayer2 = null;
        DataOutputStream outPlayer2 = null;

        try {
            talkingPlayer1 = listener.accept();
            Log("player1 is connected");

            inPlayer1 = new DataInputStream(talkingPlayer1.getInputStream());
            outPlayer1 = new DataOutputStream(talkingPlayer1.getOutputStream());

        } catch (Exception e)
        {
            Log("player1 error: " + e.getMessage());
            return;
        }

        try {
            talkingPlayer2 = listener.accept();
            Log("player2 is connected");

            inPlayer2 = new DataInputStream(talkingPlayer1.getInputStream());
            outPlayer2 = new DataOutputStream(talkingPlayer1.getOutputStream());

        } catch (Exception e)
        {
            Log("player2 error: " + e.getMessage());
            return;
        }

        int i, j;
        boolean setSignResult;

        while (true)
        {
            try
            {
                do {
                    outPlayer1.writeUTF(controller.GetFirstPlayerFields());

                    String requestPlayer1 = inPlayer1.readUTF();
                    Log("from player1: " + requestPlayer1);

                    String[] params = requestPlayer1.split("|");
                    i = Integer.parseInt(params[0]);
                    j = Integer.parseInt(params[1]);

                    setSignResult = controller.FirstPlayerShootToSecondPlayer(i,j);

                    String responsePlayer1 = setSignResult == true ? "ok" : "error";

                    outPlayer1.writeUTF(responsePlayer1);
                    Log("to player1: " + responsePlayer1);

                }while (setSignResult == false);

                outPlayer1.writeUTF(controller.GetGameResult());
                outPlayer2.writeUTF(controller.GetGameResult());

                do {
                    outPlayer2.writeUTF(controller.GetSecondPlayerFields());

                    String requestPlayer2 = inPlayer2.readUTF();
                    Log("from player2: " + requestPlayer2);

                    String[] params = requestPlayer2.split("|");
                    i = Integer.parseInt(params[0]);
                    j = Integer.parseInt(params[1]);

                    setSignResult = controller.SecondPlayerShootToFirstPlayer(i,j);

                    String responsePlayer2 = setSignResult == true ? "ok" : "error";

                    outPlayer2.writeUTF(responsePlayer2);
                    Log("to player2: " + responsePlayer2);

                }while (setSignResult == false);

                outPlayer1.writeUTF(controller.GetGameResult());
                outPlayer2.writeUTF(controller.GetGameResult());
            }
            catch (Exception e) {
                Log("some error: " + e.getMessage());
            }
        }

    }
}
