package com.ateikon.function;

import java.sql.ResultSet;
import java.sql.PreparedStatement;

import com.ateikon.common.Atk_sql;
import com.ateikon.common.Ep_contator;
import com.ateikon.common.Ep_costanti;
import com.ateikon.common.Ep_utente;

import com.ateikon.structure.Rec_ep_utente;



public class F_utente extends Atk_sql {

    public F_utente() {

        super();

    }






    /***

        getEmail

    */
    public String getEmailFunzione( long   tkutente
                                  , String cdfunzione_m) throws Exception {

						ResultSet rs = null;

						com.ateikon.common.Ep_funz_tpservizio ep_funz_tpservizio = new com.ateikon.common.Ep_funz_tpservizio();

						setProfilo((Atk_sql) ep_funz_tpservizio);


						String cdutente_tpservizio_m = "";

						rs = ep_funz_tpservizio.search(cdfunzione_m, false, "");

						if (rs!=null && rs.next()){

						  cdutente_tpservizio_m = "";

								if (rs.getObject("cdutente_tpservizio_m")!= null)  cdutente_tpservizio_m = rs.getString("cdutente_tpservizio_m");
						}

						return getEmailTiposervizio(tkutente, cdutente_tpservizio_m);

    }   // FINE getEmail


    /***

        getEmail

    */
    public String getEmailTiposervizio( long   tkutente
                                      , String cdutente_tpservizio_m) throws Exception {

						ResultSet rs = null;

						com.ateikon.common.Ep_utente ep_utente = new com.ateikon.common.Ep_utente();
						com.ateikon.common.Ep_utente_tpservizio ep_utente_tpservizio = new com.ateikon.common.Ep_utente_tpservizio();
						com.ateikon.common.Ep_utente_recapito   ep_utente_recapito   = new com.ateikon.common.Ep_utente_recapito  ();
						com.ateikon.common.Ep_utente_recapito_tipo   ep_utente_recapito_tipo   = new com.ateikon.common.Ep_utente_recapito_tipo  ();

						setProfilo((Atk_sql) ep_utente);
						setProfilo((Atk_sql) ep_utente_tpservizio);
						setProfilo((Atk_sql) ep_utente_recapito  );
						setProfilo((Atk_sql) ep_utente_recapito_tipo  );


      long tkutente_rec_tipo_EMAIL = ep_utente_recapito_tipo.getKey_m("EMAIL");
						long tkutente_tpservizio = ep_utente_tpservizio.getKey_m(cdutente_tpservizio_m);

					 String ls_email = "";

						if (   tkutente_tpservizio > 0
										&&	tkutente_rec_tipo_EMAIL > 0){

					   rs = ep_utente_recapito.search(tkutente, tkutente_tpservizio, tkutente_rec_tipo_EMAIL, false, "");

					   if (rs!=null && rs.next()){

					     ls_email = "";

					   		if (rs.getObject("recapito")!= null)  ls_email = rs.getString("recapito");
					   }
						}

						if (ls_email.equals("")){
								Rec_ep_utente lstr_eute = new Rec_ep_utente();

        rs = ep_utente.getKey(tkutente);

								if (rs!=null && rs.next()){
										lstr_eute.setResultSet(rs);
								}

								ls_email = lstr_eute.email;
						}

						ep_utente.close();
						ep_utente_tpservizio.close();
						ep_utente_recapito.close();
						ep_utente_recapito_tipo.close();

						return ls_email;

    }   // FINE getEmail


    public String message = "";



}
