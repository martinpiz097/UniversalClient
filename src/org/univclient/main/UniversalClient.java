/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.univclient.main;

import org.univclient.net.Connector;

/**
 *
 * @author martin
 */
public class UniversalClient {
    public static void main(String[] args) {
        Connector connector = new Connector();
        connector.connect("localhost", 4000, Connector.PROTOCOL.TCP);
        connector.start();
    }
}
