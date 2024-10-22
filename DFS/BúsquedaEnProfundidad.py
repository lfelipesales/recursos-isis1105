def busqueda_en_profundidad(V, arcos, S):
    marcado = [False] * V  # marcado[v] = ¿hay un camino s-v?
    caminos = []  # Lista para almacenar los caminos
    listaAdyacencia = [[] for _ in range(V)]  # Lista de adyacencia

    # Agrega los arcos a la lista de adyacencia
    for arco in arcos:
        v, w = arco
        listaAdyacencia[v].append(w)
        listaAdyacencia[w].append(v)  # Grafo no dirigido

    validar_vertice(S, V)
    dfs(S, listaAdyacencia, marcado, [S], caminos)  # Comienza el DFS con el camino inicial

    return caminos  # Devuelve todos los caminos encontrados


def validar_vertice(v, totalV):
    if v < 0 or v >= totalV:
        raise ValueError(f"El vértice {v} no está entre 0 y {totalV - 1}")


def dfs(v, listaAdyacencia, marcado, camino_actual, caminos):
    marcado[v] = True  # Marca el vértice como visitado

    # Recorre los vértices adyacentes
    for w in listaAdyacencia[v]:  # Itera a través de todos los vértices adyacentes
        if not marcado[w]:  # Si el vértice no está marcado
            dfs(w, listaAdyacencia, marcado, camino_actual + [w], caminos)  # Visita recursivamente el vértice

    # Cuando se regresa, se agrega el camino a la lista de caminos
    if camino_actual:
        caminos.append((camino_actual[-1], ' -> '.join(map(str, camino_actual))))  # Almacena el vértice de llegada y el camino


# Ejemplo ejecucion
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

    caminos_conectados = busqueda_en_profundidad(V, arcos, S)
    print("\nCaminos desde el vértice 'S':")
    for vertice_llegada, camino in caminos_conectados:
        print(f"Vértice de llegada: {vertice_llegada}, Camino: {camino}")