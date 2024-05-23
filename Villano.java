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
Villano{
    //Constantes
    public static final int TOPE_ENEMIGOS=4;//para ajustar la dificultad del juego
    private static final int TOPE_BATALLA=2;//para ajustar la dificultad del juego
    private static final double PROB_EXITO_OCULTACION=0.25;//para ajustar la dificultad del juego
    public static final int VIDA_INICIAL=100;//para ajustar la dificultad del juego
    public static final String VOCALES="aeiou";
    public static final String CONSONANTES="bcdfghjklmnpqrstvwxyz";
    
    //Propiedades de clase
    private static int contadorTotal=0;//Siempre indicará el total de enemigos generados
    private static int contadorVivos=0;
   
    //Propiedades de instancia
    private boolean oculto;
    private int id;
    private int recompensa;
    
    public Villano(String inicial,int recompensa) {
        super(generaNombreAleatorio(inicial), VIDA_INICIAL);
        oculto=false;
        id=contadorTotal;
        this.recompensa=recompensa;
        contadorTotal++;
        contadorVivos++;
        decir("Aquí estoy");
    }
    
    
    //------------------------------- ACCIONES ---------------------------------
    public boolean ocultar(){
        //Devuelve true si se ha ocultado con éxito; false en caso contrario
        if(Math.random()<0.25){
            oculto=true;
            decir("¡Cu-cu, no me ves!");
            return true;
        }else{
            oculto=false;
            return false;
        }
    }
    
    public Villano pedirAyuda(){
        if(contadorVivos>=TOPE_BATALLA){
            decir("¡Nosotros "+contadorVivos+" deberíamos bastarnos!");
            return null;//no se llama a nuevas fuerzas
        }else{
            decir("¡AYUDA!");
            return generaVillanoaleatorio();
        }
        
    }
    
    //----------------------- PRESENTACIÓN ---------------------------------
    //Cada uno el suyo
    
    
    
    //---------------------- GETTERS y SETTERS ---------------------------------

    public static int getContadorTotal() {
        return contadorTotal;
    }
    public static void setContadorTotal(int contadorTotal) {
        Villano.contadorTotal = contadorTotal;
    }

    public static int getContadorVivos() {
        return contadorVivos;
    }
    public static void setContadorVivos(int contadorVivos) {
        Villano.contadorVivos = contadorVivos;
    }
    public static void reduceContadorVivos(int cantidad){
        Villano.contadorVivos-=cantidad;
    }

    public int getId() {
        return id;
    }
    
    public boolean isOculto() {
        return oculto;
    }  

    public void setOculto(boolean oculto) {
        this.oculto = oculto;
    }

    public int getRecompensa() {
        return recompensa;
    }
    
    //---------------------- MÉTODOS DE CLASE ----------------------------------
    generaVillanoaleatorio(){
        //Si se ha alcanzado el tope de villanos, ya no se añaden más
        //En caso contrario, se crea un nuevo villano y se devuelve 
        if(contadorTotal>=TOPE_ENEMIGOS){
            return null;
        }else{
            Villano v=(Math.random()<Probabilidad.GUERRERO.valor)?new Guerrero():new Nigromante();
            return v;
        }
    }
    
    generaNombreAleatorio(String inicial){
        StringBuilder nombre=new StringBuilder(inicial);
        nombre.append(vocalAleatoria());
        nombre.append(consonanteAleatoria());
        if(Math.random()<0.5){//por añadir un poco de variedad a la estructura
            nombre.append(vocalAleatoria());
            nombre.append(consonanteAleatoria());
        }else{
            nombre.append(consonanteAleatoria());
            nombre.append(vocalAleatoria());
        }
        return nombre.toString();
    }
    
    vocalAleatoria(){
        int pos=(int)(Math.random()*VOCALES.length());
        return VOCALES.substring(pos,pos+1);
    }
    
    consonanteAleatoria(){
        int pos=(int)(Math.random()*CONSONANTES.length());
        return CONSONANTES.substring(pos,pos+1);
    }
}
