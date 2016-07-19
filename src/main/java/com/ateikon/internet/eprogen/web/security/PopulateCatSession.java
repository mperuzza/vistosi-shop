/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ateikon.internet.eprogen.web.security;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.Authentication;
import org.springframework.security.util.AuthorityUtils;
import org.springframework.web.util.CookieGenerator;
import org.springframework.web.util.WebUtils;

/**
 *
 * @author emiliano
 */
public class PopulateCatSession {


    public static void populate(HttpServletRequest request, HttpServletResponse response, Authentication authResult){
        
        ShopUser user = (ShopUser)authResult.getPrincipal();
        
        request.getSession().removeAttribute("tkordi");

        request.getSession().setAttribute("user_name", user.getUsername());
        request.getSession().setAttribute("cdazie", user.getUserDB().getCdazie());
        request.getSession().setAttribute("cddipa", user.getUserDB().getCddipa());
        if(user.getUserDB().getTkclie()!=null) {request.getSession().setAttribute("s_tkclie", user.getUserDB().getTkclie());}
        if(user.getUserDB().getTkutente()!=null) {request.getSession().setAttribute("s_tkutente", user.getUserDB().getTkutente());}
        if(user.getUserDB().getTkforn()!=null) {request.getSession().setAttribute("s_tkforn", user.getUserDB().getTkforn());}
        if(user.getUserDB().getCdagen()!=null) {request.getSession().setAttribute("s_cdagen", user.getUserDB().getCdagen());}
        //if(user.getUserDB().getCdagen()!=null) {request.getSession().setAttribute("s_cdlist", user.getUserDB().getCd());}


        //gestione cookie visualizzazione prezzi
        Cookie ckVp = WebUtils.getCookie(request, "view_net_price");

        if(ckVp == null || AuthorityUtils.userHasAuthority("ROLE_SUBUTENTE")){
            CookieGenerator ckGen = new CookieGenerator();

            ckGen.setCookieName("view_net_price");
            ckGen.setCookiePath("/");
            ckGen.setCookieMaxAge(1209600);

            ckGen.addCookie(response, "N");
        }

        if ((AuthorityUtils.userHasAuthority("ROLE_CLIE") || AuthorityUtils.userHasAuthority("ROLE_SUBUTENTE"))
                && (!AuthorityUtils.userHasAuthority("ROLE_FORN")
                && !AuthorityUtils.userHasAuthority("ROLE_CAPO")
                && !AuthorityUtils.userHasAuthority("ROLE_AGEN")
                && !AuthorityUtils.userHasAuthority("ROLE_ISPE")
                && !AuthorityUtils.userHasAuthority("ROLE_GEST"))) {
            user.setRealCustomer(true);
        }else{
            user.setRealCustomer(false);
        }        
        
    }

}
