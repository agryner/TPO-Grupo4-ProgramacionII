package servicios;

import modelo.Esquina;
import interfaces.INodo;

import java.util.*;

public class Dijkstra {

    public static Map<Esquina, Integer> calcularDistancias(Map<Integer, Esquina> nodos, Esquina origen) {
        Map<Esquina, Integer> distancias = new HashMap<>();
        Set<Esquina> visitados = new HashSet<>();
        PriorityQueue<NodoDistancia> cola = new PriorityQueue<>();

        // Inicializo distancias en infinito
        for (Esquina nodo : nodos.values()) {
            distancias.put(nodo, Integer.MAX_VALUE);
        }

        distancias.put(origen, 0);
        cola.add(new NodoDistancia(origen, 0));

        while (!cola.isEmpty()) {
            NodoDistancia actual = cola.poll();
            Esquina nodoActual = actual.nodo;

            if (visitados.contains(nodoActual)) continue;
            visitados.add(nodoActual);

            List<INodo> vecinosGenericos = nodoActual.getVecinos();
            List<Integer> pesos = nodoActual.getPesos();

            for (int i = 0; i < vecinosGenericos.size(); i++) {
                Esquina vecino = (Esquina) vecinosGenericos.get(i); // Cast necesario
                int peso = pesos.get(i);
                int nuevaDistancia = distancias.get(nodoActual) + peso;

                if (nuevaDistancia < distancias.get(vecino)) {
                    distancias.put(vecino, nuevaDistancia);
                    cola.add(new NodoDistancia(vecino, nuevaDistancia));
                }
            }
        }

        return distancias;
    }

    private static class NodoDistancia implements Comparable<NodoDistancia> {
        Esquina nodo;
        int distancia;

        NodoDistancia(Esquina nodo, int distancia) {
            this.nodo = nodo;
            this.distancia = distancia;
        }

        @Override
        public int compareTo(NodoDistancia otro) {
            return Integer.compare(this.distancia, otro.distancia);
        }
    }
}
