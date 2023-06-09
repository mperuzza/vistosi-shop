package com.ateikon.internet.eprogen.dao.pgmr;

import com.ateikon.internet.eprogen.domain.pgmr.Archrubrica_condc_note;
import com.ateikon.internet.eprogen.domain.pgmr.Archrubrica_condc_noteExample;
import java.util.List;

public interface Archrubrica_condc_noteDAO {
    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.archrubrica_condc_note
     *
     * @ibatorgenerated Thu Jul 18 12:08:12 CEST 2013
     */
    int countByExample(Archrubrica_condc_noteExample example);

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.archrubrica_condc_note
     *
     * @ibatorgenerated Thu Jul 18 12:08:12 CEST 2013
     */
    int deleteByExample(Archrubrica_condc_noteExample example);

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.archrubrica_condc_note
     *
     * @ibatorgenerated Thu Jul 18 12:08:12 CEST 2013
     */
    int deleteByPrimaryKey(Long tkrubr_condc_n);

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.archrubrica_condc_note
     *
     * @ibatorgenerated Thu Jul 18 12:08:12 CEST 2013
     */
    void insert(Archrubrica_condc_note record);

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.archrubrica_condc_note
     *
     * @ibatorgenerated Thu Jul 18 12:08:12 CEST 2013
     */
    void insertSelective(Archrubrica_condc_note record);

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.archrubrica_condc_note
     *
     * @ibatorgenerated Thu Jul 18 12:08:12 CEST 2013
     */
    List<Archrubrica_condc_note> selectByExample(Archrubrica_condc_noteExample example);

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.archrubrica_condc_note
     *
     * @ibatorgenerated Thu Jul 18 12:08:12 CEST 2013
     */
    Archrubrica_condc_note selectByPrimaryKey(Long tkrubr_condc_n);

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.archrubrica_condc_note
     *
     * @ibatorgenerated Thu Jul 18 12:08:12 CEST 2013
     */
    int updateByExampleSelective(Archrubrica_condc_note record, Archrubrica_condc_noteExample example);

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.archrubrica_condc_note
     *
     * @ibatorgenerated Thu Jul 18 12:08:12 CEST 2013
     */
    int updateByExample(Archrubrica_condc_note record, Archrubrica_condc_noteExample example);

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.archrubrica_condc_note
     *
     * @ibatorgenerated Thu Jul 18 12:08:12 CEST 2013
     */
    int updateByPrimaryKeySelective(Archrubrica_condc_note record);

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.archrubrica_condc_note
     *
     * @ibatorgenerated Thu Jul 18 12:08:12 CEST 2013
     */
    int updateByPrimaryKey(Archrubrica_condc_note record);
}