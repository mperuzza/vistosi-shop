package com.ateikon.internet.generic.dao.ibatis;

import java.util.List;
import java.util.Map;
import java.util.Date;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ateikon.internet.generic.dao.NazioneDao;
import com.ateikon.internet.generic.domain.Nazione;
import com.ateikon.internet.generic.dao.ibatis.SqlMapKeypoolDao;
import com.ateikon.internet.utils.AdjustText;

public class SqlMapNazioneDao extends SqlMapClientDaoSupport implements NazioneDao {
    
    private SqlMapKeypoolDao keypoolDao;  
  
    public void setKeypoolDao(SqlMapKeypoolDao keypoolDao) {
        this.keypoolDao = keypoolDao;
    }       

    public List getNazioni() throws DataAccessException {
        return getSqlMapClientTemplate().queryForList("nazioni.getNazioni", null);
    }    
  
    public Nazione getNazione(String cdnazione) throws DataAccessException {
        return (Nazione)getSqlMapClientTemplate().queryForObject("nazioni.getNazioneByCd", cdnazione);
    }
    
    public int deleteItem(String cd) throws DataAccessException {
        return getSqlMapClientTemplate().delete("nazioni.deleteNazioneItem", cd);
    }
    
    public void insertItem(Nazione item) throws DataAccessException {
        String cd = AdjustText.rightAdjustZeroFill(String.valueOf(this.keypoolDao.getNextId("CDNAZIONE")), 10);
        item.setCdnazione(cd);
        item.setCdazie("01");
        item.setCddipa("0000");
        item.setProfil("web");
        item.setDtinse(new Date());
        item.setDtulag(new Date());    
        getSqlMapClientTemplate().insert("nazioni.insertNazioneItem", item);
    }
    
    public int updateItem(Nazione item) throws DataAccessException {
        item.setDtulag(new Date());  
        return getSqlMapClientTemplate().update("nazioni.updateNazioneItem", item);
    }
    
}
