package com.ateikon.common;

import java.sql.ResultSet;
import java.sql.PreparedStatement;


public class Vist_mpron extends com.ateikon.standard.Vist_mpron {


    public Vist_mpron() {

        super();

        ib_calcola_token = false;
    }

    public double of_getImporto_totale_c (long al_tkmpron) throws Exception {
      int ind = 0;
      int tot_rec = 0;
      PreparedStatement pstm = null;
      ResultSet rs = null;

      Vist_mpron_posi vist_mpron_posi = new Vist_mpron_posi(); 

      setProfilo((Atk_sql) vist_mpron_posi);
      
      double ld_importo_totale = 0;

      rs = vist_mpron_posi.of_searchRighe_scadenze(al_tkmpron);

      while(rs!= null && rs.next()){
        double  ld_importo   = 0;
        double  ld_cambeuro  = 0;
        double  ld_importo_euro   = 0;

        if (rs.getObject("importo" ) != null)  ld_importo  = rs.getDouble("importo" ); 
        if (rs.getObject("cambeuro") != null)  ld_cambeuro = rs.getDouble("cambeuro"); 

        if (ld_cambeuro > 0){
          ld_importo_euro = (ld_importo / ld_cambeuro);
        } else {
          ld_importo_euro = ld_importo;
        }

        ld_importo_totale += ld_importo_euro;

      }
      
      vist_mpron_posi.close();
      
      return ld_importo_totale;
    }

}

