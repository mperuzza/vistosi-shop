package com.ateikon.internet.eprogen.domain.pgmr;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Vist_tipi_scadExample {
    /**
     * This field was generated by Apache iBATIS Ibator.
     * This field corresponds to the database table pgmr.vist_tipi_scad
     *
     * @ibatorgenerated Fri Jun 12 17:05:36 CEST 2009
     */
    protected String orderByClause;

    /**
     * This field was generated by Apache iBATIS Ibator.
     * This field corresponds to the database table pgmr.vist_tipi_scad
     *
     * @ibatorgenerated Fri Jun 12 17:05:36 CEST 2009
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.vist_tipi_scad
     *
     * @ibatorgenerated Fri Jun 12 17:05:36 CEST 2009
     */
    public Vist_tipi_scadExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.vist_tipi_scad
     *
     * @ibatorgenerated Fri Jun 12 17:05:36 CEST 2009
     */
    protected Vist_tipi_scadExample(Vist_tipi_scadExample example) {
        this.orderByClause = example.orderByClause;
        this.oredCriteria = example.oredCriteria;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.vist_tipi_scad
     *
     * @ibatorgenerated Fri Jun 12 17:05:36 CEST 2009
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.vist_tipi_scad
     *
     * @ibatorgenerated Fri Jun 12 17:05:36 CEST 2009
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.vist_tipi_scad
     *
     * @ibatorgenerated Fri Jun 12 17:05:36 CEST 2009
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.vist_tipi_scad
     *
     * @ibatorgenerated Fri Jun 12 17:05:36 CEST 2009
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.vist_tipi_scad
     *
     * @ibatorgenerated Fri Jun 12 17:05:36 CEST 2009
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
     * This method corresponds to the database table pgmr.vist_tipi_scad
     *
     * @ibatorgenerated Fri Jun 12 17:05:36 CEST 2009
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.vist_tipi_scad
     *
     * @ibatorgenerated Fri Jun 12 17:05:36 CEST 2009
     */
    public void clear() {
        oredCriteria.clear();
    }

    /**
     * This class was generated by Apache iBATIS Ibator.
     * This class corresponds to the database table pgmr.vist_tipi_scad
     *
     * @ibatorgenerated Fri Jun 12 17:05:36 CEST 2009
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

        public Criteria andCdvisttpscadIsNull() {
            addCriterion("cdvisttpscad is null");
            return this;
        }

        public Criteria andCdvisttpscadIsNotNull() {
            addCriterion("cdvisttpscad is not null");
            return this;
        }

        public Criteria andCdvisttpscadEqualTo(String value) {
            addCriterion("cdvisttpscad =", value, "cdvisttpscad");
            return this;
        }

        public Criteria andCdvisttpscadNotEqualTo(String value) {
            addCriterion("cdvisttpscad <>", value, "cdvisttpscad");
            return this;
        }

        public Criteria andCdvisttpscadGreaterThan(String value) {
            addCriterion("cdvisttpscad >", value, "cdvisttpscad");
            return this;
        }

        public Criteria andCdvisttpscadGreaterThanOrEqualTo(String value) {
            addCriterion("cdvisttpscad >=", value, "cdvisttpscad");
            return this;
        }

        public Criteria andCdvisttpscadLessThan(String value) {
            addCriterion("cdvisttpscad <", value, "cdvisttpscad");
            return this;
        }

        public Criteria andCdvisttpscadLessThanOrEqualTo(String value) {
            addCriterion("cdvisttpscad <=", value, "cdvisttpscad");
            return this;
        }

        public Criteria andCdvisttpscadLike(String value) {
            addCriterion("cdvisttpscad like", value, "cdvisttpscad");
            return this;
        }

        public Criteria andCdvisttpscadNotLike(String value) {
            addCriterion("cdvisttpscad not like", value, "cdvisttpscad");
            return this;
        }

        public Criteria andCdvisttpscadIn(List<String> values) {
            addCriterion("cdvisttpscad in", values, "cdvisttpscad");
            return this;
        }

        public Criteria andCdvisttpscadNotIn(List<String> values) {
            addCriterion("cdvisttpscad not in", values, "cdvisttpscad");
            return this;
        }

        public Criteria andCdvisttpscadBetween(String value1, String value2) {
            addCriterion("cdvisttpscad between", value1, value2, "cdvisttpscad");
            return this;
        }

        public Criteria andCdvisttpscadNotBetween(String value1, String value2) {
            addCriterion("cdvisttpscad not between", value1, value2, "cdvisttpscad");
            return this;
        }

        public Criteria andCdvisttpscad_mIsNull() {
            addCriterion("cdvisttpscad_m is null");
            return this;
        }

        public Criteria andCdvisttpscad_mIsNotNull() {
            addCriterion("cdvisttpscad_m is not null");
            return this;
        }

        public Criteria andCdvisttpscad_mEqualTo(String value) {
            addCriterion("cdvisttpscad_m =", value, "cdvisttpscad_m");
            return this;
        }

        public Criteria andCdvisttpscad_mNotEqualTo(String value) {
            addCriterion("cdvisttpscad_m <>", value, "cdvisttpscad_m");
            return this;
        }

        public Criteria andCdvisttpscad_mGreaterThan(String value) {
            addCriterion("cdvisttpscad_m >", value, "cdvisttpscad_m");
            return this;
        }

        public Criteria andCdvisttpscad_mGreaterThanOrEqualTo(String value) {
            addCriterion("cdvisttpscad_m >=", value, "cdvisttpscad_m");
            return this;
        }

        public Criteria andCdvisttpscad_mLessThan(String value) {
            addCriterion("cdvisttpscad_m <", value, "cdvisttpscad_m");
            return this;
        }

        public Criteria andCdvisttpscad_mLessThanOrEqualTo(String value) {
            addCriterion("cdvisttpscad_m <=", value, "cdvisttpscad_m");
            return this;
        }

        public Criteria andCdvisttpscad_mLike(String value) {
            addCriterion("cdvisttpscad_m like", value, "cdvisttpscad_m");
            return this;
        }

        public Criteria andCdvisttpscad_mNotLike(String value) {
            addCriterion("cdvisttpscad_m not like", value, "cdvisttpscad_m");
            return this;
        }

        public Criteria andCdvisttpscad_mIn(List<String> values) {
            addCriterion("cdvisttpscad_m in", values, "cdvisttpscad_m");
            return this;
        }

        public Criteria andCdvisttpscad_mNotIn(List<String> values) {
            addCriterion("cdvisttpscad_m not in", values, "cdvisttpscad_m");
            return this;
        }

        public Criteria andCdvisttpscad_mBetween(String value1, String value2) {
            addCriterion("cdvisttpscad_m between", value1, value2, "cdvisttpscad_m");
            return this;
        }

        public Criteria andCdvisttpscad_mNotBetween(String value1, String value2) {
            addCriterion("cdvisttpscad_m not between", value1, value2, "cdvisttpscad_m");
            return this;
        }

        public Criteria andDsvisttpscadIsNull() {
            addCriterion("dsvisttpscad is null");
            return this;
        }

        public Criteria andDsvisttpscadIsNotNull() {
            addCriterion("dsvisttpscad is not null");
            return this;
        }

        public Criteria andDsvisttpscadEqualTo(String value) {
            addCriterion("dsvisttpscad =", value, "dsvisttpscad");
            return this;
        }

        public Criteria andDsvisttpscadNotEqualTo(String value) {
            addCriterion("dsvisttpscad <>", value, "dsvisttpscad");
            return this;
        }

        public Criteria andDsvisttpscadGreaterThan(String value) {
            addCriterion("dsvisttpscad >", value, "dsvisttpscad");
            return this;
        }

        public Criteria andDsvisttpscadGreaterThanOrEqualTo(String value) {
            addCriterion("dsvisttpscad >=", value, "dsvisttpscad");
            return this;
        }

        public Criteria andDsvisttpscadLessThan(String value) {
            addCriterion("dsvisttpscad <", value, "dsvisttpscad");
            return this;
        }

        public Criteria andDsvisttpscadLessThanOrEqualTo(String value) {
            addCriterion("dsvisttpscad <=", value, "dsvisttpscad");
            return this;
        }

        public Criteria andDsvisttpscadLike(String value) {
            addCriterion("dsvisttpscad like", value, "dsvisttpscad");
            return this;
        }

        public Criteria andDsvisttpscadNotLike(String value) {
            addCriterion("dsvisttpscad not like", value, "dsvisttpscad");
            return this;
        }

        public Criteria andDsvisttpscadIn(List<String> values) {
            addCriterion("dsvisttpscad in", values, "dsvisttpscad");
            return this;
        }

        public Criteria andDsvisttpscadNotIn(List<String> values) {
            addCriterion("dsvisttpscad not in", values, "dsvisttpscad");
            return this;
        }

        public Criteria andDsvisttpscadBetween(String value1, String value2) {
            addCriterion("dsvisttpscad between", value1, value2, "dsvisttpscad");
            return this;
        }

        public Criteria andDsvisttpscadNotBetween(String value1, String value2) {
            addCriterion("dsvisttpscad not between", value1, value2, "dsvisttpscad");
            return this;
        }

        public Criteria andFgarischioIsNull() {
            addCriterion("fgarischio is null");
            return this;
        }

        public Criteria andFgarischioIsNotNull() {
            addCriterion("fgarischio is not null");
            return this;
        }

        public Criteria andFgarischioEqualTo(String value) {
            addCriterion("fgarischio =", value, "fgarischio");
            return this;
        }

        public Criteria andFgarischioNotEqualTo(String value) {
            addCriterion("fgarischio <>", value, "fgarischio");
            return this;
        }

        public Criteria andFgarischioGreaterThan(String value) {
            addCriterion("fgarischio >", value, "fgarischio");
            return this;
        }

        public Criteria andFgarischioGreaterThanOrEqualTo(String value) {
            addCriterion("fgarischio >=", value, "fgarischio");
            return this;
        }

        public Criteria andFgarischioLessThan(String value) {
            addCriterion("fgarischio <", value, "fgarischio");
            return this;
        }

        public Criteria andFgarischioLessThanOrEqualTo(String value) {
            addCriterion("fgarischio <=", value, "fgarischio");
            return this;
        }

        public Criteria andFgarischioLike(String value) {
            addCriterion("fgarischio like", value, "fgarischio");
            return this;
        }

        public Criteria andFgarischioNotLike(String value) {
            addCriterion("fgarischio not like", value, "fgarischio");
            return this;
        }

        public Criteria andFgarischioIn(List<String> values) {
            addCriterion("fgarischio in", values, "fgarischio");
            return this;
        }

        public Criteria andFgarischioNotIn(List<String> values) {
            addCriterion("fgarischio not in", values, "fgarischio");
            return this;
        }

        public Criteria andFgarischioBetween(String value1, String value2) {
            addCriterion("fgarischio between", value1, value2, "fgarischio");
            return this;
        }

        public Criteria andFgarischioNotBetween(String value1, String value2) {
            addCriterion("fgarischio not between", value1, value2, "fgarischio");
            return this;
        }

        public Criteria andPgriscIsNull() {
            addCriterion("pgrisc is null");
            return this;
        }

        public Criteria andPgriscIsNotNull() {
            addCriterion("pgrisc is not null");
            return this;
        }

        public Criteria andPgriscEqualTo(Double value) {
            addCriterion("pgrisc =", value, "pgrisc");
            return this;
        }

        public Criteria andPgriscNotEqualTo(Double value) {
            addCriterion("pgrisc <>", value, "pgrisc");
            return this;
        }

        public Criteria andPgriscGreaterThan(Double value) {
            addCriterion("pgrisc >", value, "pgrisc");
            return this;
        }

        public Criteria andPgriscGreaterThanOrEqualTo(Double value) {
            addCriterion("pgrisc >=", value, "pgrisc");
            return this;
        }

        public Criteria andPgriscLessThan(Double value) {
            addCriterion("pgrisc <", value, "pgrisc");
            return this;
        }

        public Criteria andPgriscLessThanOrEqualTo(Double value) {
            addCriterion("pgrisc <=", value, "pgrisc");
            return this;
        }

        public Criteria andPgriscIn(List<Double> values) {
            addCriterion("pgrisc in", values, "pgrisc");
            return this;
        }

        public Criteria andPgriscNotIn(List<Double> values) {
            addCriterion("pgrisc not in", values, "pgrisc");
            return this;
        }

        public Criteria andPgriscBetween(Double value1, Double value2) {
            addCriterion("pgrisc between", value1, value2, "pgrisc");
            return this;
        }

        public Criteria andPgriscNotBetween(Double value1, Double value2) {
            addCriterion("pgrisc not between", value1, value2, "pgrisc");
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