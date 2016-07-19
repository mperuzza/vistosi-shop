package com.ateikon.common;


import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;


public class Kg_articolo_cons extends Atk_sql {

    public Kg_articolo_cons() {
        
        super();
    }



   
    public String getConsegna  ( String    cdstagione
                               , int       annostag
                               , long      tkcarello
                               , Timestamp dtordi 
                                                       ) throws Exception {
    
                                                    
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        String rc = "0";


        l_query   = "";
		l_query  += " select *                                  \n";
		l_query  += "   from pgmr.kg_articolo_cons          \n";
		l_query  += "  where cdstagione = ?                     \n";
		l_query  += "    and annostag   = ?                     \n";
		l_query  += "    and cdarti is null                     \n";
		l_query  += "    and ? between dtord_da and  dtord_a    \n";


		pstm = setQuery( l_query ) ;

        ind = 1;

        pstm.setString   (ind, cdstagione); ind +=1 ;
        pstm.setInt      (ind, annostag  ); ind +=1 ;
        pstm.setTimestamp(ind, dtordi    ); ind +=1 ;


        Timestamp dtcons_da = null;
        Timestamp dtcons_a = null;

        rs = pstm.executeQuery();

        if (rs !=null && rs.next()){
            if (rs.getObject("dtcons_da")!=null ) dtcons_da = rs.getTimestamp("dtcons_da");
            if (rs.getObject("dtcons_a")!=null )  dtcons_a = rs.getTimestamp("dtcons_a");
        }

        if (dtcons_da == null){
            
            dtcons_da = new Timestamp(System.currentTimeMillis());

            dtcons_da.setHours(0);
            dtcons_da.setMinutes(0);
            dtcons_da.setSeconds(0);
            dtcons_da.setNanos(0);

        }


        this.dtcons_da = dtcons_da;
        this.dtcons_a  = dtcons_a;



        l_query   = "";
		l_query  += " select max(dtcons_da) as dtcons_da              \n";
		l_query  += "      , min(dtcons_a)  as dtcons_a               \n";
		l_query  += "   from pgmr.kg_articolo_cons                \n";
		l_query  += "  where cdstagione = ?                           \n";
		l_query  += "    and annostag   = ?                           \n";
		l_query  += "    and ? between dtord_da and dtord_a           \n";
		l_query  += "    and cdarti in (                              \n";
		l_query  += "                  select cdarti                  \n";
		l_query  += "                    from web.carrello   \n";
		l_query  += "                   where cdstagione = ?          \n";
		l_query  += "                     and annostag   = ?          \n";
		l_query  += "                     and tkcarrello = ?          \n";
		l_query  += "                  )                              \n";


		pstm = setQuery( l_query ) ;

        ind = 1;

        pstm.setString   (ind, cdstagione); ind +=1 ;
        pstm.setInt      (ind, annostag  ); ind +=1 ;
        pstm.setTimestamp(ind, dtordi    ); ind +=1 ;

        pstm.setString   (ind, cdstagione); ind +=1 ;
        pstm.setInt      (ind, annostag  ); ind +=1 ;
        pstm.setLong     (ind, tkcarello ); ind +=1 ;

        dtcons_da = null;
        dtcons_a = null;

        rs = pstm.executeQuery();

        if (rs !=null && rs.next()){
            
            if (rs.getObject("dtcons_da")!=null ) dtcons_da = rs.getTimestamp("dtcons_da");
            if (rs.getObject("dtcons_a")!=null )  dtcons_a = rs.getTimestamp("dtcons_a");

        }else {
            
            return rc;
        }

        if (dtcons_da != null && dtcons_da.compareTo(this.dtcons_da) >0){
            
            this.dtcons_da = dtcons_da;
            
        }

        if (dtcons_a != null && this.dtcons_a == null ){
            
            this.dtcons_a = dtcons_a;

        }else if (dtcons_a != null  && this.dtcons_a != null && dtcons_a.compareTo(this.dtcons_a) < 0){
            
            this.dtcons_a = dtcons_a;
            
        }



        return rc;

    }

    public ResultSet getCdpagame  ( String    cdstagione
                                  , int       annostag
                                  , long      tkcarello
                                  , Timestamp dtordi 
                                                          ) throws Exception {
    
                                                    
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;



        l_query   = "";
		l_query  += " select a.cdpagame                              \n";
		l_query  += "      , a.dtdec                                 \n";
		l_query  += "      , a.ggdec                                 \n";
		l_query  += "      , b.dspagame                              \n";
		l_query  += "   from pgmr.kg_articolo_cons a             \n";
		l_query  += "      , pgmr.mac_pagame       b             \n";
		l_query  += "  where a.cdstagione = ?                        \n";
		l_query  += "    and a.annostag   = ?                        \n";
		l_query  += "    and a.cdpagame is not null                  \n";
		l_query  += "    and a.cdpagame = b.cdpagame                 \n";
		l_query  += "    and ? between a.dtord_da and a.dtord_a      \n";
		l_query  += "    and (a.cdarti is null                       \n";
		l_query  += "         or a.cdarti in (                       \n";
		l_query  += "                  select cdarti                 \n";
		l_query  += "                    from web.carrello  \n";
		l_query  += "                   where cdstagione = ?         \n";
		l_query  += "                     and annostag   = ?         \n";
		l_query  += "                     and tkcarrello = ?         \n";
		l_query  += "                  )                             \n";
		l_query  += "         )                                      \n";
		l_query  += "  order by a.cdpagame                           \n";


		pstm = setQuery( l_query ) ;

        ind = 1;

        pstm.setString   (ind, cdstagione); ind +=1 ;
        pstm.setInt      (ind, annostag  ); ind +=1 ;
        pstm.setTimestamp(ind, dtordi    ); ind +=1 ;

        pstm.setString   (ind, cdstagione); ind +=1 ;
        pstm.setInt      (ind, annostag  ); ind +=1 ;
        pstm.setLong     (ind, tkcarello ); ind +=1 ;


        rs = pstm.executeQuery();

        return rs;
    }





    public Timestamp dtcons_da = null;
    public Timestamp dtcons_a  = null;




}


