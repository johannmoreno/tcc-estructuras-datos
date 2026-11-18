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
 * MOISES BANDA PEREZ – <CÓDIGO> (integrante 5)
 *
 ****************************************************************************/

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Utilidad de lectura del archivo de mapa de prueba en formato JSON.
 *
 * IMPORTANTE: esta clase NO es un parser JSON de propósito general.
 * Está escrita específicamente para el esquema fijo del archivo
 * "datos/mapa_prueba.json" de este proyecto:
 *
 * <pre>
 * {
 *   "vertices": [
 *     {"nombre": "Centro Historico", "x": 60, "y": 260},
 *     ...
 *   ],
 *   "aristas": [
 *     {"origen": "Centro Historico", "destino": "Getsemani", "distancia": 1.2},
 *     ...
 *   ]
 * }
 * </pre>
 *
 * Se entrega completamente resuelta: el objetivo de aprendizaje de este
 * proyecto es el TAD Grafo y el algoritmo de Dijkstra, no la escritura
 * de un parser de JSON. El estudiante solo debe usar los datos ya
 * leídos (clase {@link DatosMapa}) para poblar su Grafo, dentro del
 * método TODO {@code Grafo.cargarDesdeJSON(DatosMapa)}.
 *
 * Nota técnica: se usan expresiones regulares (java.util.regex), que
 * son una utilidad de procesamiento de texto, no una colección de
 * datos — su uso aquí no contradice la restricción de no usar
 * colecciones de java.util (List, Queue, Stack, Map, etc.) que aplica
 * al resto del proyecto.
 */
public class LectorMapaJSON {

    private static final Pattern PATRON_VERTICE = Pattern.compile(
        "\\{\\s*\"nombre\"\\s*:\\s*\"([^\"]+)\"\\s*,\\s*\"x\"\\s*:\\s*(-?\\d+)\\s*,\\s*\"y\"\\s*:\\s*(-?\\d+)\\s*\\}");

    private static final Pattern PATRON_ARISTA = Pattern.compile(
        "\\{\\s*\"origen\"\\s*:\\s*\"([^\"]+)\"\\s*,\\s*\"destino\"\\s*:\\s*\"([^\"]+)\"\\s*,\\s*\"distancia\"\\s*:\\s*([0-9]+(?:\\.[0-9]+)?)\\s*\\}");

    /**
     * Lee el archivo indicado y retorna los vértices y aristas ya
     * extraídos en un objeto {@link DatosMapa}.
     *
     * @param rutaArchivo ruta del archivo JSON a leer.
     * @return los datos del mapa listos para poblar un Grafo.
     * @throws IOException si el archivo no existe o no puede leerse.
     */
    public static DatosMapa leer(String rutaArchivo) throws IOException {
        String contenido = new String(Files.readAllBytes(Paths.get(rutaArchivo)));

        Lista<Vertice> vertices = new Lista<>();
        Matcher mVertice = PATRON_VERTICE.matcher(contenido);
        while (mVertice.find()) {
            String nombre = mVertice.group(1);
            int x = Integer.parseInt(mVertice.group(2));
            int y = Integer.parseInt(mVertice.group(3));
            vertices.agregar(new Vertice(nombre, x, y));
        }

        Lista<String> origenes = new Lista<>();
        Lista<String> destinos = new Lista<>();
        Lista<Double> distancias = new Lista<>();
        Matcher mArista = PATRON_ARISTA.matcher(contenido);
        while (mArista.find()) {
            origenes.agregar(mArista.group(1));
            destinos.agregar(mArista.group(2));
            distancias.agregar(Double.parseDouble(mArista.group(3)));
        }

        DatosMapa datos = new DatosMapa();

        datos.vertices = new Vertice[vertices.tamano()];
        for (int i = 0; i < vertices.tamano(); i++) {
            datos.vertices[i] = vertices.obtener(i);
        }

        datos.aristaOrigen = new String[origenes.tamano()];
        datos.aristaDestino = new String[destinos.tamano()];
        datos.aristaDistancia = new double[distancias.tamano()];
        for (int i = 0; i < origenes.tamano(); i++) {
            datos.aristaOrigen[i] = origenes.obtener(i);
            datos.aristaDestino[i] = destinos.obtener(i);
            datos.aristaDistancia[i] = distancias.obtener(i);
        }

        return datos;
    }
}
