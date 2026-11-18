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
 * Contenedor de todos los resultados producidos al procesar una
 * expresión: su forma postfija, los tres recorridos, el resultado
 * numérico, y la raíz del árbol construido (para poder dibujarlo).
 *
 * Se entrega completa: la produce el método ya resuelto
 * {@code ArbolExpresion.procesarExpresion(String)}, que internamente
 * llama a los métodos TODO que el estudiante debe completar.
 */
public class ResultadoExpresion implements Serializable {
    private static final long serialVersionUID = 1L;

    public String expresionOriginal;
    public String expresionPostfija;
    public String prefijo;
    public String infijoConParentesis;
    public String postfijo;
    public double resultado;
    public transient NodoArbol raiz; // no se serializa: solo se usa para dibujar en pantalla

    @Override
    public String toString() {
        return "Expresion: " + expresionOriginal
                + "\nPostfija:  " + expresionPostfija
                + "\nPrefijo:   " + prefijo
                + "\nInfijo:    " + infijoConParentesis
                + "\nPostorden: " + postfijo
                + "\nResultado: " + resultado;
    }
}
