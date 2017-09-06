/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.univclient.net;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.univclient.io.StringInputStream;
import org.univclient.io.StringOutputStream;

/**
 *
 * @author martin
 */
public class TCPClient extends Client {
    private Socket cliSock;
    private StringOutputStream output;
    private StringInputStream input;

    public TCPClient(String serverName, int port) throws IOException {
        super(serverName, port);
    }

    public TCPClient(byte[] serverIp, int port) throws UnknownHostException, IOException {
        super(serverIp, port);
    }

    public TCPClient() {
        super();
    }
    
    @Override
    public boolean connect(String host, int port){
        try {
            cliSock = new Socket();
            cliSock.connect(new InetSocketAddress(host, port));
            output = new StringOutputStream(cliSock.getOutputStream());
            input = new StringInputStream(cliSock.getInputStream());
            return true;
        } catch (IOException ex) {
            return false;
        }
    }
    
    public boolean connect(byte[] ip, int port){
        try {
            cliSock = new Socket();
            cliSock.connect(new InetSocketAddress(InetAddress.getByAddress(ip), port));
            output = new StringOutputStream(cliSock.getOutputStream());
            input = new StringInputStream(cliSock.getInputStream());
            return true;
        } catch (IOException ex) {
            return false;
        }
    }

    @Override
    public void send(String msg) {
        try {
            output.writeString(msg);
        } catch (IOException ex) {
            Logger.getLogger(TCPClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void send(byte[] bytes) {
        try {
            output.write(bytes);
        } catch (IOException ex) {
            Logger.getLogger(TCPClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public boolean hasReceivData() {
        try {
            return input.available() > 0;
        } catch (IOException ex) {
            Logger.getLogger(TCPClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public String getReceivData() {
        try {
            return input.readString();
        } catch (IOException ex) {
            Logger.getLogger(TCPClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public byte[] getReceivBytes() {
        try {
            return input.readBytes();
        } catch (IOException ex) {
            Logger.getLogger(TCPClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
