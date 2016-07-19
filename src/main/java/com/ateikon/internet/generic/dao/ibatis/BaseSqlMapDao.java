/*
 * BaseSqlMapDao.java
 *
 * Created on 12 aprile 2005, 16.29
 */

package com.ateikon.internet.generic.dao.ibatis;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 *
 * @author Emiliano
 */
public class BaseSqlMapDao extends SqlMapClientDaoSupport{
    
    private SqlMapKeypoolDao keypoolDao;
               
    public void setKeypoolDao(SqlMapKeypoolDao keypoolDao) {
            this.keypoolDao = keypoolDao;
    }
    
}
