package com.ateikon.internet.eprogen.domain.pgmr;

import com.ateikon.internet.generic.domain.BaseTableBean;
import java.util.Date;

public class Utenti extends BaseTableBean {
    /**
     * This field was generated by Apache iBATIS Ibator.
     * This field corresponds to the database column pgmr.utenti.tkutente
     *
     * @ibatorgenerated Wed May 27 11:43:00 CEST 2009
     */
    private Long tkutente;

    /**
     * This field was generated by Apache iBATIS Ibator.
     * This field corresponds to the database column pgmr.utenti.cdutente
     *
     * @ibatorgenerated Wed May 27 11:43:00 CEST 2009
     */
    private Long cdutente;

    /**
     * This field was generated by Apache iBATIS Ibator.
     * This field corresponds to the database column pgmr.utenti.username
     *
     * @ibatorgenerated Wed May 27 11:43:00 CEST 2009
     */
    private String username;

    /**
     * This field was generated by Apache iBATIS Ibator.
     * This field corresponds to the database column pgmr.utenti.password
     *
     * @ibatorgenerated Wed May 27 11:43:00 CEST 2009
     */
    private String password;

    /**
     * This field was generated by Apache iBATIS Ibator.
     * This field corresponds to the database column pgmr.utenti.cdagente
     *
     * @ibatorgenerated Wed May 27 11:43:00 CEST 2009
     */
    private String cdagente;

    /**
     * This field was generated by Apache iBATIS Ibator.
     * This field corresponds to the database column pgmr.utenti.cdcliente
     *
     * @ibatorgenerated Wed May 27 11:43:00 CEST 2009
     */
    private String cdcliente;

    /**
     * This field was generated by Apache iBATIS Ibator.
     * This field corresponds to the database column pgmr.utenti.dtblocco
     *
     * @ibatorgenerated Wed May 27 11:43:00 CEST 2009
     */
    private Date dtblocco;

    /**
     * This field was generated by Apache iBATIS Ibator.
     * This field corresponds to the database column pgmr.utenti.fgblocco
     *
     * @ibatorgenerated Wed May 27 11:43:00 CEST 2009
     */
    private String fgblocco;

    /**
     * This field was generated by Apache iBATIS Ibator.
     * This field corresponds to the database column pgmr.utenti.email
     *
     * @ibatorgenerated Wed May 27 11:43:00 CEST 2009
     */
    private String email;

    /**
     * This field was generated by Apache iBATIS Ibator.
     * This field corresponds to the database column pgmr.utenti.indirizzo
     *
     * @ibatorgenerated Wed May 27 11:43:00 CEST 2009
     */
    private String indirizzo;

    /**
     * This field was generated by Apache iBATIS Ibator.
     * This field corresponds to the database column pgmr.utenti.cap
     *
     * @ibatorgenerated Wed May 27 11:43:00 CEST 2009
     */
    private String cap;

    /**
     * This field was generated by Apache iBATIS Ibator.
     * This field corresponds to the database column pgmr.utenti.comune
     *
     * @ibatorgenerated Wed May 27 11:43:00 CEST 2009
     */
    private String comune;

    /**
     * This field was generated by Apache iBATIS Ibator.
     * This field corresponds to the database column pgmr.utenti.provincia
     *
     * @ibatorgenerated Wed May 27 11:43:00 CEST 2009
     */
    private String provincia;

    /**
     * This field was generated by Apache iBATIS Ibator.
     * This field corresponds to the database column pgmr.utenti.nazione
     *
     * @ibatorgenerated Wed May 27 11:43:00 CEST 2009
     */
    private String nazione;

    /**
     * This field was generated by Apache iBATIS Ibator.
     * This field corresponds to the database column pgmr.utenti.telefono
     *
     * @ibatorgenerated Wed May 27 11:43:00 CEST 2009
     */
    private String telefono;

    /**
     * This field was generated by Apache iBATIS Ibator.
     * This field corresponds to the database column pgmr.utenti.fax
     *
     * @ibatorgenerated Wed May 27 11:43:00 CEST 2009
     */
    private String fax;

    /**
     * This field was generated by Apache iBATIS Ibator.
     * This field corresponds to the database column pgmr.utenti.cellulare
     *
     * @ibatorgenerated Wed May 27 11:43:00 CEST 2009
     */
    private String cellulare;

    /**
     * This field was generated by Apache iBATIS Ibator.
     * This field corresponds to the database column pgmr.utenti.skypeid
     *
     * @ibatorgenerated Wed May 27 11:43:00 CEST 2009
     */
    private String skypeid;

    /**
     * This field was generated by Apache iBATIS Ibator.
     * This field corresponds to the database column pgmr.utenti.cdgruppo
     *
     * @ibatorgenerated Wed May 27 11:43:00 CEST 2009
     */
    private String cdgruppo;

    /**
     * This field was generated by Apache iBATIS Ibator.
     * This field corresponds to the database column pgmr.utenti.dsutente
     *
     * @ibatorgenerated Wed May 27 11:43:00 CEST 2009
     */
    private String dsutente;

    /**
     * This field was generated by Apache iBATIS Ibator.
     * This field corresponds to the database column pgmr.utenti.ragsoc
     *
     * @ibatorgenerated Wed May 27 11:43:00 CEST 2009
     */
    private String ragsoc;

    /**
     * This field was generated by Apache iBATIS Ibator.
     * This field corresponds to the database column pgmr.utenti.dtultsync
     *
     * @ibatorgenerated Wed May 27 11:43:00 CEST 2009
     */
    private Date dtultsync;

    /**
     * This field was generated by Apache iBATIS Ibator.
     * This field corresponds to the database column pgmr.utenti.cdprofilo
     *
     * @ibatorgenerated Wed May 27 11:43:00 CEST 2009
     */
    private Long cdprofilo;

    /**
     * This field was generated by Apache iBATIS Ibator.
     * This field corresponds to the database column pgmr.utenti.fg_pub
     *
     * @ibatorgenerated Wed May 27 11:43:00 CEST 2009
     */
    private String fg_pub;

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method returns the value of the database column pgmr.utenti.tkutente
     *
     * @return the value of pgmr.utenti.tkutente
     *
     * @ibatorgenerated Wed May 27 11:43:00 CEST 2009
     */
    public Long getTkutente() {
        return tkutente;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method sets the value of the database column pgmr.utenti.tkutente
     *
     * @param tkutente the value for pgmr.utenti.tkutente
     *
     * @ibatorgenerated Wed May 27 11:43:00 CEST 2009
     */
    public void setTkutente(Long tkutente) {
        this.tkutente = tkutente;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method returns the value of the database column pgmr.utenti.cdutente
     *
     * @return the value of pgmr.utenti.cdutente
     *
     * @ibatorgenerated Wed May 27 11:43:00 CEST 2009
     */
    public Long getCdutente() {
        return cdutente;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method sets the value of the database column pgmr.utenti.cdutente
     *
     * @param cdutente the value for pgmr.utenti.cdutente
     *
     * @ibatorgenerated Wed May 27 11:43:00 CEST 2009
     */
    public void setCdutente(Long cdutente) {
        this.cdutente = cdutente;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method returns the value of the database column pgmr.utenti.username
     *
     * @return the value of pgmr.utenti.username
     *
     * @ibatorgenerated Wed May 27 11:43:00 CEST 2009
     */
    public String getUsername() {
        return username;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method sets the value of the database column pgmr.utenti.username
     *
     * @param username the value for pgmr.utenti.username
     *
     * @ibatorgenerated Wed May 27 11:43:00 CEST 2009
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method returns the value of the database column pgmr.utenti.password
     *
     * @return the value of pgmr.utenti.password
     *
     * @ibatorgenerated Wed May 27 11:43:00 CEST 2009
     */
    public String getPassword() {
        return password;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method sets the value of the database column pgmr.utenti.password
     *
     * @param password the value for pgmr.utenti.password
     *
     * @ibatorgenerated Wed May 27 11:43:00 CEST 2009
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method returns the value of the database column pgmr.utenti.cdagente
     *
     * @return the value of pgmr.utenti.cdagente
     *
     * @ibatorgenerated Wed May 27 11:43:00 CEST 2009
     */
    public String getCdagente() {
        return cdagente;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method sets the value of the database column pgmr.utenti.cdagente
     *
     * @param cdagente the value for pgmr.utenti.cdagente
     *
     * @ibatorgenerated Wed May 27 11:43:00 CEST 2009
     */
    public void setCdagente(String cdagente) {
        this.cdagente = cdagente == null ? null : cdagente.trim();
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method returns the value of the database column pgmr.utenti.cdcliente
     *
     * @return the value of pgmr.utenti.cdcliente
     *
     * @ibatorgenerated Wed May 27 11:43:00 CEST 2009
     */
    public String getCdcliente() {
        return cdcliente;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method sets the value of the database column pgmr.utenti.cdcliente
     *
     * @param cdcliente the value for pgmr.utenti.cdcliente
     *
     * @ibatorgenerated Wed May 27 11:43:00 CEST 2009
     */
    public void setCdcliente(String cdcliente) {
        this.cdcliente = cdcliente == null ? null : cdcliente.trim();
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method returns the value of the database column pgmr.utenti.dtblocco
     *
     * @return the value of pgmr.utenti.dtblocco
     *
     * @ibatorgenerated Wed May 27 11:43:00 CEST 2009
     */
    public Date getDtblocco() {
        return dtblocco;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method sets the value of the database column pgmr.utenti.dtblocco
     *
     * @param dtblocco the value for pgmr.utenti.dtblocco
     *
     * @ibatorgenerated Wed May 27 11:43:00 CEST 2009
     */
    public void setDtblocco(Date dtblocco) {
        this.dtblocco = dtblocco;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method returns the value of the database column pgmr.utenti.fgblocco
     *
     * @return the value of pgmr.utenti.fgblocco
     *
     * @ibatorgenerated Wed May 27 11:43:00 CEST 2009
     */
    public String getFgblocco() {
        return fgblocco;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method sets the value of the database column pgmr.utenti.fgblocco
     *
     * @param fgblocco the value for pgmr.utenti.fgblocco
     *
     * @ibatorgenerated Wed May 27 11:43:00 CEST 2009
     */
    public void setFgblocco(String fgblocco) {
        this.fgblocco = fgblocco == null ? null : fgblocco.trim();
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method returns the value of the database column pgmr.utenti.email
     *
     * @return the value of pgmr.utenti.email
     *
     * @ibatorgenerated Wed May 27 11:43:00 CEST 2009
     */
    public String getEmail() {
        return email;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method sets the value of the database column pgmr.utenti.email
     *
     * @param email the value for pgmr.utenti.email
     *
     * @ibatorgenerated Wed May 27 11:43:00 CEST 2009
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method returns the value of the database column pgmr.utenti.indirizzo
     *
     * @return the value of pgmr.utenti.indirizzo
     *
     * @ibatorgenerated Wed May 27 11:43:00 CEST 2009
     */
    public String getIndirizzo() {
        return indirizzo;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method sets the value of the database column pgmr.utenti.indirizzo
     *
     * @param indirizzo the value for pgmr.utenti.indirizzo
     *
     * @ibatorgenerated Wed May 27 11:43:00 CEST 2009
     */
    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo == null ? null : indirizzo.trim();
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method returns the value of the database column pgmr.utenti.cap
     *
     * @return the value of pgmr.utenti.cap
     *
     * @ibatorgenerated Wed May 27 11:43:00 CEST 2009
     */
    public String getCap() {
        return cap;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method sets the value of the database column pgmr.utenti.cap
     *
     * @param cap the value for pgmr.utenti.cap
     *
     * @ibatorgenerated Wed May 27 11:43:00 CEST 2009
     */
    public void setCap(String cap) {
        this.cap = cap == null ? null : cap.trim();
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method returns the value of the database column pgmr.utenti.comune
     *
     * @return the value of pgmr.utenti.comune
     *
     * @ibatorgenerated Wed May 27 11:43:00 CEST 2009
     */
    public String getComune() {
        return comune;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method sets the value of the database column pgmr.utenti.comune
     *
     * @param comune the value for pgmr.utenti.comune
     *
     * @ibatorgenerated Wed May 27 11:43:00 CEST 2009
     */
    public void setComune(String comune) {
        this.comune = comune == null ? null : comune.trim();
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method returns the value of the database column pgmr.utenti.provincia
     *
     * @return the value of pgmr.utenti.provincia
     *
     * @ibatorgenerated Wed May 27 11:43:00 CEST 2009
     */
    public String getProvincia() {
        return provincia;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method sets the value of the database column pgmr.utenti.provincia
     *
     * @param provincia the value for pgmr.utenti.provincia
     *
     * @ibatorgenerated Wed May 27 11:43:00 CEST 2009
     */
    public void setProvincia(String provincia) {
        this.provincia = provincia == null ? null : provincia.trim();
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method returns the value of the database column pgmr.utenti.nazione
     *
     * @return the value of pgmr.utenti.nazione
     *
     * @ibatorgenerated Wed May 27 11:43:00 CEST 2009
     */
    public String getNazione() {
        return nazione;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method sets the value of the database column pgmr.utenti.nazione
     *
     * @param nazione the value for pgmr.utenti.nazione
     *
     * @ibatorgenerated Wed May 27 11:43:00 CEST 2009
     */
    public void setNazione(String nazione) {
        this.nazione = nazione == null ? null : nazione.trim();
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method returns the value of the database column pgmr.utenti.telefono
     *
     * @return the value of pgmr.utenti.telefono
     *
     * @ibatorgenerated Wed May 27 11:43:00 CEST 2009
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method sets the value of the database column pgmr.utenti.telefono
     *
     * @param telefono the value for pgmr.utenti.telefono
     *
     * @ibatorgenerated Wed May 27 11:43:00 CEST 2009
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono == null ? null : telefono.trim();
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method returns the value of the database column pgmr.utenti.fax
     *
     * @return the value of pgmr.utenti.fax
     *
     * @ibatorgenerated Wed May 27 11:43:00 CEST 2009
     */
    public String getFax() {
        return fax;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method sets the value of the database column pgmr.utenti.fax
     *
     * @param fax the value for pgmr.utenti.fax
     *
     * @ibatorgenerated Wed May 27 11:43:00 CEST 2009
     */
    public void setFax(String fax) {
        this.fax = fax == null ? null : fax.trim();
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method returns the value of the database column pgmr.utenti.cellulare
     *
     * @return the value of pgmr.utenti.cellulare
     *
     * @ibatorgenerated Wed May 27 11:43:00 CEST 2009
     */
    public String getCellulare() {
        return cellulare;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method sets the value of the database column pgmr.utenti.cellulare
     *
     * @param cellulare the value for pgmr.utenti.cellulare
     *
     * @ibatorgenerated Wed May 27 11:43:00 CEST 2009
     */
    public void setCellulare(String cellulare) {
        this.cellulare = cellulare == null ? null : cellulare.trim();
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method returns the value of the database column pgmr.utenti.skypeid
     *
     * @return the value of pgmr.utenti.skypeid
     *
     * @ibatorgenerated Wed May 27 11:43:00 CEST 2009
     */
    public String getSkypeid() {
        return skypeid;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method sets the value of the database column pgmr.utenti.skypeid
     *
     * @param skypeid the value for pgmr.utenti.skypeid
     *
     * @ibatorgenerated Wed May 27 11:43:00 CEST 2009
     */
    public void setSkypeid(String skypeid) {
        this.skypeid = skypeid == null ? null : skypeid.trim();
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method returns the value of the database column pgmr.utenti.cdgruppo
     *
     * @return the value of pgmr.utenti.cdgruppo
     *
     * @ibatorgenerated Wed May 27 11:43:00 CEST 2009
     */
    public String getCdgruppo() {
        return cdgruppo;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method sets the value of the database column pgmr.utenti.cdgruppo
     *
     * @param cdgruppo the value for pgmr.utenti.cdgruppo
     *
     * @ibatorgenerated Wed May 27 11:43:00 CEST 2009
     */
    public void setCdgruppo(String cdgruppo) {
        this.cdgruppo = cdgruppo == null ? null : cdgruppo.trim();
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method returns the value of the database column pgmr.utenti.dsutente
     *
     * @return the value of pgmr.utenti.dsutente
     *
     * @ibatorgenerated Wed May 27 11:43:00 CEST 2009
     */
    public String getDsutente() {
        return dsutente;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method sets the value of the database column pgmr.utenti.dsutente
     *
     * @param dsutente the value for pgmr.utenti.dsutente
     *
     * @ibatorgenerated Wed May 27 11:43:00 CEST 2009
     */
    public void setDsutente(String dsutente) {
        this.dsutente = dsutente == null ? null : dsutente.trim();
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method returns the value of the database column pgmr.utenti.ragsoc
     *
     * @return the value of pgmr.utenti.ragsoc
     *
     * @ibatorgenerated Wed May 27 11:43:00 CEST 2009
     */
    public String getRagsoc() {
        return ragsoc;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method sets the value of the database column pgmr.utenti.ragsoc
     *
     * @param ragsoc the value for pgmr.utenti.ragsoc
     *
     * @ibatorgenerated Wed May 27 11:43:00 CEST 2009
     */
    public void setRagsoc(String ragsoc) {
        this.ragsoc = ragsoc == null ? null : ragsoc.trim();
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method returns the value of the database column pgmr.utenti.dtultsync
     *
     * @return the value of pgmr.utenti.dtultsync
     *
     * @ibatorgenerated Wed May 27 11:43:00 CEST 2009
     */
    public Date getDtultsync() {
        return dtultsync;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method sets the value of the database column pgmr.utenti.dtultsync
     *
     * @param dtultsync the value for pgmr.utenti.dtultsync
     *
     * @ibatorgenerated Wed May 27 11:43:00 CEST 2009
     */
    public void setDtultsync(Date dtultsync) {
        this.dtultsync = dtultsync;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method returns the value of the database column pgmr.utenti.cdprofilo
     *
     * @return the value of pgmr.utenti.cdprofilo
     *
     * @ibatorgenerated Wed May 27 11:43:00 CEST 2009
     */
    public Long getCdprofilo() {
        return cdprofilo;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method sets the value of the database column pgmr.utenti.cdprofilo
     *
     * @param cdprofilo the value for pgmr.utenti.cdprofilo
     *
     * @ibatorgenerated Wed May 27 11:43:00 CEST 2009
     */
    public void setCdprofilo(Long cdprofilo) {
        this.cdprofilo = cdprofilo;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method returns the value of the database column pgmr.utenti.fg_pub
     *
     * @return the value of pgmr.utenti.fg_pub
     *
     * @ibatorgenerated Wed May 27 11:43:00 CEST 2009
     */
    public String getFg_pub() {
        return fg_pub;
    }

    /**
     * This method was generated by Apache iBATIS Ibator.
     * This method sets the value of the database column pgmr.utenti.fg_pub
     *
     * @param fg_pub the value for pgmr.utenti.fg_pub
     *
     * @ibatorgenerated Wed May 27 11:43:00 CEST 2009
     */
    public void setFg_pub(String fg_pub) {
        this.fg_pub = fg_pub == null ? null : fg_pub.trim();
    }
}