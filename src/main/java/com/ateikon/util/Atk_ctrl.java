package com.ateikon.util;


public class Atk_ctrl extends Object{

    public Atk_ctrl() {

        super();
    }


    public String decode (String password) throws Exception{
        
        String key = "atkciao";

        long finalKey = 0;
        for (int i=0; i<key.length(); i++)
        {
                long tempKey = key.charAt(i);
                tempKey *= 128;
                finalKey += tempKey;
        }

        java.util.Random generator = new java.util.Random(finalKey);
        String returnString = "";
        for (int i=0; i<password.length(); i++)
        {
                int temp = (int)password.charAt(i);
                temp -= generator.nextInt(95);
                if (temp < 36)
                {
                        temp+= 95;
                }
                else if (temp > 126)
                {
                        temp -= 95;
                }
                returnString += (char)temp;
        }

        return returnString;
    }

    public String encode (String password) throws Exception{
        

        String key = "atkciao";

        long finalKey = 0;
        for (int i=0; i<key.length(); i++)
        {
                long tempKey = key.charAt(i);
                tempKey *= 128;
                finalKey += tempKey;
        }

        java.util.Random generator = new java.util.Random(finalKey);
        String returnString = "";

        for (int i=0; i<password.length(); i++)
        {
                int temp = (int)password.charAt(i);
                temp += generator.nextInt(95);
                if (temp > 126)
                {
                        temp -= 95;
                }
                returnString += (char)temp;
        }

//        log.debug("decoded: " + returnString);
        return returnString;
    }


    public String of_ggmmsa(String valore_campo, String label) throws Exception{
        return of_ggmmsa(valore_campo, label, "DA") ;
    }


    public java.sql.Timestamp ggmmsa = null;
    public String of_ggmmsa(String valore_campo, String label, String tipo) throws Exception{

        this.rc = "0";
        this.message = "";

        String str_app = "";

        int gg = 0;
        int mm = 0;
        int sa = 0;
        int hh = 0;
        int mi = 0;


        String str_gg = "";
        String str_mm = "";
        String str_sa = "";

        str_app = valore_campo.trim();
        str_app = str_app.replace('.','/');
        str_app = str_app.replace('-','/');
        str_app = str_app.replace('\\','/');


        ggmmsa = null;
        if (valore_campo.equals("")){

            rc = "1";

            ggmmsa = new java.sql.Timestamp(System.currentTimeMillis( ));

            ggmmsa.setHours(0);
            ggmmsa.setMinutes(0);
            ggmmsa.setSeconds(0);
            ggmmsa.setNanos(0);




            gg = ggmmsa.getDate( )    ;
            mm = ggmmsa.getMonth()+1  ;
            sa = ggmmsa.getYear()+1900;

            valore_campo =       ((gg<10)?"0"+gg:""+gg)
                         + "/" + ((mm<10)?"0"+mm:""+mm)
                         + "/" + ((sa<10)?"0"+sa:""+sa);

            return valore_campo;

        }else {

            if (str_app.indexOf("/")<0){
                
                if (str_app.length() == 8){
                
                    str_app = str_app.substring(0,2)
                            + "/"
                            + str_app.substring(2,4)
                            + "/"
                            + str_app.substring(4,8)
                            ;
                }
                if (str_app.length() == 6){
                
                    str_app = str_app.substring(0,2)
                            + "/"
                            + str_app.substring(2,4)
                            + "/20"
                            + str_app.substring(4,6)
                            ;
                }

            }

        
        
            str_gg = str_app;
            if (str_gg.indexOf("/")>0){

                str_gg = str_gg.substring(0, str_gg.indexOf("/"));

                if (str_gg.length()+1<str_app.length()){

                    str_mm = str_app;
                    if (str_mm.lastIndexOf("/")>0 && str_mm.lastIndexOf("/") > str_mm.indexOf("/")){

                        str_mm = str_mm.substring(str_mm.indexOf("/")+1, str_mm.lastIndexOf("/"));

                    }else {
                        message += "\n"+label+" non è un campo data (gg/mm/aaaa).";
                        str_mm = "";
                        rc = "-1";
                    }

                    if (str_mm.lastIndexOf("/")+1<str_app.length()){

                        str_sa = str_app;

                        str_sa = str_sa.substring(str_sa.lastIndexOf("/")+1, str_sa.length());

                        try {
                            gg = Integer.parseInt(str_gg);
                            mm = Integer.parseInt(str_mm);
                            sa = Integer.parseInt(str_sa);

                            valore_campo =       ((gg<10)?"0"+gg:""+gg)
                                         + "/" + ((mm<10)?"0"+mm:""+mm)
                                         + "/" + ((sa<10)?"0"+sa:""+sa);


                                               // Timestamp(int year,int month,int date,int hour,int minute,int second,int nano)

                            if (tipo.equals("DA")){
                                ggmmsa = new java.sql.Timestamp(sa-1900, mm-1, gg, 0,0,0,0);
                            }else {
                                ggmmsa = new java.sql.Timestamp(sa-1900, mm-1, gg, 23,59,59,0);
                            }


                        }catch (Exception e){
                            message += "\n"+label+" non è un campo data (gg/mm/aaaa).";
                            str_gg = "";
                            rc = "-1";
                        }

                    }else {
                        message += "\n"+label+" non è un campo data (gg/mm/aaaa).";
                        str_sa = "";
                        rc = "-1";
                    }

                }else {
                    message += "\n"+label+" non è un campo data (gg/mm/aaaa).";
                    str_mm = "";
                    rc = "-1";
                }

            }else {
                message += "\n"+label+" non è un campo data (gg/mm/aaaa).";
                str_gg = "";
                rc = "-1";
            }


        } // fine if valore_campo == ""


        return valore_campo;

    }


    public String of_ggmmsa_hhmi(String valore_campo, String label) throws Exception{
        return of_ggmmsa_hhmi(valore_campo, label, "DA") ;
    }

    public String is_ggmmsa = "";
    public String is_hhmi   = "";
    public java.sql.Timestamp ggmmsa_hhmi = null;

    public String of_ggmmsa_hhmi(String valore_campo, String label, String tipo) throws Exception{

        this.rc = "0";
        this.message = "";

        String str_app = "";

        int gg = 0;
        int mm = 0;
        int sa = 0;
        int hh = 0;
        int mi = 0;


        String str_gg = "";
        String str_mm = "";
        String str_sa = "";
        String str_hh = "";
        String str_mi = "";

        str_app = valore_campo.trim();
        str_app = str_app.replace('.','/');
        str_app = str_app.replace('-','/');
        str_app = str_app.replace('\\','/');

        str_app = str_app.replace(' ','/');
        str_app = str_app.replace(':','/');

        ggmmsa_hhmi = null;

        is_ggmmsa = "";
        is_hhmi   = "";


        if (valore_campo.equals("")){

            rc = "1";

            ggmmsa = new java.sql.Timestamp(System.currentTimeMillis( ));

            gg = ggmmsa.getDate( )    ;
            mm = ggmmsa.getMonth()+1  ;
            sa = ggmmsa.getYear()+1900;
            hh = ggmmsa.getHours( )   ;
            mi = ggmmsa.getMinutes( ) ;

            valore_campo =       ((gg<10)?"0"+gg:""+gg)
                         + "/" + ((mm<10)?"0"+mm:""+mm)
                         + "/" + ((sa<10)?"0"+sa:""+sa)
                         + " " + ((hh<10)?"0"+hh:""+hh)
                         + ":" + ((mi<10)?"0"+mi:""+mi)
                         ;

            is_ggmmsa    =       ((gg<10)?"0"+gg:""+gg)
                         + "/" + ((mm<10)?"0"+mm:""+mm)
                         + "/" + ((sa<10)?"0"+sa:""+sa)
                         ;
            is_hhmi      =       ((hh<10)?"0"+hh:""+hh)
                         + ":" + ((mi<10)?"0"+mi:""+mi)
                         ;

            return valore_campo;

        }else {

            String ls_dta = "";

            String ls_hh = "00";
            String ls_mi = "00";



            java.util.StringTokenizer stringTokenizer = new java.util.StringTokenizer( str_app, "/");
            String[] arr_ = new String[stringTokenizer.countTokens( )];

	        int numToken = 0;
	        while (stringTokenizer.hasMoreTokens()) {

	        	arr_[numToken] = stringTokenizer.nextToken();
	        	numToken += 1;
	        }


            if (arr_.length == 2){
                
                ls_dta = of_ggmmsa(arr_[0], label, tipo);

                ls_hh = "00";
                ls_mi = "00";

                if (arr_[1].length() == 4){
                 
                    ls_hh =  arr_[1].substring(0,2);
                    ls_mi =  arr_[1].substring(2,4);
                    
                }else if (arr_[1].length() == 3){
                    
                    ls_hh =  arr_[1].substring(0,1);
                    ls_mi =  arr_[1].substring(1,3);

                }else if (arr_[1].length() == 2){
                    
                    ls_hh =  arr_[1];
                }

                try{

                    hh = Integer.parseInt(ls_hh);
                    mi = Integer.parseInt(ls_mi);

                    ls_hh = ((hh<10)?"0"+hh:""+hh);
                    ls_mi = ((mi<10)?"0"+mi:""+mi);

                }catch (Exception e){
                    
                    ls_hh = "00";
                    ls_mi = "00";
                    
                }

                ls_dta = ls_dta + "/" + ls_hh + "/" + ls_mi;

                stringTokenizer = new java.util.StringTokenizer( ls_dta, "/");

                arr_ = new String[stringTokenizer.countTokens( )];

    	        numToken = 0;
    	        while (stringTokenizer.hasMoreTokens()) {
    
    	        	arr_[numToken] = stringTokenizer.nextToken();
    	        	numToken += 1;
    	        }


            }    
                

            if (arr_.length == 3 && arr_[0].length() >= 6){
                  
                ls_dta = of_ggmmsa(arr_[0], label, tipo);
                
                ls_hh = "00";
                ls_mi = "00";

                try{

                    hh = Integer.parseInt(arr_[1]);
                    mi = Integer.parseInt(arr_[2]);

                    ls_hh = ((hh<10)?"0"+hh:""+hh);
                    ls_mi = ((mi<10)?"0"+mi:""+mi);

                }catch (Exception e){
                    
                    ls_hh = "00";
                    ls_mi = "00";
                    
                }

                ls_dta = ls_dta + "/" + ls_hh + "/" + ls_mi;

                stringTokenizer = new java.util.StringTokenizer( ls_dta, "/");

                arr_ = new String[stringTokenizer.countTokens( )];

    	        numToken = 0;
    	        while (stringTokenizer.hasMoreTokens()) {
    
    	        	arr_[numToken] = stringTokenizer.nextToken();
    	        	numToken += 1;
    	        }
            }
            
            
            if (arr_.length == 3){

                    try {
                        gg = Integer.parseInt(arr_[0]);
                        mm = Integer.parseInt(arr_[1]);
                        sa = Integer.parseInt(arr_[2]);
                        if (tipo.equals("DA")){
                          hh = 0;
                          mi = 0;
                        }else {
                          hh = 23;
                          mi = 59;
                        }

                        valore_campo =       ((gg<10)?"0"+gg:""+gg)
                                     + "/" + ((mm<10)?"0"+mm:""+mm)
                                     + "/" + ((sa<10)?"0"+sa:""+sa)
                                     + " " + ((hh<10)?"0"+hh:""+hh)
                                     + ":" + ((mi<10)?"0"+mi:""+mi)
                                     ;

                        is_ggmmsa    =       ((gg<10)?"0"+gg:""+gg)
                                     + "/" + ((mm<10)?"0"+mm:""+mm)
                                     + "/" + ((sa<10)?"0"+sa:""+sa)
                                     ;
                        is_hhmi      =       ((hh<10)?"0"+hh:""+hh)
                                     + ":" + ((mi<10)?"0"+mi:""+mi)
                                     ;


                         ggmmsa_hhmi = new java.sql.Timestamp(sa-1900, mm-1, gg, hh,mi,0,0);


                    }catch (Exception e){
                        message += "\n"+label+" non è un campo data (gg/mm/aaaa hh:mi).";
                        str_gg = "";
                        rc = "-1";
                    }


            }else if (arr_.length == 5){

                    try {
                        gg = Integer.parseInt(arr_[0]);
                        mm = Integer.parseInt(arr_[1]);
                        sa = Integer.parseInt(arr_[2]);
                        hh = Integer.parseInt(arr_[3]);
                        mi = Integer.parseInt(arr_[4]);

                        valore_campo =       ((gg<10)?"0"+gg:""+gg)
                                     + "/" + ((mm<10)?"0"+mm:""+mm)
                                     + "/" + ((sa<10)?"0"+sa:""+sa)
                                     + " " + ((hh<10)?"0"+hh:""+hh)
                                     + ":" + ((mi<10)?"0"+mi:""+mi)
                                     ;

                        is_ggmmsa    =       ((gg<10)?"0"+gg:""+gg)
                                     + "/" + ((mm<10)?"0"+mm:""+mm)
                                     + "/" + ((sa<10)?"0"+sa:""+sa)
                                     ;
                        is_hhmi      =       ((hh<10)?"0"+hh:""+hh)
                                     + ":" + ((mi<10)?"0"+mi:""+mi)
                                     ;

                         if (hh == 24) hh = 0;

                         ggmmsa_hhmi = new java.sql.Timestamp(sa-1900, mm-1, gg, hh,mi,0,0);


                    }catch (Exception e){
                        message += "\n"+label+" non è un campo data (gg/mm/aaaa hh:mi).";
                        str_gg = "";
                        rc = "-1";
                    }


            }else {
                message += "\n"+label+" non è un campo data (gg/mm/aaaa hh:mi). ";
                rc = "-1";
            }



        } // fine if valore_campo == ""


        return valore_campo;

    }






    /***
        
        verifica il periodo     
    */


    public int periodo_samm = 0;

    public String of_periodo_mmsa(String valore_campo, String label) throws Exception{

        this.rc = "0";
        this.message = "";

        String str_app = "";

        int gg = 0;
        int mm = 0;
        int sa = 0;
        int hh = 0;
        int mi = 0;


        String str_gg = "";
        String str_mm = "";
        String str_sa = "";
        String str_hh = "";
        String str_mi = "";

        str_app = valore_campo.trim();
        str_app = str_app.replace('.','/');
        str_app = str_app.replace('-','/');
        str_app = str_app.replace('\\','/');

        str_app = str_app.replace(' ','/');
        str_app = str_app.replace(':','/');

        ggmmsa_hhmi = null;

        if (valore_campo.equals("")){

            rc = "1";

            ggmmsa = new java.sql.Timestamp(System.currentTimeMillis( ));

            gg = ggmmsa.getDate( )    ;
            mm = ggmmsa.getMonth()+1  ;
            sa = ggmmsa.getYear()+1900;
            hh = ggmmsa.getHours( )   ;
            mi = ggmmsa.getMinutes( ) ;

            valore_campo =       ((mm<10)?"0"+mm:""+mm)
                         + "/" + ((sa<10)?"0"+sa:""+sa)
                         ;

            periodo_samm = (sa*100) + mm;

            return valore_campo;

        }else {

            if (str_app.length()==7){
                
            }else if (str_app.length()==6){
                str_app = str_app.substring(0,2)+"/"+str_app.substring(2,6);
            }else if (str_app.length()==5){
                str_app = str_app.substring(0,1)+"/"+str_app.substring(1,5);
            }else if (str_app.length()==4){
                str_app = "01/"+str_app;
            }else {
                message += "\n"+label+" non è un campo Periodo (mm/aaaa). ";
                return valore_campo;
            }

            java.util.StringTokenizer stringTokenizer = new java.util.StringTokenizer( str_app, "/");
            String[] arr_ = new String[stringTokenizer.countTokens( )];

	        int numToken = 0;
	        while (stringTokenizer.hasMoreTokens()) {

	        	arr_[numToken] = stringTokenizer.nextToken();
	        	numToken += 1;
	        }

            if (arr_.length == 2){

                    try {
                        mm = Integer.parseInt(arr_[0]);
                        sa = Integer.parseInt(arr_[1]);


                        valore_campo =       ((mm<10)?"0"+mm:""+mm)
                                     + "/" + ((sa<10)?"0"+sa:""+sa)
                                     ;


                        periodo_samm = (sa*100) + mm;



                    }catch (Exception e){
                        message += "\n"+label+" non è un campo Periodo (mm/aaaa).";
                        str_gg = "";
                        rc = "-1";
                    }


            }else {
                message += "\n"+label+" non è un campo Periodo (mm/aaaa). ";
            }



        } // fine if valore_campo == ""


        return valore_campo;

    }












    public String getDate(java.sql.Timestamp adt_) throws Exception{
        
        this.rc = "0";
        this.message = "";

        if (adt_ == null ) return  "";


        int sa = adt_.getYear( ) + 1900;
        int mm = adt_.getMonth( ) + 1;
        int gg = adt_.getDate( );

        String ls =       ((gg<10)?"0"+gg:""+gg)
                  + "/" + ((mm<10)?"0"+mm:""+mm)
                  + "/" + sa
                  ;

        return  ls;
    }

    public String getDatetime(java.sql.Timestamp adt_) throws Exception{
        
        this.rc = "0";
        this.message = "";

        if (adt_ == null ) return  "";


        int sa = adt_.getYear( ) + 1900;
        int mm = adt_.getMonth( ) + 1;
        int gg = adt_.getDate( );

        int hh = adt_.getHours( );
        int mi = adt_.getMinutes( );

        String ls =       ((gg<10)?"0"+gg:""+gg)
                  + "/" + ((mm<10)?"0"+mm:""+mm)
                  + "/" + sa
                  + " " + ((hh<10)?"0"+hh:""+hh)
                  + ":" + ((mi<10)?"0"+mi:""+mi)
                  ;

        return  ls;
    }


    public double of_strToDouble(String as_, String  label) throws Exception{

        double ld_ = 0;

        this.rc = "0";
        this.message = "";

        try {
            
            // IN LWM la virgola è il punto
            as_ = StringUtils.replace(as_,".", "");
            as_ = as_.replace(',', '.');
            

            ld_ = Double.parseDouble(as_);


        }catch(Exception ex_){
            this.message += "\n"+label+" non è numerico (#.###,##).";
            this.rc = "-1";
            ld_ = 0;
        }

        return ld_;

    }

    public int of_strToInt(String as_, String  label) throws Exception{

        int li_ = 0;

        this.rc = "0";
        this.message = "";

        try {

            as_ = StringUtils.replace(as_,".", "");
            as_ = as_.replace(',', '.');

            li_ = Integer.parseInt(as_);


        }catch(Exception ex_){
            this.message += "\n"+label+" non è numerico (#.###).";
            this.rc = "-1";
            li_ = 0;
        }

        return li_;

    }

    public long of_strToLong(String as_, String  label) throws Exception{

        long ll_ = 0;

        this.rc = "0";
        this.message = "";

        try {

            as_ = StringUtils.replace(as_,".", "");
            as_ = as_.replace(',', '.');

            ll_ = Long.parseLong(as_);


        }catch(Exception ex_){
            this.message += "\n"+label+" non è numerico (#.###).";
            this.rc = "-1";
            ll_ = 0;
        }

        return ll_;

    }


    public String getRequest_param(String param_name, javax.servlet.http.HttpServletRequest request) throws Exception{
        
        String value = "";

        javax.servlet.http.HttpSession session = request.getSession(true);

        if (request.getParameter(param_name)!=null){
            value  = request.getParameter(param_name);
        }else {
            if (session.getAttribute("par_"+param_name)!=null){
                value = (String)session.getAttribute("par_"+param_name);
            }
        }

        if (value.equals("")){
            
            session.removeAttribute("par_"+param_name);
        }else {
            session.setAttribute("par_"+param_name, value);
        }

        return value;
    }


    public String setRequest_param(String param_name, String value, javax.servlet.http.HttpServletRequest request) throws Exception{
        

        javax.servlet.http.HttpSession session = request.getSession(true);


        if (value.equals("")){
            
            session.removeAttribute("par_"+param_name);
        }else {
            session.setAttribute("par_"+param_name, value);
        }



        return value;
    }



    public void proc_output(Process proc ) throws Exception {

        java.io.BufferedReader in  = new java.io.BufferedReader(new java.io.InputStreamReader(proc.getInputStream()),5000);
        String a = new String("");
 
        while (( a = in.readLine()) != null){
            System.out.println(""+a);
        }
        in.close();

    }






    public String getRequestQuery( javax.servlet.http.HttpServletRequest request
                               , String[] _param_esclusi
                                        )throws Exception {
                                            
    

        String ls_query = "";

        // _param_esclusi è un arrai che contiene i parametri da escludere


        return ls_query;
                                        
    }


    public String getRequestHidden( javax.servlet.http.HttpServletRequest request
                                  , String[] _param_esclusi
                                        )throws Exception {
                                            
    
        rc = "0";
        message = "";

        String ls_hidden = "";

        // _param_esclusi è un arrai che contiene i parametri da escludere


        java.util.Vector vec_esclusi = new java.util.Vector();

        for(int i=0; _param_esclusi != null && i<_param_esclusi.length; i++){
            vec_esclusi.add(_param_esclusi[i]);
        }


        java.util.Enumeration lobj_pname = request.getParameterNames();

        java.util.Vector   lvec_pname  = new java.util.Vector();
        java.util.Vector   lvec_pvalue = new java.util.Vector();
        String[] larr_pvalue = null;
        String   ls_pname    = "";

        String   str_app = "";

        // Ripasso tutti i parametri

        String ls_and = "";

        while ( lobj_pname!=null && lobj_pname.hasMoreElements() ) {
            
            larr_pvalue = null;
            
            ls_pname = (String) lobj_pname.nextElement();

            if (vec_esclusi.contains(ls_pname)){
                
                continue;
            }
    
            // questi sono i parametri aggiuntivi
            
            if (request.getParameterValues(ls_pname)!=null) larr_pvalue = request.getParameterValues(ls_pname);
    
            for (int i=0; larr_pvalue!=null && i<larr_pvalue.length; i++){
                
                str_app = StringParsToHTML.escapeHTMLTextbox(larr_pvalue[i]);
            
                ls_hidden += "<input type=\"hidden\" name=\""+ls_pname+"\" value=\""+str_app+"\" />";
                
                ls_and = "&";

            }
    
        }   // FINE while request






        return ls_hidden;
                                        
    }


    /***


    */

    public String getBase_URL ( javax.servlet.http.HttpServletRequest request ) throws Exception {
        
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



    /***


    */

    public String ctrl_email ( String email) throws Exception {
        
        message = "";
        rc = "0";

        email = email.trim().toLowerCase();

        // --- Verifico se ha La @ (e non deve essere la prima lettera)

        int li_et = email.indexOf("@"); 

        if (li_et <= 0){
            
            rc = "-1";
            return "";
        }

        // verifico che non ci siano spazi (carattere non permesso)

        int li_sp = email.indexOf(" "); 

        if (li_sp >= 0){
            
            rc = "-2";
            return "";
        }


        // --- Controllo che ci sia un dominio dopo la chicciola:
        //     ci deve essere il punto e deve essere posizionato 
        //     dopo la @ e tra il punto e pa @ ci deve essere 
        //     almeno una lettera


        int li_punto = email.lastIndexOf("."); 
        
        if (li_punto > (li_et+1)){
            
        }else {
            rc = "-3";
            return "";
        }


        return email;

    }
    

    public java.sql.Timestamp relativeDate(java.sql.Timestamp adt_, int ai_gg) throws Exception{
        
        message = "";
        rc = "0";
        java.util.Calendar gc = new java.util.GregorianCalendar();

    	int sa = adt_.getYear( ) + 1900;
    	int mm = adt_.getMonth( ) + 1;
    	int gg = adt_.getDate( );

    	gc.set(sa, mm-1, gg);

        gc.add(java.util.Calendar.DATE, ai_gg);

        gg         = gc.get(gc.DAY_OF_MONTH);
        mm         = gc.get(gc.MONTH)+1;
        sa         = gc.get(gc.YEAR);

        adt_.setYear (sa-1900);
        adt_.setMonth(mm-1);
        adt_.setDate (gg);


        return adt_;

    }




    /***


    */

    public String descrSconti (double[] arr_scont  ) throws Exception {
        
        String ls_sconto = "";
        String ls_piu = "";

        java.text.NumberFormat numFormat = java.text.NumberFormat.getInstance(java.util.Locale.ITALY);
        numFormat.setGroupingUsed( false );
        numFormat.setMaximumFractionDigits( 2 );
        numFormat.setMinimumFractionDigits( 0 );


        for(int i=0; arr_scont!=null && i<arr_scont.length; i++){
            if (arr_scont[i] > 0) {
                ls_sconto += ls_piu + numFormat.format(arr_scont[i]);
                ls_piu = " + ";
            }
        }


        return ls_sconto ;

    }


    public String message = "";
    public String rc = "0";

}
