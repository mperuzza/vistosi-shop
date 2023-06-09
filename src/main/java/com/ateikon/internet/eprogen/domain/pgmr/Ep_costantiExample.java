package com.ateikon.internet.eprogen.domain.pgmr;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Ep_costantiExample {
    /**
     * This field was generated by Apache iBATIS Ibator.
     * This field corresponds to the database table pgmr.ep_costanti
     *
     * @ibatorgenerated Wed Nov 27 12:37:26 CET 2013
     */
    protected String orderByClause;

    /**
     * This field was generated by Apache iBATIS Ibator.
     * This field corresponds to the database table pgmr.ep_costanti
     *
     * @ibatorgenerated Wed Nov 27 12:37:26 CET 2013
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.ep_costanti
     *
     * @ibatorgenerated Wed Nov 27 12:37:26 CET 2013
     */
    public Ep_costantiExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.ep_costanti
     *
     * @ibatorgenerated Wed Nov 27 12:37:26 CET 2013
     */
    protected Ep_costantiExample(Ep_costantiExample example) {
        this.orderByClause = example.orderByClause;
        this.oredCriteria = example.oredCriteria;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.ep_costanti
     *
     * @ibatorgenerated Wed Nov 27 12:37:26 CET 2013
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.ep_costanti
     *
     * @ibatorgenerated Wed Nov 27 12:37:26 CET 2013
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.ep_costanti
     *
     * @ibatorgenerated Wed Nov 27 12:37:26 CET 2013
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.ep_costanti
     *
     * @ibatorgenerated Wed Nov 27 12:37:26 CET 2013
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.ep_costanti
     *
     * @ibatorgenerated Wed Nov 27 12:37:26 CET 2013
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
     * This method corresponds to the database table pgmr.ep_costanti
     *
     * @ibatorgenerated Wed Nov 27 12:37:26 CET 2013
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.ep_costanti
     *
     * @ibatorgenerated Wed Nov 27 12:37:26 CET 2013
     */
    public void clear() {
        oredCriteria.clear();
    }

    /**
     * This class was generated by Apache iBATIS Ibator.
     * This class corresponds to the database table pgmr.ep_costanti
     *
     * @ibatorgenerated Wed Nov 27 12:37:26 CET 2013
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

        public Criteria andTkcostIsNull() {
            addCriterion("tkcost is null");
            return this;
        }

        public Criteria andTkcostIsNotNull() {
            addCriterion("tkcost is not null");
            return this;
        }

        public Criteria andTkcostEqualTo(Long value) {
            addCriterion("tkcost =", value, "tkcost");
            return this;
        }

        public Criteria andTkcostNotEqualTo(Long value) {
            addCriterion("tkcost <>", value, "tkcost");
            return this;
        }

        public Criteria andTkcostGreaterThan(Long value) {
            addCriterion("tkcost >", value, "tkcost");
            return this;
        }

        public Criteria andTkcostGreaterThanOrEqualTo(Long value) {
            addCriterion("tkcost >=", value, "tkcost");
            return this;
        }

        public Criteria andTkcostLessThan(Long value) {
            addCriterion("tkcost <", value, "tkcost");
            return this;
        }

        public Criteria andTkcostLessThanOrEqualTo(Long value) {
            addCriterion("tkcost <=", value, "tkcost");
            return this;
        }

        public Criteria andTkcostIn(List<Long> values) {
            addCriterion("tkcost in", values, "tkcost");
            return this;
        }

        public Criteria andTkcostNotIn(List<Long> values) {
            addCriterion("tkcost not in", values, "tkcost");
            return this;
        }

        public Criteria andTkcostBetween(Long value1, Long value2) {
            addCriterion("tkcost between", value1, value2, "tkcost");
            return this;
        }

        public Criteria andTkcostNotBetween(Long value1, Long value2) {
            addCriterion("tkcost not between", value1, value2, "tkcost");
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

        public Criteria andCdapplIsNull() {
            addCriterion("cdappl is null");
            return this;
        }

        public Criteria andCdapplIsNotNull() {
            addCriterion("cdappl is not null");
            return this;
        }

        public Criteria andCdapplEqualTo(String value) {
            addCriterion("cdappl =", value, "cdappl");
            return this;
        }

        public Criteria andCdapplNotEqualTo(String value) {
            addCriterion("cdappl <>", value, "cdappl");
            return this;
        }

        public Criteria andCdapplGreaterThan(String value) {
            addCriterion("cdappl >", value, "cdappl");
            return this;
        }

        public Criteria andCdapplGreaterThanOrEqualTo(String value) {
            addCriterion("cdappl >=", value, "cdappl");
            return this;
        }

        public Criteria andCdapplLessThan(String value) {
            addCriterion("cdappl <", value, "cdappl");
            return this;
        }

        public Criteria andCdapplLessThanOrEqualTo(String value) {
            addCriterion("cdappl <=", value, "cdappl");
            return this;
        }

        public Criteria andCdapplLike(String value) {
            addCriterion("cdappl like", value, "cdappl");
            return this;
        }

        public Criteria andCdapplNotLike(String value) {
            addCriterion("cdappl not like", value, "cdappl");
            return this;
        }

        public Criteria andCdapplIn(List<String> values) {
            addCriterion("cdappl in", values, "cdappl");
            return this;
        }

        public Criteria andCdapplNotIn(List<String> values) {
            addCriterion("cdappl not in", values, "cdappl");
            return this;
        }

        public Criteria andCdapplBetween(String value1, String value2) {
            addCriterion("cdappl between", value1, value2, "cdappl");
            return this;
        }

        public Criteria andCdapplNotBetween(String value1, String value2) {
            addCriterion("cdappl not between", value1, value2, "cdappl");
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

        public Criteria andCostnameIsNull() {
            addCriterion("costname is null");
            return this;
        }

        public Criteria andCostnameIsNotNull() {
            addCriterion("costname is not null");
            return this;
        }

        public Criteria andCostnameEqualTo(String value) {
            addCriterion("costname =", value, "costname");
            return this;
        }

        public Criteria andCostnameNotEqualTo(String value) {
            addCriterion("costname <>", value, "costname");
            return this;
        }

        public Criteria andCostnameGreaterThan(String value) {
            addCriterion("costname >", value, "costname");
            return this;
        }

        public Criteria andCostnameGreaterThanOrEqualTo(String value) {
            addCriterion("costname >=", value, "costname");
            return this;
        }

        public Criteria andCostnameLessThan(String value) {
            addCriterion("costname <", value, "costname");
            return this;
        }

        public Criteria andCostnameLessThanOrEqualTo(String value) {
            addCriterion("costname <=", value, "costname");
            return this;
        }

        public Criteria andCostnameLike(String value) {
            addCriterion("costname like", value, "costname");
            return this;
        }

        public Criteria andCostnameNotLike(String value) {
            addCriterion("costname not like", value, "costname");
            return this;
        }

        public Criteria andCostnameIn(List<String> values) {
            addCriterion("costname in", values, "costname");
            return this;
        }

        public Criteria andCostnameNotIn(List<String> values) {
            addCriterion("costname not in", values, "costname");
            return this;
        }

        public Criteria andCostnameBetween(String value1, String value2) {
            addCriterion("costname between", value1, value2, "costname");
            return this;
        }

        public Criteria andCostnameNotBetween(String value1, String value2) {
            addCriterion("costname not between", value1, value2, "costname");
            return this;
        }

        public Criteria andCostlabelIsNull() {
            addCriterion("costlabel is null");
            return this;
        }

        public Criteria andCostlabelIsNotNull() {
            addCriterion("costlabel is not null");
            return this;
        }

        public Criteria andCostlabelEqualTo(String value) {
            addCriterion("costlabel =", value, "costlabel");
            return this;
        }

        public Criteria andCostlabelNotEqualTo(String value) {
            addCriterion("costlabel <>", value, "costlabel");
            return this;
        }

        public Criteria andCostlabelGreaterThan(String value) {
            addCriterion("costlabel >", value, "costlabel");
            return this;
        }

        public Criteria andCostlabelGreaterThanOrEqualTo(String value) {
            addCriterion("costlabel >=", value, "costlabel");
            return this;
        }

        public Criteria andCostlabelLessThan(String value) {
            addCriterion("costlabel <", value, "costlabel");
            return this;
        }

        public Criteria andCostlabelLessThanOrEqualTo(String value) {
            addCriterion("costlabel <=", value, "costlabel");
            return this;
        }

        public Criteria andCostlabelLike(String value) {
            addCriterion("costlabel like", value, "costlabel");
            return this;
        }

        public Criteria andCostlabelNotLike(String value) {
            addCriterion("costlabel not like", value, "costlabel");
            return this;
        }

        public Criteria andCostlabelIn(List<String> values) {
            addCriterion("costlabel in", values, "costlabel");
            return this;
        }

        public Criteria andCostlabelNotIn(List<String> values) {
            addCriterion("costlabel not in", values, "costlabel");
            return this;
        }

        public Criteria andCostlabelBetween(String value1, String value2) {
            addCriterion("costlabel between", value1, value2, "costlabel");
            return this;
        }

        public Criteria andCostlabelNotBetween(String value1, String value2) {
            addCriterion("costlabel not between", value1, value2, "costlabel");
            return this;
        }

        public Criteria andCostvalueIsNull() {
            addCriterion("costvalue is null");
            return this;
        }

        public Criteria andCostvalueIsNotNull() {
            addCriterion("costvalue is not null");
            return this;
        }

        public Criteria andCostvalueEqualTo(String value) {
            addCriterion("costvalue =", value, "costvalue");
            return this;
        }

        public Criteria andCostvalueNotEqualTo(String value) {
            addCriterion("costvalue <>", value, "costvalue");
            return this;
        }

        public Criteria andCostvalueGreaterThan(String value) {
            addCriterion("costvalue >", value, "costvalue");
            return this;
        }

        public Criteria andCostvalueGreaterThanOrEqualTo(String value) {
            addCriterion("costvalue >=", value, "costvalue");
            return this;
        }

        public Criteria andCostvalueLessThan(String value) {
            addCriterion("costvalue <", value, "costvalue");
            return this;
        }

        public Criteria andCostvalueLessThanOrEqualTo(String value) {
            addCriterion("costvalue <=", value, "costvalue");
            return this;
        }

        public Criteria andCostvalueLike(String value) {
            addCriterion("costvalue like", value, "costvalue");
            return this;
        }

        public Criteria andCostvalueNotLike(String value) {
            addCriterion("costvalue not like", value, "costvalue");
            return this;
        }

        public Criteria andCostvalueIn(List<String> values) {
            addCriterion("costvalue in", values, "costvalue");
            return this;
        }

        public Criteria andCostvalueNotIn(List<String> values) {
            addCriterion("costvalue not in", values, "costvalue");
            return this;
        }

        public Criteria andCostvalueBetween(String value1, String value2) {
            addCriterion("costvalue between", value1, value2, "costvalue");
            return this;
        }

        public Criteria andCostvalueNotBetween(String value1, String value2) {
            addCriterion("costvalue not between", value1, value2, "costvalue");
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