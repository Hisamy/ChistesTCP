/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientesocket;

/**
 *
 * @author castr
 */
public class ClienteSocket{

    private String host;
    
    private int puerto;

    public ClienteSocket(String host, int puerto) {
        this.host = host;
        this.puerto = puerto;
    }

    public static void main(String[] args) {
        ClienteSocketProxy proxy = new ClienteSocketProxy("localhost", 4444);
        System.out.println("Escriba sus respuestas siguiendo el juego de Knock Knock!");
        proxy.enviarMensaje();
    }    
    
    
}
