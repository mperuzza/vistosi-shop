/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ateikon.internet.eprogen.domain.logic;

import com.ateikon.internet.eprogen.domain.pgmr.Archagen;
import com.ateikon.internet.eprogen.domain.pgmr.Archclie;
import com.ateikon.internet.eprogen.domain.pgmr.Archenti;
import com.ateikon.internet.eprogen.domain.pgmr.Ep_subutente;
import com.ateikon.internet.eprogen.domain.pgmr.Ep_utente;
import com.ateikon.internet.eprogen.domain.pgmr.Vist_filtro_articoli;
import java.util.List;

/**
 *
 * @author emiliano
 */
public interface ExternalLoginManager extends BaseManager{

    Ep_utente getUser(String username, String password, String cdazie, String cddipa);

    Archclie getArchlieByKey(String tkclie, String cdazie);

    Archenti getArchentiByKey(String cdente);

    List<Vist_filtro_articoli> getCdclas_aByCdlist(String cdlist);

    List<String> getCdclas_aByTkclie(Archclie clie);

    Archagen getArchagenByKey(String cdagen, String cdazie);

    Ep_subutente getSubutente(Long tksubutente);

}
