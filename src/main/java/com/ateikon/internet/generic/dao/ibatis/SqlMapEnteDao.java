package com.ateikon.internet.generic.dao.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ateikon.internet.generic.dao.EnteDao;
import com.ateikon.internet.generic.domain.Ente;
import java.util.Date;

public class SqlMapEnteDao extends SqlMapClientDaoSupport implements EnteDao {

    public List getItems(Map pars) throws DataAccessException {
        pars.put( "dtfval", new Date());
        return getSqlMapClientTemplate().queryForList("getEnte", pars);
    }    
  
    public Ente getItem(Map pars) throws DataAccessException {
        pars.put( "dtfval", new Date());
        return (Ente)getSqlMapClientTemplate().queryForObject("getEnte", pars);
    }
    
}
