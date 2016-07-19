/*
 * SqlMapAziendaDao.java
 *
 * Created on 11 maggio 2005, 9.43
 */

package com.ateikon.internet.generic.dao.ibatis;

import com.ateikon.internet.eprogen.dao.ibatis.SqlMapBaseDao;
import com.ateikon.internet.generic.dao.SysRemoteUserDao;
import com.ateikon.internet.generic.domain.SysRemoteUser;
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
public class SqlMapSysRemoteUserDao extends SqlMapBaseDao implements SysRemoteUserDao{
    
    /** Logger for this class and subclasses */        
    private Log log = LogFactory.getLog(this.getClass());      
    
    public SysRemoteUser getItem(Integer id) throws DataAccessException {
        Map pars = new HashMap();
        pars.put( "user_id", id);
        pars.put( "owner_web", getProfil().getOwner_web());
        return (SysRemoteUser)getSqlMapClientTemplate().queryForObject( "getSysRemoteUser", pars);
    }
    
    public List getItems(Map pars) throws DataAccessException {
        pars.put( "owner_web", getProfil().getOwner_web());
        return getSqlMapClientTemplate().queryForList("getSysRemoteUser", pars);        
    }
    
    public int deleteItem(Integer id) throws DataAccessException {
        //TODO
        return 0;
    }
    
    public int updateItem(SysRemoteUser item) throws DataAccessException {
        //TODO
        return 0;
    }
    
    public void insertItem(SysRemoteUser item) throws DataAccessException {
        //TODO
    }
    
}
