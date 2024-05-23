/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krippulo.finalfight;

import java.util.Arrays;

/**
 *
 * @author krip
 */
Heroe{
    //Constantes
    private static final int VIDA_INICIAL=200;

    //Propiedades de instancia
    private Arma arma;
    private int bajas;
    
    Heroe() {
        super(nombre, VIDA_INICIAL);
        this.arma=arma;
        bajas=0;
    }

    //------------------------  ACCIONES ---------------------------------------
    public Villano[] ataqueMele(Villano[] villanos){
        Villano[] victimas;//para los que mate
        Villano[] resultado;
        int contador;
        //Calculo cuántos hay 'atacables'
        int atacables=0;
        for(Villano v:villanos){
            if(v==null) break;//por la ordenación que tiene, los null están al final
            if(v.isOculto()) continue;//no puedo atacar a los que están ocultos
            atacables++;
        }
        
        //Si no puedo atacar a nadie, no hago nada
        if(atacables==0){
            decir("¿No hay nadie a quién atacar?");
            return new Villano[0];
        }
        
        decir("¡Probad TODOS mi acero!");
        int fuerza=(int)Math.ceil((double)(arma.ataque*getEnergia())/atacables);//redondeo al alza para compensar las pérdidas por decimales
        victimas=new Villano[atacables];
        contador=0;
        for(Villano v:villanos){
            if(v==null) break;//por la ordenación que tiene, los null están al final
            if(v.isOculto()) continue;//no puedo atacar a los que están ocultos
            if(v.recibirAtaque(fuerza)==1){//Si el ataque tiene resultado de muerte...
                bajas++;//...contabilizo la baja
                setEnergia(getEnergia()+v.getRecompensa());//Me cobro la recompensa
                decir("¡¡¡ +"+v.getRecompensa()+" !!!");
                victimas[contador]=v;
                contador++;
            }
        }
        resultado=Arrays.copyOfRange(victimas, 0, contador);
        return resultado;
    }
    
    @Override
    public void atacar(Personaje p) {
        Villano v=(Villano)p;
        int miEnergia=getEnergia();
        decir("¡Prueba mi acero "+v.getNombre()+"!");
        int fuerza=(int)(arma.ataque*miEnergia);
        if(v.recibirAtaque(fuerza)==1){//Si el ataque tiene resultado de muerte...
            bajas++;//...contabilizo la baja
            setEnergia(miEnergia+v.getRecompensa());//Me cobro la recompensa
            decir("¡¡¡ +"+v.getRecompensa()+" !!!");
        }
    }

    @Override
    public int recibirAtaque(int fuerza) {
        int energiaInicial=getEnergia();
        if(esquivar(energiaInicial,arma)){
            decir("...esquivando...");
            return -1;
        }else{
            int danio=parar(fuerza, energiaInicial, arma);
            int energiaFinal=energiaInicial-danio;
            setEnergia(energiaFinal);
            if (energiaFinal>0){
                decir("¡Oh! (-"+danio+")");
                return 0;
            }else{
                decir("¡NOOOOO! (-"+danio+")");
                return 1;
            }
        }
    }
    
    public void pideArma() {
        int opcion = -1;
        while (opcion < 0 || opcion >= Arma.values().length) {
            System.out.print("Elige un arma ");
            for (int i = 0; i < Arma.values().length; i++) {
                System.out.print(i + ")" + Arma.values()[i] + " ");
            }
            System.out.print(": ");
            opcion = Utilidades.pideInt();
        }
        setArma(Arma.values()[opcion]);
    }

    //----------------------- PRESENTACIÓN ---------------------------------
    @Override
    public String toString() {
        return getNombre()+"("+arma.nombre+"-"+getEnergia()+")";
    }
    
    //---------------------- GETTERS y SETTERS ---------------------------------
    public Arma getArma() {
        return arma;
    }

    public void setArma(Arma arma) {
        this.arma = arma;
    }

    public int getBajas() {
        return bajas;
    }
    
}
