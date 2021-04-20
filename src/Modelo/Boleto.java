/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author a-adr_000
 */
public class Boleto {
    private String IdBoleto;
    private Vendedor v;
    private Funcion f;
    private Categoria c;
    private Compra co;

    public Boleto(String IdBoleto, Vendedor v, Funcion f, Categoria c, Compra co) {
        this.IdBoleto = IdBoleto;
        this.v = v;
        this.f = f;
        this.c = c;
        this.co = co;
    }

    public Vendedor getV() {
        return v;
    }

    public void setV(Vendedor v) {
        this.v = v;
    }

    public Funcion getF() {
        return f;
    }

    public void setF(Funcion f) {
        this.f = f;
    }

    public Categoria getC() {
        return c;
    }

    public void setC(Categoria c) {
        this.c = c;
    }

    public Compra getCo() {
        return co;
    }

    public void setCo(Compra co) {
        this.co = co;
    }
    

    public Boleto() {
    }

    public Boleto(String IdBoleto) {
        this.IdBoleto = IdBoleto;
    }

    public String getIdBoleto() {
        return IdBoleto;
    }

    public void setIdBoleto(String IdBoleto) {
        this.IdBoleto = IdBoleto;
    }
    
    
}
