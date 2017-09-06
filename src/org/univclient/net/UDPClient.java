/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.univclient.net;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author martin
 */
public class UDPClient extends Client{

    private DatagramSocket cliSock;
    private DatagramPacket sendPacket;
    private DatagramPacket receivPacket;

    public UDPClient(String serverName, int port) {
        super(serverName, port);
    }

    public UDPClient(byte[] serverIp, int port) {
        super(serverIp, port);
    }

    public UDPClient() {}
    

    @Override
    public boolean connect(String host, int port) {
        try {
            cliSock = new DatagramSocket();
            cliSock.connect(InetAddress.getByName(host), port);
            sendPacket = new DatagramPacket(new byte[1024], 1024);
            receivPacket = new DatagramPacket(new byte[1024], 1024);
            return true;
        } catch (SocketException | UnknownHostException ex) {
            Logger.getLogger(UDPClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean connect(byte[] ip, int port) {
         try {
            cliSock = new DatagramSocket();
            cliSock.connect(InetAddress.getByAddress(ip), port);
            sendPacket = new DatagramPacket(new byte[1024], 1024);
            receivPacket = new DatagramPacket(new byte[1024], 1024);
            return true;
        } catch (SocketException | UnknownHostException ex) {
            Logger.getLogger(UDPClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    @Override
    public void send(String msg) {
        try {
            send(msg.getBytes("UNICODE"));
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(UDPClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void send(byte[] bytes) {
        try {
            sendPacket.setData(bytes);
            cliSock.send(sendPacket);
        } catch (IOException ex) {
            Logger.getLogger(UDPClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public boolean hasReceivData() {
        try {
            cliSock.receive(receivPacket);
            return receivPacket.getLength() > 0;
        } catch (IOException ex) {
            Logger.getLogger(UDPClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public String getReceivData() {
        try {
            cliSock.receive(receivPacket);
            return new String(receivPacket.getData(), "UNICODE");
        } catch (IOException ex) {
            Logger.getLogger(UDPClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public byte[] getReceivBytes() {
        try {
            cliSock.receive(receivPacket);
            return receivPacket.getData();
        } catch (IOException ex) {
            Logger.getLogger(UDPClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
