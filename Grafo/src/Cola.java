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
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * TAD Cola (FIFO). Implementación propia con nodos enlazados.
 * Reutilizada de la actividad de Digiturno.
 */
public class Cola<T> implements Iterable<T>, Serializable {
    private static final long serialVersionUID = 1L;

    private static class Nodo<T> implements Serializable {
        private static final long serialVersionUID = 1L;
        T dato;
        Nodo<T> siguiente;
        Nodo(T dato) { this.dato = dato; }
    }

    private Nodo<T> frente;
    private Nodo<T> ultimo;
    private int tamano;

    public Cola() { frente = null; ultimo = null; tamano = 0; }

    public void encolar(T elemento) {
        Nodo<T> nuevo = new Nodo<>(elemento);
        if (estaVacia()) frente = nuevo; else ultimo.siguiente = nuevo;
        ultimo = nuevo;
        tamano++;
    }

    public T desencolar() {
        if (estaVacia()) throw new IllegalStateException("No se puede desencolar: la cola está vacía.");
        T dato = frente.dato;
        frente = frente.siguiente;
        if (frente == null) ultimo = null;
        tamano--;
        return dato;
    }

    public T verFrente() {
        if (estaVacia()) throw new IllegalStateException("La cola está vacía.");
        return frente.dato;
    }

    public boolean estaVacia() { return tamano == 0; }
    public int tamano() { return tamano; }

    public boolean eliminar(T elemento) {
        if (estaVacia()) return false;
        if (frente.dato.equals(elemento)) { desencolar(); return true; }
        Nodo<T> anterior = frente;
        Nodo<T> actual = frente.siguiente;
        while (actual != null) {
            if (actual.dato.equals(elemento)) {
                anterior.siguiente = actual.siguiente;
                if (actual == ultimo) ultimo = anterior;
                tamano--;
                return true;
            }
            anterior = actual;
            actual = actual.siguiente;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Nodo<T> actual = frente;
        while (actual != null) {
            sb.append(actual.dato);
            if (actual.siguiente != null) sb.append(", ");
            actual = actual.siguiente;
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Nodo<T> actual = frente;
            @Override public boolean hasNext() { return actual != null; }
            @Override public T next() {
                if (!hasNext()) throw new NoSuchElementException();
                T dato = actual.dato;
                actual = actual.siguiente;
                return dato;
            }
        };
    }
}
