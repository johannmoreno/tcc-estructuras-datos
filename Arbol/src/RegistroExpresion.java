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
import java.time.LocalDateTime;

/**
 * Registro histórico de una expresión evaluada por el usuario.
 * Se guarda en el archivo binario de historial.
 *
 * Se entrega completa.
 */
public class RegistroExpresion implements Serializable {
    private static final long serialVersionUID = 1L;

    private String expresionOriginal;
    private String expresionPostfija;
    private double resultado;
    private String fechaHora;

    public RegistroExpresion(String expresionOriginal, String expresionPostfija, double resultado) {
        this.expresionOriginal = expresionOriginal;
        this.expresionPostfija = expresionPostfija;
        this.resultado = resultado;
        this.fechaHora = LocalDateTime.now().toString();
    }

    @Override
    public String toString() {
        return fechaHora + " | " + expresionOriginal + " = " + resultado
                + " (postfija: " + expresionPostfija + ")";
    }
}
