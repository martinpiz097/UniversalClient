/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.univclient.interfaces;

/**
 *
 * @author martin
 */
public interface Receivable {
    public boolean hasReceivData();
    public String getReceivData();
    public byte[] getReceivBytes();
}
