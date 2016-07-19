
package com.ateikon.util;


public class StringParsToHTML {



    /**
     * questo metodo esegue il parse di una stringa per
     * textarea
     *
     */
    public static final String escapeHTMLTextarea( String input ) {
        //Check if the string is null or zero length -- if so, return
        //what was sent in.
        if( input == null || input.length() == 0 ) {
            return input;
        }
        //Use a StringBuffer in lieu of String concatenation -- it is
        //much more efficient this way.
        StringBuffer buf = new StringBuffer(input.length());
        char ch = ' ';
        int  int_ch = 0;
        for( int i=0; i<input.length(); i++ ) {
            ch = input.charAt(i);
            int_ch = input.charAt(i);
            if ( (int_ch >= 48 && int_ch <= 57)    //evito conversione numeri 0-9
                || (int_ch >= 65 && int_ch <= 90)   //evito conversione lettere A-Z
                || (int_ch >= 97 && int_ch <= 122)  //evito conversione lettere a-z
                      ) {
              buf.append( ch );
            } else {
              buf.append("&#"+ (int) input.charAt(i) +";");
            }
        }
        return buf.toString();
    }

    /**
     * questo metodo esegue il parse di una stringa per
     * input-box type text
     *
     */
    public static final String escapeHTMLTextbox( String input ) {
        //Check if the string is null or zero length -- if so, return
        //what was sent in.
        if( input == null || input.length() == 0 ) {
            return input;
        }
        //Use a StringBuffer in lieu of String concatenation -- it is
        //much more efficient this way.
        StringBuffer buf = new StringBuffer(input.length());
        char ch = ' ';
        int  int_ch = 0;
        for( int i=0; i<input.length(); i++ ) {
            ch = input.charAt(i);
            int_ch = input.charAt(i);
            if (ch == '\n') {      //gestione specifica del carattere di new line
              buf.append("");
            } else if (ch == '\r'){ //gestione specifica del carattere di carriage return
              buf.append("");
            } else if ( (int_ch >= 48 && int_ch <= 57)    //evito conversione numeri 0-9
                      || (int_ch >= 65 && int_ch <= 90)   //evito conversione lettere A-Z
                      || (int_ch >= 97 && int_ch <= 122)  //evito conversione lettere a-z
                      ) {
              buf.append( ch );
            } else {
              buf.append("&#"+ (int) input.charAt(i) +";");
            }
        }
        return buf.toString();
    }

    public static final String escapeHTMLHidden( String input ) {
        //Check if the string is null or zero length -- if so, return
        //what was sent in.
        if( input == null || input.length() == 0 ) {
            return input;
        }
        //Use a StringBuffer in lieu of String concatenation -- it is
        //much more efficient this way.
        StringBuffer buf = new StringBuffer(input.length());
        char ch = ' ';
        int  int_ch = 0;
        for( int i=0; i<input.length(); i++ ) {
            ch = input.charAt(i);
            int_ch = input.charAt(i);
            if (ch == '\n') { //gestione specifica del carattere di new line
              buf.append("");
	           } else if (ch == '\r'){ //gestione specifica del carattere di carriage return
              buf.append("");
            } else if ( (int_ch >= 48 && int_ch <= 57)    //evito conversione numeri 0-9
                      || (int_ch >= 65 && int_ch <= 90)   //evito conversione lettere A-Z
                      || (int_ch >= 97 && int_ch <= 122)  //evito conversione lettere a-z
                      || (int_ch >= 32 && int_ch <= 47)  //evito alcuni caratteri di Punteggiatura, vedi tabella ASCII
                      || (int_ch >= 58 && int_ch <= 64) //evito alcuni caratteri di Punteggiatura, vedi tabella ASCII
                      ) {
              buf.append( ch );
            } else {
              buf.append("&#"+ (int) input.charAt(i) +";");
            }
        }
        return buf.toString();
    }

    public static final String escapeHTML( String input ) {
        //Check if the string is null or zero length -- if so, return
        //what was sent in.
        if( input == null || input.length() == 0 ) {
            return input;
        }
        //Use a StringBuffer in lieu of String concatenation -- it is
        //much more efficient this way.
        StringBuffer buf = new StringBuffer(input.length());
        char ch = ' ';
        int  int_ch = 0;
        for( int i=0; i<input.length(); i++ ) {
            ch = input.charAt(i);
            int_ch = input.charAt(i);
            if (ch == '\n') { //gestione specifica del carattere di new line
              buf.append("<br />");
	           } else if (ch == '\r'){ //gestione specifica del carattere di carriage return
              buf.append("");
	           } else if (ch == '['){ //gestione specifica del carattere di carriage return
              buf.append("[");
	           } else if (ch == ']'){ //gestione specifica del carattere di carriage return
              buf.append("]");
            } else if ( (int_ch >= 48 && int_ch <= 57)    //evito conversione numeri 0-9
                      || (int_ch >= 65 && int_ch <= 90)   //evito conversione lettere A-Z
                      || (int_ch >= 97 && int_ch <= 122)  //evito conversione lettere a-z
                      || (int_ch >= 32 && int_ch <= 47) //evito alcuni caratteri di Punteggiatura, vedi tabella ASCII
                      || (int_ch >= 58 && int_ch <= 64) //evito alcuni caratteri di Punteggiatura, vedi tabella ASCII
                      ) {
              buf.append( ch );
            } else {
              buf.append("&#"+ (int) input.charAt(i) +";");
            }
        }
        return buf.toString();
    }

    public static final String chgSpecialChar( String input ) {
        if( input == null || input.length() == 0 ) {
            return input;
        }

        input = input.trim();
        input = input.replace(' ','_');
        input = input.replace('é','e');
        input = input.replace('è','e');
        input = input.replace('à','a');
        input = input.replace('ì','i');
        input = input.replace('ò','o');

        return input;
    }



    // Escape javaScript

    public static final String escapeJS( String input ) {
        //Check if the string is null or zero length -- if so, return
        //what was sent in.
        if( input == null || input.length() == 0 ) {
            return input;
        }
        //Use a StringBuffer in lieu of String concatenation -- it is
        //much more efficient this way.
        StringBuffer buf = new StringBuffer(input.length());
        char ch = ' ';
        for( int i=0; i<input.length(); i++ ) {
            ch = input.charAt(i);

            if (ch == '\'') {
                buf.append("\\'");
            }else  if (ch == '\n') {
                buf.append("\\n");
            }else  if (ch == '\r') {
                buf.append("\\r");
            }else  if (ch == '\\') {
                buf.append("\\\\");
            }
            else {
                buf.append( ch );
            }
        }
        return buf.toString();
    }


    public static final String escapeRequest( String input ) {
        //Check if the string is null or zero length -- if so, return
        //what was sent in.
        if( input == null || input.length() == 0 ) {
            return input;
        }
        //Use a StringBuffer in lieu of String concatenation -- it is
        //much more efficient this way.
        StringBuffer buf = new StringBuffer(input.length());
        char ch = ' ';
        for( int i=0; i<input.length(); i++ ) {
            ch = input.charAt(i);

            if( ch == ' ' )       buf.append("+");
            else if( ch == '+')   buf.append("%2B");
            else if( ch == 'à')   buf.append("%E0");
            else if( ch == 'ò')   buf.append("%F2");
            else if( ch == 'ì')   buf.append("%EC");
            else if( ch == '^')   buf.append("%5E");
            else if( ch == '?')   buf.append("%3F");
            else if( ch == 'è')   buf.append("%E8");
            else if( ch == 'é')   buf.append("%E9");
            else if( ch == 'ù')   buf.append("%F9");
            else if( ch == '[')   buf.append("%5B");
            else if( ch == ']')   buf.append("%5D");
            else if( ch == '>')   buf.append("%3E");
            else if( ch == '<')   buf.append("%3C");
            else if( ch == ':')   buf.append("%3A");
            else if( ch == ';')   buf.append("%3B");
            else if( ch == ',')   buf.append("%2C");
            else if( ch == '#')   buf.append("%23");
            else if( ch == '"')   buf.append("%22");
            else if( ch == '£')   buf.append("%A3");
            else if( ch == '$')   buf.append("%24");
            else if( ch == '%')   buf.append("%25");
            else if( ch == '&')   buf.append("%26");
            else if( ch == '\'')  buf.append("%27");
            else                  buf.append( ch );
        }
        return buf.toString();
    }



}
