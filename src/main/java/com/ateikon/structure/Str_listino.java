package com.ateikon.structure;

import java.sql.Timestamp;




public class Str_listino extends Object {

    public Str_listino() {

        super();
    }


    public String seStesso = "Str_listino";

    public String rc          = "";
    public String message     = "";



    /****
        Properties 
    **/ 

   




    public String tkclie      = "";
    public String cdarti      = "";

    public String cdlist      = "";
    public String cdcllist    = "";
    public String cdclsc_v    = "";
    public String vacodi      = "";
    public double impuni      = 0;
    public double impuninetto = 0;
    public double sconto1     = 0;
    public double sconto2     = 0;
    public double sconto3     = 0;
    public double sconto4     = 0;
    public double scrap1      = 0;
    public double scrap2      = 0;
                              
    public double scmax_1      = 0;
    public double scmax_2      = 0;

    public double scocas      = 0;

    public String descr_sconti = "";

    public String descr_sconti_imballo = "";


    public String ftiposconto = "";
    public String descr_tipoconto = "";

    public boolean valido = false;



    // memorizzo Gli Sconti per imballo

    public double ppm = 0;

    public double imb_sconto1 = 0;
    public double imb_sconto2 = 0;
    public double imb_sconto3 = 0;
    public double imb_sconto4 = 0;

    public String imb_descr_sconti = "";




    /***


    */

    public void print ( ) throws Exception {
        

        System.out.println("-------------------------------------------------------");

        System.out.println("tkclie          >>"+tkclie      +"<<");
        System.out.println("cdarti          >>"+cdarti      +"<<");
        System.out.println("cdlist          >>"+cdlist      +"<<");
        System.out.println("cdcllist        >>"+cdcllist    +"<<");
        System.out.println("cdclsc_v        >>"+cdclsc_v    +"<<");
        System.out.println("vacodi          >>"+vacodi      +"<<");
        System.out.println("impuni          >>"+impuni      +"<<");
        System.out.println("impuninetto     >>"+impuninetto +"<<");
        System.out.println("sconto1         >>"+sconto1     +"<<");
        System.out.println("sconto2         >>"+sconto2     +"<<");
        System.out.println("sconto3         >>"+sconto3     +"<<");
        System.out.println("sconto4         >>"+sconto4     +"<<");
        System.out.println("scrap1          >>"+scrap1      +"<<");
        System.out.println("scrap2          >>"+scrap2      +"<<");
        System.out.println("scocas          >>"+scocas      +"<<");
        System.out.println("descr_sconti    >>"+descr_sconti+"<<");
        System.out.println("descr_tipoconto >>"+descr_tipoconto+"<<");
        

        return ;

    }








}


