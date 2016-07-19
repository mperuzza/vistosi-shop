<%




    // ** versione 1.0
    // ** 
    // ** aggiunto i parametri da DB con costanti_comm
    // ** 


    java.sql.Connection conn = null;

    com.ateikon.common.Cat_costanti cat_costanti = new com.ateikon.common.Cat_costanti();

    atk_sql.setProfilo((Atk_sql) cat_costanti );

    conn = atk_sql.m_connection;


    com.ateikon.util.HTML  html = new com.ateikon.util.HTML();
    com.ateikon.structure.Str_html str_html = null;
    com.ateikon.util.Atk_ctrl atk_ctrl = new com.ateikon.util.Atk_ctrl();

    


    java.text.NumberFormat numFormat = java.text.NumberFormat.getInstance(java.util.Locale.ITALY);
    numFormat.setGroupingUsed( true );
    numFormat.setMaximumFractionDigits( 2 );
    numFormat.setMinimumFractionDigits( 0 );

    java.text.NumberFormat przFormat = java.text.NumberFormat.getInstance(java.util.Locale.ITALY);
    przFormat.setGroupingUsed( true );
    przFormat.setMaximumFractionDigits( 2 );
    przFormat.setMinimumFractionDigits( 2 );

    java.text.NumberFormat qtaFormat = java.text.NumberFormat.getInstance(java.util.Locale.ITALY);
    qtaFormat.setGroupingUsed( true );
    qtaFormat.setMaximumFractionDigits( 3 );
    qtaFormat.setMinimumFractionDigits( 3 );

    java.text.NumberFormat dimFormat = java.text.NumberFormat.getInstance(java.util.Locale.ITALY);
    dimFormat.setGroupingUsed( true );
    dimFormat.setMaximumFractionDigits( 3 );
    dimFormat.setMinimumFractionDigits( 0 );

    java.text.NumberFormat scoFormat = java.text.NumberFormat.getInstance(java.util.Locale.ITALY);
    scoFormat.setGroupingUsed( true );
    scoFormat.setMaximumFractionDigits( 5 );
    scoFormat.setMinimumFractionDigits( 0 );


    /*****************************************************
    *
    *   PARAMETRI cat_costanti
    *
    *****************************************************/

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
    


    String dwlingua_link = " <a href=\"javaScript:ATK_edit_label('seStesso','cdcampo')\"><img src=\"img/ico_flag.gif\" border=\"0\"></a>";


        // Head

    String logo_cliente        = cat_costanti.getCostvalue("logo_cliente");
    String logo_link           = cat_costanti.getCostvalue("logo_link");
    String ep_style            = cat_costanti.getCostvalue("style");
    String head_background_img = cat_costanti.getCostvalue("head_background_img");
    String head_bgcolor        = cat_costanti.getCostvalue("head_bgcolor");

    if (logo_cliente.equals("")) logo_cliente = "img/logo_epro.gif";
    if (cliente.equals("")) cliente = atk_sql.ATEIKON;
    if (ep_style.equals("")) ep_style = "ATEIKON";
    



    String par_tipo_head = "";     // pop_up
    String par_visu_foot = "S";


    int    righe_cata = 15;
    String tipoLista  = "L";  // L = Listino   C = Catalogo


    if(logo_link.equals("")){
        logo_link = "javaScript:;";
    }

    String slash = System.getProperty( "file.separator" );

    if (seStesso.indexOf("catalogo")>=0){
        par_visu_foot = "N";
    }


    try {
        
        righe_cata = Integer.parseInt(cat_costanti.getCostvalue("num_rec_catalogo"));

    }catch (Exception ex){
        righe_cata = 15;
    }



%>
