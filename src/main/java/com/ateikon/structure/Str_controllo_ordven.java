package com.ateikon.structure;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;


public class Str_controllo_ordven extends Str_rep_base {

    public Str_controllo_ordven(){
 
        super();

    }


    public     String    fgevaso       = "";
    public     long      anno          = 0;
    public     String    cdordi        = "";
    public     String    cdrifo        = "";
    public     Timestamp dtordi_da     = null;
    public     Timestamp dtordi_a      = null;
    public     Timestamp dtcons_da     = null;
    public     Timestamp dtcons_a      = null;


    /***


    */

    public void copy_into ( Str_controllo_ordven astr) throws Exception {
       
       super.copy_into((Str_rep_base) astr);

       astr.fgevaso     = this.fgevaso;   
       astr.anno        = this.anno; 
       astr.cdordi      = this.cdordi; 
       astr.cdrifo      = this.cdrifo; 
       astr.dtordi_da   = this.dtordi_da; 
       astr.dtordi_a    = this.dtordi_a; 
       astr.dtcons_da   = this.dtcons_da; 
       astr.dtcons_a    = this.dtcons_a; 

    }




}
