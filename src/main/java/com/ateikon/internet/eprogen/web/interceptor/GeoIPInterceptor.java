package com.ateikon.internet.eprogen.web.interceptor;

import com.maxmind.geoip.Country;
import com.maxmind.geoip.LookupService;
import com.maxmind.geoip.timeZone;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.LocaleEditor;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.support.RequestContextUtils;

/**
 *
 * @author alessandro
 */
public class GeoIPInterceptor extends HandlerInterceptorAdapter {

    public static final String VISTOSI_NAZIONE_ATTR = GeoIPInterceptor.class.getName() + "-VISTOSI_NAZIONE";
    private String userAgentRegex = "^.*[bB]ot.*|.*Yahoo! Slurp.*|.*Feedfetcher-Google.*$";
    protected final transient Log log = LogFactory.getLog(getClass());
    private LookupService cl;

    private String defaultISOCountryCode = "IT";
    private boolean canFakeIP = true;
    private boolean block = true;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException {

        try {
            if (cl == null) {
                String datFilePath = request.getServletContext().getRealPath("/WEB-INF/geoip/GeoIP.dat");
                cl = new LookupService(new File(datFilePath), LookupService.GEOIP_MEMORY_CACHE);
            }

            String remoteAddr = request.getRemoteAddr();
            String xForwardedFor = request.getHeader("x-forwarded-for");
            String userAgent = request.getHeader("User-Agent");
            String serverName = request.getServerName();

            String fg_eur_usa = request.getParameter("fg_eur_usa");

            if (xForwardedFor != null && xForwardedFor.length() > 0) {
                String[] split = xForwardedFor.split(",");
                //remoteAddr = split[0];
                remoteAddr = split[split.length - 1];
                remoteAddr = remoteAddr.trim();
            }

            if ("127.0.0.1".equals(remoteAddr) || canFakeIP) {
                String fakeRemoteAddr = request.getParameter("remote_ip");
                if (fakeRemoteAddr != null) {
                    remoteAddr = fakeRemoteAddr;
                    request.getSession().removeAttribute(VISTOSI_NAZIONE_ATTR);
                }
            }

            boolean isRobot = userAgent.matches(userAgentRegex);

            Country country = cl.getCountry(remoteAddr);

            String sCountry = "IT";

            if (isRobot) {

//                if (StringUtils.endsWithIgnoreCase(serverName, ".it")) {
//                    sCountry = "IT";
//                } else if (StringUtils.endsWithIgnoreCase(serverName, ".de")) {
//                    sCountry = "DE";
//                } else if (StringUtils.endsWithIgnoreCase(serverName, ".fr")) {
//                    sCountry = "FR";
//                } else if (StringUtils.endsWithIgnoreCase(serverName, "usa.com")) {
//                    sCountry = "US";
//                } else {
//                    sCountry = "GB";
//                }
            }
            if (StringUtils.isNotEmpty(fg_eur_usa)) {

                if ("U".equals(fg_eur_usa)) {
                    sCountry = "US";
                } else if ("E".equals(fg_eur_usa)) {
                    sCountry = "IT";
                }
                request.getSession().removeAttribute(VISTOSI_NAZIONE_ATTR);

            } else {
                sCountry = country.getCode();

                if (StringUtils.isEmpty(sCountry)) {
                    sCountry = "IT";
                }
            }

//            String timezone = timeZone.timeZoneByCountryAndRegion(sCountry, "xx");
//
//            //RPI11.1212
//            Locale ipLocale = new Locale(sCountry);
//
//            //calendar.setTimeZone(TimeZone.getTimeZone(timezone));
//
//            Calendar calendar = Calendar.getInstance(ipLocale);
//
//            //System.out.println("> ore " + calendar.get(Calendar.HOUR_OF_DAY));             
//
//            //System.out.println(calendar.getTime() + "Country: " + sCountry);
//
//            Calendar startDate = Calendar.getInstance();
//
//            startDate.set(2011, 11, 13, 10, 8, 0);
//
//            if ("127.0.0.1".equals(remoteAddr)
//                    || "77.93.255.18".equals(remoteAddr)
//                    || "151.32.41.164".equals(remoteAddr)
//                    || "188.125.108.247".equals(remoteAddr)
//                    || "172.27.4.195".equals(remoteAddr)
//                    || "93.69.129.69".equals(remoteAddr)
//                    || "87.4.223.31".equals(remoteAddr)
//                    || "109.117.80.137".equals(remoteAddr)) {
//                //System.out.println(">>>> " + remoteAddr + " enabled");        
//            } else {
//                if (sCountry.toUpperCase().equals("IT") || sCountry.toUpperCase().equals("FR")) {
//                    if (block && calendar.before(startDate)) {
//                        response.sendError(HttpServletResponse.SC_NOT_FOUND); //SC_SERVICE_UNAVAILABLE);
//                        //System.out.println(">>>> " + remoteAddr + " disabled  ora Start: " + startDate.getTime());        
//                        return false;
//                    }
//                } else {
//                    startDate.set(2011, 11, 13, 1, 8, 0);
//
//                    if (sCountry.toUpperCase().equals("US")) {
//                        startDate.set(2011, 11, 13, 16, 8, 0);
//                    } else if (sCountry.toUpperCase().equals(Locale.CANADA.getCountry())) {
//                        startDate.set(2011, 11, 13, 16, 8, 0);
//                    } else if (sCountry.toUpperCase().equals("UK")) {
//                        startDate.set(2011, 11, 13, 11, 8, 0);
//                    }
//                    //System.out.println(">>>> " + remoteAddr + " ora Start: " + startDate.getTime());        
//
//                    if (block && calendar.before(startDate)) {
//                        response.sendError(HttpServletResponse.SC_NOT_FOUND); //SC_SERVICE_UNAVAILABLE);
//                        //System.out.println(">>>> " + remoteAddr + " disabled  ora Start: " + startDate.getTime());        
//                        return false;
//                    }
//                }
//            }
//            LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
            if (request.getSession().getAttribute(VISTOSI_NAZIONE_ATTR) == null) {

                request.getSession().setAttribute(VISTOSI_NAZIONE_ATTR, sCountry);

//
//                List<GessiNazione> found = jpqlJpaController.findEntities(GessiNazione.class, 1, 0, Filter.equal("countryIso", sCountry));
//                GessiNazione gessiNazione = null;
//                if (!Utils.isEmpty(found)) {
//                    gessiNazione = found.get(0);
//                    if (log.isDebugEnabled()) {
//                        log.debug(request.getRemoteAddr() + " is from " + country.getName());
//                    }
//                } else {
//                    gessiNazione = jpqlJpaController.findEntities(GessiNazione.class, 1, 0, Filter.equal("countryIso", defaultISOCountryCode)).get(0);
//                    if (log.isDebugEnabled()) {
//                        log.debug("can't get country for " + request.getRemoteAddr() + " let's suppose it's in " + gessiNazione.getNazioneEn());
//                    }
//                }
//
//                if (localeResolver == null) {
//                    throw new IllegalStateException("No LocaleResolver found: not in a DispatcherServlet request?");
//                }
//                LocaleEditor localeEditor = new LocaleEditor();
//                localeEditor.setAsText(gessiNazione.getLinguaIso());
//                localeResolver.setLocale(request, response, (Locale) localeEditor.getValue());
//
//                request.getSession().setAttribute(VISTOSI_NAZIONE_ATTR, gessiNazione);
            }
        } catch (IOException ioe) {
            throw new ServletException(ioe);
        }

        return true;
    }

    public static String getCountry(HttpServletRequest request) {

        return (String) request.getSession().getAttribute(VISTOSI_NAZIONE_ATTR);

    }

//    public static void setCountry(HttpServletRequest request, GessiNazione gessiNazione) {
//
//        request.getSession().setAttribute(VISTOSI_NAZIONE_ATTR, gessiNazione);
//
//    }
}
