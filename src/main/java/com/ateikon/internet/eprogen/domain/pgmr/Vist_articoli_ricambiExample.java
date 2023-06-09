package com.ateikon.internet.eprogen.domain.pgmr;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Vist_articoli_ricambiExample {
    /**
     * This field was generated by Apache iBATIS Ibator.
     * This field corresponds to the database table pgmr.vist_articoli_ricambi
     *
     * @ibatorgenerated Wed Oct 27 13:04:36 CEST 2010
     */
    protected String orderByClause;

    /**
     * This field was generated by Apache iBATIS Ibator.
     * This field corresponds to the database table pgmr.vist_articoli_ricambi
     *
     * @ibatorgenerated Wed Oct 27 13:04:36 CEST 2010
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.vist_articoli_ricambi
     *
     * @ibatorgenerated Wed Oct 27 13:04:36 CEST 2010
     */
    public Vist_articoli_ricambiExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.vist_articoli_ricambi
     *
     * @ibatorgenerated Wed Oct 27 13:04:36 CEST 2010
     */
    protected Vist_articoli_ricambiExample(Vist_articoli_ricambiExample example) {
        this.orderByClause = example.orderByClause;
        this.oredCriteria = example.oredCriteria;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.vist_articoli_ricambi
     *
     * @ibatorgenerated Wed Oct 27 13:04:36 CEST 2010
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.vist_articoli_ricambi
     *
     * @ibatorgenerated Wed Oct 27 13:04:36 CEST 2010
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.vist_articoli_ricambi
     *
     * @ibatorgenerated Wed Oct 27 13:04:36 CEST 2010
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.vist_articoli_ricambi
     *
     * @ibatorgenerated Wed Oct 27 13:04:36 CEST 2010
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.vist_articoli_ricambi
     *
     * @ibatorgenerated Wed Oct 27 13:04:36 CEST 2010
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
     * This method corresponds to the database table pgmr.vist_articoli_ricambi
     *
     * @ibatorgenerated Wed Oct 27 13:04:36 CEST 2010
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method corresponds to the database table pgmr.vist_articoli_ricambi
     *
     * @ibatorgenerated Wed Oct 27 13:04:36 CEST 2010
     */
    public void clear() {
        oredCriteria.clear();
    }

    /**
     * This class was generated by Apache iBATIS Ibator.
     * This class corresponds to the database table pgmr.vist_articoli_ricambi
     *
     * @ibatorgenerated Wed Oct 27 13:04:36 CEST 2010
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

        public Criteria andCdarti_ricIsNull() {
            addCriterion("cdarti_ric is null");
            return this;
        }

        public Criteria andCdarti_ricIsNotNull() {
            addCriterion("cdarti_ric is not null");
            return this;
        }

        public Criteria andCdarti_ricEqualTo(String value) {
            addCriterion("cdarti_ric =", value, "cdarti_ric");
            return this;
        }

        public Criteria andCdarti_ricNotEqualTo(String value) {
            addCriterion("cdarti_ric <>", value, "cdarti_ric");
            return this;
        }

        public Criteria andCdarti_ricGreaterThan(String value) {
            addCriterion("cdarti_ric >", value, "cdarti_ric");
            return this;
        }

        public Criteria andCdarti_ricGreaterThanOrEqualTo(String value) {
            addCriterion("cdarti_ric >=", value, "cdarti_ric");
            return this;
        }

        public Criteria andCdarti_ricLessThan(String value) {
            addCriterion("cdarti_ric <", value, "cdarti_ric");
            return this;
        }

        public Criteria andCdarti_ricLessThanOrEqualTo(String value) {
            addCriterion("cdarti_ric <=", value, "cdarti_ric");
            return this;
        }

        public Criteria andCdarti_ricLike(String value) {
            addCriterion("cdarti_ric like", value, "cdarti_ric");
            return this;
        }

        public Criteria andCdarti_ricNotLike(String value) {
            addCriterion("cdarti_ric not like", value, "cdarti_ric");
            return this;
        }

        public Criteria andCdarti_ricIn(List<String> values) {
            addCriterion("cdarti_ric in", values, "cdarti_ric");
            return this;
        }

        public Criteria andCdarti_ricNotIn(List<String> values) {
            addCriterion("cdarti_ric not in", values, "cdarti_ric");
            return this;
        }

        public Criteria andCdarti_ricBetween(String value1, String value2) {
            addCriterion("cdarti_ric between", value1, value2, "cdarti_ric");
            return this;
        }

        public Criteria andCdarti_ricNotBetween(String value1, String value2) {
            addCriterion("cdarti_ric not between", value1, value2, "cdarti_ric");
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

        public Criteria andImgv_filenameIsNull() {
            addCriterion("imgv_filename is null");
            return this;
        }

        public Criteria andImgv_filenameIsNotNull() {
            addCriterion("imgv_filename is not null");
            return this;
        }

        public Criteria andImgv_filenameEqualTo(String value) {
            addCriterion("imgv_filename =", value, "imgv_filename");
            return this;
        }

        public Criteria andImgv_filenameNotEqualTo(String value) {
            addCriterion("imgv_filename <>", value, "imgv_filename");
            return this;
        }

        public Criteria andImgv_filenameGreaterThan(String value) {
            addCriterion("imgv_filename >", value, "imgv_filename");
            return this;
        }

        public Criteria andImgv_filenameGreaterThanOrEqualTo(String value) {
            addCriterion("imgv_filename >=", value, "imgv_filename");
            return this;
        }

        public Criteria andImgv_filenameLessThan(String value) {
            addCriterion("imgv_filename <", value, "imgv_filename");
            return this;
        }

        public Criteria andImgv_filenameLessThanOrEqualTo(String value) {
            addCriterion("imgv_filename <=", value, "imgv_filename");
            return this;
        }

        public Criteria andImgv_filenameLike(String value) {
            addCriterion("imgv_filename like", value, "imgv_filename");
            return this;
        }

        public Criteria andImgv_filenameNotLike(String value) {
            addCriterion("imgv_filename not like", value, "imgv_filename");
            return this;
        }

        public Criteria andImgv_filenameIn(List<String> values) {
            addCriterion("imgv_filename in", values, "imgv_filename");
            return this;
        }

        public Criteria andImgv_filenameNotIn(List<String> values) {
            addCriterion("imgv_filename not in", values, "imgv_filename");
            return this;
        }

        public Criteria andImgv_filenameBetween(String value1, String value2) {
            addCriterion("imgv_filename between", value1, value2, "imgv_filename");
            return this;
        }

        public Criteria andImgv_filenameNotBetween(String value1, String value2) {
            addCriterion("imgv_filename not between", value1, value2, "imgv_filename");
            return this;
        }

        public Criteria andNumvIsNull() {
            addCriterion("numv is null");
            return this;
        }

        public Criteria andNumvIsNotNull() {
            addCriterion("numv is not null");
            return this;
        }

        public Criteria andNumvEqualTo(Long value) {
            addCriterion("numv =", value, "numv");
            return this;
        }

        public Criteria andNumvNotEqualTo(Long value) {
            addCriterion("numv <>", value, "numv");
            return this;
        }

        public Criteria andNumvGreaterThan(Long value) {
            addCriterion("numv >", value, "numv");
            return this;
        }

        public Criteria andNumvGreaterThanOrEqualTo(Long value) {
            addCriterion("numv >=", value, "numv");
            return this;
        }

        public Criteria andNumvLessThan(Long value) {
            addCriterion("numv <", value, "numv");
            return this;
        }

        public Criteria andNumvLessThanOrEqualTo(Long value) {
            addCriterion("numv <=", value, "numv");
            return this;
        }

        public Criteria andNumvIn(List<Long> values) {
            addCriterion("numv in", values, "numv");
            return this;
        }

        public Criteria andNumvNotIn(List<Long> values) {
            addCriterion("numv not in", values, "numv");
            return this;
        }

        public Criteria andNumvBetween(Long value1, Long value2) {
            addCriterion("numv between", value1, value2, "numv");
            return this;
        }

        public Criteria andNumvNotBetween(Long value1, Long value2) {
            addCriterion("numv not between", value1, value2, "numv");
            return this;
        }
    }
}