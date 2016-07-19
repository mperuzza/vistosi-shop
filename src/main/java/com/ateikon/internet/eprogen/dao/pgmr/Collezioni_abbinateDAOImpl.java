package com.ateikon.internet.eprogen.dao.pgmr;

import com.ateikon.internet.eprogen.domain.pgmr.Collezioni_abbinateExample;
import com.ateikon.internet.eprogen.domain.pgmr.Collezioni_abbinateKey;
import java.util.List;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class Collezioni_abbinateDAOImpl extends SqlMapClientDaoSupport implements Collezioni_abbinateDAO {

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table pgmr.collezioni_abbinate
     *
     * @ibatorgenerated Mon Feb 23 19:20:39 CET 2009
     */
    public Collezioni_abbinateDAOImpl() {
        super();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table pgmr.collezioni_abbinate
     *
     * @ibatorgenerated Mon Feb 23 19:20:39 CET 2009
     */
    public int countByExample(Collezioni_abbinateExample example) {
        Integer count = (Integer)  getSqlMapClientTemplate().queryForObject("pgmr_collezioni_abbinate.ibatorgenerated_countByExample", example);
        return count;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table pgmr.collezioni_abbinate
     *
     * @ibatorgenerated Mon Feb 23 19:20:39 CET 2009
     */
    public int deleteByExample(Collezioni_abbinateExample example) {
        int rows = getSqlMapClientTemplate().delete("pgmr_collezioni_abbinate.ibatorgenerated_deleteByExample", example);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table pgmr.collezioni_abbinate
     *
     * @ibatorgenerated Mon Feb 23 19:20:39 CET 2009
     */
    public int deleteByPrimaryKey(Collezioni_abbinateKey key) {
        int rows = getSqlMapClientTemplate().delete("pgmr_collezioni_abbinate.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table pgmr.collezioni_abbinate
     *
     * @ibatorgenerated Mon Feb 23 19:20:39 CET 2009
     */
    public void insert(Collezioni_abbinateKey record) {
        getSqlMapClientTemplate().insert("pgmr_collezioni_abbinate.ibatorgenerated_insert", record);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table pgmr.collezioni_abbinate
     *
     * @ibatorgenerated Mon Feb 23 19:20:39 CET 2009
     */
    public void insertSelective(Collezioni_abbinateKey record) {
        getSqlMapClientTemplate().insert("pgmr_collezioni_abbinate.ibatorgenerated_insertSelective", record);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table pgmr.collezioni_abbinate
     *
     * @ibatorgenerated Mon Feb 23 19:20:39 CET 2009
     */
    @SuppressWarnings("unchecked")
    public List<Collezioni_abbinateKey> selectByExample(Collezioni_abbinateExample example) {
        List<Collezioni_abbinateKey> list = getSqlMapClientTemplate().queryForList("pgmr_collezioni_abbinate.ibatorgenerated_selectByExample", example);
        return list;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table pgmr.collezioni_abbinate
     *
     * @ibatorgenerated Mon Feb 23 19:20:39 CET 2009
     */
    public int updateByExampleSelective(Collezioni_abbinateKey record, Collezioni_abbinateExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("pgmr_collezioni_abbinate.ibatorgenerated_updateByExampleSelective", parms);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table pgmr.collezioni_abbinate
     *
     * @ibatorgenerated Mon Feb 23 19:20:39 CET 2009
     */
    public int updateByExample(Collezioni_abbinateKey record, Collezioni_abbinateExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("pgmr_collezioni_abbinate.ibatorgenerated_updateByExample", parms);
        return rows;
    }

    /**
     * This class was generated by Apache iBATIS ibator.
     * This class corresponds to the database table pgmr.collezioni_abbinate
     *
     * @ibatorgenerated Mon Feb 23 19:20:39 CET 2009
     */
    private static class UpdateByExampleParms extends Collezioni_abbinateExample {
        private Object record;

        public UpdateByExampleParms(Object record, Collezioni_abbinateExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
}