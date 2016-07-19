package com.ateikon.internet.eprogen.dao.pgmr;

import com.ateikon.internet.eprogen.dao.ibatis.SqlMapBaseCRUDDao;
import com.ateikon.internet.eprogen.domain.pgmr.Wishlist_test;
import com.ateikon.internet.eprogen.domain.pgmr.Wishlist_testExample;
import com.ateikon.internet.eprogen.domain.pgmr.Wishlist_testKey;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Wishlist_testDAOImpl extends SqlMapBaseCRUDDao implements Wishlist_testDAO {

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.wishlist_test
     *
     * @ibatorgenerated Wed May 27 17:58:11 CEST 2009
     */
    public Wishlist_testDAOImpl() {
        super();
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.wishlist_test
     *
     * @ibatorgenerated Wed May 27 17:58:11 CEST 2009
     */
    public int countByExample(Wishlist_testExample example) {
        Integer count = (Integer)  getSqlMapClientTemplate().queryForObject("pgmr_wishlist_test.ibatorgenerated_countByExample", example);
        return count;
    }


    private long nextId(Wishlist_testKey key) {
        Long id = (Long)  getSqlMapClientTemplate().queryForObject("pgmr_wishlist_test.keyGen", key);
        if(id!=null) return ++id;
        else return 1;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.wishlist_test
     *
     * @ibatorgenerated Wed May 27 17:58:11 CEST 2009
     */
    public int deleteByExample(Wishlist_testExample example) {
        int rows = getSqlMapClientTemplate().delete("pgmr_wishlist_test.ibatorgenerated_deleteByExample", example);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.wishlist_test
     *
     * @ibatorgenerated Wed May 27 17:58:11 CEST 2009
     */
    public int deleteByPrimaryKey(Wishlist_testKey key) {
        int rows = getSqlMapClientTemplate().delete("pgmr_wishlist_test.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.wishlist_test
     *
     * @ibatorgenerated Wed May 27 17:58:11 CEST 2009
     */
    public Long insert(Wishlist_test record) {

        record.setId(nextId(record));
        record.setDtinse(new Date());
        record.setDtulag(new Date());
        Object newKey = getSqlMapClientTemplate().insert("pgmr_wishlist_test.ibatorgenerated_insert", record);
        return (Long) newKey;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.wishlist_test
     *
     * @ibatorgenerated Wed May 27 17:58:11 CEST 2009
     */
    public Long insertSelective(Wishlist_test record) {
        record.setId(nextId(record));
        record.setDtinse(new Date());
        record.setDtulag(new Date());
        Object newKey = getSqlMapClientTemplate().insert("pgmr_wishlist_test.ibatorgenerated_insertSelective", record);
        return (Long) newKey;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.wishlist_test
     *
     * @ibatorgenerated Wed May 27 17:58:11 CEST 2009
     */
    @SuppressWarnings("unchecked")
    public List<Wishlist_test> selectByExample(Wishlist_testExample example) {
        List<Wishlist_test> list = getSqlMapClientTemplate().queryForList("pgmr_wishlist_test.ibatorgenerated_selectByExample", example);
        return list;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.wishlist_test
     *
     * @ibatorgenerated Wed May 27 17:58:11 CEST 2009
     */
    public Wishlist_test selectByPrimaryKey(Wishlist_testKey key) {
        Wishlist_test record = (Wishlist_test) getSqlMapClientTemplate().queryForObject("pgmr_wishlist_test.ibatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.wishlist_test
     *
     * @ibatorgenerated Wed May 27 17:58:11 CEST 2009
     */
    public int updateByExampleSelective(Wishlist_test record, Wishlist_testExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        record.setDtulag(new Date());
        int rows = getSqlMapClientTemplate().update("pgmr_wishlist_test.ibatorgenerated_updateByExampleSelective", parms);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.wishlist_test
     *
     * @ibatorgenerated Wed May 27 17:58:11 CEST 2009
     */
    public int updateByExample(Wishlist_test record, Wishlist_testExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        record.setDtulag(new Date());
        int rows = getSqlMapClientTemplate().update("pgmr_wishlist_test.ibatorgenerated_updateByExample", parms);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.wishlist_test
     *
     * @ibatorgenerated Wed May 27 17:58:11 CEST 2009
     */
    public int updateByPrimaryKeySelective(Wishlist_test record) {
        record.setDtulag(new Date());
        int rows = getSqlMapClientTemplate().update("pgmr_wishlist_test.ibatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.wishlist_test
     *
     * @ibatorgenerated Wed May 27 17:58:11 CEST 2009
     */
    public int updateByPrimaryKey(Wishlist_test record) {
        record.setDtulag(new Date());
        int rows = getSqlMapClientTemplate().update("pgmr_wishlist_test.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

    public List<Wishlist_test> searchWishlist(Map pars) {
        List<Wishlist_test> list = getSqlMapClientTemplate().queryForList("pgmr_wishlist_test.searchWishlist", pars);
        return list;
    }



    /**
     * This class was generated by Apache iBATIS Ibator.
     * This class corresponds to the database table pgmr.wishlist_test
     *
     * @ibatorgenerated Wed May 27 17:58:11 CEST 2009
     */
    private static class UpdateByExampleParms extends Wishlist_testExample {
        private Object record;

        public UpdateByExampleParms(Object record, Wishlist_testExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
}