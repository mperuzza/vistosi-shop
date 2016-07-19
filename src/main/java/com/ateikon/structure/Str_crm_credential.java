package com.ateikon.structure;

import java.sql.Timestamp;


/**********************/
//     NOTA:
//     modificare questa classe nel portale implica un allineamento della stessa classe in eprogen
//     e viceversa modificare questa classe nel eprogen implica un allineamento della stessa classe nel portale
//
/*********************/

public class Str_crm_credential extends Object {

    public Str_crm_credential(){
 
        super();

    }

    //PARAMETRI OUTPUT
    public String                       keycode     = "";
    public String                       keycode_url = "";
    
    //PARAMETRI INPUT
    public String                       url         = "";
    public long                         tkutente    = 0;
    


}
