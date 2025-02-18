/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package clientesocket;

/**
 *
 * @author castr
 */
public interface ServicioChistes {

    String obtenerChiste() throws Exception;

    void responder(String respuesta) throws Exception;

}
