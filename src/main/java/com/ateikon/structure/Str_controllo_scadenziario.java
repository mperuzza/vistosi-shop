package com.ateikon.structure;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;


public class Str_controllo_scadenziario extends Str_rep_base {

    public Str_controllo_scadenziario(){
 
        super();

    }

    public     String    ftpscad       = "";
    public     String    fgscaduto     = "";
    public     String    fginsoluto    = "";
    public     String    fgall_scad    = "";//avendramin20110511
    public     String    fgno_accrediti    = "";//avendramin20110523
    public     String    cdzcom        = "";
    public     String    cdnazi        = "";
    public     String    cdprov        = "";
    public     String    cdispe        = "";
    public     String    cap           = "";
    public     String    comune        = "";
    public     String    pgcodi        = "";
    public     String    fgdtinc       = "";
    public     String    fgnocliebloc  = "";
    public     String    fgnotifscad   = "";
    public     Timestamp dtscad_da     = null;
    public     Timestamp dtscad_a      = null;
    public     long      conpro        = 0; 
    public     Timestamp dtprot        = null; 
    public     String    descr_prot_iva = "";
    public     Timestamp dtprot_da     = null;//avendramin20110523
    public     Timestamp dtprot_a      = null;//avendramin20110523
    public     long      tkscad        = 0; //avendramin20110527
    public     long      nrrata        = 0; //avendramin20110527
    public     long      nrriga_ori    = 0; //avendramin20110527
    public     String    cdresp        = "";
    public     String    fg_solo_scadenze_negative   = "";
    public     String    fg_null_cdresp   = "N";




    /***


    */

    public void copy_into ( Str_controllo_scadenziario astr) throws Exception {
       
       super.copy_into((Str_rep_base) astr);

       astr.ftpscad                          = this.ftpscad                          ;
       astr.fgscaduto                        = this.fgscaduto                        ;
       astr.fginsoluto                       = this.fginsoluto                       ;
       astr.fgall_scad                       = this.fgall_scad                       ;//avendramin20110511
       astr.cdzcom                           = this.cdzcom                           ;
       astr.cdnazi                           = this.cdnazi                           ;
       astr.cdprov                           = this.cdprov                           ;
       astr.cdispe                           = this.cdispe                           ;
       astr.cap                              = this.cap                              ;
       astr.comune                           = this.comune                           ;
       astr.pgcodi                           = this.pgcodi                           ;
       astr.fgdtinc                          = this.fgdtinc                          ;
       astr.fgnocliebloc                     = this.fgnocliebloc                     ;
       astr.fgnotifscad                      = this.fgnotifscad                      ;
       astr.dtscad_da                        = this.dtscad_da                        ;
       astr.dtscad_a                         = this.dtscad_a                         ;
       astr.conpro                           = this.conpro                           ;
       astr.dtprot                           = this.dtprot                           ;
       astr.descr_prot_iva                   = this.descr_prot_iva                   ;
       astr.dtprot_da                        = this.dtprot_da                        ;//avendramin20110523
       astr.dtprot_a                         = this.dtprot_a                         ;//avendramin20110523
       astr.fgno_accrediti                   = this.fgno_accrediti                   ;//avendramin20110523
       astr.tkscad                           = this.tkscad                           ;//avendramin20110527
       astr.nrrata                           = this.nrrata                           ;//avendramin20110527
       astr.nrriga_ori                       = this.nrriga_ori                       ;//avendramin20110527
       astr.cdresp                           = this.cdresp                           ;
       astr.fg_solo_scadenze_negative        = this.fg_solo_scadenze_negative        ;
       astr.fg_null_cdresp                   = this.fg_null_cdresp                   ;

    }




}
