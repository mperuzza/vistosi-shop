package com.ateikon.internet.eprogen.dao.pgmr;

import com.ateikon.internet.eprogen.domain.pgmr.VoceSelezioneItem;
import com.ateikon.internet.eprogen.domain.pgmr.Voce_selezione;
import com.ateikon.internet.eprogen.domain.pgmr.Voce_selezioneExample;
import com.ateikon.internet.eprogen.domain.pgmr.Voce_selezioneKey;
import java.util.List;
import java.util.Map;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class Voce_selezioneDAOImpl extends SqlMapClientDaoSupport implements Voce_selezioneDAO {

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table pgmr.voce_selezione
     *
     * @ibatorgenerated Mon Feb 23 19:20:40 CET 2009
     */
    public Voce_selezioneDAOImpl() {
        super();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table pgmr.voce_selezione
     *
     * @ibatorgenerated Mon Feb 23 19:20:40 CET 2009
     */
    public int countByExample(Voce_selezioneExample example) {
        Integer count = (Integer)  getSqlMapClientTemplate().queryForObject("pgmr_voce_selezione.ibatorgenerated_countByExample", example);
        return count;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table pgmr.voce_selezione
     *
     * @ibatorgenerated Mon Feb 23 19:20:40 CET 2009
     */
    public int deleteByExample(Voce_selezioneExample example) {
        int rows = getSqlMapClientTemplate().delete("pgmr_voce_selezione.ibatorgenerated_deleteByExample", example);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table pgmr.voce_selezione
     *
     * @ibatorgenerated Mon Feb 23 19:20:40 CET 2009
     */
    public int deleteByPrimaryKey(Voce_selezioneKey key) {
        int rows = getSqlMapClientTemplate().delete("pgmr_voce_selezione.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table pgmr.voce_selezione
     *
     * @ibatorgenerated Mon Feb 23 19:20:40 CET 2009
     */
    public void insert(Voce_selezione record) {
        getSqlMapClientTemplate().insert("pgmr_voce_selezione.ibatorgenerated_insert", record);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table pgmr.voce_selezione
     *
     * @ibatorgenerated Mon Feb 23 19:20:40 CET 2009
     */
    public void insertSelective(Voce_selezione record) {
        getSqlMapClientTemplate().insert("pgmr_voce_selezione.ibatorgenerated_insertSelective", record);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table pgmr.voce_selezione
     *
     * @ibatorgenerated Mon Feb 23 19:20:40 CET 2009
     */
    @SuppressWarnings("unchecked")
    public List<Voce_selezione> selectByExample(Voce_selezioneExample example) {
        List<Voce_selezione> list = getSqlMapClientTemplate().queryForList("pgmr_voce_selezione.ibatorgenerated_selectByExample", example);
        return list;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table pgmr.voce_selezione
     *
     * @ibatorgenerated Mon Feb 23 19:20:40 CET 2009
     */
    public Voce_selezione selectByPrimaryKey(Voce_selezioneKey key) {
        Voce_selezione record = (Voce_selezione) getSqlMapClientTemplate().queryForObject("pgmr_voce_selezione.ibatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table pgmr.voce_selezione
     *
     * @ibatorgenerated Mon Feb 23 19:20:40 CET 2009
     */
    public int updateByExampleSelective(Voce_selezione record, Voce_selezioneExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("pgmr_voce_selezione.ibatorgenerated_updateByExampleSelective", parms);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table pgmr.voce_selezione
     *
     * @ibatorgenerated Mon Feb 23 19:20:40 CET 2009
     */
    public int updateByExample(Voce_selezione record, Voce_selezioneExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("pgmr_voce_selezione.ibatorgenerated_updateByExample", parms);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table pgmr.voce_selezione
     *
     * @ibatorgenerated Mon Feb 23 19:20:40 CET 2009
     */
    public int updateByPrimaryKeySelective(Voce_selezione record) {
        int rows = getSqlMapClientTemplate().update("pgmr_voce_selezione.ibatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table pgmr.voce_selezione
     *
     * @ibatorgenerated Mon Feb 23 19:20:40 CET 2009
     */
    public int updateByPrimaryKey(Voce_selezione record) {
        int rows = getSqlMapClientTemplate().update("pgmr_voce_selezione.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

    public List<VoceSelezioneItem> getVoci(Map pars) {
         List<VoceSelezioneItem> list = getSqlMapClientTemplate().queryForList("pgmr_voce_selezione.getVoceSelezione", pars);
        return list;
    }



    /**
     * This class was generated by Apache iBATIS ibator.
     * This class corresponds to the database table pgmr.voce_selezione
     *
     * @ibatorgenerated Mon Feb 23 19:20:40 CET 2009
     */
    private static class UpdateByExampleParms extends Voce_selezioneExample {
        private Object record;

        public UpdateByExampleParms(Object record, Voce_selezioneExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
}