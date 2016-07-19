package com.ateikon.function;

import java.sql.ResultSet;
import java.sql.PreparedStatement;

import com.ateikon.common.Atk_sql;


public class F_login extends Atk_sql {

    public F_login() {

        super();

    }





    public boolean isLogin( String user_name
                          , String password
                                              ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        user_name = user_name.trim().toUpperCase();
        password  = password.trim().toUpperCase();

        com.ateikon.util.Atk_ctrl atk_ctrl = new com.ateikon.util.Atk_ctrl();




        password = atk_ctrl.encode(password);


        tot_rec = 0;

        l_query   = "";
		l_query  += " select count(*)        \n";
		l_query  += "   from pgmr.cat_utente  \n";
		l_query  += "  where user_name = ?   \n";
		l_query  += "    and password  = ?   \n";

        pstm = m_connection.prepareStatement(l_query);

        ind = 1;

        System.out.println(user_name);
        System.out.println(password);
        pstm.setString(ind++, user_name);
        pstm.setString(ind++, password );

        rs = pstm.executeQuery();


        int li_ = 0;

        if (rs!=null && rs.next()){
            
            tot_rec += 1;
            ind = 0;
            if (rs.getObject(++ind)!=null)  li_ = rs.getInt(ind);

        }
        pstm.close();

        if (li_ <= 0){
            
            return false;
        }

        return true;
    }





    public ResultSet login( String user_name
                          , String cdazie 
                          , String cddipa
                                            ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        user_name = user_name.trim().toUpperCase();

        l_query   = "";
		l_query  += " select *                \n";
		l_query  += "   from pgmr.cat_utente   \n";
		l_query  += "  where user_name  = ?   \n";
        if (!cdazie.equals("")){
		l_query  += "    and cdazie = ?       \n";
        }
        if (!cddipa.equals("")){
		l_query  += "    and cddipa = ?       \n";
        }

		pstm = setQuery( l_query ) ;

        ind = 1;
        pstm.setString(ind, user_name); ind += 1;

        if (!cdazie.equals("")){
            pstm.setString(ind, cdazie); ind += 1;
        }
        if (!cddipa.equals("")){
            pstm.setString(ind, cddipa); ind += 1;
        }

        rs = pstm.executeQuery();

        return rs;


    }

    public ResultSet getCdazie(String user_name) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        user_name = user_name.trim().toUpperCase();


        l_query   = "";
		l_query  += "  select b.cdazie              \n";
		l_query  += "       , max(a.tarags)         \n";
		l_query  += "    from pgmr.cat_utente b      \n";
		l_query  += "       , pgmr.azienda    a     \n";
		l_query  += "   where user_name = ?         \n";
		l_query  += "     and b.cdazie = a.cdazie   \n";
		l_query  += "   group by b.cdazie           \n";
		l_query  += "   order by b.cdazie           \n";

		pstm = setQuery( l_query ) ;

        ind = 1;
        pstm.setString(ind, user_name); ind += 1;

        rs = pstm.executeQuery();

        return rs;


    }



    public ResultSet getCddipa(String user_name, String cdazie) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        user_name = user_name.trim().toUpperCase();


        l_query   = "";
		l_query  += "  select b.cddipa              \n";
		l_query  += "       , a.dsdipa              \n";
		l_query  += "    from pgmr.cat_utente    b   \n";
		l_query  += "       , pgmr.dipartimenti  a  \n";
		l_query  += "   where b.cddipa = a.cddipa   \n";
		l_query  += "     and b.cdazie = a.cdazie   \n";
		l_query  += "     and b.cdazie    = ?       \n";
		l_query  += "     and b.user_name = ?       \n";
		l_query  += "   order by b.cddipa           \n";

		pstm = setQuery( l_query ) ;

        ind = 1;
        pstm.setString(ind, cdazie   ); ind += 1;
        pstm.setString(ind, user_name); ind += 1;

        rs = pstm.executeQuery();

        return rs;


    }



    /***


    */

    public String getDsutente (long tkutente ) throws Exception {
        
    	int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;


        tot_rec = 0;

        String cdazie   = "";
        String dsutente = "";
        String tkclie   = "";
        String tkforn   = "";
        String cdagen   = "";
        String cdente   = "";
        String cdutente = "";

        l_query  = "";
        l_query += " select *                       \n";
        l_query += "   from pgmr.cat_utente          \n";
        l_query += "  where tkutente = "+tkutente+" \n";

        pstm = m_connection.prepareStatement(l_query);

        ind = 1;

        rs = pstm.executeQuery();

        if (rs!=null && rs.next()){
            
            tot_rec += 1;
            if (rs.getObject("cdazie"    )!=null) cdazie    = rs.getString("cdazie");

            if (rs.getObject("dsutente"  )!=null) dsutente  = rs.getString("dsutente");
            if (rs.getObject("tkclie"    )!=null) tkclie    = rs.getString("tkclie"  );
            if (rs.getObject("tkforn"    )!=null) tkforn    = rs.getString("tkforn"  );
            if (rs.getObject("cdagen"    )!=null) cdagen    = rs.getString("cdagen"  );
            if (rs.getObject("cdente"    )!=null) cdente    = rs.getString("cdente"  );
            if (rs.getObject("cdutente"  )!=null) cdutente  = rs.getString("cdutente");

        }
        pstm.close();

        if (!tkclie.equals("")){

            l_query  = "";
            l_query += " select ente.ragcog                \n";
            l_query += "   from pgmr.archclie clie         \n";
            l_query += "      , pgmr.archenti ente         \n";
            l_query += "  where ente.cdente = clie.cdente  \n";
            l_query += "    and clie.cdazie = '"+cdazie+"' \n";
            l_query += "    and clie.tkclie = '"+tkclie+"' \n";
            
            dsutente = sql_String(l_query);

        }else if (!cdagen.equals("")){
            
            l_query  = "";
            l_query += " select agen.dsagen                \n";
            l_query += "   from pgmr.archagen agen         \n";
            l_query += "  where agen.cdazie = '"+cdazie+"' \n";
            l_query += "    and agen.cdagen = '"+cdagen+"' \n";
            
            dsutente = sql_String(l_query);

        }else if (!tkforn.equals("")){
            
            l_query  = "";
            l_query += " select ente.ragcog                \n";
            l_query += "   from pgmr.archforn forn         \n";
            l_query += "      , pgmr.archenti ente         \n";
            l_query += "  where ente.cdente = forn.cdente  \n";
            l_query += "    and forn.cdazie = '"+cdazie+"' \n";
            l_query += "    and forn.tkforn = '"+tkforn+"' \n";
            
            dsutente = sql_String(l_query);

        }else if (!cdutente.equals("")){
            
            l_query  = "";
            l_query += " select uten.dsutente                  \n";
            l_query += "   from pgmr.atk_utente uten           \n";
            l_query += "  where uten.cdazie = '"+cdazie+"'     \n";
            l_query += "    and uten.cdutente = '"+cdutente+"' \n";
            
            dsutente = sql_String(l_query);

        }else if (!cdente.equals("")){
            
            l_query  = "";
            l_query += " select ente.ragcog                \n";
            l_query += "   from pgmr.archenti ente         \n";
            l_query += "  where ente.cdente = '"+cdente+"'  \n";
            
            dsutente = sql_String(l_query);
        }


        return dsutente;

    }

    // Properties


 
}
