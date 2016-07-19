/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ateikon.internet.eprogen.web.spring;

import com.ateikon.internet.eprogen.domain.pgmr.Archclie;
import com.ateikon.internet.eprogen.domain.logic.VistosiShopManager;
import com.ateikon.internet.eprogen.domain.pgmr.Cliente;
import com.ateikon.internet.eprogen.domain.pgmr.Vist_filtro_articoli;
import com.ateikon.internet.eprogen.web.security.ShopUser;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.security.Authentication;
import org.springframework.security.GrantedAuthorityImpl;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.security.providers.UsernamePasswordAuthenticationToken;
import org.springframework.security.util.AuthorityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.util.WebUtils;

/**
 *
 * @author emiliano
 */
@Controller
@RequestMapping("/setclie")
@SessionAttributes(types=Archclie.class)
public class SetClieFormController {

    private Log log = LogFactory.getLog(this.getClass());


    @Autowired
    private VistosiShopManager vistosiShopManager;

    public void setVistosiShopManager(VistosiShopManager vistosiShopManager) {
        this.vistosiShopManager = vistosiShopManager;
    }

    private void referenceData(HttpServletRequest request, Archclie obj, Model model){

        int page = ServletRequestUtils.getIntParameter(request, "page", 1);
        String sort = ServletRequestUtils.getStringParameter(request, "sort", "ragcog");
        String dir = ServletRequestUtils.getStringParameter(request, "dir", "asc");

        String ragcog = ServletRequestUtils.getStringParameter(request, "ragcogfilter", null);
        String ck_fnoord = ServletRequestUtils.getStringParameter(request, "ck_fnoord", "N");

        Map pars = new HashMap();
        if(StringUtils.isNotBlank(ragcog)){
            pars.put("ragcog", "%" + ragcog + "%");
        }

        if("N".equals(ck_fnoord)){
            pars.put("fnoord", ck_fnoord);
        }
        
        pars.put("fseleg", "S");
        
        int size = 100;

        if(AuthorityUtils.userHasAuthority("ROLE_AGEN")){
            //retrieve i clienti dell'agente per farglielo scegliere
            ShopUser user = (ShopUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            
            pars.put("cdazie", user.getUserDB().getCdazie());
            pars.put("cdagen", user.getUserDB().getCdagen());
            //model.addAttribute("clienti", vistosiShopManager.getClienteByCdagen(user.getUserDB().getCdagen(), user.getUserDB().getCdazie()));
            model.addAttribute("theList", vistosiShopManager.selectCliePag(pars, sort, dir, page, size));
        }
        if(AuthorityUtils.userHasAuthority("ROLE_CAPO")){
            //retrieve i clienti dell'agente capo area per farglielo scegliere
            ShopUser user = (ShopUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            pars.put("cdazie", user.getUserDB().getCdazie());
            pars.put("cdcapoarea", user.getUserDB().getCdagen());
            //model.addAttribute("clienti", vistosiShopManager.getClienteByCdagen(user.getUserDB().getCdagen(), user.getUserDB().getCdazie()));
            model.addAttribute("theList", vistosiShopManager.selectCliePag(pars, sort, dir, page, size));
        }
        if(AuthorityUtils.userHasAuthority("ROLE_ISPE")){
            //retrieve i clienti dell'agente per farglielo scegliere
            ShopUser user = (ShopUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            pars.put("cdazie", user.getUserDB().getCdazie());
            pars.put("cdispe", user.getUserDB().getCdispe());
            //model.addAttribute("clienti", vistosiShopManager.getClienteByCdagen(user.getUserDB().getCdagen(), user.getUserDB().getCdazie()));
            model.addAttribute("theList", vistosiShopManager.selectCliePag(pars, sort, dir, page, size));
        }
        if(AuthorityUtils.userHasAuthority("ROLE_GEST") || AuthorityUtils.userHasAuthority("ROLE_ADMIN")){
            //retrieve i clienti dell'agente per farglielo scegliere
            ShopUser user = (ShopUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            pars.put("cdazie", user.getUserDB().getCdazie());
            model.addAttribute("theList", vistosiShopManager.selectCliePag(pars, sort, dir, page, size));
        }

    }


    @RequestMapping(method=RequestMethod.GET)
    public String setupForm(HttpServletRequest request,
            @RequestParam(value = "tkclie",required=false) String tkclie,  Model model) {

        Archclie clie =  new Archclie();
        if(tkclie!=null) clie.setTkclie(tkclie);

        model.addAttribute("cliente", clie);

        referenceData(request, clie, model);

        return "setclieForm";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String processSubmit(HttpServletRequest request,
            @ModelAttribute("cliente") Archclie form,  BindingResult result,  Model model, SessionStatus status) {

        ShopUser user = (ShopUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        
        
        if(StringUtils.isBlank(form.getTkclie())){
            log.debug("cliente non valorizzato");
            result.rejectValue("tkclie", "errors.required", new Object[] {new DefaultMessageSourceResolvable(new String[] {"tkclie"})}  , "Cliente non valorizzato");
        }


        if(!result.hasErrors()){
            Cliente cliente = vistosiShopManager.getCliente(form.getTkclie(), user.getUserDB().getCdazie());
//            GrantedAuthority[] ap = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
//            log.debug("-pre-auth");
//            for (GrantedAuthority grantedAuthority : ap) {
//                log.debug(grantedAuthority.getAuthority());
//            }
//            GrantedAuthority[] au = user.getAuthorities();
//            log.debug("-pre-user");
//            for (GrantedAuthority grantedAuthority : au) {
//                log.debug(grantedAuthority.getAuthority());
//            }
            user.getUserDB().setTkclie(cliente.getArchclie().getTkclie());
            user.addAuthority(new GrantedAuthorityImpl("ROLE_CLIE"));

            user.setCdclas_aFilter(vistosiShopManager.getCdclas_aByTkclie(cliente.getArchclie()));

            log.debug("listino cliente: " +cliente.getArchclie().getCdlist());

            if(StringUtils.equalsIgnoreCase("LUS", cliente.getArchclie().getCdlist())){
                // se il cliente è listino America aggiungo il filtro per listino Europa
                //user.setCdclas_aFilterBase(user.getCdclas_aFilter()); //salvo filtro base per ricerche articoli dei clienti america
                List<Vist_filtro_articoli> filtro_list = vistosiShopManager.getCdclas_aByCdlist("L03");

                List<String> cdclas_aFilter = user.getCdclas_aFilter();
                for (Vist_filtro_articoli vist_filtro_articoli : filtro_list) {
                    cdclas_aFilter.add(vist_filtro_articoli.getCdclas_a());
                }

                user.setIsSpecList(Boolean.TRUE);
            }else{
                user.setIsSpecList(Boolean.FALSE);
            }


            user.setCliente(cliente);
//            GrantedAuthority[] pu = user.getAuthorities();
//            log.debug("-post-user");
//            for (GrantedAuthority grantedAuthority : pu) {
//                log.debug(grantedAuthority.getAuthority());
//            }

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(auth.getPrincipal(), auth.getCredentials(), user.getAuthorities()));

            //correzione cambio cliente per utenti con i permessi per farlo per il carrello old
            request.getSession().setAttribute("s_tkclie", user.getUserDB().getTkclie());
//            log.debug("-post-auth");
//            GrantedAuthority[] pp = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
//            for (GrantedAuthority grantedAuthority : pp) {
//                log.debug(grantedAuthority.getAuthority());
//            }
        }

        referenceData(request, form, model);

        return "setclieForm";
    }
}
