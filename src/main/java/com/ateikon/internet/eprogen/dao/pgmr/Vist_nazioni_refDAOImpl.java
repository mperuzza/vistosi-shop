package com.ateikon.internet.eprogen.dao.pgmr;

import com.ateikon.internet.eprogen.domain.pgmr.Vist_nazioni_ref;
import com.ateikon.internet.eprogen.domain.pgmr.Vist_nazioni_refExample;
import java.util.List;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class Vist_nazioni_refDAOImpl extends SqlMapClientDaoSupport implements Vist_nazioni_refDAO {

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.vist_nazioni_ref
     *
     * @ibatorgenerated Wed Jul 24 17:37:18 CEST 2013
     */
    public Vist_nazioni_refDAOImpl() {
        super();
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.vist_nazioni_ref
     *
     * @ibatorgenerated Wed Jul 24 17:37:18 CEST 2013
     */
    public int countByExample(Vist_nazioni_refExample example) {
        Integer count = (Integer)  getSqlMapClientTemplate().queryForObject("pgmr_vist_nazioni_ref.ibatorgenerated_countByExample", example);
        return count;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.vist_nazioni_ref
     *
     * @ibatorgenerated Wed Jul 24 17:37:18 CEST 2013
     */
    public int deleteByExample(Vist_nazioni_refExample example) {
        int rows = getSqlMapClientTemplate().delete("pgmr_vist_nazioni_ref.ibatorgenerated_deleteByExample", example);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.vist_nazioni_ref
     *
     * @ibatorgenerated Wed Jul 24 17:37:18 CEST 2013
     */
    public int deleteByPrimaryKey(Long tknaziref) {
        Vist_nazioni_ref key = new Vist_nazioni_ref();
        key.setTknaziref(tknaziref);
        int rows = getSqlMapClientTemplate().delete("pgmr_vist_nazioni_ref.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.vist_nazioni_ref
     *
     * @ibatorgenerated Wed Jul 24 17:37:18 CEST 2013
     */
    public void insert(Vist_nazioni_ref record) {
        getSqlMapClientTemplate().insert("pgmr_vist_nazioni_ref.ibatorgenerated_insert", record);
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.vist_nazioni_ref
     *
     * @ibatorgenerated Wed Jul 24 17:37:18 CEST 2013
     */
    public void insertSelective(Vist_nazioni_ref record) {
        getSqlMapClientTemplate().insert("pgmr_vist_nazioni_ref.ibatorgenerated_insertSelective", record);
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.vist_nazioni_ref
     *
     * @ibatorgenerated Wed Jul 24 17:37:18 CEST 2013
     */
    @SuppressWarnings("unchecked")
    public List<Vist_nazioni_ref> selectByExample(Vist_nazioni_refExample example) {
        List<Vist_nazioni_ref> list = getSqlMapClientTemplate().queryForList("pgmr_vist_nazioni_ref.ibatorgenerated_selectByExample", example);
        return list;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.vist_nazioni_ref
     *
     * @ibatorgenerated Wed Jul 24 17:37:18 CEST 2013
     */
    public Vist_nazioni_ref selectByPrimaryKey(Long tknaziref) {
        Vist_nazioni_ref key = new Vist_nazioni_ref();
        key.setTknaziref(tknaziref);
        Vist_nazioni_ref record = (Vist_nazioni_ref) getSqlMapClientTemplate().queryForObject("pgmr_vist_nazioni_ref.ibatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.vist_nazioni_ref
     *
     * @ibatorgenerated Wed Jul 24 17:37:18 CEST 2013
     */
    public int updateByExampleSelective(Vist_nazioni_ref record, Vist_nazioni_refExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("pgmr_vist_nazioni_ref.ibatorgenerated_updateByExampleSelective", parms);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.vist_nazioni_ref
     *
     * @ibatorgenerated Wed Jul 24 17:37:18 CEST 2013
     */
    public int updateByExample(Vist_nazioni_ref record, Vist_nazioni_refExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("pgmr_vist_nazioni_ref.ibatorgenerated_updateByExample", parms);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.vist_nazioni_ref
     *
     * @ibatorgenerated Wed Jul 24 17:37:18 CEST 2013
     */
    public int updateByPrimaryKeySelective(Vist_nazioni_ref record) {
        int rows = getSqlMapClientTemplate().update("pgmr_vist_nazioni_ref.ibatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.vist_nazioni_ref
     *
     * @ibatorgenerated Wed Jul 24 17:37:18 CEST 2013
     */
    public int updateByPrimaryKey(Vist_nazioni_ref record) {
        int rows = getSqlMapClientTemplate().update("pgmr_vist_nazioni_ref.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

    /**
     * This class was generated by Apache iBATIS Ibator.
     * This class corresponds to the database table pgmr.vist_nazioni_ref
     *
     * @ibatorgenerated Wed Jul 24 17:37:18 CEST 2013
     */
    private static class UpdateByExampleParms extends Vist_nazioni_refExample {
        private Object record;

        public UpdateByExampleParms(Object record, Vist_nazioni_refExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
}