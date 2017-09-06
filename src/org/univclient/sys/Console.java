/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.univclient.sys;

import java.util.Scanner;

/**
 *
 * @author martin
 */
public class Console {
    private final Scanner consoleScanner;

    public Console() {
        consoleScanner = new Scanner(System.in);
    }
    
    public void printMsg(String msg){
        System.out.print(msg);
    }
    
    public String getReadedMsg(){
        return consoleScanner.nextLine();
    }
    
}
