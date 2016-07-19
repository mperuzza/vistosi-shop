


    function atk_pagina(nextPageRic, current_page){

        document.body.scrollTop = 0;
        document.body.scrollLeft = 0;

        elDivAttesa = document.getElementById("divAttesa");
        if (elDivAttesa != null){
            elDivAttesa.style.visibility ='visible';
        }
        
        el_new_page = document.getElementById('new_page');


        if (el_new_page){
            if (el_new_page.value != '') return;
        }


        var form_ric;

        if (document.form_ric){
            form_ric = document.form_ric;
        }else {
            form_ric = document.form1;
        }

        form_ric.azione.value       = 'report';   
        form_ric.nextPageRic.value  = nextPageRic;   
        form_ric.current_page.value = current_page;  
        form_ric.submit();

    }

    function atk_new_page(current_page, nr_inc_paginazione){
    
        document.body.scrollTop = 0;
        document.body.scrollLeft = 0;

        elDivAttesa = document.getElementById("divAttesa");
        if (elDivAttesa != null){
            elDivAttesa.style.visibility ='visible';
        }

        el = document.getElementById('new_page'+nr_inc_paginazione);
        
        var form_ric;

        if (document.form_ric){
            form_ric = document.form_ric;
        }else {
            form_ric = document.form1;
        }

        form_ric.azione.value = 'report';   
        form_ric.nextPageRic.value = el.value;   
        form_ric.current_page.value = current_page;   
        form_ric.submit();
    }


