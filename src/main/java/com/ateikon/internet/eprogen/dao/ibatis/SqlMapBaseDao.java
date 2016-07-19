/*
 * SqlMapUserDao.java
 *
 * Created on 11 aprile 2005, 18.30
 */

package com.ateikon.internet.eprogen.dao.ibatis;

import com.ateikon.internet.generic.dao.CostanteDao;
import com.ateikon.internet.generic.domain.Profil;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;


/**
 *
 * @author Emiliano
 */
public abstract class SqlMapBaseDao extends SqlMapClientDaoSupport {
    
    /** Logger for this class and subclasses */        
    private Log log = LogFactory.getLog(this.getClass());  
    
    private Profil profil;
    
    public final void setProfil(Profil profil){
        this.profil = profil;        
    }
    
    public final Profil getProfil(){
        return this.profil;        
    }    
    
    
}
