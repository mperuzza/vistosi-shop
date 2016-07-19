/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ateikon.internet.eprogen.web.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.Authentication;
import org.springframework.security.ui.rememberme.RememberMeProcessingFilter;

/**
 *
 * @author emiliano
 */
public class AtkRememberMeProcessingFilter extends RememberMeProcessingFilter{

    private Log log = LogFactory.getLog(this.getClass());

    @Override
    protected void onSuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, Authentication authResult) {
        super.onSuccessfulAuthentication(request, response, authResult);

        log.debug("test custom remember filter");
        PopulateCatSession.populate(request, response, authResult);
    }




}
