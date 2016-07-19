/*
 * DateTime.java
 *
 * Created on 18 giugno 2001, 12.26
 */

package com.ateikon.internet.utils;

import java.beans.*;
//import java.text.DateFormat;
import java.util.*;
import java.sql.Timestamp;
import com.ateikon.internet.utils.Debug;

public class DateTime extends Object implements java.io.Serializable {

    //debug
    private Debug debug;
    
    protected Calendar calendar = null;
    Timestamp ts;
    int gg;
    int mm;
    int yy;
    
    /** Creates new DateTime */
    public DateTime () {
        calendar = Calendar.getInstance(java.util.TimeZone.getTimeZone("GMT+1:00"), Locale.ITALY);
        Date trialTime = new Date();
        calendar.setTime(trialTime);
        gg=mm=yy=-99999;
        this.debug = Debug.getInstance();
    }

    public int getAnno() {
		return calendar.get(Calendar.YEAR);
    }

    public int getDate() {
		return calendar.get(Calendar.DATE);
    }
    public String getMese() {
		int m = getMeseInt();
		String[] mesi = new String [] { "Gennaio", "Febbraio", "Marzo",
						"Aprile", "Maggio", "Giugno",
						"Luglio", "Agosto", "Settembre",
						"Ottobre", "Novembre", "Dicembre" };
		if (m > 12)
			return "Mese sconosciuto";

		return mesi[m - 1];

    }

    public String getGiorno() {
		int x = getGiornoDellaSettimana();
		String[] giorni = new String[] {"Domenica", "Lunedì", "Martedì", "Mercoledì", 
						  "Giovedì", "Venerdì", "Sabato"};

		if (x > 7)
			return "Giorno sconosciuto";

		return giorni[x - 1];

    }

    public int getMeseInt() {
		return 1 + calendar.get(Calendar.MONTH);
    }

    public String getData() {
		return  getGiornoDelMese() + "/" + getMeseInt() + "/" +  getAnno();

    }

    public String getDataOra() {
		return  getAnno() + "/" + getMeseInt() + "/" + getGiornoDelMese() +" "+getOra() ;

    }

    public Timestamp getTimestamp() {
        ts = new Timestamp(System.currentTimeMillis());
        return ts;
    }
    
    public String getOra() {
		return getHour() + ":" + getMinute() + ":" + getSecond();
    }

    public int getGiornoDelMese() {
		return calendar.get(Calendar.DAY_OF_MONTH);
    }

    public int getGiornoDellAnno() {
		return calendar.get(Calendar.DAY_OF_YEAR);
    }

    public int getSettimanaDellAnno() {
		return calendar.get(Calendar.WEEK_OF_YEAR);
    }

    public int getSettimanaDelMese() {
		return calendar.get(Calendar.WEEK_OF_MONTH);
    }

    public int getGiornoDellaSettimana() {
		return calendar.get(Calendar.DAY_OF_WEEK);
    }

    public int getHour() {
		return calendar.get(Calendar.HOUR_OF_DAY);
    }

    public int getMinute() {
		return calendar.get(Calendar.MINUTE);
    }

    public int getSecond() {
		return calendar.get(Calendar.SECOND);
    }

    private static void p(String x) {
		System.out.println(x);
    }

    public int getEra() {
		return calendar.get(Calendar.ERA);
    }

    public String getUSTimeZone() {
		String[] zones = new String[] {"Hawaii", "Alaskan", "Pacific",
						   "Mountain", "Central", "Eastern"};

		int index = 10 + getZoneOffset();

		if (index <= 5) {	
			return zones[10 + getZoneOffset()];
		}
		else return "Only US Time Zones supported";
    }

    public int getZoneOffset() {
		return calendar.get(Calendar.ZONE_OFFSET)/(60*60*1000);
    }

    public int getDSTOffset() {
		return calendar.get(Calendar.DST_OFFSET)/(60*60*1000);
    }

    public int getAMPM() {
		return calendar.get(Calendar.AM_PM);
    }

    public void setGg(int i) {
        this.gg=i;
    }
    public void setMm(int i) {
        this.mm=i;
    }
    public void setYy(int i) {
        this.yy=i;
    }

    public Timestamp getDatetm() {
        //debug.writeToLog( "getDatetm: PRIMA parametri gg "+gg+" mm "+mm+" yy "+yy);
        if (gg != -99999 && mm != -99999 && yy != -99999) {
            calendar.clear();
            calendar.set(yy,mm,gg);
            ts = new Timestamp(calendar.getTimeInMillis());
            //ts = new Timestamp(System.currentTimeMillis());
            //ts= new Timestamp (yy,mm,gg,0,0,0,0);
        }
        else {
            ts=null;
            //ts = new Timestamp(System.currentTimeMillis());
            //debug.writeToLog( "getDatetm: parametri gg "+gg+" mm "+mm+" yy "+yy);
        }
        return ts;
    }
    public void setDate() {
        //debug.writeToLog( "setDatetm: PRIMA parametri gg "+gg+" mm "+mm+" yy "+yy);
        if (gg != -99999 && mm != -99999 && yy != -99999) {
            calendar.clear();
            calendar.set(yy,mm,gg);
        }
        else {
            //debug.writeToLog( "setDatetm: parametri gg "+gg+" mm "+mm+" yy "+yy);
        }
    }
    
    
}
