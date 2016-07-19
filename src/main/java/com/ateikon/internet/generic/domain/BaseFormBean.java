/*
 * BaseTableBean.java
 *
 * Created on 8 luglio 2004, 15.58
 */

package com.ateikon.internet.generic.domain;

import java.beans.*;
import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
/**
 * @author Emiliano
 */
public class BaseFormBean extends Object implements Serializable {
    
    public BaseFormBean() {
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this,
                ToStringStyle.MULTI_LINE_STYLE);        
    }
    
    public boolean equals(Object o) {
        return EqualsBuilder.reflectionEquals(this, o);
    }    
}
