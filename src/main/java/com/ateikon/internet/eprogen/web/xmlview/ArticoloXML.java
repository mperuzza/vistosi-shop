/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ateikon.internet.eprogen.web.xmlview;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.xstream.XStreamMarshaller;
import org.springframework.web.servlet.view.xml.MarshallingView;

/**
 *
 * @author emiliano
 */
public class ArticoloXML extends MarshallingView{

    private Log log = LogFactory.getLog(this.getClass());

    public ArticoloXML() {
        setMarshaller(new XStreamMarshaller());
        setModelKey("scheda");
    }


    @Override
    protected void renderMergedOutputModel(Map model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.debug("mashalling view");
        super.renderMergedOutputModel(model, request, response);
    }






}
