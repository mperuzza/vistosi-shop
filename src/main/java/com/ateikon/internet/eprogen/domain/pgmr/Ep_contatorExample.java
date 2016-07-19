package com.ateikon.internet.eprogen.domain.pgmr;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Ep_contatorExample {
    /**
     * This field was generated by Apache iBATIS Ibator.
     * This field corresponds to the database table pgmr.ep_contator
     *
     * @ibatorgenerated Mon Dec 13 18:01:25 CET 2010
     */
    protected String orderByClause;

    /**
     * This field was generated by Apache iBATIS Ibator.
     * This field corresponds to the database table pgmr.ep_contator
     *
     * @ibatorgenerated Mon Dec 13 18:01:25 CET 2010
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.ep_contator
     *
     * @ibatorgenerated Mon Dec 13 18:01:25 CET 2010
     */
    public Ep_contatorExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.ep_contator
     *
     * @ibatorgenerated Mon Dec 13 18:01:25 CET 2010
     */
    protected Ep_contatorExample(Ep_contatorExample example) {
        this.orderByClause = example.orderByClause;
        this.oredCriteria = example.oredCriteria;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.ep_contator
     *
     * @ibatorgenerated Mon Dec 13 18:01:25 CET 2010
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.ep_contator
     *
     * @ibatorgenerated Mon Dec 13 18:01:25 CET 2010
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.ep_contator
     *
     * @ibatorgenerated Mon Dec 13 18:01:25 CET 2010
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.ep_contator
     *
     * @ibatorgenerated Mon Dec 13 18:01:25 CET 2010
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.ep_contator
     *
     * @ibatorgenerated Mon Dec 13 18:01:25 CET 2010
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
     * This method corresponds to the database table pgmr.ep_contator
     *
     * @ibatorgenerated Mon Dec 13 18:01:25 CET 2010
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.ep_contator
     *
     * @ibatorgenerated Mon Dec 13 18:01:25 CET 2010
     */
    public void clear() {
        oredCriteria.clear();
    }

    /**
     * This class was generated by Apache iBATIS Ibator.
     * This class corresponds to the database table pgmr.ep_contator
     *
     * @ibatorgenerated Mon Dec 13 18:01:25 CET 2010
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

        public Criteria andAnnocoIsNull() {
            addCriterion("annoco is null");
            return this;
        }

        public Criteria andAnnocoIsNotNull() {
            addCriterion("annoco is not null");
            return this;
        }

        public Criteria andAnnocoEqualTo(String value) {
            addCriterion("annoco =", value, "annoco");
            return this;
        }

        public Criteria andAnnocoNotEqualTo(String value) {
            addCriterion("annoco <>", value, "annoco");
            return this;
        }

        public Criteria andAnnocoGreaterThan(String value) {
            addCriterion("annoco >", value, "annoco");
            return this;
        }

        public Criteria andAnnocoGreaterThanOrEqualTo(String value) {
            addCriterion("annoco >=", value, "annoco");
            return this;
        }

        public Criteria andAnnocoLessThan(String value) {
            addCriterion("annoco <", value, "annoco");
            return this;
        }

        public Criteria andAnnocoLessThanOrEqualTo(String value) {
            addCriterion("annoco <=", value, "annoco");
            return this;
        }

        public Criteria andAnnocoLike(String value) {
            addCriterion("annoco like", value, "annoco");
            return this;
        }

        public Criteria andAnnocoNotLike(String value) {
            addCriterion("annoco not like", value, "annoco");
            return this;
        }

        public Criteria andAnnocoIn(List<String> values) {
            addCriterion("annoco in", values, "annoco");
            return this;
        }

        public Criteria andAnnocoNotIn(List<String> values) {
            addCriterion("annoco not in", values, "annoco");
            return this;
        }

        public Criteria andAnnocoBetween(String value1, String value2) {
            addCriterion("annoco between", value1, value2, "annoco");
            return this;
        }

        public Criteria andAnnocoNotBetween(String value1, String value2) {
            addCriterion("annoco not between", value1, value2, "annoco");
            return this;
        }

        public Criteria andCdazieIsNull() {
            addCriterion("cdazie is null");
            return this;
        }

        public Criteria andCdazieIsNotNull() {
            addCriterion("cdazie is not null");
            return this;
        }

        public Criteria andCdazieEqualTo(String value) {
            addCriterion("cdazie =", value, "cdazie");
            return this;
        }

        public Criteria andCdazieNotEqualTo(String value) {
            addCriterion("cdazie <>", value, "cdazie");
            return this;
        }

        public Criteria andCdazieGreaterThan(String value) {
            addCriterion("cdazie >", value, "cdazie");
            return this;
        }

        public Criteria andCdazieGreaterThanOrEqualTo(String value) {
            addCriterion("cdazie >=", value, "cdazie");
            return this;
        }

        public Criteria andCdazieLessThan(String value) {
            addCriterion("cdazie <", value, "cdazie");
            return this;
        }

        public Criteria andCdazieLessThanOrEqualTo(String value) {
            addCriterion("cdazie <=", value, "cdazie");
            return this;
        }

        public Criteria andCdazieLike(String value) {
            addCriterion("cdazie like", value, "cdazie");
            return this;
        }

        public Criteria andCdazieNotLike(String value) {
            addCriterion("cdazie not like", value, "cdazie");
            return this;
        }

        public Criteria andCdazieIn(List<String> values) {
            addCriterion("cdazie in", values, "cdazie");
            return this;
        }

        public Criteria andCdazieNotIn(List<String> values) {
            addCriterion("cdazie not in", values, "cdazie");
            return this;
        }

        public Criteria andCdazieBetween(String value1, String value2) {
            addCriterion("cdazie between", value1, value2, "cdazie");
            return this;
        }

        public Criteria andCdazieNotBetween(String value1, String value2) {
            addCriterion("cdazie not between", value1, value2, "cdazie");
            return this;
        }

        public Criteria andPrnameIsNull() {
            addCriterion("prname is null");
            return this;
        }

        public Criteria andPrnameIsNotNull() {
            addCriterion("prname is not null");
            return this;
        }

        public Criteria andPrnameEqualTo(String value) {
            addCriterion("prname =", value, "prname");
            return this;
        }

        public Criteria andPrnameNotEqualTo(String value) {
            addCriterion("prname <>", value, "prname");
            return this;
        }

        public Criteria andPrnameGreaterThan(String value) {
            addCriterion("prname >", value, "prname");
            return this;
        }

        public Criteria andPrnameGreaterThanOrEqualTo(String value) {
            addCriterion("prname >=", value, "prname");
            return this;
        }

        public Criteria andPrnameLessThan(String value) {
            addCriterion("prname <", value, "prname");
            return this;
        }

        public Criteria andPrnameLessThanOrEqualTo(String value) {
            addCriterion("prname <=", value, "prname");
            return this;
        }

        public Criteria andPrnameLike(String value) {
            addCriterion("prname like", value, "prname");
            return this;
        }

        public Criteria andPrnameNotLike(String value) {
            addCriterion("prname not like", value, "prname");
            return this;
        }

        public Criteria andPrnameIn(List<String> values) {
            addCriterion("prname in", values, "prname");
            return this;
        }

        public Criteria andPrnameNotIn(List<String> values) {
            addCriterion("prname not in", values, "prname");
            return this;
        }

        public Criteria andPrnameBetween(String value1, String value2) {
            addCriterion("prname between", value1, value2, "prname");
            return this;
        }

        public Criteria andPrnameNotBetween(String value1, String value2) {
            addCriterion("prname not between", value1, value2, "prname");
            return this;
        }

        public Criteria andNprogrIsNull() {
            addCriterion("nprogr is null");
            return this;
        }

        public Criteria andNprogrIsNotNull() {
            addCriterion("nprogr is not null");
            return this;
        }

        public Criteria andNprogrEqualTo(Long value) {
            addCriterion("nprogr =", value, "nprogr");
            return this;
        }

        public Criteria andNprogrNotEqualTo(Long value) {
            addCriterion("nprogr <>", value, "nprogr");
            return this;
        }

        public Criteria andNprogrGreaterThan(Long value) {
            addCriterion("nprogr >", value, "nprogr");
            return this;
        }

        public Criteria andNprogrGreaterThanOrEqualTo(Long value) {
            addCriterion("nprogr >=", value, "nprogr");
            return this;
        }

        public Criteria andNprogrLessThan(Long value) {
            addCriterion("nprogr <", value, "nprogr");
            return this;
        }

        public Criteria andNprogrLessThanOrEqualTo(Long value) {
            addCriterion("nprogr <=", value, "nprogr");
            return this;
        }

        public Criteria andNprogrIn(List<Long> values) {
            addCriterion("nprogr in", values, "nprogr");
            return this;
        }

        public Criteria andNprogrNotIn(List<Long> values) {
            addCriterion("nprogr not in", values, "nprogr");
            return this;
        }

        public Criteria andNprogrBetween(Long value1, Long value2) {
            addCriterion("nprogr between", value1, value2, "nprogr");
            return this;
        }

        public Criteria andNprogrNotBetween(Long value1, Long value2) {
            addCriterion("nprogr not between", value1, value2, "nprogr");
            return this;
        }

        public Criteria andProfilIsNull() {
            addCriterion("profil is null");
            return this;
        }

        public Criteria andProfilIsNotNull() {
            addCriterion("profil is not null");
            return this;
        }

        public Criteria andProfilEqualTo(String value) {
            addCriterion("profil =", value, "profil");
            return this;
        }

        public Criteria andProfilNotEqualTo(String value) {
            addCriterion("profil <>", value, "profil");
            return this;
        }

        public Criteria andProfilGreaterThan(String value) {
            addCriterion("profil >", value, "profil");
            return this;
        }

        public Criteria andProfilGreaterThanOrEqualTo(String value) {
            addCriterion("profil >=", value, "profil");
            return this;
        }

        public Criteria andProfilLessThan(String value) {
            addCriterion("profil <", value, "profil");
            return this;
        }

        public Criteria andProfilLessThanOrEqualTo(String value) {
            addCriterion("profil <=", value, "profil");
            return this;
        }

        public Criteria andProfilLike(String value) {
            addCriterion("profil like", value, "profil");
            return this;
        }

        public Criteria andProfilNotLike(String value) {
            addCriterion("profil not like", value, "profil");
            return this;
        }

        public Criteria andProfilIn(List<String> values) {
            addCriterion("profil in", values, "profil");
            return this;
        }

        public Criteria andProfilNotIn(List<String> values) {
            addCriterion("profil not in", values, "profil");
            return this;
        }

        public Criteria andProfilBetween(String value1, String value2) {
            addCriterion("profil between", value1, value2, "profil");
            return this;
        }

        public Criteria andProfilNotBetween(String value1, String value2) {
            addCriterion("profil not between", value1, value2, "profil");
            return this;
        }

        public Criteria andDtinseIsNull() {
            addCriterion("dtinse is null");
            return this;
        }

        public Criteria andDtinseIsNotNull() {
            addCriterion("dtinse is not null");
            return this;
        }

        public Criteria andDtinseEqualTo(Date value) {
            addCriterion("dtinse =", value, "dtinse");
            return this;
        }

        public Criteria andDtinseNotEqualTo(Date value) {
            addCriterion("dtinse <>", value, "dtinse");
            return this;
        }

        public Criteria andDtinseGreaterThan(Date value) {
            addCriterion("dtinse >", value, "dtinse");
            return this;
        }

        public Criteria andDtinseGreaterThanOrEqualTo(Date value) {
            addCriterion("dtinse >=", value, "dtinse");
            return this;
        }

        public Criteria andDtinseLessThan(Date value) {
            addCriterion("dtinse <", value, "dtinse");
            return this;
        }

        public Criteria andDtinseLessThanOrEqualTo(Date value) {
            addCriterion("dtinse <=", value, "dtinse");
            return this;
        }

        public Criteria andDtinseIn(List<Date> values) {
            addCriterion("dtinse in", values, "dtinse");
            return this;
        }

        public Criteria andDtinseNotIn(List<Date> values) {
            addCriterion("dtinse not in", values, "dtinse");
            return this;
        }

        public Criteria andDtinseBetween(Date value1, Date value2) {
            addCriterion("dtinse between", value1, value2, "dtinse");
            return this;
        }

        public Criteria andDtinseNotBetween(Date value1, Date value2) {
            addCriterion("dtinse not between", value1, value2, "dtinse");
            return this;
        }

        public Criteria andDtulagIsNull() {
            addCriterion("dtulag is null");
            return this;
        }

        public Criteria andDtulagIsNotNull() {
            addCriterion("dtulag is not null");
            return this;
        }

        public Criteria andDtulagEqualTo(Date value) {
            addCriterion("dtulag =", value, "dtulag");
            return this;
        }

        public Criteria andDtulagNotEqualTo(Date value) {
            addCriterion("dtulag <>", value, "dtulag");
            return this;
        }

        public Criteria andDtulagGreaterThan(Date value) {
            addCriterion("dtulag >", value, "dtulag");
            return this;
        }

        public Criteria andDtulagGreaterThanOrEqualTo(Date value) {
            addCriterion("dtulag >=", value, "dtulag");
            return this;
        }

        public Criteria andDtulagLessThan(Date value) {
            addCriterion("dtulag <", value, "dtulag");
            return this;
        }

        public Criteria andDtulagLessThanOrEqualTo(Date value) {
            addCriterion("dtulag <=", value, "dtulag");
            return this;
        }

        public Criteria andDtulagIn(List<Date> values) {
            addCriterion("dtulag in", values, "dtulag");
            return this;
        }

        public Criteria andDtulagNotIn(List<Date> values) {
            addCriterion("dtulag not in", values, "dtulag");
            return this;
        }

        public Criteria andDtulagBetween(Date value1, Date value2) {
            addCriterion("dtulag between", value1, value2, "dtulag");
            return this;
        }

        public Criteria andDtulagNotBetween(Date value1, Date value2) {
            addCriterion("dtulag not between", value1, value2, "dtulag");
            return this;
        }
    }
}