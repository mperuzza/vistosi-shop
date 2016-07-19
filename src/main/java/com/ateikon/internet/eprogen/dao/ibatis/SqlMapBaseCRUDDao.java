/*
 * SqlMapUserDao.java
 *
 * Created on 11 aprile 2005, 18.30
 */

package com.ateikon.internet.eprogen.dao.ibatis;

import com.ateikon.internet.generic.dao.ibatis.SqlMapKeypoolsDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Emiliano
 */
public abstract class SqlMapBaseCRUDDao extends SqlMapBaseDao {
    
    /** Logger for this class and subclasses */        
    public  Log log = LogFactory.getLog(this.getClass());  
    
    private SqlMapKeypoolsDao keypoolsDao;
    
    public void setKeypoolsDao(SqlMapKeypoolsDao keypoolsDao) {
            this.keypoolsDao = keypoolsDao;
    }   

    public SqlMapKeypoolsDao getKeypoolsDao() {
        return keypoolsDao;
    }
    
    
}
