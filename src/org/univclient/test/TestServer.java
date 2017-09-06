/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.univclient.test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author martin
 */
public class TestServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSock = new ServerSocket(4000);
        Socket client = serverSock.accept();
        OutputStream os = client.getOutputStream();
        InputStream in = client.getInputStream();
        os.write("hola".getBytes("UNICODE"));
        String receiv;
        byte[] receivBuff = new byte[10];
        int readed;
        
        while (true) {
            receiv = new String(receivBuff, "UNICODE");
            System.out.println(receiv);
            os.write(receiv.getBytes("UNICODE"));
        }
    }
}
