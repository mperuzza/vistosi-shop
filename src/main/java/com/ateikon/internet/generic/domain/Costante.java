
package com.ateikon.internet.generic.domain;


// custom imports for Marca

// add your custom import statements here
import java.util.Date;


public class Costante extends BaseTableBean implements java.io.Serializable {
	public Costante() {
	}
        /**
         * Getter for property costvalue.
         * @return Value of property costvalue.
         */
        public String getCostvalue() {
            return this.costvalue;
        }        
    
        /**
         * Setter for property costvalue.
         * @param costvalue New value of property costvalue.
         */
        public void setCostvalue(String costvalue) {
            this.costvalue = costvalue;
        }
        
        /**
         * Getter for property tkcost.
         * @return Value of property tkcost.
         */
        public int getTkcost() {
            return this.tkcost;
        }
        
        /**
         * Setter for property tkcost.
         * @param tkcost New value of property tkcost.
         */
        public void setTkcost(int tkcost) {
            this.tkcost = tkcost;
        }
        
        /**
         * Getter for property cdappl.
         * @return Value of property cdappl.
         */
        public String getCdappl() {
            return this.cdappl;
        }
        
        /**
         * Setter for property cdappl.
         * @param cdappl New value of property cdappl.
         */
        public void setCdappl(String cdappl) {
            this.cdappl = cdappl;
        }
        
        /**
         * Getter for property costlabel.
         * @return Value of property costlabel.
         */
        public String getCostlabel() {
            return this.costlabel;
        }
        
        /**
         * Setter for property costlabel.
         * @param costlabel New value of property costlabel.
         */
        public void setCostlabel(String costlabel) {
            this.costlabel = costlabel;
        }
        
        /**
         * Getter for property costname.
         * @return Value of property costname.
         */
        public String getCostname() {
            return this.costname;
        }
        
        /**
         * Setter for property costname.
         * @param costname New value of property costname.
         */
        public void setCostname(String costname) {
            this.costname = costname;
        }
        
        /**
         * Holds value of property costvalue.
         */
        private String costvalue;
        
        /**
         * Holds value of property tkcost.
         */
        private int tkcost;
        
        /**
         * Holds value of property cdappl.
         */
        private String cdappl;
        
        /**
         * Holds value of property costlabel.
         */
        private String costlabel;
        
        /**
         * Holds value of property costname.
         */
        private String costname;
        
}

