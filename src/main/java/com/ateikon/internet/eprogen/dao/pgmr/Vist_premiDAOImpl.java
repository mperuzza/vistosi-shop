package com.ateikon.internet.eprogen.dao.pgmr;

import com.ateikon.internet.eprogen.domain.pgmr.Vist_premi;
import com.ateikon.internet.eprogen.domain.pgmr.Vist_premiExample;
import com.ateikon.internet.eprogen.domain.pgmr.Vist_premiKey;
import java.util.List;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class Vist_premiDAOImpl extends SqlMapClientDaoSupport implements Vist_premiDAO {

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.vist_premi
     *
     * @ibatorgenerated Fri Jun 12 17:05:36 CEST 2009
     */
    public Vist_premiDAOImpl() {
        super();
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.vist_premi
     *
     * @ibatorgenerated Fri Jun 12 17:05:36 CEST 2009
     */
    public int countByExample(Vist_premiExample example) {
        Integer count = (Integer)  getSqlMapClientTemplate().queryForObject("pgmr_vist_premi.ibatorgenerated_countByExample", example);
        return count;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.vist_premi
     *
     * @ibatorgenerated Fri Jun 12 17:05:36 CEST 2009
     */
    public int deleteByExample(Vist_premiExample example) {
        int rows = getSqlMapClientTemplate().delete("pgmr_vist_premi.ibatorgenerated_deleteByExample", example);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.vist_premi
     *
     * @ibatorgenerated Fri Jun 12 17:05:36 CEST 2009
     */
    public int deleteByPrimaryKey(Vist_premiKey key) {
        int rows = getSqlMapClientTemplate().delete("pgmr_vist_premi.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.vist_premi
     *
     * @ibatorgenerated Fri Jun 12 17:05:36 CEST 2009
     */
    public void insert(Vist_premi record) {
        getSqlMapClientTemplate().insert("pgmr_vist_premi.ibatorgenerated_insert", record);
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.vist_premi
     *
     * @ibatorgenerated Fri Jun 12 17:05:36 CEST 2009
     */
    public void insertSelective(Vist_premi record) {
        getSqlMapClientTemplate().insert("pgmr_vist_premi.ibatorgenerated_insertSelective", record);
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.vist_premi
     *
     * @ibatorgenerated Fri Jun 12 17:05:36 CEST 2009
     */
    @SuppressWarnings("unchecked")
    public List<Vist_premi> selectByExample(Vist_premiExample example) {
        List<Vist_premi> list = getSqlMapClientTemplate().queryForList("pgmr_vist_premi.ibatorgenerated_selectByExample", example);
        return list;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.vist_premi
     *
     * @ibatorgenerated Fri Jun 12 17:05:36 CEST 2009
     */
    public Vist_premi selectByPrimaryKey(Vist_premiKey key) {
        Vist_premi record = (Vist_premi) getSqlMapClientTemplate().queryForObject("pgmr_vist_premi.ibatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.vist_premi
     *
     * @ibatorgenerated Fri Jun 12 17:05:36 CEST 2009
     */
    public int updateByExampleSelective(Vist_premi record, Vist_premiExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("pgmr_vist_premi.ibatorgenerated_updateByExampleSelective", parms);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.vist_premi
     *
     * @ibatorgenerated Fri Jun 12 17:05:36 CEST 2009
     */
    public int updateByExample(Vist_premi record, Vist_premiExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("pgmr_vist_premi.ibatorgenerated_updateByExample", parms);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.vist_premi
     *
     * @ibatorgenerated Fri Jun 12 17:05:36 CEST 2009
     */
    public int updateByPrimaryKeySelective(Vist_premi record) {
        int rows = getSqlMapClientTemplate().update("pgmr_vist_premi.ibatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.vist_premi
     *
     * @ibatorgenerated Fri Jun 12 17:05:36 CEST 2009
     */
    public int updateByPrimaryKey(Vist_premi record) {
        int rows = getSqlMapClientTemplate().update("pgmr_vist_premi.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

    /**
     * This class was generated by Apache iBATIS Ibator.
     * This class corresponds to the database table pgmr.vist_premi
     *
     * @ibatorgenerated Fri Jun 12 17:05:36 CEST 2009
     */
    private static class UpdateByExampleParms extends Vist_premiExample {
        private Object record;

        public UpdateByExampleParms(Object record, Vist_premiExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
}