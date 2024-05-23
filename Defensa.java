/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krippulo.finalfight;

/**
 *
 * @author krip
 */
Defensa {
    public static final double ESQUIVA_MINIMA=0.1;
    public static final double ESQUIVA_MAXIMA=0.9;
    public static final double PARADA_MINIMA=0;
    public static final double PARADA_MAXIMA=0.9;
    
    esquivar(int energiaDefensor, Arma armaDefensiva) {
        double probabilidad=armaDefensiva.esquive*energiaDefensor/100;
        probabilidad=Math.max(ESQUIVA_MINIMA, probabilidad);
        probabilidad=Math.min(ESQUIVA_MAXIMA, probabilidad);
        if(Math.random()<probabilidad){
            return true;
        }else{
            return false;
        }
    }
    
    parar(int fuerzaAtaque, int energiaDefensor, Arma armaDefensiva){
        double porcentajeDefensa=armaDefensiva.parada*energiaDefensor/100;
        porcentajeDefensa=Math.max(PARADA_MINIMA, porcentajeDefensa);
        porcentajeDefensa=Math.min(porcentajeDefensa, PARADA_MAXIMA);
        int danio=(int)(fuerzaAtaque*(1-porcentajeDefensa));
        return danio;
    }
}
