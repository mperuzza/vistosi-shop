package com.ateikon.internet.eprogen.domain.pgmr;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cms_promozioni_clienteExample {
    /**
     * This field was generated by Apache iBATIS Ibator.
     * This field corresponds to the database table pgmr.cms_promozioni_cliente
     *
     * @ibatorgenerated Mon Jan 24 10:07:36 CET 2011
     */
    protected String orderByClause;

    /**
     * This field was generated by Apache iBATIS Ibator.
     * This field corresponds to the database table pgmr.cms_promozioni_cliente
     *
     * @ibatorgenerated Mon Jan 24 10:07:36 CET 2011
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.cms_promozioni_cliente
     *
     * @ibatorgenerated Mon Jan 24 10:07:36 CET 2011
     */
    public Cms_promozioni_clienteExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.cms_promozioni_cliente
     *
     * @ibatorgenerated Mon Jan 24 10:07:36 CET 2011
     */
    protected Cms_promozioni_clienteExample(Cms_promozioni_clienteExample example) {
        this.orderByClause = example.orderByClause;
        this.oredCriteria = example.oredCriteria;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.cms_promozioni_cliente
     *
     * @ibatorgenerated Mon Jan 24 10:07:36 CET 2011
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.cms_promozioni_cliente
     *
     * @ibatorgenerated Mon Jan 24 10:07:36 CET 2011
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.cms_promozioni_cliente
     *
     * @ibatorgenerated Mon Jan 24 10:07:36 CET 2011
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.cms_promozioni_cliente
     *
     * @ibatorgenerated Mon Jan 24 10:07:36 CET 2011
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.cms_promozioni_cliente
     *
     * @ibatorgenerated Mon Jan 24 10:07:36 CET 2011
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
     * This method corresponds to the database table pgmr.cms_promozioni_cliente
     *
     * @ibatorgenerated Mon Jan 24 10:07:36 CET 2011
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.cms_promozioni_cliente
     *
     * @ibatorgenerated Mon Jan 24 10:07:36 CET 2011
     */
    public void clear() {
        oredCriteria.clear();
    }

    /**
     * This class was generated by Apache iBATIS Ibator.
     * This class corresponds to the database table pgmr.cms_promozioni_cliente
     *
     * @ibatorgenerated Mon Jan 24 10:07:36 CET 2011
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

        public Criteria andTkproclieIsNull() {
            addCriterion("tkproclie is null");
            return this;
        }

        public Criteria andTkproclieIsNotNull() {
            addCriterion("tkproclie is not null");
            return this;
        }

        public Criteria andTkproclieEqualTo(Long value) {
            addCriterion("tkproclie =", value, "tkproclie");
            return this;
        }

        public Criteria andTkproclieNotEqualTo(Long value) {
            addCriterion("tkproclie <>", value, "tkproclie");
            return this;
        }

        public Criteria andTkproclieGreaterThan(Long value) {
            addCriterion("tkproclie >", value, "tkproclie");
            return this;
        }

        public Criteria andTkproclieGreaterThanOrEqualTo(Long value) {
            addCriterion("tkproclie >=", value, "tkproclie");
            return this;
        }

        public Criteria andTkproclieLessThan(Long value) {
            addCriterion("tkproclie <", value, "tkproclie");
            return this;
        }

        public Criteria andTkproclieLessThanOrEqualTo(Long value) {
            addCriterion("tkproclie <=", value, "tkproclie");
            return this;
        }

        public Criteria andTkproclieIn(List<Long> values) {
            addCriterion("tkproclie in", values, "tkproclie");
            return this;
        }

        public Criteria andTkproclieNotIn(List<Long> values) {
            addCriterion("tkproclie not in", values, "tkproclie");
            return this;
        }

        public Criteria andTkproclieBetween(Long value1, Long value2) {
            addCriterion("tkproclie between", value1, value2, "tkproclie");
            return this;
        }

        public Criteria andTkproclieNotBetween(Long value1, Long value2) {
            addCriterion("tkproclie not between", value1, value2, "tkproclie");
            return this;
        }

        public Criteria andTkpromoIsNull() {
            addCriterion("tkpromo is null");
            return this;
        }

        public Criteria andTkpromoIsNotNull() {
            addCriterion("tkpromo is not null");
            return this;
        }

        public Criteria andTkpromoEqualTo(Long value) {
            addCriterion("tkpromo =", value, "tkpromo");
            return this;
        }

        public Criteria andTkpromoNotEqualTo(Long value) {
            addCriterion("tkpromo <>", value, "tkpromo");
            return this;
        }

        public Criteria andTkpromoGreaterThan(Long value) {
            addCriterion("tkpromo >", value, "tkpromo");
            return this;
        }

        public Criteria andTkpromoGreaterThanOrEqualTo(Long value) {
            addCriterion("tkpromo >=", value, "tkpromo");
            return this;
        }

        public Criteria andTkpromoLessThan(Long value) {
            addCriterion("tkpromo <", value, "tkpromo");
            return this;
        }

        public Criteria andTkpromoLessThanOrEqualTo(Long value) {
            addCriterion("tkpromo <=", value, "tkpromo");
            return this;
        }

        public Criteria andTkpromoIn(List<Long> values) {
            addCriterion("tkpromo in", values, "tkpromo");
            return this;
        }

        public Criteria andTkpromoNotIn(List<Long> values) {
            addCriterion("tkpromo not in", values, "tkpromo");
            return this;
        }

        public Criteria andTkpromoBetween(Long value1, Long value2) {
            addCriterion("tkpromo between", value1, value2, "tkpromo");
            return this;
        }

        public Criteria andTkpromoNotBetween(Long value1, Long value2) {
            addCriterion("tkpromo not between", value1, value2, "tkpromo");
            return this;
        }

        public Criteria andTkclieIsNull() {
            addCriterion("tkclie is null");
            return this;
        }

        public Criteria andTkclieIsNotNull() {
            addCriterion("tkclie is not null");
            return this;
        }

        public Criteria andTkclieEqualTo(String value) {
            addCriterion("tkclie =", value, "tkclie");
            return this;
        }

        public Criteria andTkclieNotEqualTo(String value) {
            addCriterion("tkclie <>", value, "tkclie");
            return this;
        }

        public Criteria andTkclieGreaterThan(String value) {
            addCriterion("tkclie >", value, "tkclie");
            return this;
        }

        public Criteria andTkclieGreaterThanOrEqualTo(String value) {
            addCriterion("tkclie >=", value, "tkclie");
            return this;
        }

        public Criteria andTkclieLessThan(String value) {
            addCriterion("tkclie <", value, "tkclie");
            return this;
        }

        public Criteria andTkclieLessThanOrEqualTo(String value) {
            addCriterion("tkclie <=", value, "tkclie");
            return this;
        }

        public Criteria andTkclieLike(String value) {
            addCriterion("tkclie like", value, "tkclie");
            return this;
        }

        public Criteria andTkclieNotLike(String value) {
            addCriterion("tkclie not like", value, "tkclie");
            return this;
        }

        public Criteria andTkclieIn(List<String> values) {
            addCriterion("tkclie in", values, "tkclie");
            return this;
        }

        public Criteria andTkclieNotIn(List<String> values) {
            addCriterion("tkclie not in", values, "tkclie");
            return this;
        }

        public Criteria andTkclieBetween(String value1, String value2) {
            addCriterion("tkclie between", value1, value2, "tkclie");
            return this;
        }

        public Criteria andTkclieNotBetween(String value1, String value2) {
            addCriterion("tkclie not between", value1, value2, "tkclie");
            return this;
        }

        public Criteria andCdagenIsNull() {
            addCriterion("cdagen is null");
            return this;
        }

        public Criteria andCdagenIsNotNull() {
            addCriterion("cdagen is not null");
            return this;
        }

        public Criteria andCdagenEqualTo(String value) {
            addCriterion("cdagen =", value, "cdagen");
            return this;
        }

        public Criteria andCdagenNotEqualTo(String value) {
            addCriterion("cdagen <>", value, "cdagen");
            return this;
        }

        public Criteria andCdagenGreaterThan(String value) {
            addCriterion("cdagen >", value, "cdagen");
            return this;
        }

        public Criteria andCdagenGreaterThanOrEqualTo(String value) {
            addCriterion("cdagen >=", value, "cdagen");
            return this;
        }

        public Criteria andCdagenLessThan(String value) {
            addCriterion("cdagen <", value, "cdagen");
            return this;
        }

        public Criteria andCdagenLessThanOrEqualTo(String value) {
            addCriterion("cdagen <=", value, "cdagen");
            return this;
        }

        public Criteria andCdagenLike(String value) {
            addCriterion("cdagen like", value, "cdagen");
            return this;
        }

        public Criteria andCdagenNotLike(String value) {
            addCriterion("cdagen not like", value, "cdagen");
            return this;
        }

        public Criteria andCdagenIn(List<String> values) {
            addCriterion("cdagen in", values, "cdagen");
            return this;
        }

        public Criteria andCdagenNotIn(List<String> values) {
            addCriterion("cdagen not in", values, "cdagen");
            return this;
        }

        public Criteria andCdagenBetween(String value1, String value2) {
            addCriterion("cdagen between", value1, value2, "cdagen");
            return this;
        }

        public Criteria andCdagenNotBetween(String value1, String value2) {
            addCriterion("cdagen not between", value1, value2, "cdagen");
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