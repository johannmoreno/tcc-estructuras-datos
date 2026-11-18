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

/**
 * Casos de prueba de consola para verificar la clase Grafo, sin
 * necesidad de abrir la interfaz gráfica.
 *
 * Grafo de prueba usado (construido a mano, no desde JSON, para tener
 * un resultado esperado 100% determinístico):
 *
 *      A --2.0-- B --3.0-- C --1.0-- D
 *      |____________10.0____________|
 *                (A - C)
 *
 * Aristas agregadas en este orden: A-B(2), B-C(3), C-D(1), A-C(10).
 *
 * Trazas esperadas (ver enunciado y Javadoc de Grafo para el detalle
 * del algoritmo BFS/DFS tomado del problemario de referencia):
 *
 * BFS desde A: A, B, C, D
 *   (se marca A; se procesan sus adyacentes en orden de índice: B, C;
 *    luego los adyacentes de B ya están marcados; luego los adyacentes
 *    de C: D)
 *
 * DFS desde A: A, C, D, B
 *   (se marca A; se apilan sus adyacentes B, C -> tope C; se procesa C,
 *    se apila su adyacente no marcado D -> tope D; se procesa D; queda
 *    B en la pila y se procesa de último)
 *
 * Dijkstra A -> D: ruta A -> B -> C -> D, distancia 6.0
 *   (2 + 3 + 1 = 6, mejor que A -> C -> D = 10 + 1 = 11)
 */
public class Pruebas {

    private static int total = 0;
    private static int exitosos = 0;

    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println(" CASOS DE PRUEBA - PROYECTO GRAFO/DIJKSTRA");
        System.out.println("========================================\n");

        Grafo g = construirGrafoDePrueba();

        System.out.println("--- Caso 1: cantidad de vertices ---");
        verificar("cantidadVertices() == 4", 4, g.cantidadVertices());

        System.out.println("\n--- Caso 2: adyacencia ---");
        verificar("sonAdyacentes(A,B) == true", true, g.sonAdyacentes("A", "B"));
        verificar("sonAdyacentes(A,D) == false", false, g.sonAdyacentes("A", "D"));
        verificar("sonAdyacentes(A,C) == true", true, g.sonAdyacentes("A", "C"));

        System.out.println("\n--- Caso 3: grado de los vertices ---");
        verificar("grado(A) == 2", 2, g.grado("A"));
        verificar("grado(B) == 2", 2, g.grado("B"));
        verificar("grado(C) == 3", 3, g.grado("C"));
        verificar("grado(D) == 1", 1, g.grado("D"));

        System.out.println("\n--- Caso 4: recorrido BFS desde A ---");
        verificar("recorridoBFS(A) == [A, B, C, D]", "[A, B, C, D]", g.recorridoBFS("A").toString());

        System.out.println("\n--- Caso 5: recorrido DFS desde A ---");
        verificar("recorridoDFS(A) == [A, C, D, B]", "[A, C, D, B]", g.recorridoDFS("A").toString());

        System.out.println("\n--- Caso 6: Dijkstra A -> D (debe preferir el camino mas largo en pasos pero mas corto en distancia) ---");
        ResultadoRuta resultado = g.dijkstra("A", "D");
        verificar("dijkstra(A,D).getDistanciaTotal() == 6.0", 6.0, resultado.getDistanciaTotal());
        verificar("dijkstra(A,D) ruta == [A, B, C, D]", "[A, B, C, D]", resultado.getRuta().toString());

        System.out.println("\n--- Caso 7: eliminar arista y volver a verificar adyacencia ---");
        g.eliminarArista("A", "C");
        verificar("sonAdyacentes(A,C) == false tras eliminarArista", false, g.sonAdyacentes("A", "C"));
        verificar("grado(A) == 1 tras eliminar la arista A-C", 1, g.grado("A"));

        System.out.println("\n--- Caso 8: eliminar vertice ---");
        g.eliminarVertice("D");
        verificar("cantidadVertices() == 3 tras eliminar D", 3, g.cantidadVertices());

        System.out.println("\n--- Caso 9: cargar mapa desde JSON ---");
        try {
            DatosMapa datos = LectorMapaJSON.leer("datos/mapa_prueba.json");
            Grafo mapa = new Grafo();
            mapa.cargarDesdeJSON(datos);
            verificar("cargarDesdeJSON: cantidadVertices() > 0", true, mapa.cantidadVertices() > 0);
            System.out.println("   Vertices cargados: " + mapa.cantidadVertices());
        } catch (Exception e) {
            System.out.println("   FALLA (excepcion al leer/cargar JSON): " + e.getMessage());
            total++;
        }

        System.out.println("\n========================================");
        System.out.println("RESULTADO FINAL: " + exitosos + " / " + total + " casos exitosos");
        System.out.println("========================================");
    }

    private static Grafo construirGrafoDePrueba() {
        Grafo g = new Grafo();
        g.agregarVertice("A", 0, 0);
        g.agregarVertice("B", 100, 0);
        g.agregarVertice("C", 200, 0);
        g.agregarVertice("D", 300, 0);
        g.agregarArista("A", "B", 2.0);
        g.agregarArista("B", "C", 3.0);
        g.agregarArista("C", "D", 1.0);
        g.agregarArista("A", "C", 10.0);
        return g;
    }

    private static void verificar(String descripcion, Object esperado, Object obtenido) {
        total++;
        boolean pasa;
        try {
            pasa = esperado.equals(obtenido);
        } catch (Exception e) {
            pasa = false;
        }
        String estado = pasa ? "PASA" : "FALLA";
        if (pasa) exitosos++;
        System.out.println("[" + estado + "] " + descripcion
                + "  (esperado=" + esperado + ", obtenido=" + obtenido + ")");
    }
}
