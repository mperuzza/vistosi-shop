package com.ateikon.internet.generic.dao.ibatis;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ateikon.internet.generic.domain.Keypool;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SqlMapKeypoolDao extends SqlMapClientDaoSupport {

    /** Logger for this class and subclasses */        
    private Log log = LogFactory.getLog(this.getClass());       
  /**
   * This is a generic sequence ID generator that is based on a database
   * table called 'SEQUENCE', which contains two columns (NAME, NEXTID).
   * This approach should work with any database.
   * @param name the name of the sequence
   * @return the next ID
   */
  public int getNextId(String name) throws DataAccessException {
    Keypool keypool = new Keypool(name, -1);
    keypool = (Keypool) getSqlMapClientTemplate().queryForObject("keypool.getKeypool", keypool);
    log.debug("-- " + keypool);
    if (keypool == null) {
      throw new DataRetrievalFailureException("Error: A null sequence was returned from the database (could not get next " + name + " sequence).");
    }
    //Object parameterObject = new Keypool(name, keypool.getNextId() + 1);
    Keypool next = new Keypool(name, keypool.getNextId() + 1);;
    getSqlMapClientTemplate().update("keypool.updateKeypool", next);
    log.debug("-- " + next.getNextId());
    return next.getNextId();
  }

}
