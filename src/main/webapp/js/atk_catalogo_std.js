

    function atk_catalogo(){

        //self.location = 'catalogo_index.jsp';
        self.location = 'catalogo_index.jsp';//JMEL
    }

    function atk_carr(){

        //self.location = 'catalogo_index.jsp?azione=carrello';
        self.location = 'catalogo_index.jsp?azione=carrello';//JMEL
    }

    //Inizio -- avendramin20080124
    function atk_ord_clie(){

        self.location = 'catalogo_index.jsp?azione=ordini';
    }
    //Fine -- avendramin20080124

    function atk_scheda_cliente(tkclie){

        var ls_url = 'catalogo_controllo_cliente_dettaglio.jsp?tkclie='+tkclie;

        atk_winSel(ls_url);
    }

