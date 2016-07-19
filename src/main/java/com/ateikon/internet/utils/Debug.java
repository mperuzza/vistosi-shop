/*
 * Debug.java
 *
 * Created on 8 ottobre 2001, 16.07
 */

package com.ateikon.internet.utils;

import java.beans.*;
import java.io.*;
import java.util.Date;
import java.util.StringTokenizer;
import java.text.*;

/**
 *
 * @author  administrator
 * @version 
 */
public class Debug extends Object implements java.io.Serializable {

    private StringBuffer logger;
    private java.io.FileWriter fw;
    private String fileName = "eprogen.log";
    private String br = "\n";
    /** Creates new Debug */
    public Debug() {
        this.init();
    }

    protected void init(){
        java.util.Properties props = new java.util.Properties();
        try{
            props.load(getClass().getResourceAsStream( "/eprogen.properties" ));
            if (props.getProperty( "logfile" ) != null) {
                this.fileName = props.getProperty( "logfile" );        
            }
            if (this.fileName == null) {
                fileName = "eprogen.log";
            }
        }catch(Exception e){
            ///errore nel caricamento del file di properties 
            //prende il file di default
        }
    }
    
    /**
     *  get instance
     */
    public static synchronized Debug getInstance() {

            if ( theInstance == null ) {
                    theInstance = new Debug();
            }

            return theInstance;
    }
    
    public String getLogger() {
        return this.logger.toString();
    }

   public void log(String message) {
	String date = DateFormat.getDateTimeInstance
	    (DateFormat.LONG, DateFormat.LONG).format(new java.util.Date());
	this.logger.append(date + ": " + message +" <br>");
    }

   public void logF(String message) {
	String date = DateFormat.getDateTimeInstance
	    (DateFormat.LONG, DateFormat.LONG).format(new java.util.Date());
	try{
            this.fw.write(date + ": " + message +" \n");
        }catch(IOException e){
            
        }
    }
    
   public void log(String message, Exception e) {
	log(message);
	this.logger.append("EXCEPTION: " + e.getMessage() + /* ": " + e.toString() +*/ " <br>");
	/*if (metadata.isDebugging())
	    e.printStackTrace(this.logger);*/
    }

    public void writeToLog( String msg ) {
        RandomAccessFile file = open();
        if (file != null) {
            writeToLog( file, msg, new Date());
            close( file );
        }
    }
    
    
    public void writeToLog( String msg, Date date ) {
        RandomAccessFile file = open();
        writeToLog( file, msg, date );
        //writeToLog(file, msg);
        close( file );
    }

    protected void writeToLog( RandomAccessFile file, String msg, Date date ) {
        if( msg == null || msg.length() == 0 ) return;
        SimpleDateFormat formatter = new SimpleDateFormat ("MM/dd/yyyy hh:mm:ss z");
        String dateStr = formatter.format( date );
        StringTokenizer st = new StringTokenizer(msg, "\r\n", false);
        while( st.hasMoreTokens() ) {
            String m = st.nextToken();
            m = dateStr + " : " + m + "\r\n";
            writeToLog( file, m );
        }
    }

    protected RandomAccessFile open() {
        try {
            RandomAccessFile raf = new RandomAccessFile( fileName, "rw" );
            raf.seek( raf.length());
            return raf;
        } catch( FileNotFoundException e ) {
            try {
                FileWriter writer = new FileWriter(fileName);
                writer.write( "<--------------------------- Eprogen Log file  ------------------------------->" + br );
                writer.close();
            } catch( IOException ee ) {
                //Log.err( "Cannot create log file", ee );
                return null;
            }
            return open();
        }
        catch( IOException e ) {
            //Log.err( e );
            return null;
        }
    }

    protected void close( RandomAccessFile file ) {
        try {
            file.close();
        } catch( IOException e ) {
            //Log.err( e );
        }
    }

    protected void writeToLog( RandomAccessFile raf, String msg ) {
        try {
            raf.write( msg.getBytes() );
        } catch( IOException e ) {
            //Log.err( msg, e );
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // class attributes
    static protected Debug theInstance = null;       
}
