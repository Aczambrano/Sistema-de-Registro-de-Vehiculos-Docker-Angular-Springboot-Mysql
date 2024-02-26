/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fibonacci;

import java.util.Scanner;

/**
 *
 * @author Anderson
 */
public class Fibonacci {

    // Método para verificar si un número pertenece a la serie de Fibonacci
    public static boolean perteneceASerieFibonacci(int numero) {
        // Inicializamos los primeros dos números de Fibonacci
        int previo = 0;
        int actual = 1;

        // Iteramos para generar números de Fibonacci hasta que alcancemos o superemos el número dado
        while (actual < numero) {
            int siguiente = previo + actual;
            previo = actual;
            actual = siguiente;
        }

        // Si el número dado es igual al número actual, está en la serie de Fibonacci
        return actual == numero;
    }

    public static void main(String[] args) {
        int numero = 13; // Número que queremos verificar si pertenece a la serie de Fibonacci
        boolean resultado = perteneceASerieFibonacci(numero);
        System.out.println("¿El número " + numero + " pertenece a la serie de Fibonacci? " + resultado);
    }
    
}
