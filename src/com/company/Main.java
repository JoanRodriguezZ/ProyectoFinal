package com.company;

public class Main {

    public static void main(String[] args) {
        System.out.println();
        System.out.println("Hola Jugador 1!");
        System.out.println("Bienvenido al juego de Hundir la flota!\n");

        HundirLaFlota hundirLaFlota = new HundirLaFlota();
        hundirLaFlota.start();


    }
}

/*
BIENVENIDO

se colocan las naves del jugador 1 y 2 en el tablero aleatoriamente

empieza bucle
    Que posicion ataca jugador 1? eje x y

    ha dado?
    si = se ha quedado sin naves?
        si = fin del juego
        no = ataca jugador 1
    no = ataca jugador 2 aleatoriamente
acaba bucle



 */