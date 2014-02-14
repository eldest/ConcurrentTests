package com.eldest;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Scanner;

public class Math {

	public static void main( String[] args ) throws IOException
    {
        new Math().run();
    }    
    
    Scanner in;
    PrintWriter out;
    
    void run() throws IOException
    {
        in = new Scanner ( System.in );
        out = new PrintWriter ( System.out );
        solve();
        out.flush();
    }
    
    void solve() throws IOException
    {
        BigInteger a = in.nextBigInteger();
        BigInteger b = in.nextBigInteger();
        out.println( a.add(b) );
    }
}
