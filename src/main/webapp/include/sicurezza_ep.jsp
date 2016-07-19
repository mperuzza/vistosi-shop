<%

    // CTRL AUTORIZZAZIONE


    //com.ateikon.common.Cat_utente  s_cat_utente = new com.ateikon.common.Cat_utente();
    com.ateikon.common.Ep_utente  s_cat_utente = new com.ateikon.common.Ep_utente();
    atk_sql.setProfilo((Atk_sql) s_cat_utente      );

    com.ateikon.common.Atk_lingua s_atk_lingua = new com.ateikon.common.Atk_lingua();
    atk_sql.setProfilo((Atk_sql) s_atk_lingua);


    long     s_tkutente      = 0;
    String   s_user_name     = "";
    String   s_cdazie        = "";
    String   s_cddipa        = "";
    String   s_fgadmin       = "";
    boolean  s_autorizzato   = false;

    // --- variabili di appoggio 
    String   s_dsutente      = "";
    String   s_tkclie        = "";
    String   s_tkforn        = "";
    String   s_cdagen        = "";
    String   s_cdcapoarea        = "";
    String   s_cdutente      = "";
    String   s_cdente        = "";

    // --- variabili cliente
    String   s_cdclie_m      = "";
    String   s_cdnazi        = "";
    String   s_email         = "";

    java.util.Vector s_vec_cdlist = new java.util.Vector();
    String   s_cdlist        = "";
    String   s_ragcog        = "";
    String   s_dsvacodi      = "";


    String   s_cdpagame      = "";
    String   s_pgcodi        = "";
    String   s_dpcodi        = "";
    String   s_cddpag        = "";
    String   s_ffinme        = "";
    String   s_cdiva         = "";
    String   s_cdfisc        = "";
    String   s_tafasc        = "S";
    String   s_dsfisc        = "";

    String   s_vacodi        = "";
    String   s_ivato         = "";
    String   s_fgweb         = "S";
    String   s_fnoord        = "N";


    String   s_dspagame      = "";
    String   s_dsformpaga    = "";
    String   s_dsdilapaga    = "";
    String   s_dsdecorpag    = "";
    
    // scontistica del cliente

    double   s_sconto1 = 0;
    double   s_sconto2 = 0;
    double   s_sconto3 = 0;
    double   s_sconto4 = 0;
    double   s_scrap1  = 0;
    double   s_scrap2  = 0;
    double   s_scocas  = 0;


    String s_cdling      = "I";
    String s_cdling_def  = "N";
    String s_cdling_edit = "N";

    String s_url = "";
    String s_queryString = "";

    boolean ctrl_listino = true;


    boolean catalogo_off = false;

    if (catalogo_off){
        response.sendRedirect("catalogo_off.jsp");
        return;
    }
 
    if (request.getParameter("cdling")!=null) {       
        s_cdling = request.getParameter("cdling");
        
        s_cdling_def = s_atk_lingua.getFgdef(s_cdling);
        
        session.setAttribute("cdling", s_cdling );  
        session.setAttribute("cdling_def", s_cdling_def );  
    } 
    


    if (session.getAttribute("user_name") == null
    ||  session.isNew()
    ){

        
        if (cat_publico.equals("S")){
        
            session.setAttribute("user_name", PUBLIC_USER);
            session.setAttribute("cdazie", PUBLIC_CDAZIE);            
            session.setAttribute("cddipa", PUBLIC_CDDIPA);
        
        }else {
    
            s_user_name = "";
    
            System.out.println("Sessione Scaduta ... Re-login");
    
            s_url  = "catLogin.jsp";
            s_url += "?nextPage="+seStesso;
    
            s_queryString  = atk_ctrl.getRequestQuery(request, null);
    
            if(!s_queryString.equals("")){
                s_url += "&" + s_queryString;
            }
    
            response.sendRedirect(s_url);
    
            return;

        }   // FINE if (cat_publico.equals("S")){



    }   // FINE  if   session.isNew()


    s_user_name = (String) session.getAttribute("user_name");
    s_cdazie    = (String) session.getAttribute("cdazie");
    s_cddipa    = (String) session.getAttribute("cddipa");
    s_cdling    = (String) session.getAttribute("cdling");



    if (s_cdling == null || s_cdling.equals("") ) {
        s_cdling = s_atk_lingua.getDefault();
        s_cdling_def = "S";
        session.setAttribute("cdling", s_cdling );  
        session.setAttribute("cdling_def", s_cdling_def );  
        session.setAttribute("cdling_edit", "N");  
    }

    rs = s_cat_utente.getUser_name(s_user_name, s_cdazie, s_cddipa);
    

    if (rs != null && rs.next()){

        // Imposto Il Profilo di tutte Le Classi 

        atk_sql.profil      = s_user_name;
        atk_sql.cdazie      = s_cdazie;
        atk_sql.cddipa      = s_cddipa;

        if (rs.getObject("tkutente")!=null) s_tkutente = rs.getLong  ("tkutente");
        if (rs.getObject("email"   )!=null) s_email    = rs.getString("email"   );
        if (rs.getObject("dsutente")!=null) s_dsutente = rs.getString("dsutente");
        if (rs.getObject("tkclie"  )!=null) s_tkclie   = rs.getString("tkclie"  );
        if (rs.getObject("cdagen"  )!=null) s_cdagen   = rs.getString("cdagen"  );
        if (rs.getObject("tkforn"  )!=null) s_tkforn   = rs.getString("tkforn"  );
        if (rs.getObject("fgadmin" )!=null) s_fgadmin  = rs.getString("fgadmin" );
        
        

        // 
        //       se trovo la variabile in sessione significa che questo 
        //       utente ha la possibilità di cambiare il suo profilo
        // 

        if (session.getAttribute("s_tkclie")!=null){
            s_tkclie = (String) session.getAttribute("s_tkclie");
        }
        if (session.getAttribute("s_tkforn")!=null){
            s_tkforn = (String) session.getAttribute("s_tkforn");
        }
        if (session.getAttribute("s_cdagen")!=null){
            s_cdagen = (String) session.getAttribute("s_cdagen");
        }


    }else {

        if (cat_publico.equals("S")){

            // Imposto il Defaul 

            s_dsutente  = "/";


            atk_sql.profil      = s_user_name;
            atk_sql.cdazie      = s_cdazie;
            atk_sql.cddipa      = s_cddipa;

            // Imposto il Cliente di Publico

            s_tkclie = tkclie_publico;

        }else {

            System.out.println("Utente "+s_user_name+" non Censito ... Re-login");
    
            s_url  = "catLogin.jsp";
    
            response.sendRedirect(s_url);
    
            return;
        }

    }


    if (s_fgadmin.equals("S")){
    }else if (!s_cdutente.equals("")){
        atk_sql.s_cdutente = s_cdutente;
    }else if (!s_cdcapoarea.equals("")){
        atk_sql.s_cdcapoarea = s_cdcapoarea;
    }else if (!s_cdagen.equals("")){
        atk_sql.s_cdagen = s_cdagen;
    }else if (!s_tkclie.equals("")){
        atk_sql.s_tkclie = s_tkclie;
    }










    if (s_fgadmin.equals("S")){
        if (s_tkclie.equals("")){
            s_tkclie = cat_costanti.getCostvalue("tkclie_def_ordi");
        }
    }


    // *** Decodifico l'agente

    if (!s_cdagen.equals("")){



        if (s_tkclie.equals("")){
        
            s_tkclie = cat_costanti.getCostvalue("tkclie_def_ordi");
                

            /****************
            // --- se non ho impostato un cliente verifico con quale listino voglio lavorare

            String ls_s_cdlist = "";

            for(int i=1; i<=5; i++){
                
                ls_s_cdlist = cat_costanti.getCostvalue("cdlist_def_0"+i);
                if (!ls_s_cdlist.equals("")){
                    s_vec_cdlist.add(ls_s_cdlist); 
                }
            }
            
            if (s_vec_cdlist.size()>0){
                if (session.getAttribute("s_cdlist")!=null){
                    ls_s_cdlist = (String)session.getAttribute("s_cdlist");
    
                    if (s_vec_cdlist.contains(ls_s_cdlist)){
                        s_cdlist =  ls_s_cdlist;
                    }else {
                        s_cdlist = (String) s_vec_cdlist.elementAt(0);
                        session.setAttribute("s_cdlist", s_cdlist);
                    }
                }else {
                    s_cdlist = (String) s_vec_cdlist.elementAt(0);
                    session.setAttribute("s_cdlist", s_cdlist);
                }

            }   // FINE if (s_vec_cdlist.size()>0){
            *****************************************************/
        }   // FINE if (s_tkclie.equals("")){

    }   // FINE if (!s_cdagen.equals("")){
    


    // *** Decodifico il cliente in Sessione
    
    if (!s_tkclie.equals("")){
        
        // *** Decodifico il Cliente 

        com.ateikon.common.Archclie         s_archclie      = new com.ateikon.common.Archclie(); 
        com.ateikon.common.Assofiscal       s_assofiscal    = new com.ateikon.common.Assofiscal(); 
        
        
        atk_sql.setProfilo((Atk_sql) s_archclie   );
        atk_sql.setProfilo((Atk_sql) s_assofiscal );

        
        rs = s_archclie.getCliente(s_tkclie);

        if (rs!=null && rs.next()){
            
            if (rs.getObject("cdente"  )!=null) s_cdente   = rs.getString("cdente");
            if (rs.getObject("cdlist"  )!=null) s_cdlist   = rs.getString("cdlist");
            if (rs.getObject("ragcog"  )!=null) s_ragcog   = rs.getString("ragcog");
            if (rs.getObject("cdclie_m")!=null) s_cdclie_m = rs.getString("cdclie_m");
            
            if (rs.getObject("cdpagame")!=null) s_cdpagame = rs.getString("cdpagame");
            
            if (rs.getObject("sconto1" )!=null) s_sconto1  = rs.getDouble("sconto1");
            if (rs.getObject("sconto2" )!=null) s_sconto2  = rs.getDouble("sconto2");
            if (rs.getObject("sconto3" )!=null) s_sconto3  = rs.getDouble("sconto3");
            if (rs.getObject("sconto4" )!=null) s_sconto4  = rs.getDouble("sconto4");

            if (rs.getObject("scrap1"  )!=null) s_scrap1   = rs.getDouble("scrap1");
            if (rs.getObject("scrap2"  )!=null) s_scrap2   = rs.getDouble("scrap2");

            if (rs.getObject("scocas"  )!=null) s_scocas   = rs.getDouble("scocas");

            if (rs.getObject("pgcodi"  )!=null) s_pgcodi   = rs.getString("pgcodi");
            if (rs.getObject("dpcodi"  )!=null) s_dpcodi   = rs.getString("dpcodi");
            if (rs.getObject("cddpag"  )!=null) s_cddpag   = rs.getString("cddpag");
            if (rs.getObject("ffinme"  )!=null) s_ffinme   = rs.getString("ffinme");
            if (rs.getObject("cdiva"   )!=null) s_cdiva    = rs.getString("cdiva");
            if (rs.getObject("cdfisc"  )!=null) s_cdfisc   = rs.getString("cdfisc");
            if (rs.getObject("fnoord"  )!=null) s_fnoord   = rs.getString("fnoord");
            

            if (s_cdfisc.equals("")){
                
                com.ateikon.common.Costanti_comm s_costanti_comm = new com.ateikon.common.Costanti_comm();
                
                atk_sql.setProfilo((Atk_sql) s_costanti_comm );

                s_cdfisc = s_costanti_comm.getCostvalue("cdfisc_default");
            }

            rs = s_assofiscal.getTafasc( s_cdfisc );

            if (rs!=null && rs.next()){

                if (rs.getObject("tafasc")!=null)  s_tafasc = rs.getString("tafasc");
                if (rs.getObject("dsfisc")!=null)  s_dsfisc = rs.getString("dsfisc");
            }
            
            s_archclie.close();

        }else {

            s_archclie.close();
            throw new Exception("TOKEN CLIENTE NON TROVATO tkclie = "+s_tkclie);
            
        }


    }   // FINE if tkclie <> ""


    // ********************************
    // *** AUTORIZZAZIONI di PAGINA ***
    // ********************************


    com.ateikon.function.F_sicurezza_cat f_sicurezza = new com.ateikon.function.F_sicurezza_cat();
          
    atk_sql.setProfilo((Atk_sql) f_sicurezza );

    if (seStesso.equals("catLogin.jsp")){
        f_sicurezza.init();
    }


    // --- verifico che l'utente sia stato definito correttamente
    //     ho come agente/cliente/fornitore

    s_autorizzato = f_sicurezza.isAbil(funzione, s_user_name);

    if (!s_autorizzato){
        
            s_url  = "catLogin.jsp";
            s_url += "?azione=re-login&message=Funzione NON Autorizzata!";
    
            response.sendRedirect(s_url);
    
            return;
    }




    if (funzione.equals("CATALOGO")){

        if (s_fnoord.equals("S")){

            s_url  = "catMessage.jsp?message=msg026";
    
            response.sendRedirect(s_url);
    
            return;
            
        }

        if (!s_cdlist.trim().equals("") && s_cdlist != null) {
        
            com.ateikon.common.Lis_listino  s_lis_listino = new com.ateikon.common.Lis_listino();
        
            atk_sql.setProfilo((Atk_sql) s_lis_listino );
        
            rs = s_lis_listino.getCdlist(s_cdlist);
    
            if (rs !=null && rs.next()){
    
                s_vacodi = rs.getString("vacodi");
                s_ivato  = rs.getString("ivato");

    
                rs = s_lis_listino.getVacodi( s_cdlist ) ;
    

                if (rs !=null && rs.next()){
                    s_dsvacodi = rs.getString("dsvacodi").trim();
                    s_dsvacodi = com.ateikon.util.StringParsToHTML.escapeHTML(s_dsvacodi);
    
                    if (s_dsvacodi.equals("EURO")) s_dsvacodi = "&euro;";
                    if (s_dsvacodi.equals("EUR")) s_dsvacodi = "&euro;";
    
                }
    
            }else {
                
                s_url  = "catMessage.jsp?message=msg023";

                response.sendRedirect(s_url);

                return;

            }
            s_lis_listino.close();
        }

    } // FINE Funzione Catalogo






    // **** FINE CATALOLGO *****



    // ********************************
    // *** Gestione Multi Lingua    ***
    // ********************************


    com.ateikon.common.Atk_dwlingua  atk_dwlingua = new com.ateikon.common.Atk_dwlingua();

    atk_sql.setProfilo((Atk_sql) atk_dwlingua );

    if (!s_cdling_edit.equals("S")){
        dwlingua_link = "";
    }else {
        dwlingua_link = com.ateikon.util.StringUtils.replace(dwlingua_link, "seStesso", seStesso);
    }

    atk_dwlingua.dwin           = seStesso;
    atk_dwlingua.cdling         = s_cdling;
    //atk_dwlingua.fgdef          = s_cdling_def;



    boolean s_visu_prezzo = false;

    String ep_cat_prezzo = cat_costanti.getCostvalue("cat_prezzo");
    String ep_cat_carrello = cat_costanti.getCostvalue("cat_carrello");
    
    if (ep_cat_prezzo.equals("mai")){

    }else  if (ep_cat_prezzo.equals("loged_user_nopromo")){

        if (!s_user_name.equals("") 
            && !s_user_name.equals(PUBLIC_USER)
             ){
            s_visu_prezzo = true;
        }else {
            
            if (seStesso.equals("catalogo_promo.jsp") ){
                s_visu_prezzo = true;
            }

        }

    }else if (ep_cat_prezzo.equals("loged_user")){
        
        if (!s_user_name.equals("") && !s_user_name.equals(PUBLIC_USER)){
            s_visu_prezzo = true;
        }

    }else if (ep_cat_prezzo.equals("sempre")){

        s_visu_prezzo = true;
    }







%>
