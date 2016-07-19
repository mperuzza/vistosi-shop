package com.ateikon.internet.generic.dao.ibatis;

import com.ateikon.internet.eprogen.dao.ibatis.SqlMapBaseDao;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import com.ateikon.internet.generic.domain.AtkPargen;
import com.ateikon.internet.generic.dao.AtkPargenDao;
import java.util.HashMap;

public class SqlMapAtkPargenDao extends SqlMapBaseDao implements AtkPargenDao {

    public AtkPargen getAtkPargen(String parametro) throws DataAccessException {
        Map pars = new HashMap();
        pars.put( "parametro", parametro);
        return getAtkPargen(pars);
    }    

    public AtkPargen getAtkPargen(Map pars) throws DataAccessException {
        pars.put( "owner_web", getProfil().getOwner_web());
        if(!pars.containsKey("cdazie")){
            pars.put( "cdazie", getProfil().getCdazie());
        }        
        return (AtkPargen)getSqlMapClientTemplate().queryForObject("atkPargen.getAtkPargen", pars);
    }
   
}
