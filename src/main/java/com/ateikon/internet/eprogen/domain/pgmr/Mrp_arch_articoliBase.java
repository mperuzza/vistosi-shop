/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ateikon.internet.eprogen.domain.pgmr;

import com.ateikon.internet.generic.domain.BaseTableBean;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author emiliano
 */
public class Mrp_arch_articoliBase extends BaseTableBean {

    private Log log = LogFactory.getLog(this.getClass());
    private Vist_var1 vist_var1;
    private Vist_var2 vist_var2;
    private Vist_var3 vist_var3;
    private Vist_famiglia vist_famiglia;
    private Vist_tipi vist_tipi;
    private Vist_finit_mont vist_finit_mont;
    private Vist_finit_vetro vist_finit_vetro;
    private Vist_colori_vetro vist_colori_vetro;
    private Vist_elettrificazioni vist_elettrificazioni;
    private Mrp_file_giacenza mrp_file_giacenza;
    private Vist_semilavorati vist_semilavorati;
    private Vist_articoli_img vist_articoli_img;
    private Mrp_arch_stato stato;
    private Vist_offerte vist_offerte;

    private String dsestesa;
    private String dsestesa_eng;
    private String dsestesa_fra;
    private String dsestesa_spa;
    private String dsestesa_ted;
    private String dsestesa_rus;
    private String model3D_igs;
    private String model3D_eprt;
    private String model3D_easm;
    private String model2D_dwg;
    private Boolean model3D_igsExists = Boolean.FALSE;
    private Boolean model3D_eprtExists = Boolean.FALSE;
    private Boolean model3D_easmExists = Boolean.FALSE;
    private Boolean model2D_dwgExists = Boolean.FALSE;
    private Boolean specsheetExists = Boolean.FALSE;
    private boolean ricambio = Boolean.FALSE;
    
    private String cdvisttpOri;
    private List<Vist_tipi> vistTipiAlt;

    public List<Vist_tipi> getVistTipiAlt() {
        return vistTipiAlt;
    }

    public void setVistTipiAlt(List<Vist_tipi> vistTipiAlt) {
        this.vistTipiAlt = vistTipiAlt;
    }

    public Vist_colori_vetro getVist_colori_vetro() {
        return vist_colori_vetro;
    }

    public void setVist_colori_vetro(Vist_colori_vetro vist_colori_vetro) {
        this.vist_colori_vetro = vist_colori_vetro;
    }

    public Vist_elettrificazioni getVist_elettrificazioni() {
        return vist_elettrificazioni;
    }

    public void setVist_elettrificazioni(Vist_elettrificazioni vist_elettrificazioni) {
        this.vist_elettrificazioni = vist_elettrificazioni;
    }

    public Vist_famiglia getVist_famiglia() {
        return vist_famiglia;
    }

    public void setVist_famiglia(Vist_famiglia vist_famiglia) {
        this.vist_famiglia = vist_famiglia;
    }

    public Vist_finit_mont getVist_finit_mont() {
        return vist_finit_mont;
    }

    public void setVist_finit_mont(Vist_finit_mont vist_finit_mont) {
        this.vist_finit_mont = vist_finit_mont;
    }

    public Vist_finit_vetro getVist_finit_vetro() {
        return vist_finit_vetro;
    }

    public void setVist_finit_vetro(Vist_finit_vetro vist_finit_vetro) {
        this.vist_finit_vetro = vist_finit_vetro;
    }

    public Vist_var1 getVist_var1() {
        return vist_var1;
    }

    public void setVist_var1(Vist_var1 vist_var1) {
        this.vist_var1 = vist_var1;
    }

    public Vist_var2 getVist_var2() {
        return vist_var2;
    }

    public void setVist_var2(Vist_var2 vist_var2) {
        this.vist_var2 = vist_var2;
    }

    public Vist_var3 getVist_var3() {
        return vist_var3;
    }

    public void setVist_var3(Vist_var3 vist_var3) {
        this.vist_var3 = vist_var3;
    }

    public Mrp_file_giacenza getMrp_file_giacenza() {
        return mrp_file_giacenza;
    }

    public void setMrp_file_giacenza(Mrp_file_giacenza mrp_file_giacenza) {
        this.mrp_file_giacenza = mrp_file_giacenza;
    }

    public boolean isRicambio() {
        return ricambio;
    }

    public void setRicambio(boolean ricambio) {
        this.ricambio = ricambio;
    }

    public Vist_tipi getVist_tipi() {
        return vist_tipi;
    }

    public void setVist_tipi(Vist_tipi vist_tipi) {
        this.vist_tipi = vist_tipi;
    }

    public Vist_semilavorati getVist_semilavorati() {
        return vist_semilavorati;
    }

    public void setVist_semilavorati(Vist_semilavorati vist_semilavorati) {
        this.vist_semilavorati = vist_semilavorati;
    }

    private String getDsestesaByLang(String langsfx) {

        String ds = "";

        try {
            if (vist_famiglia != null) {
                ds += StringUtils.trimToEmpty(BeanUtils.getSimpleProperty(vist_famiglia, "dsvistfam" + langsfx));
            }
            if (vist_tipi != null) {
                ds += " " + StringUtils.trimToEmpty(BeanUtils.getSimpleProperty(vist_tipi, "dsextvisttp" + langsfx));
            }
            if (isRicambio()) {
                if (vist_semilavorati != null) {
                    ds += " " + StringUtils.trimToEmpty(BeanUtils.getSimpleProperty(vist_semilavorati, "dsvistseml" + langsfx));
                }
                if (StringUtils.isNotBlank(getCdvistv1())) {
                    ds += " " + getCdvistv1().toUpperCase();
                }
                if (StringUtils.isNotBlank(getCdvistv2())) {
                    ds += " " + getCdvistv2().toUpperCase();
                }
                if (StringUtils.isNotBlank(getCdvistv3())) {
                    ds += " " + getCdvistv3().toUpperCase();
                }
            } else {
                if (vist_var1 != null) {
                    ds += " " + StringUtils.trimToEmpty(BeanUtils.getSimpleProperty(vist_var1, "dsextvistv1" + langsfx));
                }
                if (vist_var2 != null) {
                    ds += " " + StringUtils.trimToEmpty(BeanUtils.getSimpleProperty(vist_var2, "dsextvistv2" + langsfx));
                }
                if (vist_var3 != null) {
                    ds += " " + StringUtils.trimToEmpty(BeanUtils.getSimpleProperty(vist_var3, "dsextvistv3" + langsfx));
                }
            }
            if (vist_colori_vetro != null) {
                ds += " " + StringUtils.trimToEmpty(BeanUtils.getSimpleProperty(vist_colori_vetro, "dsextvistcolv" + langsfx));
            }
            if (vist_finit_vetro != null) {
                ds += " " + StringUtils.trimToEmpty(BeanUtils.getSimpleProperty(vist_finit_vetro, "dsextvistfinv" + langsfx));
            }
            if (vist_finit_mont != null) {
                ds += " " + StringUtils.trimToEmpty(BeanUtils.getSimpleProperty(vist_finit_mont, "dsextvistfinm" + langsfx));
            }
            ds = StringUtils.lowerCase(ds);

            if (vist_elettrificazioni != null) {
                String dselet = StringUtils.trimToEmpty(BeanUtils.getSimpleProperty(vist_elettrificazioni, "dsextvistelet" + langsfx));
                if (dselet.length() > 3) {
                    dselet = dselet.toLowerCase();
                }
                ds += " " + dselet;
            }

            ds = StringUtils.capitalize(ds);
        } catch (IllegalAccessException ex) {
            log.debug(ex);
        } catch (InvocationTargetException ex) {
            log.debug(ex);
        } catch (NoSuchMethodException ex) {
            log.debug(ex);
        }


        return ds;

    }

    public String getDsestesa() {
        return getDsestesaByLang("");
    }

    public String getDsestesa_eng() {
        return getDsestesaByLang("_eng");
    }

    public String getDsestesa_fra() {
        return getDsestesaByLang("_fra");
    }

    public String getDsestesa_spa() {
        return getDsestesaByLang("_spa");
    }

    public String getDsestesa_ted() {
        return getDsestesaByLang("_ted");
    }
    
    public String getDsestesa_rus() {
        return getDsestesaByLang("_rus");
    }
    private String cdvistv1;
    private String cdvistv2;
    private String cdvistv3;

    public String getCdvistv1() {
        return cdvistv1;
    }

    public void setCdvistv1(String cdvistv1) {
        this.cdvistv1 = cdvistv1;
    }

    public String getCdvistv2() {
        return cdvistv2;
    }

    public void setCdvistv2(String cdvistv2) {
        this.cdvistv2 = cdvistv2;
    }

    public String getCdvistv3() {
        return cdvistv3;
    }

    public void setCdvistv3(String cdvistv3) {
        this.cdvistv3 = cdvistv3;
    }

    public String getCdvistv1Pad() {

        return "  " + StringUtils.rightPad(StringUtils.trimToEmpty(getCdvistv1()), 3);
    }

    public String getCdvistv2Pad() {

        return StringUtils.rightPad(StringUtils.trimToEmpty(getCdvistv2()), 1);
    }

    public String getCdvistv3Pad() {

        return StringUtils.rightPad(StringUtils.trimToEmpty(getCdvistv3()), 2);
    }
    private Vist_articoli_ricambi datiRicambio;

    public Vist_articoli_ricambi getDatiRicambio() {
        return datiRicambio;
    }

    public void setDatiRicambio(Vist_articoli_ricambi datiRicambio) {
        this.datiRicambio = datiRicambio;
    }

    public Vist_articoli_img getVist_articoli_img() {
        return vist_articoli_img;
    }

    public void setVist_articoli_img(Vist_articoli_img vist_articoli_img) {
        this.vist_articoli_img = vist_articoli_img;
    }

    public String getModel2D_dwg() {
        return model2D_dwg;
    }

    public void setModel2D_dwg(String model2D_dwg) {
        this.model2D_dwg = model2D_dwg;
    }

    public String getModel3D_easm() {
        return model3D_easm;
    }

    public void setModel3D_easm(String model3D_easm) {
        this.model3D_easm = model3D_easm;
    }

    public String getModel3D_eprt() {
        return model3D_eprt;
    }

    public void setModel3D_eprt(String model3D_eprt) {
        this.model3D_eprt = model3D_eprt;
    }

    public String getModel3D_igs() {
        return model3D_igs;
    }

    public void setModel3D_igs(String model3D_igs) {
        this.model3D_igs = model3D_igs;
    }

    public Boolean getModel2D_dwgExists() {
        return model2D_dwgExists;
    }

    public void setModel2D_dwgExists(Boolean model2D_dwgExists) {
        this.model2D_dwgExists = model2D_dwgExists;
    }

    public Boolean getModel3D_easmExists() {
        return model3D_easmExists;
    }

    public void setModel3D_easmExists(Boolean model3D_easmExists) {
        this.model3D_easmExists = model3D_easmExists;
    }

    public Boolean getModel3D_eprtExists() {
        return model3D_eprtExists;
    }

    public void setModel3D_eprtExists(Boolean model3D_eprtExists) {
        this.model3D_eprtExists = model3D_eprtExists;
    }

    public Boolean getModel3D_igsExists() {
        return model3D_igsExists;
    }

    public void setModel3D_igsExists(Boolean model3D_igsExists) {
        this.model3D_igsExists = model3D_igsExists;
    }
    

    public Mrp_arch_stato getStato() {
        return stato;
    }

    public void setStato(Mrp_arch_stato stato) {
        this.stato = stato;
    }

    public Vist_offerte getVist_offerte() {
        return vist_offerte;
    }

    public void setVist_offerte(Vist_offerte vist_offerte) {
        this.vist_offerte = vist_offerte;
    }
    
    List<Ep_posts> techNewsList;

    public List<Ep_posts> getTechNewsList() {
        return techNewsList;
    }

    public void setTechNewsList(List<Ep_posts> techNewsList) {
        this.techNewsList = techNewsList;
    }    
    
    LinkedList<Map<String, String>> certList = new LinkedList<Map<String, String>>();

    public LinkedList<Map<String, String>> getCertList() {
        return certList;
    }

    public void setCertList(LinkedList<Map<String, String>> certList) {
        this.certList = certList;
    }    

    public String getCdvisttpOri() {
        return cdvisttpOri;
    }

    public void setCdvisttpOri(String cdvisttpOri) {
        this.cdvisttpOri = cdvisttpOri;
    }
    
    String energyClass;

    public String getEnergyClass() {
        return energyClass;
    }

    public void setEnergyClass(String energyClass) {
        this.energyClass = energyClass;
    }    
    
    Map eletDatiExtraMap;

    public Map getEletDatiExtraMap() {
        return eletDatiExtraMap;
    }

    public void setEletDatiExtraMap(Map eletDatiExtraMap) {
        this.eletDatiExtraMap = eletDatiExtraMap;
    }

    public Boolean getSpecsheetExists() {
        return specsheetExists;
    }

    public void setSpecsheetExists(Boolean specsheetExists) {
        this.specsheetExists = specsheetExists;
    }
    
    
    
}
