package com.company;

import java.util.*;

class HundirLaFlota {
    public static int tamanyo = 10;
    public static String[][] tablero = new String[tamanyo][tamanyo];
    public static int turno;


    public static void start() {
        turno = 1;

        tablero();
        colocarBarcos();
    }

    public static void tablero() {
        System.out.print("  ");
        for (int i = 0; i < tamanyo; i++)
            System.out.print(i);
        System.out.println();
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                tablero[i][j] = " ";
                if (j == 0)
                    System.out.print(i + "|" + tablero[i][j]);
                else if (j == tablero[i].length - 1)
                    System.out.print(tablero[i][j] + "|");
                else
                    System.out.print(tablero[i][j]);
            }
            System.out.println();
        }
        System.out.print("  ");
        for (int i = 0; i < tamanyo; i++)
            System.out.print("¯");
        System.out.println();

    }

    private static void colocarBarcos() {

    }

}