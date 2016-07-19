package com.ateikon.common;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import com.ateikon.common.Atk_sql;
import com.ateikon.function.F_tabkey;


public class Archrubrica_modif extends Atk_sql {


    public Archrubrica_modif() {

        super();
    }
    
    
    public final static String CDSTATO_APPR  = "APPR";
    public final static String CDSTATO_NAPP  = "NAPP";
    public final static String CDSTATO_PARZ  = "PARZ";
    
    public final static String ESCAPE_ADDED    = "[added]";
    public final static String ESCAPE_ADD      = "[add]";
    public final static String ESCAPE_DELETED  = "[deleted]";
    public final static String ESCAPE_DELETE   = "[delete]";
    public final static String ESCAPE_PUNTO    = "[punto]";
    
    public final static String IMG_ADD     = "rmodif_add.png";
    public final static String IMG_DELETE  = "rmodif_delete.png";
    public final static String IMG_PUNTO  = "rmodif_list.png";
    
}

