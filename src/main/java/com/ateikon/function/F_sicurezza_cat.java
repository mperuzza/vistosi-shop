package com.ateikon.function;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.sql.PreparedStatement;

import java.util.ArrayList;

import com.ateikon.common.Atk_sql;


public class F_sicurezza_cat extends Atk_sql {

    public F_sicurezza_cat() {

        super();

        
    }



    /***

        Abilitazione 
    */


    public boolean isAbil( String cdfunzione_m
                         , String user_name
                                                    ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        if (cdfunzione_m.equals("")) return true;

       
        long tkfunzione = 0;
        String fgall_users = "N";
       
        tot_rec = 0;

        l_query  = "";
        l_query += "  select tkfunzione       \n";
        l_query += "       , fgall_users       \n";
        l_query += "    from pgmr.cat_funz     \n";
        l_query += "   where cdfunzione_m = ? \n";
        

        pstm = m_connection.prepareStatement(l_query);

        ind = 1;
        pstm.setString(ind++, cdfunzione_m);

        rs = pstm.executeQuery();

        if (rs!=null && rs.next()){
            if (rs.getObject("tkfunzione")!=null)  tkfunzione = rs.getLong("tkfunzione");
            if (rs.getObject("fgall_users")!=null)  fgall_users = rs.getString("fgall_users");
        }
        pstm.close();

        if (tkfunzione == 0){

            long tkutente = getTkutente(user_name);
        
            String fgadmin = "N";
        
            l_query  = "";
            l_query += " select fgadmin                   \n";
            l_query += "   from pgmr.cat_utente            \n";
            l_query += "  where tkutente = ?              \n";
    
            pstm = setQuery(l_query);
    
            ind = 1;
            pstm.setLong(ind++, tkutente);
    
            rs = pstm.executeQuery();
    
            if (rs!=null && rs.next()){
                
                if (rs.getObject("fgadmin" )!=null)  fgadmin  = rs.getString("fgadmin" );
            }
        
            if (fgadmin.equals("S")){
                return true;
            }else {
                throw new Exception("Funzione "+cdfunzione_m+" NON PREVISTA!");
            }
        }

        if (fgall_users.equals("S")){
            return true;
        }


        return  isAbil(tkfunzione, user_name);

    }

    /***

        Abilitazione 
    */


    public boolean isAbil( long   tkfunzione
                         , String user_name
                                                    ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;



        long tkutente = getTkutente(user_name);


        String fgadmin   = "N";
        String cdutente  = "";
        String cdagen    = "";
        String tkclie    = "";
        String tkforn    = "";
        String cdente    = "";


        tot_rec = 0;

        l_query  = "";
        l_query += " select fgadmin                   \n";
        l_query += "   from pgmr.cat_utente            \n";
        l_query += "  where tkutente = ?              \n";

        pstm = setQuery(l_query);

        ind = 1;
        pstm.setLong(ind++, tkutente);

        rs = pstm.executeQuery();

        if (rs!=null && rs.next()){
            
            if (rs.getObject("fgadmin" )!=null)  fgadmin  = rs.getString("fgadmin" );
        }

        if (fgadmin.equals("S")) return true;

        // --- Verifico se la funzione è Publica
        
        String fgall_users = "N";

        l_query   = "";
        l_query  += " select fgall_users       \n";
        l_query  += "   from pgmr.cat_funz    \n";
        l_query  += "  where tkfunzione = ?    \n";
        

        pstm = setQuery( l_query ) ;


        ind = 1;
        pstm.setLong(ind, tkfunzione); ind += 1;

        rs = pstm.executeQuery();

        if (rs!=null && rs.next()){
            if (rs.getObject("fgall_users")!=null ) fgall_users = rs.getString("fgall_users");
        }else {
            throw new Exception("Funzione tkfunzione = "+tkfunzione+" NON PREVISTA!");
        }


        if (fgall_users.equals("S")){
            return true;
        }


        // --- Attenzione verifico se l'utente è abilitati a questa funzione


        boolean lb_abil = false;

        l_query   = "";
        l_query  += " select count(1)            \n";
        l_query  += "   from pgmr.cat_funz_user \n";
        l_query  += "  where tkutente = ?        \n";
        l_query  += "    and tkfunzione = ?      \n";


        pstm = setQuery( l_query ) ;


        ind = 1;
        pstm.setLong  (ind, tkutente    ); ind += 1;
        pstm.setLong  (ind, tkfunzione  ); ind += 1;

        rs = pstm.executeQuery();

        if (rs!=null && rs.next() && rs.getObject(1) !=null && rs.getInt(1)>0){

            lb_abil = true;         
        }

        return lb_abil;

    }



    /***


    */

    public int init ( ) throws Exception {
        
    	int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        PreparedStatement pstm_box = null;
        ResultSet         rs_box   = null;


        com.ateikon.util.Atk_ctrl     atk_ctrl   = new com.ateikon.util.Atk_ctrl();
        com.ateikon.common.Cat_utente cat_utente = new com.ateikon.common.Cat_utente();
        
        setProfilo((Atk_sql)cat_utente);




        tot_rec = 0;

        l_query  = "";
        l_query += " select cdazie       \n";
        l_query += "   from pgmr.azienda \n";

        pstm = m_connection.prepareStatement(l_query);

        rs = pstm.executeQuery();

        while (rs!=null && rs.next()){
            
                    String ls_cdazie = rs.getString(1);


                    l_query  = "";
                    l_query += " select cddipa                   \n";
                    l_query += "   from pgmr.dipartimenti        \n";
                    l_query += "  where cdazie = '"+ls_cdazie+"' \n";
        
                    pstm_box = m_connection.prepareStatement(l_query);
        
                    rs_box = pstm_box.executeQuery();
        
                    while (rs_box!=null && rs_box.next()){
                        
                        String ls_cddipa = rs_box.getString(1);
        
                        l_query  = "";                                
                        l_query += " select count(*)                  \n";
                        l_query += "   from pgmr.cat_utente           \n";
                        l_query += "  where cdazie = '"+ls_cdazie+"'  \n";
                        l_query += "    and cddipa = '"+ls_cddipa+"'  \n";
                        l_query += "    and lower(user_name) = 'pgmr' \n";
                
                        int is_pgmr = sql_int(l_query);

                        if (is_pgmr <= 0){
                            
                            // inserisco l'utente di default per Azienda/dipartimento

                            cat_utente.cdazie = ls_cdazie;
                            cat_utente.cddipa = ls_cddipa;
                            cat_utente.profil = "cat_def";
                                
                            com.ateikon.structure.Rec_cat_utente lstr = new com.ateikon.structure.Rec_cat_utente();

                            lstr.user_name = "PGMR"; 
                            lstr.password  = atk_ctrl.encode("ADMIN"); 
                            lstr.dsutente  = "Amministrator - "+ls_cdazie+"/"+ls_cddipa; 
                            lstr.fgadmin   = "S"; 

                            tot_rec = cat_utente.execute(lstr);

                            if (tot_rec != 1){
                                cat_utente.m_connection.rollback();
                                
                                throw new Exception("Errore INS PGMR --> cat_utente ");
                            }

                        }
                    }   // FINE dipartimenti
                    pstm_box.close();

        }   // FINE azienda
        pstm.close();


        cat_utente.m_connection.commit();


        return 1;

    }



}
