package com.ateikon.structure;

import com.ateikon.common.Ep_msgmod_cont;
import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

/**********************/
//     NOTA:
//     modificare questa classe nel portale implica un allineamento della stessa classe in eprogen
//     e viceversa modificare questa classe nel eprogen implica un allineamento della stessa classe nel portale
//
/*********************/
/**
 *
 * @author leo
 * 
 * Gestione modelli mail: dato il token del modello e la lingua &egrave;
 * vengono caricati fli elementi principali della mail 
 * <ul>
 *      <li>Mittente</li>
 *      <li>Destinatari (List <Rec_ep_msgmod_dest>)</li>
 *      <li>Oggetto</li>
 *      <li>Contenuto</li>
 *      <li>Allegati (List  <Rec_ep_msgmod_file>)</li>
 * </ul>
 * 
 * <i> Nessuno di questi elementi e' Obbligatorio<>
 * 
 */
public class Str_msgmod extends Object {

    public Str_msgmod(){
 
        super();

        list_dest = new Vector();
        list_file = new Vector();
        fgreturn_rcpt_to = "N";
        VALORIZZA_DTRICSPED = "S";
    }
    
    public long tkmsgmod = 0;
    public String cdling = "";
    
    
    public String mittente  = "";
    public String oggetto   = "";
    public Vector <Rec_ep_msgmod_dest> list_dest  = null;

    public String contenuto = "";
    public String VALORIZZA_DTRICSPED = "S";
    
    public Vector <Rec_ep_msgmod_file> list_file  = null;
    
    /**
     * <b>Ricevuta di ritorno.</b><br/>
     * 
     * <i>Valori</i>:
     *  <ul> 
     *      <li>[S] = ricevuta di ritorno abilitata </li> 
     *      <li>[N] = ricevuta di ritorno NON abilitata </li> 
     *  </ul> 
     * <i>Default</i>: N
     * 
     */
    public String fgreturn_rcpt_to = "N";

    
    /**
     <b>outout: </b> token atk_messaggio
     <br />
     <i>Il seguente token e' opportunamente valorizzato dal metodo com.ateikon.function.F_msgmod.of_send_mail().</i>
    */
    public long tkmsg = 0;

    /**
     <b>parametro: </b> Nome file
     <br />
     <i>Il seguente nome file e' opportunamente valorizzato, questo valore viene usato per sostituire dei marcatori nel modello.</i>
    */
    public String par__file_filename = "";
    /**
     <b>parametro: </b> Scadenze, tabella scadenza
     <br />
     <i>Il seguente parametro viene caricato dal servizio Srv_scadenz.</i>
    */
    public String par__scadenze_tbl_scadenze = "";
    /**
     <b>parametro: </b> Scadenze, tabella scadenza dettaglio
     <br />
     <i>Il seguente parametro viene caricato dal servizio Srv_scadenz.</i>
    */
    public String par__scadenze_tbl_scadenze_dett = "";
    /**
     <b>parametro: </b> Scadenze, totale residuo delle scadenze inviate nella notifica
     <br />
     <i>Il seguente parametro viene caricato dal servizio Srv_scadenz.</i>
    */
    public String par__scadenze_ls_totale_residuo_notifica = "";
    /**
     <b>parametro: </b> Scadenze, codice responsabile notifica
     <br />
     <i>Il seguente parametro viene caricato dal servizio Srv_scadenz.</i>
    */
    public String par__scadenze_cdresp = "";
    
    
    
    /**
     * Inizializzazione dell'oggetto.<br/>
     * <br/>
     * <b color="red">La chiave NON viene inizializzata [tkmsgmod | cdling]</b>
     * <br/>
     * 
     * @return [1] = OK
     */
    public int of_init(){
        
        mittente = "";
        oggetto = "";
        list_dest = new Vector();
        contenuto = "";
        list_file = new Vector();
        
        fgreturn_rcpt_to = "N";

        return 1;
    }
    
    
    
    /**
     * Restituisce il <b>Contenuto HTML</b> della mail; qu&igrave occorre impostare eventuali Header/Fotter 
     * della mail.<br/>
     * <i>Come da indicazioni di Roberto, questi elemnti sono cablati e non modificabili dal cliente</i>.<br/>
     * <br/>
     * Questo contenuto Opportunamente rielaborato va salvato in <b>pgmr.atk_msgcont</b>
     * 
     * @return resituisce il contenuto HTML della mail.
     * @throws Exception 
     */

    public String of_getHTML ( String base_url, String ep_portal_url) throws Exception {
        
        Ep_msgmod_cont ep_msgmod_cont = new Ep_msgmod_cont();
        
        return ep_msgmod_cont.of_getHTML(contenuto, base_url, ep_portal_url);

    }
    
    public String of_stripHTML ( ) throws Exception {
        
        Ep_msgmod_cont ep_msgmod_cont = new Ep_msgmod_cont();
        
        return ep_msgmod_cont.of_stripHTML(contenuto);

    }
    
    
}
