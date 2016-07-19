/*
 * AjaxInterceptor.java
 * 
 * Created on 13-lug-2007, 12.41.02
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ateikon.internet.eprogen.web.interceptor;

import com.ateikon.internet.eprogen.dao.pgmr.Atk_linguaDAO;
import com.ateikon.internet.eprogen.domain.pgmr.Atk_lingua;
import com.ateikon.internet.eprogen.domain.pgmr.Atk_linguaExample;
import java.util.List;
import java.util.Locale;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.support.RequestContext;
import org.springframework.web.util.WebUtils;

/**
 *
 * @author emi
 */
public class AtkLocaleChangeInterceptor extends LocaleChangeInterceptor {

    protected final transient Log log = LogFactory.getLog(getClass());
    //private String lang;

    public AtkLocaleChangeInterceptor() {
    }
    @Autowired
    private Atk_linguaDAO atk_linguaDAO;

    public void setAtk_linguaDAO(Atk_linguaDAO atk_linguaDAO) {
        this.atk_linguaDAO = atk_linguaDAO;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException {
        boolean sup = super.preHandle(request, response, handler);

        //log.debug("test interc");
        //System.out.println("ghghghggh");

        RequestContext rc = new RequestContext(request);

        Locale locale = rc.getLocale();

        String s_locale = locale.getLanguage();

        if ("it".equals(s_locale)) {
            WebUtils.setSessionAttribute(request, "atkLangSfx", "");
        } else if ("en".equals(s_locale)) {
            WebUtils.setSessionAttribute(request, "atkLangSfx", "_eng");
        } else if ("de".equals(s_locale)) {
            WebUtils.setSessionAttribute(request, "atkLangSfx", "_ted");
        } else if ("es".equals(s_locale)) {
            WebUtils.setSessionAttribute(request, "atkLangSfx", "_spa");
        } else if ("fr".equals(s_locale)) {
            WebUtils.setSessionAttribute(request, "atkLangSfx", "_fra");
        } else if ("ru".equals(s_locale)) {
            WebUtils.setSessionAttribute(request, "atkLangSfx", "_rus");
        } else {
            WebUtils.setSessionAttribute(request, "atkLangSfx", "");
        }

        WebUtils.setSessionAttribute(request, "cdling", getCdling_by_cdISO639(s_locale));

        return sup;

    }

    private String getCdling_by_cdISO639(String cdISO639) {

        Atk_linguaExample ex = new Atk_linguaExample();

        ex.createCriteria().andCdISO639EqualTo(cdISO639);

        List<Atk_lingua> itemList = atk_linguaDAO.selectByExample(ex);

        if (!itemList.isEmpty() && itemList.size() == 1) {
            return itemList.get(0).getCdling();
        } else {
            return null;
        }

    }
}
