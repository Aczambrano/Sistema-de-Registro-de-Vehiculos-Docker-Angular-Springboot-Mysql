/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmo.pkg1;

/**
 *
 * @author Anderson
 */
public class Algoritmo1 {

   public static int calcularElemento(int X, int Y) {
        int resultado = X;
        int multiplicador = 2; // Inicializamos el multiplicador con el valor de X
        for (int i = 1; i < Y; i++) {
            resultado *= multiplicador; // Multiplicamos el resultado actual por el multiplicador
            multiplicador++; // Incrementamos el multiplicador para la siguiente iteración
        }
        return resultado;
    }

    public static void main(String[] args) {
        int X = 10;
        int Y = 4;
        int resultado = calcularElemento(X, Y);
        System.out.println("Para X = " + X + ", Y = " + Y + ", el resultado es: " + resultado);
    }
    
}
