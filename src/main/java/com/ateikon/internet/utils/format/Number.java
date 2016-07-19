/*
 * Number.java
 *
 * Created on 13 luglio 2001, 12.35
 */

package com.ateikon.internet.utils.format;

import java.beans.*;

/**
 *
 * @author  administrator
 * @version 
 */
public class Number extends Object implements java.io.Serializable {

    /** Creates new Number */
    public Number () {
    }

    public String DoFormatNumber(double number,int NumDigitsAfterDecimal,int UseParensForNegativeNumbers,int GroupDigits, java.util.Locale loc) 
    {

            java.text.NumberFormat form;
            form = java.text.NumberFormat.getInstance(loc);
            if (NumDigitsAfterDecimal != -1) 
                    form.setMaximumFractionDigits(NumDigitsAfterDecimal);

            if (UseParensForNegativeNumbers == -1) {
                    ((java.text.DecimalFormat)form).setNegativePrefix("(");
                    ((java.text.DecimalFormat)form).setNegativeSuffix(")");
            }
            else if (UseParensForNegativeNumbers == 0) {
                    ((java.text.DecimalFormat)form).setNegativePrefix("-");
                    ((java.text.DecimalFormat)form).setNegativeSuffix("");
            }

            if (GroupDigits == -1) {
                    form.setGroupingUsed(true);
            }
            else if (GroupDigits == 0) {
                    form.setGroupingUsed(false);
            }

            return form.format(number);
    }

}
