package com.ateikon.internet.eprogen.domain.pgmr;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Vist_offerteExample {
    /**
     * This field was generated by Apache iBATIS Ibator.
     * This field corresponds to the database table pgmr.vist_offerte
     *
     * @ibatorgenerated Tue Mar 27 10:06:31 CEST 2012
     */
    protected String orderByClause;

    /**
     * This field was generated by Apache iBATIS Ibator.
     * This field corresponds to the database table pgmr.vist_offerte
     *
     * @ibatorgenerated Tue Mar 27 10:06:31 CEST 2012
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.vist_offerte
     *
     * @ibatorgenerated Tue Mar 27 10:06:31 CEST 2012
     */
    public Vist_offerteExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.vist_offerte
     *
     * @ibatorgenerated Tue Mar 27 10:06:31 CEST 2012
     */
    protected Vist_offerteExample(Vist_offerteExample example) {
        this.orderByClause = example.orderByClause;
        this.oredCriteria = example.oredCriteria;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.vist_offerte
     *
     * @ibatorgenerated Tue Mar 27 10:06:31 CEST 2012
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.vist_offerte
     *
     * @ibatorgenerated Tue Mar 27 10:06:31 CEST 2012
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.vist_offerte
     *
     * @ibatorgenerated Tue Mar 27 10:06:31 CEST 2012
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.vist_offerte
     *
     * @ibatorgenerated Tue Mar 27 10:06:31 CEST 2012
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.vist_offerte
     *
     * @ibatorgenerated Tue Mar 27 10:06:31 CEST 2012
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
     * This method corresponds to the database table pgmr.vist_offerte
     *
     * @ibatorgenerated Tue Mar 27 10:06:31 CEST 2012
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.vist_offerte
     *
     * @ibatorgenerated Tue Mar 27 10:06:31 CEST 2012
     */
    public void clear() {
        oredCriteria.clear();
    }

    /**
     * This class was generated by Apache iBATIS Ibator.
     * This class corresponds to the database table pgmr.vist_offerte
     *
     * @ibatorgenerated Tue Mar 27 10:06:31 CEST 2012
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

        public Criteria andTkvistoffIsNull() {
            addCriterion("tkvistoff is null");
            return this;
        }

        public Criteria andTkvistoffIsNotNull() {
            addCriterion("tkvistoff is not null");
            return this;
        }

        public Criteria andTkvistoffEqualTo(Long value) {
            addCriterion("tkvistoff =", value, "tkvistoff");
            return this;
        }

        public Criteria andTkvistoffNotEqualTo(Long value) {
            addCriterion("tkvistoff <>", value, "tkvistoff");
            return this;
        }

        public Criteria andTkvistoffGreaterThan(Long value) {
            addCriterion("tkvistoff >", value, "tkvistoff");
            return this;
        }

        public Criteria andTkvistoffGreaterThanOrEqualTo(Long value) {
            addCriterion("tkvistoff >=", value, "tkvistoff");
            return this;
        }

        public Criteria andTkvistoffLessThan(Long value) {
            addCriterion("tkvistoff <", value, "tkvistoff");
            return this;
        }

        public Criteria andTkvistoffLessThanOrEqualTo(Long value) {
            addCriterion("tkvistoff <=", value, "tkvistoff");
            return this;
        }

        public Criteria andTkvistoffIn(List<Long> values) {
            addCriterion("tkvistoff in", values, "tkvistoff");
            return this;
        }

        public Criteria andTkvistoffNotIn(List<Long> values) {
            addCriterion("tkvistoff not in", values, "tkvistoff");
            return this;
        }

        public Criteria andTkvistoffBetween(Long value1, Long value2) {
            addCriterion("tkvistoff between", value1, value2, "tkvistoff");
            return this;
        }

        public Criteria andTkvistoffNotBetween(Long value1, Long value2) {
            addCriterion("tkvistoff not between", value1, value2, "tkvistoff");
            return this;
        }

        public Criteria andCdartiIsNull() {
            addCriterion("cdarti is null");
            return this;
        }

        public Criteria andCdartiIsNotNull() {
            addCriterion("cdarti is not null");
            return this;
        }

        public Criteria andCdartiEqualTo(String value) {
            addCriterion("cdarti =", value, "cdarti");
            return this;
        }

        public Criteria andCdartiNotEqualTo(String value) {
            addCriterion("cdarti <>", value, "cdarti");
            return this;
        }

        public Criteria andCdartiGreaterThan(String value) {
            addCriterion("cdarti >", value, "cdarti");
            return this;
        }

        public Criteria andCdartiGreaterThanOrEqualTo(String value) {
            addCriterion("cdarti >=", value, "cdarti");
            return this;
        }

        public Criteria andCdartiLessThan(String value) {
            addCriterion("cdarti <", value, "cdarti");
            return this;
        }

        public Criteria andCdartiLessThanOrEqualTo(String value) {
            addCriterion("cdarti <=", value, "cdarti");
            return this;
        }

        public Criteria andCdartiLike(String value) {
            addCriterion("cdarti like", value, "cdarti");
            return this;
        }

        public Criteria andCdartiNotLike(String value) {
            addCriterion("cdarti not like", value, "cdarti");
            return this;
        }

        public Criteria andCdartiIn(List<String> values) {
            addCriterion("cdarti in", values, "cdarti");
            return this;
        }

        public Criteria andCdartiNotIn(List<String> values) {
            addCriterion("cdarti not in", values, "cdarti");
            return this;
        }

        public Criteria andCdartiBetween(String value1, String value2) {
            addCriterion("cdarti between", value1, value2, "cdarti");
            return this;
        }

        public Criteria andCdartiNotBetween(String value1, String value2) {
            addCriterion("cdarti not between", value1, value2, "cdarti");
            return this;
        }

        public Criteria andZee_zef_realeIsNull() {
            addCriterion("zee_zef_reale is null");
            return this;
        }

        public Criteria andZee_zef_realeIsNotNull() {
            addCriterion("zee_zef_reale is not null");
            return this;
        }

        public Criteria andZee_zef_realeEqualTo(Double value) {
            addCriterion("zee_zef_reale =", value, "zee_zef_reale");
            return this;
        }

        public Criteria andZee_zef_realeNotEqualTo(Double value) {
            addCriterion("zee_zef_reale <>", value, "zee_zef_reale");
            return this;
        }

        public Criteria andZee_zef_realeGreaterThan(Double value) {
            addCriterion("zee_zef_reale >", value, "zee_zef_reale");
            return this;
        }

        public Criteria andZee_zef_realeGreaterThanOrEqualTo(Double value) {
            addCriterion("zee_zef_reale >=", value, "zee_zef_reale");
            return this;
        }

        public Criteria andZee_zef_realeLessThan(Double value) {
            addCriterion("zee_zef_reale <", value, "zee_zef_reale");
            return this;
        }

        public Criteria andZee_zef_realeLessThanOrEqualTo(Double value) {
            addCriterion("zee_zef_reale <=", value, "zee_zef_reale");
            return this;
        }

        public Criteria andZee_zef_realeIn(List<Double> values) {
            addCriterion("zee_zef_reale in", values, "zee_zef_reale");
            return this;
        }

        public Criteria andZee_zef_realeNotIn(List<Double> values) {
            addCriterion("zee_zef_reale not in", values, "zee_zef_reale");
            return this;
        }

        public Criteria andZee_zef_realeBetween(Double value1, Double value2) {
            addCriterion("zee_zef_reale between", value1, value2, "zee_zef_reale");
            return this;
        }

        public Criteria andZee_zef_realeNotBetween(Double value1, Double value2) {
            addCriterion("zee_zef_reale not between", value1, value2, "zee_zef_reale");
            return this;
        }

        public Criteria andZee_zef_teoricoIsNull() {
            addCriterion("zee_zef_teorico is null");
            return this;
        }

        public Criteria andZee_zef_teoricoIsNotNull() {
            addCriterion("zee_zef_teorico is not null");
            return this;
        }

        public Criteria andZee_zef_teoricoEqualTo(Double value) {
            addCriterion("zee_zef_teorico =", value, "zee_zef_teorico");
            return this;
        }

        public Criteria andZee_zef_teoricoNotEqualTo(Double value) {
            addCriterion("zee_zef_teorico <>", value, "zee_zef_teorico");
            return this;
        }

        public Criteria andZee_zef_teoricoGreaterThan(Double value) {
            addCriterion("zee_zef_teorico >", value, "zee_zef_teorico");
            return this;
        }

        public Criteria andZee_zef_teoricoGreaterThanOrEqualTo(Double value) {
            addCriterion("zee_zef_teorico >=", value, "zee_zef_teorico");
            return this;
        }

        public Criteria andZee_zef_teoricoLessThan(Double value) {
            addCriterion("zee_zef_teorico <", value, "zee_zef_teorico");
            return this;
        }

        public Criteria andZee_zef_teoricoLessThanOrEqualTo(Double value) {
            addCriterion("zee_zef_teorico <=", value, "zee_zef_teorico");
            return this;
        }

        public Criteria andZee_zef_teoricoIn(List<Double> values) {
            addCriterion("zee_zef_teorico in", values, "zee_zef_teorico");
            return this;
        }

        public Criteria andZee_zef_teoricoNotIn(List<Double> values) {
            addCriterion("zee_zef_teorico not in", values, "zee_zef_teorico");
            return this;
        }

        public Criteria andZee_zef_teoricoBetween(Double value1, Double value2) {
            addCriterion("zee_zef_teorico between", value1, value2, "zee_zef_teorico");
            return this;
        }

        public Criteria andZee_zef_teoricoNotBetween(Double value1, Double value2) {
            addCriterion("zee_zef_teorico not between", value1, value2, "zee_zef_teorico");
            return this;
        }

        public Criteria andIncentiveIsNull() {
            addCriterion("incentive is null");
            return this;
        }

        public Criteria andIncentiveIsNotNull() {
            addCriterion("incentive is not null");
            return this;
        }

        public Criteria andIncentiveEqualTo(Double value) {
            addCriterion("incentive =", value, "incentive");
            return this;
        }

        public Criteria andIncentiveNotEqualTo(Double value) {
            addCriterion("incentive <>", value, "incentive");
            return this;
        }

        public Criteria andIncentiveGreaterThan(Double value) {
            addCriterion("incentive >", value, "incentive");
            return this;
        }

        public Criteria andIncentiveGreaterThanOrEqualTo(Double value) {
            addCriterion("incentive >=", value, "incentive");
            return this;
        }

        public Criteria andIncentiveLessThan(Double value) {
            addCriterion("incentive <", value, "incentive");
            return this;
        }

        public Criteria andIncentiveLessThanOrEqualTo(Double value) {
            addCriterion("incentive <=", value, "incentive");
            return this;
        }

        public Criteria andIncentiveIn(List<Double> values) {
            addCriterion("incentive in", values, "incentive");
            return this;
        }

        public Criteria andIncentiveNotIn(List<Double> values) {
            addCriterion("incentive not in", values, "incentive");
            return this;
        }

        public Criteria andIncentiveBetween(Double value1, Double value2) {
            addCriterion("incentive between", value1, value2, "incentive");
            return this;
        }

        public Criteria andIncentiveNotBetween(Double value1, Double value2) {
            addCriterion("incentive not between", value1, value2, "incentive");
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

        public Criteria andFg_aggIsNull() {
            addCriterion("fg_agg is null");
            return this;
        }

        public Criteria andFg_aggIsNotNull() {
            addCriterion("fg_agg is not null");
            return this;
        }

        public Criteria andFg_aggEqualTo(String value) {
            addCriterion("fg_agg =", value, "fg_agg");
            return this;
        }

        public Criteria andFg_aggNotEqualTo(String value) {
            addCriterion("fg_agg <>", value, "fg_agg");
            return this;
        }

        public Criteria andFg_aggGreaterThan(String value) {
            addCriterion("fg_agg >", value, "fg_agg");
            return this;
        }

        public Criteria andFg_aggGreaterThanOrEqualTo(String value) {
            addCriterion("fg_agg >=", value, "fg_agg");
            return this;
        }

        public Criteria andFg_aggLessThan(String value) {
            addCriterion("fg_agg <", value, "fg_agg");
            return this;
        }

        public Criteria andFg_aggLessThanOrEqualTo(String value) {
            addCriterion("fg_agg <=", value, "fg_agg");
            return this;
        }

        public Criteria andFg_aggLike(String value) {
            addCriterion("fg_agg like", value, "fg_agg");
            return this;
        }

        public Criteria andFg_aggNotLike(String value) {
            addCriterion("fg_agg not like", value, "fg_agg");
            return this;
        }

        public Criteria andFg_aggIn(List<String> values) {
            addCriterion("fg_agg in", values, "fg_agg");
            return this;
        }

        public Criteria andFg_aggNotIn(List<String> values) {
            addCriterion("fg_agg not in", values, "fg_agg");
            return this;
        }

        public Criteria andFg_aggBetween(String value1, String value2) {
            addCriterion("fg_agg between", value1, value2, "fg_agg");
            return this;
        }

        public Criteria andFg_aggNotBetween(String value1, String value2) {
            addCriterion("fg_agg not between", value1, value2, "fg_agg");
            return this;
        }
    }
}