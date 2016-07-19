package com.ateikon.structure;


import com.ateikon.util.StringUtils;
import com.ateikon.util.Atk_ctrl;

import java.sql.Timestamp;

public class Str_giacenza extends Object {


    public Str_giacenza(){
 
        super();

    }

    public String seStesso = "Str_giacenza";

    public String rc          = "";
    public String message     = "";


    // --------------------------------------------------------
    //      Input 
    // --------------------------------------------------------

    public String cdarti       = "";
    public long   tkmatricola  = 0;
    public long   tkmatrdist   = 0;
    public String fpacco       = "";
    public String fpaccodist   = "";
    public String fmisvar      = "";


    public double qtagia_prel = 0;      // Utili al confronto 
    public double ncolli_prel = 0;
    public double dimenx_prel = 0;


    // --------------------------------------------------------
    //      Calcolati Dalla Funzione
    // --------------------------------------------------------

    public long   tkmaga = 0;


    // --------------------------------------------------------
    //      Output 
    // --------------------------------------------------------

    public double qtagia = 0;
    public double ncolli = 0;
    public double dimenx = 0;

    public double dist_qtatot  = 0; 
    public double dist_qtacons = 0;

    public int is_giacenza = 0;         // is_giacenza = 1 ==>> giacenza OK
                                        // is_giacenza = 0 ==>> giacenza KO





}































