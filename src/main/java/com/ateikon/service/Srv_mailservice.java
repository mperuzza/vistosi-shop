
package com.ateikon.service;


import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.sql.Clob;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import java.util.Vector;

import com.ateikon.common.Costanti_comm;
import com.ateikon.common.Atk_sql;
import com.ateikon.common.Atk_messaggio;

import com.ateikon.util.Afs_ctrl;
import com.ateikon.util.Atk_ctrl;

public class Srv_mailservice extends Service {



    public Srv_mailservice (String[] args){

        super("Srv_mailservice", args);
    }




    /***
    *
    *   Impemento il Metodo Astratto
    **/



    public void elabora() throws Exception{

        

        PreparedStatement pstm = null;
        ResultSet         rs = null;
        ResultSet         rsDest = null;
        ResultSet         rsAttach = null;
        ResultSet         rsObj = null;
        
        int               tot_rec = 0;
        boolean           lb_ = false;




        Costanti_comm   costanti_comm   = new Costanti_comm();
        Atk_messaggio   atk_messaggio   = new Atk_messaggio();
        Srv_sendmail    srv_sendmail    = new Srv_sendmail();

        
        atk_sql.setProfilo((Atk_sql) costanti_comm  );
        atk_sql.setProfilo((Atk_sql) atk_messaggio  );
        atk_sql.setProfilo((Atk_sql) srv_sendmail   );




        // *** verifico se questo servizio deve elaborare un solo tipo di 
        // *** invio (vedi Savio Mailing List)

        String tpservizio      = costanti_comm.getCostvalue("srv_mailservice_tpservizio");
        String is_test         = costanti_comm.getCostvalue("srv_mailservice_is_test");

        costanti_comm.close();
        

        // vatiabili di appoggio

        long      tkmsg     = 0;
        String    host      = "";
        String    username  = "";
        String    password  = "";
        String    oggetto   = "";
        String    mittente  = "";
        String    contenuto = "";
        String    nomefile  = "";
        String    pathfile  = "";
        Timestamp dtricsped = null;
        Timestamp dtsped    = null;
        Timestamp dtproc    = null;
        String    nota      = "";
        long      id_object = 0;

        Vector    dest_TO     = new Vector();
        Vector    dest_CC     = new Vector();
        Vector    dest_BCC    = new Vector();
        Vector    attach      = new Vector();
        Vector    attach_name = new Vector();
        Vector    attach_conv = new Vector();
        Vector    attach_id_object   = new Vector();
        String    fgconv_pdf  = "N";


        String    ls_dsrifdoc  = "";
        String    relativepath = "";

        Clob         c_lob = null;
        byte         raw_buffer[];              
        InputStream  raw_instream = null;       




        rs = atk_messaggio.getMessaggi(tpservizio);


        while (rs!=null && rs.next()){
        
            
            tkmsg     = 0;
            host      = "";
            username  = "";
            password  = "";
            oggetto   = "";
            mittente  = "";
            contenuto = "";
            nomefile  = "";
            pathfile  = "";
            dtricsped = null;
            dtsped    = null;
            dtproc    = null;
            nota      = "";
            id_object = 0;

            dest_TO     = new Vector();
            dest_CC     = new Vector();
            dest_BCC    = new Vector();
            attach      = new Vector();
            attach_name = new Vector();
            attach_conv = new Vector();
            attach_id_object = new Vector();
            fgconv_pdf  = "N";

            rsAttach = null;
            rsDest   = null;

            ls_dsrifdoc  = "";
            relativepath = "";

            c_lob        = null;
            raw_buffer   = null; 
            raw_instream = null ;       


            if (rs.getObject("tkmsg"    )!=null) tkmsg      = rs.getLong     ("tkmsg"    );
            if (rs.getObject("host"     )!=null) host       = rs.getString   ("host"     );
            if (rs.getObject("username" )!=null) username   = rs.getString   ("username" );
            if (rs.getObject("password" )!=null) password   = rs.getString   ("password" );
            if (rs.getObject("oggetto"  )!=null) oggetto    = rs.getString   ("oggetto"  );
            if (rs.getObject("mittente" )!=null) mittente   = rs.getString   ("mittente" );
            if (rs.getObject("dtricsped")!=null) dtricsped  = rs.getTimestamp("dtricsped");
            if (rs.getObject("id_object")!=null) id_object  = rs.getLong("id_object");
            if (rs.getObject("contenuto")!=null) contenuto  = rs.getString   ("contenuto");



            if (is_test.equals("S")){
                
                if (!oggetto.equals("prova")){
                    
                    continue;
                }
            }


            if (id_object>0){

                throw new Exception("Id_gest_object NON Gestito");

            }


            for (int i=1; i<=4; i++){
                
                if (rs.getObject("dest"+i)!=null) dest_TO.add ( rs.getString("dest"+1) );
                if (rs.getObject("bcc"+i )!=null) dest_BCC.add( rs.getString("bcc"+i ) );
            }



            contenuto += atk_messaggio.getContenuto(tkmsg);


            rsDest = null;

            rsDest = atk_messaggio.getDest(tkmsg, atk_messaggio.TIPODEST_TO);
            while(rsDest !=null && rsDest.next()){
                if (rsDest.getObject("dest")!=null) dest_TO.add (rsDest.getString("dest"));
            }


            rsDest = null;

            rsDest = atk_messaggio.getDest(tkmsg, atk_messaggio.TIPODEST_CC);
            while(rsDest !=null && rsDest.next()){
                if (rsDest.getObject("dest")!=null) dest_CC.add (rsDest.getString("dest"));
            }


            rsDest = null;

            rsDest = atk_messaggio.getDest(tkmsg, atk_messaggio.TIPODEST_BCC);
            while(rsDest !=null && rsDest.next()){
                if (rsDest.getObject("dest")!=null) dest_BCC.add (rsDest.getString("dest"));
            }


            rsAttach = null;

            rsAttach = atk_messaggio.getAttach(tkmsg);

            while(rsAttach !=null && rsAttach.next()){
                
                pathfile    = "";
                nomefile    = "";
                id_object   = 0;
                fgconv_pdf  = "N";

                if (rsAttach.getObject("id_object")!=null) id_object  = rsAttach.getLong("id_object");
                if (rsAttach.getObject("fgconv_pdf")!=null) fgconv_pdf = rsAttach.getString("fgconv_pdf");
                

                if (id_object>0){

                    throw new Exception ("Documentale NON Configurato!");

                }else {
                    if (rsAttach.getObject("pathfile")!=null) pathfile = rsAttach.getString("pathfile");
                    if (rsAttach.getObject("nomefile")!=null) nomefile = rsAttach.getString("nomefile");
                }


                if (!(pathfile+nomefile).equals("")){
                    attach.add     (pathfile+nomefile);
                    attach_name.add(nomefile);
                    attach_conv.add(fgconv_pdf);
                    attach_id_object.add(new Long(id_object));
                }


            }



            debugger("**************************************");
            debugger("**** Nuova richiesta di invio Mail ***");
            debugger("**************************************");
            debugger("Dati messaggio  "+tkmsg    );
            debugger(" - tkmsg      >>"+tkmsg    );
            debugger(" - host       >>"+host     );
            debugger(" - username   >>"+username );
            debugger(" - password   >>"+password );
            debugger(" - oggetto    >>"+oggetto  );
            debugger(" - mittente   >>"+mittente );
            debugger(" - contenuto  >>"+contenuto);
            debugger(" - dtricsped  >>"+dtricsped.toString());
            debugger(" ");
            debugger("Destinatari TO ");
            for (int i=0; dest_TO!=null && i<dest_TO.size(); i++){
                debugger(" - "+(String )dest_TO.elementAt(i));
            }
            debugger("Destinatari CC ");
            for (int i=0; dest_CC!=null && i<dest_CC.size(); i++){
                debugger(" - "+(String )dest_CC.elementAt(i));
            }
            debugger("Destinatari BCC ");
            for (int i=0; dest_BCC!=null && i<dest_BCC.size(); i++){
                debugger(" - "+(String )dest_BCC.elementAt(i));
            }
            debugger(" ");
            debugger("Attach ");
            for (int i=0; attach!=null && i<attach.size(); i++){
                debugger(" - "+(String )attach.elementAt(i));
            }
            debugger(" ");



            // controllo dati e invio mail

            if ( validate(tkmsg, atk_messaggio, dest_TO, dest_CC, dest_BCC, attach, attach_name, attach_conv, attach_id_object ) ){

                debugger("Controllo mail OK");

                String ls_ex = "";

                try {
                    
                    lb_ = srv_sendmail.invia( mittente    
                                            , dest_TO     
                                            , dest_CC     
                                            , dest_BCC    
                                            , oggetto      
                                            , contenuto   
                                            , attach      
                                            , attach_name );
                }catch(Exception ex_inv){
                    
                    lb_ = false;
                    ls_ex = ex_inv.toString();
                }


                if ( lb_ ){

                    debugger("Mail inviata ");

                    atk_messaggio.setProcessato(tkmsg, "", new Timestamp(System.currentTimeMillis( )) ); 

                }else {
                    
                    atk_messaggio.setProcessato(tkmsg, "Invio Errato: "+ls_ex, null ); 

                    debugger("Invio mail KO ");
                }

            }else {
                
                // aggiornamento tabella messagio  
                debugger("Controllo mail KO");
            }




        } // FINE while rs = atk_messaggio.getMessaggi(tpservizio);


        atk_messaggio.close();  
        srv_sendmail.close();



    }   // FINE ELABORA






    /***

        Controlli formali invio mail

    **/





    public boolean validate( long          tkmsg
                           , Atk_messaggio atk_messaggio
                           , Vector        dest_TO 
                           , Vector        dest_CC 
                           , Vector        dest_BCC
                           , Vector        attach
                           , Vector        attach_name
                           , Vector        attach_conv
                           , Vector        attach_id_object
                                                            ) throws Exception{

        File file = null;
        boolean lb_ = false;

        String slash = System.getProperty( "file.separator" );


        Afs_ctrl afs_ctrl = new Afs_ctrl();
        Atk_ctrl atk_ctrl = new Atk_ctrl();
        Costanti_comm costanti_comm = new Costanti_comm();

        atk_sql.setProfilo((Atk_sql)costanti_comm);

        String fgconv = "";

        String path_backup_pdf = costanti_comm.getCostvalue("srv_mailservice_path_pdf");

        costanti_comm.close();


        path_backup_pdf += tkmsg+slash;


        long     id_object    = 0;
        String   filepath     = "";
        String   extension    = "";
        String   filepath_pdf = "";
        String   binRoot      = "";
        String   ls_printer   = "";
        String   ls_vbs_name  = "";
        String[] param_exec   = null;
        Process  proc       = null;




        // ctrl Destinatari

        int tot_dest = 0;
        if (dest_TO !=null ) tot_dest += dest_TO.size();
        if (dest_CC !=null ) tot_dest += dest_CC.size();
        if (dest_BCC!=null ) tot_dest += dest_BCC.size();

        if (tot_dest<=0){

            atk_messaggio.setProcessato(tkmsg, "Valorizzare il destinatario", null ); 
            return false;
        }


        for (int i=0; dest_TO!=null && i<dest_TO.size(); i++){
            
            String ls_email = (String) dest_TO.elementAt(i);

            int indx1 = ls_email.indexOf("@");
            int indx2 = ls_email.lastIndexOf("@");
            int indx3 = ls_email.lastIndexOf("/");
            int indx4 = ls_email.lastIndexOf("'");
            int indx5 = ls_email.lastIndexOf(" ");

            if (indx3 > 0){
                atk_messaggio.setProcessato(tkmsg, "Mail "+ls_email+" errata", null ); 
                return false;
            }
            if (indx4 > 0){
                atk_messaggio.setProcessato(tkmsg, "Mail "+ls_email+" errata", null ); 
                return false;
            }
            if (indx5 > 0){
                atk_messaggio.setProcessato(tkmsg, "Mail "+ls_email+" errata", null ); 
                return false;
            }
            if (indx1 < 0){
                atk_messaggio.setProcessato(tkmsg, "Mail "+ls_email+" errata", null ); 
                return false;
            }
            if (indx1 != indx2){
                atk_messaggio.setProcessato(tkmsg, "Mail "+ls_email+" errata", null ); 
                return false;
            }

        }
        for (int i=0; dest_CC!=null && i<dest_CC.size(); i++){
            
            String ls_email = (String) dest_CC.elementAt(i);

            int indx1 = ls_email.indexOf("@");
            int indx2 = ls_email.lastIndexOf("@");
            int indx3 = ls_email.lastIndexOf("/");
            int indx4 = ls_email.lastIndexOf("'");
            int indx5 = ls_email.lastIndexOf(" ");

            if (indx3 > 0){
                atk_messaggio.setProcessato(tkmsg, "Mail "+ls_email+" errata", null ); 
                return false;
            }
            if (indx4 > 0){
                atk_messaggio.setProcessato(tkmsg, "Mail "+ls_email+" errata", null ); 
                return false;
            }
            if (indx5 > 0){
                atk_messaggio.setProcessato(tkmsg, "Mail "+ls_email+" errata", null ); 
                return false;
            }
            if (indx1 < 0){
                atk_messaggio.setProcessato(tkmsg, "Mail "+ls_email+" errata", null ); 
                return false;
            }
            if (indx1 != indx2){
                atk_messaggio.setProcessato(tkmsg, "Mail "+ls_email+" errata", null ); 
                return false;
            }

        }

        for (int i=0; dest_BCC!=null && i<dest_BCC.size(); i++){
            
            String ls_email = (String) dest_BCC.elementAt(i);

            int indx1 = ls_email.indexOf("@");
            int indx2 = ls_email.lastIndexOf("@");
            int indx3 = ls_email.lastIndexOf("/");
            int indx4 = ls_email.lastIndexOf("'");
            int indx5 = ls_email.lastIndexOf(" ");

            if (indx3 > 0){
                atk_messaggio.setProcessato(tkmsg, "Mail "+ls_email+" errata", null ); 
                return false;
            }
            if (indx4 > 0){
                atk_messaggio.setProcessato(tkmsg, "Mail "+ls_email+" errata", null ); 
                return false;
            }
            if (indx5 > 0){
                atk_messaggio.setProcessato(tkmsg, "Mail "+ls_email+" errata", null ); 
                return false;
            }
            if (indx1 < 0){
                atk_messaggio.setProcessato(tkmsg, "Mail "+ls_email+" errata", null ); 
                return false;
            }
            if (indx1 != indx2){
                atk_messaggio.setProcessato(tkmsg, "Mail "+ls_email+" errata", null ); 
                return false;
            }
        }


        // ctrl percorso file
        for (int i=0; attach!=null && i<attach.size(); i++){

            filepath = (String)attach.elementAt(i);
            extension = afs_ctrl.getExtension(filepath);

            extension = extension.toLowerCase();

            file = new File(filepath);

            id_object = ((Long)attach_id_object.elementAt(i)).longValue();


            if (!file.exists() || !file.isFile()){
            
                atk_messaggio.setProcessato(tkmsg, "Percorso File \""+(String)attach_name.elementAt(i)+"\" Errato", null ); 
                return false;
            }

            fgconv = (String)attach_conv.elementAt(i);

            if (fgconv.equals("S")){

                ls_vbs_name  = "";

                if (extension.equals("doc")){
                    
                    ls_vbs_name = "stampa_word.vbs";
    
                }else if (extension.equals("txt")){
                    
                    ls_vbs_name = "stampa_word.vbs";

                }else if (extension.equals("xls")){
                    
                    ls_vbs_name = "stampa_excel.vbs";

                }else {
                    
                    atk_messaggio.setProcessato(tkmsg, "Errore Conv. PDF: estensione file "+extension+" NON prevista", null ); 
                    return false;
                }


            
                // converto il file in pdf

                // creo la dir. di appoggio

                File f_file = new File(path_backup_pdf);

                if (!f_file.exists()){
                    
                    lb_ = f_file.mkdirs() ;
                    
                    if (!lb_){
                        atk_messaggio.setProcessato(tkmsg, "Errore creazione Dir. Conv. PDF. "+path_backup_pdf , null ); 
                        return false;
                    }
                }

                filepath_pdf = file.getName();
                filepath_pdf = path_backup_pdf + afs_ctrl.getFileSenzaExt(filepath_pdf)+".pdf";

                binRoot      = costanti_comm.getCostvalue("srv_conv_doc_bin");
                ls_printer   = costanti_comm.getCostvalue("srv_conv_doc_printerPS");        // stampante Post Script

                costanti_comm.close();


                param_exec = new String[]{ binRoot+"stampa_pdf.bat"
                                         , binRoot+ls_vbs_name
                                         , filepath
                                         , ls_printer
                                         , filepath_pdf
                                         };

                proc = Runtime.getRuntime( ).exec(param_exec);

                atk_ctrl.proc_output(proc);

                proc.waitFor();
                

                file = new File(filepath_pdf);
    
                if (!file.exists()){
                    
                    atk_messaggio.setProcessato(tkmsg, "Err. Conv. PDF: "+filepath, null ); 
                    return false;
                }

                // sostituisco il path dei documenti originali con
                // quelli convertiti!

                attach.setElementAt(filepath_pdf, i);
                attach_name.setElementAt(file.getName(), i);

                file = null;


                if (id_object > 0){
                    
                    atk_messaggio.setConv_pdf(tkmsg, id_object, filepath_pdf);
                }else {
                    atk_messaggio.setConv_pdf(tkmsg, filepath, filepath_pdf);
                }
            


            } // Fine if (fgconv.equals("S")){
                
            



        }


        return true;

    }






    /***
    *
    *   Main
    **/



    public static void main(String[] args){

        if (args==null || args.length != 1){
            System.out.println("");
            System.out.println("");
            System.out.println("Argomenti validi: start|stop");
            System.out.println("");
            System.out.println("");

            System.gc();
            System.exit(0);
        }
        args[0] = args[0].toLowerCase();

        Srv_mailservice srv_mailservice = new Srv_mailservice(args);

        System.gc();
        System.exit(0);
    }



    //  Properties






}
