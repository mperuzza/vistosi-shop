package com.ateikon.internet.eprogen.dao.pgmr;

import com.ateikon.internet.eprogen.domain.pgmr.Atk_lingua;
import com.ateikon.internet.eprogen.domain.pgmr.Atk_linguaExample;
import java.util.List;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class Atk_linguaDAOImpl extends SqlMapClientDaoSupport implements Atk_linguaDAO {

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.atk_lingua
     *
     * @ibatorgenerated Tue Nov 26 17:29:40 CET 2013
     */
    public Atk_linguaDAOImpl() {
        super();
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.atk_lingua
     *
     * @ibatorgenerated Tue Nov 26 17:29:40 CET 2013
     */
    public int countByExample(Atk_linguaExample example) {
        Integer count = (Integer)  getSqlMapClientTemplate().queryForObject("pgmr_atk_lingua.ibatorgenerated_countByExample", example);
        return count;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.atk_lingua
     *
     * @ibatorgenerated Tue Nov 26 17:29:40 CET 2013
     */
    public int deleteByExample(Atk_linguaExample example) {
        int rows = getSqlMapClientTemplate().delete("pgmr_atk_lingua.ibatorgenerated_deleteByExample", example);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.atk_lingua
     *
     * @ibatorgenerated Tue Nov 26 17:29:40 CET 2013
     */
    public int deleteByPrimaryKey(String cdling) {
        Atk_lingua key = new Atk_lingua();
        key.setCdling(cdling);
        int rows = getSqlMapClientTemplate().delete("pgmr_atk_lingua.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.atk_lingua
     *
     * @ibatorgenerated Tue Nov 26 17:29:40 CET 2013
     */
    public void insert(Atk_lingua record) {
        getSqlMapClientTemplate().insert("pgmr_atk_lingua.ibatorgenerated_insert", record);
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.atk_lingua
     *
     * @ibatorgenerated Tue Nov 26 17:29:40 CET 2013
     */
    public void insertSelective(Atk_lingua record) {
        getSqlMapClientTemplate().insert("pgmr_atk_lingua.ibatorgenerated_insertSelective", record);
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.atk_lingua
     *
     * @ibatorgenerated Tue Nov 26 17:29:40 CET 2013
     */
    @SuppressWarnings("unchecked")
    public List<Atk_lingua> selectByExample(Atk_linguaExample example) {
        List<Atk_lingua> list = getSqlMapClientTemplate().queryForList("pgmr_atk_lingua.ibatorgenerated_selectByExample", example);
        return list;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.atk_lingua
     *
     * @ibatorgenerated Tue Nov 26 17:29:40 CET 2013
     */
    public Atk_lingua selectByPrimaryKey(String cdling) {
        Atk_lingua key = new Atk_lingua();
        key.setCdling(cdling);
        Atk_lingua record = (Atk_lingua) getSqlMapClientTemplate().queryForObject("pgmr_atk_lingua.ibatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.atk_lingua
     *
     * @ibatorgenerated Tue Nov 26 17:29:40 CET 2013
     */
    public int updateByExampleSelective(Atk_lingua record, Atk_linguaExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("pgmr_atk_lingua.ibatorgenerated_updateByExampleSelective", parms);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.atk_lingua
     *
     * @ibatorgenerated Tue Nov 26 17:29:40 CET 2013
     */
    public int updateByExample(Atk_lingua record, Atk_linguaExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("pgmr_atk_lingua.ibatorgenerated_updateByExample", parms);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.atk_lingua
     *
     * @ibatorgenerated Tue Nov 26 17:29:40 CET 2013
     */
    public int updateByPrimaryKeySelective(Atk_lingua record) {
        int rows = getSqlMapClientTemplate().update("pgmr_atk_lingua.ibatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.atk_lingua
     *
     * @ibatorgenerated Tue Nov 26 17:29:40 CET 2013
     */
    public int updateByPrimaryKey(Atk_lingua record) {
        int rows = getSqlMapClientTemplate().update("pgmr_atk_lingua.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

    /**
     * This class was generated by Apache iBATIS Ibator.
     * This class corresponds to the database table pgmr.atk_lingua
     *
     * @ibatorgenerated Tue Nov 26 17:29:40 CET 2013
     */
    private static class UpdateByExampleParms extends Atk_linguaExample {
        private Object record;

        public UpdateByExampleParms(Object record, Atk_linguaExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
}