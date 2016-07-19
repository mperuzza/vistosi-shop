package com.ateikon.function;


import com.ateikon.common.Atk_sql;




public class F_bolla_test extends Atk_sql {

    public F_bolla_test() {

        super();

    }

    //avendramin20101228 - I
    public String getLink_spedizione   ( String cdvett
                                       , String cdling
                                       , String cdnazi
                                       , String docmit
                                                             ) throws Exception{


       return getLink_spedizione   ( cdvett
                                   , cdling
                                   , cdnazi
                                   , docmit
                                   , "DIRECTSHIP"
                                            );


    }
    //avendramin20101228 - F

    public String getLink_spedizione   ( String cdvett
                                       , String cdling
                                       , String cdnazi
                                       , String docmit
                                       , String fgtipo  //avendramin20101228
                                                             ) throws Exception{

      if (1 == 1) throw new Exception("ATTENZIONE!!! questo metodo può essere richiamato solo in E-progen");
      
      return "";

    }


    public String message = "";
    public String rc = "0";
}
