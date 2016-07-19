/*
 * SqlMapAziendaDao.java
 *
 * Created on 11 maggio 2005, 9.43
 */

package com.ateikon.internet.generic.dao.ibatis;

import com.ateikon.internet.eprogen.dao.ibatis.SqlMapBaseDao;
import com.ateikon.internet.generic.dao.AziendaDao;
import com.ateikon.internet.generic.domain.Azienda;
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
public class SqlMapAziendaDao extends SqlMapBaseDao implements AziendaDao{
    
    /** Logger for this class and subclasses */        
    private Log log = LogFactory.getLog(this.getClass());      
    
    public Azienda getItem(String cd) throws DataAccessException {
        Map pars = new HashMap();
        pars.put( "cdazie", cd);
        return (Azienda)getSqlMapClientTemplate().queryForObject( "getAziende", pars);
    }
    
    public List getItems(Map pars) throws DataAccessException {
        return getSqlMapClientTemplate().queryForList("getAziende", pars);        
    }
    
    public int deleteItem(String cd) throws DataAccessException {
        //TODO
        return 0;
    }
    
    public int updateItem(Azienda item) throws DataAccessException {
        //TODO
        return 0;
    }
    
    public void insertItem(Azienda item) throws DataAccessException {
        //TODO
    }
    
    
    
}
