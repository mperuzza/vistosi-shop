package com.ateikon.internet.eprogen.domain.pgmr;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Atk_msgcontExample {
    /**
     * This field was generated by Apache iBATIS Ibator.
     * This field corresponds to the database table pgmr.atk_msgcont
     *
     * @ibatorgenerated Mon May 25 15:41:28 CEST 2009
     */
    protected String orderByClause;

    /**
     * This field was generated by Apache iBATIS Ibator.
     * This field corresponds to the database table pgmr.atk_msgcont
     *
     * @ibatorgenerated Mon May 25 15:41:28 CEST 2009
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.atk_msgcont
     *
     * @ibatorgenerated Mon May 25 15:41:28 CEST 2009
     */
    public Atk_msgcontExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.atk_msgcont
     *
     * @ibatorgenerated Mon May 25 15:41:28 CEST 2009
     */
    protected Atk_msgcontExample(Atk_msgcontExample example) {
        this.orderByClause = example.orderByClause;
        this.oredCriteria = example.oredCriteria;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.atk_msgcont
     *
     * @ibatorgenerated Mon May 25 15:41:28 CEST 2009
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.atk_msgcont
     *
     * @ibatorgenerated Mon May 25 15:41:28 CEST 2009
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.atk_msgcont
     *
     * @ibatorgenerated Mon May 25 15:41:28 CEST 2009
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.atk_msgcont
     *
     * @ibatorgenerated Mon May 25 15:41:28 CEST 2009
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.atk_msgcont
     *
     * @ibatorgenerated Mon May 25 15:41:28 CEST 2009
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
     * This method corresponds to the database table pgmr.atk_msgcont
     *
     * @ibatorgenerated Mon May 25 15:41:28 CEST 2009
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.atk_msgcont
     *
     * @ibatorgenerated Mon May 25 15:41:28 CEST 2009
     */
    public void clear() {
        oredCriteria.clear();
    }

    /**
     * This class was generated by Apache iBATIS Ibator.
     * This class corresponds to the database table pgmr.atk_msgcont
     *
     * @ibatorgenerated Mon May 25 15:41:28 CEST 2009
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

        public Criteria andNrposiIsNull() {
            addCriterion("nrposi is null");
            return this;
        }

        public Criteria andNrposiIsNotNull() {
            addCriterion("nrposi is not null");
            return this;
        }

        public Criteria andNrposiEqualTo(Integer value) {
            addCriterion("nrposi =", value, "nrposi");
            return this;
        }

        public Criteria andNrposiNotEqualTo(Integer value) {
            addCriterion("nrposi <>", value, "nrposi");
            return this;
        }

        public Criteria andNrposiGreaterThan(Integer value) {
            addCriterion("nrposi >", value, "nrposi");
            return this;
        }

        public Criteria andNrposiGreaterThanOrEqualTo(Integer value) {
            addCriterion("nrposi >=", value, "nrposi");
            return this;
        }

        public Criteria andNrposiLessThan(Integer value) {
            addCriterion("nrposi <", value, "nrposi");
            return this;
        }

        public Criteria andNrposiLessThanOrEqualTo(Integer value) {
            addCriterion("nrposi <=", value, "nrposi");
            return this;
        }

        public Criteria andNrposiIn(List<Integer> values) {
            addCriterion("nrposi in", values, "nrposi");
            return this;
        }

        public Criteria andNrposiNotIn(List<Integer> values) {
            addCriterion("nrposi not in", values, "nrposi");
            return this;
        }

        public Criteria andNrposiBetween(Integer value1, Integer value2) {
            addCriterion("nrposi between", value1, value2, "nrposi");
            return this;
        }

        public Criteria andNrposiNotBetween(Integer value1, Integer value2) {
            addCriterion("nrposi not between", value1, value2, "nrposi");
            return this;
        }

        public Criteria andTkmsgIsNull() {
            addCriterion("tkmsg is null");
            return this;
        }

        public Criteria andTkmsgIsNotNull() {
            addCriterion("tkmsg is not null");
            return this;
        }

        public Criteria andTkmsgEqualTo(Long value) {
            addCriterion("tkmsg =", value, "tkmsg");
            return this;
        }

        public Criteria andTkmsgNotEqualTo(Long value) {
            addCriterion("tkmsg <>", value, "tkmsg");
            return this;
        }

        public Criteria andTkmsgGreaterThan(Long value) {
            addCriterion("tkmsg >", value, "tkmsg");
            return this;
        }

        public Criteria andTkmsgGreaterThanOrEqualTo(Long value) {
            addCriterion("tkmsg >=", value, "tkmsg");
            return this;
        }

        public Criteria andTkmsgLessThan(Long value) {
            addCriterion("tkmsg <", value, "tkmsg");
            return this;
        }

        public Criteria andTkmsgLessThanOrEqualTo(Long value) {
            addCriterion("tkmsg <=", value, "tkmsg");
            return this;
        }

        public Criteria andTkmsgIn(List<Long> values) {
            addCriterion("tkmsg in", values, "tkmsg");
            return this;
        }

        public Criteria andTkmsgNotIn(List<Long> values) {
            addCriterion("tkmsg not in", values, "tkmsg");
            return this;
        }

        public Criteria andTkmsgBetween(Long value1, Long value2) {
            addCriterion("tkmsg between", value1, value2, "tkmsg");
            return this;
        }

        public Criteria andTkmsgNotBetween(Long value1, Long value2) {
            addCriterion("tkmsg not between", value1, value2, "tkmsg");
            return this;
        }

        public Criteria andContenutoIsNull() {
            addCriterion("contenuto is null");
            return this;
        }

        public Criteria andContenutoIsNotNull() {
            addCriterion("contenuto is not null");
            return this;
        }

        public Criteria andContenutoEqualTo(String value) {
            addCriterion("contenuto =", value, "contenuto");
            return this;
        }

        public Criteria andContenutoNotEqualTo(String value) {
            addCriterion("contenuto <>", value, "contenuto");
            return this;
        }

        public Criteria andContenutoGreaterThan(String value) {
            addCriterion("contenuto >", value, "contenuto");
            return this;
        }

        public Criteria andContenutoGreaterThanOrEqualTo(String value) {
            addCriterion("contenuto >=", value, "contenuto");
            return this;
        }

        public Criteria andContenutoLessThan(String value) {
            addCriterion("contenuto <", value, "contenuto");
            return this;
        }

        public Criteria andContenutoLessThanOrEqualTo(String value) {
            addCriterion("contenuto <=", value, "contenuto");
            return this;
        }

        public Criteria andContenutoLike(String value) {
            addCriterion("contenuto like", value, "contenuto");
            return this;
        }

        public Criteria andContenutoNotLike(String value) {
            addCriterion("contenuto not like", value, "contenuto");
            return this;
        }

        public Criteria andContenutoIn(List<String> values) {
            addCriterion("contenuto in", values, "contenuto");
            return this;
        }

        public Criteria andContenutoNotIn(List<String> values) {
            addCriterion("contenuto not in", values, "contenuto");
            return this;
        }

        public Criteria andContenutoBetween(String value1, String value2) {
            addCriterion("contenuto between", value1, value2, "contenuto");
            return this;
        }

        public Criteria andContenutoNotBetween(String value1, String value2) {
            addCriterion("contenuto not between", value1, value2, "contenuto");
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

        public Criteria andCddipaIsNull() {
            addCriterion("cddipa is null");
            return this;
        }

        public Criteria andCddipaIsNotNull() {
            addCriterion("cddipa is not null");
            return this;
        }

        public Criteria andCddipaEqualTo(String value) {
            addCriterion("cddipa =", value, "cddipa");
            return this;
        }

        public Criteria andCddipaNotEqualTo(String value) {
            addCriterion("cddipa <>", value, "cddipa");
            return this;
        }

        public Criteria andCddipaGreaterThan(String value) {
            addCriterion("cddipa >", value, "cddipa");
            return this;
        }

        public Criteria andCddipaGreaterThanOrEqualTo(String value) {
            addCriterion("cddipa >=", value, "cddipa");
            return this;
        }

        public Criteria andCddipaLessThan(String value) {
            addCriterion("cddipa <", value, "cddipa");
            return this;
        }

        public Criteria andCddipaLessThanOrEqualTo(String value) {
            addCriterion("cddipa <=", value, "cddipa");
            return this;
        }

        public Criteria andCddipaLike(String value) {
            addCriterion("cddipa like", value, "cddipa");
            return this;
        }

        public Criteria andCddipaNotLike(String value) {
            addCriterion("cddipa not like", value, "cddipa");
            return this;
        }

        public Criteria andCddipaIn(List<String> values) {
            addCriterion("cddipa in", values, "cddipa");
            return this;
        }

        public Criteria andCddipaNotIn(List<String> values) {
            addCriterion("cddipa not in", values, "cddipa");
            return this;
        }

        public Criteria andCddipaBetween(String value1, String value2) {
            addCriterion("cddipa between", value1, value2, "cddipa");
            return this;
        }

        public Criteria andCddipaNotBetween(String value1, String value2) {
            addCriterion("cddipa not between", value1, value2, "cddipa");
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