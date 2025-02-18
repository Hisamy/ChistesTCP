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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author castr
 */
public class ClienteSocket implements Cliente {

    private String host;
    private int puerto;
    private StubChistes stubChistes;

    public ClienteSocket(String host, int puerto) {
        this.host = host;
        this.puerto = puerto;
    }

    public static void main(String[] args) {
        Cliente cliente = new ClienteSocketProxy("localhost", 4444);
        System.out.println("Escriba sus respuestas siguiendo el juego de Knock Knock!");
        cliente.conectar();
    }

    @Override
    public void conectar() {

        try {
            stubChistes = new StubChistes(host, puerto);
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

            String fromServer;
            String fromUser;

            while ((fromServer = stubChistes.obtenerChiste()) != null) {
                System.out.println("Server: " + fromServer);
                if (fromServer.equals("Bye.")) {
                    break;
                }

                fromUser = stdIn.readLine();
                if (fromUser != null) {
                    System.out.println("Client: " + fromUser);
                    stubChistes.responder(fromUser);
                }
            }

            stubChistes.cerrar();
            stdIn.close();

        } catch (IOException ex) {
            Logger.getLogger(ClienteSocket.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ClienteSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
