/*
 * PrintArray.java
 *
 * Created on 9 gennaio 2002, 10.57
 */

package com.ateikon.internet.utils;

/**
 *
 * @author  administrator
 */
public class PrintArray {

    /** Creates a new instance of PrintArray */
    public PrintArray() {
    }
    
    public static void print(Object[] ar) {
        for(int i=0; i<ar.length; i++)
            System.out.println(ar[i].toString());
    }    

}
