package test;

import modelo.MapaDeBicisendas;
import modelo.Esquina;
import servicios.Dijkstra;

import java.util.Map;

public class TestBicisendasCiudad {

    public static void main(String[] args) {
        MapaDeBicisendas mapa = new MapaDeBicisendas();

        mapa.agregarNodo(1);
        mapa.agregarNodo(2);
        mapa.agregarNodo(3);
        mapa.agregarNodo(4);

        mapa.agregarArista(1, 2, 3);
        mapa.agregarArista(2, 3, 4);
        mapa.agregarArista(1, 3, 10);
        mapa.agregarArista(3, 4, 2);

        Esquina origen = mapa.getNodo(1);
        Map<Esquina, Integer> distancias = Dijkstra.calcularDistancias(mapa.getNodos(), origen);

        boolean test1 = distancias.get(mapa.getNodo(1)) == 0;
        boolean test2 = distancias.get(mapa.getNodo(2)) == 3;
        boolean test3 = distancias.get(mapa.getNodo(3)) == 7;
        boolean test4 = distancias.get(mapa.getNodo(4)) == 9;

        if (test1 && test2 && test3 && test4) {
            System.out.println("Test de bicisendas en la ciudad PASÓ correctamente.");
        } else {
            System.out.println("Test de bicisendas FALLÓ.");
        }

        // Mostrar distancias siempre
        System.out.println("Distancias calculadas desde el nodo origen:");
        for (Map.Entry<Esquina, Integer> entry : distancias.entrySet()) {
            System.out.println("Esquina " + entry.getKey().getValor() + " → Distancia: " + entry.getValue());
        }
    }
}
