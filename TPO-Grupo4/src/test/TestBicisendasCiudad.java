package test;

import modelo.MapaDeBicisendas;
import modelo.Esquina;
import servicios.Dijkstra;
import java.util.Scanner;
import java.util.Map;

public class TestBicisendasCiudad {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MapaDeBicisendas mapa = new MapaDeBicisendas();

        mapa.agregarNodo(1);
        mapa.agregarNodo(2);
        mapa.agregarNodo(3);
        mapa.agregarNodo(4);
        mapa.agregarNodo(5);

        System.out.println("Ingrese el peso entre 1 y 2:");
        int p12 = scanner.nextInt();
        mapa.agregarArista(1, 2, p12);

        System.out.println("Ingrese el peso entre 2 y 3:");
        int p23 = scanner.nextInt();
        mapa.agregarArista(2, 3, p23);

        System.out.println("Ingrese el peso entre 2 y 4:");
        int p24 = scanner.nextInt();
        mapa.agregarArista(2, 4, p24);
        
        System.out.println("Ingrese el peso entre 1 y 3:");
        int p13 = scanner.nextInt();
        mapa.agregarArista(1, 3, p13);

        System.out.println("Ingrese el peso entre 3 y 4:");
        int p34 = scanner.nextInt();
        mapa.agregarArista(3, 4, p34);

        System.out.println("Ingrese el peso entre 3 y 5:");
        int p35 = scanner.nextInt();
        mapa.agregarArista(3, 5, p35);
        
        System.out.println("Ingrese el peso entre 4 y 5:");
        int p45 = scanner.nextInt();
        mapa.agregarArista(4, 5, p45);
        
        // Calculo distancias desde el inicio
        Esquina origen = mapa.getNodo(1);
        Map<Esquina, Integer> distancias = Dijkstra.calcularDistancias(mapa.getNodos(), origen);

        // Mostrar resultados
        System.out.println("\nDistancias calculadas desde la esquina 1:");
        for (Map.Entry<Esquina, Integer> entry : distancias.entrySet()) {
            System.out.println("Esquina " + entry.getKey().getValor() + " → Distancia: " + entry.getValue());
        }

        scanner.close();
    }
}