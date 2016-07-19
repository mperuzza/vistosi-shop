/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ateikon.internet.eprogen.web.security;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.security.providers.encoding.PasswordEncoder;

/**
 *
 * @author emiliano
 */
public class Ep_utentePasswordEncoder implements PasswordEncoder{

    private Log log = LogFactory.getLog(this.getClass());

    public String encodePassword(String password, Object salt) throws DataAccessException {

        String key = (String)salt;

        long finalKey = 0;
        for (int i=0; i<key.length(); i++)
        {
                long tempKey = key.charAt(i);
                tempKey *= 128;
                finalKey += tempKey;
        }

        java.util.Random generator = new java.util.Random(finalKey);
        String returnString = "";

        for (int i=0; i<password.length(); i++)
        {
                int temp = (int)password.charAt(i);
                temp += generator.nextInt(95);
                if (temp > 126)
                {
                        temp -= 95;
                }
                returnString += (char)temp;
        }

//        log.debug("decoded: " + returnString);
        return returnString;

    }

    public boolean isPasswordValid(String encPass, String rawPass, Object salt) throws DataAccessException {

        log.debug("encPass: " + encPass);
        try {
            log.debug(decodePassword(encPass, salt));
        } catch (Exception ex) {
            log.debug(ex.getMessage());
        }
        log.debug("rawPass: " +rawPass);
        log.debug(encodePassword(rawPass, salt));

        
        String rawPassEnc = encodePassword(rawPass.toUpperCase(), salt);

        return StringUtils.equals(encPass, rawPassEnc);


    }

    private String decodePassword(String password, Object salt) throws Exception{

        String key = (String)salt;

        long finalKey = 0;
        for (int i=0; i<key.length(); i++)
        {
                long tempKey = key.charAt(i);
                tempKey *= 128;
                finalKey += tempKey;
        }

        java.util.Random generator = new java.util.Random(finalKey);
        String returnString = "";
        for (int i=0; i<password.length(); i++)
        {
                int temp = (int)password.charAt(i);
                temp -= generator.nextInt(95);
                if (temp < 36)
                {
                        temp+= 95;
                }
                else if (temp > 126)
                {
                        temp -= 95;
                }
                returnString += (char)temp;
        }

        return returnString;

    }

}
