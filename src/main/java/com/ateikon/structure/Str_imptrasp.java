package com.ateikon.structure;

import java.sql.Timestamp;




public class Str_imptrasp extends Object {

    public Str_imptrasp() {

        super();
    }


    public String seStesso = "Str_imptrasp";

    public String rc          = "";
    public String message     = "";



    /****
        INPUT
    **/ 

    public long   tkordi = 0;
    public long   tkcarrello = 0;

   
    /****
        OUTPUT
    **/ 

    public double imptrasp = 0;
    public double importo_min  = 0;
    public double importo_max  = 0;
    public double perc     = 0;
    public String cdnazi   = "";
    public String cdprov   = "";
    public String comune   = "";
    public String cap      = "";
    public String cdvett1  = "";

    public int    tipo_calc = 0;   // TIPO CALCOLO




    /****
        COSTANTI
    **/ 

    public int TIPO_CALC_PERC        = 1;
    public int TIPO_CALC_IMPORTO_MIN = 2;
    public int TIPO_CALC_IMPORTO_MAX = 3;


}


