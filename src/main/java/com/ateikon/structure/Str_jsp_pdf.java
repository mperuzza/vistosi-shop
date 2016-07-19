package com.ateikon.structure;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.sql.PreparedStatement;

import java.io.File;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.awt.Color;

import com.ateikon.common.Atk_sql;
import com.ateikon.common.Costanti_comm;


public class Str_jsp_pdf extends Str_jsp{

    public Str_jsp_pdf() {   
    
        // Constructor.

        super();
    }


    




    // *** Properties *** 

    public    float m_top     = 65;
    public    float m_right   = 25;
    public    float m_bottom  = 25;
    public    float m_left    = 25;



    public    String [] arr_titolo   = null;
    public    int []    arr_colspan  = null;

    public    int []    arr_align    = null;
    public    float []  arr_relwidth = null;

    public    int       tot_colonne_head = 0;
    public    int       tot_colonne_dett = 0;
                        
    
    public    int       ii_riga = 0;


    // *** Variabili per Vendite




}


