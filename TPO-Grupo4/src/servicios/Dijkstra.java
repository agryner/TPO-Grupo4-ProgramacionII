package servicios;

import modelo.Esquina;
import interfaces.INodo;

import java.util.*;

public class Dijkstra {

    public static Map<Esquina, Integer> calcularDistancias(Map<Integer, Esquina> nodos, Esquina origen) {
        Map<Esquina, Integer> distancia = new HashMap<>();
        Map<Esquina, Esquina> padre = new HashMap<>();
        Set<Esquina> visitado = new HashSet<>();

        // Cola de prioridad usando un comparador directo
        PriorityQueue<Esquina> cola = new PriorityQueue<>(new Comparator<Esquina>() {
            public int compare(Esquina a, Esquina b) {
                return Integer.compare(distancia.get(a), distancia.get(b));
            }
        });

        // Inicializo las distancias
        for (Esquina nodo : nodos.values()) {
            distancia.put(nodo, Integer.MAX_VALUE);
            padre.put(nodo, null);
        }

        distancia.put(origen, 0);
        cola.add(origen);

        while (!cola.isEmpty()) {
            Esquina actual = cola.poll();

            if (visitado.contains(actual)) continue;
            visitado.add(actual);

            List<INodo> vecinos = actual.getVecinos();
            List<Integer> pesos = actual.getPesos();

            for (int i = 0; i < vecinos.size(); i++) {
                Esquina vecino = (Esquina) vecinos.get(i); // Cast necesario
                int peso = pesos.get(i);
                int nuevaDist = distancia.get(actual) + peso;

                if (!visitado.contains(vecino) && nuevaDist < distancia.get(vecino)) {
                    distancia.put(vecino, nuevaDist);
                    padre.put(vecino, actual);
                    cola.add(vecino);
                }
            }
        }

        return distancia;
    }
}
