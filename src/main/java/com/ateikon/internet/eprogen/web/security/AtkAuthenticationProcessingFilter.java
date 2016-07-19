/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ateikon.internet.eprogen.web.security;

import com.ateikon.internet.eprogen.dao.pgmr.Ep_logDAO;
import com.ateikon.internet.eprogen.dao.pgmr.Ep_log_tipoDAO;
import com.ateikon.internet.eprogen.domain.pgmr.Ep_log;
import com.ateikon.internet.eprogen.domain.pgmr.Ep_log_tipo;
import com.ateikon.internet.eprogen.domain.pgmr.Ep_log_tipoExample;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.Authentication;
import org.springframework.security.ui.webapp.AuthenticationProcessingFilter;

/**
 *
 * @author emiliano
 */
public class AtkAuthenticationProcessingFilter extends AuthenticationProcessingFilter {

    private Log log = LogFactory.getLog(this.getClass());
    @Autowired
    private Ep_logDAO ep_logDAO;

    public void setEp_logDAO(Ep_logDAO ep_logDAO) {
        this.ep_logDAO = ep_logDAO;
    }
    @Autowired
    private Ep_log_tipoDAO ep_log_tipoDAO;

    public void setEp_log_tipoDAO(Ep_log_tipoDAO ep_log_tipoDAO) {
        this.ep_log_tipoDAO = ep_log_tipoDAO;
    }

    @Override
    protected void onSuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, Authentication authResult) throws IOException {
        super.onSuccessfulAuthentication(request, response, authResult);
        log.debug("test custom auth filter");

        ShopUser user = (ShopUser) authResult.getPrincipal();
        log.debug(user.getUserDB());
        PopulateCatSession.populate(request, response, authResult);

        //log accesso su ep_log
        Ep_log epLog = new Ep_log();
        epLog.setApplicazione("SHOP");
        epLog.setTkutente(user.getUserDB().getTkutente());
        logAccess("LOGIN", epLog);

    }
    
    
    public void logAccess(String cdeplog_tipo_m, Ep_log ep_log){

        Ep_log_tipoExample extipo =  new Ep_log_tipoExample();
        extipo.createCriteria().andCdeplog_tipo_mEqualTo(cdeplog_tipo_m);

        List<Ep_log_tipo> tipoList = ep_log_tipoDAO.selectByExample(extipo);

        if(!tipoList.isEmpty() && tipoList.size() == 1){
            Ep_log_tipo eplt = tipoList.get(0);

            ep_log.setTkeplog_tipo(eplt.getTkeplog_tipo());

            Calendar rightNow = GregorianCalendar.getInstance();

            ep_log.setDtlog(rightNow.getTime());
            ep_log.setDtlogin(rightNow.getTime());
            ep_log.setAnno((short)rightNow.get(Calendar.YEAR));
            ep_log.setMese((short)(rightNow.get(Calendar.MONTH) + 1));
            ep_log.setGiorno((short)rightNow.get(Calendar.DAY_OF_MONTH));
            ep_log.setOra((short)rightNow.get(Calendar.HOUR_OF_DAY));

            ep_logDAO.insert(ep_log);

        }



    }      
}
