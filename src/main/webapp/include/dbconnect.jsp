<%

    // *** Connessione di default al DB
    //     questo Metodo setta il profilo

    atk_sql.dbConnection();

    // questo profilo viene reimpostato in sicurezza.jsp
    // imposto il default per gestire il catalogo pubblico

    atk_sql.profil = "PUBLIC_USER";
    atk_sql.cdazie = "01";
    atk_sql.cddipa = "0000";

    if (session.getAttribute("user_name")!=null) atk_sql.profil = (String ) session.getAttribute("user_name");
    if (session.getAttribute("cdazie")!=null)    atk_sql.cdazie = (String ) session.getAttribute("cdazie");
    if (session.getAttribute("cddipa")!=null)    atk_sql.cddipa = (String ) session.getAttribute("cddipa");

%>
