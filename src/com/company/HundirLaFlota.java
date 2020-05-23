package com.company;

import java.util.*;

class HundirLaFlota {
    public static Scanner scanner = new Scanner(System.in);
    public static int tamanyo = 10;
    public static String[][] tablero = new String[tamanyo][tamanyo];
    public static int turno;
    public static int barcos;
    public static int navesJ1;
    public static int navesJ2;

    public static void start() {
        turno = 0;

        tablero();

        cuantasNaves();
        colocarNavesJ1();
        colocarNavesJ2();

        for (;navesJ1 !=0 && navesJ2 != 0;) {
            combate();
        }

        fin();
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
        barcos = scanner.nextInt();
        navesJ1 = barcos;
        navesJ2 = barcos;
    }

    private static void colocarNavesJ1() {
        System.out.println();
        for (int i = 1; i <= navesJ1;) {
            int x = (int) (Math.random() * 10);
            int y = (int) (Math.random() * 10);

            if ((x >= 0 && x < tamanyo) && (y >= 0 && y < tamanyo) && (tablero[x][y] == " ")) {
                tablero[x][y] = "0";
                i++;
            }
        }
    }

    private static void colocarNavesJ2() {
        System.out.println();
        System.out.println("Las naves del Jugador 1 estan representadas con 0.");
        System.out.println("Las naves del Jugador 2 estan representadas con H.");
        for (int i = 1; i <= navesJ2;) {
            int x = (int) (Math.random() * 10);
            int y = (int) (Math.random() * 10);

            if ((x >= 0 && x < tamanyo) && (y >= 0 && y < tamanyo) && (tablero[x][y] == " ")) {
                tablero[x][y] = "H";
                i++;
            }
        }
        imprimirCampoBatalla();
        System.out.println("Empieza el combate!");
        System.out.println();
    }

    private static void combate() {
        ++turno;

        turnoJ1();
        turnoJ2();

        imprimirCampoBatalla();

        System.out.println();
        System.out.println("Naves restantes del J1: " + navesJ1 + ". Naves restantes del J2: " + navesJ2);
        System.out.println();
    }

    private static void turnoJ1() {
        System.out.println("TURNO " + turno);
        int x = -1;
        int y = -1;
        for (;(x < 0 || x >= tamanyo) || (y < 0 || y >= tamanyo);) {
            System.out.print("Introduce la coordenada X (horizontal): ");
            y = scanner.nextInt();
            System.out.print("Introduce la coordenada Y (vertical): ");
            x = scanner.nextInt();
            System.out.println();

            if ((x >= 0 && x < tamanyo) && (y >= 0 && y < tamanyo)) {
                if (tablero[x][y] == "H") {
                    System.out.println("J1: Tocado y hundido!");
                    tablero[x][y] = "!";
                    --navesJ2;
                } else if (tablero[x][y] == "0") {
                    System.out.println("J1: Has hundido tu propio barco!");
                    tablero[x][y] = "x";
                    --navesJ1;
                } else if (tablero[x][y] == " " || tablero[x][y] == "/") {
                    System.out.println("J1: Has fallado...");
                    tablero[x][y] = "-";
                } else if (tablero[x][y] == "-") {
                    System.out.println("J1: Ya habias fallado anteriormente ahí...");
                } else if (tablero[x][y] == "!" || tablero[x][y] == "x") {
                    System.out.println("J1: Ese barco ya está hundido...");
                }
            } else if ((x < 0 || x >= tamanyo) || (y < 0 || y >= tamanyo)) {
                System.out.println("Te has pasado del tablero, vuelve a introducir los valores.");
            }
        }
    }

    private static void turnoJ2() {
        int x = -1;
        int y = -1;
        for (;(x < 0 || x >= tamanyo) || (y < 0 || y >= tamanyo);) {
            x = (int) (Math.random() * 10);
            y = (int) (Math.random() * 10);

            if ((x >= 0 && x < tamanyo) && (y >= 0 && y < tamanyo)) {
                if (tablero[x][y] == "0") {
                    System.out.println("J2: Tocado y hundido!");
                    tablero[x][y] = "x";
                    --navesJ1;
                } else if (tablero[x][y] == "H") {
                    System.out.println("J2: Has hundido tu propio barco!");
                    tablero[x][y] = "!";
                } else if (tablero[x][y] == " " | tablero[x][y] == "-") {
                    System.out.println("J2: Has fallado...");
                    tablero[x][y] = "/";
                } else if (tablero[x][y] == "/") {
                    System.out.println("J2: Ya habias fallado anteriormente ahí...");
                } else if (tablero[x][y] == "!" || tablero[x][y] == "x") {
                    System.out.println("J2: Ese barco ya está hundido...");
                }
            }
        }
    }

    public static void fin() {
        if (navesJ1 > 0 && navesJ2 <= 0)
            System.out.println("El Jugador 1 ha ganado en " + turno + " turnos con " + navesJ1 + " naves!");
        else
            System.out.println("El Jugador 2 ha ganado en " + turno + " turnos con " + navesJ2 + " naves!");
    }

    private static void imprimirCampoBatalla() {
        System.out.println();
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