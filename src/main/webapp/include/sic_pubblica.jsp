<%
    /*** Gesione Sicurezza ****/

        String s_user_name     = "";
        String s_cdutente      = "";
        String s_cdazie        = "";
        String s_cddipa        = "";
        String s_cdutente_gest = "";
        String s_cdagen        = "";
        String s_cdclie_m      = "";
        String s_ragcog        = "";
        String s_tkclie        = "";
        String s_email         = "";
        String s_fgadmin       = "N";
        String s_dsutente      = "";

        java.util.Vector s_vec_cdlist = new java.util.Vector();
        String s_cdlist        = "";
    
    
        String s_cdling      = "I";
        String s_cdling_def  = "I";
        String s_cdling_edit = "N";

    
        // ********************************
        // *** Gestione Multi Lingua    ***
        // ********************************

        if (request.getParameter("cdling")!=null ) s_cdling = request.getParameter("cdling");

        session.setAttribute("cdling", s_cdling);
        

        com.ateikon.common.Atk_dwlingua  atk_dwlingua = new com.ateikon.common.Atk_dwlingua();
    
        atk_sql.setProfilo((Atk_sql) atk_dwlingua );
    
    
        if (!s_cdling_edit.equals("S")){
            dwlingua_link = "";
        }else {
            dwlingua_link = com.ateikon.util.StringUtils.replace(dwlingua_link, "seStesso", seStesso);
        }
    
        atk_dwlingua.dwin    = seStesso;
        atk_dwlingua.cdling  = s_cdling;
    

        // forzo Azienda / Dipartimento di Default

        atk_sql.cdazie = "01";
        atk_sql.cddipa = "0000";

    
    
        com.ateikon.function.F_sicurezza_cat f_sicurezza = new com.ateikon.function.F_sicurezza_cat();
              
        atk_sql.setProfilo((Atk_sql) f_sicurezza );
    
        if (seStesso.equals("catLogin.jsp")){
            f_sicurezza.init();
        }
    

    /*** Fine ***/

%>
