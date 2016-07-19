package com.ateikon.common;

import com.ateikon.structure.Str_crm_credential;
import com.ateikon.util.Atk_ctrl;
import com.ateikon.util.StringUtils;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;


/**********************/
//     NOTA:
//     modificare questa classe nel portale implica un allineamento della stessa classe in eprogen
//     e viceversa modificare questa classe nel eprogen implica un allineamento della stessa classe nel portale
//
/*********************/

public class Crm_credential extends com.ateikon.standard.Crm_credential {


    public Crm_credential() {

        super();
    }

    
    /**
   
    Metodo che genera il keycode, data una struttura
    @param astr
    @return
    @throws Exception 
    */
    public int of_createKeycode_generate (Str_crm_credential astr) throws Exception {
      int ind = 0;
      int tot_rec = 0;
      PreparedStatement pstm = null;
      ResultSet rs = null;
      
      // crea una stringa alfanumerica random di n caratteri
      // no caratteri di punteggiatura
      String ls_keycode = StringUtils.randomString(64);
    
      ls_keycode = ls_keycode.toLowerCase();

      astr.keycode = ls_keycode;
      
      
      return 1;
    }
    
    /**
    
    Metodo che crea il keycode, data una struttura
    @param astr
    @return
    @throws Exception 
    */
    public int of_createKeycode (Str_crm_credential astr) throws Exception {
      int ind = 0;
      int tot_rec = 0;
      PreparedStatement pstm = null;
      ResultSet rs = null;
      
      Atk_ctrl atk_ctrl = new Atk_ctrl();
      
      int rc = 0;
      
      // chiama metodo che genera keycode
      if ( of_createKeycode_generate (astr) != 1) return -1;
      
      if (!astr.keycode.equals("")){
        
        // verificare che il keycode non esista già
        if (of_existKeycode(astr.keycode)){
          
          //  - se esiste chiama nuovamente se stesso
          rc = of_createKeycode(astr);          
          
        } else {
          
          //  - altrimenti viene inserito un record nella tabella pgmr.crm_credential database con questi dati:
          //    - tkcredential (PK) : chiave primaria
          //    - keycode : keycode passato cifrato
          //    - dtinva : data inizio validità (giorno di creazione)
          //    - dtfiva : data fine validità (giorno di creazione + 15)
          //    - tkutente : identificativo dell'utente del portale
          
          com.ateikon.structure.Rec_crm_credential lstr = new com.ateikon.structure.Rec_crm_credential();
          
          lstr.keycode = astr.keycode;
          lstr.tkutente = astr.tkutente;
          
          lstr.dtival = of_getTimestamp();
          lstr.dtfval = atk_ctrl.relativeDate(new Timestamp(lstr.dtival.getTime()), GG_BETWEEN_DTIVAL_AND_DTFVAL);
          
          rc = execute(lstr);
          
          if (rc != 1){
            m_connection.rollback();
            throw new Exception("Errore INS crm_credential! ");
          }
          
        }

      }  
      
      
      return rc;
    }
    
    /**
    
    @param as_keycode
    @return
    @throws Exception 
    */
    public boolean of_existKeycode (String as_keycode) throws Exception {
      int ind = 0;
      int tot_rec = 0;
      PreparedStatement pstm = null;
      ResultSet rs = null;
      
      Atk_ctrl atk_ctrl = new Atk_ctrl();
      
      
      boolean lb_ = false;
          
      
      l_query   = "";
      l_query  += " select *                            \n";
      l_query  += "   from pgmr.crm_credential          \n";
      l_query  += "  where keycode = '"+ as_keycode +"' \n";
      
      pstm = m_connection.prepareStatement(l_query);
      
      ind = 1;
      
      rs = pstm.executeQuery();
      
      if (rs!=null && rs.next()){
        lb_ = true;
      }
      
      pstm.close();
        
      
      
      return lb_;
    }
    
    /**
    
    @param as_keycode
    @return
    @throws Exception 
    */
    public long of_getTkutente (String as_keycode) throws Exception {
      int ind = 0;
      int tot_rec = 0;
      PreparedStatement pstm = null;
      ResultSet rs = null;
      
      long ll_ = 0;
          
      
      l_query   = "";
      l_query  += " select tkutente                                         \n";
      l_query  += "   from pgmr.crm_credential                              \n";
      l_query  += "  where keycode = '"+ as_keycode +"'                     \n";
      l_query  += "    and (dtival is null or dtival <= "+ sysdate +")      \n";
      l_query  += "    and (dtfval is null or dtfval >= "+ sysdate +")      \n";
      
      ll_ = sql_long(l_query);  
      
      
      return ll_;
    }
    
    
    public int of_getUrlKeycode (Str_crm_credential astr) throws Exception {
      int ind = 0;
      int tot_rec = 0;
      PreparedStatement pstm = null;
      ResultSet rs = null;
      
      astr.keycode_url = SERVLET_CREDENTIAL;
      
      //---------
      //Creo keycode
      if ( of_createKeycode (astr) != 1) return -1;
      
      
      //---------
      //Setto parametri
      String ls_querys = "";
      
      //Parametro keycode
      ls_querys += (ls_querys.equals("")? "?" : "&");
      ls_querys += "keycode="+ astr.keycode;
      
      //Altri parametri
      String ls_other_param  = "";
      
      ls_other_param = astr.url;
      ls_other_param = ls_other_param.replace("?", ESCAPE_URL_PDOMANDA);
      ls_other_param = ls_other_param.replace("&", ESCAPE_URL_ECOMM);
      
      ls_querys += (ls_querys.equals("")? "?" : "&");
      ls_querys += "querys="+ ls_other_param;
      
      
      
      
      astr.keycode_url += ls_querys;
      
      return 1;
    }
    
    public static int GG_BETWEEN_DTIVAL_AND_DTFVAL = 15;
    public static String  SERVLET_CREDENTIAL = "epKeycode.jsp";
}

