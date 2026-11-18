/****************************************************************************
 * Palabra de honor:
 * - No he discutido ni mostrado el código de mi programa con alguien que no sea mi
 *   compañero de CIPA, Profesor o con el monitor asignado a este curso.
 * - No he utilizado código obtenido de otro u otros estudiantes,
 *   o cualquier otra fuente no autorizada, ya sea modificado o sin modificar.
 * - Si llego a hacer uso de código de IA Generativa, debo indicar con exactitud
 *   qué análisis del código realicé.
 * - Si cualquier código o documentación utilizada en mi programa no es de mi autoría,
 *   debo señalarlo claramente con una cita apropiada en los comentarios de mi programa.
 * - No copio y pego código de IA generativa sin analizarlo.
 *
 * SAID ALEJANDRO MARTELO DIAZ – <7502410025> (integrante 1)
 * JOHANN DAVID MORENO CASSIANI – <7502423002> (integrante 2)
 * JUAN DAVID ALZATE GALVIS – <7502410040> (integrante 3)
 * DAVID SANTIAGO ESCAMILLA CASTRO – <7502410012> (integrante 4)
 * MOISES BANDA PEREZ – <7502320047> (integrante 5)
 *
 ****************************************************************************/

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * TAD Grafo ponderado no dirigido para el Sistema de Rutas de Entrega
 * Urbana.
 *
 * <p>El mapa se representa con una <b>matriz de adyacencia propia</b>
 * ({@code double[][]}): la posición {@code [i][j]} guarda la distancia de la
 * calle entre los vértices {@code i} y {@code j}, o el centinela
 * {@link #SIN_CONEXION} cuando no existe una calle directa. Como el grafo no
 * es dirigido, toda arista se escribe de forma simétrica. Los vértices se
 * guardan en la clase {@link Lista} (arreglo dinámico propio) y los recorridos
 * reutilizan {@link Cola} y {@link Pila}, sin usar colecciones de
 * {@code java.util}.</p>
 *
 * <p>Esta representación permite comprobar una adyacencia en tiempo constante
 * una vez conocidos los índices y hace que Dijkstra pueda leer el peso de
 * cualquier calle directamente de la matriz. El costo de esa comodidad es el
 * espacio O(V^2), aceptable para el tamaño de un mapa urbano de este
 * proyecto.</p>
 */
public class Grafo {

    /** Valor centinela: indica que no existe conexión directa entre dos vértices. */
    public static final double SIN_CONEXION = -1.0;

    private Lista<Vertice> vertices;
    private double[][] distancias;
    private int capacidad;
    private Lista<ConsultaRuta> historialConsultas;

    public Grafo() {
        vertices = new Lista<>();
        capacidad = 10;
        distancias = new double[capacidad][capacidad];
        inicializarMatriz(distancias, capacidad);
        historialConsultas = new Lista<>();
    }

    // ----------------------------------------------------------------
    // Métodos de apoyo YA RESUELTOS (no son TODO, no los modifiques).
    // ----------------------------------------------------------------

    private void inicializarMatriz(double[][] m, int tamano) {
        for (int i = 0; i < tamano; i++) {
            for (int j = 0; j < tamano; j++) {
                m[i][j] = SIN_CONEXION;
            }
        }
    }

    /**
     * Duplica la capacidad de la matriz de adyacencia cuando se llena,
     * conservando los datos existentes. Llámalo desde tu propio
     * método agregarVertice() antes de registrar un vértice nuevo,
     * si vertices.tamano() ya alcanzó la capacidad actual.
     */
    private void asegurarCapacidad() {
        if (vertices.tamano() < capacidad) return;
        int nuevaCapacidad = capacidad * 2;
        double[][] nuevaMatriz = new double[nuevaCapacidad][nuevaCapacidad];
        inicializarMatriz(nuevaMatriz, nuevaCapacidad);
        for (int i = 0; i < capacidad; i++) {
            for (int j = 0; j < capacidad; j++) {
                nuevaMatriz[i][j] = distancias[i][j];
            }
        }
        distancias = nuevaMatriz;
        capacidad = nuevaCapacidad;
    }

    /**
     * Busca la posición (índice) de un vértice por su nombre.
     * @return el índice en la Lista de vértices, o -1 si no existe.
     */
    private int indiceDe(String nombre) {
        for (int i = 0; i < vertices.tamano(); i++) {
            if (vertices.obtener(i).getNombre().equals(nombre)) return i;
        }
        return -1;
    }

    /** Cantidad de vértices actualmente en el grafo. */
    public int cantidadVertices() { return vertices.tamano(); }

    /** Vértice almacenado en la posición indicada. */
    public Vertice obtenerVertice(int indice) { return vertices.obtener(indice); }

    /** Valor crudo de la matriz de adyacencia entre las posiciones i y j (uso del PanelMapa). */
    public double obtenerPeso(int i, int j) { return distancias[i][j]; }

    /** Agrega una consulta de ruta ya calculada al historial en memoria. */
    public void agregarConsultaAlHistorial(ConsultaRuta c) { historialConsultas.agregar(c); }

    public Lista<ConsultaRuta> getHistorialConsultas() { return historialConsultas; }

    // ----------------------------------------------------------------
    // TODO 1: agregarVertice
    // ----------------------------------------------------------------
    /**
     * Agrega un nuevo punto (vértice) al mapa.
     *
     * <p><b>Qué hace:</b> valida el nombre, comprueba que no esté repetido,
     * garantiza espacio en la matriz y agrega un objeto {@link Vertice} al
     * arreglo dinámico. No escribe en la matriz porque las posiciones nuevas
     * ya nacen con {@link #SIN_CONEXION}.</p>
     *
     * <p><b>Estructura y decisión de diseño:</b> se usa {@link Lista} para
     * conservar el orden de inserción, que es el mismo orden de los índices de
     * la matriz. Esa correspondencia índice-lista / índice-matriz es la base de
     * todos los demás métodos. La duplicidad se detecta con una búsqueda
     * lineal; no se usa un mapa de {@code java.util} porque el enunciado lo
     * prohíbe y porque el número de puntos de un mapa es pequeño.</p>
     *
     * <p><b>Complejidad:</b> O(V) por la búsqueda del nombre; el crecimiento de
     * la matriz es O(V^2) pero ocurre de forma amortizada cada vez que se
     * duplica la capacidad.</p>
     *
     * @param nombre nombre del punto; no puede ser nulo ni vacío.
     * @param x coordenada horizontal usada para dibujar el punto.
     * @param y coordenada vertical usada para dibujar el punto.
     * @return {@code true} si se agregó; {@code false} si el nombre es inválido
     *         o si ya existía un vértice con ese nombre.
     */
    public boolean agregarVertice(String nombre, int x, int y) {
        // Generado con IA (opencode) – analizado y adaptado por el CIPA
        if (nombre == null || nombre.trim().isEmpty()) return false;
        if (indiceDe(nombre) != -1) return false;
        asegurarCapacidad();
        vertices.agregar(new Vertice(nombre, x, y));
        return true;
    }

    // ----------------------------------------------------------------
    // TODO 2: eliminarVertice
    // ----------------------------------------------------------------
    /**
     * Elimina un punto del mapa junto con todas las calles que lo tocaban.
     *
     * <p><b>Qué hace:</b> localiza el índice del vértice y reconstruye tanto la
     * lista de vértices como la matriz, omitiendo la fila y la columna de ese
     * índice. Al reconstruir se "cierra el hueco" para que los índices sigan
     * siendo contiguos y coincidan con la lista.</p>
     *
     * <p><b>Estructura y decisión de diseño:</b> una matriz no permite borrar
     * una fila/columna desplazando memoria como un arreglo lineal; por eso se
     * crea una matriz nueva y se copia con dos contadores independientes (uno
     * para filas y otro para columnas). Es O(V^2), pero la alternativa de dejar
     * huecos obligaría a manejar índices "muertos" en todos los demás métodos,
     * lo que aumentaría la complejidad lógica del TAD.</p>
     *
     * <p><b>Complejidad:</b> O(V^2) en tiempo y O(V^2) en espacio adicional
     * durante la reconstrucción.</p>
     *
     * @param nombre nombre del punto a eliminar.
     * @return {@code true} si se eliminó; {@code false} si no existía.
     */
    public boolean eliminarVertice(String nombre) {
        // Generado con IA (opencode) – analizado y adaptado por el CIPA
        int indiceEliminar = indiceDe(nombre);
        if (indiceEliminar == -1) return false;

        int n = vertices.tamano();
        Lista<Vertice> nuevaLista = new Lista<>();
        for (int i = 0; i < n; i++) {
            if (i != indiceEliminar) nuevaLista.agregar(vertices.obtener(i));
        }

        double[][] nuevaMatriz = new double[capacidad][capacidad];
        inicializarMatriz(nuevaMatriz, capacidad);

        int filaNueva = 0;
        for (int i = 0; i < n; i++) {
            if (i == indiceEliminar) continue;
            int columnaNueva = 0;
            for (int j = 0; j < n; j++) {
                if (j == indiceEliminar) continue;
                nuevaMatriz[filaNueva][columnaNueva] = distancias[i][j];
                columnaNueva++;
            }
            filaNueva++;
        }

        vertices = nuevaLista;
        distancias = nuevaMatriz;
        return true;
    }

    // ----------------------------------------------------------------
    // TODO 3: agregarArista
    // ----------------------------------------------------------------
    /**
     * Registra una calle entre dos puntos existentes.
     *
     * <p><b>Qué hace:</b> busca ambos índices y escribe la distancia en las dos
     * posiciones simétricas de la matriz, porque el grafo es no dirigido. Una
     * distancia negativa o {@code NaN} se rechaza para no confundirla con el
     * centinela {@link #SIN_CONEXION} ni romper las comparaciones de
     * Dijkstra.</p>
     *
     * <p><b>Estructura y decisión de diseño:</b> la matriz permite que
     * {@code sonAdyacentes} y Dijkstra consulten el peso en O(1) una vez
     * conocidos los índices. La escritura simétrica evita tener que recordar
     * una dirección especial para cada calle.</p>
     *
     * <p><b>Complejidad:</b> O(V) por las dos búsquedas de nombre; la escritura
     * en la matriz es O(1).</p>
     *
     * @param v1 nombre del primer punto.
     * @param v2 nombre del segundo punto.
     * @param distancia distancia en kilómetros; debe ser un valor no negativo.
     * @return {@code true} si se registró; {@code false} si algún punto no
     *         existe o la distancia es inválida.
     */
    public boolean agregarArista(String v1, String v2, double distancia) {
        // Generado con IA (opencode) – analizado y adaptado por el CIPA
        int i = indiceDe(v1);
        int j = indiceDe(v2);
        if (i == -1 || j == -1) return false;
        if (Double.isNaN(distancia) || distancia < 0) return false;
        distancias[i][j] = distancia;
        distancias[j][i] = distancia;
        return true;
    }

    // ----------------------------------------------------------------
    // TODO 4: eliminarArista
    // ----------------------------------------------------------------
    /**
     * Elimina la calle que une dos puntos.
     *
     * <p><b>Qué hace:</b> obtiene ambos índices y vuelve a escribir el centinela
     * {@link #SIN_CONEXION} en las dos posiciones simétricas. No elimina
     * vértices ni modifica el tamaño de la matriz.</p>
     *
     * <p><b>Estructura y decisión de diseño:</b> en una matriz de adyacencia
     * "eliminar" una arista equivale a restaurar el valor de no conexión; no hay
     * que desplazar datos. Esto mantiene la operación simple y conserva la
     * correspondencia entre índices y vértices.</p>
     *
     * <p><b>Complejidad:</b> O(V) por las búsquedas de los nombres y O(1) para
     * las dos asignaciones.</p>
     *
     * @param v1 nombre del primer punto.
     * @param v2 nombre del segundo punto.
     * @return {@code true} si se procesó la eliminación; {@code false} si algún
     *         punto no existe.
     */
    public boolean eliminarArista(String v1, String v2) {
        // Generado con IA (opencode) – analizado y adaptado por el CIPA
        int i = indiceDe(v1);
        int j = indiceDe(v2);
        if (i == -1 || j == -1) return false;
        distancias[i][j] = SIN_CONEXION;
        distancias[j][i] = SIN_CONEXION;
        return true;
    }

    // ----------------------------------------------------------------
    // TODO 5: sonAdyacentes
    // ----------------------------------------------------------------
    /**
     * Indica si existe una calle directa entre dos puntos.
     *
     * <p><b>Qué hace:</b> traduce los nombres a índices y compara la celda de la
     * matriz con {@link #SIN_CONEXION}.</p>
     *
     * <p><b>Estructura y decisión de diseño:</b> es la consulta que justifica
     * la matriz de adyacencia: una vez obtenidos los índices, la respuesta es
     * inmediata. Se devuelve {@code false} ante nombres inexistentes en lugar de
     * lanzar una excepción, para que la interfaz gráfica pueda informar el error
     * sin cerrarse.</p>
     *
     * <p><b>Complejidad:</b> O(V) por las búsquedas lineales; la comparación
     * final es O(1).</p>
     *
     * @param v1 nombre del primer punto.
     * @param v2 nombre del segundo punto.
     * @return {@code true} si hay conexión directa; {@code false} en caso
     *         contrario o si algún punto no existe.
     */
    public boolean sonAdyacentes(String v1, String v2) {
        // Generado con IA (opencode) – analizado y adaptado por el CIPA
        int i = indiceDe(v1);
        int j = indiceDe(v2);
        if (i == -1 || j == -1) return false;
        return distancias[i][j] != SIN_CONEXION;
    }

    // ----------------------------------------------------------------
    // TODO 6: grado
    // ----------------------------------------------------------------
    /**
     * Calcula el grado de un punto: cuántas calles lo conectan.
     *
     * <p><b>Qué hace:</b> recorre la fila del vértice en la matriz y cuenta las
     * celdas que no son el centinela, excluyendo la diagonal (un punto no se
     * considera conectado consigo mismo).</p>
     *
     * <p><b>Estructura y decisión de diseño:</b> el grado es simplemente el
     * número de vecinos distintos; con matriz basta una pasada por la fila. En
     * un grafo no dirigido la fila y la columna tienen la misma información, así
     * que no hay que duplicar el conteo.</p>
     *
     * <p><b>Complejidad:</b> O(V) para la búsqueda del nombre más O(V) para
     * recorrer la fila; en total O(V).</p>
     *
     * @param v nombre del punto consultado.
     * @return el número de calles que lo conectan, o {@code -1} si el punto no
     *         existe.
     */
    public int grado(String v) {
        // Generado con IA (opencode) – analizado y adaptado por el CIPA
        int indice = indiceDe(v);
        if (indice == -1) return -1;
        int contador = 0;
        for (int j = 0; j < cantidadVertices(); j++) {
            if (j != indice && distancias[indice][j] != SIN_CONEXION) contador++;
        }
        return contador;
    }

    // ----------------------------------------------------------------
    // TODO 7: recorridoBFS
    // ----------------------------------------------------------------
    /**
     * Recorrido en anchura (BFS) desde un punto de partida.
     *
     * <p><b>Qué hace:</b> visita el origen, lo marca y lo encola; luego extrae
     * índices de la cola y, por cada uno, agrega a la cola los vecinos no
     * marcados. El orden en que se encolan los vecinos es el orden de los
     * índices de la matriz.</p>
     *
     * <p><b>Estructura y decisión de diseño:</b> se usa la clase {@link Cola}
     * entregada, cuyo comportamiento FIFO garantiza que primero se visiten todos
     * los vecinos de un nivel antes de pasar al siguiente. El arreglo
     * {@code boolean[] marcado} evita ciclos y visitas repetidas. Se encola el
     * índice y no el nombre para no repetir búsquedas lineales.</p>
     *
     * <p><b>Complejidad:</b> O(V^2) con matriz de adyacencia (para cada vértice
     * se recorre su fila completa); espacio O(V) para la cola y el arreglo de
     * marcas.</p>
     *
     * @param origen nombre del punto de partida.
     * @return lista con los nombres en el orden visitado; vacía si el origen no
     *         existe.
     */
    public Lista<String> recorridoBFS(String origen) {
        // Generado con IA (opencode) – analizado y adaptado por el CIPA
        Lista<String> resultado = new Lista<>();
        int inicio = indiceDe(origen);
        if (inicio == -1) return resultado;

        int n = cantidadVertices();
        boolean[] marcado = new boolean[n];
        Cola<Integer> cola = new Cola<>();

        marcado[inicio] = true;
        cola.encolar(inicio);

        while (!cola.estaVacia()) {
            int actual = cola.desencolar();
            resultado.agregar(vertices.obtener(actual).getNombre());
            for (int j = 0; j < n; j++) {
                if (distancias[actual][j] != SIN_CONEXION && !marcado[j]) {
                    marcado[j] = true;
                    cola.encolar(j);
                }
            }
        }
        return resultado;
    }

    // ----------------------------------------------------------------
    // TODO 8: recorridoDFS
    // ----------------------------------------------------------------
    /**
     * Recorrido en profundidad (DFS) desde un punto de partida.
     *
     * <p><b>Qué hace:</b> aplica el mismo esquema que BFS, pero con una
     * {@link Pila}: se marca el origen, se apila y, en cada iteración, se
     * desapila el último índice agregado. Sus vecinos no marcados se marcan y se
     * apilan, de modo que el siguiente en salir es el último que se apiló.</p>
     *
     * <p><b>Estructura y decisión de diseño:</b> el cambio de FIFO a LIFO es
     * exactamente lo que transforma la exploración por niveles en exploración
     * por profundidad. Se reutiliza la misma {@link Pila} de la actividad de
     * Digiturno, cumpliendo el objetivo de integrar TAD ya construidos. Marcar
     * al apilar (y no al desapilar) evita que un mismo vértice quede varias
     * veces en la pila.</p>
     *
     * <p><b>Complejidad:</b> O(V^2) por el recorrido de filas de la matriz;
     * espacio O(V) para la pila y las marcas.</p>
     *
     * @param origen nombre del punto de partida.
     * @return lista con los nombres en el orden visitado; vacía si el origen no
     *         existe.
     */
    public Lista<String> recorridoDFS(String origen) {
        // Generado con IA (opencode) – analizado y adaptado por el CIPA
        Lista<String> resultado = new Lista<>();
        int inicio = indiceDe(origen);
        if (inicio == -1) return resultado;

        int n = cantidadVertices();
        boolean[] marcado = new boolean[n];
        Pila<Integer> pila = new Pila<>();

        marcado[inicio] = true;
        pila.apilar(inicio);

        while (!pila.estaVacia()) {
            int actual = pila.desapilar();
            resultado.agregar(vertices.obtener(actual).getNombre());
            for (int j = 0; j < n; j++) {
                if (distancias[actual][j] != SIN_CONEXION && !marcado[j]) {
                    marcado[j] = true;
                    pila.apilar(j);
                }
            }
        }
        return resultado;
    }

    // ----------------------------------------------------------------
    // TODO 9: dijkstra
    // ----------------------------------------------------------------
    /**
     * Calcula la ruta de menor distancia total entre dos puntos con el
     * algoritmo de Dijkstra.
     *
     * <p><b>Qué hace:</b> mantiene tres arreglos: {@code dist} (mejor distancia
     * conocida desde el origen), {@code visitado} (vértices ya consolidados) y
     * {@code predecesor} (vértice anterior en el mejor camino). En cada paso
     * selecciona el no visitado con menor distancia, lo consolida y relaja sus
     * vecinos. Al final reconstruye el camino desde el destino usando una
     * {@link Pila} para invertir el orden.</p>
     *
     * <p><b>Estructura y decisión de diseño:</b> se usa la variante clásica
     * O(V^2) con arreglos, que es la adecuada para una matriz de adyacencia
     * densa y, además, evita una cola de prioridad de {@code java.util}. La
     * relajación solo se aplica a vecinos no visitados y con distancia
     * estrictamente menor, lo que impide ciclos en el árbol de predecesores.
     * Los vértices inalcanzables conservan {@code Double.POSITIVE_INFINITY} y se
     * informan como "sin ruta".</p>
     *
     * <p><b>Complejidad:</b> O(V^2) en tiempo (V selecciones y V relajaciones
     * por selección) y O(V) en espacio adicional; no se modifica la matriz.</p>
     *
     * @param origen nombre del punto de partida.
     * @param destino nombre del punto de llegada.
     * @return un {@link ResultadoRuta} con la secuencia y la distancia total; si
     *         algún punto no existe o no hay camino, retorna un resultado sin
     *         ruta.
     */
    public ResultadoRuta dijkstra(String origen, String destino) {
        // Generado con IA (opencode) – analizado y adaptado por el CIPA
        int inicio = indiceDe(origen);
        int fin = indiceDe(destino);
        if (inicio == -1 || fin == -1) {
            return new ResultadoRuta(new Lista<>(), 0, false);
        }

        int n = cantidadVertices();
        double[] dist = new double[n];
        boolean[] visitado = new boolean[n];
        int[] predecesor = new int[n];

        for (int i = 0; i < n; i++) {
            dist[i] = Double.POSITIVE_INFINITY;
            visitado[i] = false;
            predecesor[i] = -1;
        }
        dist[inicio] = 0.0;

        for (int paso = 0; paso < n; paso++) {
            int actual = -1;
            double menorDistancia = Double.POSITIVE_INFINITY;
            for (int i = 0; i < n; i++) {
                if (!visitado[i] && dist[i] < menorDistancia) {
                    menorDistancia = dist[i];
                    actual = i;
                }
            }
            if (actual == -1) break;

            visitado[actual] = true;

            for (int j = 0; j < n; j++) {
                if (!visitado[j] && distancias[actual][j] != SIN_CONEXION) {
                    double candidata = dist[actual] + distancias[actual][j];
                    if (candidata < dist[j]) {
                        dist[j] = candidata;
                        predecesor[j] = actual;
                    }
                }
            }
        }

        if (dist[fin] == Double.POSITIVE_INFINITY) {
            return new ResultadoRuta(new Lista<>(), 0, false);
        }

        Pila<String> pila = new Pila<>();
        int cursor = fin;
        while (cursor != -1) {
            pila.apilar(vertices.obtener(cursor).getNombre());
            cursor = predecesor[cursor];
        }

        Lista<String> ruta = new Lista<>();
        while (!pila.estaVacia()) {
            ruta.agregar(pila.desapilar());
        }
        return new ResultadoRuta(ruta, dist[fin], true);
    }

    // ----------------------------------------------------------------
    // TODO 10: cargarDesdeJSON
    // ----------------------------------------------------------------
    /**
     * Puebla el grafo con los datos que ya leyó {@link LectorMapaJSON}.
     *
     * <p><b>Qué hace:</b> recorre el arreglo de vértices y luego los tres
     * arreglos paralelos de aristas, delegando en {@link #agregarVertice} y
     * {@link #agregarArista}. Así se reutilizan todas las validaciones y no se
     * duplica la lógica de inserción.</p>
     *
     * <p><b>Estructura y decisión de diseño:</b> {@link DatosMapa} es un
     * contenedor de transporte; el grafo no depende del parser ni del formato
     * JSON. Esa separación permite cambiar el lector sin tocar el TAD. Se
     * recorre con {@code Math.min} de los tamaños para tolerar arreglos
     * incompletos sin lanzar {@code ArrayIndexOutOfBoundsException}.</p>
     *
     * <p><b>Complejidad:</b> O(V^2) por el crecimiento de la matriz en el peor
     * caso, más O(E*V) por las búsquedas de nombres de cada arista; espacio
     * O(V^2) para la matriz.</p>
     *
     * @param datos datos ya leídos del archivo JSON; si es {@code null} no se
     *              hace nada.
     */
    public void cargarDesdeJSON(DatosMapa datos) {
        // Generado con IA (opencode) – analizado y adaptado por el CIPA
        if (datos == null) return;

        if (datos.vertices != null) {
            for (Vertice v : datos.vertices) {
                if (v != null) agregarVertice(v.getNombre(), v.getX(), v.getY());
            }
        }

        if (datos.aristaOrigen != null && datos.aristaDestino != null
                && datos.aristaDistancia != null) {
            int cantidad = Math.min(datos.aristaOrigen.length,
                    Math.min(datos.aristaDestino.length, datos.aristaDistancia.length));
            for (int i = 0; i < cantidad; i++) {
                agregarArista(datos.aristaOrigen[i], datos.aristaDestino[i],
                        datos.aristaDistancia[i]);
            }
        }
    }

    // ----------------------------------------------------------------
    // TODO 11: guardarHistorialBinario
    // ----------------------------------------------------------------
    /**
     * Guarda el historial de consultas de ruta en
     * {@code datos/historial_rutas.dat}.
     *
     * <p><b>Qué hace:</b> crea la carpeta {@code datos} si no existe, abre un
     * {@link ObjectOutputStream} con try-with-resources y serializa el objeto
     * {@link Lista} completo; al final informa cuántos registros se
     * escribieron.</p>
     *
     * <p><b>Estructura y decisión de diseño:</b> la serialización binaria
     * conserva el historial como objetos Java y permite leerlo después con
     * {@code ObjectInputStream}, tal como se trabajó en la actividad de
     * Digiturno. Try-with-resources garantiza el cierre del flujo incluso si la
     * escritura falla. La {@link Lista} y {@link ConsultaRuta} ya implementan
     * {@code Serializable}, de modo que no hay que convertir los datos a otro
     * formato.</p>
     *
     * <p><b>Complejidad:</b> O(H) en tiempo y espacio de escritura, donde H es
     * el número de consultas del historial.</p>
     */
    public void guardarHistorialBinario() {
        // Generado con IA (opencode) – analizado y adaptado por el CIPA
        File carpeta = new File("datos");
        if (!carpeta.exists()) {
            carpeta.mkdirs();
        }

        try (ObjectOutputStream salida = new ObjectOutputStream(
                new FileOutputStream("datos/historial_rutas.dat"))) {
            salida.writeObject(historialConsultas);
            System.out.println("Historial de rutas guardado en datos/historial_rutas.dat ("
                    + historialConsultas.tamano() + " registros).");
        } catch (IOException e) {
            System.out.println("Error al guardar el historial de rutas: " + e.getMessage());
        }
    }
}
