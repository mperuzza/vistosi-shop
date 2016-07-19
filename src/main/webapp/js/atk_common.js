// FUNZIONI Standard

var agt=navigator.userAgent.toLowerCase();
var is_gecko = (agt.indexOf('gecko') != -1); 
var is_ie     = ((agt.indexOf("msie") != -1) && (agt.indexOf("opera") == -1));


var tr_css = '';
function tr_over(el_tr){

  tr_css = el_tr.className;
  el_tr.className = 'css0'

}
function tr_out(el_tr){
    el_tr.className = tr_css;
}


var old_tr;
var old_tr_css;
function tr_click(el_tr){
  
    if (tr_css != 'css0'){

        if (old_tr){
            old_tr.className = old_tr_css;
        }

        old_tr_css = tr_css;
        old_tr = el_tr;

        tr_css = 'css0';
        el_tr.className = 'css0';
    }

}



function atk_hide_wait(){
  try{
    elDivAttesa = document.getElementById("divAttesa");
    if (elDivAttesa != null){
      elDivAttesa.style.visibility ='hidden';
    }
  }catch(ex){
  }
  try{
    elDivAttesa = parent.document.getElementById("divAttesa");
    if (elDivAttesa != null){
      elDivAttesa.style.visibility ='hidden';
    }
  }catch(ex){
  }
}

function atk_wait(){

  if (parent.document){
    elDivAttesa = parent.document.getElementById("divAttesa");
  }else {
    elDivAttesa = document.getElementById("divAttesa");
  }
  if (elDivAttesa != null){
    elDivAttesa.style.visibility = 'visible';
  }
}

function atk_setUp_calendar(input_name){


  if ( arguments[1] ){
    // mi hanno passato anche il form
    el_ = eval('document.'+arguments[1]+'.'+input_name);

    if (el_){
            

        if (el_.type == 'text'){
            ls_id = el_.id;
    
            Calendar.setup(
                {
                    inputField  : ls_id,        // id of the input field
                    ifFormat    : "%d/%m/%Y",   // the date format
                    button      : "cal_"+ls_id  // id of the button
                    //date        : today
                }
            );
        }
    }


  }



  if (document.form1){
    el_ = eval('document.form1.'+input_name);
    if (el_){
        
        if (el_.type == 'text'){
            ls_id = el_.id;
    
            Calendar.setup(
                {
                    inputField  : ls_id,        // id of the input field
                    ifFormat    : "%d/%m/%Y",   // the date format
                    button      : "cal_"+ls_id  // id of the button
                    //date        : today
                }
            );
        }
    }
  } // FINE if form1
    
  if (document.form_ric){
    el_ = eval('document.form_ric.'+input_name);
    if (el_){

        if (el_.type == 'text'){
            ls_id = el_.id;
    
            Calendar.setup(
                {
                    inputField  : ls_id,        // id of the input field
                    ifFormat    : "%d/%m/%Y",   // the date format
                    button      : "cal_"+ls_id  // id of the button
                    //date        : today
                }
            );
        }
    }
  } // FINE if form1

}


function  atk_cls(){

        for (var i=0 ; i < arguments.length; i++) {
            var ls_ = arguments[i] + '';
            atk_cls_el(ls_);
        }

}


function  atk_cls_el(ls_){
    el = document.getElementById(ls_);
    if (el){
        el.value = '';
    }else {
        el = eval('document.form1.'+ls_);
        if (el){
            el.value = '';
        }
    }
}






    var atkWinSel;

    function atk_winSel(theUrl) {

	    winwidth = 900;
        winheight = 500;
        winleft = (screen.availWidth - winwidth)/2;
        wintop  = (screen.availHeight - winheight - 20 )/2;
        
        tmt_winOpen(theUrl,'atkWinSel','width='+ winwidth +',height=' + winheight +',menubar=no,left='+ winleft +',top='+wintop+',status=yes,scrollbars=yes,resizable=yes',0);
	}



	function tmt_winOpen(u,id,f,df){

        if (u.indexOf('\\')>=0){
            // imposto la regular expression
            var regexp = /\\/g
            u = u.replace(regexp, '\\\\');
        }

		if(eval(id)==null||eval(id+".closed")){
		    eval(id+"=window.open('"+u+"','"+id+"','"+f+"')");
		}else if(df){
		}else{
		    eval(id+"=window.open('"+u+"','"+id+"','"+f+"')");
		}
		try{
		    eval(id+".focus()");
		}catch(ex){
        }
	}




    function atk_dec(azione, el){

        var input_name = el.name;
        var el_form;

        if (document.form1){
            el_form = document.form1;
        }else if (document.form1){
            el_form = document.form1;
        }

        if (input_name == 'cdagen_m'){
            if (azione == 'decode'){
                url_ = 'epSelezione_agente.jsp?cdagen_m_dec='+el.value;
                if_ = document.getElementById('iframeSel');
                if_.src = url_;
            }else{
                url_ = 'epSelezione_agente.jsp';
                atk_winSel(url_);
            }
        } 

        if (input_name == 'cdclie_m'){
            if (azione == 'decode'){
                url_ = 'epSelezione_cliente.jsp?cdclie_m_dec='+el.value;
                if_ = document.getElementById('iframeSel');
                if_.src = url_;
            }else{
                url_ = 'epSelezione_cliente.jsp';
                atk_winSel(url_);
            }
        } 

        if (input_name == 'cdtipm_m'){
            if (azione == 'decode'){
                url_ = 'epSelezione_tipomapr.jsp?cdtipm_m_dec='+el.value;
                if_ = document.getElementById('iframeSel');
                if_.src = url_;
            }else{
                url_ = 'epSelezione_tipomapr.jsp';
                atk_winSel(url_);
            }
        } 

        if (input_name == 'cdartm'){
            
            var tkmaga_dep = '';

            if (el_form){


                if (el_form.tkmaga_dep) tkmaga_dep = el_form.tkmaga_dep.value;
            }

            if (azione == 'decode'){
                url_ = 'epSelezione_articolo.jsp?cdartm_dec='+el.value;

                if (tkmaga_dep != ''){
                    url_  = 'epSelezione_articolo_deposito.jsp?cdartm_dec='+el.value;
                    url_ += '&tkmaga_dep='+tkmaga_dep;
                }

                if_ = document.getElementById('iframeSel');
                if_.src = url_;
            }else{
                url_ = 'epSelezione_articolo.jsp';
                
                if (tkmaga_dep != ''){
                    url_  = 'epSelezione_articolo_deposito.jsp';
                    url_ += '?tkmaga_dep='+tkmaga_dep;
                }

                
                atk_winSel(url_);
            }
        } 


        if (input_name == 'cdacli'){
            
            var tkmaga_dep = '';

            if (el_form){


                if (el_form.tkmaga_dep) tkmaga_dep = el_form.tkmaga_dep.value;
            }

            if (azione == 'decode'){
                url_ = 'epSelezione_articolo.jsp?cdacli_dec='+el.value;

                if (tkmaga_dep != ''){
                    url_  = 'epSelezione_articolo_deposito.jsp?cdacli_dec='+el.value;
                    url_ += '&tkmaga_dep='+tkmaga_dep;
                }

                if_ = document.getElementById('iframeSel');
                if_.src = url_;
            }else{
                url_ = 'epSelezione_articolo.jsp';
                
                if (tkmaga_dep != ''){
                    url_  = 'epSelezione_articolo_deposito.jsp';
                    url_ += '?tkmaga_dep='+tkmaga_dep;
                }

                
                atk_winSel(url_);
            }
        } 


    }   // FINE atk_dec



