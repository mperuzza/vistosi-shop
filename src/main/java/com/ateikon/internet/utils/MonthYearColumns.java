/*
 * MonthYearColumns.java
 *
 * Created on 9 gennaio 2004, 16.45
 */

package com.ateikon.internet.utils;

import java.beans.*;
import java.util.ArrayList;

/**
 *
 * @author  Emiliano
 */
public class MonthYearColumns extends Object implements java.io.Serializable {
    
    /** Holds value of property headers. */
    private ArrayList headers = new ArrayList();
    
    /** Holds value of property startMM. */
    private int startMM = 0;
    
    /** Holds value of property endMM. */
    private int endMM = 0;
    
    /** Holds value of property startYY. */
    private int startYY = 0;
    
    /** Holds value of property endYY. */
    private int endYY = 0;
    
;
    
    /** Creates new MonthYearColumns */
    public MonthYearColumns() {
        
    }
    
    /** Getter for property headers.
     * @return Value of property headers.
     *
     */
    public ArrayList getHeaders() {
        if(startYY != 0 && endYY != 0
            && startMM != 0 && endMM != 0){
            
            if(startYY < endYY){
                int start_yy = startYY;
                for(int i=startMM; i<=12; i++){
                    MonthYear my = new MonthYear();
                    my.setN_month(i);
                    my.setYear(start_yy);
                    this.setHeader(my);                    
                }
                start_yy++;
                for(int j=start_yy; j<endYY; j++){
                    for(int i=1; i<=12; i++){
                        MonthYear my = new MonthYear();
                        my.setN_month(i);
                        my.setYear(j);
                        this.setHeader(my);                    
                    }                
                }                
                for(int i=1; i<=endMM; i++){
                    MonthYear my = new MonthYear();
                    my.setN_month(i);
                    my.setYear(endYY);
                    this.setHeader(my);                    
                }                
            }else if(startYY == endYY && startMM <= endMM){
                 for(int i=startMM; i<=endMM; i++){
                    MonthYear my = new MonthYear();
                    my.setN_month(i);
                    my.setYear(endYY);
                    this.setHeader(my);
                }               
            }
        }
        
        return this.headers;
    }    
    
    /** Setter for property headers.
     * @param headers New value of property headers.
     *
     */
    public void setHeaders(ArrayList headers) {
        this.headers = headers;
    }
    
    public void setHeader(MonthYear header) {
        this.headers.add(header);
    }    
    
    /** Getter for property startMM.
     * @return Value of property startMM.
     *
     */
    public int getStartMM() {
        return this.startMM;
    }
    
    /** Setter for property startMM.
     * @param startMM New value of property startMM.
     *
     */
    public void setStartMM(int startMM) {
        this.startMM = startMM;
    }
    
    /** Getter for property endMM.
     * @return Value of property endMM.
     *
     */
    public int getEndMM() {
        return this.endMM;
    }
    
    /** Setter for property endMM.
     * @param endMM New value of property endMM.
     *
     */
    public void setEndMM(int endMM) {
        this.endMM = endMM;
    }
    
    /** Getter for property startYY.
     * @return Value of property startYY.
     *
     */
    public int getStartYY() {
        return this.startYY;
    }
    
    /** Setter for property startYY.
     * @param startYY New value of property startYY.
     *
     */
    public void setStartYY(int startYY) {
        this.startYY = startYY;
    }
    
    /** Getter for property endYY.
     * @return Value of property endYY.
     *
     */
    public int getEndYY() {
        return this.endYY;
    }
    
    /** Setter for property endYY.
     * @param endYY New value of property endYY.
     *
     */
    public void setEndYY(int endYY) {
        this.endYY = endYY;
    }
    
}
