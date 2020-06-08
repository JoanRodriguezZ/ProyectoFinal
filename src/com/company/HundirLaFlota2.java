package com.company;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class HundirLaFlota2 {
    public int turno;
    public Tablero tablero = new Tablero();
    public int ganador = 0;

    public void start() {
        turno = 0;

        tablero.colocarNaves();

        imprimirCampoBatalla();
    }

    public void combate() {
        ++turno;
        System.out.println("TURNO " + turno);

        Scanner scanner = new Scanner(System.in);
        int disparoX = -1;
        int disparoY = -1;
        boolean posicionValida = false;

        //turno J1
        while(posicionValida == false) {
            System.out.print("Introduce la coordenada vertical: ");
            disparoX = scanner.nextInt();
            System.out.print("Introduce la coordenada horizontal: ");
            disparoY = scanner.nextInt();
            System.out.println();

            if (disparoX < 0 || disparoX > (tablero.tamanyo-1) || disparoY < 0 || disparoY > (tablero.tamanyo-1)){
                System.out.println("Posicion invalida, por favor introduce una posicion válida.");
            } else {
                posicionValida = true;
            }
        }

        if ((disparoX >= 0 && disparoX < tablero.tamanyo) && (disparoY >= 0 && disparoY < tablero.tamanyo)) {
            if (tablero.casillas[disparoX][disparoY].contenido.equals("H")) {
                System.out.println("J1: Tocado y hundido!");
                tablero.casillas[disparoX][disparoY].contenido = "!";
                --tablero.naves[1];
            } else if (tablero.casillas[disparoX][disparoY].contenido.equals("0")) {
                System.out.println("J1: Has hundido tu propio barco!");
                tablero.casillas[disparoX][disparoY].contenido = "x";
                --tablero.naves[0];
            } else if (tablero.casillas[disparoX][disparoY].contenido.equals(" ") || tablero.casillas[disparoX][disparoY].contenido.equals("/")) {
                System.out.println("J1: Has fallado...");
                tablero.casillas[disparoX][disparoY].contenido = "-";
            } else if (tablero.casillas[disparoX][disparoY].contenido.equals("-")) {
                System.out.println("J1: Ya habias fallado anteriormente ahí...");
            } else if (tablero.casillas[disparoX][disparoY].contenido.equals("!") || tablero.casillas[disparoX][disparoY].contenido.equals("x")) {
                System.out.println("J1: Ese barco ya está hundido...");
            }
        }

        //turno J2
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        disparoX = (int) (Math.random() * tablero.tamanyo-1);
        disparoY = (int) (Math.random() * tablero.tamanyo-1);

        if ((disparoX >= 0 && disparoX < tablero.tamanyo) && (disparoY >= 0 && disparoY < tablero.tamanyo)) {
            if (tablero.casillas[disparoX][disparoY].contenido.equals("0")) {
                System.out.println("El enemigo a hundido una de tus naves!");
                tablero.casillas[disparoX][disparoY].contenido = "x";
                --tablero.naves[0];
            } else if (tablero.casillas[disparoX][disparoY].contenido.equals("H")) {
                System.out.println("El enemigo ha hundido su propia nave!");
                tablero.casillas[disparoX][disparoY].contenido = "!";
                --tablero.naves[1];
            } else if (tablero.casillas[disparoX][disparoY].contenido.equals(" ") || tablero.casillas[disparoX][disparoY].contenido.equals("-") || tablero.casillas[disparoX][disparoY].equals("/")) {
                System.out.println("El enemigo ha fallado...");
                tablero.casillas[disparoX][disparoY].contenido = "/";
            } else if (tablero.casillas[disparoX][disparoY].contenido.equals("!") || tablero.casillas[disparoX][disparoY].contenido.equals("x")) {
                System.out.println("El enemigo ha disparado a una nave ya hundida...");
            }
        }

        imprimirCampoBatalla();
        tablero.mostrarNavesRestantes();
        ganador = tablero.jugadorGanador();

        gestionarFinPartida();
    }

    public void gestionarFinPartida() {
        if (ganador != 0){
            Scanner scanner = new Scanner(System.in);
            if (ganador == 1){
                System.out.println("Has ganado!");
                } else if (ganador == 2){
                System.out.println("Has perdido.");
                }
            System.out.println();
            System.out.println("Quieres jugar otra vez?");
            System.out.println("Y: si | N: no");
            String comprobar = Character.toString(scanner.next().charAt(0));
            if (comprobar.equals("Y") || comprobar.equals("y")){
                ganador = 0;
                tablero.vaciarCasillas();
                start();
            }
        }
    }

    public void imprimirCampoBatalla() {
        System.out.println();
        System.out.print("  ");
        for (int i = 0; i < tablero.tamanyo; i++) {
            System.out.print(i);
        }
        System.out.println();
        for (int i = 0; i < tablero.tamanyo; i++) {
            System.out.print(i + "|");

            for (int j = 0; j < tablero.tamanyo; j++) {
                System.out.print(tablero.casillas[i][j].contenido);
            }
            System.out.println("|");
        }
        System.out.print("  ");
        for (int i = 0; i < tablero.tamanyo; i++) {
            System.out.print("¯");
        }
        System.out.println();
    }

    public void mostrarTitulo() {
        System.out.println(" __   __  __   __  __    _  ______   ___  ______      ___      _______    _______  ___      _______  _______  _______ ");
        System.out.println("|  | |  ||  | |  ||  |  | ||      | |   ||    _ |    |   |    |   _   |  |       ||   |    |       ||       ||   _   |");
        System.out.println("|  |_|  ||  | |  ||   |_| ||  _    ||   ||   | ||    |   |    |  |_|  |  |    ___||   |    |   _   ||_     _||  |_|  |");
        System.out.println("|       ||  |_|  ||       || | |   ||   ||   |_||_   |   |    |       |  |   |___ |   |    |  | |  |  |   |  |       |");
        System.out.println("|       ||       ||  _    || |_|   ||   ||    __  |  |   |___ |       |  |    ___||   |___ |  |_|  |  |   |  |       |");
        System.out.println("|   _   ||       || | |   ||       ||   ||   |  | |  |       ||   _   |  |   |    |       ||       |  |   |  |   _   |");
        System.out.println("|__| |__||_______||_|  |__||______| |___||___|  |_|  |_______||__| |__|  |___|    |_______||_______|  |___|  |__| |__|");
        System.out.println();
        System.out.println("By Joan Rodriguez");
    }
}

