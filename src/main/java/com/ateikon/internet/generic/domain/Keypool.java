package com.ateikon.internet.generic.domain;

import java.io.Serializable;
import java.util.Date;

public class Keypool implements Serializable {

  /* Private Fields */

  private String name;
  private int nextId;

  /**
   * Holds value of property dtulag.
   */
  private Date dtulag;
  
  /* Constructors */

  public Keypool() {
  }

  public Keypool(String name, int nextId) {
    this.name = name;
    this.nextId = nextId;
    this.dtulag = new Date();
  }

  /* JavaBeans Properties */

  public String getName() { return name; }
  public void setName(String name) { this.name = name; }

  public int getNextId() { return nextId; }
  public void setNextId(int nextId) { this.nextId = nextId; }

  /**
   * Getter for property dtulag.
   * @return Value of property dtulag.
   */
  public Date getDtulag() {
      return this.dtulag;
  }
  
  /**
   * Setter for property dtulag.
   * @param dtulag New value of property dtulag.
   */
  public void setDtulag(Date dtulag) {
      this.dtulag = dtulag;
  }
  
}
