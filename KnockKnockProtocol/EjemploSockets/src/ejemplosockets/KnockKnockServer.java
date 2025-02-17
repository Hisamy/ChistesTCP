/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplosockets;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author gilberto.borrego
 */
public class KnockKnockServer {

    public static void main(String[] args) throws IOException {
        
        ExecutorService servicio = Executors.newCachedThreadPool();
        
        try (ServerSocket servidorSocket = new ServerSocket(4444)) {
            System.out.println("Servidor multihilo iniciando en el puerto 4444");
            Socket clientSocket = null;
            while (true) {
                clientSocket = servidorSocket.accept();
                System.out.println("Acepté a un cliente");
                servicio.execute(new KnockKnockClientManager(clientSocket));
            }
        } catch (IOException e) {
            System.err.println("Could not listen on port: 4444.");
            System.exit(1);
        }
        finally{
            servicio.shutdown();
        }
    }
         

}
