/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ateikon.internet.eprogen.web.spring;

import com.ateikon.internet.eprogen.domain.logic.ExternalLoginManager;
import com.ateikon.internet.eprogen.domain.pgmr.Archagen;
import com.ateikon.internet.eprogen.domain.pgmr.Archclie;
import com.ateikon.internet.eprogen.domain.pgmr.Cliente;
import com.ateikon.internet.eprogen.domain.pgmr.Ep_subutente;
import com.ateikon.internet.eprogen.domain.pgmr.Ep_utente;
import com.ateikon.internet.eprogen.domain.pgmr.Vist_famiglia;
import com.ateikon.internet.eprogen.domain.pgmr.Vist_filtro_articoli;
import com.ateikon.internet.eprogen.domain.pgmr.Vist_tipi;
import com.ateikon.internet.eprogen.web.security.PopulateCatSession;
import com.ateikon.internet.eprogen.web.security.ShopUser;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.GrantedAuthority;
import org.springframework.security.GrantedAuthorityImpl;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.security.providers.UsernamePasswordAuthenticationToken;
import org.springframework.security.providers.anonymous.AnonymousAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriTemplate;
import org.springframework.web.util.WebUtils;

/**
 *
 * @author emiliano
 */
@Controller
public class ExternalLoginController {

    private Log log = LogFactory.getLog(this.getClass());

    @Autowired
    private ExternalLoginManager externalLoginManager;

    public ExternalLoginManager getExternalLoginManager() {
        return externalLoginManager;
    }

    public void setExternalLoginManager(ExternalLoginManager externalLoginManager) {
        this.externalLoginManager = externalLoginManager;
    }

    @RequestMapping("/session/logout.*")
    public String logoutSession( HttpServletRequest request, Model model){

        log.debug("call logout");

        return "redirect:/static/j_spring_security_logout";

    }

    @RequestMapping("/extlogin")
    public String accessFromEprogen(
            @RequestParam(value="username", required=true) String username,
            @RequestParam(value="password", required=true) String password,
            @RequestParam(value="cdazie", required=false) String cdazie,
            @RequestParam(value="cddipa", required=false) String cddipa,
            @RequestParam(value="nextPage", required=false, defaultValue="") String target,
            HttpServletRequest request, HttpServletResponse response, Model model){

//        log.debug("username: " + username);
//        log.debug("password: " + password);
//        log.debug("cdazie: " + cdazie);
//        log.debug("cddipa: " + cddipa);

        try{
            AnonymousAuthenticationToken auth = (AnonymousAuthenticationToken)SecurityContextHolder.getContext().getAuthentication();
            log.debug("--- utente anonimo: "+auth+"---");
        }catch(Exception e){
            //ho già l'utente in sessione controllo se è lo stesso
            log.debug("--- utente già logato da verificare");
            try{
                ShopUser user = (ShopUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                if(StringUtils.equalsIgnoreCase(username, user.getUsername())
                    && StringUtils.equalsIgnoreCase(cdazie, user.getUserDB().getCdazie())
                    && StringUtils.equalsIgnoreCase(cddipa, user.getUserDB().getCddipa())){

                    if(StringUtils.isNotBlank(target)){
                        return "redirect:/" + StringUtils.trimToEmpty(target);
                    }else{
                        //verifica se ho una ricerca in corso
                        String uri = "search/";
                        Map<String, String> uriVariables = new HashMap<String, String>();
                        //tipologia/SP/famiglia/DAMAS/
                        Vist_tipi tipologia = (Vist_tipi)WebUtils.getSessionAttribute(request, "tipologiaFilter");
                        if(tipologia!=null){
                            uri += "tipologia/{cdvisttp}/";
                            uriVariables.put("cdvisttp", tipologia.getCdvisttp());
                        }
                        Vist_famiglia famiglia = (Vist_famiglia)WebUtils.getSessionAttribute(request, "famigliaFilter");
                        if(famiglia!=null){
                            uri += "famiglia/{cdvistfam}/";
                            uriVariables.put("cdvistfam", famiglia.getCdvistfam());
                        }

                        UriTemplate template = new UriTemplate(uri);


                        return "redirect:/" + template.expand(uriVariables).toString();
                    }
                    
                }
            }catch(Exception ex){

            }
        }


        Ep_utente user = externalLoginManager.getUser(username, password, cdazie, cddipa);

        if(user!=null){

            //ShopUser epUser = new ShopUser(user.getUser_name().toUpperCase(), user.getPassword(), true, true, true, true, getAuthorities(user), user);
            ShopUser shopUser = new ShopUser(user.getUser_name().toUpperCase(), user.getPassword(), true, true, true, true, getAuthorities(user), user);

            //setup filter for "articoli" based on cdclas_a (codice startegico)
            if(StringUtils.isNotBlank(user.getTkclie())){
//                Archclie clie = new Archclie();
//                clie.setTkclie(user.getTkclie());
//                clie.setCdazie(user.getCdazie());
//                clie = archclieDAO.selectByPrimaryKey(clie);
                Archclie clie = externalLoginManager.getArchlieByKey(user.getTkclie(), user.getCdazie());

                Cliente cliente = new Cliente();
                cliente.setArchclie(clie);
                //cliente.setArchenti(archentiDAO.selectByPrimaryKey(clie.getCdente()));
                cliente.setArchenti(externalLoginManager.getArchentiByKey(clie.getCdente()));


                //shopUser.setCdclas_aFilter(vist_filtro_articoliDAO.getCdclas_aByTkclie(clie));
                shopUser.setCdclas_aFilter(externalLoginManager.getCdclas_aByTkclie(clie));

                if(StringUtils.equalsIgnoreCase("LUS", cliente.getArchclie().getCdlist())){
                    // se il cliente è listino America aggiungo il filtro per listino Europa
                    //shopUser.setCdclas_aFilterBase(shopUser.getCdclas_aFilter()); //salvo filtro base per ricerche articoli dei clienti america
                    //List<Vist_filtro_articoli> filtro_list = vist_filtro_articoliDAO.getCdclas_aByCdlist("L03");
                    List<Vist_filtro_articoli> filtro_list = externalLoginManager.getCdclas_aByCdlist("L03");

                    List<String> cdclas_aFilter = shopUser.getCdclas_aFilter();
                    for (Vist_filtro_articoli vist_filtro_articoli : filtro_list) {
                        cdclas_aFilter.add(vist_filtro_articoli.getCdclas_a());
                    }

                    shopUser.setIsSpecList(Boolean.TRUE);
                }else{
                    shopUser.setIsSpecList(Boolean.FALSE);
                }

                shopUser.setCliente(cliente);

            }


            //SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(epUser, epUser, epUser.getAuthorities()));
            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(shopUser, shopUser, shopUser.getAuthorities()));
            PopulateCatSession.populate(request, response, SecurityContextHolder.getContext().getAuthentication());
            
            return "redirect:/" + StringUtils.trimToEmpty(target);

        }else{
            return "redirect:/";
        }
        

    }


    private GrantedAuthority[] getAuthorities(Ep_utente user) {
            List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
            authList.add(new GrantedAuthorityImpl("ROLE_USER"));
            if (StringUtils.equals(user.getFgadmin(), "S")) {
                authList.add(new GrantedAuthorityImpl("ROLE_ADMIN"));
            }
            if (StringUtils.isNotBlank(user.getTkclie())){
                authList.add(new GrantedAuthorityImpl("ROLE_CLIE"));
            }
//            if (user.getTksubutente()!=null){
//                authList.add(new GrantedAuthorityImpl("ROLE_SUBUTENTE"));
//            }        
            if (user.getTksubutente()!=null){
                authList.add(new GrantedAuthorityImpl("ROLE_SUBUTENTE"));
                
                Ep_subutente subutente = externalLoginManager.getSubutente(user.getTksubutente());
                
                if(StringUtils.isNotBlank(subutente.getTkclie())){
                    user.setTkclie(subutente.getTkclie());
                    authList.add(new GrantedAuthorityImpl("ROLE_CLIE"));
                }
                
            }            
            if (StringUtils.isNotBlank(user.getTkforn())){
                authList.add(new GrantedAuthorityImpl("ROLE_FORN"));
            }
            if (StringUtils.isNotBlank(user.getCdagen())){

                Archagen age = externalLoginManager.getArchagenByKey(user.getCdagen(), user.getCdazie());

                if(StringUtils.equals(age.getCdagen(), age.getCdcapo())){
                    authList.add(new GrantedAuthorityImpl("ROLE_CAPO"));
                }else{
                    authList.add(new GrantedAuthorityImpl("ROLE_AGEN"));
                }
            }
            if (StringUtils.isNotBlank(user.getCdispe())){
                authList.add(new GrantedAuthorityImpl("ROLE_ISPE"));
            }
            if (StringUtils.isNotBlank(user.getCdutente())){
                authList.add(new GrantedAuthorityImpl("ROLE_GEST"));
            }
            return authList.toArray(new GrantedAuthority[] {});
    }

    private GrantedAuthority[] getAuthorities(boolean isAdmin) {
            List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>(2);
            authList.add(new GrantedAuthorityImpl("ROLE_USER"));
            if (isAdmin) {
                    authList.add(new GrantedAuthorityImpl("ROLE_ADMIN"));
            }
            return authList.toArray(new GrantedAuthority[] {});
    }

}
