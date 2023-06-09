package com.ateikon.internet.eprogen.dao.pgmr;

import com.ateikon.internet.eprogen.domain.pgmr.Assofiscal;
import com.ateikon.internet.eprogen.domain.pgmr.AssofiscalExample;
import java.util.List;

public interface AssofiscalDAO {
    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.assofiscal
     *
     * @ibatorgenerated Mon Jul 27 09:43:37 CEST 2009
     */
    int countByExample(AssofiscalExample example);

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.assofiscal
     *
     * @ibatorgenerated Mon Jul 27 09:43:37 CEST 2009
     */
    int deleteByExample(AssofiscalExample example);

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.assofiscal
     *
     * @ibatorgenerated Mon Jul 27 09:43:37 CEST 2009
     */
    int deleteByPrimaryKey(String cdfisc);

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.assofiscal
     *
     * @ibatorgenerated Mon Jul 27 09:43:37 CEST 2009
     */
    void insert(Assofiscal record);

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.assofiscal
     *
     * @ibatorgenerated Mon Jul 27 09:43:37 CEST 2009
     */
    void insertSelective(Assofiscal record);

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.assofiscal
     *
     * @ibatorgenerated Mon Jul 27 09:43:37 CEST 2009
     */
    List<Assofiscal> selectByExample(AssofiscalExample example);

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.assofiscal
     *
     * @ibatorgenerated Mon Jul 27 09:43:37 CEST 2009
     */
    Assofiscal selectByPrimaryKey(String cdfisc);

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.assofiscal
     *
     * @ibatorgenerated Mon Jul 27 09:43:37 CEST 2009
     */
    int updateByExampleSelective(Assofiscal record, AssofiscalExample example);

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.assofiscal
     *
     * @ibatorgenerated Mon Jul 27 09:43:37 CEST 2009
     */
    int updateByExample(Assofiscal record, AssofiscalExample example);

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.assofiscal
     *
     * @ibatorgenerated Mon Jul 27 09:43:37 CEST 2009
     */
    int updateByPrimaryKeySelective(Assofiscal record);

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.assofiscal
     *
     * @ibatorgenerated Mon Jul 27 09:43:37 CEST 2009
     */
    int updateByPrimaryKey(Assofiscal record);
}