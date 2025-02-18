/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejemplosockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author castr
 */
public class ChistesSkeleton implements Runnable {

    private Socket clientSocket;
    private KnockKnockProtocol protocol;
    private PrintWriter out;
    private BufferedReader in;

    public ChistesSkeleton(Socket socket) throws IOException {
        this.clientSocket = socket;
        this.protocol = new KnockKnockProtocol();
        this.out = new PrintWriter(socket.getOutputStream(), true);
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    @Override
    public void run() {
        try {
            String inputLine;
            String outputLine;

            outputLine = protocol.processInput(null);
            out.println(outputLine);

            while ((inputLine = in.readLine()) != null) {
                outputLine = protocol.processInput(inputLine);
                out.println(outputLine);
                if (outputLine.equals("Bye.")) {
                    break;
                }
            }
            cerrar();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void cerrar() throws IOException {
        out.close();
        in.close();
        clientSocket.close();
    }

}
