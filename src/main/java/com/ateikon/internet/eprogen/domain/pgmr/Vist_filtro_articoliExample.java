package com.ateikon.internet.eprogen.domain.pgmr;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Vist_filtro_articoliExample {
    /**
     * This field was generated by Apache iBATIS Ibator.
     * This field corresponds to the database table pgmr.vist_filtro_articoli
     *
     * @ibatorgenerated Thu Oct 01 17:29:12 CEST 2009
     */
    protected String orderByClause;

    /**
     * This field was generated by Apache iBATIS Ibator.
     * This field corresponds to the database table pgmr.vist_filtro_articoli
     *
     * @ibatorgenerated Thu Oct 01 17:29:12 CEST 2009
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.vist_filtro_articoli
     *
     * @ibatorgenerated Thu Oct 01 17:29:12 CEST 2009
     */
    public Vist_filtro_articoliExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.vist_filtro_articoli
     *
     * @ibatorgenerated Thu Oct 01 17:29:12 CEST 2009
     */
    protected Vist_filtro_articoliExample(Vist_filtro_articoliExample example) {
        this.orderByClause = example.orderByClause;
        this.oredCriteria = example.oredCriteria;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.vist_filtro_articoli
     *
     * @ibatorgenerated Thu Oct 01 17:29:12 CEST 2009
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.vist_filtro_articoli
     *
     * @ibatorgenerated Thu Oct 01 17:29:12 CEST 2009
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.vist_filtro_articoli
     *
     * @ibatorgenerated Thu Oct 01 17:29:12 CEST 2009
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.vist_filtro_articoli
     *
     * @ibatorgenerated Thu Oct 01 17:29:12 CEST 2009
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.vist_filtro_articoli
     *
     * @ibatorgenerated Thu Oct 01 17:29:12 CEST 2009
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
     * This method corresponds to the database table pgmr.vist_filtro_articoli
     *
     * @ibatorgenerated Thu Oct 01 17:29:12 CEST 2009
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.vist_filtro_articoli
     *
     * @ibatorgenerated Thu Oct 01 17:29:12 CEST 2009
     */
    public void clear() {
        oredCriteria.clear();
    }

    /**
     * This class was generated by Apache iBATIS Ibator.
     * This class corresponds to the database table pgmr.vist_filtro_articoli
     *
     * @ibatorgenerated Thu Oct 01 17:29:12 CEST 2009
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

        public Criteria andTkartfilIsNull() {
            addCriterion("tkartfil is null");
            return this;
        }

        public Criteria andTkartfilIsNotNull() {
            addCriterion("tkartfil is not null");
            return this;
        }

        public Criteria andTkartfilEqualTo(Long value) {
            addCriterion("tkartfil =", value, "tkartfil");
            return this;
        }

        public Criteria andTkartfilNotEqualTo(Long value) {
            addCriterion("tkartfil <>", value, "tkartfil");
            return this;
        }

        public Criteria andTkartfilGreaterThan(Long value) {
            addCriterion("tkartfil >", value, "tkartfil");
            return this;
        }

        public Criteria andTkartfilGreaterThanOrEqualTo(Long value) {
            addCriterion("tkartfil >=", value, "tkartfil");
            return this;
        }

        public Criteria andTkartfilLessThan(Long value) {
            addCriterion("tkartfil <", value, "tkartfil");
            return this;
        }

        public Criteria andTkartfilLessThanOrEqualTo(Long value) {
            addCriterion("tkartfil <=", value, "tkartfil");
            return this;
        }

        public Criteria andTkartfilIn(List<Long> values) {
            addCriterion("tkartfil in", values, "tkartfil");
            return this;
        }

        public Criteria andTkartfilNotIn(List<Long> values) {
            addCriterion("tkartfil not in", values, "tkartfil");
            return this;
        }

        public Criteria andTkartfilBetween(Long value1, Long value2) {
            addCriterion("tkartfil between", value1, value2, "tkartfil");
            return this;
        }

        public Criteria andTkartfilNotBetween(Long value1, Long value2) {
            addCriterion("tkartfil not between", value1, value2, "tkartfil");
            return this;
        }

        public Criteria andCdclas_aIsNull() {
            addCriterion("cdclas_a is null");
            return this;
        }

        public Criteria andCdclas_aIsNotNull() {
            addCriterion("cdclas_a is not null");
            return this;
        }

        public Criteria andCdclas_aEqualTo(String value) {
            addCriterion("cdclas_a =", value, "cdclas_a");
            return this;
        }

        public Criteria andCdclas_aNotEqualTo(String value) {
            addCriterion("cdclas_a <>", value, "cdclas_a");
            return this;
        }

        public Criteria andCdclas_aGreaterThan(String value) {
            addCriterion("cdclas_a >", value, "cdclas_a");
            return this;
        }

        public Criteria andCdclas_aGreaterThanOrEqualTo(String value) {
            addCriterion("cdclas_a >=", value, "cdclas_a");
            return this;
        }

        public Criteria andCdclas_aLessThan(String value) {
            addCriterion("cdclas_a <", value, "cdclas_a");
            return this;
        }

        public Criteria andCdclas_aLessThanOrEqualTo(String value) {
            addCriterion("cdclas_a <=", value, "cdclas_a");
            return this;
        }

        public Criteria andCdclas_aLike(String value) {
            addCriterion("cdclas_a like", value, "cdclas_a");
            return this;
        }

        public Criteria andCdclas_aNotLike(String value) {
            addCriterion("cdclas_a not like", value, "cdclas_a");
            return this;
        }

        public Criteria andCdclas_aIn(List<String> values) {
            addCriterion("cdclas_a in", values, "cdclas_a");
            return this;
        }

        public Criteria andCdclas_aNotIn(List<String> values) {
            addCriterion("cdclas_a not in", values, "cdclas_a");
            return this;
        }

        public Criteria andCdclas_aBetween(String value1, String value2) {
            addCriterion("cdclas_a between", value1, value2, "cdclas_a");
            return this;
        }

        public Criteria andCdclas_aNotBetween(String value1, String value2) {
            addCriterion("cdclas_a not between", value1, value2, "cdclas_a");
            return this;
        }

        public Criteria andCdlistIsNull() {
            addCriterion("cdlist is null");
            return this;
        }

        public Criteria andCdlistIsNotNull() {
            addCriterion("cdlist is not null");
            return this;
        }

        public Criteria andCdlistEqualTo(String value) {
            addCriterion("cdlist =", value, "cdlist");
            return this;
        }

        public Criteria andCdlistNotEqualTo(String value) {
            addCriterion("cdlist <>", value, "cdlist");
            return this;
        }

        public Criteria andCdlistGreaterThan(String value) {
            addCriterion("cdlist >", value, "cdlist");
            return this;
        }

        public Criteria andCdlistGreaterThanOrEqualTo(String value) {
            addCriterion("cdlist >=", value, "cdlist");
            return this;
        }

        public Criteria andCdlistLessThan(String value) {
            addCriterion("cdlist <", value, "cdlist");
            return this;
        }

        public Criteria andCdlistLessThanOrEqualTo(String value) {
            addCriterion("cdlist <=", value, "cdlist");
            return this;
        }

        public Criteria andCdlistLike(String value) {
            addCriterion("cdlist like", value, "cdlist");
            return this;
        }

        public Criteria andCdlistNotLike(String value) {
            addCriterion("cdlist not like", value, "cdlist");
            return this;
        }

        public Criteria andCdlistIn(List<String> values) {
            addCriterion("cdlist in", values, "cdlist");
            return this;
        }

        public Criteria andCdlistNotIn(List<String> values) {
            addCriterion("cdlist not in", values, "cdlist");
            return this;
        }

        public Criteria andCdlistBetween(String value1, String value2) {
            addCriterion("cdlist between", value1, value2, "cdlist");
            return this;
        }

        public Criteria andCdlistNotBetween(String value1, String value2) {
            addCriterion("cdlist not between", value1, value2, "cdlist");
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

        public Criteria andDtivalIsNull() {
            addCriterion("dtival is null");
            return this;
        }

        public Criteria andDtivalIsNotNull() {
            addCriterion("dtival is not null");
            return this;
        }

        public Criteria andDtivalEqualTo(Date value) {
            addCriterion("dtival =", value, "dtival");
            return this;
        }

        public Criteria andDtivalNotEqualTo(Date value) {
            addCriterion("dtival <>", value, "dtival");
            return this;
        }

        public Criteria andDtivalGreaterThan(Date value) {
            addCriterion("dtival >", value, "dtival");
            return this;
        }

        public Criteria andDtivalGreaterThanOrEqualTo(Date value) {
            addCriterion("dtival >=", value, "dtival");
            return this;
        }

        public Criteria andDtivalLessThan(Date value) {
            addCriterion("dtival <", value, "dtival");
            return this;
        }

        public Criteria andDtivalLessThanOrEqualTo(Date value) {
            addCriterion("dtival <=", value, "dtival");
            return this;
        }

        public Criteria andDtivalIn(List<Date> values) {
            addCriterion("dtival in", values, "dtival");
            return this;
        }

        public Criteria andDtivalNotIn(List<Date> values) {
            addCriterion("dtival not in", values, "dtival");
            return this;
        }

        public Criteria andDtivalBetween(Date value1, Date value2) {
            addCriterion("dtival between", value1, value2, "dtival");
            return this;
        }

        public Criteria andDtivalNotBetween(Date value1, Date value2) {
            addCriterion("dtival not between", value1, value2, "dtival");
            return this;
        }

        public Criteria andDtfvalIsNull() {
            addCriterion("dtfval is null");
            return this;
        }

        public Criteria andDtfvalIsNotNull() {
            addCriterion("dtfval is not null");
            return this;
        }

        public Criteria andDtfvalEqualTo(Date value) {
            addCriterion("dtfval =", value, "dtfval");
            return this;
        }

        public Criteria andDtfvalNotEqualTo(Date value) {
            addCriterion("dtfval <>", value, "dtfval");
            return this;
        }

        public Criteria andDtfvalGreaterThan(Date value) {
            addCriterion("dtfval >", value, "dtfval");
            return this;
        }

        public Criteria andDtfvalGreaterThanOrEqualTo(Date value) {
            addCriterion("dtfval >=", value, "dtfval");
            return this;
        }

        public Criteria andDtfvalLessThan(Date value) {
            addCriterion("dtfval <", value, "dtfval");
            return this;
        }

        public Criteria andDtfvalLessThanOrEqualTo(Date value) {
            addCriterion("dtfval <=", value, "dtfval");
            return this;
        }

        public Criteria andDtfvalIn(List<Date> values) {
            addCriterion("dtfval in", values, "dtfval");
            return this;
        }

        public Criteria andDtfvalNotIn(List<Date> values) {
            addCriterion("dtfval not in", values, "dtfval");
            return this;
        }

        public Criteria andDtfvalBetween(Date value1, Date value2) {
            addCriterion("dtfval between", value1, value2, "dtfval");
            return this;
        }

        public Criteria andDtfvalNotBetween(Date value1, Date value2) {
            addCriterion("dtfval not between", value1, value2, "dtfval");
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