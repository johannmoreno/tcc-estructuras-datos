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

/**
 * TAD Pila (LIFO). Implementación propia con nodos enlazados.
 * Reutilizada de la actividad de Digiturno.
 */
public class Pila<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private static class Nodo<T> implements Serializable {
        private static final long serialVersionUID = 1L;
        T dato;
        Nodo<T> siguiente;
        Nodo(T dato) { this.dato = dato; }
    }

    private Nodo<T> tope;
    private int tamano;

    public Pila() { tope = null; tamano = 0; }

    public void apilar(T elemento) {
        Nodo<T> nuevo = new Nodo<>(elemento);
        nuevo.siguiente = tope;
        tope = nuevo;
        tamano++;
    }

    public T desapilar() {
        if (estaVacia()) throw new IllegalStateException("No se puede desapilar: la pila está vacía.");
        T dato = tope.dato;
        tope = tope.siguiente;
        tamano--;
        return dato;
    }

    public T verTope() {
        if (estaVacia()) throw new IllegalStateException("La pila está vacía.");
        return tope.dato;
    }

    public boolean estaVacia() { return tamano == 0; }
    public int tamano() { return tamano; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Nodo<T> actual = tope;
        while (actual != null) {
            sb.append(actual.dato);
            if (actual.siguiente != null) sb.append(", ");
            actual = actual.siguiente;
        }
        sb.append("]");
        return sb.toString();
    }
}
