package com.ateikon.internet.eprogen.dao.pgmr;

import com.ateikon.internet.eprogen.dao.ibatis.SqlMapBaseCRUDDao;
import com.ateikon.internet.eprogen.domain.pgmr.Wishlist_posi;
import com.ateikon.internet.eprogen.domain.pgmr.Wishlist_posiExample;
import com.ateikon.internet.eprogen.domain.pgmr.Wishlist_posiKey;
import java.util.Date;
import java.util.List;

public class Wishlist_posiDAOImpl extends SqlMapBaseCRUDDao implements Wishlist_posiDAO {

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.wishlist_posi
     *
     * @ibatorgenerated Wed May 27 17:58:11 CEST 2009
     */
    public Wishlist_posiDAOImpl() {
        super();
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.wishlist_posi
     *
     * @ibatorgenerated Wed May 27 17:58:11 CEST 2009
     */
    public int countByExample(Wishlist_posiExample example) {
        Integer count = (Integer)  getSqlMapClientTemplate().queryForObject("pgmr_wishlist_posi.ibatorgenerated_countByExample", example);
        return count;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.wishlist_posi
     *
     * @ibatorgenerated Wed May 27 17:58:11 CEST 2009
     */
    public int deleteByExample(Wishlist_posiExample example) {
        int rows = getSqlMapClientTemplate().delete("pgmr_wishlist_posi.ibatorgenerated_deleteByExample", example);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.wishlist_posi
     *
     * @ibatorgenerated Wed May 27 17:58:11 CEST 2009
     */
    public int deleteByPrimaryKey(Wishlist_posiKey key) {
        int rows = getSqlMapClientTemplate().delete("pgmr_wishlist_posi.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    private long nextId(Wishlist_posiKey key) {
        Long id = (Long)  getSqlMapClientTemplate().queryForObject("pgmr_wishlist_posi.keyGen", key);
        if(id!=null) return ++id;
        else return 1;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.wishlist_posi
     *
     * @ibatorgenerated Wed May 27 17:58:11 CEST 2009
     */
    public Long insert(Wishlist_posi record) {
        record.setId(nextId(record));
        record.setDtinse(new Date());
        record.setDtulag(new Date());
        Object newKey = getSqlMapClientTemplate().insert("pgmr_wishlist_posi.ibatorgenerated_insert", record);
        return (Long) newKey;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.wishlist_posi
     *
     * @ibatorgenerated Wed May 27 17:58:11 CEST 2009
     */
    public Long insertSelective(Wishlist_posi record) {
        record.setId(nextId(record));
        record.setDtinse(new Date());
        record.setDtulag(new Date());
        Object newKey = getSqlMapClientTemplate().insert("pgmr_wishlist_posi.ibatorgenerated_insertSelective", record);
        return (Long) newKey;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.wishlist_posi
     *
     * @ibatorgenerated Wed May 27 17:58:11 CEST 2009
     */
    @SuppressWarnings("unchecked")
    public List<Wishlist_posi> selectByExample(Wishlist_posiExample example) {
        List<Wishlist_posi> list = getSqlMapClientTemplate().queryForList("pgmr_wishlist_posi.ibatorgenerated_selectByExample", example);
        return list;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.wishlist_posi
     *
     * @ibatorgenerated Wed May 27 17:58:11 CEST 2009
     */
    public Wishlist_posi selectByPrimaryKey(Wishlist_posiKey key) {
        Wishlist_posi record = (Wishlist_posi) getSqlMapClientTemplate().queryForObject("pgmr_wishlist_posi.ibatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.wishlist_posi
     *
     * @ibatorgenerated Wed May 27 17:58:11 CEST 2009
     */
    public int updateByExampleSelective(Wishlist_posi record, Wishlist_posiExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        record.setDtulag(new Date());
        int rows = getSqlMapClientTemplate().update("pgmr_wishlist_posi.ibatorgenerated_updateByExampleSelective", parms);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.wishlist_posi
     *
     * @ibatorgenerated Wed May 27 17:58:11 CEST 2009
     */
    public int updateByExample(Wishlist_posi record, Wishlist_posiExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        record.setDtulag(new Date());
        int rows = getSqlMapClientTemplate().update("pgmr_wishlist_posi.ibatorgenerated_updateByExample", parms);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.wishlist_posi
     *
     * @ibatorgenerated Wed May 27 17:58:11 CEST 2009
     */
    public int updateByPrimaryKeySelective(Wishlist_posi record) {
        record.setDtulag(new Date());
        int rows = getSqlMapClientTemplate().update("pgmr_wishlist_posi.ibatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.wishlist_posi
     *
     * @ibatorgenerated Wed May 27 17:58:11 CEST 2009
     */
    public int updateByPrimaryKey(Wishlist_posi record) {
        record.setDtulag(new Date());
        int rows = getSqlMapClientTemplate().update("pgmr_wishlist_posi.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

    /**
     * This class was generated by Apache iBATIS Ibator.
     * This class corresponds to the database table pgmr.wishlist_posi
     *
     * @ibatorgenerated Wed May 27 17:58:11 CEST 2009
     */
    private static class UpdateByExampleParms extends Wishlist_posiExample {
        private Object record;

        public UpdateByExampleParms(Object record, Wishlist_posiExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
}