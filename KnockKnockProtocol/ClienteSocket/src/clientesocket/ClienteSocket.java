/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientesocket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gilberto.borrego
 */
public class ClienteSocket implements Cliente {

    private String host;
    private int puerto;
    private Socket kkSocket;
    private PrintWriter out;
    private BufferedReader in;

    public ClienteSocket(String host, int puerto) {
        this.host = host;
        this.puerto = puerto;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Cliente cliente = new ClienteSocketProxy("localhost", 4444);
        cliente.conectar();

    }

    @Override
    public void conectar() {

        try {
            kkSocket = new Socket(host, puerto);
            out = new PrintWriter(kkSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(kkSocket.getInputStream()));
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

            String fromServer;
            String fromUser;

            while ((fromServer = in.readLine()) != null) {
                System.out.println("Server: " + fromServer);
                if (fromServer.equals("Bye.")) {
                    break;
                }

                fromUser = stdIn.readLine();
                if (fromUser != null) {
                    System.out.println("Client: " + fromUser);
                    out.println(fromUser);
                }
            }
            out.close();
            in.close();
            stdIn.close();
            kkSocket.close();
        } catch (IOException ex) {
            Logger.getLogger(ClienteSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
