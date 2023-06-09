package com.ateikon.internet.eprogen.domain.pgmr;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DesignerExample {
    /**
     * This field was generated by Apache iBATIS Ibator.
     * This field corresponds to the database table pgmr.designer
     *
     * @ibatorgenerated Tue Sep 21 13:08:34 CEST 2010
     */
    protected String orderByClause;

    /**
     * This field was generated by Apache iBATIS Ibator.
     * This field corresponds to the database table pgmr.designer
     *
     * @ibatorgenerated Tue Sep 21 13:08:34 CEST 2010
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.designer
     *
     * @ibatorgenerated Tue Sep 21 13:08:34 CEST 2010
     */
    public DesignerExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.designer
     *
     * @ibatorgenerated Tue Sep 21 13:08:34 CEST 2010
     */
    protected DesignerExample(DesignerExample example) {
        this.orderByClause = example.orderByClause;
        this.oredCriteria = example.oredCriteria;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.designer
     *
     * @ibatorgenerated Tue Sep 21 13:08:34 CEST 2010
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.designer
     *
     * @ibatorgenerated Tue Sep 21 13:08:34 CEST 2010
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.designer
     *
     * @ibatorgenerated Tue Sep 21 13:08:34 CEST 2010
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.designer
     *
     * @ibatorgenerated Tue Sep 21 13:08:34 CEST 2010
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.designer
     *
     * @ibatorgenerated Tue Sep 21 13:08:34 CEST 2010
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
     * This method corresponds to the database table pgmr.designer
     *
     * @ibatorgenerated Tue Sep 21 13:08:34 CEST 2010
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.designer
     *
     * @ibatorgenerated Tue Sep 21 13:08:34 CEST 2010
     */
    public void clear() {
        oredCriteria.clear();
    }

    /**
     * This class was generated by Apache iBATIS Ibator.
     * This class corresponds to the database table pgmr.designer
     *
     * @ibatorgenerated Tue Sep 21 13:08:34 CEST 2010
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

        public Criteria andCddesignerIsNull() {
            addCriterion("cddesigner is null");
            return this;
        }

        public Criteria andCddesignerIsNotNull() {
            addCriterion("cddesigner is not null");
            return this;
        }

        public Criteria andCddesignerEqualTo(String value) {
            addCriterion("cddesigner =", value, "cddesigner");
            return this;
        }

        public Criteria andCddesignerNotEqualTo(String value) {
            addCriterion("cddesigner <>", value, "cddesigner");
            return this;
        }

        public Criteria andCddesignerGreaterThan(String value) {
            addCriterion("cddesigner >", value, "cddesigner");
            return this;
        }

        public Criteria andCddesignerGreaterThanOrEqualTo(String value) {
            addCriterion("cddesigner >=", value, "cddesigner");
            return this;
        }

        public Criteria andCddesignerLessThan(String value) {
            addCriterion("cddesigner <", value, "cddesigner");
            return this;
        }

        public Criteria andCddesignerLessThanOrEqualTo(String value) {
            addCriterion("cddesigner <=", value, "cddesigner");
            return this;
        }

        public Criteria andCddesignerLike(String value) {
            addCriterion("cddesigner like", value, "cddesigner");
            return this;
        }

        public Criteria andCddesignerNotLike(String value) {
            addCriterion("cddesigner not like", value, "cddesigner");
            return this;
        }

        public Criteria andCddesignerIn(List<String> values) {
            addCriterion("cddesigner in", values, "cddesigner");
            return this;
        }

        public Criteria andCddesignerNotIn(List<String> values) {
            addCriterion("cddesigner not in", values, "cddesigner");
            return this;
        }

        public Criteria andCddesignerBetween(String value1, String value2) {
            addCriterion("cddesigner between", value1, value2, "cddesigner");
            return this;
        }

        public Criteria andCddesignerNotBetween(String value1, String value2) {
            addCriterion("cddesigner not between", value1, value2, "cddesigner");
            return this;
        }

        public Criteria andCddesigner_mIsNull() {
            addCriterion("cddesigner_m is null");
            return this;
        }

        public Criteria andCddesigner_mIsNotNull() {
            addCriterion("cddesigner_m is not null");
            return this;
        }

        public Criteria andCddesigner_mEqualTo(String value) {
            addCriterion("cddesigner_m =", value, "cddesigner_m");
            return this;
        }

        public Criteria andCddesigner_mNotEqualTo(String value) {
            addCriterion("cddesigner_m <>", value, "cddesigner_m");
            return this;
        }

        public Criteria andCddesigner_mGreaterThan(String value) {
            addCriterion("cddesigner_m >", value, "cddesigner_m");
            return this;
        }

        public Criteria andCddesigner_mGreaterThanOrEqualTo(String value) {
            addCriterion("cddesigner_m >=", value, "cddesigner_m");
            return this;
        }

        public Criteria andCddesigner_mLessThan(String value) {
            addCriterion("cddesigner_m <", value, "cddesigner_m");
            return this;
        }

        public Criteria andCddesigner_mLessThanOrEqualTo(String value) {
            addCriterion("cddesigner_m <=", value, "cddesigner_m");
            return this;
        }

        public Criteria andCddesigner_mLike(String value) {
            addCriterion("cddesigner_m like", value, "cddesigner_m");
            return this;
        }

        public Criteria andCddesigner_mNotLike(String value) {
            addCriterion("cddesigner_m not like", value, "cddesigner_m");
            return this;
        }

        public Criteria andCddesigner_mIn(List<String> values) {
            addCriterion("cddesigner_m in", values, "cddesigner_m");
            return this;
        }

        public Criteria andCddesigner_mNotIn(List<String> values) {
            addCriterion("cddesigner_m not in", values, "cddesigner_m");
            return this;
        }

        public Criteria andCddesigner_mBetween(String value1, String value2) {
            addCriterion("cddesigner_m between", value1, value2, "cddesigner_m");
            return this;
        }

        public Criteria andCddesigner_mNotBetween(String value1, String value2) {
            addCriterion("cddesigner_m not between", value1, value2, "cddesigner_m");
            return this;
        }

        public Criteria andDsdesignerIsNull() {
            addCriterion("dsdesigner is null");
            return this;
        }

        public Criteria andDsdesignerIsNotNull() {
            addCriterion("dsdesigner is not null");
            return this;
        }

        public Criteria andDsdesignerEqualTo(String value) {
            addCriterion("dsdesigner =", value, "dsdesigner");
            return this;
        }

        public Criteria andDsdesignerNotEqualTo(String value) {
            addCriterion("dsdesigner <>", value, "dsdesigner");
            return this;
        }

        public Criteria andDsdesignerGreaterThan(String value) {
            addCriterion("dsdesigner >", value, "dsdesigner");
            return this;
        }

        public Criteria andDsdesignerGreaterThanOrEqualTo(String value) {
            addCriterion("dsdesigner >=", value, "dsdesigner");
            return this;
        }

        public Criteria andDsdesignerLessThan(String value) {
            addCriterion("dsdesigner <", value, "dsdesigner");
            return this;
        }

        public Criteria andDsdesignerLessThanOrEqualTo(String value) {
            addCriterion("dsdesigner <=", value, "dsdesigner");
            return this;
        }

        public Criteria andDsdesignerLike(String value) {
            addCriterion("dsdesigner like", value, "dsdesigner");
            return this;
        }

        public Criteria andDsdesignerNotLike(String value) {
            addCriterion("dsdesigner not like", value, "dsdesigner");
            return this;
        }

        public Criteria andDsdesignerIn(List<String> values) {
            addCriterion("dsdesigner in", values, "dsdesigner");
            return this;
        }

        public Criteria andDsdesignerNotIn(List<String> values) {
            addCriterion("dsdesigner not in", values, "dsdesigner");
            return this;
        }

        public Criteria andDsdesignerBetween(String value1, String value2) {
            addCriterion("dsdesigner between", value1, value2, "dsdesigner");
            return this;
        }

        public Criteria andDsdesignerNotBetween(String value1, String value2) {
            addCriterion("dsdesigner not between", value1, value2, "dsdesigner");
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

        public Criteria andLista_designerIsNull() {
            addCriterion("lista_designer is null");
            return this;
        }

        public Criteria andLista_designerIsNotNull() {
            addCriterion("lista_designer is not null");
            return this;
        }

        public Criteria andLista_designerEqualTo(String value) {
            addCriterion("lista_designer =", value, "lista_designer");
            return this;
        }

        public Criteria andLista_designerNotEqualTo(String value) {
            addCriterion("lista_designer <>", value, "lista_designer");
            return this;
        }

        public Criteria andLista_designerGreaterThan(String value) {
            addCriterion("lista_designer >", value, "lista_designer");
            return this;
        }

        public Criteria andLista_designerGreaterThanOrEqualTo(String value) {
            addCriterion("lista_designer >=", value, "lista_designer");
            return this;
        }

        public Criteria andLista_designerLessThan(String value) {
            addCriterion("lista_designer <", value, "lista_designer");
            return this;
        }

        public Criteria andLista_designerLessThanOrEqualTo(String value) {
            addCriterion("lista_designer <=", value, "lista_designer");
            return this;
        }

        public Criteria andLista_designerLike(String value) {
            addCriterion("lista_designer like", value, "lista_designer");
            return this;
        }

        public Criteria andLista_designerNotLike(String value) {
            addCriterion("lista_designer not like", value, "lista_designer");
            return this;
        }

        public Criteria andLista_designerIn(List<String> values) {
            addCriterion("lista_designer in", values, "lista_designer");
            return this;
        }

        public Criteria andLista_designerNotIn(List<String> values) {
            addCriterion("lista_designer not in", values, "lista_designer");
            return this;
        }

        public Criteria andLista_designerBetween(String value1, String value2) {
            addCriterion("lista_designer between", value1, value2, "lista_designer");
            return this;
        }

        public Criteria andLista_designerNotBetween(String value1, String value2) {
            addCriterion("lista_designer not between", value1, value2, "lista_designer");
            return this;
        }
    }
}