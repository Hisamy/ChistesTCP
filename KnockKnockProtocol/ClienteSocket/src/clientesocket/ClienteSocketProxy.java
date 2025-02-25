/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clientesocket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import proxyknockknock.IProxy;

/**
 *
 * @author castr
 */
public class ClienteSocketProxy implements IProxy {

    private ClienteSocket clienteSocket;
    private String host;
    private int puerto;
    private StubChistes stubChistes;

    public ClienteSocketProxy(String host, int puerto) {
        this.host = host;
        this.puerto = puerto;
    }

    @Override
    public void enviarMensaje() {
        if (clienteSocket == null) {
            clienteSocket = new ClienteSocket(host, puerto);
        }
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
