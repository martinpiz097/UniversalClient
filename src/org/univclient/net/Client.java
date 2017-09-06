/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.univclient.net;

import java.net.Socket;
import org.univclient.interfaces.Communicable;

/**
 *
 * @author martin
 */
public abstract class Client implements Communicable{

    public Client(String serverName, int port) {
        connect(serverName, port);
    }
    public Client(byte[] serverIp, int port){
        connect(serverIp, port);
    }

    public Client() {}

    public abstract boolean connect(String host, int port); 
    public abstract boolean connect(byte[] ip, int port); 
    
}
