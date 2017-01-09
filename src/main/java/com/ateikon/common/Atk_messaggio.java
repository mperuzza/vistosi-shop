
package com.ateikon.common;


import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;

import java.util.Vector;
import java.util.StringTokenizer;

import com.ateikon.function.F_tabkey;


public class Atk_messaggio extends com.ateikon.standard.Atk_messaggio {

    public Atk_messaggio() {

        super();
    }




    // *** SERVIZIO INVIO MAIL







    /***
     *  Recupero tutti i messaggi non ancora processati
     *      tutti i record non ancora processati
     *      e ancora da spedire
     *      e con data richiesta spedizione < di adesso
     *    se tpservizio � valorizzato indica il campo 
     *    in tabella che non deve essere null
     *    es. invio delle sole bolle --> tkbol is not null
     *              news Letter      --> tkml is not null
     *        
     */



    public ResultSet getMessaggi(String tpservizio) throws Exception
    {

        PreparedStatement pstm = null;
        ResultSet         rs = null;
        int               ind = 0;
        int               tot_rec = 0;

        Timestamp         adesso = new Timestamp(System.currentTimeMillis( ));

        l_query  = "";

		l_query += " select *                       \n";
		l_query += "   from pgmr.atk_messaggio \n";
		l_query += "  where dtproc is null          \n";
		l_query += "    and dtsped is null          \n";
		l_query += "    and dtricsped is not null   \n";
		l_query += "    and dtricsped <= ?          \n";
        if (tpservizio.equals("news_letter")){
		l_query += "    and tkml is not null        \n";
        }
		l_query += "  order by dtricsped            \n";
		l_query += "         , dtinse               \n";
		l_query += "         , tkmsg                \n";


        pstm = setQuery( l_query ) ;
        pstm.setTimestamp(1, adesso);

        rs = pstm.executeQuery();

        return rs;

    }








    public int setProcessato(long tkmsg, String nota, Timestamp dtsped ) throws Exception    {

        PreparedStatement pstm = null;
        ResultSet         rs = null;
        int               ind = 0;
        int               tot_rec = 0;

        Timestamp         dtproc = new Timestamp(System.currentTimeMillis( ));

        dtulag = new Timestamp(System.currentTimeMillis( ));


        if (nota.equals("")) nota = null;

        l_query  = "";
		l_query += " update pgmr.atk_messaggio \n";
		l_query += "    set nota   = ?              \n";
		l_query += "      , dtulag = ?              \n";
		l_query += "      , dtproc = ?              \n";
		l_query += "      , dtsped = ?              \n";
		l_query += "  where tkmsg  = ?              \n";

        pstm = m_connection.prepareStatement( l_query ) ;
        
        ind = 1;
        pstm.setString   (ind, nota  ); ind += 1;
        pstm.setTimestamp(ind, dtulag); ind += 1;
        pstm.setTimestamp(ind, dtproc); ind += 1;
        pstm.setTimestamp(ind, dtsped); ind += 1;
        pstm.setLong     (ind, tkmsg ); ind += 1;

        tot_rec = pstm.executeUpdate();

        m_connection.commit();
        pstm.close();
        pstm = null;

        return tot_rec;

    }









    // *** Client 





    public ResultSet getMail( long tkmsg) throws Exception {
        PreparedStatement pstm = null;
        ResultSet         rs = null;
        int               ind = 0;
        int               tot_rec = 0;


        l_query   = "";
        l_query  += " select *                          \n";
        l_query  += "   from pgmr.atk_messaggio         \n";
        l_query  += "  where tkmsg = ?                  \n";

        pstm = setQuery( l_query ) ;

        ind = 1;
        pstm.setLong  (ind, tkmsg); ind += 1;

        rs = pstm.executeQuery();

        return rs;
    }




    public ResultSet getMail( Timestamp dtinse
                            , Timestamp dtinse_a
                            , String    fgStato
                            , String    mittente
                            , String    order_by
                                                            ) throws Exception {
        PreparedStatement pstm = null;
        ResultSet         rs = null;
        int               ind = 0;
        int               tot_rec = 0;

        if (order_by.equals("")) order_by = " dtinse desc ";


        l_query   = "";
        l_query  += " select *                   \n";
        l_query  += "   from pgmr.atk_messaggio  \n";
        l_query  += "  where 1=1   \n";

        if (!mittente.equals("")){
 
            l_query  += "    and lower(mittente) like lower('%"+mittente+"%') \n";
        }
        if (fgStato.equals("bloccate")){

            l_query  += "    and nota is not null    \n";
            l_query  += "    and dtproc is not null  \n";

        }else if (fgStato.equals("non_processate")){

            l_query  += "    and dtproc is null  \n";

        }else if (fgStato.equals("spedite")){

            l_query  += "    and dtsped is not null  \n";
        }
        if (dtinse != null){
            l_query  += "    and dtinse >= ?          \n";
        }
        if (dtinse_a != null){
            l_query  += "    and dtinse <= ?          \n";
        }
        l_query  += "  order by "+ order_by +" \n";


        pstm = setQuery( l_query ) ;

        ind = 1;
        if (dtinse != null){
            pstm.setTimestamp(ind, dtinse); ind += 1;
        }
        if (dtinse_a != null){
            pstm.setTimestamp(ind, dtinse_a); ind += 1;
        }

        rs = pstm.executeQuery();

        return rs;
    }



    public int sbloccaMail(long tkmsg) throws Exception {
        PreparedStatement pstm = null;
        ResultSet         rs = null;
        int               ind = 0;
        int               tot_rec = 0;

        dtulag  = new Timestamp(System.currentTimeMillis( ));

        l_query   = "";
        l_query  += " update pgmr.atk_messaggio \n";
        l_query  += "    set dtproc = null      \n";
        l_query  += "      , nota   = null      \n";
        l_query  += "      , profil = ?         \n";
        l_query  += "      , dtulag = ?         \n";
        l_query  += "  where tkmsg  = ?         \n";


        pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;
        pstm.setString   (ind, profil); ind += 1;
        pstm.setTimestamp(ind, dtulag); ind += 1;
        pstm.setLong     (ind, tkmsg ); ind += 1;

        tot_rec = pstm.executeUpdate();

        pstm.close();

        return tot_rec;
    }



    public ResultSet getSpedito( long id_gest_object) throws Exception {

        PreparedStatement pstm = null;
        ResultSet         rs = null;
        int               ind = 0;
        int               tot_rec = 0;

        l_query  = "";
        l_query += " select count(a.tkmsg) as nrsped \n";
        l_query += "   from pgmr.atk_msgfile a       \n";
        l_query += "      , pgmr.atk_messaggio b     \n";
        l_query += "  where b.dtsped is not null     \n";
        l_query += "    and a.tkmsg = b.tkmsg        \n";
        l_query += "    and a.id_object = ?          \n";

        pstm = setQuery( l_query ) ;

        ind = 1;
        pstm.setLong(ind, id_gest_object); ind += 1;

        rs = pstm.executeQuery();

        return rs;
    }



    public ResultSet getSpeditoVar( long id_gest_object) throws Exception {

        PreparedStatement pstm = null;
        ResultSet         rs = null;
        int               ind = 0;
        int               tot_rec = 0;

        l_query  = "";
        l_query += " select count(a.tkmsg) as nrsped                        \n";
        l_query += "   from pgmr.atk_msgfile a                              \n";
        l_query += "      , pgmr.atk_messaggio b                            \n";
        l_query += "  where b.dtsped is not null                            \n";
        l_query += "    and a.tkmsg = b.tkmsg                               \n";
        l_query += "    and a.id_object = ( select id                       \n";
        l_query += "                          from pgmr.afs_gest_object     \n";
        l_query += "                         where id_docvar = ?          ) \n";

        pstm = setQuery( l_query ) ;

        ind = 1;
        pstm.setLong(ind, id_gest_object); ind += 1;

        rs = pstm.executeQuery();

        return rs;
    }




    public ResultSet getDefault( long id_gest_object) throws Exception {
        
        PreparedStatement pstm = null;
        ResultSet         rs = null;
        int               ind = 0;
        int               tot_rec = 0;


        l_query   = "";
        l_query  += " select *                    \n";
        l_query  += "   from pgmr.afs_maildef     \n";
        l_query  += "  where id_gest_object = ?   \n";

        pstm = setQuery( l_query ) ;

        ind = 1;
        pstm.setLong(ind, id_gest_object); ind += 1;

        rs = pstm.executeQuery();

        return rs;
    }





    public ResultSet getMailOfDoc(long id_object) throws Exception {
        PreparedStatement pstm = null;
        ResultSet         rs = null;
        int               ind = 0;
        int               tot_rec = 0;


        l_query   = "";
        l_query  += " select a.*                        \n";
        l_query  += "   from pgmr.atk_messaggio a       \n";
        l_query  += "      , pgmr.atk_msgfile   b       \n";
        l_query  += "  where a.tkmsg = b.tkmsg          \n";
        l_query  += "    and b.id_object = ?            \n";
        l_query  += "  order by a.dtsped desc           \n";
        l_query  += "         , a.tkmsg  desc           \n";

        pstm = setQuery( l_query ) ;

        ind = 1;
        pstm.setLong  (ind, id_object); ind += 1;

        rs = pstm.executeQuery();

        return rs;
    }




    public ResultSet getMailOfDocRif(long id_object) throws Exception {
        
        PreparedStatement pstm = null;
        ResultSet         rs = null;
        int               ind = 0;
        int               tot_rec = 0;


        l_query   = "";
        l_query  += " select *                        \n";
        l_query  += "   from pgmr.atk_messaggio       \n";
        l_query  += "  where id_object = ?            \n";
        l_query  += "  order by dtsped desc           \n";
        l_query  += "         , tkmsg  desc           \n";

        pstm = setQuery( l_query ) ;

        ind = 1;
        pstm.setLong  (ind, id_object); ind += 1;

        rs = pstm.executeQuery();

        return rs;
    }



    public ResultSet getMailOfMittente(String mittente) throws Exception {
        PreparedStatement pstm = null;
        ResultSet         rs = null;
        int               ind = 0;
        int               tot_rec = 0;


        l_query   = "";
        l_query  += " select *                          \n";
        l_query  += "   from pgmr.atk_messaggio         \n";
        l_query  += "  where trim(lower(mittente)) = ?  \n";
        l_query  += "  order by dtsped desc             \n";
        l_query  += "         , tkmsg  desc             \n";

        pstm = setQuery( l_query ) ;

        ind = 1;
        pstm.setString(ind, mittente ); ind += 1;

        rs = pstm.executeQuery();

        return rs;
    }








    public int executeInsert( long      tkmsg
                            , String    host
                            , String    username
                            , String    password
                            , String    oggetto
                            , String    mittente
                            , String    contenuto
                            , String    fgbloccamail
                            , long      id_object_t
                            , Timestamp dtricsped
                            , long      tkml
                                                    ) throws Exception {

        return this.executeInsert(tkmsg, host, username, password, oggetto, mittente, contenuto, fgbloccamail, id_object_t, dtricsped, tkml, 0);

    }

    public int executeInsert( long      tkmsg    
                            , String    host     
                            , String    username 
                            , String    password 
                            , String    oggetto  
                            , String    mittente 
                            , String    contenuto
                            , String    fgbloccamail
                            , long      id_object_t
                            , Timestamp dtricsped
                            , long      tkml
                            , long      tkordi
                                                    ) throws Exception {

        PreparedStatement pstm = null;
        ResultSet         rs = null;
        int               ind = 0;
        int               tot_rec = 0;


        this.tkmsg = 0;

        tkmsg = this.getNewId();

        if (tkmsg <= 0){
            
            throw new Exception("Errore tkmsg = "+tkmsg);
        }

        this.tkmsg = tkmsg;

        if (host.equals("")      ) host      = null;
        if (username.equals("")  ) username  = null;
        if (password.equals("")  ) password  = null;
        if (oggetto.equals("")   ) oggetto   = null;
        if (mittente.equals("")  ) mittente  = null;
//         if (contenuto.equals("") ) contenuto = null;

        String nota = null;

        if (fgbloccamail.equals("P")) nota = "*** Mail Prenotata.";

        nota = null;

		l_query   = "";
		l_query  += " insert into pgmr.atk_messaggio (  \n";
		l_query  += "                    tkmsg          \n";
		l_query  += "                  , host           \n";
		l_query  += "                  , username       \n";
		l_query  += "                  , password       \n";
		l_query  += "                  , oggetto        \n";
		l_query  += "                  , mittente       \n";
//		l_query  += "                  , contenuto      \n";
		l_query  += "                  , cdazie         \n";
		l_query  += "                  , cddipa         \n";
		l_query  += "                  , profil         \n";
		l_query  += "                  , dtinse         \n";
		l_query  += "                  , dtulag         \n";
		l_query  += "                  , nota           \n";
		l_query  += "                  , dtricsped      \n";
		l_query  += "                  , id_object      \n";
		l_query  += "                  , tkml           \n";
		l_query  += "                  , tkordi         \n";
		l_query  += "                )values (          \n";
		l_query  += "                    ?,?,?,?,?      \n";
		l_query  += "                  , ?,?,?,?,?      \n";
		l_query  += "                  , ?,?,?,?,?      \n";
		l_query  += "                  , ?              \n";
        l_query  += "                )                  \n";


        pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;
        pstm.setLong     ( ind,   tkmsg    ); ind += 1;
        pstm.setString   ( ind,   host     ); ind += 1;
        pstm.setString   ( ind,   username ); ind += 1;
        pstm.setString   ( ind,   password ); ind += 1;
        pstm.setString   ( ind,   oggetto  ); ind += 1;
        pstm.setString   ( ind,   mittente ); ind += 1;
//        pstm.setString   ( ind,   contenuto); ind += 1;
        pstm.setString   ( ind,   cdazie   ); ind += 1;
        pstm.setString   ( ind,   cddipa   ); ind += 1;
        pstm.setString   ( ind,   profil   ); ind += 1;
        pstm.setTimestamp( ind,   dtinse   ); ind += 1;
        pstm.setTimestamp( ind,   dtulag   ); ind += 1;
        pstm.setString   ( ind,   nota     ); ind += 1;
        pstm.setTimestamp( ind,   dtricsped); ind += 1;
        if (id_object_t > 0){
            pstm.setLong ( ind,   id_object_t); ind += 1;
        }else { 
            pstm.setNull (ind, java.sql.Types.NUMERIC); ind += 1;
        }
        if (tkml > 0){
            pstm.setLong ( ind,   tkml); ind += 1;
        }else { 
            pstm.setNull (ind, java.sql.Types.NUMERIC); ind += 1;
        }
        if (tkordi > 0){
            pstm.setLong ( ind,   tkordi); ind += 1;
        }else {
            pstm.setNull (ind, java.sql.Types.NUMERIC); ind += 1;
        }

        tot_rec += pstm.executeUpdate();

        pstm.close();

        setContenuto(tkmsg, contenuto);


        m_connection.commit();


        return tot_rec;
    }


    public int inviaPrenot(long id_gest_object) throws Exception {
        PreparedStatement pstm = null;
        ResultSet         rs = null;
        int               ind = 0;
        int               tot_rec = 0;

        String lb_stato = "";

        // testo se respinto

        l_query  = "";
        l_query += " select *                    \n";
        l_query += "   from pgmr.afs_firma       \n";
        l_query += "  where id_gest_object = ?   \n";
        l_query += "  order by posi              \n";


        pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;
        pstm.setLong  (ind, id_gest_object); ind += 1;

        rs = pstm.executeQuery();

        int posi                = 0;
        int posi_my             = 0;
        int posi_prossima_firma = 0;
        int posi_sospeso        = 0;
        int posi_respinto       = 0;

        while (rs!=null && rs.next()){
            String flfirma = "";
            Timestamp dtfirma = null;

            posi += 1;
            if (rs.getObject("flfirma")!=null) flfirma = rs.getString("flfirma");
            if (rs.getObject("dtfirma")!=null) dtfirma = rs.getTimestamp("dtfirma");

            if (flfirma.equals("") && posi_prossima_firma == 0) posi_prossima_firma = posi;
            if (flfirma.equals("R") && posi_respinto == 0)      posi_respinto = posi;
            if (flfirma.equals("S") && posi_sospeso == 0)       posi_sospeso = posi;
        }

        pstm.close();

        if (posi_respinto >0){

            lb_stato = "RESPINTO";

        }else if (posi_sospeso > 0){

            lb_stato = "SOSPESO";

        }else {

            if (posi_prossima_firma <= 0){

                lb_stato = "WORK-FLOW_COMPLETATO";

            }else {
                lb_stato = "WORK-FLOW_IN_PROGRESS";

            }
        }


        if (lb_stato.equals("WORK-FLOW_COMPLETATO")){

            l_query  = "";
            l_query += " update pgmr.atk_messaggio                  \n";
            l_query += "    set dtricsped = "+sysdate+"       \n";
            l_query += "      , nota = null                         \n";
            l_query += "  where nota = '*** Mail Prenotata.'        \n";
            l_query += "    and tkmsg in ( select tkmsg             \n";
            l_query += "                     from pgmr.atk_msgfile  \n";
            l_query += "                    where id_object = ?     \n";
            l_query += "                     )                      \n";
            l_query += "    and dtproc is null                      \n";
            l_query += "    and dtsped is null                      \n";

            pstm = m_connection.prepareStatement( l_query ) ;

            ind = 1;
            pstm.setLong(ind, id_gest_object); ind += 1;

            tot_rec = pstm.executeUpdate();

            pstm.close();

            // documento variazione

            l_query  = "";
            l_query += " update pgmr.atk_messaggio                  \n";
            l_query += "    set dtricsped = "+sysdate+"       \n";
            l_query += "      , nota = null                         \n";
            l_query += "  where nota = '*** Mail Prenotata.'        \n";
            l_query += "    and tkmsg in ( select tkmsg             \n";
            l_query += "                     from pgmr.atk_msgfile  \n";
            l_query += "                    where id_object = (     \n";
            l_query += "              select id                     \n";
            l_query += "                from pgmr.afs_gest_object   \n";
            l_query += "               where id_docvar = ?        ) \n";
            l_query += "                     )                      \n";
            l_query += "    and dtproc is null                      \n";
            l_query += "    and dtsped is null                      \n";

            pstm = m_connection.prepareStatement( l_query ) ;

            ind = 1;
            pstm.setLong(ind, id_gest_object); ind += 1;

            tot_rec += pstm.executeUpdate();

            pstm.close();

        }else if (lb_stato.equals("RESPINTO")){

            l_query  = "";
            l_query += " update pgmr.atk_messaggio                      \n";
            l_query += "    set dtproc = "+sysdate+"              \n";
            l_query += "      , nota = '*** Mail Prenotata. RESPINTA'   \n";
            l_query += "  where nota = '*** Mail Prenotata.'            \n";
            l_query += "    and tkmsg in ( select tkmsg                 \n";
            l_query += "                     from pgmr.atk_msgfile      \n";
            l_query += "                    where id_object = ?         \n";
            l_query += "                     )                          \n";
            l_query += "    and dtproc is null                          \n";
            l_query += "    and dtsped is null                          \n";

            pstm = m_connection.prepareStatement( l_query ) ;

            ind = 1;
            pstm.setLong(ind, id_gest_object); ind += 1;

            tot_rec = pstm.executeUpdate();

            pstm.close();

            l_query  = "";
            l_query += " update pgmr.atk_messaggio                      \n";
            l_query += "    set dtproc = "+sysdate+"              \n";
            l_query += "      , nota = '*** Mail Prenotata. RESPINTA'   \n";
            l_query += "  where nota = '*** Mail Prenotata.'            \n";
            l_query += "    and tkmsg in ( select tkmsg                 \n";
            l_query += "                     from pgmr.atk_msgfile      \n";
            l_query += "                    where id_object = (         \n";
            l_query += "              select id                         \n";
            l_query += "                from pgmr.afs_gest_object       \n";
            l_query += "               where id_docvar = ?        )     \n";
            l_query += "                     )                          \n";
            l_query += "    and dtproc is null                          \n";
            l_query += "    and dtsped is null                          \n";

            pstm = m_connection.prepareStatement( l_query ) ;

            ind = 1;
            pstm.setLong(ind, id_gest_object); ind += 1;

            tot_rec += pstm.executeUpdate();

            pstm.close();

            tot_rec = tot_rec * -1;

        }

        m_connection.commit();

        return tot_rec;
    }







    public int executeUpdate( long      tkmsg
                            , String    host
                            , String    username 
                            , String    password 
                            , String    oggetto  
                            , String    mittente 
                            , String    contenuto
                            , String    fgbloccamail
                            , long      id_object_t
                            , Timestamp dtricsped
                                                     ) throws Exception {
        PreparedStatement pstm = null;
        ResultSet         rs = null;
        int               ind = 0;
        int               tot_rec = 0;



        mittente = mittente.trim();
        mittente = mittente.toLowerCase();

        if (host.equals("")      ) host      = null;
        if (username.equals("")  ) username  = null;
        if (password.equals("")  ) password  = null;
        if (oggetto.equals("")   ) oggetto   = null;
        if (mittente.equals("")  ) mittente  = null;
//        if (contenuto.equals("") ) contenuto = null;

        String nota = null;

        // imposto la nota sempre a null

        l_query  = "";
        l_query += " update pgmr.atk_messaggio \n";
        l_query += "    set cdazie    = ?      \n";
        l_query += "      , cddipa    = ?      \n";
        l_query += "      , profil    = ?      \n";
        l_query += "      , dtulag    = ?      \n";
        l_query += "      , host      = ?      \n";
        l_query += "      , username  = ?      \n";
        l_query += "      , password  = ?      \n";
        l_query += "      , oggetto   = ?      \n";
        l_query += "      , mittente  = ?      \n";
//        l_query += "      , contenuto = ?      \n";
        l_query += "      , dtricsped = ?      \n";
        l_query += "      , dtsped    = null   \n";
        l_query += "      , dtproc    = null   \n";
        l_query += "      , nota      = null   \n";
        l_query += "      , id_object = ?      \n";
        l_query += "  where tkmsg     = ?      \n";

        pstm = m_connection.prepareStatement( l_query ) ;
        ind  = 1;
        // campi di audit sempre presenti


        pstm.setString   (ind, cdazie   );   ind += 1;
        pstm.setString   (ind, cddipa   );   ind += 1;
        pstm.setString   (ind, profil   );   ind += 1;
        pstm.setTimestamp(ind, dtulag   );   ind += 1;

        pstm.setString   (ind, host     );   ind += 1;
        pstm.setString   (ind, username );   ind += 1;
        pstm.setString   (ind, password );   ind += 1;
        pstm.setString   (ind, oggetto  );   ind += 1;
        pstm.setString   (ind, mittente );   ind += 1;
//        pstm.setString   (ind, contenuto);   ind += 1;
        pstm.setTimestamp(ind, dtricsped);   ind += 1;
        if (id_object_t>0){
            pstm.setLong (ind, id_object_t); ind += 1;
        }else {
            pstm.setNull (ind, java.sql.Types.NUMERIC); ind += 1;
        }
        pstm.setLong     (ind, tkmsg    );   ind += 1;

        tot_rec += pstm.executeUpdate();

        pstm.close();

        setContenuto(tkmsg, contenuto);

        m_connection.commit();

        return tot_rec;

    }


    public int executeDelete(long tkmsg) throws Exception {
        PreparedStatement pstm = null;
        ResultSet         rs = null;
        int               ind = 0;
        int               tot_rec = 0;

        l_query  = " delete from pgmr.atk_msgfile where tkmsg = ? ";
        pstm = m_connection.prepareStatement( l_query ) ;
        pstm.setLong     (1, tkmsg);
        
        tot_rec += pstm.executeUpdate();

        pstm.close();

        l_query  = " delete from pgmr.atk_msgdest where tkmsg = ? ";
        pstm = m_connection.prepareStatement( l_query ) ;
        pstm.setLong     (1, tkmsg);
        
        tot_rec += pstm.executeUpdate();

        pstm.close();
        
        l_query  = " delete from pgmr.atk_messaggio where tkmsg = ? ";
        pstm = m_connection.prepareStatement( l_query ) ;
        
        pstm.setLong     (1, tkmsg);

        tot_rec += pstm.executeUpdate();

        pstm.close();

        m_connection.commit();


        return tot_rec;
    }










    /***

        DESTINATARI

    ***/

    public ResultSet getDest( long tkmsg, String tipodest) throws Exception {
        PreparedStatement pstm = null;
        ResultSet         rs = null;
        int               ind = 0;
        int               tot_rec = 0;


        l_query   = "";
        l_query  += " select *                 \n";
        l_query  += "   from pgmr.atk_msgdest  \n";
        l_query  += "  where tkmsg = ?         \n";
        if (!tipodest.equals("")){
        l_query  += "    and tipodest = ?         \n";
        }
        l_query  += "  order by dtinse         \n";

        pstm = setQuery( l_query ) ;

        ind = 1;
        pstm.setLong  (ind, tkmsg); ind += 1;
        if (!tipodest.equals("")){
        pstm.setString(ind, tipodest); ind += 1;
        }
        rs = pstm.executeQuery();

        return rs;
    }



    public int setDest( long     tkmsg
                      , String   dest_to
                      , String   dest_cc
                      , String   dest_bcc
                                        ) throws Exception {

        PreparedStatement pstm     = null;
        ResultSet         rs       = null;
        int               ind      = 0;
        int               tot_rec  = 0;


        String[] arr_dest = null;

        arr_dest = destToArray(dest_to);

        if (arr_dest !=null && arr_dest.length > 0){
            
            tot_rec += setDest(tkmsg, arr_dest, this.TIPODEST_TO, 0);
        }


        arr_dest = destToArray(dest_cc);

        if (arr_dest !=null && arr_dest.length > 0){
            
            tot_rec += setDest(tkmsg, arr_dest, this.TIPODEST_CC, 0);
        }


        arr_dest = destToArray(dest_bcc);

        if (arr_dest !=null && arr_dest.length > 0){
            
            tot_rec += setDest(tkmsg, arr_dest, this.TIPODEST_BCC, 0);
        }


        return tot_rec;
    }




    public int setDest( long     tkmsg
                      , String[] dest
                      , String   tipodest
                      , long     tkmlp
                                        ) throws Exception {

        PreparedStatement pstm     = null;
        PreparedStatement pstm_sql = null;
        PreparedStatement pstm_upd = null;
        PreparedStatement pstm_ins = null;
        ResultSet         rs       = null;
        int               ind      = 0;
        int               tot_rec  = 0;


		l_query   = "";
		l_query  += " select count(1)              \n";
		l_query  += "   from pgmr.atk_msgdest  \n";
		l_query  += "  where tkmsg = ?             \n";
		l_query  += "    and dest  = ?             \n";

        pstm_sql = m_connection.prepareStatement( l_query ) ;
        



		l_query   = "";
		l_query  += " insert into pgmr.atk_msgdest (  \n";
		l_query  += "             tkmsg                   \n";
		l_query  += "           , dest                    \n";
		l_query  += "           , tipodest                \n";
		l_query  += "           , cdazie                  \n";
		l_query  += "           , cddipa                  \n";
		l_query  += "           , profil                  \n";
		l_query  += "           , dtinse                  \n";
		l_query  += "           , dtulag                  \n";
		l_query  += "           , tkmlp                   \n";
		l_query  += "           )values(                  \n";
		l_query  += "             ?,?,?,?,?               \n";
		l_query  += "           , ?,?,?,?                 \n";
		l_query  += "           )                         \n";

        pstm_ins = m_connection.prepareStatement( l_query ) ;



		l_query   = "";
		l_query  += " update pgmr.atk_msgdest     \n";
		l_query  += "    set tipodest = ?             \n";
		l_query  += "      , profil   = ?             \n";
		l_query  += "      , dtulag   = ?             \n";
		l_query  += "  where tkmsg    = ?             \n";
		l_query  += "    and dest     = ?             \n";

        pstm_upd = m_connection.prepareStatement( l_query ) ;


        System.out.println("l_query = " + l_query);


        for (int i=0 ; dest!=null && i<dest.length; i++){

            ind = 1;
            pstm_sql.setLong     ( ind, tkmsg   ); ind += 1;
            pstm_sql.setString   ( ind, dest[i] ); ind += 1;
            
            rs = pstm_sql.executeQuery();

            if (rs !=null && rs.next() && rs.getObject(1) != null && rs.getInt(1)>0){
                
                
                ind = 1;
                pstm_upd.setString   ( ind,   tipodest ); ind += 1;
                pstm_upd.setString   ( ind,   profil   ); ind += 1;
                pstm_upd.setTimestamp( ind,   dtulag   ); ind += 1;

                pstm_upd.setLong     ( ind,   tkmsg    ); ind += 1;
                pstm_upd.setString   ( ind,   dest[i]  ); ind += 1;
                

                tot_rec += pstm_upd.executeUpdate();

            }else {
                
                ind = 1;
                pstm_ins.setLong     ( ind,   tkmsg    ); ind += 1;
                pstm_ins.setString   ( ind,   dest[i]  ); ind += 1;
                pstm_ins.setString   ( ind,   tipodest ); ind += 1;
                pstm_ins.setString   ( ind,   cdazie   ); ind += 1;
                pstm_ins.setString   ( ind,   cddipa   ); ind += 1;
                pstm_ins.setString   ( ind,   profil   ); ind += 1;
                pstm_ins.setTimestamp( ind,   dtinse   ); ind += 1;
                pstm_ins.setTimestamp( ind,   dtulag   ); ind += 1;
                if (tkmlp > 0){
                    pstm_ins.setLong ( ind,   tkmlp); ind += 1;
                }else { 
                    pstm_ins.setNull (ind, java.sql.Types.NUMERIC); ind += 1;
                }
    
                tot_rec += pstm_ins.executeUpdate();
            }

        }

        pstm_sql.close();
        pstm_upd.close();
        pstm_ins.close();

        m_connection.commit();

        return tot_rec;

    }



    public int executeDeleteDest(long tkmsg) throws Exception {
        PreparedStatement pstm = null;
        ResultSet         rs = null;
        int               ind = 0;
        int               tot_rec = 0;

        l_query  = " delete from pgmr.atk_msgdest where tkmsg = ? ";


        pstm = m_connection.prepareStatement( l_query ) ;

        pstm.setLong     (1, tkmsg);

        tot_rec += pstm.executeUpdate();


        pstm.close();

        m_connection.commit();

        return tot_rec;
    }







    /***

        ATTACH

    ***/

    public ResultSet getAttach( long tkmsg) throws Exception {
        PreparedStatement pstm = null;
        ResultSet         rs = null;
        int               ind = 0;
        int               tot_rec = 0;


        l_query   = "";
        l_query  += " select *                 \n";
        l_query  += "   from pgmr.atk_msgfile  \n";
        l_query  += "  where tkmsg = ?         \n";
        l_query  += "  order by posi           \n";

        pstm = setQuery( l_query ) ;

        ind = 1;
        pstm.setLong  (ind, tkmsg); ind += 1;

        rs = pstm.executeQuery();

        return rs;
    }



    public int setAttach( long   tkmsg
                        , String pathfile
                        , String nomefile
                        , long   id_object     
                        , String fgconv_pdf
                                                    ) throws Exception {
                                                        
        PreparedStatement pstm = null;
        ResultSet         rs = null;
        int               ind = 0;
        int               tot_rec = 0;



        return setAttach(tkmsg, new String[]{pathfile}, new String[]{nomefile}, new long[]{id_object}, new String[]{fgconv_pdf});
    }




    public int setConv_pdf( long   tkmsg
                          , String pathfile
                          , String pathconv_pdf
                                                    ) throws Exception {
                                                        
        PreparedStatement pstm = null;
        ResultSet         rs = null;
        int               ind = 0;
        int               tot_rec = 0;


        l_query   = "";
        l_query  += " update pgmr.atk_msgfile     \n";
        l_query  += "    set pathconv_pdf = ?         \n";
        l_query  += " where pathfile || nomefile = ?  \n";
        l_query  += "   and tkmsg = ?                 \n";

        pstm = m_connection.prepareStatement( l_query ) ;


        ind = 1;

        pstm.setString   ( ind, pathconv_pdf  ); ind += 1;

        pstm.setString   ( ind, pathfile  ); ind += 1;
        pstm.setLong     ( ind, tkmsg     ); ind += 1;

        tot_rec = pstm.executeUpdate();

        pstm.close();

        m_connection.commit();

        return tot_rec;

    }

    public int setConv_pdf( long   tkmsg
                          , long   id_object
                          , String pathconv_pdf
                                                    ) throws Exception {
                                                        
        PreparedStatement pstm = null;
        ResultSet         rs = null;
        int               ind = 0;
        int               tot_rec = 0;


        l_query   = "";
        l_query  += " update pgmr.atk_msgfile     \n";
        l_query  += "    set pathconv_pdf = ?         \n";
        l_query  += " where id_object = ?             \n";
        l_query  += "   and tkmsg = ?                 \n";

        pstm = m_connection.prepareStatement( l_query ) ;


        ind = 1;

        pstm.setString   ( ind, pathconv_pdf  ); ind += 1;

        pstm.setLong     ( ind, id_object ); ind += 1;
        pstm.setLong     ( ind, tkmsg     ); ind += 1;

        tot_rec = pstm.executeUpdate();

        pstm.close();

        m_connection.commit();

        return tot_rec;

    }



    public int setAttach( long      tkmsg
                        , String[]  pathfile
                        , String[]  nomefile
                        , long  []  id_object     
                        , String[]  fgconv_pdf
                                                    ) throws Exception {

        PreparedStatement pstm = null;
        ResultSet         rs = null;
        int               ind = 0;
        int               tot_rec = 0;


        l_query   = "";
        l_query  += " insert into pgmr.atk_msgfile (  \n";
        l_query  += "                   tkmsg         \n";
        l_query  += "                 , nomefile      \n";
        l_query  += "                 , pathfile      \n";
        l_query  += "                 , id_object     \n";
        l_query  += "                 , fgconv_pdf    \n";
        l_query  += "                 , cdazie        \n";
        l_query  += "                 , cddipa        \n";
        l_query  += "                 , profil        \n";
        l_query  += "                 , dtinse        \n";
        l_query  += "                 , dtulag        \n";
        l_query  += "                 , posi          \n";
        l_query  += "             )values(            \n";
        l_query  += "                   ?,?,?,?,?     \n";
        l_query  += "                 , ?,?,?,?,?     \n";
        l_query  += "                 , ?             \n";
        l_query  += "             )                   \n";

        pstm = m_connection.prepareStatement( l_query ) ;


        for (int i=0 ; nomefile!=null && i<nomefile.length; i++){

            dtinse = new Timestamp(System.currentTimeMillis( ));
            dtulag = dtinse;


            if (!fgconv_pdf[i].equals("S")) fgconv_pdf[i] = "N";

            ind = 1;
            pstm.setLong     ( ind, tkmsg        ); ind += 1;
            pstm.setString   ( ind, nomefile [i] ); ind += 1;
            pstm.setString   ( ind, pathfile [i] ); ind += 1;
            pstm.setLong     ( ind, id_object[i] ); ind += 1;
            pstm.setString   ( ind, fgconv_pdf[i]); ind += 1;
            pstm.setString   ( ind, cdazie       ); ind += 1;
            pstm.setString   ( ind, cddipa       ); ind += 1;
            pstm.setString   ( ind, profil       ); ind += 1;
            pstm.setTimestamp( ind, dtinse       ); ind += 1;
            pstm.setTimestamp( ind, dtulag       ); ind += 1;
            pstm.setInt      ( ind, (i+1)        ); ind += 1;

            tot_rec += pstm.executeUpdate();
        }

        m_connection.commit();

        return tot_rec;

    }



    public int executeDeleteAttach(long tkmsg) throws Exception {
        PreparedStatement pstm = null;
        ResultSet         rs = null;
        int               ind = 0;
        int               tot_rec = 0;

        l_query  = " delete from pgmr.atk_msgfile where tkmsg = ? ";
        pstm = m_connection.prepareStatement( l_query ) ;
        pstm.setLong     (1, tkmsg);

        tot_rec += pstm.executeUpdate();

        m_connection.commit();


        return tot_rec;
    }




    /***

        UTIL

    ***/

    public String[] destToArray(String dest) throws Exception {


        Vector   v_dest = new Vector();
        String[] a_dest = null;
        String   app_dest = "";

        dest = dest.replace(',',';');

        StringTokenizer stringTokenizer = new StringTokenizer( dest, ";");

        while (stringTokenizer.hasMoreTokens()) {
		    app_dest = "";
            app_dest = stringTokenizer.nextToken();

            app_dest = app_dest.toLowerCase();
            app_dest = app_dest.trim();

            if (app_dest !=null && !app_dest.equals("")){
                v_dest.add(app_dest);
            }
	    }

        if (v_dest.size()<=0) return null;

        a_dest = new String[v_dest.size()];


        for (int i=0; i<v_dest.size(); i++){
            a_dest[i] = (String) v_dest.elementAt(i);
        }

        return a_dest;

    }








    public long getNewId() throws Exception {
        PreparedStatement pstm = null;
        ResultSet         rs = null;
        int               ind = 0;
        int               tot_rec = 0;


        F_tabkey f_tabkey = new F_tabkey();
        setProfilo((Atk_sql) f_tabkey );

        long ll_tk = f_tabkey.getTabkey_gest( "atk_messaggio" );

        return ll_tk;


    }


    public ResultSet getUserEmail( String portal_user ) throws Exception {

        return getUserEmail( ""
                           , ""
                           , "USR"
                           , ""
                           , portal_user
                           , "email"
                           );

    }
    public ResultSet getUserEmail( String member_type
                                 , String portal_user
                                        ) throws Exception {

        return getUserEmail( ""
                           , ""
                           , member_type
                           , ""
                           , portal_user
                           , "email"
                           );

    }
    public ResultSet getUserEmail( String email
                                 , String descr
                                 , String member_type
                                 , String cdqualifica
                                 , String portal_user
                                 , String order_by

                                        ) throws Exception {
        PreparedStatement pstm = null;
        ResultSet         rs = null;
        int               ind = 0;
        int               tot_rec = 0;
        String[] arr_email = null;

        email = email.trim().toLowerCase();
        descr = descr.trim().toLowerCase();
        if (order_by.equals("")) order_by = "email";

        java.util.StringTokenizer stringTokenizer = new java.util.StringTokenizer( email, ";");

        if (stringTokenizer.countTokens( )>0){
            arr_email = new String[stringTokenizer.countTokens( )];
        }

    	int numToken = 0;
    	while (stringTokenizer.hasMoreTokens()) {

    		arr_email[numToken] = stringTokenizer.nextToken();
            arr_email[numToken] = arr_email[numToken].trim().toLowerCase();
    		numToken += 1;
    	}

        l_query   = "";
        l_query  += " select *                 \n";
        l_query  += "   from pgmr.portal_mail  \n";
        l_query  += "  where 1 = 1             \n";
        for (int i=0; arr_email!=null && i<arr_email.length; i++) {
            if (!arr_email[i].equals("")){
                if (i==0){
                l_query  += " and ( lower(email) like '%"+arr_email[i]+"%' \n";
                }else {
                l_query  += " or lower(email) like '%"+arr_email[i]+"%' \n";
                }
            }
        }
        if (arr_email!=null && arr_email.length>0){
            l_query  += "    )   \n";
        }
        if (!descr.equals("")){
        l_query  += "    and lower(descr) like '%"+descr+"%'   \n";
        }
        if (!member_type.equals("")){
        l_query  += "    and member_type = ?            \n";
        }
        if (!cdqualifica.equals("")){
        l_query  += "    and cdqualifica = ?            \n";
        }
        if (!portal_user.equals("")){
        l_query  += "    and portal_user = ?            \n";
        }
        l_query  += "  order by "+order_by+"   \n";


        pstm = setQuery( l_query ) ;

        ind = 1;
        if (!member_type.equals("")){
        pstm.setString(ind, member_type); ind += 1;
        }
        if (!cdqualifica.equals("")){
        pstm.setString(ind, cdqualifica); ind += 1;
        }
        if (!portal_user.equals("")){
        pstm.setString(ind, portal_user); ind += 1;
        }

        rs = pstm.executeQuery();

        return rs;
    }


    public ResultSet getUserEmail( String[] id
                                 , String order_by

                                        ) throws Exception {
        PreparedStatement pstm = null;
        ResultSet         rs = null;
        int               ind = 0;
        int               tot_rec = 0;

        if(id == null || id.length <= 0) return null;


        l_query   = "";
        l_query  += " select *                 \n";
        l_query  += "   from pgmr.portal_mail  \n";
        l_query  += "  where 1 = 1             \n";
        l_query  += "  and id in ( \n";
        for (int i=0; i<id.length; i++){
            if (i==0){
                l_query  += id[i];
            }else {
                l_query  += ","+id[i];
            }
        }
        l_query  += "            ) \n";
        l_query  += "  order by "+order_by+"   \n";


        pstm = setQuery( l_query ) ;


        rs = pstm.executeQuery();

        return rs;
    }


    public ResultSet getEmail( String tipref, String codref) throws Exception {
        PreparedStatement pstm = null;
        ResultSet         rs = null;
        int               ind = 0;
        int               tot_rec = 0;

        tipref = tipref.trim();
        tipref = tipref.toUpperCase();
        codref = codref.trim();
        codref = codref.toUpperCase();

        l_query   = "";
        l_query  += " select *                            \n";
        l_query  += "   from prod.qmail00                 \n";
        l_query  += "  where trim(upper(tipref)) = ?      \n";
        l_query  += "    and trim(upper(codref)) = ?      \n";

        pstm = setQuery( l_query ) ;

        ind = 1;
        pstm.setString(ind, tipref); ind += 1;
        pstm.setString(ind, codref); ind += 1;

        rs = pstm.executeQuery();

        return rs;
    }



    public ResultSet getQualifica(String cdqualifica) throws Exception {
        PreparedStatement pstm = null;
        ResultSet         rs = null;
        int               ind = 0;
        int               tot_rec = 0;

        l_query   = "";
        l_query  += " select *                     \n";
        l_query  += "   from pgmr.portal_qualifica \n";
        l_query  += "  where dtdel is null         \n";
        if (!cdqualifica.equals("")){
        l_query  += "    and cdqualifica = ?       \n";
        }
        l_query  += "  order by descr              \n";

        pstm = setQuery( l_query ) ;

        if (!cdqualifica.equals("")){
        pstm.setString(1, cdqualifica);
        }

        rs = pstm.executeQuery();

        return rs;
    }


    public ResultSet getMemtype(String member_type) throws Exception {
        PreparedStatement pstm = null;
        ResultSet         rs = null;
        int               ind = 0;
        int               tot_rec = 0;

        l_query  += " select *                     \n";
        l_query  += "   from pgmr.portal_memtype   \n";
        l_query  += "  where dtdel is null         \n";
        if (!member_type.equals("")){
            l_query  += "  and member_type = ?     \n";
        }
        l_query  += "  order by descr              \n";

        pstm = setQuery( l_query ) ;

        if (!member_type.equals("")){
        pstm.setString(1, member_type);
        }

        rs = pstm.executeQuery();

        return rs;
    }





    /***


    */

    public String  getContenuto (long tkmsg ) throws Exception {
        
    	int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        String ls_ = "";

        tot_rec = 0;

        l_query  = "";
        l_query += " select contenuto         \n";
        l_query += "   from pgmr.atk_msgcont  \n";
        l_query += "  where tkmsg = "+tkmsg+" \n";
        l_query += "  order by nrposi         \n";

        pstm = m_connection.prepareStatement(l_query);

        ind = 1;

        rs = pstm.executeQuery();

        while (rs!=null && rs.next() && rs.getObject(1)!=null){
            
            tot_rec += 1;
            ls_ += rs.getString(1);

        }
        pstm.close();


        return ls_;

    }


    /***


    */

    public int setContenuto (long tkmsg, String contenuto ) throws Exception {
        
    	int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        Costanti_comm costanti_comm = new Costanti_comm();

        setProfilo((Atk_sql) costanti_comm);

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


            l_query  = "";
            l_query += " insert into pgmr.atk_msgcont (\n";
            l_query += "        tkmsg                 \n";
            l_query += "      , nrposi                \n";
            l_query += "      , contenuto             \n";
            l_query += "      , cdazie                \n";
            l_query += "      , cddipa                \n";
            l_query += "      , profil                \n";
            l_query += "      , dtinse                \n";
            l_query += "      , dtulag                \n";
            l_query += "      )values (               \n";
            l_query += "        ?,?,?,?,?             \n";
            l_query += "      , ?,?,?                 \n";
            l_query += "      )                       \n";

            pstm = m_connection.prepareStatement(l_query);

            ind = 1;
            pstm.setLong     (ind, tkmsg    );    ind += 1;
            pstm.setLong     (ind, nrposi   );    ind += 1;
            pstm.setString   (ind, ls_cont  );    ind += 1;
            pstm.setString   (ind, cdazie   );    ind += 1;
            pstm.setString   (ind, cddipa   );    ind += 1;
            pstm.setString   (ind, profil   );    ind += 1;
            pstm.setTimestamp(ind, oggi     );    ind += 1;
            pstm.setTimestamp(ind, oggi     );    ind += 1;

            tot_rec = pstm.executeUpdate();
            pstm.close();

            if (tot_rec != 1){
                throw new Exception("Err. INS pgmr.atk_msgcont");
            }

            
        }



        return tot_rec;

    }



    public long getNew_token( ) throws Exception{ 
        return getNewId();
    }










     // Properties

    public           long       tkmsg     = 0;
    

    public String TIPODEST_TO  = "D";
    public String TIPODEST_CC  = "C";
    public String TIPODEST_BCC = "B";



}
