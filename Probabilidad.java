/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krippulo.finalfight;

/**
 *
 * @author krip
 * 
 *      ¡¡¡IMPORTANTE!!!
 * Si se quiere cambiar la probabilidad de aparrición de los villanos,
 * se puede hacer aquí. Pero siempre deben sumar 1!!
 */
public enum Probabilidad {
    GUERRERO(0.75), NIGROMANTE(0.25);
    double valor;
    private Probabilidad(double valor) {
        this.valor = valor;
    }
}
