package com.ateikon.structure;

import java.io.IOException;

import java.sql.ResultSet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ateikon.common.Atk_sql;


public class Str_jsp extends Object {

    public Str_jsp(){
 
        super();

    }


    public void dbconnect(HttpServletRequest request) throws Exception{
        
        atk_sql = new Atk_sql();

        atk_sql.dbConnection();

        HttpSession session = request.getSession(true);
        
        atk_sql.profil = "PUBLIC_USER";
        atk_sql.cdazie = "01";
        atk_sql.cddipa = "0000";
    
        if (session.getAttribute("user_name")!=null) atk_sql.profil = (String ) session.getAttribute("user_name");
        if (session.getAttribute("cdazie")!=null)    atk_sql.cdazie = (String ) session.getAttribute("cdazie");
        if (session.getAttribute("cddipa")!=null)    atk_sql.cddipa = (String ) session.getAttribute("cddipa");

    }



    public void parametri(HttpServletRequest request) throws Exception{

    
        html = new com.ateikon.util.HTML();
        atk_ctrl = new com.ateikon.util.Atk_ctrl();
    
        cat_costanti  = new com.ateikon.common.Cat_costanti();
    
        atk_sql.setProfilo((Atk_sql) cat_costanti   );
    
        conn = atk_sql.m_connection;
    
    
        numFormat = java.text.NumberFormat.getInstance(java.util.Locale.ITALY);
        numFormat.setGroupingUsed( true );
        numFormat.setMaximumFractionDigits( 2 );
        numFormat.setMinimumFractionDigits( 0 );
    
        qtaFormat = java.text.NumberFormat.getInstance(java.util.Locale.ITALY);
        qtaFormat.setGroupingUsed( true );
        qtaFormat.setMaximumFractionDigits( 2 );
        qtaFormat.setMinimumFractionDigits( 0 );
    
    
        przFormat = java.text.NumberFormat.getInstance(java.util.Locale.ITALY);
        przFormat.setGroupingUsed( true );
        przFormat.setMaximumFractionDigits( 2 );
        przFormat.setMinimumFractionDigits( 2 );
    
        dimFormat = java.text.NumberFormat.getInstance(java.util.Locale.ITALY);
        dimFormat.setGroupingUsed( true );
        dimFormat.setMaximumFractionDigits( 2 );
        dimFormat.setMinimumFractionDigits( 0 );
    
        przkgFormat = java.text.NumberFormat.getInstance(java.util.Locale.ITALY);
        przkgFormat.setGroupingUsed( true );
        przkgFormat.setMaximumFractionDigits( 5 );
        przkgFormat.setMinimumFractionDigits( 5 );
    
    
    
        /*****************************************************
        *
        *   PARAMETRI COSTANTI_COMM
        *
        *****************************************************/
    
        // parametri comuni

        if (cat_costanti.cdazie.equals("")) cat_costanti.cdazie = "01";
    
        // parametri comuni
    
        String siteRoot            = cat_costanti.getCostvalue("siteRoot");
        String siteContext         = cat_costanti.getCostvalue("siteContext");
        String cliente             = cat_costanti.getCostvalue("cliente");
        String title               = cat_costanti.getCostvalue("title");
        String mail_admin          = cat_costanti.getCostvalue("mail_admin");
    
        String page_pagamento      = cat_costanti.getCostvalue("pagamento");
        String cat_publico         = cat_costanti.getCostvalue("cat_publico");
        String tkclie_publico      = cat_costanti.getCostvalue("tkclie_publico");
        String PUBLIC_USER         = "PUBLIC_USER";
        String PUBLIC_CDAZIE       = "01";
        String PUBLIC_CDDIPA       = "0000";
        int    nr_inc_paginazione  = 1;
    
        String cdstat_it           = cat_costanti.getCostvalue("cdstat_it");
        
    
            // Head
    
        String logo_cliente        = cat_costanti.getCostvalue("logo_cliente");
        String logo_link           = cat_costanti.getCostvalue("logo_link");
        String ep_style            = cat_costanti.getCostvalue("style");
        String head_background_img = cat_costanti.getCostvalue("head_background_img");
        String head_bgcolor        = cat_costanti.getCostvalue("head_bgcolor");
    
        if (logo_cliente.equals("")) logo_cliente = "img/logo_epro.gif";
        if (cliente.equals("")) cliente = atk_sql.ATEIKON;
        if (ep_style.equals("")) ep_style = "ATEIKON";


        slash = System.getProperty( "file.separator" );
    
        base_URL = getBase_URL ( request );

        seStesso = request.getRequestURI();
        seStesso = seStesso.replace('\\', '/');

        if (seStesso.lastIndexOf("/") >=0){
            seStesso = seStesso.substring( (seStesso.lastIndexOf("/")+1) );
        }

        
    }  // FINE parametri


    public int sicurezza (HttpServletRequest request, HttpServletResponse response) throws Exception{

        com.ateikon.common.Cat_utente  s_cat_utente = new com.ateikon.common.Cat_utente();
        atk_sql.setProfilo((Atk_sql) s_cat_utente      );

        s_tkutente      = 0;
        s_user_name     = "";
        s_cdazie        = "";
        s_cddipa        = "";
        s_fgadmin       = "";
        s_autorizzato   = false;

        s_dsutente      = "";
        s_tkclie        = "";
        s_tkforn        = "";
        s_cdagen        = "";
        s_cdcapoarea        = "";
        s_cdutente      = "";
        s_cdente        = "";



        session = request.getSession(true);
        application =  session.getServletContext();

        


        if (session.getAttribute("user_name") == null
        ||  session.isNew()
        ){
    
            s_user_name = "";
    
            System.out.println("Sessione Scaduta ... Re-login");

            s_url  = "../catLogin.jsp";
    
            response.sendRedirect(s_url);
    
            return -1;
    
        }else {
    
            s_user_name = (String) session.getAttribute("user_name");
            s_cdazie    = (String) session.getAttribute("cdazie");
            s_cddipa    = (String) session.getAttribute("cddipa");
    
            if (s_user_name.equals("") ){
    
                System.out.println("Utente non Valorizzato ... Re-login");
        
                s_url  = "../catLogin.jsp";
    
                response.sendRedirect(s_url);
    
                return -2;
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
                //       utente ha la possibilit� di cambiare il suo profilo
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
        
                System.out.println("Utente "+s_user_name+" non Censito ... Re-login");
        
                s_url  = "catLogin.jsp";
        
                response.sendRedirect(s_url);
        
                return -3;
        
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
    


        return 1;

    }


    public void exception(Exception ex){

        System.err.println();
        System.err.println();
        System.err.println("********************************************************");
        System.err.println("    Exception  "+seStesso);
        System.err.println("********************************************************");
        System.err.println();
        System.err.println();
        
        ex.printStackTrace();
    
        System.err.println();
        System.err.println();
        System.err.println("********************************************************");
        
        try {
            if (atk_sql != null) atk_sql.m_connection.rollback();
        }catch (Exception ex_page_sql){ 
        }
        
    }



    public void dbclose() {

        if (atk_sql == null) return;
    
        try {
            atk_sql.m_connection.commit();
            atk_sql.m_connection.close();
        }catch (Exception _e){ 
        
        }

    }



    /***
    */

    public String getBase_URL ( HttpServletRequest request ) throws Exception {
        
		String  server_protocol = "http://";

        if (request.isSecure()) server_protocol = "https://";

		String  server_name     = request.getServerName();
		int     server_port     = request.getServerPort();
		String  server_context  = request.getContextPath();
        

        String ls_base_URL = server_protocol 
                           + server_name
                           + ":" + server_port
                           + server_context + "/"
                           ;




        return ls_base_URL ;

    }






    /****************************************
     * data members
     ****************************************/

    public HttpSession session = null;
        
    public ServletContext application =  null;
    


    public Atk_sql atk_sql = null;

    public String seStesso = "Atk_servlet";
    public String funzione = "";


    public String azione  = "";
    public String message = "";
    public int    tot_rec = 0;
    public int    ind     = 0;
    public int    idx_f   = 0;



    public ResultSet rs     = null;
    public ResultSet rs_box = null;

    public int  gg = 0;
    public int  mm = 0;
    public int  sa = 0;
    public int  hh = 0;
    public int  mi = 0;


    



    // --- Parametri

    public    java.sql.Connection              conn          = null;

    public    com.ateikon.util.HTML            html          = null;
    public    com.ateikon.structure.Str_html   str_html      = null;
    public    com.ateikon.util.Atk_ctrl        atk_ctrl      = null;
    
    public    com.ateikon.common.Costanti_comm costanti_comm = null;
    public    com.ateikon.common.Cat_costanti  cat_costanti   = null;

    public    java.text.NumberFormat numFormat   = null;
    public    java.text.NumberFormat qtaFormat   = null;
    public    java.text.NumberFormat przFormat   = null;
    public    java.text.NumberFormat dimFormat   = null;
    public    java.text.NumberFormat przkgFormat = null;


    public    String    cliente        = "";
    public    String    title          = "";
    public    String    logo_cliente   = "";
    public    String    mail_admin     = "";
    public    String    siteRoot       = "";
    public    String    siteContext    = "";
    public    String    logo_link      = "";

    public    String    par_visu_foot  = "";
    public    String    head_tipomenu  = "";

    public    String    dwlingua_link  = "";
    public    String    slash          = "";



    // Sicurezza 


    public    String   s_user_name   = "";
    public    String   s_cdazie      = "";
    public    String   s_cddipa      = "";

    public    String   s_cdling      = "I";
    public    String   s_cdling_def  = "N";
    public    String   s_cdling_edit = "N";

    public    String   s_url = "";
    public    String   s_queryString = "";

    public    String   s_dsutente   = "";

    public    String   s_tkclie     = "";
    public    String   s_tkforn     = "";
    public    String   s_cdagen     = "";
    public    String   s_cdcapoarea     = "";
    public    String   s_cdente     = "";
    public    String   s_cdutente   = "";
    public    long     s_tkutente   = 0;

    public    String   s_fgadmin    = "";

    public    String   s_email      = "";

    public    boolean  s_autorizzato = true;


    public     String base_URL  = ""; 




}
