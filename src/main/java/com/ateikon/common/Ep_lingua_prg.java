package com.ateikon.common;



import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;

import java.util.Collection;
import java.util.Properties;
import java.util.Enumeration;
import java.util.Vector;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.commons.io.FileUtils;

import com.ateikon.common.Atk_sql;
import com.ateikon.function.F_tabkey;

import com.ateikon.common.Ep_lingua; 
import com.ateikon.common.Ep_lingua_lb; 
import com.ateikon.common.Ep_lingua_prg; 
import com.ateikon.common.Ep_lingua_url; 

import com.ateikon.structure.Rec_ep_lingua; 
import com.ateikon.structure.Rec_ep_lingua_lb; 
import com.ateikon.structure.Rec_ep_lingua_prg; 
import com.ateikon.structure.Rec_ep_lingua_url; 

import com.ateikon.util.StringUtils; 


public class Ep_lingua_prg extends com.ateikon.standard.Ep_lingua_prg {


    public Ep_lingua_prg() {

        super();
    }




    /***


    */

    public ResultSet getWebinfpath ( ) throws Exception {
        
    	int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;


        l_query  = "";                              
        l_query += " select cdprogetto             \n";
        l_query += "      , webinfpath             \n";
        l_query += "   from pgmr.ep_lingua_prg     \n";
        l_query += "  where webinfpath is not null \n";
        l_query += "  group by cdprogetto          \n";
        l_query += "      , webinfpath             \n";

        rs = sql_query(l_query);

        return rs;

    }


    /***


    */

    public ResultSet getAll ( ) throws Exception {
        
    	int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;


        l_query  = "";
        l_query += " select *                   \n";
        l_query += "   from pgmr.ep_lingua_prg  \n";
        l_query += "  order by cdprogetto       \n";
        l_query += "         , virtualhost      \n";
        l_query += "         , contextpath      \n";

        rs = sql_query(l_query);

        return rs;

    }





    /***


    */

    public int of_import_properties_lb(String cdling, long tklingua_url, File lo_file_prop) throws Exception {
        
    	int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        Ep_lingua_lb ep_lingua_lb = new Ep_lingua_lb();

        setProfilo((Atk_sql) ep_lingua_lb);

        Rec_ep_lingua_lb lstr = new Rec_ep_lingua_lb();
    
        l_query  = "";
        l_query += " select cdling         \n";
        l_query += "   from pgmr.ep_lingua \n";
        l_query += "  where fgdef = 'S'    \n";

        String  cdling_def = sql_String(l_query);

        l_query  = "";
        l_query += " select cdiso639       \n";
        l_query += "   from pgmr.ep_lingua \n";
        l_query += "  where fgdef = 'S'    \n";

        String  cdiso639_def = sql_String(l_query);

        String ls_properties_ori = lo_file_prop.getAbsolutePath();
        String ls_properties_def = lo_file_prop.getAbsolutePath();

        l_query  = "";
        l_query += " select cdiso639                       \n";
        l_query += "   from pgmr.ep_lingua                 \n";
        l_query += "  where cdiso639 <> '"+cdiso639_def+"' \n";

        pstm = m_connection.prepareStatement(l_query);

        rs = pstm.executeQuery();

        while (rs!=null && rs.next()){
            String cdiso639 = "";     

            if (rs.getObject("cdiso639")!=null)  cdiso639 = rs.getString("cdiso639");

            ls_properties_def = StringUtils.replace( ls_properties_def
                                                   , "_"+cdiso639+".properties"
                                                   , "_"+cdiso639_def+".properties"
                                                            );

        }
        pstm.close();


        Vector vec_cdcampo = new Vector();


        Properties lo_properties  = new Properties();
        Properties lo_properties_def  = null;


        lo_properties.load((new FileInputStream(lo_file_prop)));

        try {
            
            if (!ls_properties_def.equals(ls_properties_ori)){
                
                lo_properties_def = new Properties();
                lo_properties_def.load((new FileInputStream(ls_properties_def)));
            }
            
        }catch(Exception ex){
            ex.printStackTrace();
            lo_properties_def = null;
        }


        Enumeration enum_key = lo_properties.propertyNames();

        while (enum_key!=null && enum_key.hasMoreElements()){
            
            String ls_key = (String)enum_key.nextElement();

            if(ls_key == null ) ls_key = "";

            if(ls_key == null  || ls_key.equals("")){
                System.out.println("\tFile "+lo_file_prop.getName()+" Key NULLA !?!");
                continue;
            }

            System.out.println("\tKey >>"+ls_key+"<<");

            String ls_value = lo_properties.getProperty(ls_key) ;
            String ls_value_def = "";

            if (lo_properties_def!=null) {
                ls_value_def = lo_properties_def.getProperty(ls_key) ;
            }

            if (ls_value == null) ls_value = "";
            if (ls_value_def == null) ls_value_def = "";


            if (cdling.equals(cdling_def)
            ||  !ls_value.equals(ls_value_def)
                    ){

                lstr = new Rec_ep_lingua_lb();

                lstr.cdling           = cdling;
                lstr.tklingua_url     = tklingua_url;
                lstr.cdcampo          = ls_key;
                lstr.dscampo          = ls_value;
                lstr.dscampo_defling  = ls_value_def;

                if (cdling.equals(cdling_def)) lstr.dscampo_defling  = lstr.dscampo;

                //lstr.cdcampo = lstr.cdcampo.toLowerCase();

                if (!vec_cdcampo.contains(lstr.cdcampo)){

                    tot_rec = ep_lingua_lb.execute(lstr);

                    vec_cdcampo.add(lstr.cdcampo);
                }else {
                    System.out.println("\tFile "+lo_file_prop.getName()+" Key "+lstr.cdcampo+" NON univoca.");
                }
            }else {

                System.out.println("\tFile "+lo_file_prop.getName()+" Key "+lstr.cdcampo+": valore = al valore di default; scartata!");
            }

        }



        return 1;

    }


    /***


    */

    public long of_import_properties_url (String as_cdprogetto, String ls_webinfpath, File file_prop) throws Exception {
        
    	int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        Ep_lingua_url ep_lingua_url = new Ep_lingua_url();

        setProfilo((Atk_sql) ep_lingua_url);

        Rec_ep_lingua_url lstr = new Rec_ep_lingua_url();


        String slash = System.getProperty( "file.separator" );


        lstr.tklingua_url = 0; 
        lstr.cdprogetto   = as_cdprogetto; 
        lstr.relativepath = file_prop.getParent()+slash; 
        lstr.filename     = file_prop.getName(); 
        lstr.fgtraduci    = ""; // --- viene impostato quello del DB se presente, altrimenti "S" 
         
        lstr.relativepath  = StringUtils.replace(lstr.relativepath , ls_webinfpath, "");
         
        l_query  = "";
        l_query += " select cdiso639       \n";
        l_query += "   from pgmr.ep_lingua \n";
        
        pstm = m_connection.prepareStatement(l_query);
        
        rs = pstm.executeQuery();
        
        while (rs!=null && rs.next()){
           
           String cdiso639 = "";
           
           if (rs.getObject("cdiso639")!=null)  cdiso639  = rs.getString("cdiso639");

           lstr.filename = StringUtils.replace(lstr.filename, "messages_", "");
           lstr.filename = StringUtils.replace(lstr.filename, "_"+cdiso639+".properties", ".jsp");
           lstr.filename = StringUtils.replace(lstr.filename, cdiso639+".properties", "");
           
        }
        pstm.close();
        

        tot_rec = ep_lingua_url.execute(lstr);


        return lstr.tklingua_url ;

    }


    /***


    */

    public String of_import_properties_ling (File file_prop) throws Exception {
        
    	int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        String ls_filename     = file_prop.getName(); 
         
         
        l_query  = "";
        l_query += " select cdling         \n";
        l_query += "      , cdiso639       \n";
        l_query += "   from pgmr.ep_lingua \n";
        
        pstm = m_connection.prepareStatement(l_query);
        
        rs = pstm.executeQuery();
        
        while (rs!=null && rs.next()){
           
           String cdling = "";
           String cdiso639 = "";
           
           if (rs.getObject("cdling")!=null)  cdling  = rs.getString("cdling");
           if (rs.getObject("cdiso639")!=null)  cdiso639  = rs.getString("cdiso639");

           if (ls_filename.indexOf(cdiso639+".properties") > 0){
                return cdling;   
           }
        }
        pstm.close();
        
        return "";

    }



    /***


    */

    public int of_import_properties ( long tklingua_prg) throws Exception {
        
    	int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        l_query  = "";                              
        l_query += " select cdprogetto                        \n";
        l_query += "      , webinfpath                        \n";
        l_query += "   from pgmr.ep_lingua_prg                \n";
        l_query += "  where tklingua_prg = "+tklingua_prg+"   \n";

        rs = sql_query(l_query);

        if (rs!=null && rs.next()){
            
            String ls_cdprogetto = "";
            String ls_webinfpath = "";
    
            if (rs.getObject("cdprogetto")!=null)  ls_cdprogetto = rs.getString("cdprogetto");
            if (rs.getObject("webinfpath")!=null)  ls_webinfpath = rs.getString("webinfpath");

            String slash = System.getProperty( "file.separator" );

            ls_webinfpath = ls_webinfpath+DIR_MESSAGES;
            
            ls_webinfpath = StringUtils.replace(ls_webinfpath, "/", slash);
            ls_webinfpath = StringUtils.replace(ls_webinfpath, "\\", slash);

            File        lo_webinfpath  = new File(ls_webinfpath);
            String []   arr_extensions = new String[]{"properties"};
            boolean     lb_recursive   = true;
    
            if (lo_webinfpath.exists() && lo_webinfpath.isDirectory()){
            }else {
                return -1;
            }

            java.util.Collection collection = FileUtils.listFiles(lo_webinfpath, arr_extensions, lb_recursive) ;
    
            if (collection!=null){
    
                Object[] arr_file = collection.toArray();
    
                for(int i=0; arr_file!=null && i<arr_file.length; i++){
                    
                    File lo_file_prop = (File)arr_file[i];
                    
                    if (lo_file_prop.exists() && lo_file_prop.isFile()){
                    
                        String ls_filename = lo_file_prop.getName();
    
                        System.out.println("Import File >>"+ls_filename+"<<");

                        if (ls_filename.indexOf("messages_") == 0){
                            
                            long   tklingua_url = of_import_properties_url(ls_cdprogetto, ls_webinfpath, lo_file_prop);
                            String cdling       = of_import_properties_ling(lo_file_prop);
        
                            if (!cdling.equals("") && tklingua_url>0) {
                                
                                tot_rec = of_import_properties_lb(cdling, tklingua_url, lo_file_prop);
    
                            }else {
                                System.out.println("File "+ls_filename+" NON elaborato ");
                            }
                        }else {
                            System.out.println("File "+ls_filename+" NON messages_ ");
                        }   // --- FINE if (ls_filename.indexOf("messages_") == 0){

                    }   // --- FINE if (lo_file_prop.exists() && lo_file_prop.isFile()){
    
                }
    
            }
    
    
        }   // --- while
    



        return 1;

    }




    /***


    */

    public int of_create_properties ( ) throws Exception {
        
    	int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        Ep_lingua ep_lingua = new Ep_lingua();

        setProfilo((Atk_sql) ep_lingua);

        String[] arr_cdling   = ep_lingua.getArray("cdling"  , "");
        String[] arr_cdiso639 = ep_lingua.getArray("cdiso639", "");

        String slash = System.getProperty( "file.separator" );


        l_query  = "";
        l_query += " select cdprogetto         \n";
        l_query += "      , webinfpath         \n";
        l_query += "   from pgmr.ep_lingua_prg \n";
        l_query += "  group by cdprogetto      \n";
        l_query += "         , webinfpath      \n";
        l_query += "  order by cdprogetto      \n";
        l_query += "         , webinfpath      \n";

        pstm = m_connection.prepareStatement(l_query);

        rs = pstm.executeQuery();

        while(rs!=null && rs.next()){
            
            String ls_cdprogetto = "";
            String ls_webinfpath = "";

            if (rs.getObject("cdprogetto")!=null)  ls_cdprogetto = rs.getString("cdprogetto");
            if (rs.getObject("webinfpath")!=null)  ls_webinfpath = rs.getString("webinfpath");

            System.out.println(ls_cdprogetto+" >> "+ls_webinfpath);

            ls_webinfpath += DIR_MESSAGES;


                    l_query  = "";
                    l_query += " select *                                 \n";
                    l_query += "   from pgmr.ep_lingua_url                \n";
                    l_query += "  where cdprogetto = '"+ls_cdprogetto+"'  \n";
                    l_query += "  order by relativepath                   \n";
                    l_query += "         , filename                       \n";
                    l_query += "         , tklingua_url                   \n";

                    PreparedStatement pstm_url = m_connection.prepareStatement(l_query);

                    ResultSet rs_url = pstm_url.executeQuery();

                    while (rs_url!=null && rs_url.next()){
                        
                        Rec_ep_lingua_url lstr_url = new Rec_ep_lingua_url();
            
                        lstr_url.setResultSet(rs_url);

                        System.out.println("\tFile: "+lstr_url.filename);

                        String ls_abspath  = ls_webinfpath+lstr_url.relativepath;
                        
                        ls_abspath = StringUtils.replace(ls_abspath, "\\", slash);
                        ls_abspath = StringUtils.replace(ls_abspath, "/", slash);


                        File of_dir = new File(ls_abspath);

                        if (!of_dir.exists()){
                            boolean lb_ = of_dir.mkdirs();

                            if (!lb_) throw new Exception("Errore creazione directory "+ls_abspath);
                        }

                        for (int i=0; arr_cdiso639!=null && i<arr_cdiso639.length; i++){
    
                            String ls_filename = lstr_url.filename;
                            
                            ls_filename = StringUtils.replace(ls_filename, ".jsp", "");
                            if (!ls_filename.equals("")){
                                ls_filename = "messages_"+ls_filename + "_"+arr_cdiso639[i]+".properties";
                            }else {
                                ls_filename = "messages_"+arr_cdiso639[i]+".properties";
                            }
    
                            File of_file = new File(ls_abspath+ls_filename);
    
                            if (of_file.exists()){
                                boolean lb_ = of_file.delete();
    
                                if (!lb_) throw new Exception("Errore cancellazione file "+ls_abspath+ls_filename);
                            }
    
                            of_create_properties(arr_cdling[i], lstr_url.tklingua_url, of_file);

                        }

                    }
                    pstm_url.close();


        }
        pstm.close();



        return 1;


    }




    /***


    */

    public int of_create_properties(String cdling, long tklingua_url, File of_file) throws Exception {
        
    	int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        Ep_lingua ep_lingua = new Ep_lingua ();

        setProfilo((Atk_sql) ep_lingua );

        String cdling_def = ep_lingua.getCdling_def();

        java.util.Properties lo_properties = new java.util.Properties();

        l_query  = "";
        l_query += " select cdcampo                         \n";
        l_query += "      , dscampo                         \n";
        l_query += "   from pgmr.ep_lingua_lb               \n";
        l_query += "  where cdling       = '"+cdling_def+"' \n";
        l_query += "    and tklingua_url = "+tklingua_url+" \n";
        l_query += "  order by cdcampo                      \n";


        pstm = m_connection.prepareStatement(l_query);

        rs = pstm.executeQuery();

        while (rs!=null && rs.next()){
            
            String cdcampo     = "";
            String dscampo_def = "";
            String dscampo     = "";

            if (rs.getObject("cdcampo")!=null)  cdcampo     = rs.getString("cdcampo");
            if (rs.getObject("dscampo")!=null)  dscampo_def = rs.getString("dscampo");

            if (cdcampo.equals("")) continue;

            if (!cdling.equals(cdling_def)){
                
                l_query  = "";
                l_query += " select dscampo            \n";
                l_query += "   from pgmr.ep_lingua_lb  \n";
                l_query += "  where cdling       = ?   \n";
                l_query += "    and tklingua_url = ?   \n";
                l_query += "    and cdcampo      = ?   \n";
                l_query += "  order by cdcampo         \n";
                l_query += "         , tklingua_url    \n";
    
                PreparedStatement pstm_box = m_connection.prepareStatement(l_query);
    
                ind = 1;
                pstm_box.setString(ind++, cdling      );
                pstm_box.setLong  (ind++, tklingua_url);
                pstm_box.setString(ind++, cdcampo     );
    
                ResultSet rs_box = pstm_box.executeQuery();
    
                if (rs_box!=null && rs_box.next()){
    
                    if (rs_box.getObject("dscampo")!=null)  dscampo = rs_box.getString("dscampo");
                }
                pstm_box.close();
            }

            if (dscampo.equals("")) dscampo = dscampo_def;

            lo_properties.setProperty(cdcampo, dscampo);

        }
        pstm.close();


        com.ateikon.util.Atk_ctrl atk_ctrl = new com.ateikon.util.Atk_ctrl();

        java.io.FileOutputStream fos = new java.io.FileOutputStream(of_file);

        String ls_comments = "";
        ls_comments += "\n";
        ls_comments += "\tQuesto file viene generato AUTOMATICAMENTE dal \n";
        ls_comments += "\tportale e-progen \n";
        ls_comments += "\n";
        ls_comments += "\tDati generazione:\n";
        ls_comments += "\t\tUtente: "+profil+" \n";
        ls_comments += "\t\tData  : "+atk_ctrl.getDatetime(oggi)+" \n";
        ls_comments += "\n";

        lo_properties.store((java.io.OutputStream) fos, ls_comments);

        fos.close();

        return 1;

    }








    public static String DIR_MESSAGES = "classes/";


}

