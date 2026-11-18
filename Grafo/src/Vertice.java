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

import java.io.Serializable;

/**
 * Representa un punto (vértice) del mapa urbano: un cruce, una bodega o
 * una zona de entrega. Guarda el nombre del punto y las coordenadas
 * (x, y) usadas únicamente para dibujarlo en el panel gráfico.
 *
 * Esta clase se entrega completa.
 */
public class Vertice implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nombre;
    private int x;
    private int y;

    public Vertice(String nombre, int x, int y) {
        this.nombre = nombre;
        this.x = x;
        this.y = y;
    }

    public String getNombre() { return nombre; }
    public int getX() { return x; }
    public int getY() { return y; }

    @Override
    public String toString() { return nombre; }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Vertice)) return false;
        return nombre.equals(((Vertice) obj).nombre);
    }

    @Override
    public int hashCode() { return nombre.hashCode(); }
}
