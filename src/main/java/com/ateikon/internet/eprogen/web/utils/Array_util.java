/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ateikon.internet.eprogen.web.utils;

/**
 *
 * @author emiliano
 */
public class Array_util {

     static public String dump(int[] elementi) {
     StringBuffer e = new StringBuffer();

     if (elementi.length > 0) {
     e.append("[ " + elementi[0]);

     for (int i = 1; i < elementi.length; i++) {
     e.append(",");
     e.append(elementi[i]);
     }
     }
     e.append(" ]");
     return e.toString();
     }

}
