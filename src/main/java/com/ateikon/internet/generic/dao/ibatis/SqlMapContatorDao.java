/*
 * SqlMapContatorDao.java
 *
 * Created on 20 aprile 2006, 9.49
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.ateikon.internet.generic.dao.ibatis;

import com.ateikon.internet.eprogen.dao.ibatis.SqlMapBaseDao;
import com.ateikon.internet.generic.dao.ContatorDao;
import com.ateikon.internet.generic.domain.Contator;
import java.util.HashMap;
import java.util.Map;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author Emiliano
 */
public class SqlMapContatorDao extends SqlMapBaseDao implements ContatorDao{
    
    /** Creates a new instance of SqlMapContatorDao */
    public SqlMapContatorDao() {
    }
    
    public int deleteItem(String cdazie, String prname, String annoco) throws DataAccessException {
        Map pars = new HashMap();
        pars.put("cdazie", cdazie);
        pars.put("prname", prname);
        pars.put("annoco", annoco);
        return getSqlMapClientTemplate().delete("contator.deleteItem", pars);
    }
    
    public void insertItem(Contator item) throws DataAccessException {
        getSqlMapClientTemplate().insert("contator.insertItem", item);
    }
    
    public int updateItem(Contator item) throws DataAccessException {
        return getSqlMapClientTemplate().update("contator.updateItem", item);
    } 
    
    private Contator selectByPars(Map pars){
    
        return (Contator)getSqlMapClientTemplate().queryForObject("contator.selectContator", pars);
    }
    
    private int checkContatorExists(Map pars){
        
        Integer count = (Integer)getSqlMapClientTemplate().queryForObject( "contator.ckExists", pars);
        
        if(count!=null) return count.intValue();
        else return 0;
    }
    
    public long newProg(String cdazie, String prname, String annoco){
        Map pars = new HashMap();
        pars.put("cdazie", cdazie);
        pars.put("prname", prname);
        pars.put("annoco", annoco);  
        
        int num_rig = checkContatorExists(pars);
        if(num_rig==0){
            Contator newc = new Contator();
            newc.setCdazie(cdazie);
            newc.setPrname(prname);
            newc.setAnnoco(annoco);
            newc.setNprogr(new Long(0));
            insertItem(newc);
        }
        
        Contator c = selectByPars(pars);
        long num_prog = c.getNprogr().longValue();
        num_prog = num_prog + 1;
        c.setNprogr(new Long(num_prog));
		
        updateItem(c);
        
        return num_prog;
    }
}
