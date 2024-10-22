import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * La clase BúsquedaEnAnchura representa un tipo de dato para
 * determinar los caminos desde un vértice fuente "S" dado
 * en un grafo no dirigido representado como una lista de adyacencia.
 * 
 * @author Felipe Sales
 */

public class BúsquedaEnAnchura {
    private boolean[] marcado;  // marcado[v] = ¿hay un camino s-v?
    private List<String> caminos;  // Lista para almacenar los caminos
    private List<List<Integer>> listaAdyacencia;  // Lista de adyacencia
    private int V;  // Número de vértices
    private int S;  // Vértice fuente

    /**
     * Constructor que inicializa la lista de adyacencia y ejecuta BFS.
     * @param V el número de vértices
     * @param arcos arreglo de pares de enteros que representan los arcos
     * @param S el vértice fuente
     */
    public BúsquedaEnAnchura(int V, int[][] arcos, int S) {
        this.V = V;
        this.S = S;
        this.marcado = new boolean[V];
        this.caminos = new ArrayList<>();
        this.listaAdyacencia = new ArrayList<>();

        // Inicializa la lista de adyacencia
        for (int i = 0; i < V; i++) {
            listaAdyacencia.add(new ArrayList<>());
        }

        // Agrega los arcos a la lista de adyacencia
        for (int[] arco : arcos) {
            int v = arco[0];
            int w = arco[1];
            listaAdyacencia.get(v).add(w);
            listaAdyacencia.get(w).add(v);  // Grafo no dirigido
        }

        validarVertice(S);  // Valida el vértice fuente
        bfs();  // Comienza el BFS con el vértice inicial
    }

    /**
     * Valida que el vértice esté dentro del rango válido.
     *
     * @param v el vértice a validar
     * @throws IllegalArgumentException si el vértice no es válido
     */
    public void validarVertice(int v) {
        if (v < 0 || v >= V) {
            throw new IllegalArgumentException("El vértice " + v + " no está entre 0 y " + (V - 1));
        }
    }

    /**
     * Ejecuta la búsqueda en anchura desde el vértice fuente "S".
     */
    public void bfs() {
        Queue<Pareja> queue = new LinkedList<>();  // Cola para BFS
        queue.add(new Pareja(S, String.valueOf(S)));  // Inicializa la cola con el vértice fuente y el camino actual

        while (!queue.isEmpty()) {
            Pareja actual = queue.poll();  // Toma el primer elemento de la cola
            int v = actual.verticeAUX;
            String caminoActual = actual.caminoAUX;

            if (!marcado[v]) {  // Si el vértice no está marcado
                marcado[v] = true;  // Marca el vértice como visitado

                // Recorre los vértices adyacentes
                for (int w : listaAdyacencia.get(v)) {  // Itera a través de todos los vértices adyacentes
                    if (!marcado[w]) {  // Si el vértice no está marcado
                        queue.add(new Pareja(w, caminoActual + " -> " + w));  // Agrega el vértice y el nuevo camino a la cola
                    }
                }

                // Cuando se regresa, se agrega el camino a la lista de caminos
                if (!caminoActual.isEmpty()) {
                    caminos.add("Vértice de llegada: " + v + ", Camino: " + caminoActual);  // Almacena el vértice de llegada y el camino
                }
            }
        }
    }

    // Clase auxiliar para almacenar el vértice y el camino
    static class Pareja {
        int verticeAUX;
        String caminoAUX;

        Pareja(int verticeAUX, String caminoAUX) {
            this.verticeAUX = verticeAUX;
            this.caminoAUX = caminoAUX;
        }
    }

    // Ejemplo ejecución
    public static void main(String[] args) {
        BúsquedaEnAnchura bfs = new BúsquedaEnAnchura(7, new int[][] {
            {0, 1},
            {0, 2},
            {1, 3},
            {1, 4},
            {2, 5},
            {2, 6},
            {3, 4}
        }, 0);

        for (String camino : bfs.caminos) {
            System.out.println(camino);
        }
    }
}