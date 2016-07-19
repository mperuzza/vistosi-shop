/*
 * ContatorDao.java
 *
 * Created on 20 aprile 2006, 10.35
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.ateikon.internet.generic.dao;

import org.springframework.dao.DataAccessException;

/**
 *
 * @author Emiliano
 */
public interface ContatorDao {
    
    long newProg(String cdazie, String prname, String annoco) throws DataAccessException;
    
}
