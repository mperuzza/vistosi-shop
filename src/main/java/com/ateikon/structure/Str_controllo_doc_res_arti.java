package com.ateikon.structure;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;


public class Str_controllo_doc_res_arti extends Str_rep_base {

    public Str_controllo_doc_res_arti(){
 
        super();

    }


    public     String      cdling            = "";
    public     String      cdvisttp          = "";
    public     String      cdvistfam         = "";
    public     String      fgscarta_sp_con_v3         = "N";
    public     String      fg_eur_usa        = "";
    public     String      cdvistv1          = "";
    public     String      cdvistv2          = "";
    public     String      cdvistv3          = "";
    
    /***


    */

    public void copy_into ( Str_controllo_doc_res_arti astr) throws Exception {
       
       super.copy_into((Str_rep_base) astr);

       astr.cdling             = this.cdling        ;
       astr.cdvisttp           = this.cdvisttp      ;
       astr.cdvistfam          = this.cdvistfam     ;
       astr.fgscarta_sp_con_v3 = this.fgscarta_sp_con_v3     ;
       astr.fg_eur_usa         = this.fg_eur_usa    ;
       astr.cdvistv1           = this.cdvistv1      ;
       astr.cdvistv2           = this.cdvistv2      ;
       astr.cdvistv3           = this.cdvistv3      ;

    }




}
