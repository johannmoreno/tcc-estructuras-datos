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
 * SAID ALEJANDRO MARTELO DIAZ – <CÓDIGO> (integrante 1)
 * JOHANN DAVID MORENO CASSIANI – <7502423002> (integrante 2)
 * JUAN DAVID ALZATE GALVIS – <CÓDIGO> (integrante 3)
 * DAVID SANTIAGO ESCAMILLA CASTRO – <7502410012> (integrante 4)
 * MOISES BANDA PEREZ – <CÓDIGO> (integrante 5)
 *
 ****************************************************************************/

import java.io.Serializable;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * TAD Lista. Implementación propia (sin java.util) con arreglo dinámico
 * que duplica su capacidad al llenarse. Reutilizada de la actividad de
 * Digiturno.
 */
public class Lista<T> implements Iterable<T>, Serializable {
    private static final long serialVersionUID = 1L;
    private static final int CAPACIDAD_INICIAL = 10;

    private Object[] elementos;
    private int tamano;

    public Lista() {
        elementos = new Object[CAPACIDAD_INICIAL];
        tamano = 0;
    }

    public void agregar(T elemento) {
        if (tamano == elementos.length) duplicarCapacidad();
        elementos[tamano] = elemento;
        tamano++;
    }

    @SuppressWarnings("unchecked")
    public T obtener(int indice) {
        validarIndice(indice);
        return (T) elementos[indice];
    }

    public int tamano() { return tamano; }
    public boolean estaVacia() { return tamano == 0; }

    private void validarIndice(int indice) {
        if (indice < 0 || indice >= tamano) {
            throw new IndexOutOfBoundsException("Índice fuera de rango: " + indice);
        }
    }

    private void duplicarCapacidad() {
        Object[] nuevo = new Object[elementos.length * 2];
        for (int i = 0; i < elementos.length; i++) nuevo[i] = elementos[i];
        elementos = nuevo;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < tamano; i++) {
            sb.append(elementos[i]);
            if (i < tamano - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int indiceActual = 0;

            @Override
            public boolean hasNext() { return indiceActual < tamano; }

            @Override
            @SuppressWarnings("unchecked")
            public T next() {
                if (!hasNext()) throw new NoSuchElementException();
                return (T) elementos[indiceActual++];
            }
        };
    }
}
