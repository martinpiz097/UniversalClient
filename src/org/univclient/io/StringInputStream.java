/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.univclient.io;

import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author martin
 */
public class StringInputStream extends InputStream{

    private final InputStream in;

    public StringInputStream(InputStream in) {
        this.in = in;
    }

    @Override
    public boolean markSupported() {
        return in.markSupported();
    }

    @Override
    public synchronized void reset() throws IOException {
        in.reset();
    }

    @Override
    public synchronized void mark(int readlimit) {
        in.mark(readlimit);
    }

    @Override
    public void close() throws IOException {
        in.close();
    }

    @Override
    public int available() throws IOException {
        return in.available();
    }

    @Override
    public long skip(long n) throws IOException {
        return in.skip(n);
    }

    public String readString() throws IOException{
        byte[] bytes = readBytes();
        return bytes.length > 0 ? new String(bytes, "UNICODE") : "";
    }
    
    public byte[] readBytes() throws IOException{
        byte[] bytes = new byte[available()];
        if (bytes.length > 0)
            read(bytes);
        return bytes;
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        if (off < 0 || off>= len || len > b.length)
            throw new IndexOutOfBoundsException();
        final int available = available();
        final int readed = len <= available?len:available;
        
        for (int i = off; i < readed; i++)
            b[i] = (byte) read();
        return readed;
    }

    @Override
    public int read(byte[] b) throws IOException {
        final int arrayLen = b.length;
        final int available = available();
        final int readed = arrayLen <= available?arrayLen:available;
        
        for (int i = 0; i < readed; i++)
            b[i] = (byte) read();
        return readed;
    }
    
    @Override
    public int read() throws IOException {
        return in.read();
    }
    
}
