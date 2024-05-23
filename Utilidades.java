/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krippulo.finalfight;

import java.util.Scanner;

/**
 *
 * @author krip
 */
public class Utilidades {
    private static final Scanner sc=new Scanner(System.in);
    
    static int pideInt() {//Para que sólo acepte enteros
        int i = 0;
        boolean valido = false;
        while (!valido) {
            if (sc.hasNextInt()) {
                i = sc.nextInt();
                sc.nextLine();
                valido = true;
            } else {
                sc.nextLine();
            }
        }
        return i;
    }
}
