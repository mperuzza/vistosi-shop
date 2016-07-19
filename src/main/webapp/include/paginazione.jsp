<%


                if (request.getParameter("nextPageRic")!=null)    nextPageRic   = request.getParameter("nextPageRic");
                if (request.getParameter("current_page")!=null)   current_page  = request.getParameter("current_page");


                try {
                    li_current_page = Integer.parseInt(current_page);
                }catch(Exception ex_paginazione){
                    li_current_page = 0;
                }

                li_numpage = li_current_page;

                int li_recposi = 0;

                if ((count_rec%max_rig)==0){
                    li_totpage = count_rec/max_rig;
                }else {
                    li_totpage = (count_rec/max_rig)+1;
                }

                if (nextPageRic.equals("next")){

                    if (li_numpage < li_totpage){
                                li_numpage = li_numpage + 1;
                        li_recposi = (li_numpage -1) * max_rig;
                    }else {
                        li_recposi = (li_totpage - 1) * max_rig;
                    }

                }else if (nextPageRic.equals("prev") ) {

                    if (li_numpage > 1){
                                li_numpage = li_numpage - 1;
                        li_recposi = (li_numpage - 1) * max_rig;
                    }else {
                        li_recposi = 0;

                        li_numpage = 1;
                    }

                }else {
                    if (nextPageRic.equals("")) {
                    } else {
                        
                        try {
                            li_numpage = Integer.parseInt(nextPageRic);
                        }catch(Exception ex_paginazione){
                            li_numpage = li_current_page;
                        }
                    }   
                    if (li_numpage <= 0) li_numpage=1;
                    li_recposi = (li_numpage - 1) * max_rig;
                }


                // devo stabilire da dove cominciare a contare
                // per creare i numeri di pagina
    
                int tot_blocchi_pag    = 0;
                int pos_pag_nel_blocco = 0;
                int pos_start_blocco   = 1;
                int pos_stop_blocco    = num_pag_visu;
                int mod_num_pag_visu   = num_pag_visu % 2;
    
    
                if (li_totpage > num_pag_visu){
                
                    if ((li_totpage % num_pag_visu) == 0){
                        
                        tot_blocchi_pag = li_totpage / num_pag_visu;
                        
                    }else {
    
                        tot_blocchi_pag = (li_totpage / num_pag_visu) + 1;
                    }
    
                    pos_pag_nel_blocco = li_numpage % num_pag_visu;
    
    
                    pos_start_blocco = (li_numpage / num_pag_visu) * num_pag_visu;
    
                    if (pos_pag_nel_blocco > (num_pag_visu/2)){
                        
                        pos_start_blocco += (num_pag_visu/2);
    
                    }else if ((li_numpage % num_pag_visu) == 0){
    
                        pos_start_blocco -= ((num_pag_visu/2)+mod_num_pag_visu);
                    }
    
                    pos_stop_blocco = pos_start_blocco + num_pag_visu;
    
                    if (pos_start_blocco == 0) pos_start_blocco = 1;
    
                    
                } // FINE if (li_totpage > num_pag_visu){


%>
