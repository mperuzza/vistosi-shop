<%


    Atk_sql atk_sql = new Atk_sql();

    String seStesso = "";
    String funzione = "";

    long inc_accecci_time_ini = System.currentTimeMillis( );


    String azione   = "";
    String message  = "";
    String order_by = "";
    int    tot_rec  = 0;
    int    ind      = 0;
    int    idx_f    = 0;



    ResultSet rs     = null;
    ResultSet rs_box = null;

    int  gg = 0;
    int  mm = 0;
    int  sa = 0;
    int  hh = 0;
    int  mi = 0;


    seStesso = request.getRequestURI();
    seStesso = seStesso.replace('\\', '/');

    if (seStesso.lastIndexOf("/") >=0){
        seStesso = seStesso.substring( (seStesso.lastIndexOf("/")+1) );
    }

         if (seStesso.indexOf("epSelezione") >= 0) funzione = "SELEZIONE";
    else if (seStesso.indexOf("epConfig"   ) >= 0) funzione = "ADMIN";
    else if (seStesso.indexOf("epAdmin"    ) >= 0) funzione = "ADMIN";
    else if (seStesso.indexOf("catalogo_"  ) >= 0) funzione = "CATALOGO";
    else if (seStesso.indexOf("epRep"      ) >= 0) funzione = "REPORT";

    if (seStesso.equals("catalogo_carrello.jsp" )) funzione = "CARRELLO";
    if (seStesso.equals("catalogo_dest_merce.jsp" )) funzione = "CARRELLO";
    if (seStesso.equals("catalogo_ordine_fine.jsp" )) funzione = "CARRELLO";


%>
