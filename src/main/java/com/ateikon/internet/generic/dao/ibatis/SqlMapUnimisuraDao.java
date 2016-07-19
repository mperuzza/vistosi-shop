package com.ateikon.internet.generic.dao.ibatis;

import com.ateikon.internet.generic.dao.UnimisuraDao;
import java.util.List;
import java.util.Date;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import com.ateikon.internet.generic.domain.Unimisura;
import com.ateikon.internet.utils.AdjustText;

public class SqlMapUnimisuraDao extends SqlMapClientDaoSupport implements UnimisuraDao {
    
    private SqlMapKeypoolDao keypoolDao;  
  
    public void setKeypoolDao(SqlMapKeypoolDao keypoolDao) {
        this.keypoolDao = keypoolDao;
    }       

    public List getItems() throws DataAccessException {
        return getSqlMapClientTemplate().queryForList("Unimisura.getUnimisura", null);
    }    
  
    public Unimisura getItem(String cdunim) throws DataAccessException {
        return (Unimisura)getSqlMapClientTemplate().queryForObject("Unimisura.getUnimisuraByCd", cdunim);
    }
    
    public int deleteItem(String cd) throws DataAccessException {
        return getSqlMapClientTemplate().delete("Unimisura.deleteUnimisuraItem", cd);
    }
    
    public void insertItem(Unimisura item) throws DataAccessException {
       /* String cd = AdjustText.rightAdjustZeroFill(String.valueOf(this.keypoolDao.getNextId("CDUNIM")), 10);
        item.setCdunim(cd);
        //item.setCdazie("01");
        //item.setCddipa("0000");
        //item.setProfil("web");
        item.setDtinse(new Date());
        item.setDtulag(new Date());    
        getSqlMapClientTemplate().insert("insertNazioneItem", item);
        */
    }
    
    public int updateItem(Unimisura item) throws DataAccessException {
        item.setDtulag(new Date());  
        return getSqlMapClientTemplate().update("Unimisura.updateUnimisuraItem", item);
    }
    
}
