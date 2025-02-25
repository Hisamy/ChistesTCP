/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clientesocket;

import proxyknockknock.Proxy;

/**
 *
 * @author castr
 */
public class ClienteSocketProxy implements Proxy {

    private ClienteSocket clienteSocket;
    private String host;
    private int puerto;

    public ClienteSocketProxy(String host, int puerto) {
        this.host = host;
        this.puerto = puerto;
    }

    @Override
    public void conectar() {
        if (clienteSocket == null) {
            clienteSocket = new ClienteSocket(host, puerto);
        }
        clienteSocket.conectar();
    }
}
