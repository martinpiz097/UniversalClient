/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.univclient.net;

import java.io.IOException;
import org.univclient.interfaces.Communicable;
import org.univclient.sys.Console;

/**
 *
 * @author martin
 */
public class Connector extends Thread implements Communicable{
    private Client client;
    private final Console console;

    public static enum PROTOCOL {
        TCP, UDP;
    }
    
    public Connector(String serverIp, int port, PROTOCOL protocol) {
        connect(serverIp, port, protocol);
        console = new Console();
    }
    
    public Connector(byte[] serverIp, int port, PROTOCOL protocol){
        connect(serverIp, port, protocol);
        console = new Console();
    }

    public Connector() {
        console = new Console();
    }

    public Client getClient() {
        return client;
    }

    public boolean connect(String host, int port, PROTOCOL protocol) {
        try {
            client = (protocol == PROTOCOL.TCP ?
                    new TCPClient(host, port) : new UDPClient(host, port));
            return true;
        } catch (IOException ex) {
            return false;
        }
    }
    
    public boolean connect(byte[] ip, int port, PROTOCOL protocol) {
        try {
            client = (protocol == PROTOCOL.TCP ?
                    new TCPClient(ip, port) : new UDPClient(ip, port));
            return true;
        } catch (IOException ex) {
            return false;
        }
    } 
    
    @Override
    public void send(String msg) {
        client.send(msg);
    }

    @Override
    public void send(byte[] bytes) {
        client.send(bytes);
    }

    @Override
    public boolean hasReceivData() {
        return client.hasReceivData();
    }

    @Override
    public String getReceivData() {
        return client.getReceivData();
    }

    @Override
    public byte[] getReceivBytes() {
        return client.getReceivBytes();
    }

    @Override
    public void run() {
        while (true) {
            console.printMsg(getReceivData());
            send(console.getReadedMsg());
        }
    }
    
}
