package com.ateikon.internet.generic.dao.ibatis;

import com.ateikon.internet.eprogen.dao.ibatis.SqlMapBaseDao;
import java.util.Map;
import org.springframework.dao.DataAccessException;
import com.ateikon.internet.generic.domain.Costante;
import com.ateikon.internet.generic.dao.CostanteDao;
import java.util.HashMap;

public class SqlMapCostanteDao extends SqlMapBaseDao implements CostanteDao {

    /**
     * Holds value of property prefix.
     */
    private String prefix;

    public Costante getCostante(String costname) throws DataAccessException {
        Map pars = new HashMap();
        pars.put( "cdazie", getProfil().getCdazie());
        pars.put( "costname",  costname);
        return getCostante(pars);
    }    

    public Costante getCostante(Map pars) throws DataAccessException {
        return getCostante(pars, false);
    }
    
    public Costante getCostante(Map pars, boolean usePrefix) throws DataAccessException {
        if(!pars.containsKey("owner")){
            pars.put( "owner", getProfil().getOwner());
        }
        if(usePrefix){
            pars.put( "costname",  this.prefix + (String)pars.get("costname"));
        }
        Costante c = (Costante)getSqlMapClientTemplate().queryForObject("costante.getCostante", pars);
        if(c==null) logger.debug("Costante non trovata: " + pars.toString());
        return (c!=null)?c:new Costante();
    }

    /**
     * Getter for property prefix.
     * @return Value of property prefix.
     */
    public String getPrefix() {

        return this.prefix;
    }

    /**
     * Setter for property prefix.
     * @param prefix New value of property prefix.
     */
    public void setPrefix(String prefix) {

        this.prefix = prefix;
    }
   
}
