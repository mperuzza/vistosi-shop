package com.ateikon.structure;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;


public class Str_controllo_articolo extends Str_rep_base {

    public Str_controllo_articolo(){
 
        super();

    }


    public     String      f_search          = "";
    public     String      cdrepa            = "";
    public     String      cdstato           = "";
    public     String      cdtipm            = "";
    public     String      cdartipo          = "";
    public     String      cdvisttp          = "";
    public     String      cdvistccol        = "";
    public     String      dsvistccol        = "";
    public     String      fgweb             = "";
    public     String[]    arr_cdclas_a      = new String[0];

    /***


    */

    public void copy_into ( Str_controllo_articolo astr) throws Exception {
       
       super.copy_into((Str_rep_base) astr);

       astr.f_search           = this.f_search      ;
       astr.cdrepa             = this.cdrepa        ;
       astr.cdstato            = this.cdstato       ;
       astr.cdtipm             = this.cdtipm        ;
       astr.cdartipo           = this.cdartipo      ;
       astr.cdvisttp           = this.cdvisttp      ;
       astr.cdvistccol         = this.cdvistccol    ;
       astr.dsvistccol         = this.dsvistccol    ;
       astr.fgweb              = this.fgweb         ;
       astr.arr_cdclas_a       = this.arr_cdclas_a  ;

    }




}
