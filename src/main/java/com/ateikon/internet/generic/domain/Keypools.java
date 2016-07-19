package com.ateikon.internet.generic.domain;

import java.io.Serializable;

public class Keypools extends BaseTableBean implements Serializable {
    
    /* Private Fields */
    
    private String name;
    private int nextId;
    
    /**
     * Holds value of property cdagen.
     */
    private String cdagen;
    
    /**
     * Holds value of property init1.
     */
    private Long init1;
    
    /**
     * Holds value of property fine1.
     */
    private Long fine1;
    
    /**
     * Holds value of property init2.
     */
    private Long init2;
    
    /**
     * Holds value of property fine2.
     */
    private Long fine2;
    
    /* Constructors */
    
    public Keypools() {
    }
    
    public Keypools(String name, String cddipa, String cdazie, String cdagen) {
        this.name = name;
        this.setCddipa(cddipa);
        this.setCdazie(cdazie);
        if(cdagen!=null) this.setCdagen(cdagen);
    }    
    
    public Keypools(String name, String cddipa, String cdagen) {
        this.name = name;
        this.setCddipa(cddipa);
        this.setCdagen(cdagen);
        //this.nextId = nextId;
    }
    
    public Keypools(String name, String cdagen) {
        this.name = name;
        this.setCdagen(cdagen);
    }    
    
    /* JavaBeans Properties */
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public int getNextId() { return nextId; }
    public void setNextId(int nextId) { this.nextId = nextId; }
    
    /**
     * Getter for property cdagen.
     * @return Value of property cdagen.
     */
    public String getCdagen() {
        
        return this.cdagen;
    }
    
    /**
     * Setter for property cdagen.
     * @param cdagen New value of property cdagen.
     */
    public void setCdagen(String cdagen) {
        
        this.cdagen = cdagen;
    }
    
    /**
     * Getter for property init1.
     * @return Value of property init1.
     */
    public Long getInit1() {
        
        return this.init1;
    }
    
    /**
     * Setter for property init1.
     * @param init1 New value of property init1.
     */
    public void setInit1(Long init1) {
        
        this.init1 = init1;
    }
    
    /**
     * Getter for property fine1.
     * @return Value of property fine1.
     */
    public Long getFine1() {
        
        return this.fine1;
    }
    
    /**
     * Setter for property fine1.
     * @param fine1 New value of property fine1.
     */
    public void setFine1(Long fine1) {
        
        this.fine1 = fine1;
    }
    
    /**
     * Getter for property init2.
     * @return Value of property init2.
     */
    public Long getInit2() {
        
        return this.init2;
    }
    
    /**
     * Setter for property init2.
     * @param init2 New value of property init2.
     */
    public void setInit2(Long init2) {
        
        this.init2 = init2;
    }
    
    /**
     * Getter for property fine2.
     * @return Value of property fine2.
     */
    public Long getFine2() {
        
        return this.fine2;
    }
    
    /**
     * Setter for property fine2.
     * @param fine2 New value of property fine2.
     */
    public void setFine2(Long fine2) {
        
        this.fine2 = fine2;
    }
    
    
    
}
