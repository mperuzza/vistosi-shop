/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ateikon.internet.eprogen.web.xmlview;

import com.ateikon.internet.eprogen.domain.pgmr.Articoli;
import com.ateikon.internet.eprogen.domain.pgmr.Cliente;
import com.ateikon.internet.eprogen.domain.pgmr.Collezioni;
import com.ateikon.internet.eprogen.domain.pgmr.Finiture;
import com.ateikon.internet.eprogen.domain.pgmr.Gruppo_tipologia;
import com.ateikon.internet.eprogen.domain.pgmr.Linee;
import com.ateikon.internet.eprogen.domain.pgmr.Listino_cliente;
import com.ateikon.internet.eprogen.domain.pgmr.Province;
import com.ateikon.internet.eprogen.domain.pgmr.Sottogruppo_tipologia;
import com.ateikon.internet.eprogen.domain.pgmr.Tipi_allegati;
import com.ateikon.internet.eprogen.domain.pgmr.Tipo_finitura;
import com.ateikon.internet.eprogen.domain.pgmr.Utenti;
import com.ateikon.internet.eprogen.domain.pgmr.Wishlist_posi;
import com.ateikon.internet.eprogen.domain.pgmr.Wishlist_test;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.View;
import com.thoughtworks.xstream.XStream;
import java.io.Writer;

/**
 *
 * @author emiliano
 */
public class GenericXMLView implements View{

    private Log log = LogFactory.getLog(this.getClass());

    public String getContentType() {
        return "text/xml; charset=ISO-8859-1";
    }

    public void render(Map model, HttpServletRequest request, HttpServletResponse response) throws Exception {


        XStream xstream = new XStream();
        xstream.alias("gruppo", Gruppo_tipologia.class);
        xstream.aliasField("cd", Gruppo_tipologia.class, "cdgruppo");
        xstream.aliasField("ds", Gruppo_tipologia.class, "dsgruppo");
        xstream.alias("tipoprodotto", Sottogruppo_tipologia.class);
        xstream.aliasField("cd", Sottogruppo_tipologia.class, "cdsottogruppo");
        xstream.aliasField("ds", Sottogruppo_tipologia.class, "dssottogruppo");
        xstream.alias("finitura", Tipo_finitura.class);
        xstream.aliasField("cd", Tipo_finitura.class, "cdtipofinitura");
        xstream.aliasField("ds", Tipo_finitura.class, "dstipofinitura");
        xstream.alias("articolo", Articoli.class);
        xstream.alias("finitura", Finiture.class);
        xstream.alias("collezione", Collezioni.class);
        xstream.alias("allegato", Tipi_allegati.class);
        xstream.alias("linea", Linee.class);
        xstream.alias("cliente", Cliente.class);
        xstream.alias("provincia", Province.class);
        xstream.alias("utenti", Utenti.class);
        xstream.alias("wishlist", Wishlist_test.class);
        xstream.alias("posi", Wishlist_posi.class);
        xstream.alias("prezzi", Listino_cliente.class);

        //xstream.aliasField("provincia.dsprovincia", Cliente.class, "dsprovincia");
        //xstream.aliasField("nazione.dsnazione", Cliente.class, "dsnazione");

        String xml = xstream.toXML(model.get("theList"));

        response.setContentType(getContentType());
        response.setCharacterEncoding("UTF-8");


        Writer writer = response.getWriter();
        writer.write(xml);
    }




}
