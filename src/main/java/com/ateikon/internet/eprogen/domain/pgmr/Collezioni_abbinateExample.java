package com.ateikon.internet.eprogen.domain.pgmr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Collezioni_abbinateExample {
    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database table pgmr.collezioni_abbinate
     *
     * @ibatorgenerated Mon Feb 23 19:20:39 CET 2009
     */
    protected String orderByClause;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database table pgmr.collezioni_abbinate
     *
     * @ibatorgenerated Mon Feb 23 19:20:39 CET 2009
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table pgmr.collezioni_abbinate
     *
     * @ibatorgenerated Mon Feb 23 19:20:39 CET 2009
     */
    public Collezioni_abbinateExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table pgmr.collezioni_abbinate
     *
     * @ibatorgenerated Mon Feb 23 19:20:39 CET 2009
     */
    protected Collezioni_abbinateExample(Collezioni_abbinateExample example) {
        this.orderByClause = example.orderByClause;
        this.oredCriteria = example.oredCriteria;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table pgmr.collezioni_abbinate
     *
     * @ibatorgenerated Mon Feb 23 19:20:39 CET 2009
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table pgmr.collezioni_abbinate
     *
     * @ibatorgenerated Mon Feb 23 19:20:39 CET 2009
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table pgmr.collezioni_abbinate
     *
     * @ibatorgenerated Mon Feb 23 19:20:39 CET 2009
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table pgmr.collezioni_abbinate
     *
     * @ibatorgenerated Mon Feb 23 19:20:39 CET 2009
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table pgmr.collezioni_abbinate
     *
     * @ibatorgenerated Mon Feb 23 19:20:39 CET 2009
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table pgmr.collezioni_abbinate
     *
     * @ibatorgenerated Mon Feb 23 19:20:39 CET 2009
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table pgmr.collezioni_abbinate
     *
     * @ibatorgenerated Mon Feb 23 19:20:39 CET 2009
     */
    public void clear() {
        oredCriteria.clear();
    }

    /**
     * This class was generated by Apache iBATIS ibator.
     * This class corresponds to the database table pgmr.collezioni_abbinate
     *
     * @ibatorgenerated Mon Feb 23 19:20:39 CET 2009
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

        public Criteria andCdcollezioneIsNull() {
            addCriterion("cdcollezione is null");
            return this;
        }

        public Criteria andCdcollezioneIsNotNull() {
            addCriterion("cdcollezione is not null");
            return this;
        }

        public Criteria andCdcollezioneEqualTo(Integer value) {
            addCriterion("cdcollezione =", value, "cdcollezione");
            return this;
        }

        public Criteria andCdcollezioneNotEqualTo(Integer value) {
            addCriterion("cdcollezione <>", value, "cdcollezione");
            return this;
        }

        public Criteria andCdcollezioneGreaterThan(Integer value) {
            addCriterion("cdcollezione >", value, "cdcollezione");
            return this;
        }

        public Criteria andCdcollezioneGreaterThanOrEqualTo(Integer value) {
            addCriterion("cdcollezione >=", value, "cdcollezione");
            return this;
        }

        public Criteria andCdcollezioneLessThan(Integer value) {
            addCriterion("cdcollezione <", value, "cdcollezione");
            return this;
        }

        public Criteria andCdcollezioneLessThanOrEqualTo(Integer value) {
            addCriterion("cdcollezione <=", value, "cdcollezione");
            return this;
        }

        public Criteria andCdcollezioneIn(List<Integer> values) {
            addCriterion("cdcollezione in", values, "cdcollezione");
            return this;
        }

        public Criteria andCdcollezioneNotIn(List<Integer> values) {
            addCriterion("cdcollezione not in", values, "cdcollezione");
            return this;
        }

        public Criteria andCdcollezioneBetween(Integer value1, Integer value2) {
            addCriterion("cdcollezione between", value1, value2, "cdcollezione");
            return this;
        }

        public Criteria andCdcollezioneNotBetween(Integer value1, Integer value2) {
            addCriterion("cdcollezione not between", value1, value2, "cdcollezione");
            return this;
        }

        public Criteria andCdcollezioneabbIsNull() {
            addCriterion("cdcollezioneabb is null");
            return this;
        }

        public Criteria andCdcollezioneabbIsNotNull() {
            addCriterion("cdcollezioneabb is not null");
            return this;
        }

        public Criteria andCdcollezioneabbEqualTo(Integer value) {
            addCriterion("cdcollezioneabb =", value, "cdcollezioneabb");
            return this;
        }

        public Criteria andCdcollezioneabbNotEqualTo(Integer value) {
            addCriterion("cdcollezioneabb <>", value, "cdcollezioneabb");
            return this;
        }

        public Criteria andCdcollezioneabbGreaterThan(Integer value) {
            addCriterion("cdcollezioneabb >", value, "cdcollezioneabb");
            return this;
        }

        public Criteria andCdcollezioneabbGreaterThanOrEqualTo(Integer value) {
            addCriterion("cdcollezioneabb >=", value, "cdcollezioneabb");
            return this;
        }

        public Criteria andCdcollezioneabbLessThan(Integer value) {
            addCriterion("cdcollezioneabb <", value, "cdcollezioneabb");
            return this;
        }

        public Criteria andCdcollezioneabbLessThanOrEqualTo(Integer value) {
            addCriterion("cdcollezioneabb <=", value, "cdcollezioneabb");
            return this;
        }

        public Criteria andCdcollezioneabbIn(List<Integer> values) {
            addCriterion("cdcollezioneabb in", values, "cdcollezioneabb");
            return this;
        }

        public Criteria andCdcollezioneabbNotIn(List<Integer> values) {
            addCriterion("cdcollezioneabb not in", values, "cdcollezioneabb");
            return this;
        }

        public Criteria andCdcollezioneabbBetween(Integer value1, Integer value2) {
            addCriterion("cdcollezioneabb between", value1, value2, "cdcollezioneabb");
            return this;
        }

        public Criteria andCdcollezioneabbNotBetween(Integer value1, Integer value2) {
            addCriterion("cdcollezioneabb not between", value1, value2, "cdcollezioneabb");
            return this;
        }
    }
}