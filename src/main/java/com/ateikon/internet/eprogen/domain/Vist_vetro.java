/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ateikon.internet.eprogen.domain;

import com.ateikon.internet.eprogen.domain.pgmr.Mrp_arch_stato;
import com.ateikon.internet.eprogen.domain.pgmr.Vist_colori_vetro;
import com.ateikon.internet.eprogen.domain.pgmr.Vist_finit_vetro;
import java.io.Serializable;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 *
 * @author emiliano
 */
public class Vist_vetro extends Object implements Serializable {

    public Vist_vetro() {
        this.col = new Vist_colori_vetro();
        this.fin = new Vist_finit_vetro();
    }

    private Vist_colori_vetro col;

    private Vist_finit_vetro fin;

    private Mrp_arch_stato stato;

    public Mrp_arch_stato getStato() {
        return stato;
    }

    public void setStato(Mrp_arch_stato stato) {
        this.stato = stato;
    }

    public Vist_colori_vetro getCol() {
        return col;
    }

    public void setCol(Vist_colori_vetro col) {
        this.col = col;
    }

    public Vist_finit_vetro getFin() {
        return fin;
    }

    public void setFin(Vist_finit_vetro fin) {
        this.fin = fin;
    }

    private String cdvistvetro;

    private String dsvistvetro;

    private String dsextvistvetro;
    private String dsextvistvetro_eng;
    private String dsextvistvetro_fra;
    private String dsextvistvetro_ted;
    private String dsextvistvetro_spa;
    private String dsextvistvetro_rus;

    public String getCdvistvetro() {
        String mcd = "";

        mcd += (getCol().getCdvistcolv() != null) ? getCol().getCdvistcolv() : "";
        /*
         * mcd += "|"; mcd +=
         * (getFin().getCdvistfinv()!=null)?getFin().getCdvistfinv():"";
         */

        return mcd;
    }

    public void setCdvistvetro(String cdvistvetro) {
        this.cdvistvetro = cdvistvetro;
    }

    public String getDsvistvetro() {

        String mds = "";

        mds += (getCol().getDsvistcolv() != null) ? getCol().getDsvistcolv() : "";
        // mds += (getFin().getDsvistfinv()!=null)?getFin().getDsvistfinv():"";

        return mds;
    }

    public void setDsvistvetro(String dsvistvetro) {
        this.dsvistvetro = dsvistvetro;
    }

    // public String getCdvistvetro() {
    //
    // String mcd = "";
    //
    // mcd += (getCol().getCdvistcolv()!=null)?getCol().getCdvistcolv():"";
    // mcd += "|";
    // mcd += (getFin().getCdvistfinv()!=null)?getFin().getCdvistfinv():"";
    //
    // return mcd;
    // }
    //
    // public void setCdvistvetro(String cdvistvetro) {
    // this.cdvistvetro = cdvistvetro;
    // }
    //
    // public String getDsvistvetro() {
    //
    // String mds = "";
    //
    // mds += (getCol().getDsvistcolv()!=null)?getCol().getDsvistcolv()+" ":"";
    // mds += (getFin().getDsvistfinv()!=null)?getFin().getDsvistfinv():"";
    //
    // this.dsvistvetro = mds;
    // return dsvistvetro;
    // }
    //
    // public void setDsvistvetro(String dsvistvetro) {
    // this.dsvistvetro = dsvistvetro;
    // }

    public String toString() {
        // return this.toString();
        return ToStringBuilder.reflectionToString(this,
                /* ToStringStyle.DEFAULT_STYLE); */
                ToStringStyle.MULTI_LINE_STYLE);
    }

    public String getDsextvistvetro() {
        String mds = "";

        mds += (getCol().getDsextvistcolv() != null) ? getCol().getDsextvistcolv() : "";
        // mds += (getFin().getDsextvistfinv()!=null)?getFin().getDsextvistfinv():"";

        return mds;
    }

    public void setDsextvistvetro(String dsextvistvetro) {
        this.dsextvistvetro = dsextvistvetro;
    }

    public String getDsextvistvetro_eng() {
        String mds = "";

        mds += (getCol().getDsextvistcolv_eng() != null) ? getCol().getDsextvistcolv_eng() : "";
        // mds +=
        // (getFin().getDsextvistfinv_eng()!=null)?getFin().getDsextvistfinv_eng():"";

        return mds;
    }

    public void setDsextvistvetro_eng(String dsextvistvetro_eng) {
        this.dsextvistvetro_eng = dsextvistvetro_eng;
    }

    public String getDsextvistvetro_fra() {
        String mds = "";

        mds += (getCol().getDsextvistcolv_fra() != null) ? getCol().getDsextvistcolv_fra() : "";
        // mds +=
        // (getFin().getDsextvistfinv_fra()!=null)?getFin().getDsextvistfinv_fra():"";

        return mds;
    }

    public void setDsextvistvetro_fra(String dsextvistvetro_fra) {
        this.dsextvistvetro_fra = dsextvistvetro_fra;
    }

    public String getDsextvistvetro_spa() {
        String mds = "";

        mds += (getCol().getDsextvistcolv_spa() != null) ? getCol().getDsextvistcolv_spa() : "";
        // mds +=
        // (getFin().getDsextvistfinv_spa()!=null)?getFin().getDsextvistfinv_spa():"";

        return mds;
    }

    public void setDsextvistvetro_spa(String dsextvistvetro_spa) {
        this.dsextvistvetro_spa = dsextvistvetro_spa;
    }

    public String getDsextvistvetro_ted() {
        String mds = "";

        mds += (getCol().getDsextvistcolv_ted() != null) ? getCol().getDsextvistcolv_ted() : "";
        // mds +=
        // (getFin().getDsextvistfinv_ted()!=null)?getFin().getDsextvistfinv_ted():"";

        return mds;
    }

    public void setDsextvistvetro_ted(String dsextvistvetro_ted) {
        this.dsextvistvetro_ted = dsextvistvetro_ted;
    }

    public String getDsextvistvetro_rus() {
        String mds = "";

        mds += (getCol().getDsextvistcolv_rus() != null) ? getCol().getDsextvistcolv_rus() : "";
        // mds +=
        // (getFin().getDsextvistfinv_rus()!=null)?getFin().getDsextvistfinv_rus():"";

        if (StringUtils.isNotBlank(mds))
            return mds;
        else
            return getDsextvistvetro_eng();
    }

    public void setDsextvistvetro_rus(String dsextvistvetro_rus) {
        this.dsextvistvetro_rus = dsextvistvetro_rus;
    }

}
