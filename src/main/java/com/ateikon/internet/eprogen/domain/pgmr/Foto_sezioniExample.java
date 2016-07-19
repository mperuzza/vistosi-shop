package com.ateikon.internet.eprogen.domain.pgmr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Foto_sezioniExample {
    /**
     * This field was generated by Apache iBATIS Ibator.
     * This field corresponds to the database table pgmr.Foto_Sezioni
     *
     * @ibatorgenerated Mon Apr 20 11:33:53 CEST 2009
     */
    protected String orderByClause;

    /**
     * This field was generated by Apache iBATIS Ibator.
     * This field corresponds to the database table pgmr.Foto_Sezioni
     *
     * @ibatorgenerated Mon Apr 20 11:33:53 CEST 2009
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.Foto_Sezioni
     *
     * @ibatorgenerated Mon Apr 20 11:33:53 CEST 2009
     */
    public Foto_sezioniExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.Foto_Sezioni
     *
     * @ibatorgenerated Mon Apr 20 11:33:53 CEST 2009
     */
    protected Foto_sezioniExample(Foto_sezioniExample example) {
        this.orderByClause = example.orderByClause;
        this.oredCriteria = example.oredCriteria;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.Foto_Sezioni
     *
     * @ibatorgenerated Mon Apr 20 11:33:53 CEST 2009
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.Foto_Sezioni
     *
     * @ibatorgenerated Mon Apr 20 11:33:53 CEST 2009
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.Foto_Sezioni
     *
     * @ibatorgenerated Mon Apr 20 11:33:53 CEST 2009
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.Foto_Sezioni
     *
     * @ibatorgenerated Mon Apr 20 11:33:53 CEST 2009
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.Foto_Sezioni
     *
     * @ibatorgenerated Mon Apr 20 11:33:53 CEST 2009
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.Foto_Sezioni
     *
     * @ibatorgenerated Mon Apr 20 11:33:53 CEST 2009
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.Foto_Sezioni
     *
     * @ibatorgenerated Mon Apr 20 11:33:53 CEST 2009
     */
    public void clear() {
        oredCriteria.clear();
    }

    /**
     * This class was generated by Apache iBATIS Ibator.
     * This class corresponds to the database table pgmr.Foto_Sezioni
     *
     * @ibatorgenerated Mon Apr 20 11:33:53 CEST 2009
     */
    public static class Criteria {
        protected List<String> criteriaWithoutValue;

        protected List<Map<String, Object>> criteriaWithSingleValue;

        protected List<Map<String, Object>> criteriaWithListValue;

        protected List<Map<String, Object>> criteriaWithBetweenValue;

        protected Criteria() {
            super();
            criteriaWithoutValue = new ArrayList<String>();
            criteriaWithSingleValue = new ArrayList<Map<String, Object>>();
            criteriaWithListValue = new ArrayList<Map<String, Object>>();
            criteriaWithBetweenValue = new ArrayList<Map<String, Object>>();
        }

        public boolean isValid() {
            return criteriaWithoutValue.size() > 0
                || criteriaWithSingleValue.size() > 0
                || criteriaWithListValue.size() > 0
                || criteriaWithBetweenValue.size() > 0;
        }

        public List<String> getCriteriaWithoutValue() {
            return criteriaWithoutValue;
        }

        public List<Map<String, Object>> getCriteriaWithSingleValue() {
            return criteriaWithSingleValue;
        }

        public List<Map<String, Object>> getCriteriaWithListValue() {
            return criteriaWithListValue;
        }

        public List<Map<String, Object>> getCriteriaWithBetweenValue() {
            return criteriaWithBetweenValue;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteriaWithoutValue.add(condition);
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("condition", condition);
            map.put("value", value);
            criteriaWithSingleValue.add(map);
        }

        protected void addCriterion(String condition, List<? extends Object> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("condition", condition);
            map.put("values", values);
            criteriaWithListValue.add(map);
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            List<Object> list = new ArrayList<Object>();
            list.add(value1);
            list.add(value2);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("condition", condition);
            map.put("values", list);
            criteriaWithBetweenValue.add(map);
        }

        public Criteria andCdsezioneIsNull() {
            addCriterion("Cdsezione is null");
            return this;
        }

        public Criteria andCdsezioneIsNotNull() {
            addCriterion("Cdsezione is not null");
            return this;
        }

        public Criteria andCdsezioneEqualTo(String value) {
            addCriterion("Cdsezione =", value, "cdsezione");
            return this;
        }

        public Criteria andCdsezioneNotEqualTo(String value) {
            addCriterion("Cdsezione <>", value, "cdsezione");
            return this;
        }

        public Criteria andCdsezioneGreaterThan(String value) {
            addCriterion("Cdsezione >", value, "cdsezione");
            return this;
        }

        public Criteria andCdsezioneGreaterThanOrEqualTo(String value) {
            addCriterion("Cdsezione >=", value, "cdsezione");
            return this;
        }

        public Criteria andCdsezioneLessThan(String value) {
            addCriterion("Cdsezione <", value, "cdsezione");
            return this;
        }

        public Criteria andCdsezioneLessThanOrEqualTo(String value) {
            addCriterion("Cdsezione <=", value, "cdsezione");
            return this;
        }

        public Criteria andCdsezioneLike(String value) {
            addCriterion("Cdsezione like", value, "cdsezione");
            return this;
        }

        public Criteria andCdsezioneNotLike(String value) {
            addCriterion("Cdsezione not like", value, "cdsezione");
            return this;
        }

        public Criteria andCdsezioneIn(List<String> values) {
            addCriterion("Cdsezione in", values, "cdsezione");
            return this;
        }

        public Criteria andCdsezioneNotIn(List<String> values) {
            addCriterion("Cdsezione not in", values, "cdsezione");
            return this;
        }

        public Criteria andCdsezioneBetween(String value1, String value2) {
            addCriterion("Cdsezione between", value1, value2, "cdsezione");
            return this;
        }

        public Criteria andCdsezioneNotBetween(String value1, String value2) {
            addCriterion("Cdsezione not between", value1, value2, "cdsezione");
            return this;
        }

        public Criteria andFotoIsNull() {
            addCriterion("Foto is null");
            return this;
        }

        public Criteria andFotoIsNotNull() {
            addCriterion("Foto is not null");
            return this;
        }

        public Criteria andFotoEqualTo(String value) {
            addCriterion("Foto =", value, "foto");
            return this;
        }

        public Criteria andFotoNotEqualTo(String value) {
            addCriterion("Foto <>", value, "foto");
            return this;
        }

        public Criteria andFotoGreaterThan(String value) {
            addCriterion("Foto >", value, "foto");
            return this;
        }

        public Criteria andFotoGreaterThanOrEqualTo(String value) {
            addCriterion("Foto >=", value, "foto");
            return this;
        }

        public Criteria andFotoLessThan(String value) {
            addCriterion("Foto <", value, "foto");
            return this;
        }

        public Criteria andFotoLessThanOrEqualTo(String value) {
            addCriterion("Foto <=", value, "foto");
            return this;
        }

        public Criteria andFotoLike(String value) {
            addCriterion("Foto like", value, "foto");
            return this;
        }

        public Criteria andFotoNotLike(String value) {
            addCriterion("Foto not like", value, "foto");
            return this;
        }

        public Criteria andFotoIn(List<String> values) {
            addCriterion("Foto in", values, "foto");
            return this;
        }

        public Criteria andFotoNotIn(List<String> values) {
            addCriterion("Foto not in", values, "foto");
            return this;
        }

        public Criteria andFotoBetween(String value1, String value2) {
            addCriterion("Foto between", value1, value2, "foto");
            return this;
        }

        public Criteria andFotoNotBetween(String value1, String value2) {
            addCriterion("Foto not between", value1, value2, "foto");
            return this;
        }

        public Criteria andCdcollezioneIsNull() {
            addCriterion("Cdcollezione is null");
            return this;
        }

        public Criteria andCdcollezioneIsNotNull() {
            addCriterion("Cdcollezione is not null");
            return this;
        }

        public Criteria andCdcollezioneEqualTo(Integer value) {
            addCriterion("Cdcollezione =", value, "cdcollezione");
            return this;
        }

        public Criteria andCdcollezioneNotEqualTo(Integer value) {
            addCriterion("Cdcollezione <>", value, "cdcollezione");
            return this;
        }

        public Criteria andCdcollezioneGreaterThan(Integer value) {
            addCriterion("Cdcollezione >", value, "cdcollezione");
            return this;
        }

        public Criteria andCdcollezioneGreaterThanOrEqualTo(Integer value) {
            addCriterion("Cdcollezione >=", value, "cdcollezione");
            return this;
        }

        public Criteria andCdcollezioneLessThan(Integer value) {
            addCriterion("Cdcollezione <", value, "cdcollezione");
            return this;
        }

        public Criteria andCdcollezioneLessThanOrEqualTo(Integer value) {
            addCriterion("Cdcollezione <=", value, "cdcollezione");
            return this;
        }

        public Criteria andCdcollezioneIn(List<Integer> values) {
            addCriterion("Cdcollezione in", values, "cdcollezione");
            return this;
        }

        public Criteria andCdcollezioneNotIn(List<Integer> values) {
            addCriterion("Cdcollezione not in", values, "cdcollezione");
            return this;
        }

        public Criteria andCdcollezioneBetween(Integer value1, Integer value2) {
            addCriterion("Cdcollezione between", value1, value2, "cdcollezione");
            return this;
        }

        public Criteria andCdcollezioneNotBetween(Integer value1, Integer value2) {
            addCriterion("Cdcollezione not between", value1, value2, "cdcollezione");
            return this;
        }

        public Criteria andCdarticoloIsNull() {
            addCriterion("Cdarticolo is null");
            return this;
        }

        public Criteria andCdarticoloIsNotNull() {
            addCriterion("Cdarticolo is not null");
            return this;
        }

        public Criteria andCdarticoloEqualTo(String value) {
            addCriterion("Cdarticolo =", value, "cdarticolo");
            return this;
        }

        public Criteria andCdarticoloNotEqualTo(String value) {
            addCriterion("Cdarticolo <>", value, "cdarticolo");
            return this;
        }

        public Criteria andCdarticoloGreaterThan(String value) {
            addCriterion("Cdarticolo >", value, "cdarticolo");
            return this;
        }

        public Criteria andCdarticoloGreaterThanOrEqualTo(String value) {
            addCriterion("Cdarticolo >=", value, "cdarticolo");
            return this;
        }

        public Criteria andCdarticoloLessThan(String value) {
            addCriterion("Cdarticolo <", value, "cdarticolo");
            return this;
        }

        public Criteria andCdarticoloLessThanOrEqualTo(String value) {
            addCriterion("Cdarticolo <=", value, "cdarticolo");
            return this;
        }

        public Criteria andCdarticoloLike(String value) {
            addCriterion("Cdarticolo like", value, "cdarticolo");
            return this;
        }

        public Criteria andCdarticoloNotLike(String value) {
            addCriterion("Cdarticolo not like", value, "cdarticolo");
            return this;
        }

        public Criteria andCdarticoloIn(List<String> values) {
            addCriterion("Cdarticolo in", values, "cdarticolo");
            return this;
        }

        public Criteria andCdarticoloNotIn(List<String> values) {
            addCriterion("Cdarticolo not in", values, "cdarticolo");
            return this;
        }

        public Criteria andCdarticoloBetween(String value1, String value2) {
            addCriterion("Cdarticolo between", value1, value2, "cdarticolo");
            return this;
        }

        public Criteria andCdarticoloNotBetween(String value1, String value2) {
            addCriterion("Cdarticolo not between", value1, value2, "cdarticolo");
            return this;
        }

        public Criteria andCdPadreIsNull() {
            addCriterion("CdPadre is null");
            return this;
        }

        public Criteria andCdPadreIsNotNull() {
            addCriterion("CdPadre is not null");
            return this;
        }

        public Criteria andCdPadreEqualTo(String value) {
            addCriterion("CdPadre =", value, "cdPadre");
            return this;
        }

        public Criteria andCdPadreNotEqualTo(String value) {
            addCriterion("CdPadre <>", value, "cdPadre");
            return this;
        }

        public Criteria andCdPadreGreaterThan(String value) {
            addCriterion("CdPadre >", value, "cdPadre");
            return this;
        }

        public Criteria andCdPadreGreaterThanOrEqualTo(String value) {
            addCriterion("CdPadre >=", value, "cdPadre");
            return this;
        }

        public Criteria andCdPadreLessThan(String value) {
            addCriterion("CdPadre <", value, "cdPadre");
            return this;
        }

        public Criteria andCdPadreLessThanOrEqualTo(String value) {
            addCriterion("CdPadre <=", value, "cdPadre");
            return this;
        }

        public Criteria andCdPadreLike(String value) {
            addCriterion("CdPadre like", value, "cdPadre");
            return this;
        }

        public Criteria andCdPadreNotLike(String value) {
            addCriterion("CdPadre not like", value, "cdPadre");
            return this;
        }

        public Criteria andCdPadreIn(List<String> values) {
            addCriterion("CdPadre in", values, "cdPadre");
            return this;
        }

        public Criteria andCdPadreNotIn(List<String> values) {
            addCriterion("CdPadre not in", values, "cdPadre");
            return this;
        }

        public Criteria andCdPadreBetween(String value1, String value2) {
            addCriterion("CdPadre between", value1, value2, "cdPadre");
            return this;
        }

        public Criteria andCdPadreNotBetween(String value1, String value2) {
            addCriterion("CdPadre not between", value1, value2, "cdPadre");
            return this;
        }
    }
}