package com.ateikon.internet.eprogen.dao.pgmr;

import com.ateikon.internet.eprogen.domain.pgmr.Tipo_finitura;
import com.ateikon.internet.eprogen.domain.pgmr.Tipo_finituraExample;
import com.ateikon.internet.eprogen.domain.pgmr.Tipo_finituraKey;
import java.util.List;

public interface Tipo_finituraDAO {
    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table pgmr.tipo_finitura
     *
     * @ibatorgenerated Mon Feb 23 19:20:39 CET 2009
     */
    int countByExample(Tipo_finituraExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table pgmr.tipo_finitura
     *
     * @ibatorgenerated Mon Feb 23 19:20:39 CET 2009
     */
    int deleteByExample(Tipo_finituraExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table pgmr.tipo_finitura
     *
     * @ibatorgenerated Mon Feb 23 19:20:39 CET 2009
     */
    int deleteByPrimaryKey(Tipo_finituraKey key);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table pgmr.tipo_finitura
     *
     * @ibatorgenerated Mon Feb 23 19:20:39 CET 2009
     */
    void insert(Tipo_finitura record);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table pgmr.tipo_finitura
     *
     * @ibatorgenerated Mon Feb 23 19:20:39 CET 2009
     */
    void insertSelective(Tipo_finitura record);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table pgmr.tipo_finitura
     *
     * @ibatorgenerated Mon Feb 23 19:20:39 CET 2009
     */
    List<Tipo_finitura> selectByExample(Tipo_finituraExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table pgmr.tipo_finitura
     *
     * @ibatorgenerated Mon Feb 23 19:20:39 CET 2009
     */
    Tipo_finitura selectByPrimaryKey(Tipo_finituraKey key);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table pgmr.tipo_finitura
     *
     * @ibatorgenerated Mon Feb 23 19:20:39 CET 2009
     */
    int updateByExampleSelective(Tipo_finitura record, Tipo_finituraExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table pgmr.tipo_finitura
     *
     * @ibatorgenerated Mon Feb 23 19:20:39 CET 2009
     */
    int updateByExample(Tipo_finitura record, Tipo_finituraExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table pgmr.tipo_finitura
     *
     * @ibatorgenerated Mon Feb 23 19:20:39 CET 2009
     */
    int updateByPrimaryKeySelective(Tipo_finitura record);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table pgmr.tipo_finitura
     *
     * @ibatorgenerated Mon Feb 23 19:20:39 CET 2009
     */
    int updateByPrimaryKey(Tipo_finitura record);
}