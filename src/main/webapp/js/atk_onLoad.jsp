        atk_hide_wait();

        <%
        
        if (!message.equals("")){

            %>alert('<%= html.js(message) %>');<%

            message = "";
        }

        %>

        var el_f;

        try {
            el_ = document.getElementById('el~0');    
    
            if (el_){
                el_.focus();
            }else{
                el_ = document.form1.bt_ok;
                el_.focus();
            }
        }catch(ex_f){
        
        }

