/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clientesocket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author castr
 */
public class StubChistes implements ServicioChistes {

    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    public StubChistes(String host, int puerto) throws IOException {
        socket = new Socket(host, puerto);
        out = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

    }

    @Override
    public String obtenerChiste() throws Exception {
        return in.readLine();
    }

    @Override
    public void responder(String respuesta) throws Exception {
        out.println(respuesta);
    }

    public void cerrar() throws IOException {
        out.close();
        in.close();
        socket.close();
    }

}
