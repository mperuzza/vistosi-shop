package com.ateikon.function;




import com.ateikon.common.Atk_messaggio;
import com.ateikon.common.Atk_msgcont;
import com.ateikon.common.Atk_msgdest;
import com.ateikon.common.Atk_msgfile;
import com.ateikon.common.Atk_sql;
import com.ateikon.common.Costanti_comm;
import com.ateikon.common.Ep_costanti;
import com.ateikon.util.StringUtils;
import com.ateikon.structure.Rec_atk_messaggio;
import com.ateikon.structure.Rec_atk_msgcont;
import com.ateikon.structure.Rec_atk_msgdest;
import com.ateikon.structure.Rec_atk_msgfile;
import com.voxbiblia.jresolver.Resolver;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Vector;
import javax.mail.internet.InternetAddress;


public class F_sender extends Atk_sql {

    public F_sender() {

        super();

        
    }

    public long inviaSMS( String  mittente
                        , String  cellulare
                        , String  oggetto
                        , String  contenuto
                        , Resolver ao_resolver
                                                        ) throws Exception {
        return  inviaSMS  ( mittente
                          , cellulare
                          , oggetto
                          , contenuto
                          , new Timestamp(System.currentTimeMillis())
                          , true
                          , ao_resolver
                                             );

    }

    public long inviaSMS( String  mittente
                        , String  cellulare
                        , String  oggetto
                        , String  contenuto
                        , Timestamp dtricsped
                        , boolean fgcommit
                        , Resolver ao_resolver
                                                        ) throws Exception {

        Vector  dest_TO = new Vector();

        dest_TO.add(cellulare);

        return inviaSMS  ( mittente
                         , dest_TO
                         , null
                         , oggetto
                         , contenuto
                         , dtricsped
                         , fgcommit
                         , ao_resolver
                         );


    }

    public long inviaSMS( String  mittente
                        , Vector  dest_TO
                        , Vector  dest_email_BCC
                        , String  oggetto
                        , String  contenuto
                        , boolean fgcommit
                        , Resolver ao_resolver
                                                        ) throws Exception {
        return  inviaSMS  ( mittente
                          , dest_TO
                          , dest_email_BCC
                          , oggetto
                          , contenuto
                          , new Timestamp(System.currentTimeMillis())
                          , fgcommit
                          , ao_resolver
                                             );

    }


    public long inviaSMS( String  mittente
                        , Vector  dest_TO
                        , Vector  dest_email_BCC
                        , String  oggetto
                        , String  contenuto
                        , Timestamp dtricsped
                        , boolean fgcommit
                        , Resolver ao_resolver
                                                        ) throws Exception {

        if (dest_TO == null || dest_TO.size() == 0) return -1;

        for (int i=0; i < dest_TO.size(); i++){
            String ls_cellulare = (String) dest_TO.get(i);

            ls_cellulare += "@sms.kqumg.it";

            dest_TO.set(i, ls_cellulare);

            System.out.println(ls_cellulare);
        }

        return inviaEMail( mittente
                         , dest_TO
                         , null
                         , dest_email_BCC
                         , oggetto
                         , contenuto
                         , null
                         , null
                         , dtricsped
                         , fgcommit
                         , ao_resolver
                         );


    }



    public long inviaEMail( String  mittente
                        , String  as_dest_TO
                        , String  oggetto
                        , String  contenuto
                        , Resolver ao_resolver
                                                        ) throws Exception {
        return  inviaEMail( mittente
                          , as_dest_TO
                          , oggetto
                          , contenuto
                          , new Timestamp(System.currentTimeMillis())
                          , true
                          , ao_resolver
                                             );

    }

    public long inviaEMail( String  mittente
                        , String  as_dest_TO
                        , String  oggetto
                        , String  contenuto
                        , boolean fgcommit
                        , Resolver ao_resolver
                                                        ) throws Exception {
        return  inviaEMail( mittente
                          , as_dest_TO
                          , oggetto
                          , contenuto
                          , new Timestamp(System.currentTimeMillis())
                          , fgcommit
                          , ao_resolver
                                             );

    }

    public long inviaEMail( String  mittente
                        , String  as_dest_TO
                        , String  oggetto
                        , String  contenuto
                        , Timestamp dtricsped
                        , boolean fgcommit
                        , Resolver ao_resolver
                                                        ) throws Exception {

        Vector  dest_TO = new Vector();

        dest_TO.add(as_dest_TO);

        return inviaEMail( mittente
                         , dest_TO
                         , null
                         , null
                         , oggetto
                         , contenuto
                         , null
                         , null
                         , dtricsped
                         , fgcommit
                         , ao_resolver
                         );


    }


    public long inviaEMail( String  mittente
                            , Vector  dest_TO
                            , Vector  dest_CC
                            , Vector  dest_BCC
                            , String  oggetto
                            , String  contenuto
                            , Vector  attach
                            , Vector  attach_name
                            , Resolver ao_resolver
                                                        ) throws Exception {

           return inviaEMail( mittente
                            , dest_TO
                            , dest_CC
                            , dest_BCC
                            , oggetto
                            , contenuto
                            , attach
                            , attach_name
                            , new Timestamp(System.currentTimeMillis())
                            , true
                            , ao_resolver
                            );
                                                       
    }

    public long inviaEMail( String  mittente
                            , Vector  dest_TO
                            , Vector  dest_CC
                            , Vector  dest_BCC
                            , String  oggetto
                            , String  contenuto
                            , Vector  attach
                            , Vector  attach_name
                            , boolean fgcommit
                            , Resolver ao_resolver
                                                        ) throws Exception {

           return inviaEMail( mittente
                            , dest_TO
                            , dest_CC
                            , dest_BCC
                            , oggetto
                            , contenuto
                            , attach
                            , attach_name
                            , new Timestamp(System.currentTimeMillis())
                            , fgcommit
                            , ao_resolver
                            );

    }



    public long inviaEMail( String  mittente
                            , Vector  dest_TO
                            , Vector  dest_CC
                            , Vector  dest_BCC
                            , String  oggetto
                            , String  contenuto
                            , Vector  attach
                            , Vector  attach_name
                            , Timestamp dtricsped
                            , boolean fgcommit
                            , Resolver ao_resolver
                                                        ) throws Exception {

        int tot_rec = 0;

        Atk_messaggio atk_messaggio = new Atk_messaggio();
        Atk_msgfile atk_msgfile = new Atk_msgfile();

        setProfilo((Atk_sql) atk_messaggio);
        setProfilo((Atk_sql) atk_msgfile);


        //Controlli Formali

        if (dest_TO == null || dest_TO.size() <= 0){
            System.out.println("Nessun destinatario TO impostato!!!");
            return -2;
        }


        //System.out.println("I - msg ");
        //Preparo record Messaggio
        Rec_atk_messaggio lstr_msg = new Rec_atk_messaggio();

        lstr_msg.oggetto = oggetto;
        lstr_msg.dtricsped = dtricsped;
        lstr_msg.mittente = mittente;

        //Inserisco record Messaggio in Atk_messaggio
        tot_rec = atk_messaggio.execute(lstr_msg);

        //System.out.println("F - msg ");

        if (tot_rec != 1){
           m_connection.rollback();
           
           return -1;
        } else {

           //record destinatari
           //System.out.println("I - dest");
           //System.out.println("I - dest TO");
           tot_rec = setDestinatari_TO(lstr_msg.tkmsg, dest_TO, ao_resolver);

           if (tot_rec != 1){
              m_connection.rollback();

              return -1;
           }
           //System.out.println("F - dest TO");
           //System.out.println("I - dest CC");

           if (dest_CC!=null && dest_CC.size() > 0){
               tot_rec = setDestinatari_CC(lstr_msg.tkmsg, dest_CC, ao_resolver);

               if (tot_rec != 1){
                  m_connection.rollback();

                  return -1;
               }
           }

           //System.out.println("F - dest CC");
           //System.out.println("I - dest BCC");
           if (dest_BCC!=null && dest_BCC.size() > 0){
               tot_rec = setDestinatari_BCC(lstr_msg.tkmsg, dest_BCC, ao_resolver);

               if (tot_rec != 1){
                  m_connection.rollback();

                  return -1;
               }
           }
           //System.out.println("F - dest BCC");
           //System.out.println("F - dest");


           //System.out.println("I - contenuto");
           //record contenuto
           if (!contenuto.equals("")){
               tot_rec = setContenuto(lstr_msg.tkmsg, contenuto);

               if (tot_rec != 1){
                  m_connection.rollback();

                  return -1;
               }
           }
           //System.out.println("F - contenuto");


           //System.out.println("I - attach");
           //record attach
           if (attach!=null && attach.size() > 0){
               tot_rec = setAllegati(lstr_msg.tkmsg, attach, attach_name);

               if (tot_rec != 1){
                  m_connection.rollback();

                  return -1;
               }
           }
           //System.out.println("F - attach");


           //Se arriva qui tutto bene
           if (fgcommit){
               m_connection.commit();
           }
        }


        return lstr_msg.tkmsg;

    }


    public boolean isValid(String email, Resolver ao_resolver) throws Exception                {

        boolean fg_mail_ok = false;

        try {
            InternetAddress mail_address = new InternetAddress(email);

            mail_address.validate();

            mail_address = null;

            if (email.indexOf("@")>0){

                int li_ = email.indexOf("@");

                String ls_dominio = email.substring((li_+1));

                if (ls_dominio.equals("")){
                    throw new Exception("NO indirizzo e-mail valido: Dominio non valido");
                }
                
                int idx_punto = ls_dominio.lastIndexOf(".");

                if (idx_punto < 0){
                  throw new Exception("NO indirizzo e-mail valido: Dominio non valido, non è almeno di 2 livello: non trovato punto");
                }
                
                String ls_tld = ls_dominio.substring(idx_punto + 1);//top-level domain
                String ls_residuo_dominio = ls_dominio.substring(0, idx_punto);
                
                if (ls_tld.equals("")){
                  throw new Exception("NO indirizzo e-mail valido: Dominio non valido, non è almeno di 2 livello: no tld");
                }
                
                if (ls_residuo_dominio.equals("")){
                  throw new Exception("NO indirizzo e-mail valido: Dominio non valido, non è almeno di 2 livello");
                }

            }else {
                throw new Exception("NO indirizzo e-mail valido: @ non trovata");
            }

            //E-mail valida
            fg_mail_ok = true;
        } catch (Exception ex) {
            //E-mail NON valida
										  //ex.printStackTrace();
            fg_mail_ok = false;
        }

        return fg_mail_ok;
    }


    public String getModel(String path) throws Exception                {

		FileInputStream 	fileInputStream = null;
		File 				file = null;
		byte[] 				arr_buffer = null;
		StringBuffer		buffer = new StringBuffer("");
        String              testo = "";


		file = new File(path);

		if (!file.exists( ) || file.isDirectory( )){
			throw new Exception ("Il Modello non è un File: >>"+path+"<< ");
		}

		// Carico il buffer
		fileInputStream = new FileInputStream(file);

		arr_buffer = new byte[new Long(file.length( )).intValue()];
		fileInputStream.read( arr_buffer );
		fileInputStream.close();

		testo = new String(arr_buffer);

        return testo;
    }

    public int setContenuto (long tkmsg, String contenuto ) throws Exception {

    	int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;


        Costanti_comm costanti_comm = new Costanti_comm();
        Atk_msgcont  atk_msgcont = new Atk_msgcont();

        setProfilo((Atk_sql) costanti_comm);
        setProfilo((Atk_sql) atk_msgcont);



		// Se trovo nel contenuto @tkmsg@ lo sostituisco con il valore di tkmsg
		contenuto = StringUtils.replace(contenuto, "@tkmsg@", "" + tkmsg);



        int len_rec = 1000;

        try {

            len_rec = Integer.parseInt(costanti_comm.getCostvalue("srv_mailservice_lcont"));
        }catch(Exception ex){

            len_rec = 1000;
        }


        // cancello tutto il contenuto

        tot_rec = 0;

        l_query  = "";
        l_query += " delete from pgmr.atk_msgcont where tkmsg = "+tkmsg+" \n";

        pstm = m_connection.prepareStatement(l_query);

        tot_rec = pstm.executeUpdate();

        pstm.close();


        int    li_     = 0;
        int    nrposi  = 0;
        String ls_cont = "";

        li_ = contenuto.length();

        while (li_ > 0){

            ls_cont  = "";
            nrposi  += 10;

            if (li_ > len_rec){

                ls_cont   = contenuto.substring(0, len_rec);
                contenuto = contenuto.substring(len_rec);
            }else {

                ls_cont   = contenuto;
                contenuto = "";
            }

            li_ = contenuto.length();


            Rec_atk_msgcont lstr_cont = new Rec_atk_msgcont();

            lstr_cont.contenuto = ls_cont;
            lstr_cont.nrposi    = nrposi;
            lstr_cont.tkmsg     = tkmsg;


            tot_rec = atk_msgcont.execute(lstr_cont);

            if (tot_rec != 1){
               m_connection.rollback();

               return -1;
            }
        }


        costanti_comm.close();
        atk_msgcont.close();

        return tot_rec;

    }

    public int setDestinatari_TO (long tkmsg, Vector dest, Resolver ao_resolver) throws Exception {

        int tot_rec = 0;

        for (int i=0; dest != null && i < dest.size(); i++ ){

             String ls_dest = (String) dest.get(i);
             if (ls_dest == null) ls_dest = "";

             tot_rec = setDestinatario(tkmsg, ls_dest, this.TIPODEST_TO, ao_resolver);

             if (tot_rec != 1){
                m_connection.rollback();

                return -1;
             }
        }

        return tot_rec;

    }


    public int setDestinatari_CC (long tkmsg, Vector dest, Resolver ao_resolver) throws Exception {

        int tot_rec = 0;

        for (int i=0; dest != null && i < dest.size(); i++ ){

             String ls_dest = (String) dest.get(i);
             if (ls_dest == null) ls_dest = "";

             tot_rec = setDestinatario(tkmsg, ls_dest, this.TIPODEST_CC, ao_resolver);

             if (tot_rec != 1){
                m_connection.rollback();

                return -1;
             }
        }

        return tot_rec;

    }


    public int setDestinatari_BCC (long tkmsg, Vector dest, Resolver ao_resolver) throws Exception {

        int tot_rec = 0;

        for (int i=0; dest != null && i < dest.size(); i++ ){

             String ls_dest = (String) dest.get(i);
             if (ls_dest == null) ls_dest = "";

             tot_rec = setDestinatario(tkmsg, ls_dest, this.TIPODEST_BCC, ao_resolver);

             if (tot_rec != 1){
                m_connection.rollback();

                return -1;
             }
        }

        return tot_rec;

    }


    public int setDestinatario (long tkmsg, String dest, String tipodest, Resolver ao_resolver) throws Exception {

        int tot_rec = 0;

        Atk_msgdest atk_msgdest = new Atk_msgdest();
        Ep_costanti ep_costanti = new Ep_costanti();

        setProfilo((Atk_sql) atk_msgdest);
        setProfilo((Atk_sql) ep_costanti);

        Rec_atk_msgdest lstr_dest = new Rec_atk_msgdest();

        lstr_dest.tkmsg = tkmsg;
        lstr_dest.dest  = dest;
        lstr_dest.tipodest = tipodest;

        //Valido l'indirizzo email
								//avendramin20110708 - I
        //TODO
        if (!isValid(lstr_dest.dest, ao_resolver)){
          System.out.println("Indirizzo NON valido: "+ lstr_dest.dest);

          String ep_mail_dest_TO    = ep_costanti.getCostvalue("ep.mail_dest_TO");

          if (!ep_mail_dest_TO.equals("")){
              lstr_dest.dest = ep_mail_dest_TO;
          } else {
              return -2;
          }

								}
								//avendramin20110708 - F


        //Inserisco record destinatari in Atk_msgdest
        tot_rec = atk_msgdest.execute(lstr_dest);

        if (tot_rec != 1){
           m_connection.rollback();

           return -1;
        }

        atk_msgdest.close();
        ep_costanti.close();

        return tot_rec;

    }


    public int setAllegati (long tkmsg, Vector attach, Vector attach_name ) throws Exception {

        int tot_rec = 0;

        for (int i=0; attach != null && i < attach.size(); i++ ){

             if (attach_name != null && attach.size() != attach_name.size()) return -2;

             File lobj_file = (File) attach.get(i);
             String ls_filename = "";

             if (attach_name != null){
                 ls_filename = (String) attach_name.get(i);
                 if (ls_filename == null) ls_filename = "";
             }

             tot_rec = setAllegato(tkmsg, lobj_file, ls_filename, i+1);

             if (tot_rec != 1){
                m_connection.rollback();

                return -1;
             }
        }

        return tot_rec;
    }

    public int setAllegato (long tkmsg, File file, String filename, int posi ) throws Exception {

        int tot_rec = 0;

        if (file != null){
            if (filename.equals("")) filename = file.getName();

            //copio il file nella cartella del dispatcher
            Costanti_comm costanti_comm = new Costanti_comm();

            setProfilo((Atk_sql) costanti_comm);

            String ls_office_root = costanti_comm.getCostvalue("officeRoot");
            String slash = System.getProperty( "file.separator" );

            if (ls_office_root.equals("")) throw new Exception("Valorizzare Office ROOT documentale");
            if (slash.equals("")) throw new Exception("Slash NON trovato");

            String pathfile = ls_office_root + dispatcher_dir + slash +  "tkmsg_"+ tkmsg + slash;

            //creo cartella se non esiste
            File obj_copy_dir = new File(pathfile);

            if (!obj_copy_dir.isDirectory()){
               obj_copy_dir.mkdirs();
               if (!obj_copy_dir.isDirectory()) throw new Exception("Errore in creazione directory");
            }

            //Copio il file nella directory del Dipatcher
            File obj_copy_file = new File(pathfile + filename);

            //Copio il file
            InputStream in = new FileInputStream(file);
            OutputStream out = new FileOutputStream(obj_copy_file);

            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0){
              out.write(buf, 0, len);
            }
            in.close();
            out.close();

            //Inserimento Record
            Atk_msgfile atk_msgfile = new Atk_msgfile();

            setProfilo((Atk_sql) atk_msgfile);

            Rec_atk_msgfile lstr_file = new Rec_atk_msgfile();

            lstr_file.tkmsg = tkmsg;
            lstr_file.nomefile  = filename;
            lstr_file.pathfile = pathfile;
            lstr_file.fgconv_pdf = "N";
            lstr_file.id_object = 0;
            lstr_file.posi = posi;

            //Inserisco record destinatari in Atk_msgdest
            tot_rec = atk_msgfile.execute(lstr_file);

            if (tot_rec != 1){
               m_connection.rollback();

               return -1;
            }

            atk_msgfile.close();
            costanti_comm.close();

        } else {
            return -1;
        }

        return tot_rec;
    }

    public Resolver createMXResolver() throws Exception                {

        Costanti_comm costanti_comm = new Costanti_comm();

        setProfilo((Atk_sql) costanti_comm);

        String ls_dns_host = costanti_comm.getCostvalue("dns_host");

        Resolver lo_resolver = null;

        try {
          lo_resolver = new Resolver(ls_dns_host);
        } catch (Exception ex){
          lo_resolver = null;
        } 

								costanti_comm.close();

        return lo_resolver;
    }

    public String TIPODEST_TO  = "D";
    public String TIPODEST_CC  = "C";
    public String TIPODEST_BCC = "B";

    public String dispatcher_dir = "AtkDispatcher";
}

