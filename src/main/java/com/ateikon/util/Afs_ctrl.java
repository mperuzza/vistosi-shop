
/******

    Sostituita con ../util

*********/



package com.ateikon.util;


public class Afs_ctrl extends Object{

    public Afs_ctrl() {

        super();
    }




    /**
     * questo metodo esegue il controllo del nome file in caso di inserimento
     * di un documento nella intranet
     *
     */

    public String filename( String filename ) {

        rc = "0";
        message = "";

        filename = filename.trim();
        filename = filename.replace(' ','_');
        filename = filename.replace('è','e');
        filename = filename.replace('é','e');
        filename = filename.replace('à','a');
        filename = filename.replace('ì','i');
        filename = filename.replace('ò','o');

        if (filename.indexOf("'")>=0) message += "Carattere \"'\" non ammesso;<br>";
        if (filename.indexOf("%")>=0) message += "Carattere \"%\" non ammesso;<br>";
        if (filename.indexOf("*")>=0) message += "Carattere \"*\" non ammesso;<br>";
        if (filename.indexOf("?")>=0) message += "Carattere \"?\" non ammesso;<br>";


        if (!message.equals("")) rc = "-1";

        return filename;
    }


    public String revisione(String revisione) throws Exception{

        int li_revisione = 0;

        rc = "0";
        message = "";


        try {

            li_revisione = Integer.parseInt(revisione);

        }catch (Exception e_num){
            message += "Revisione non numerica;<br>";
            li_revisione = -1;
        }

        if (li_revisione > 999)  message += "Non ci possono essere pi� di 999 revisioni;<br>";



        if (li_revisione < 0){
            revisione = "";
        }else  if (li_revisione < 10){
            revisione = "00"+li_revisione;
        }else  if (li_revisione < 100){
            revisione = "0"+li_revisione;
        }else {
            revisione = ""+li_revisione;
        }

        if (!message.equals("")) rc = "-1";


        return revisione;
    }


    public String of_ggmmsa(String valore_campo, String label) throws Exception{
        return of_ggmmsa(valore_campo, label, "DA") ;
    }


    public java.sql.Timestamp ggmmsa = null;
    public String of_ggmmsa(String valore_campo, String label, String tipo) throws Exception{

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

            gg = ggmmsa.getDate( )    ;
            mm = ggmmsa.getMonth()+1  ;
            sa = ggmmsa.getYear()+1900;
            hh = ggmmsa.getHours( )   ;
            mi = ggmmsa.getMinutes( ) ;

            valore_campo =       ((gg<10)?"0"+gg:""+gg)
                         + "/" + ((mm<10)?"0"+mm:""+mm)
                         + "/" + ((sa<10)?"0"+sa:""+sa);

            return valore_campo;

        }else {

            str_gg = str_app;

            if (str_gg.indexOf("/")>0){

                str_gg = str_gg.substring(0, str_gg.indexOf("/"));

                if (str_gg.length()+1<str_app.length()){

                    str_mm = str_app;
                    if (str_mm.lastIndexOf("/")>0 && str_mm.lastIndexOf("/") > str_mm.indexOf("/")){

                        str_mm = str_mm.substring(str_mm.indexOf("/")+1, str_mm.lastIndexOf("/"));

                    }else {
                        message += "<li> Campo \""+label+"\" non e&grave; un campo data (gg/mm/aaaa). </li>";
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
                            message += "<li> Campo \""+label+"\" non e&grave; un campo data (gg/mm/aaaa).</li>";
                            str_gg = "";
                            rc = "-1";
                        }

                    }else {
                        message += "<li> Campo \""+label+"\" non e&grave; un campo data (gg/mm/aaaa).</li>>";
                        str_sa = "";
                        rc = "-1";
                    }

                }else {
                    message += "<li> Campo \""+label+"\" non e&grave; un campo data (gg/mm/aaaa).</li>";
                    str_mm = "";
                    rc = "-1";
                }

            }else {
                message += "<li> Campo \""+label+"\" non e&grave; un campo data (gg/mm/aaaa).</li>";
                str_gg = "";
                rc = "-1";
            }


        } // fine if valore_campo == ""


        return valore_campo;

    }


    public String of_ggmmsa_hhmi(String valore_campo, String label) throws Exception{
        return of_ggmmsa_hhmi(valore_campo, label, "DA") ;
    }

    public java.sql.Timestamp ggmmsa_hhmi = null;
    public String of_ggmmsa_hhmi(String valore_campo, String label, String tipo) throws Exception{

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

            valore_campo =       ((gg<10)?"0"+gg:""+gg)
                         + "/" + ((mm<10)?"0"+mm:""+mm)
                         + "/" + ((sa<10)?"0"+sa:""+sa)
                         + " " + ((hh<10)?"0"+hh:""+hh)
                         + ":" + ((mi<10)?"0"+mi:""+mi)
                         ;

            return valore_campo;

        }else {


            java.util.StringTokenizer stringTokenizer = new java.util.StringTokenizer( str_app, "/");
            String[] arr_ = new String[stringTokenizer.countTokens( )];

	        int numToken = 0;
	        while (stringTokenizer.hasMoreTokens()) {

	        	arr_[numToken] = stringTokenizer.nextToken();
	        	numToken += 1;
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



                         ggmmsa_hhmi = new java.sql.Timestamp(sa-1900, mm-1, gg, hh,mi,0,0);


                    }catch (Exception e){
                        message += "<li> Campo \""+label+"\" non e&grave; un campo data (gg/mm/aaaa hh:mi).</li>";
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


                         if (hh == 24) hh = 0;

                         ggmmsa_hhmi = new java.sql.Timestamp(sa-1900, mm-1, gg, hh,mi,0,0);


                    }catch (Exception e){
                        message += "<li> Campo \""+label+"\" non e&grave; un campo data (gg/mm/aaaa hh:mi).</li>";
                        str_gg = "";
                        rc = "-1";
                    }


            }else {
                message += "<li> Campo \""+label+"\" non e&grave; un campo data (gg/mm/aaaa hh:mi). </li>";
            }



        } // fine if valore_campo == ""


        return valore_campo;

    }







    public String getExtension(String filename) throws Exception{

        if (filename.lastIndexOf(".") > 0){
            return filename.substring(filename.lastIndexOf(".")+1);
        }

        return "";

    }

    public String getFileSenzaExt(String filename) throws Exception{

        if (filename.lastIndexOf(".") > 0){
            return filename.substring(0, filename.lastIndexOf("."));
        }

        return filename;

    }



    public String getFilename(String filename_abs) throws Exception{

        String slash = System.getProperty( "file.separator" );

        int idx = filename_abs.lastIndexOf(slash);

        if (idx > 0){
            return filename_abs.substring((idx+1));
        }

        return filename_abs;

    }

    public String getRelativepath(String filename_abs) throws Exception{

        String slash = System.getProperty( "file.separator" );

        int idx = filename_abs.lastIndexOf(slash);

        if (idx > 0){
            return filename_abs.substring(0, (idx+1));
        }

        return "";

    }







    /*
        Questa routine imposta il path togliendo 
        l'eventuale slash iniziale e mettendo la 
        slash finale
    */


    public String formatRelpath(String relativepath) throws Exception{

        String slash = System.getProperty( "file.separator" );
        String slash_inversa = "";

        if (slash.equals("/")){
            
            slash_inversa = "\\";
        }else {
            slash_inversa = "/";
        }
        
        relativepath = relativepath.replace(slash_inversa.charAt(0), slash.charAt(0));



        // verifico che non vi sia la slah iniziale


        int len = relativepath.length();

        if (len <= 0) return "";

        int idx = relativepath.indexOf(slash);

        if (idx == 0){
                
            if (relativepath.equals(slash)) return "";
            
            relativepath = relativepath.substring(1,len);
        }


        // verifico lo slash finale


        idx = relativepath.lastIndexOf(slash);
        len = relativepath.length();

        if (idx != (len - 1)){
            
            relativepath = relativepath + slash; 
        }

        return relativepath;

    }




    public String[] listaSubDir(String relpath, String slash) throws Exception{


        java.util.StringTokenizer stringTokenizer = new java.util.StringTokenizer( relpath, slash);
        
        String[] arr_listaSubDir = new String[stringTokenizer.countTokens( )];
    
    	int    numToken = 0;
        String str_app = "";

    	while (stringTokenizer.hasMoreTokens()) {

    		arr_listaSubDir[numToken] = str_app + stringTokenizer.nextToken()+slash;
            str_app = arr_listaSubDir[numToken];

    		numToken += 1;
    	}


        return arr_listaSubDir;
    }

    public String[] listaSubDir(String relpath) throws Exception{
        
        String slash = System.getProperty( "file.separator" );

        return listaSubDir(relpath, slash);
    }



    public String message = "";
    public String rc = "0";

}
