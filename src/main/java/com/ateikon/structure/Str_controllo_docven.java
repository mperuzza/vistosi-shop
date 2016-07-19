package com.ateikon.structure;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;


public class Str_controllo_docven extends Str_rep_base {

    public Str_controllo_docven(){
 
        super();

        tipodoc = TIPODOC_BOLL;
        try {
            ctrlTipodoc();
        }catch(Exception ex){
            
        }

    }


    public     String    tipodoc       = "";
    public     long      anno          = 0;
    public     long      tkposi        = 0;         // posizione dell'ordine
    public     String    nrdocu        = "";
    public     Timestamp dtdocu_da     = null;
    public     Timestamp dtdocu_a      = null;


    public     String TIPODOC_BOLL = "B";
    public     String TIPODOC_FATT = "F";

    public     String dtname   = "";
    public     String nrname   = "";
    public     String rgname   = "";
    public     String joinp    = "";
    public     String docname  = "";




    /***


    */

    public void copy_into ( Str_controllo_docven astr) throws Exception {
       
       super.copy_into((Str_rep_base) astr);

       astr.anno        = this.anno; 
       astr.tkposi      = this.tkposi; 
       astr.nrdocu      = this.nrdocu; 
       astr.dtdocu_da   = this.dtdocu_da; 
       astr.dtdocu_a    = this.dtdocu_a; 

       astr.cdtipm      = this.cdtipm;
       astr.cdclas_a    = this.cdclas_a;
       astr.cdartipo    = this.cdartipo;
       astr.cdzcom      = this.cdzcom;
    }




    /***


    */

    public int ctrlTipodoc ( ) throws Exception {
        

        if (tipodoc.equals(TIPODOC_BOLL)){
            dtname = "dtboll";
            nrname = "cdboll";
            joinp  = "tkboll";
            rgname = "nrriga_b";
            docname = "Bolla";
        }else if (tipodoc.equals(TIPODOC_FATT)){
            dtname = "dtprot";
            nrname = "conpro";
            joinp  = "tkfatt";
            rgname = "nrriga_f";
            docname = "Fattura";
        }else {
            throw new Exception("tipodoc NON previsto");
        }

        return 1;

    }




}
