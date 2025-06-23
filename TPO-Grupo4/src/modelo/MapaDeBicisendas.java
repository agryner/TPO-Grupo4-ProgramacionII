package modelo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import interfaces.IGrafo;
import interfaces.INodo;

public class MapaDeBicisendas implements IGrafo {
    private Map<Integer, Esquina> nodos = new HashMap<>();

    public void agregarNodo(int valor) {
        nodos.putIfAbsent(valor, new Esquina(valor));
    }

    public void agregarArista(int origen, int destino, int peso) {
        Esquina nodoOrigen = nodos.get(origen);
        Esquina nodoDestino = nodos.get(destino);
        if (nodoOrigen != null && nodoDestino != null) {
            nodoOrigen.agregarVecino(nodoDestino, peso);
            nodoDestino.agregarVecino(nodoOrigen, peso); // No dirigido
        }
    }

    public void mostrarListaAdyacencia() {
        for (Esquina nodo : nodos.values()) {
            System.out.print(nodo.getValor() + ": ");
            List<INodo> vecinos = nodo.getVecinos();
            List<Integer> pesos = nodo.getPesos();
            for (int i = 0; i < vecinos.size(); i++) {
                System.out.print("(" + vecinos.get(i).getValor() + ", peso=" + pesos.get(i) + ") ");
            }
            System.out.println();
        }
    }

    public Esquina getNodo(int valor) {
        return nodos.get(valor);
    }

    public Map<Integer, Esquina> getNodos() {
        return nodos;
    }
}
