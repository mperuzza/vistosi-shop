package com.ateikon.internet.eprogen.dao.pgmr;

import com.ateikon.internet.eprogen.domain.pgmr.Cms_promozioni;
import com.ateikon.internet.eprogen.domain.pgmr.Cms_promozioniExample;
import java.util.List;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class Cms_promozioniDAOImpl extends SqlMapClientDaoSupport implements Cms_promozioniDAO {

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.cms_promozioni
     *
     * @ibatorgenerated Tue Sep 29 18:12:49 CEST 2009
     */
    public Cms_promozioniDAOImpl() {
        super();
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.cms_promozioni
     *
     * @ibatorgenerated Tue Sep 29 18:12:49 CEST 2009
     */
    public int countByExample(Cms_promozioniExample example) {
        Integer count = (Integer)  getSqlMapClientTemplate().queryForObject("pgmr_cms_promozioni.ibatorgenerated_countByExample", example);
        return count;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.cms_promozioni
     *
     * @ibatorgenerated Tue Sep 29 18:12:49 CEST 2009
     */
    public int deleteByExample(Cms_promozioniExample example) {
        int rows = getSqlMapClientTemplate().delete("pgmr_cms_promozioni.ibatorgenerated_deleteByExample", example);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.cms_promozioni
     *
     * @ibatorgenerated Tue Sep 29 18:12:49 CEST 2009
     */
    public int deleteByPrimaryKey(Long tkpromo) {
        Cms_promozioni key = new Cms_promozioni();
        key.setTkpromo(tkpromo);
        int rows = getSqlMapClientTemplate().delete("pgmr_cms_promozioni.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.cms_promozioni
     *
     * @ibatorgenerated Tue Sep 29 18:12:49 CEST 2009
     */
    public void insert(Cms_promozioni record) {
        getSqlMapClientTemplate().insert("pgmr_cms_promozioni.ibatorgenerated_insert", record);
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.cms_promozioni
     *
     * @ibatorgenerated Tue Sep 29 18:12:49 CEST 2009
     */
    public void insertSelective(Cms_promozioni record) {
        getSqlMapClientTemplate().insert("pgmr_cms_promozioni.ibatorgenerated_insertSelective", record);
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.cms_promozioni
     *
     * @ibatorgenerated Tue Sep 29 18:12:49 CEST 2009
     */
    @SuppressWarnings("unchecked")
    public List<Cms_promozioni> selectByExample(Cms_promozioniExample example) {
        List<Cms_promozioni> list = getSqlMapClientTemplate().queryForList("pgmr_cms_promozioni.ibatorgenerated_selectByExample", example);
        return list;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.cms_promozioni
     *
     * @ibatorgenerated Tue Sep 29 18:12:49 CEST 2009
     */
    public Cms_promozioni selectByPrimaryKey(Long tkpromo) {
        Cms_promozioni key = new Cms_promozioni();
        key.setTkpromo(tkpromo);
        Cms_promozioni record = (Cms_promozioni) getSqlMapClientTemplate().queryForObject("pgmr_cms_promozioni.ibatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.cms_promozioni
     *
     * @ibatorgenerated Tue Sep 29 18:12:49 CEST 2009
     */
    public int updateByExampleSelective(Cms_promozioni record, Cms_promozioniExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("pgmr_cms_promozioni.ibatorgenerated_updateByExampleSelective", parms);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.cms_promozioni
     *
     * @ibatorgenerated Tue Sep 29 18:12:49 CEST 2009
     */
    public int updateByExample(Cms_promozioni record, Cms_promozioniExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("pgmr_cms_promozioni.ibatorgenerated_updateByExample", parms);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.cms_promozioni
     *
     * @ibatorgenerated Tue Sep 29 18:12:49 CEST 2009
     */
    public int updateByPrimaryKeySelective(Cms_promozioni record) {
        int rows = getSqlMapClientTemplate().update("pgmr_cms_promozioni.ibatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.cms_promozioni
     *
     * @ibatorgenerated Tue Sep 29 18:12:49 CEST 2009
     */
    public int updateByPrimaryKey(Cms_promozioni record) {
        int rows = getSqlMapClientTemplate().update("pgmr_cms_promozioni.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

    /**
     * This class was generated by Apache iBATIS Ibator.
     * This class corresponds to the database table pgmr.cms_promozioni
     *
     * @ibatorgenerated Tue Sep 29 18:12:49 CEST 2009
     */
    private static class UpdateByExampleParms extends Cms_promozioniExample {
        private Object record;

        public UpdateByExampleParms(Object record, Cms_promozioniExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
}