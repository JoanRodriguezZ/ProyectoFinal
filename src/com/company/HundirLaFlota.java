package com.company;

import java.util.*;

class HundirLaFlota {
    public static Scanner scanner = new Scanner(System.in);
    public static int tamanyo = 10;
    public static String[][] tablero = new String[tamanyo][tamanyo];
    public static int turno;
    public static int barcos;


    public static void start() {
        turno = 1;

        tablero();
        cuantasNaves();
        colocarNavesJ1();
        colocarNavesJ2();
    }




    public static void tablero() {
        System.out.print("  ");
        for (int i = 0; i < tamanyo; i++) {
            System.out.print(i);
        }
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
        for (int i = 0; i < tamanyo; i++) {
            System.out.print("¯");
        }
        System.out.println();
    }

    private static void cuantasNaves() {
        System.out.println("Con cuantas naves quieres jugar? (Default 5)");
        barcos = 5;
        barcos = scanner.nextInt();
    }

    private static void colocarNavesJ1() {
        System.out.println();
        System.out.println("Ésta es la colocación de tus naves:");
        System.out.println();
        for (int i = 1; i <= barcos; ) {
            int x = (int) (Math.random() * 10);
            int y = (int) (Math.random() * 10);

            if ((x >= 0 && x < tamanyo) && (y >= 0 && y < tamanyo) && (tablero[x][y] == " ")) {
                tablero[x][y] = "0";
                i++;
            }
        }
        imprimirCampoBatalla();
    }

    private static void colocarNavesJ2() {
        System.out.println("Desea ver las naves enemigas? (1 = si, 2 = no)");
        int verNaves = 0;
        for (; verNaves != 1 || verNaves != 2; ) {
            verNaves = scanner.nextInt();
            if (verNaves == 1) {
                for (int i = 1; i <= barcos; ) {
                    int x = (int) (Math.random() * 10);
                    int y = (int) (Math.random() * 10);

                    if ((x >= 0 && x < tamanyo) && (y >= 0 && y < tamanyo) && (tablero[x][y] == " ")) {
                        tablero[x][y] = "H";
                        i++;
                    }
                }
            } else if (verNaves == 2) {
                for (int i = 1; i <= barcos; ) {
                    int x = (int) (Math.random() * 10);
                    int y = (int) (Math.random() * 10);

                    if ((x >= 0 && x < tamanyo) && (y >= 0 && y < tamanyo) && (tablero[x][y] == " ")) {
                        tablero[x][y] = " ";
                        i++;
                    }
                }
            }
            imprimirCampoBatalla();
        }
    }

    private static void imprimirCampoBatalla() {
        System.out.print("  ");
        for (int i = 0; i < tamanyo; i++) {
            System.out.print(i);
        }
        System.out.println();
        for (int i = 0; i < tablero.length; i++) {
            System.out.print(i + "|");

            for (int j = 0; j < tablero[i].length; j++) {
                System.out.print(tablero[i][j]);
            }
            System.out.println("|");
        }
        System.out.print("  ");
        for (int i = 0; i < tamanyo; i++) {
            System.out.print("¯");
        }
        System.out.println();
    }
}
