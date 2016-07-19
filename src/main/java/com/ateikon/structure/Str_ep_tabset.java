package com.ateikon.structure;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;


public class Str_ep_tabset extends Str_rep_base {

    public Str_ep_tabset(){
 
        super();

    }


    public     String    fazienda            = "";
    public     String    fazienda_user       = "";
    public     String    fconcrete           = "";

    

    /***


    */

    public void copy_into ( Str_ep_tabset astr) throws Exception {
       
       super.copy_into((Str_rep_base) astr);

       astr.fazienda           = this.fazienda        ;
       astr.fazienda_user      = this.fazienda_user   ;
       astr.fconcrete          = this.fconcrete       ;
       
    }




}
