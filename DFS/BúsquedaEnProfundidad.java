import java.util.ArrayList;
import java.util.List;

/**
 *  La clase BúsquedaEnProfundidad representa un tipo de dato para
 *  determinar los vértices conectados a un vértice fuente "S" dado
 *  en un grafo no dirigido representado como una lista de adyacencia.
 *
 *  @author Felipe Sales
 */

public class BúsquedaEnProfundidad {
    private boolean[] marcado;    // marcado[v] = ¿hay un camino s-v?
    private int cuenta;           // número de vértices conectados a s
    private List<List<Integer>> listaAdyacencia; // Lista de adyacencia

    /**
     * Constructor que inicializa la lista de adyacencia y ejecuta DFS.
     * @param V el número de vértices
     * @param arcos arreglo de pares de enteros que representan los arcos
     * @param S el vértice fuente
     */

    public BúsquedaEnProfundidad(int V, int[][] arcos, int S) {
        marcado = new boolean[V];
        listaAdyacencia = new ArrayList<>();

        // Inicializa la lista de adyacencia
        for (int v = 0; v < V; v++) {
            listaAdyacencia.add(new ArrayList<>());
        }

        // Agrega los arcos a la lista de adyacencia
        for (int[] arco : arcos) {
            int v = arco[0];
            int w = arco[1];
            listaAdyacencia.get(v).add(w);
            listaAdyacencia.get(w).add(v); // Grafo no dirigido
        }

        validarVertice(S);
        dfs(S);
    }

    // Ejecuta búsqueda en profundidad desde el vértice fuente "S".
    private void dfs(int V) {
        cuenta++;
        marcado[V] = true; // Marca el vértice como visitado
        for (int w : listaAdyacencia.get(V)) { // Itera a través de todos los vértices adyacentes
            if (!marcado[w]) { // Si el vértice no está marcado
                dfs(w); // Visita recursivamente el vértice
            }
        }
    }


    // Retorna "True" si hay un camino entre el vértice fuente S y el vertice V, "False" de lo contrario.
    public boolean marcado(int V) {
        validarVertice(V);
        return marcado[V];
    }


    // Devuelve el número de vértices conectados al vértice fuente S.
    public int cuenta() {
        return cuenta;
    }

    // Valida que el vértice esté dentro del rango válido
    private void validarVertice(int v) {
        int V = marcado.length;
        if (v < 0 || v >= V) {
            throw new IllegalArgumentException("El vértice " + v + " no está entre 0 y " + (V - 1));
        }
    }

    // Ejemplo ejecución
    public static void main(String[] args) {
        int V = 7;  // Número de vértices
        int[][] arcos = {
            {0, 1},
            {0, 2},
            {1, 3},
            {1, 4},
            {2, 5},
            {2, 6},
            {3, 4}
        };
        int S = 0;  // Vértice fuente

        BúsquedaEnProfundidad busqueda = new BúsquedaEnProfundidad(V, arcos, S);
        System.out.println("Número de vértices conectados al vértice fuente " + S + ": " + busqueda.cuenta());

        // Verifica si hay caminos a todos los vértices
        for (int v = 0; v < V; v++) {
            System.out.println("¿Hay un camino desde " + S + " a " + v + "? " + busqueda.marcado(v));
        }
    }
}