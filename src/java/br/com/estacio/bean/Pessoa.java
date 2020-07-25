/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.estacio.bean;

/**
 *
 * @author Camilla Bim <camillabim@hotmail.com.br>
 */
public class Pessoa {
    
    private Integer codigo;

    public Pessoa() {
        
    }

    public Pessoa(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

   
    
}
