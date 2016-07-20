/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ateikon.internet.eprogen.web.spring;

import com.ateikon.internet.eprogen.domain.Vist_vetro;
import com.ateikon.internet.eprogen.domain.logic.VistosiShopManager;
import com.ateikon.internet.eprogen.domain.pgmr.DesignerWithBLOBs;
import com.ateikon.internet.eprogen.domain.pgmr.Mrp_arch_articoli;
import com.ateikon.internet.eprogen.domain.pgmr.Mrp_arch_stato;
import com.ateikon.internet.eprogen.domain.pgmr.Vist_articoli_datiextra;
import com.ateikon.internet.eprogen.domain.pgmr.Vist_colori_vetro;
import com.ateikon.internet.eprogen.domain.pgmr.Vist_elettrificazioni;
import com.ateikon.internet.eprogen.domain.pgmr.Vist_famiglia;
import com.ateikon.internet.eprogen.domain.pgmr.Vist_finit_mont;
import com.ateikon.internet.eprogen.domain.pgmr.Vist_finit_vetro;
import com.ateikon.internet.eprogen.web.interceptor.GeoIPInterceptor;
import com.ateikon.internet.eprogen.web.security.ShopUser;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfAction;
import com.itextpdf.text.pdf.PdfAnnotation;
import com.itextpdf.text.pdf.PdfAppearance;
import com.itextpdf.text.pdf.PdfArray;
import com.itextpdf.text.pdf.PdfDictionary;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfIndirectObject;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfNumber;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStream;
import com.itextpdf.text.pdf.PdfString;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.security.util.AuthorityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.support.RequestContext;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.util.WebUtils;

/**
 *
 * @author rpin
 */
@Controller
public class SpecSheetGenerica {

    @Autowired
    //private JdbcTemplate jdbcTemplate;
    private VistosiShopManager vistosiShopManager;

    public void setVistosiShopManager(VistosiShopManager vistosiShopManager) {
        this.vistosiShopManager = vistosiShopManager;
    }
    @Autowired
    protected MessageSource messageSource;
    String siteRoot = "";
    public static final String ROOT_RES = "/images/articoli/specsheetres/";
    BaseFont baseFont = null;
    BaseFont baseFontBold = null;
    Font fontListino = null;
    Font fontLogo = null;
    Font fontHeader = null;
    Font fontTitoloNota = null;
    Font fontNota = null;
    Font fontCategoria = null;
    java.text.NumberFormat numFormat = null;
    java.text.NumberFormat przFormat = null;
    float widthBody = 380f; 
    float widthRight = 180f;
    //int widthPage = 560;

    @RequestMapping(value = "/specsheetgenerica/{cdvistfam}/{cdvisttp}")
    public void createPDF(@PathVariable("cdvistfam") String cdvistfam, @PathVariable("cdvisttp") String cdvisttp, HttpServletRequest request, HttpServletResponse response) throws Exception {

        siteRoot = WebUtils.getRealPath(request.getSession().getServletContext(), "/");

        //String cdartm = (String) request.getParameter("cdartm");
        baseFont = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.WINANSI, BaseFont.EMBEDDED);
        baseFontBold = BaseFont.createFont(BaseFont.HELVETICA_BOLD, BaseFont.WINANSI, BaseFont.EMBEDDED);

        fontListino = new Font(baseFont, 6);
        fontLogo = new Font(baseFontBold, 8);
        fontHeader = new Font(baseFont, 7);
        fontTitoloNota = new Font(baseFontBold, 8);
        fontNota = new Font(baseFont, 7);
        fontCategoria = new Font(baseFont, 14);

        numFormat = java.text.NumberFormat.getInstance(java.util.Locale.ITALY);
        przFormat = java.text.NumberFormat.getInstance(java.util.Locale.ITALY);

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "inline;filename=\"" + cdvisttp + cdvistfam + ".pdf\"");

        Document document = new Document();
        document.setPageSize(PageSize.A4);
        document.setMargins(24, 24, 24, 24);
        document.setMarginMirroring(true);

        document.left(0f);

        document.getPageSize();
        numFormat.setGroupingUsed(true);
        numFormat.setMaximumFractionDigits(2);
        numFormat.setMinimumFractionDigits(0);

        przFormat.setGroupingUsed(true);
        przFormat.setMaximumFractionDigits(2);
        przFormat.setMinimumFractionDigits(2);

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        try {

            PdfWriter writer = PdfWriter.getInstance(document, response.getOutputStream());
            document.open();

            String testo = "";
            Vist_famiglia famiglia = vistosiShopManager.getVist_famigliaByKey(cdvistfam);

            Map pars = new HashMap();
            ShopUser user = null;
            if (!AuthorityUtils.userHasAuthority("ROLE_ANONYMOUS")) {
                user = (ShopUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            }

            vistosiShopManager.addCdclasFilter(pars, request);
            pars.put("fgweb", "S");
            pars.put("cdvisttp", cdvisttp);
            pars.put("cdvistfam", cdvistfam);
            pars.put("cdvistv1", ServletRequestUtils.getStringParameter(request, "cdvistv1"));
            pars.put("cdvistv2", ServletRequestUtils.getStringParameter(request, "cdvistv2"));
            pars.put("cdvistv3", ServletRequestUtils.getStringParameter(request, "cdvistv3"));

            //recupero articoli
//            if (((!AuthorityUtils.userHasAuthority("ROLE_ANONYMOUS") && user != null && user.getIsSpecList())
//                    || (AuthorityUtils.userHasAuthority("ROLE_ANONYMOUS") && GeoIPInterceptor.getCountry(request).equals("US")))
//                    && StringUtils.isNotBlank(cdvistelet)) {
//                Vist_elettrificazioni eletSpec = vistosiShopManager.getVist_elettrificazioniByKey(cdvistelet);
//
//                Map ulPars = new HashMap();
//                ulPars.putAll(pars);
//
//                pars.put("cdvistelet", eletSpec.getCdul());
//
//                pars.put("cdclas_aList", (user != null) ? user.getCdclas_aFilterBase() : vistosiShopManager.DEFAULT_CDCLAS_A_US);
//
//                List<Mrp_arch_articoli> arts = vistosiShopManager.selectMrp_arch_articoliByPars(pars);
//
//                if (!arts.isEmpty()) {
//                    scheda.setArticoli(arts);                    
//                    
//                } else {
//                    log.debug("per filtro UL articolo non trovato, vengono aggiunti i filtri listino europa");
//                    Vist_elettrificazioni altElet = vistosiShopManager.getVist_elettrificazioniAlternative(cdvistelet);
//                    pars.put("cdvistelet", (altElet != null) ? altElet.getCdvistelet() : cdvistelet);
//
//                    pars.put("cdclas_aList", (user != null) ? user.getCdclas_aFilter() : vistosiShopManager.DEFAULT_CDCLAS_A_EUUS);
//
//                    arts = vistosiShopManager.selectMrp_arch_articoliByPars(pars);
//                    scheda.setArticoli(arts);
//                    if (arts.size() == 1) {
//                        scheda.setArticolo(arts.get(0));
//                        log.debug("get etichette");
//                        //scheda.setEtichette(vistosiShopManager.getEtichette(scheda.getArticolo().getCdarti()));
//                        log.debug("get ricambi");
//                        scheda.setRicambi(vistosiShopManager.getRicambi(scheda.getArticolo().getCdarti()));
//                        //scheda.setRicambio(vistosiShopManager.getMrp_arch_articoliByKey(cdartiric));
//                        if (StringUtils.isNotEmpty(cdartiric)) {
//                            scheda.setRicambio(vistosiShopManager.getRicambio(scheda.getArticolo().getCdarti(), cdartiric));
//                        }
//                    }
//                }
//
//            } else {
            List<Mrp_arch_articoli> arts = vistosiShopManager.selectMrp_arch_articoliByPars(pars);

            Mrp_arch_articoli art = arts.get(0);

            List<Vist_elettrificazioni> vist_elettrificazioni = vistosiShopManager.findVist_elettrificazioni(pars);
            List<Vist_vetro> vist_vetro = vistosiShopManager.findVist_vetro(pars);
            List<Vist_finit_mont> vist_finit_mont = vistosiShopManager.findVist_finit_mont(pars);

            String cdvistelet = findPreferred(art, vist_elettrificazioni, request, true);

            pars.put("cdvistelet", cdvistelet);

            arts = vistosiShopManager.selectMrp_arch_articoliByPars(pars);

            art = arts.get(0);

//            }
//            Mrp_arch_articoli art = vistosiShopManager.getArticoloByCdartm(cdartm);
//            Vist_articoli_datiextra datiExtra = vistosiShopManager.getDatiExtraByCdartm(art.getCdartm());
            PdfPTable tableContainer = null;
            PdfPTable tableBody = null;
            PdfPTable tableRight = null;
            PdfPCell cell = null;
            Paragraph paragraph = null;

            float[] columnWidth = {380, 180};
            tableContainer = new PdfPTable(2);
            tableContainer.setTotalWidth(columnWidth);
            tableContainer.setLockedWidth(true);
            tableContainer.getDefaultCell().setBorder(PdfPCell.RECTANGLE);
            tableContainer.getDefaultCell().setBorderWidth(0f);
            tableContainer.getDefaultCell().setPadding(.0f);

            tableBody = new PdfPTable(1);
            tableBody.setWidthPercentage(100);
//            tableBody.getDefaultCell().setBorder(PdfPCell.RECTANGLE);
            tableBody.getDefaultCell().setBorderWidth(1f);
            tableBody.getDefaultCell().setPadding(0f);
            tableBody.getDefaultCell().setPaddingBottom(3);

            PdfPTable tableHeader = getHeader(famiglia, cdvisttp, request, document, writer);
            tableBody.addCell(tableHeader);
            tableBody.getDefaultCell().setBorderWidth(0.2f);
            tableBody.getDefaultCell().setPaddingBottom(0);
            PdfPTable tableDisegno = getDisegno(art, request, document, writer);
            tableBody.addCell(tableDisegno);

            PdfPTable tableBodycentral = new PdfPTable(2);
            float[] columnWidthTableBodycentral = {widthBody * 0.45f, widthBody * 0.55f};
            tableBodycentral.setTotalWidth(columnWidthTableBodycentral);

            PdfPTable finiture = new PdfPTable(1);
            finiture.setWidthPercentage(100);
            finiture.addCell(getFinituraDiffusore(art, vist_vetro, request, document, writer));

            if (!vist_finit_mont.isEmpty() && vist_finit_mont.get(0).getCdvistfinm() != null) {
                finiture.addCell(getFinituraMontatura(art, vist_finit_mont, request, document, writer));
            }
            tableBodycentral.addCell(finiture);
            tableBodycentral.addCell(new PdfPCell(new Paragraph("pippo", new Font(baseFontBold, 10))));

            tableBody.addCell(tableBodycentral);

            tableRight = new PdfPTable(1);
            tableRight.setWidthPercentage(100);
            tableRight.getDefaultCell().setBorder(PdfPCell.RECTANGLE);
            tableRight.getDefaultCell().setBorderColor(BaseColor.GREEN);
            tableRight.getDefaultCell().setBorderWidth(1f);
            tableRight.getDefaultCell().setPaddingTop(0);
            tableRight.getDefaultCell().setPaddingRight(0);
            tableRight.getDefaultCell().setPaddingBottom(4);
            tableRight.getDefaultCell().setPaddingLeft(10);
            tableRight.setExtendLastRow(false);

            PdfPTable tableCertificazioni = getCertificazioni(request, document, writer);
            PdfPTable tableNote = getNote(art, request, document, writer);
//            PdfPTable table3D = get3D(art, request, document, writer);

//            tableBody.getDefaultCell().setPaddingBottom(3);
            tableRight.addCell(tableCertificazioni);
//            tableRight.addCell(new PdfPCell(new Paragraph("pippo", new Font(baseFontBold, 10))));
            tableRight.getDefaultCell().setBorderWidth(0f);
            tableRight.addCell(tableNote);
            tableRight.addCell(getDisegnoDim(art, request, document, writer));
            tableRight.addCell(getQR(art, request, document, writer));
//            tableRight.addCell(table3D);
//            tableRight.addCell(getDimensioni(art, request, document, writer));
//            if (datiExtra != null) {
//                tableRight.addCell(getMarcature(art, datiExtra, request, document, writer));
//                tableRight.addCell(getLampadine(art, datiExtra, request, document, writer));
//            }

            tableRight.getDefaultCell().setPaddingBottom(0);

            tableContainer.addCell(tableBody);
            tableContainer.addCell(tableRight);

            tableContainer.completeRow();

            document.add(tableContainer);

            //  PdfAction action = PdfAction.javaScript(Utilities.readFileToString(rootImg + "js\\eprogen3d.js"), writer);
            // writer.setOpenAction(action);
            document.close();

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }

//    @RequestMapping(value = "/specsheet/**")
//    public void createPDF(HttpServletRequest request, HttpServletResponse response) throws Exception {
//
//        String pathInfo = request.getPathInfo();
//
//        createPDF(StringUtils.substringAfter(pathInfo, "/specsheet/"), request, response);
//    }
    public PdfPTable getHeader(Vist_famiglia vist_famiglia, String cdvisttp, HttpServletRequest request, Document document, PdfWriter writer) throws DocumentException, IOException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {

        WebApplicationContext ctx = RequestContextUtils.getWebApplicationContext(request);
        RequestContext rc = new RequestContext(request);

        PdfPTable tableHeader = null;
        PdfPTable tableLogo = null;
        PdfPTable tableRiepilogo = null;
        PdfPCell cell = null;
        Paragraph paragraph = null;

        float[] columnWidth = {220, 600};
        float[] columnWidthLogo = {200};
        float[] columnWidthRiepilogo = {200, 200, 200};

        tableHeader = new PdfPTable(2);
        tableHeader.setTotalWidth(columnWidth);
        tableHeader.getDefaultCell().setBorder(PdfPCell.RECTANGLE);
        tableHeader.getDefaultCell().setBorderWidth(0f);
        tableHeader.getDefaultCell().setPadding(0f);
        tableHeader.getDefaultCell().setPaddingBottom(10f);

        tableLogo = new PdfPTable(1);
        tableLogo.setTotalWidth(columnWidthLogo);
//        tableLogo.getDefaultCell().setBorder(PdfPCell.RECTANGLE);
        tableLogo.getDefaultCell().setBorderWidth(0f);
        tableLogo.getDefaultCell().setPadding(0f);
        tableLogo.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

        Image img = null;

        String realPath = WebUtils.getRealPath(ctx.getServletContext(), ROOT_RES + "logo_generica.png");
        img = Image.getInstance(realPath);
        img.setAlignment(Image.TEXTWRAP | Image.ALIGN_CENTER);

        cell = new PdfPCell(img, true);
        //cell.setFixedHeight(70);
        cell.setBorderWidth(0f);
        cell.setPadding(0);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        tableLogo.addCell(cell);

        tableHeader.addCell(tableLogo);

        PdfPTable tableDati = new PdfPTable(3);
        tableDati.setWidthPercentage(100);
        tableDati.setWidths(new int[]{1, 1, 1});
        PdfPCell defaultCell = tableDati.getDefaultCell();
        defaultCell.setPadding(0f);
        defaultCell.setBorderWidth(0);
        defaultCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        defaultCell.setUseAscender(false);
        defaultCell.setUseDescender(true);

        //descrizione famiglia
        paragraph = new Paragraph(StringUtils.trimToEmpty(BeanUtils.getSimpleProperty(vist_famiglia, "dsvistfam" + getAtkLangsfx(rc.getLocale().getLanguage()))), new Font(baseFontBold, 14));
        defaultCell.setPaddingLeft(20f);
        tableDati.addCell(paragraph);

        //tipologia
        paragraph = new Paragraph(cdvisttp, fontCategoria);
        defaultCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        defaultCell.setBorderWidthRight(.2f);
        defaultCell.setPaddingLeft(0);
        defaultCell.setPaddingRight(5);
        tableDati.addCell(paragraph);

        paragraph = new Paragraph(cdvisttp + vist_famiglia.getCdvistfam_m(), new Font(baseFont, 9));
        defaultCell.setBorderWidthRight(0);
        defaultCell.setPaddingRight(0);
        tableDati.addCell(paragraph);

        tableHeader.addCell(tableDati);

//        tableRiepilogo.addCell(cell);
//
//        cell = new PdfPCell();
//        cell.setBorderWidth(.2f);
//        String description = "";
//        if (art.getCddesigner() != null) {
//            DesignerWithBLOBs designer = vistosiShopManager.getDesigner(art.getCddesigner());
//            if (designer != null && StringUtils.isNotBlank(designer.getDsdesigner())) {
//                description += "Design " + designer.getDsdesigner().replaceAll("\\s+", " ") + "\n";
//            }
//        }
//        description += StringUtils.trimToEmpty(BeanUtils.getSimpleProperty(vist_famiglia, "dsextvistfam" + getAtkLangsfx(rc.getLocale().getLanguage())));
//        paragraph = new Paragraph(description, new Font(baseFont, 8));
//        cell.addElement(paragraph);
//        tableRiepilogo.addCell(cell);
//
//        tableHeader.addCell(tableRiepilogo);
        //tableHeader.completeRow();
        return tableHeader;

    }

    public PdfPTable getCertificazioni(HttpServletRequest request, Document document, PdfWriter writer) throws DocumentException, IOException {

        WebApplicationContext ctx = RequestContextUtils.getWebApplicationContext(request);

        PdfPTable tableRight = null;
        PdfPCell cell = null;
        Paragraph paragraph = null;

        tableRight = new PdfPTable(1);
        tableRight.setWidthPercentage(100);
        tableRight.getDefaultCell().setBorder(PdfPCell.RECTANGLE);
        tableRight.getDefaultCell().setBorderWidth(1f);
        tableRight.getDefaultCell().setBackgroundColor(BaseColor.YELLOW);

        Image img = null;

        String realPath = WebUtils.getRealPath(ctx.getServletContext(), ROOT_RES + "csq-iqnet.jpg");
        img = Image.getInstance(realPath);
        img.setAlignment(Image.TEXTWRAP | Image.ALIGN_LEFT);
        //img.scalePercent(5);

        cell = new PdfPCell(img, true);
        cell.setFixedHeight(25f);
        cell.setBorderWidth(0f);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        tableRight.addCell(cell);

        tableRight.completeRow();

        return tableRight;

    }

    public PdfPTable getQR(Mrp_arch_articoli art, HttpServletRequest request, Document document, PdfWriter writer) throws DocumentException, IOException {

        WebApplicationContext ctx = RequestContextUtils.getWebApplicationContext(request);

        PdfPTable table = null;
        PdfPCell cell = null;
        Paragraph paragraph = null;

        table = new PdfPTable(2);
        table.setWidthPercentage(100);
        table.setWidths(new int[]{1, 2});
        table.getDefaultCell().setBorder(PdfPCell.RECTANGLE);
        table.getDefaultCell().setBorderWidth(0f);
        table.getDefaultCell().setBackgroundColor(BaseColor.YELLOW);

        Image img = null;

        String realPath = WebUtils.getRealPath(ctx.getServletContext(), ROOT_RES + "csq-iqnet.jpg");
        img = Image.getInstance(realPath);
        img.setAlignment(Image.TEXTWRAP | Image.ALIGN_LEFT);
        //img.scalePercent(5);

        cell = new PdfPCell(img, true);
        cell.setFixedHeight(25f);
        cell.setBorderWidth(0f);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);

        PdfPTable text = new PdfPTable(1);
        text.getDefaultCell().setBorderWidth(0f);

        paragraph = new Paragraph("QRCODE", new Font(baseFontBold, 8));
        text.addCell(paragraph);
        paragraph = new Paragraph("per download", new Font(baseFontBold, 8));
        text.addCell(paragraph);
        paragraph = new Paragraph("disegno 2D (file DWG)", new Font(baseFont, 7));
        text.addCell(paragraph);
        paragraph = new Paragraph("disegno 3D (file EASM_IGES)", new Font(baseFont, 7));
        text.addCell(paragraph);
        paragraph = new Paragraph("istruzioni montaggio (file PDF)", new Font(baseFont, 7));
        text.addCell(paragraph);
        table.addCell(text);
        
        table.completeRow();

        return table;

    }

    public PdfPTable getDisegno(Mrp_arch_articoli art, HttpServletRequest request, Document document, PdfWriter writer) throws DocumentException, IOException {

        WebApplicationContext ctx = RequestContextUtils.getWebApplicationContext(request);
        RequestContext rc = new RequestContext(request);

        PdfPTable tableDisegno = null;
        PdfPCell cell = null;
        Paragraph paragraph = null;

        float[] columnWidth = {380};

        tableDisegno = new PdfPTable(1);
        tableDisegno.setWidthPercentage(100);
        PdfPCell defaultCell = tableDisegno.getDefaultCell();
        defaultCell.setBorder(PdfPCell.RECTANGLE);
        defaultCell.setBorderWidth(.0f);
        defaultCell.setPadding(0);
        defaultCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        defaultCell.setVerticalAlignment(Element.ALIGN_CENTER);

        String realPath = getResourcesPath(art, request, false, "pdf");

        /*realPath = WebUtils.getRealPath(ctx.getServletContext(), ROOT_RES + "/risorse/" + filename + ".pdf");

         //File f = new File(realPath);
         if (!new File(realPath).exists()) { 
            
         filename = StringUtils.stripEnd(filename, art.getCdvistelet());
         realPath = WebUtils.getRealPath(ctx.getServletContext(), ROOT_RES + "/risorse/" + filename + ".pdf");
                     
         if(!new File(realPath).exists()){
         realPath = WebUtils.getRealPath(ctx.getServletContext(), ROOT_RES + "/risorse/" + art.getVist_filedis() + ".pdf");
         }
         }*/
//        String realPath = WebUtils.getRealPath(ctx.getServletContext(), ROOT_RES + "/risorse/" + art.getVist_filedis() + ".pdf");
        PdfReader readerDis = new PdfReader(realPath);
        PdfImportedPage pdfDis = writer.getImportedPage(readerDis, 1);

        Image img = Image.getInstance(pdfDis);
//        img.setAlignment(Image.TEXTWRAP | Image.ALIGN_CENTER);
        img.setAlignment(Image.ALIGN_MIDDLE);

        cell = new PdfPCell(img, true);
        cell.setFixedHeight(400);
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_CENTER);
        cell.addElement(img);

        tableDisegno.addCell(cell);

//        tableDisegno.completeRow();
        return tableDisegno;

    }

    private String findPreferred(Mrp_arch_articoli art, List<Vist_elettrificazioni> vist_elettrificazioni, HttpServletRequest request, boolean byLang) throws FileNotFoundException {

        WebApplicationContext ctx = RequestContextUtils.getWebApplicationContext(request);
        RequestContext rc = new RequestContext(request);

        List<String> possibleFilenameList = new ArrayList<String>();
        String filename = art.getVist_filedis();
        possibleFilenameList.add(filename);

        String suffix = "";

        if ("UL".equals(art.getCdclas_a())) {
            suffix = "UL";
            possibleFilenameList.add(filename + suffix);
        }

        for (String file : possibleFilenameList) {

            String pdf = WebUtils.getRealPath(ctx.getServletContext(), ROOT_RES + "/risorse/" + file + ".pdf");
            String xls = WebUtils.getRealPath(ctx.getServletContext(), ROOT_RES + "/risorse/" + file + (byLang ? "_" + rc.getLocale().getLanguage() : "") + ".xlsx");

            if (new File(pdf).exists() && new File(xls).exists()) {
                return art.getCdvistelet();
            }
        }

        for (Vist_elettrificazioni elettrificazione : vist_elettrificazioni) {

            String file = filename + elettrificazione.getCdvistelet() + suffix;
            String pdf = WebUtils.getRealPath(ctx.getServletContext(), ROOT_RES + "/risorse/" + file + ".pdf");
            String xls = WebUtils.getRealPath(ctx.getServletContext(), ROOT_RES + "/risorse/" + file + (byLang ? "_" + rc.getLocale().getLanguage() : "") + ".xls");

            if (new File(pdf).exists() && new File(xls).exists()) {
                return elettrificazione.getCdvistelet();
            }
        }

        return art.getCdvistelet();
    }

    private String getResourcesPath(Mrp_arch_articoli art, HttpServletRequest request, boolean byLang, String ext) throws FileNotFoundException {

        WebApplicationContext ctx = RequestContextUtils.getWebApplicationContext(request);
        RequestContext rc = new RequestContext(request);

        List<String> possibleFilenameList = new ArrayList<String>();
        String filename = art.getVist_filedis();
        possibleFilenameList.add(filename);

        if ("UL".equals(art.getCdclas_a())) {

            possibleFilenameList.add(0, filename + "UL");
            possibleFilenameList.add(0, filename + art.getCdvistelet() + "UL");
        } else {
            filename += art.getCdvistelet();
            possibleFilenameList.add(0, filename);
        }
        String realPath = null;
        for (String file : possibleFilenameList) {

            realPath = WebUtils.getRealPath(ctx.getServletContext(), ROOT_RES + "/risorse/" + file + (byLang ? "_" + rc.getLocale().getLanguage() : "") + "." + ext);

            if (new File(realPath).exists()) {
                break;
            }

        }
        return realPath;
    }

    public PdfPTable getDisegnoDim(Mrp_arch_articoli art, HttpServletRequest request, Document document, PdfWriter writer) throws DocumentException, IOException {

        WebApplicationContext ctx = RequestContextUtils.getWebApplicationContext(request);
        RequestContext rc = new RequestContext(request);

        PdfPTable table = null;
        PdfPCell cell = null;
        Paragraph paragraph = null;

        PdfPTable tableCnt = new PdfPTable(1);
        tableCnt.setWidthPercentage(100);
        tableCnt.getDefaultCell().setBorder(PdfPCell.RECTANGLE);
        tableCnt.getDefaultCell().setBorderWidth(.0f);
        PdfPCell cellCnt = new PdfPCell();
        cellCnt.setBorderWidth(.2f);
        cellCnt.setPaddingTop(2);
        cellCnt.setPaddingRight(5);
        cellCnt.setPaddingBottom(4);
        cellCnt.setPaddingLeft(5);

        table = new PdfPTable(1);
        table.setWidthPercentage(100);
        PdfPCell defaultCell = table.getDefaultCell();
        defaultCell.setBorder(PdfPCell.NO_BORDER);
        defaultCell.setPaddingTop(2);
        defaultCell.setPaddingRight(0);
        defaultCell.setPaddingBottom(2);
        defaultCell.setPaddingLeft(0);
        defaultCell.setHorizontalAlignment(Element.ALIGN_LEFT);

        Paragraph paragraph1 = new Paragraph(messageSource.getMessage("dimensioni", null, rc.getLocale()).toUpperCase(), new Font(baseFontBold, 8));
        table.addCell(paragraph1);

        String realPath = WebUtils.getRealPath(ctx.getServletContext(), "/images/articoli/disegnitecnici/" + art.getVist_filedis() + ".jpg");

        Image img = Image.getInstance(realPath);
        img.setAlignment(Image.ALIGN_CENTER);

        cell = new PdfPCell(img, true);
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_CENTER);
        cell.setPaddingBottom(1);
        cell.addElement(img);

        table.addCell(cell);

        //versioni alternative
        String cdalias = StringUtils.rightPad(art.getCdvistfam(), 5) + art.getCdvisttp() + "%";
        Map pars = new HashMap();
        pars.put("cdalias", cdalias);

        Cookie ckViewOff = WebUtils.getCookie(request, "filter_off");
        pars.put("fgpromo", ((ckViewOff != null && "S".equals(ckViewOff.getValue())) ? "S" : null));

        List<Mrp_arch_stato> availableStates = vistosiShopManager.getAvailableStates();
        List<String> statiFilter = new ArrayList<String>();
        for (Mrp_arch_stato mrp_arch_stato : availableStates) {
            String cookieName = "view-stato_" + mrp_arch_stato.getCdstato();

            Cookie ckView = WebUtils.getCookie(request, cookieName);
            if (ckView != null && "S".equals(ckView.getValue())) {
                statiFilter.add(mrp_arch_stato.getCdstato());
            }
        }
        if (!statiFilter.isEmpty()) {
            pars.put("statiFilterList", statiFilter);
        }
        if (AuthorityUtils.userHasAuthority("ROLE_ANONYMOUS")) {
            pars.put("statiEscludedList", Collections.singletonList("ZEE"));
        }

        vistosiShopManager.addCdclasFilter(pars, request);
        List<Mrp_arch_articoli> modelli = vistosiShopManager.getModelliDis(pars);
        //shrink models list
        List<Mrp_arch_articoli> modellidis = new ArrayList<Mrp_arch_articoli>();
        if (modelli.size() > 1) {

            Mrp_arch_articoli artSeed = modelli.get(0);

            for (int i = 1; i < modelli.size(); i++) {

                Mrp_arch_articoli mrp_arch_articoli = modelli.get(i);
                if (StringUtils.equalsIgnoreCase(mrp_arch_articoli.getVist_filedis(), artSeed.getVist_filedis())) {

                    if (!StringUtils.equalsIgnoreCase(artSeed.getCdvistv1(), mrp_arch_articoli.getCdvistv1())) {
                        artSeed.setCdvistv1(null);
                    }
                    if (!StringUtils.equalsIgnoreCase(artSeed.getCdvistv2(), mrp_arch_articoli.getCdvistv2())) {
                        artSeed.setCdvistv2(null);
                    }
                    if (!StringUtils.equalsIgnoreCase(artSeed.getCdvistv3(), mrp_arch_articoli.getCdvistv3())) {
                        artSeed.setCdvistv3(null);
                    }
                    if (!StringUtils.equalsIgnoreCase(artSeed.getCdvistcolv(), mrp_arch_articoli.getCdvistcolv())) {
                        artSeed.setCdvistcolv(null);
                    }
                    if (!StringUtils.equalsIgnoreCase(artSeed.getCdvistfinv(), mrp_arch_articoli.getCdvistfinv())) {
                        artSeed.setCdvistfinv("ign");
                    }
                    if (!StringUtils.equalsIgnoreCase(artSeed.getCdvistfinm(), mrp_arch_articoli.getCdvistfinm())) {
                        artSeed.setCdvistfinm(null);
                    }
                    if (!StringUtils.equalsIgnoreCase(artSeed.getCdvistelet(), mrp_arch_articoli.getCdvistelet())) {
                        artSeed.setCdvistelet(null);
                    }

                } else {

                    modellidis.add(artSeed);
                    artSeed = mrp_arch_articoli;

                }

                if (i == modelli.size() - 1) {
                    modellidis.add(artSeed);
                }
            }
        } else {
            modellidis = modelli;
        }
        if (!modellidis.isEmpty() && modellidis.size() > 1) {
            defaultCell.setPaddingBottom(0);
            table.addCell(new Paragraph(messageSource.getMessage("versioni.alt", null, rc.getLocale()).toUpperCase(), new Font(baseFontBold, 8)));
            table.addCell(new Paragraph(messageSource.getMessage("versioni.alt.linkalert", null, rc.getLocale()), new Font(baseFont, 6)));

            String vers = "";

            String www = "http://www.vistosi";
            if (rc.getLocale().getLanguage().equals("it")) {
                www += ".it";
            } else if (rc.getLocale().getLanguage().equals("ru")) {
                www += ".ru";
            } else {
                www += ".com";
            }

            Vist_famiglia vist_famiglia = vistosiShopManager.getVist_famigliaByKey(art.getCdvistfam());
            www += "/shop/scheda-" + vist_famiglia.getDsvistfam() + "-" + art.getCdvisttp() + "/" + art.getCdvistfam() + "?";

            List<Chunk> versList = new ArrayList<>();
            for (Mrp_arch_articoli mrp_arch_articoli : modellidis) {
                if (!StringUtils.equals(art.getCdvistv1(), mrp_arch_articoli.getCdvistv1())
                        || !StringUtils.equals(art.getCdvistv2(), mrp_arch_articoli.getCdvistv2())
                        || !StringUtils.equals(art.getCdvistv3(), mrp_arch_articoli.getCdvistv3())) {
                    String filedis = mrp_arch_articoli.getVist_filedis();

                    String artLink = www + "model=" + filedis.replaceAll(" ", "");

                    filedis = StringUtils.remove(filedis, StringUtils.rightPad(art.getCdvistfam(), 5) + art.getCdvisttp());
                    filedis = StringUtils.substringBefore(filedis, "-");
                    filedis = StringUtils.trim(filedis);
                    if (StringUtils.isNotBlank(filedis)) {
                        vers += filedis + ", ";
                        Chunk ck = new Chunk(filedis);
                        ck.setAction(new PdfAction(new URL(artLink)));
                        versList.add(ck);
                        versList.add(new Chunk(", "));
                    }
                }
            }

            Paragraph pVersionsLinks = new Paragraph("", new Font(baseFont, 7));
            if (versList.size() > 0) {
                versList.remove(versList.size() - 1);
            }
            pVersionsLinks.addAll(versList);

            //table.addCell(new Paragraph(StringUtils.removeEnd(vers, ", "), new Font(baseFont, 7)));
            table.addCell(pVersionsLinks);

        } else {
            table.addCell(new Paragraph(" ", new Font(baseFontBold, 8)));
            table.addCell(new Paragraph(" ", new Font(baseFont, 7)));
        }

        cellCnt.addElement(table);
        tableCnt.addCell(cellCnt);

        return tableCnt;

    }

    public PdfPTable getFinituraDiffusore(Mrp_arch_articoli art, List<Vist_vetro> vist_vetro, HttpServletRequest request, Document document, PdfWriter writer) throws DocumentException, IOException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {

        WebApplicationContext ctx = RequestContextUtils.getWebApplicationContext(request);
        RequestContext rc = new RequestContext(request);

        PdfPTable table = null;
        PdfPCell cell = null;
        Paragraph paragraph = null;

        //float[] columnWidth = {130, 250};
        table = new PdfPTable(3);
        table.setWidthPercentage(100);
        table.setWidths(new int[]{1, 1, 1});
        table.getDefaultCell().setBorder(PdfPCell.RECTANGLE);
        table.getDefaultCell().setBorderWidth(0f);
        table.getDefaultCell().setPadding(0f);

        cell = new PdfPCell();
        cell.setBorderWidth(.0f);
        cell.setPaddingTop(2);
        cell.setPaddingRight(0);
        cell.setPaddingBottom(5);
        cell.setPaddingLeft(0);
        cell.setColspan(3);
        cell.setUseAscender(true);
        cell.setUseDescender(false);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);

        Paragraph paragraph1 = new Paragraph(messageSource.getMessage("col.vetro", null, rc.getLocale()).toUpperCase(), new Font(baseFontBold, 8));
        cell.addElement(paragraph1);
        table.addCell(cell);

        table.getDefaultCell().setColspan(1);

        for (Vist_vetro vetro : vist_vetro) {

            PdfPTable blockTable = new PdfPTable(1);
            blockTable.getDefaultCell().setBorderWidth(0f);
            blockTable.getDefaultCell().setPadding(0f);

            Image img = null;
            try {
                String pathimg = "/images/articoli/vetro/";
                String nomeimg = art.getCdvistfam() + "_" + art.getCdvisttp() + "_" + StringUtils.trimToEmpty(art.getCdvistv1()) + "_"
                        + StringUtils.trimToEmpty(vetro.getCol().getCdvistcolv()) + StringUtils.trimToEmpty(vetro.getFin().getCdvistfinv()) + ".jpg";
                String realPath = WebUtils.getRealPath(ctx.getServletContext(), pathimg + nomeimg);

                File f = new File(realPath);
                if (f.exists()) {
                    img = Image.getInstance(realPath);
                } else {
                    String[] comp = StringUtils.splitByWholeSeparatorPreserveAllTokens(nomeimg, "_");

                    String newFilename = comp[0] + "_" + comp[1] + "__" + comp[3];
                    String path1 = StringUtils.replace(realPath, nomeimg, newFilename);
                    f = new File(path1);
                    if (f.exists()) {
                        img = Image.getInstance(path1);
                    } else {
                        newFilename = comp[0] + "___" + comp[3];

                        String path2 = StringUtils.replace(realPath, nomeimg, newFilename);
                        f = new File(path2);
                        if (f.exists()) {
                            img = Image.getInstance(path2);
                        }
                    }

                }
            } catch (FileNotFoundException ex) {
                //log.debug("not exists");
            }

            if (img != null) {
                img.scalePercent(50);
                PdfPCell pdfPCell = new PdfPCell();
                pdfPCell.setBorderWidth(.0f);
                pdfPCell.setPadding(.0f);
                pdfPCell.setColspan(1);
                pdfPCell.addElement(img);
                pdfPCell.setHorizontalAlignment(Element.ALIGN_LEFT);
                blockTable.addCell(pdfPCell);
            }

            cell = new PdfPCell();
            //cell.setFixedHeight(25);
            cell.setBorderWidth(.0f);
            //cell.setColspan(2);
            cell.setPaddingTop(5f);
            cell.setPaddingRight(0f);
            cell.setPaddingBottom(10f);
            cell.setPaddingLeft(0f);
            cell.setUseAscender(true);
            cell.setUseDescender(false);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
            String finitura = StringUtils.trimToEmpty(vetro.getCol().getCdvistcolv());
            if (vetro.getFin() != null && StringUtils.isNotBlank(vetro.getFin().getCdvistfinv())) {
                finitura += "/" + vetro.getFin().getCdvistfinv();
            }
            cell.addElement(new Paragraph(finitura, new Font(baseFontBold, 8)));
            blockTable.addCell(cell);

            table.addCell(blockTable);

        }

        table.completeRow();

        return table;

    }

    public PdfPTable getFinituraMontatura(Mrp_arch_articoli art, List<Vist_finit_mont> vist_finit_mont, HttpServletRequest request, Document document, PdfWriter writer) throws DocumentException, IOException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {

        WebApplicationContext ctx = RequestContextUtils.getWebApplicationContext(request);
        RequestContext rc = new RequestContext(request);

        PdfPTable table = null;
        PdfPCell cell = null;
        Paragraph paragraph = null;

        //float[] columnWidth = {190, 190};
        table = new PdfPTable(3);
        table.setWidthPercentage(100);
        table.setWidths(new int[]{1, 1, 1});
        table.getDefaultCell().setBorder(PdfPCell.RECTANGLE);
        table.getDefaultCell().setBorderWidth(0f);
        table.getDefaultCell().setPadding(0f);

        if (!vist_finit_mont.isEmpty()) {
            cell = new PdfPCell();
            cell.setBorderWidth(.0f);
            cell.setPaddingTop(2);
            cell.setPaddingRight(0);
            cell.setPaddingBottom(5);
            cell.setPaddingLeft(0);
            cell.setColspan(3);
            cell.setUseAscender(true);
            cell.setUseDescender(false);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);

            Paragraph paragraph1 = new Paragraph(messageSource.getMessage("fin.mont", null, rc.getLocale()).toUpperCase(), new Font(baseFontBold, 8));
            cell.addElement(paragraph1);
            table.addCell(cell);

            table.getDefaultCell().setColspan(1);

            for (Vist_finit_mont montatura : vist_finit_mont) {

                PdfPTable blockTable = new PdfPTable(1);
                blockTable.getDefaultCell().setBorderWidth(0f);
                blockTable.getDefaultCell().setPadding(0f);

                Image img = null;
                try {
                    String pathimg = "/images/articoli/montature/";
                    String nomeimg = montatura.getCdvistfinm() + ".jpg";
                    String realPath = WebUtils.getRealPath(ctx.getServletContext(), pathimg + nomeimg);

                    File f = new File(realPath);
                    if (f.exists()) {
                        img = Image.getInstance(realPath);
                    } else {
                        //TODO???
                    }
                } catch (FileNotFoundException ex) {
                    //log.debug("not exists");
                }

                if (img != null) {
                    img.scalePercent(50);
                    PdfPCell pdfPCell = new PdfPCell();
                    pdfPCell.setBorderWidth(0f);
                    pdfPCell.setPadding(.0f);
                    pdfPCell.setColspan(2);
                    pdfPCell.addElement(img);
                    pdfPCell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    blockTable.addCell(pdfPCell);
                }

                cell = new PdfPCell();
                //cell.setFixedHeight(30);
                cell.setBorderWidth(.0f);
                //cell.setColspan(2);
                cell.setPaddingTop(5f);
                cell.setPaddingRight(0f);
                cell.setPaddingBottom(10f);
                cell.setPaddingLeft(0f);
                cell.setUseAscender(true);
                cell.setUseDescender(false);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                String finitura = StringUtils.trimToEmpty(montatura.getCdvistfinm());
                cell.addElement(new Paragraph(finitura, new Font(baseFontBold, 8)));
                blockTable.addCell(cell);
            }

            table.completeRow();
        }

        return table;

    }

    public PdfPTable getLampadine(Mrp_arch_articoli art, Vist_articoli_datiextra datiExtra, HttpServletRequest request, Document document, PdfWriter writer) throws DocumentException, IOException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {

        WebApplicationContext ctx = RequestContextUtils.getWebApplicationContext(request);
        RequestContext rc = new RequestContext(request);

        PdfPTable table = null;
        PdfPCell cell = null;
        Paragraph paragraph = null;

        float[] columnWidth = {190, 190};

        table = new PdfPTable(3);
        table.setWidthPercentage(100);
        table.setWidths(new int[]{1, 1, 4});
        table.getDefaultCell().setBorder(PdfPCell.RECTANGLE);
        table.getDefaultCell().setBorderWidth(0f);
        table.getDefaultCell().setPadding(0f);
        table.getDefaultCell().setFixedHeight(11);

        cell = new PdfPCell();
        cell.setFixedHeight(15);
        cell.setBorderWidth(.0f);
        cell.setColspan(3);
        cell.setPadding(0f);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);

        Paragraph paragraph1 = new Paragraph(messageSource.getMessage("lampadine", null, rc.getLocale()).toUpperCase() + ":", new Font(baseFontBold, 8));
        cell.addElement(paragraph1);
        table.addCell(cell);

        //creazione mappa img iniziali
        HashMap<String, String> tipoLampadine = new HashMap<String, String>();
        tipoLampadine.put("A", "ALO");
        tipoLampadine.put("F", "FL");
        tipoLampadine.put("L", "LED");
        tipoLampadine.put("E", "ES");
        tipoLampadine.put("I", "IOD");
        if (datiExtra != null) {
            Image img = null;

            String pathimg = ROOT_RES + "dati/";
            //path immagini lampadine
            String pathlampimg = ROOT_RES + "lampadine/";

            //lampadina principale
            //int start = 1;
            //for (int i = start; i <= start + 4; i += 2) {
            int[] idxs = {1, 2, 7, 8};
            for (int pos : idxs) {
                try {
                    String flag = BeanUtils.getSimpleProperty(datiExtra, "arwFlagStampa" + pos);

                    flag = StringUtils.trimToEmpty(flag);

                    if (StringUtils.equals(flag, "S")) {

                        PdfPCell pdfPCell = new PdfPCell();
                        pdfPCell.setFixedHeight(13);
                        pdfPCell.setBorderWidth(0f);
                        String nomeimg = BeanUtils.getSimpleProperty(datiExtra, "arwSimbAttacco" + pos);
                        String[] split = nomeimg.split("\\\\");
                        String acronimoLampada = (tipoLampadine.get(split[0]) != null ? tipoLampadine.get(split[0]) : split[0]) + ".jpg";
                        nomeimg = split[1];

                        String realPathAcronimo = WebUtils.getRealPath(ctx.getServletContext(), pathimg + acronimoLampada);
                        File fAcronimo = new File(realPathAcronimo);

                        String realPath = WebUtils.getRealPath(ctx.getServletContext(), pathlampimg + nomeimg);
                        File f = new File(realPath);

                        if (pos > 1) {
                            pdfPCell = new PdfPCell(new Paragraph("+", new Font(baseFont, 7)));
                            pdfPCell.setBorderWidth(0f);
                            pdfPCell.setPaddingTop(4f);
                            pdfPCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                            table.addCell(pdfPCell);
                        } else if (fAcronimo.exists()) {
                            img = Image.getInstance(realPathAcronimo);
                            if (img != null) {
                                pdfPCell = new PdfPCell();
                                pdfPCell.setHorizontalAlignment(Element.ALIGN_LEFT);
                                pdfPCell.setFixedHeight(17f);
                                pdfPCell.setPadding(2);
                                pdfPCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                                pdfPCell.addElement(img);
                                pdfPCell.setBorderWidth(0f);
                                table.addCell(pdfPCell);
                            }
                        } else {
                            table.addCell("");
                        }

                        if (f.exists()) {
                            img = Image.getInstance(realPath);

                            if (img != null) {
                                pdfPCell = new PdfPCell();
                                pdfPCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                                pdfPCell.setFixedHeight(17f);
                                pdfPCell.setPadding(0f);
                                pdfPCell.setPaddingLeft(5f);
                                pdfPCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                                pdfPCell.addElement(img);
                                pdfPCell.setBorderWidth(0f);
                                table.addCell(pdfPCell);
                            }

                        } else {
                            pdfPCell = new PdfPCell(new Phrase(""));
                            pdfPCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                            pdfPCell.setFixedHeight(17f);
                            pdfPCell.setPadding(0f);
                            pdfPCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                            pdfPCell.setBorderWidth(0f);
                            table.addCell(pdfPCell);
                        }

                        //descrizione
                        //String tipoAttacco = BeanUtils.getSimpleProperty(datiExtra, "arwTipoAttacco" + i); 
                        //TODO sostituirlo con l'icona della lampadina quando le passeranno
                        String qtaPotenza = BeanUtils.getSimpleProperty(datiExtra, "arwQtaPotenza" + pos);
                        String potenza = BeanUtils.getSimpleProperty(datiExtra, "arwPotenza" + pos);
                        //String voltaggio = BeanUtils.getSimpleProperty(datiExtra, "arwVoltaggio" + i);

                        /*String descrizione = StringUtils.trimToEmpty(tipoAttacco)
                         + " " + StringUtils.trimToEmpty(qtaPotenza) + "x" + StringUtils.trimToEmpty(potenza)
                         + " " + StringUtils.trimToEmpty(voltaggio);*/
                        String descrizione = StringUtils.trimToEmpty(qtaPotenza) + "x" + StringUtils.trimToEmpty(potenza);
                        descrizione = StringUtils.trim(descrizione);

                        if (StringUtils.isNotBlank(descrizione)) {
                            pdfPCell = new PdfPCell(new Paragraph(descrizione, new Font(baseFont, 7)));
                            pdfPCell.setBorderWidth(0f);
                            pdfPCell.setPaddingTop(4f);
                            table.addCell(pdfPCell);
                        }
                    }
                } catch (FileNotFoundException ex) {
                    //log.debug("not exists");
                }
            }

            //separo le due tabelle
            Paragraph space = new Paragraph("");
            PdfPCell spaceCell = new PdfPCell(space);
            spaceCell.setColspan(3);
            spaceCell.setFixedHeight(5f);
            spaceCell.setBorderWidth(0f);
            table.addCell(spaceCell);

            //alternative
            boolean empty = true;
            PdfPTable innerTable = new PdfPTable(3);
            innerTable.setWidthPercentage(100);
            innerTable.setWidths(new int[]{1, 1, 4});
            innerTable.getDefaultCell().setBorder(PdfPCell.RECTANGLE);
            innerTable.getDefaultCell().setBorderWidth(0f);
            innerTable.getDefaultCell().setPaddingTop(2);
            innerTable.getDefaultCell().setPaddingRight(0);
            innerTable.getDefaultCell().setPaddingBottom(2);
            innerTable.getDefaultCell().setPaddingLeft(4);
            innerTable.getDefaultCell().setUseAscender(true);
            innerTable.getDefaultCell().setUseDescender(false);
            //innerTable.getDefaultCell().setFixedHeight(11);

            cell = new PdfPCell();
            //cell.setFixedHeight(11);
            cell.setBorderWidth(.0f);
            cell.setColspan(3);
            cell.setPadding(4);
            cell.setUseAscender(true);
            cell.setUseDescender(false);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.addElement(new Paragraph(messageSource.getMessage("lampadine.alt", null, rc.getLocale()).toUpperCase() + ":", new Font(baseFontBold, 8)));
//            innerTable.getDefaultCell().setPadding(0f); 
            innerTable.addCell(cell);
            int[] altIdxs = {3, 4, 9, 10, 5, 6, 11, 12};
            boolean alt = false;
            boolean idxPresent = false;
            for (int bidx : altIdxs) {

                try {
                    String flag = BeanUtils.getSimpleProperty(datiExtra, "arwFlagStampa" + bidx);

                    flag = StringUtils.trimToEmpty(flag);

                    if (StringUtils.equals(flag, "S")) {

                        PdfPCell pdfPCell = new PdfPCell();
                        pdfPCell.setFixedHeight(13);
                        pdfPCell.setBorderWidth(0f);
                        pdfPCell.setPadding(0f);
                        pdfPCell.setPaddingLeft(10);

                        String nomeimg = BeanUtils.getSimpleProperty(datiExtra, "arwSimbAttacco" + bidx);
                        String[] split = nomeimg.split("\\\\");
                        String acronimoLampada = (tipoLampadine.get(split[0]) != null ? tipoLampadine.get(split[0]) : split[0]) + ".jpg";
                        nomeimg = split[1];

                        String realPathAcronimo = WebUtils.getRealPath(ctx.getServletContext(), pathimg + acronimoLampada);
                        File fAcronimo = new File(realPathAcronimo);

                        String realPath = WebUtils.getRealPath(ctx.getServletContext(), pathlampimg + nomeimg);
                        File f = new File(realPath);

                        if (3 != bidx) {
                            if (5 != bidx) {
                                pdfPCell = new PdfPCell(new Paragraph("+", new Font(baseFont, 7)));
                                pdfPCell.setBorderWidth(0f);
                                pdfPCell.setPaddingTop(4f);
                                pdfPCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                                innerTable.addCell(pdfPCell);
                            } else if (fAcronimo.exists()) {
                                img = Image.getInstance(realPathAcronimo);
                                if (img != null) {
                                    pdfPCell = new PdfPCell();
                                    pdfPCell.setHorizontalAlignment(Element.ALIGN_LEFT);
                                    pdfPCell.setFixedHeight(17f);
                                    pdfPCell.setPadding(2);

                                    pdfPCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                                    pdfPCell.setImage(img);
                                    pdfPCell.setBorderWidth(0f);
                                    innerTable.addCell(pdfPCell);
                                }
                            } else {
                                innerTable.addCell("");
                            }
                        } else if (fAcronimo.exists()) {
                            img = Image.getInstance(realPathAcronimo);
                            if (img != null) {
                                pdfPCell = new PdfPCell();
                                pdfPCell.setHorizontalAlignment(Element.ALIGN_LEFT);
                                pdfPCell.setFixedHeight(17f);
                                pdfPCell.setPadding(2);

                                pdfPCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                                pdfPCell.setImage(img);
                                pdfPCell.setBorderWidth(0f);
                                innerTable.addCell(pdfPCell);
                            }
                        } else {
                            innerTable.addCell("");
                        }

                        if (f.exists()) {
                            img = Image.getInstance(realPath);

                            if (img != null) {
                                pdfPCell = new PdfPCell();
                                pdfPCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                                pdfPCell.setFixedHeight(17f);
                                pdfPCell.setPadding(0f);

                                pdfPCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                                pdfPCell.addElement(img);
                                pdfPCell.setBorderWidth(0f);
                                innerTable.addCell(pdfPCell);
                            }

                        } else {
                            pdfPCell = new PdfPCell(new Phrase(""));
                            pdfPCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                            pdfPCell.setFixedHeight(17f);
                            pdfPCell.setPadding(0f);
                            pdfPCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                            pdfPCell.setBorderWidth(0f);
                            innerTable.addCell(pdfPCell);
                        }

                        //descrizione
                        String qtaPotenza = BeanUtils.getSimpleProperty(datiExtra, "arwQtaPotenza" + bidx);
                        String potenza = BeanUtils.getSimpleProperty(datiExtra, "arwPotenza" + bidx);
                        String descrizione = StringUtils.trimToEmpty(qtaPotenza) + "x" + StringUtils.trimToEmpty(potenza);
                        descrizione = StringUtils.trim(descrizione);

                        if (StringUtils.isNotBlank(descrizione)) {
                            pdfPCell = new PdfPCell(new Paragraph(descrizione, new Font(baseFont, 7)));
                            pdfPCell.setBorderWidth(0f);
                            pdfPCell.setPaddingTop(4f);
                            innerTable.addCell(pdfPCell);
                            empty = false;
                        }

                    }
                } catch (FileNotFoundException ex) {
                    //log.debug("not exists");
                }

                if (bidx == 10) {
                    //separo le due tabelle
                    Paragraph space1 = new Paragraph("");
                    PdfPCell spaceCell1 = new PdfPCell(space1);
                    spaceCell1.setColspan(3);
                    spaceCell1.setFixedHeight(2f);
                    spaceCell1.setBorderWidth(0f);
                    innerTable.addCell(spaceCell1);
                }
                if (bidx == 12) {
                    alt = true;
                }
            }

            if (alt) {
                cell = new PdfPCell(new Paragraph("", new Font(baseFont, 7)));
                cell.setPadding(2f);
                cell.setBorderWidth(0f);
                table.addCell(cell);
                cell = new PdfPCell();
                cell.setPadding(0);
                cell.setColspan(2);
                if (empty) {
                    cell.setBorderWidth(0f);
                    cell.addElement(new Phrase(""));
                } else {
                    cell.addElement(innerTable);
                }
                table.addCell(cell);
            }
            table.completeRow();

            return table;
        } else {
            return null;
        }

    }

    public PdfPTable getMarcature(Mrp_arch_articoli art, Vist_articoli_datiextra datiExtra, HttpServletRequest request, Document document, PdfWriter writer) throws DocumentException, IOException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {

        WebApplicationContext ctx = RequestContextUtils.getWebApplicationContext(request);
        RequestContext rc = new RequestContext(request);

        PdfPTable table = null;
        PdfPCell cell = null;
        Paragraph paragraph = null;

        float[] columnWidth = {190, 190};

        PdfPTable tableCnt = new PdfPTable(1);
        tableCnt.setWidthPercentage(100);
        tableCnt.getDefaultCell().setBorder(PdfPCell.RECTANGLE);
        tableCnt.getDefaultCell().setBorderWidth(.0f);
        tableCnt.getDefaultCell().setPaddingTop(.0f);
        tableCnt.getDefaultCell().setPaddingRight(.0f);
        tableCnt.getDefaultCell().setPaddingBottom(5);
        tableCnt.getDefaultCell().setPaddingLeft(.0f);

        cell = new PdfPCell();
        cell.setFixedHeight(15);
        cell.setBorderWidth(.0f);
        cell.setPadding(.0f);
        cell.setColspan(6);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);

        Paragraph paragraph1 = new Paragraph(messageSource.getMessage("marcature", null, rc.getLocale()).toUpperCase() + ":", new Font(baseFontBold, 8));
        cell.addElement(paragraph1);
        tableCnt.addCell(cell);

        if (datiExtra != null) {
            Image img = null;

            table = new PdfPTable(6);
            table.setWidthPercentage(100);
            table.getDefaultCell().setBorder(PdfPCell.RECTANGLE);
            table.getDefaultCell().setBorderWidth(0f);
            table.getDefaultCell().setPadding(0f);

            String pathimg = ROOT_RES + "dati/";

            for (int i = 1; i < 7; i++) {

                try {
                    String nomeimg = BeanUtils.getSimpleProperty(datiExtra, "arwSimbolo" + i);

                    nomeimg = StringUtils.substringBetween(nomeimg, "\\", ".");
                    nomeimg = nomeimg + ".jpg";
                    String realPath = WebUtils.getRealPath(ctx.getServletContext(), pathimg + nomeimg);

                    File f = new File(realPath);
                    if (f.exists()) {
                        img = Image.getInstance(realPath);

                        if (img != null) {
                            //img.scalePercent(50);
                            PdfPCell pdfPCell = new PdfPCell();
                            pdfPCell.setFixedHeight(13);
                            pdfPCell.setBorderWidth(0f);
                            pdfPCell.setPadding(0f);
                            pdfPCell.setHorizontalAlignment(Element.ALIGN_LEFT);
                            pdfPCell.addElement(img);
                            table.addCell(pdfPCell);
                        }

                    } else {
                        //TODO???
                    }
                } catch (FileNotFoundException ex) {
                    //log.debug("not exists");
                }
            }

            table.completeRow();
            tableCnt.addCell(table);

            table = new PdfPTable(6);
            table.setWidthPercentage(100);
            table.getDefaultCell().setBorder(PdfPCell.RECTANGLE);
            table.getDefaultCell().setBorderWidth(0f);
            table.getDefaultCell().setPadding(0f);

            for (int i = 1; i < 5; i++) {

                try {

                    String fieldName = "arwCertificazione" + i;
                    String nomefile = BeanUtils.getSimpleProperty(datiExtra, fieldName);

                    if (StringUtils.isNotBlank(nomefile)) {
                        String nomeimg = vistosiShopManager.getCertImageName(fieldName);
                        nomeimg = nomeimg + ".jpg";
                        String realPath = WebUtils.getRealPath(ctx.getServletContext(), pathimg + nomeimg);

                        File f = new File(realPath);
                        if (f.exists()) {
                            img = Image.getInstance(realPath);

                            if (img != null) {
                                //img.scalePercent(50);
                                PdfPCell pdfPCell = new PdfPCell();
                                pdfPCell.setFixedHeight(13);
                                pdfPCell.setBorderWidth(0f);
                                pdfPCell.setPadding(0f);
                                pdfPCell.setHorizontalAlignment(Element.ALIGN_LEFT);
                                pdfPCell.addElement(img);
                                table.addCell(pdfPCell);
                            }

                        } else {
                            //TODO???
                        }
                    }
                } catch (FileNotFoundException ex) {
                    //log.debug("not exists");
                }
            }

            table.completeRow();

            tableCnt.addCell(table);

            return tableCnt;
        } else {
            return null;
        }

    }

    public PdfPTable getDimensioni(Mrp_arch_articoli art, HttpServletRequest request, Document document, PdfWriter writer) throws DocumentException, IOException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {

        WebApplicationContext ctx = RequestContextUtils.getWebApplicationContext(request);
        RequestContext rc = new RequestContext(request);

        PdfPTable table = null;
        PdfPCell cell = null;
        Paragraph paragraph = null;

        float[] columnWidth = {380};

        PdfPTable tableCnt = new PdfPTable(1);
        tableCnt.setWidthPercentage(100);
        tableCnt.getDefaultCell().setBorder(PdfPCell.RECTANGLE);
        tableCnt.getDefaultCell().setBorderWidth(.0f);
        PdfPCell cellCnt = new PdfPCell();
        cellCnt.setBorderWidth(.2f);
        cellCnt.setPaddingTop(0);
        cellCnt.setPaddingRight(5);
        cellCnt.setPaddingBottom(2);
        cellCnt.setPaddingLeft(5);
        cellCnt.setBackgroundColor(BaseColor.LIGHT_GRAY);

        table = new PdfPTable(1);
        table.setWidthPercentage(100);
        PdfPCell defaultCell = table.getDefaultCell();
        defaultCell.setBorder(PdfPCell.NO_BORDER);
        defaultCell.setPaddingTop(3);
        defaultCell.setPaddingRight(0);
        defaultCell.setPaddingBottom(3);
        defaultCell.setPaddingLeft(0);
        defaultCell.setUseAscender(true);
        defaultCell.setUseDescender(false);
        defaultCell.setHorizontalAlignment(Element.ALIGN_LEFT);

        Paragraph paragraph1 = new Paragraph(messageSource.getMessage("volume", null, rc.getLocale()).toUpperCase(), new Font(baseFontBold, 9));
        table.addCell(paragraph1);

        Paragraph paragraph2 = new Paragraph("Mc " + numFormat.format(art.getVlunlt()), new Font(baseFont, 9));
        table.addCell(paragraph2);

        Paragraph paragraph3 = new Paragraph(messageSource.getMessage("peso.lordo", null, rc.getLocale()).toUpperCase(), new Font(baseFontBold, 9));
        table.addCell(paragraph3);

        Paragraph paragraph4 = new Paragraph("Kg " + numFormat.format(art.getNrpeso_l()), new Font(baseFont, 9));
        table.addCell(paragraph4);

        Paragraph paragraph5 = new Paragraph(messageSource.getMessage("peso.netto", null, rc.getLocale()).toUpperCase(), new Font(baseFontBold, 9));
        table.addCell(paragraph5);

        Paragraph paragraph6 = new Paragraph("Kg " + numFormat.format(art.getNrpeso_n()), new Font(baseFont, 9));
        table.addCell(paragraph6);
        table.completeRow();

        cellCnt.addElement(table);
        tableCnt.addCell(cellCnt);

        return tableCnt;

    }

    public PdfPTable getNote(Mrp_arch_articoli art, HttpServletRequest request, Document document, PdfWriter writer) throws DocumentException, IOException {

        WebApplicationContext ctx = RequestContextUtils.getWebApplicationContext(request);
        RequestContext rc = new RequestContext(request);

        PdfPTable tableNote = null;
        PdfPCell cell = null;
        Paragraph paragraph = null;

        tableNote = new PdfPTable(1);
        tableNote.setWidthPercentage(100);
        tableNote.getDefaultCell().setBorder(PdfPCell.RECTANGLE);
        tableNote.getDefaultCell().setPadding(0f);
        tableNote.getDefaultCell().setBorderWidth(0f);

        String realPath = getResourcesPath(art, request, true, "xlsx");

        FileInputStream file = new FileInputStream(new File(realPath));

        XSSFWorkbook workbook = new XSSFWorkbook(file);

        XSSFSheet sheet = workbook.getSheetAt(0);

        for (int i = 0; i < 15; i++) {

            String valore = "";
            String numero = "";

            if (sheet.getRow(i) != null) {
                if (sheet.getRow(i).getCell(0) != null) {
                    String nota = (String) sheet.getRow(i).getCell(0).getStringCellValue();
                    if (StringUtils.isNotBlank(nota) && StringUtils.contains(nota, "=")) {
                        String[] splitted = StringUtils.split(nota, "=");
                        numero = splitted[0];
                        valore = splitted[1];
                    } else {
                        continue;
                    }

                } else {
                    continue;
                }

            } else {
                continue;
            }

            if (valore == null) {
                valore = "";
            } else {
                valore = valore.replaceAll("\n", "");
            }
            if (numero == null) {
                numero = "";
            }

            cell = new PdfPCell();
            //cell.setFixedHeight(80);
            cell.setBorderWidth(.0f);
            paragraph = new Paragraph(numero, fontTitoloNota);
            cell.addElement(paragraph);
            paragraph = new Paragraph(valore, fontNota);
            cell.addElement(paragraph);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            tableNote.addCell(cell);
        }

        tableNote.completeRow();

        return tableNote;

    }

    public PdfPTable get3D(Mrp_arch_articoli art, HttpServletRequest request, Document document, PdfWriter writer) throws DocumentException, IOException {

        WebApplicationContext ctx = RequestContextUtils.getWebApplicationContext(request);
        RequestContext rc = new RequestContext(request);

        PdfPTable table = null;
        PdfPTable table3D = null;
        PdfPCell cell = null;
        Paragraph paragraph = null;

        float[] columnWidth = {125};

        table = new PdfPTable(1);
        table.setTotalWidth(columnWidth);
        table.setLockedWidth(true);
        table.getDefaultCell().setBorder(PdfPCell.RECTANGLE);
        table.getDefaultCell().setBorderWidth(0f);

        table3D = new PdfPTable(1);
        table3D.setWidthPercentage(100);
        table3D.getDefaultCell().setBorder(PdfPCell.RECTANGLE);
        table3D.getDefaultCell().setBorderWidth(0f);

        //PdfReader reader = new PdfReader(new FileInputStream(new File(rootImg + "APCLOTHP\\APCLOTHP.PDF")));
        //PdfImportedPage pdfDis = writer.getImportedPage(reader, 1);
//        String realPath = WebUtils.getRealPath(ctx.getServletContext(), ROOT_RES + art.getVist_filedis() + "/" + art.getVist_filedis() + "3D_" + rc.getLocale() + ".pdf");
//
//        PdfReader readerDis = new PdfReader(realPath);
//        PdfImportedPage pdfDis = writer.getImportedPage(readerDis, 1);
//
//        Image img = Image.getInstance(pdfDis);   
//        img.scalePercent(90);
        int lx = 459;
        int lY = 436;

        Rectangle rect = new Rectangle(lx, lY, lx + 115, lY + 150);
        rect.setBorder(Rectangle.BOX);
        rect.setBorderWidth(0.2f);
        rect.setBorderColor(new BaseColor(0x33, 0x33, 0x33));
        document.add(rect);

        String realPath = getResourcesPath(art, request, false, "U3D");

//        String filename = art.getVist_filedis();
//
//        if ("UL".equals(art.getCdclas_a())) {
//            filename += "UL";
//        }
//        String realPath = WebUtils.getRealPath(ctx.getServletContext(), ROOT_RES + "/risorse/" + filename + ".U3D");
//        File f = new File(realPath);
//        if (!f.exists()) {
//            realPath = WebUtils.getRealPath(ctx.getServletContext(), ROOT_RES + "/risorse/" + art.getVist_filedis() + ".U3D");
//        }
        File f = new File(realPath);
        if (f.exists()) {
            //if (realPath!=null) {

            cell = new PdfPCell();
            cell.setFixedHeight(150);
            cell.setBorderWidth(.0f);
//        cell.addElement(img);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
            cell.setPaddingLeft(6);
            cell.addElement(new Paragraph("Click to activate (only in Acrobat Reader)", new Font(baseFont, 6)));
            table3D.addCell(cell);

//        String realPath = WebUtils.getRealPath(ctx.getServletContext(), ROOT_RES + "/risorse/" + art.getVist_filedis() + ".U3D");
            PdfStream stream3D = new PdfStream(new FileInputStream(realPath), writer);
            stream3D.put(PdfName.TYPE, new PdfName("3D"));
            stream3D.put(PdfName.SUBTYPE, new PdfName("U3D"));
            stream3D.flateCompress();
            PdfIndirectObject streamObject = writer.addToBody(stream3D);
            stream3D.writeLength();

            PdfDictionary dict3D = new PdfDictionary();
            dict3D.put(PdfName.TYPE, new PdfName("3DView"));
            dict3D.put(new PdfName("XN"), new PdfString("Default"));
            dict3D.put(new PdfName("IN"), new PdfString("Unnamed"));
            dict3D.put(new PdfName("MS"), PdfName.M);
            dict3D.put(new PdfName("C2W"), new PdfArray(new float[]{-1, 0, 0, 0, 0, 1, 0, 1, 0, 0, -1, 0}));
            dict3D.put(PdfName.CO, new PdfNumber(1));

            PdfIndirectObject dictObject = writer.addToBody(dict3D);

            PdfAnnotation annot = new PdfAnnotation(writer, rect);
            annot.put(PdfName.CONTENTS, new PdfString("3D Model"));
            annot.put(PdfName.SUBTYPE, new PdfName("3D"));
            annot.put(PdfName.TYPE, PdfName.ANNOT);
            annot.put(new PdfName("3DD"), streamObject.getIndirectReference());
            annot.put(new PdfName("3DV"), dictObject.getIndirectReference());
            PdfAppearance ap = writer.getDirectContent().createAppearance(rect.getWidth(), rect.getHeight());
            annot.setAppearance(PdfAnnotation.APPEARANCE_NORMAL, ap);
            annot.setPage();

            writer.addAnnotation(annot);
        }

        cell = new PdfPCell();
        cell.setFixedHeight(150);
        cell.setBorderWidth(0f);
        cell.addElement(table3D);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);

        table.addCell(cell);

        return table;

    }

    private String getAtkLangsfx(String s_locale) {

        if ("it".equals(s_locale)) {
            return "";
        } else if ("en".equals(s_locale)) {
            return "_eng";
        } else if ("de".equals(s_locale)) {
            return "_ted";
        } else if ("es".equals(s_locale)) {
            return "_spa";
        } else if ("fr".equals(s_locale)) {
            return "_fra";
        } else {
            return "";
        }
    }
}
