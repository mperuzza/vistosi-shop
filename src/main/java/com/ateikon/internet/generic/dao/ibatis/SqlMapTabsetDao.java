/*
 * SqlMapAziendaDao.java
 *
 * Created on 11 maggio 2005, 9.43
 */

package com.ateikon.internet.generic.dao.ibatis;

import com.ateikon.internet.eprogen.dao.ibatis.SqlMapBaseDao;
import com.ateikon.internet.generic.dao.TabsetDao;
import com.ateikon.internet.generic.domain.Azienda;
import com.ateikon.internet.generic.domain.Tabset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author Emiliano
 */
public class SqlMapTabsetDao extends SqlMapBaseDao implements TabsetDao{
    
    /** Logger for this class and subclasses */        
    private Log log = LogFactory.getLog(this.getClass());      
    
    public Tabset getItem(String tabella) throws DataAccessException {
        Map pars = new HashMap();
        pars.put( "tabella", tabella);
        return (Tabset)getSqlMapClientTemplate().queryForObject( "tabset.getTabset", pars);
    }
    
    public List getItems(Map pars) throws DataAccessException {
        return getSqlMapClientTemplate().queryForList("tabset.getTabset", pars);        
    }
    
    public int deleteItem(String tabella) throws DataAccessException {
        //TODO
        return 0;
    }
    
    public int updateItem(Tabset item) throws DataAccessException {
        //TODO
        return 0;
    }
    
    public void insertItem(Tabset item) throws DataAccessException {
        //TODO
    }
    
    
    
}
