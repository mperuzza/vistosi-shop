package com.ateikon.internet.generic.dao.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ateikon.internet.generic.dao.ProvinciaDao;
import com.ateikon.internet.generic.domain.Provincia;

public class SqlMapProvinciaDao extends SqlMapClientDaoSupport implements ProvinciaDao {

    public List getProvincie() throws DataAccessException {
        return getSqlMapClientTemplate().queryForList("province.getProvincie", null);
    }    
  
    public Provincia getProvincia(String cdprov) throws DataAccessException {
        return (Provincia)getSqlMapClientTemplate().queryForObject("province.getProvinciaByCd", cdprov);
    }
    
}
