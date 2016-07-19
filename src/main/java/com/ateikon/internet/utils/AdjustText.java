/*
 * AdjustText.java
 *
 * Created on 23 luglio 2004, 14.53
 */

package com.ateikon.internet.utils;

/**
 * A Utility class to help size, adjust, and fill text fields.  Especially
handy
 * when transposing variable length numerics to fixed format Strings.<p>
 *

*---------------------------------------------------------------------------
-----<p>
 * Copyright (c) Joel R. Cochran 2002 <p>
 *
 * Written for Mass Appraisal Systems, Inc. March 2002 <p>
 *
 * You may use, distribute, or modify this code as you see fit for no charge
 * as long as this block remains unchanged. You'll probably want to change
the
 * package name. <p>

*---------------------------------------------------------------------------
-----<p>
 *
 * @author    Joel R. Cochran - original author
 */
public class AdjustText
{

    /**
     * Right Adjusts and pads with blanks to the requested size.
     *
     * @param    inText  The String to be adjusted.
     * @param    desiredLength  The final String size.
     * @return   The adjusted String.
     */
    public static String rightAdjustText( String inText , int desiredLength )
    {
        while ( inText.length() < desiredLength )
        {
            inText = " " + inText ;
        }
        return inText ;
    }

    /**
     * Left Adjusts and pads with blanks to the requested size.
     *
     * @param    inText  The String to be adjusted.
     * @param    desiredLength  The final String size.
     * @return   The adjusted String.
     */
    public static String leftAdjustText( String inText , int desiredLength )
    {
        while ( inText.length() < desiredLength )
        {
            inText = inText + " " ;
        }
        return inText ;
    }

    /**
     * Right Adjusts and pads with zeros to the requested size.
     *
     * @param    inText  The String to be adjusted.
     * @param    desiredLength  The final String size.
     * @return   The adjusted String.
     */
    public static String rightAdjustZeroFill( String inText , int desiredLength )
    {
        while ( inText.length() < desiredLength )
        {
            inText = "0" + inText ;
        }
        return inText ;
    }

    /**
     * Left Adjusts and pads with zeros to the requested size.
     *
     * @param    inText  The String to be adjusted.
     * @param    desiredLength  The final String size.
     * @return   The adjusted String.
     */
    public static String leftAdjustZeroFill( String inText , int desiredLength )
    {
        while ( inText.length() < desiredLength )
        {
            inText = inText + "0" ;
        }
        return inText ;
    }

    /**
     * Right Adjusts and pads with the specified character to the requested
size.
     *
     * @param    inText  The String to be adjusted.
     * @param    desiredLength  The final String size.
     * @param    character  The character to pad the String with.
     * @return   The adjusted String.
     */
    public static String rightAdjustCharFill( String inText , int desiredLength ,
                                            char character )
    {
        while ( inText.length() < desiredLength )
        {
            inText = character + inText ;
        }
        return inText ;
    }

    /**
     * Left Adjusts and pads with the specified character to the requested
size.
     *
     * @param    inText  The String to be adjusted.
     * @param    desiredLength  The final String size.
     * @param    character  The character to pad the String with.
     * @return   The adjusted String.
     */
    public static String leftAdjustCharFill( String inText , int desiredLength ,
                                            char character )
    {
        while ( inText.length() < desiredLength )
        {
            inText = inText + character ;
        }
        return inText ;
    }

}