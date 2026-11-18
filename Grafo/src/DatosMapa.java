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
 * Contenedor simple de los datos ya leídos desde el archivo JSON del mapa.
 * Lo produce {@link LectorMapaJSON#leer(String)} y lo consume
 * {@code Grafo.cargarDesdeJSON(DatosMapa)}.
 *
 * Se entrega completa: es solo una estructura de transporte de datos
 * (no contiene lógica de TAD).
 */
public class DatosMapa {
    public Vertice[] vertices;
    public String[] aristaOrigen;
    public String[] aristaDestino;
    public double[] aristaDistancia;
}
