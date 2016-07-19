/*
 * MonthYear.java
 *
 * Created on 9 gennaio 2004, 16.39
 */

package com.ateikon.internet.utils;

import java.beans.*;

/**
 *
 * @author  Emiliano
 */
public class MonthYear extends Object implements java.io.Serializable {
    
    /** Holds value of property n_month. */
    private int n_month;    
   
    /** Holds value of property s_month. */
    private String s_month;
    
    /** Holds value of property year. */
    private int year;
    
    /** Holds value of property totcol. */
    private double totcol = 0;
	
    /** Holds value of property totcolAcc. */
    private double totcolAcc = 0;	
	
    /** Holds value of property totcolCond. */
    private double totcolCond = 0;		
    
    /** Creates new MonthYear */
    public MonthYear() {
    }
    
    /** Getter for property n_month.
     * @return Value of property n_month.
     *
     */
    public int getN_month() {
        return this.n_month;
    }    
    
    /** Setter for property n_month.
     * @param n_month New value of property n_month.
     *
     */
    public void setN_month(int n_month) {
        this.n_month = n_month;
    }
    
    /** Getter for property s_month.
     * @return Value of property s_month.
     *
     */
    public String getS_month() {
        return this.s_month;
    }
    
    /** Setter for property s_month.
     * @param s_month New value of property s_month.
     *
     */
    public void setS_month(String s_month) {
        this.s_month = s_month;
    }
    
    /** Getter for property year.
     * @return Value of property year.
     *
     */
    public int getYear() {
        return this.year;
    }
    
    /** Setter for property year.
     * @param year New value of property year.
     *
     */
    public void setYear(int year) {
        this.year = year;
    }
    
    /** Getter for property totcol.
     * @return Value of property totcol.
     *
     */
    public double getTotcol() {
        return this.totcol;
    }
    
    /** Setter for property totcol.
     * @param totcol New value of property totcol.
     *
     */
    public void setTotcol(double totcol) {
        this.totcol += totcol;
    }
	
    /** Getter for property totcolAcc.
     * @return Value of property totcolAcc.
     *
     */
    public double getTotcolAcc() {
        return this.totcolAcc;
    }
    
    /** Setter for property totcolAcc.
     * @param totcolAcc New value of property totcolAcc.
     *
     */
    public void setTotcolAcc(double totcolAcc) {
        this.totcolAcc += totcolAcc;
    }

    /** Getter for property totcolCond.
     * @return Value of property totcolCond.
     *
     */
    public double getTotcolCond() {
        return this.totcolCond;
    }
    
    /** Setter for property totcolCond.
     * @param totcolCond New value of property totcolCond.
     *
     */
    public void setTotcolCond(double totcolCond) {
        this.totcolCond += totcolCond;
    }	
    
}
