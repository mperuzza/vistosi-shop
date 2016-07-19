/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ateikon.internet.eprogen.web.xmlview;

import com.ateikon.internet.eprogen.domain.pgmr.Wishlist;
import com.ateikon.internet.eprogen.domain.pgmr.Wishlist_posi;
import com.ateikon.internet.eprogen.domain.pgmr.Wishlist_test;
import java.io.Writer;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.web.servlet.View;

/**
 *
 * @author emiliano
 */
public class WishlistXMLView implements View{

    private Log log = LogFactory.getLog(this.getClass());

    public String getContentType() {
        return "text/xml; charset=ISO-8859-1";
    }

    public void render(Map model, HttpServletRequest request, HttpServletResponse response) throws Exception {

        Document doc = DocumentHelper.createDocument();
        Element root = doc.addElement("root");
        doc.setRootElement(root);

        Wishlist ws = (Wishlist)model.get("wishlist");

        Wishlist_test t = ws.getTest();
        Element test = root.addElement("wishlist");
        if(t!=null){
            test.addElement("id").addText(t.getId().toString());
            if(t.getCdcliente()!=null){
                test.addElement("cdcliente").addText(t.getCdcliente());
            }
            test.addElement("cdstato").addText(t.getCdstato());
            test.addElement("cdvaluta").addText(t.getCdvaluta()!=null?t.getCdvaluta().toString():"");
            test.addElement("cdlistino").addText(t.getCdlistino()!=null?t.getCdlistino().toString():"");
            test.addElement("dslist").addText(t.getDslist()!=null?t.getDslist():"");
            test.addElement("tkutente").addText(t.getTkutente()!=null?t.getTkutente()+"":"");
            test.addElement("tipo").addText(t.getTipo()!=null?t.getTipo():"");
            test.addElement("cdcliente").addText(t.getCdcliente()!=null?t.getCdcliente():"");

            List<Wishlist_posi> posis = ws.getPosis();
            for (Wishlist_posi wishlist_posi : posis) {

                Element posi = test.addElement("posi");

                posi.addElement("id").addText(wishlist_posi.getId().toString());
                posi.addElement("idtest").addText(wishlist_posi.getIdtest().toString());
                posi.addElement("tkutente").addText(wishlist_posi.getTkutente().toString());
                posi.addElement("tipo").addText(wishlist_posi.getTipo().toString());
                posi.addElement("cdarticolo").addText(wishlist_posi.getCdarticolo());
                posi.addElement("dsarticolo").addText(wishlist_posi.getDsarticolo()!=null?wishlist_posi.getDsarticolo():"");
                posi.addElement("cdfinitura").addText(wishlist_posi.getCdfinitura());
                posi.addElement("dsfinitura").addText(wishlist_posi.getDsfinitura()!=null?wishlist_posi.getDsfinitura():"");
                posi.addElement("cdcollezione").addText(wishlist_posi.getCdcollezione()!=null?wishlist_posi.getCdcollezione().toString():"");
                posi.addElement("cdlinea").addText(wishlist_posi.getCdlinea()!=null?wishlist_posi.getCdlinea().toString():"");
                posi.addElement("prezzolistino").addText(wishlist_posi.getPrezzolistino()!=null?wishlist_posi.getPrezzolistino().toString():"0");
                posi.addElement("prezzocliente").addText(wishlist_posi.getPrezzocliente()!=null?wishlist_posi.getPrezzocliente().toString():"0");
                posi.addElement("sconto1").addText(wishlist_posi.getSconto1()!=null?wishlist_posi.getSconto1().toString():"0");
                posi.addElement("sconto2").addText(wishlist_posi.getSconto2()!=null?wishlist_posi.getSconto2().toString():"0");
                posi.addElement("sconto3").addText(wishlist_posi.getSconto3()!=null?wishlist_posi.getSconto3().toString():"0");
                posi.addElement("spese").addText(wishlist_posi.getSpese()!=null?wishlist_posi.getSpese().toString():"0");
                posi.addElement("tasse").addText(wishlist_posi.getTasse()!=null?wishlist_posi.getTasse().toString():"0");
                posi.addElement("qta").addText(wishlist_posi.getQta()!=null?wishlist_posi.getQta().toString():"0");

            }
        }
        response.setContentType(getContentType());
        response.setCharacterEncoding("UTF-8");

        Writer writer = response.getWriter();
        writer.write(doc.asXML());
    }




}
