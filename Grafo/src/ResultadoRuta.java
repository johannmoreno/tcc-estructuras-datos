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

import java.io.Serializable;

/**
 * Contenedor del resultado producido por {@code Grafo.dijkstra(...)}:
 * la secuencia de nombres de vértices que conforman la ruta más corta
 * y la distancia total acumulada.
 *
 * Se entrega completa: el estudiante solo debe construir un objeto de
 * este tipo y llenarlo dentro del método TODO {@code dijkstra}.
 */
public class ResultadoRuta implements Serializable {
    private static final long serialVersionUID = 1L;

    private Lista<String> ruta;
    private double distanciaTotal;
    private boolean existeRuta;

    public ResultadoRuta(Lista<String> ruta, double distanciaTotal, boolean existeRuta) {
        this.ruta = ruta;
        this.distanciaTotal = distanciaTotal;
        this.existeRuta = existeRuta;
    }

    public Lista<String> getRuta() { return ruta; }
    public double getDistanciaTotal() { return distanciaTotal; }
    public boolean isExisteRuta() { return existeRuta; }

    @Override
    public String toString() {
        if (!existeRuta || ruta == null || ruta.estaVacia()) {
            return "No existe una ruta entre los puntos indicados.";
        }
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (String nombre : ruta) {
            sb.append(nombre);
            if (i < ruta.tamano() - 1) sb.append(" -> ");
            i++;
        }
        sb.append("  (distancia total: ").append(distanciaTotal).append(" km)");
        return sb.toString();
    }
}
