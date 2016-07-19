package com.ateikon.structure;


import com.ateikon.util.StringUtils;
import com.ateikon.util.Atk_ctrl;

public class Str_dec_barcode extends Object {


    public Str_dec_barcode(){
 
        super();

    }

    public String seStesso = "Str_dec_barcode";

    public String rc          = "";
    public String message     = "";



    public String  cdarti      = "";
    public long    tkmatricola = 0;

    public boolean trovato     = false;




    // sezione riguardo l'articolo

    public String    fpacco  = "";
    public String    dsarti  = "";
    public String    dssart  = "";
    public String    cdbarcode = "";
    public String    cdartm    = "";
    public String    cdunim    = "";
    public String    cdtins    = "";
    public String    cdstato_m = "";

    public double    dimena_art = 0;
    public double    dimenb_art = 0;
    public double    dimenc_art = 0;

    public double    scmax_1 = 0;
    public double    scmax_2 = 0;

    public String    fpacco_art  = "";
    public String    fpacco_art_disabled = "N";

    // Qta impostate Dall'utente

    public double    dimena    = 0;
    public double    dimenb    = 0;
    public double    dimenc    = 0;
    public long      ncolli    = 0;
    public double    quantita  = 0;
    





    /***

        Imposto i dati relativi alla Tabella
    */

    public int setArticolo(java.sql.ResultSet rs) throws Exception{
        

        int ind = 1;
        int rc = 1;

        fpacco    = "";
        dsarti    = "";
        dssart    = "";
        cdbarcode = "";
        cdartm    = "";
        dimena    = 0;
        dimenb    = 0;
        dimenc    = 0;
        cdunim    = "";
        cdtins    = "";
        cdstato_m = "";
        fpacco_art = "";

        scmax_1 = 0;
        scmax_2 = 0;

        if (rs == null) return 0;

        if (rs.getObject("cdartm"    )!=null ) cdartm      = rs.getString("cdartm"    ); ind += 1;
        if (rs.getObject("cdbarcode" )!=null ) cdbarcode   = rs.getString("cdbarcode" ); ind += 1;
        if (rs.getObject("dsarti"    )!=null ) dsarti      = rs.getString("dsarti"    ); ind += 1;
        if (rs.getObject("fpacco"    )!=null ) fpacco      = rs.getString("fpacco"    ); ind += 1;
        if (rs.getObject("dimena"    )!=null ) dimena      = rs.getDouble("dimena"    ); ind += 1;
        if (rs.getObject("dimenb"    )!=null ) dimenb      = rs.getDouble("dimenb"    ); ind += 1;
        if (rs.getObject("dimenc"    )!=null ) dimenc      = rs.getDouble("dimenc"    ); ind += 1;
        if (rs.getObject("cdunim"    )!=null ) cdunim      = rs.getString("cdunim"    ); ind += 1;
        if (rs.getObject("cdtins"    )!=null ) cdtins      = rs.getString("cdtins"    ); ind += 1;
        if (rs.getObject("cdstato_m" )!=null ) cdstato_m   = rs.getString("cdstato_m" ); ind += 1;
        if (rs.getObject("fpacco_art" )!=null ) fpacco_art   = rs.getString("fpacco_art" ); ind += 1;

        if (rs.getObject("scmax_1")!=null ) scmax_1 = rs.getDouble("scmax_1"); ind += 1;
        if (rs.getObject("scmax_2")!=null ) scmax_2 = rs.getDouble("scmax_2"); ind += 1;
        

        if (!fpacco_art_disabled.equals("S")){
            
            if (fpacco_art.equals("S")) fpacco = "S";
        }

        dimena_art = dimena;
        dimenb_art = dimenb;
        dimenc_art = dimenc;

        return 1;

    }




    /*** Pacco: matr_anagrafica ***/


    public String     cdmatricola_m  = "";
    public String     cdmatrprod     = "";
    public String     dsmatricola    = "";
    public String     fmisvar        = "";
    public String     fpaccodist     = "";
    public String     lotto_cdtins   = "";
    public String     lotto_cdunim   = "";

    public double     dimenamin      = 0;
    public double     dimenamax      = 0;
    public double     dimenbmin      = 0;
    public double     dimenbmax      = 0;
    public double     dimencmin      = 0;
    public double     dimencmax      = 0;  

    public double     qtaincr        = 0;



    /***

        Imposto i dati relativi al Lotto
    */

    public int setMatricola(java.sql.ResultSet rs) throws Exception{
        

        int ind = 1;
        int rc = 1;

        cdmatricola_m  = "";
        cdmatrprod     = "";
        dsmatricola    = "";
        fmisvar        = "";
        fpaccodist     = "";
        lotto_cdtins   = "";
        lotto_cdunim   = "";
        
        dimenamin      = 0;
        dimenamax      = 0;
        dimenbmin      = 0;
        dimenbmax      = 0;
        dimencmin      = 0;
        dimencmax      = 0;  
        
        qtaincr        = 0;

        if (!fpacco.equals("S")) return 0;

        if (rs == null) return 0;

        if (rs.getObject("cdmatricola_m")!=null ) cdmatricola_m  = rs.getString("cdmatricola_m"); ind += 1;
        if (rs.getObject("cdmatrprod"   )!=null ) cdmatrprod     = rs.getString("cdmatrprod"   ); ind += 1;
        if (rs.getObject("dsmatricola"  )!=null ) dsmatricola    = rs.getString("dsmatricola"  ); ind += 1;

        if (rs.getObject("fmisvar"      )!=null ) fmisvar        = rs.getString("fmisvar"   ); ind += 1;
        if (rs.getObject("fpaccodist"   )!=null ) fpaccodist     = rs.getString("fpaccodist"   ); ind += 1;
        if (rs.getObject("cdtins"       )!=null ) lotto_cdtins   = rs.getString("cdtins"); ind += 1;
        if (rs.getObject("cdunim"       )!=null ) lotto_cdunim   = rs.getString("cdunim"); ind += 1;


        if (rs.getObject("dimenamin"    )!=null ) dimenamin      = rs.getDouble("dimenamin"    ); ind += 1;
        if (rs.getObject("dimenamax"    )!=null ) dimenamax      = rs.getDouble("dimenamax"    ); ind += 1;
        if (rs.getObject("dimenbmin"    )!=null ) dimenbmin      = rs.getDouble("dimenbmin"    ); ind += 1;
        if (rs.getObject("dimenbmax"    )!=null ) dimenbmax      = rs.getDouble("dimenbmax"    ); ind += 1;
        if (rs.getObject("dimencmin"    )!=null ) dimencmin      = rs.getDouble("dimencmin"    ); ind += 1;
        if (rs.getObject("dimencmax"    )!=null ) dimencmax      = rs.getDouble("dimencmax"    ); ind += 1;

        return 1;

    }




    /*** Dati Relativi al Clacolo ***/

    public String    fdimena    = "";
    public String    fdimenb    = "";
    public String    fdimenc    = "";
    public String    fquantita  = "";
    public String    fncolli    = "";
    public int       fdivmis    = 0;

    // restituisce il cdtins Lotto/Articolo 

    public String getCdtins(){
        
        if (!fpacco.equals("S")) return cdtins;
        
        if (!lotto_cdtins.equals("")){
            return lotto_cdtins;
        }else {
            return cdtins ;
        }
    }


    // restituisce il cdunim Lotto/Articolo 

    public String getCdunim(){
        
        if (!fpacco.equals("S")) return cdunim;
        
        if (!lotto_cdunim.equals("")){
            return lotto_cdunim;
        }else {
            return cdunim ;
        }
    }



    /*** Distinta Pacco ***/

    public long tkmatrdist  = 0;










    public void println(){
        
        System.out.println("");
        System.out.println("");
        System.out.println("*************************************");
        System.out.println("***   " + seStesso);
        System.out.println("*************************************");
        System.out.println("");

        System.out.println("cdarti       >>"+cdarti     +"<<");
        System.out.println("tkmatricola  >>"+tkmatricola+"<<");
        System.out.println("trovato      >>"+trovato    +"<<");
        System.out.println("");
        System.out.println("// sezione riguardo l'articolo     ");
        System.out.println("");
        System.out.println("fpacco       >>"+fpacco   +"<<");
        System.out.println("dsarti       >>"+dsarti   +"<<");
        System.out.println("dssart       >>"+dssart   +"<<");
        System.out.println("cdbarcode    >>"+cdbarcode+"<<");
        System.out.println("cdartm       >>"+cdartm   +"<<");
        System.out.println("cdunim       >>"+cdunim   +"<<");
        System.out.println("cdtins       >>"+cdtins   +"<<");
        System.out.println("cdstato_m    >>"+cdstato_m+"<<");
        System.out.println("");
        System.out.println("// Qta impostate Dall'utente       ");
        System.out.println("");
        System.out.println("dimena       >>"+dimena   +"<<");
        System.out.println("dimenb       >>"+dimenb   +"<<");
        System.out.println("dimenc       >>"+dimenc   +"<<");
        System.out.println("ncolli       >>"+ncolli   +"<<");
        System.out.println("quantita     >>"+quantita +"<<");
        System.out.println("");
        System.out.println("/*** Pacco: matr_anagrafica ***/   ");
        System.out.println("");
        System.out.println("cdmatricola_m  >>"+cdmatricola_m+"<<");
        System.out.println("cdmatrprod     >>"+cdmatrprod   +"<<");
        System.out.println("dsmatricola    >>"+dsmatricola  +"<<");
        System.out.println("fmisvar        >>"+fmisvar      +"<<");
        System.out.println("fpaccodist     >>"+fpaccodist   +"<<");
        System.out.println("lotto_cdtins   >>"+lotto_cdtins +"<<");
        System.out.println("lotto_cdunim   >>"+lotto_cdunim +"<<");
        System.out.println("dimenamin      >>"+dimenamin    +"<<");
        System.out.println("dimenamax      >>"+dimenamax    +"<<");
        System.out.println("dimenbmin      >>"+dimenbmin    +"<<");
        System.out.println("dimenbmax      >>"+dimenbmax    +"<<");
        System.out.println("dimencmin      >>"+dimencmin    +"<<");
        System.out.println("dimencmax      >>"+dimencmax    +"<<");
        System.out.println("qtaincr        >>"+qtaincr      +"<<");
        System.out.println("");
        System.out.println("/*** Dati Relativi al Clacolo ***/ ");
        System.out.println("");
        System.out.println("fdimena        >>"+fdimena    +"<<");
        System.out.println("fdimenb        >>"+fdimenb    +"<<");
        System.out.println("fdimenc        >>"+fdimenc    +"<<");
        System.out.println("fquantita      >>"+fquantita  +"<<");
        System.out.println("fncolli        >>"+fncolli    +"<<");
        System.out.println("fdivmis        >>"+fdivmis    +"<<");
        System.out.println("getCdtins()    >>"+getCdtins()+"<<");
        System.out.println("getCdunim()    >>"+getCdunim()+"<<");
        
        System.out.println("");
        System.out.println("/*** Token Distinta Pacco ***/ ");
        System.out.println("");
        System.out.println("tkmatrdist ");




        System.out.println("");
        System.out.println("*************************************");
        System.out.println("");
        System.out.println("");

    }









}








