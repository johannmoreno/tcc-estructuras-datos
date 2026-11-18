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
 * JOHANN DAVID MORENO CASSIANI – <CÓDIGO> (integrante 2)
 * JUAN DAVID ALZATE GALVIS – <CÓDIGO> (integrante 3)
 * DAVID SANTIAGO ESCAMILLA CASTRO – <CÓDIGO> (integrante 4)
 * MOISES BANDA PEREZ – <CÓDIGO> (integrante 5)
 *
 ****************************************************************************/

import java.io.Serializable;

/**
 * Nodo de un árbol binario de expresión. Guarda un dato que puede ser
 * un operador ("+", "-", "*", "/") o un operando (un número, como
 * String, ej. "4" o "3.5"), y referencias a los hijos izquierdo y
 * derecho.
 *
 * Un nodo es una hoja (operando) cuando no tiene hijos.
 *
 * Se entrega completa.
 */
public class NodoArbol implements Serializable {
    private static final long serialVersionUID = 1L;

    private String dato;
    private NodoArbol izquierdo;
    private NodoArbol derecho;

    public NodoArbol(String dato) {
        this.dato = dato;
        this.izquierdo = null;
        this.derecho = null;
    }

    public String getDato() { return dato; }
    public NodoArbol getIzquierdo() { return izquierdo; }
    public NodoArbol getDerecho() { return derecho; }
    public void setIzquierdo(NodoArbol izquierdo) { this.izquierdo = izquierdo; }
    public void setDerecho(NodoArbol derecho) { this.derecho = derecho; }

    public boolean esHoja() { return izquierdo == null && derecho == null; }

    public boolean esOperador() {
        return dato.equals("+") || dato.equals("-") || dato.equals("*") || dato.equals("/");
    }

    @Override
    public String toString() { return dato; }
}
