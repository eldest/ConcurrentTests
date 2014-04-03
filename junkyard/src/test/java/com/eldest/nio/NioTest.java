package com.eldest.nio;

import com.eldest.AbstractTest;
import org.junit.Test;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NioTest extends AbstractTest {

    @Test
    public void readFileTest() throws Exception {
        try (RandomAccessFile aFile = new RandomAccessFile(getResourceFile("data/nio-data.txt"), "rw")) {
            FileChannel inChannel = aFile.getChannel();

            ByteBuffer buf = ByteBuffer.allocate(48);   // create buffer with capacity of 48 bytes

            while (inChannel.read(buf) != -1) { // read into buffer.
                buf.flip(); // make buffer ready for read

                while (buf.hasRemaining()) {
                    System.out.print((char) buf.get()); // read 1 byte at a time
                }

                buf.clear();    // make buffer ready for writing
            }
        }
    }
}
