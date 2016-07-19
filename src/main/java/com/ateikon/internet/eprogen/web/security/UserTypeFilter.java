/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ateikon.internet.eprogen.web.security;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.security.ui.SpringSecurityFilter;

/**
 *
 * @author emiliano
 */
public class UserTypeFilter extends SpringSecurityFilter{

    private Log log = LogFactory.getLog(this.getClass());

    @Override
    protected void doFilterHttp(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.debug("passa per il filtro custom");
        chain.doFilter(request, response);
//        ShopUser user = (ShopUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//
//        if(user.getUserDB().getTkclie()!=null){
//            chain.doFilter(request, response);
//        }else{
//            logger.debug("redirect alla scelta cliente");
//            response.sendRedirect("selclie");
//        }
        

    }

    public int getOrder() {
        return SpringSecurityFilter.LOWEST_PRECEDENCE;
    }


}
