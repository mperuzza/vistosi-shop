package com.ateikon.internet.generic.dao.ibatis;

import com.ateikon.internet.eprogen.dao.ibatis.SqlMapBaseCRUDDao;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Date;

import org.springframework.dao.DataAccessException;
import com.ateikon.internet.generic.domain.UnitaLocale;
import com.ateikon.internet.generic.dao.UnitaLocaleDao;
import com.ateikon.internet.generic.domain.BaseTableBean;
import org.apache.commons.lang.StringUtils;

public class SqlMapUnitaLocaleDao extends SqlMapBaseCRUDDao implements UnitaLocaleDao {

    public UnitaLocale getUnitaLocale(String cdente, String cdusul) throws DataAccessException {
        Map pars = new HashMap();
        pars.put("cdente", cdente);
        pars.put("cdusul", cdusul);
        UnitaLocale ul = (UnitaLocale)getSqlMapClientTemplate().queryForObject("UnitaLocale.getUnitaLocaleByCdusul", pars);

        if(ul == null){
            pars.put("fseleg", "S");
            return  (UnitaLocale)getSqlMapClientTemplate().queryForObject("UnitaLocale.getUnitaLocaleByFseleg", pars);
        }
        return ul;    
    }    

    public List getUnitaLocali(String cdente) throws DataAccessException {
        return getSqlMapClientTemplate().queryForList("UnitaLocale.getUnitaLocali", cdente);
    }   

    public void updateUnitaLocali(UnitaLocale uniloc) throws DataAccessException {
        uniloc.setDtulag(new Date());
        getSqlMapClientTemplate().update("UnitaLocale.updateUniLoc", uniloc);
    } 

    public int deleteItem(UnitaLocale item) throws DataAccessException {
        int rows = getSqlMapClientTemplate().delete("UnitaLocale.deleteItem", item);

        return rows;        
    }

    public int updateItem(UnitaLocale item) throws DataAccessException {
        item.setDtulag(new Date());
        
        int rows = getSqlMapClientTemplate().update("UnitaLocale.updateUniLoc", item);

        return rows;        
    }

    public void insertItem(UnitaLocale item) throws DataAccessException {
        item.setCdunil(StringUtils.leftPad(String.valueOf(getKeypoolsDao().getNextIdGest("UNITALOCALI", (BaseTableBean)item)), 10, "0"));
        item.setCdunil_m(item.getCdunil());
        item.setDtinse(new Date());
        item.setDtulag(new Date());           
        getSqlMapClientTemplate().insert("UnitaLocale.insertUniLoc", item);
        
    }
    
}
