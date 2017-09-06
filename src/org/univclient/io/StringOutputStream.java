/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.univclient.io;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author martin
 */
public class StringOutputStream extends OutputStream{

    private final OutputStream os;

    public StringOutputStream(OutputStream os) {
        this.os = os;
    }

    @Override
    public void close() throws IOException {
        os.close();
    }

    @Override
    public void flush() throws IOException {
        os.flush();
    }

    public void writeString(String str) throws IOException{
        try {
            byte[] strBytes = str.getBytes("UNICODE");
            write(strBytes);
            strBytes = null;
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(StringOutputStream.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        if (off >= len || len > b.length || off < 0)
            throw new IndexOutOfBoundsException();
        for (int i = off; i < len; i++)
            os.write(b[i]);
    }

    @Override
    public void write(byte[] b) throws IOException {
        for (int i = 0; i < b.length; i++)
            os.write(b[i]);
    }
    
    @Override
    public void write(int b) throws IOException {
        os.write(b);
    }
    
}
