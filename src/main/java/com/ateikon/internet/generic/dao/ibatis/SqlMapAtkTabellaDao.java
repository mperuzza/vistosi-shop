/*
 * SqlMapAziendaDao.java
 *
 * Created on 11 maggio 2005, 9.43
 */

package com.ateikon.internet.generic.dao.ibatis;

import com.ateikon.internet.eprogen.dao.ibatis.SqlMapBaseDao;
import com.ateikon.internet.generic.dao.AtkTabellaDao;
import com.ateikon.internet.generic.domain.AtkTabella;
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
public class SqlMapAtkTabellaDao extends SqlMapBaseDao implements AtkTabellaDao{
    
    /** Logger for this class and subclasses */        
    private Log log = LogFactory.getLog(this.getClass());      
    
    public AtkTabella getItem(String cdtabella, String dsoggetto) throws DataAccessException {
        Map pars = new HashMap();
        pars.put( "cdtabella", cdtabella);
        pars.put( "dsoggetto", dsoggetto);
        pars.put( "owner_web", getProfil().getOwner_web());
        return (AtkTabella)getSqlMapClientTemplate().queryForObject( "getAtkTabella", pars);
    }
    
    public List getItems(Map pars) throws DataAccessException {
        pars.put( "owner_web", getProfil().getOwner_web());
        return getSqlMapClientTemplate().queryForList("getAtkTabella", pars);        
    }
    
    public int deleteItem(String cdtabella, String dsoggetto) throws DataAccessException {
        //TODO
        return 0;
    }
    
    public int updateItem(AtkTabella item) throws DataAccessException {
        //TODO
        return 0;
    }
    
    public void insertItem(AtkTabella item) throws DataAccessException {
        //TODO
    }
    
}
