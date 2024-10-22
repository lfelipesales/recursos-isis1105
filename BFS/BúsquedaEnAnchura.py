def busqueda_en_anchura(V, arcos, S):
    marcado = [False] * V  # marcado[v] = ¿hay un camino s-v?
    caminos = []  # Lista para almacenar los caminos
    listaAdyacencia = [[] for _ in range(V)]  # Lista de adyacencia

    # Agrega los arcos a la lista de adyacencia
    for arco in arcos:
        v, w = arco
        listaAdyacencia[v].append(w)
        listaAdyacencia[w].append(v)  # Grafo no dirigido

    validar_vertice(S, V)
    bfs(S, listaAdyacencia, marcado, caminos)  # Comienza el BFS con el vértice inicial

    return caminos  # Devuelve todos los caminos encontrados


def validar_vertice(v, totalV):
    if v < 0 or v >= totalV:
        raise ValueError(f"El vértice {v} no está entre 0 y {totalV - 1}")


def bfs(S, listaAdyacencia, marcado, caminos):
    queue = [(S, [S])]  # Inicializa la cola con el vértice fuente y el camino actual

    while queue:
        v, camino_actual = queue[0]  # Toma el primer elemento de la cola
        queue = queue[1:]  # Elimina el primer elemento de la cola

        if not marcado[v]:  # Si el vértice no está marcado
            marcado[v] = True  # Marca el vértice como visitado

            # Recorre los vértices adyacentes
            for w in listaAdyacencia[v]:  # Itera a través de todos los vértices adyacentes
                if not marcado[w]:  # Si el vértice no está marcado
                    queue.append((w, camino_actual + [w]))  # Agrega el vértice y el nuevo camino a la cola

            # Cuando se regresa, se agrega el camino a la lista de caminos
            if camino_actual:
                caminos.append((camino_actual[-1], ' -> '.join(map(str, camino_actual))))  # Almacena el vértice de llegada y el camino


# Ejemplo ejecución
if __name__ == "__main__":
    V = 7  # Número de vértices
    arcos = [
                (0, 1), 
                (0, 2), 
                (1, 3), 
                (1, 4), 
                (2, 5), 
                (2, 6), 
                (3, 4)
            ]
    
    S = 0  # Vértice fuente

    caminos_conectados = busqueda_en_anchura(V, arcos, S)
    print("\nCaminos desde el vértice 'S':")
    for vertice_llegada, camino in caminos_conectados:
        print(f"Vértice de llegada: {vertice_llegada}, Camino: {camino}")