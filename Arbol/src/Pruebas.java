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
 * Casos de prueba de consola para verificar la clase ArbolExpresion,
 * sin necesidad de abrir la interfaz gráfica.
 */
public class Pruebas {

    private static int total = 0;
    private static int exitosos = 0;

    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println(" CASOS DE PRUEBA - ARBOL DE EXPRESION");
        System.out.println("========================================\n");

        ArbolExpresion arbol = new ArbolExpresion();

        System.out.println("--- Caso 1: conversion infija -> postfija (sin parentesis) ---");
        verificar("convertirAPostfija('3 + 4') == '3 4 +'",
                "3 4 +", arbol.convertirAPostfija("3 + 4"));

        System.out.println("\n--- Caso 2: precedencia de operadores sin parentesis ---");
        verificar("convertirAPostfija('2 + 3 * 4') == '2 3 4 * +'",
                "2 3 4 * +", arbol.convertirAPostfija("2 + 3 * 4"));

        System.out.println("\n--- Caso 3: parentesis simples ---");
        verificar("convertirAPostfija('(4 + 5) * (3 - 1)') == '4 5 + 3 1 - *'",
                "4 5 + 3 1 - *", arbol.convertirAPostfija("(4 + 5) * (3 - 1)"));

        System.out.println("\n--- Caso 4: construccion del arbol (raiz y hijos) ---");
        NodoArbol raiz = arbol.construirArbol("3 4 +");
        verificar("raiz.getDato() == '+'", "+", datoSeguro(raiz));
        verificar("raiz.getIzquierdo().getDato() == '3'", "3", datoSeguro(raiz == null ? null : raiz.getIzquierdo()));
        verificar("raiz.getDerecho().getDato() == '4'", "4", datoSeguro(raiz == null ? null : raiz.getDerecho()));

        System.out.println("\n--- Caso 5: recorridos sobre '(4 + 5) * (3 - 1)' ---");
        ResultadoExpresion r1 = arbol.procesarExpresion("(4 + 5) * (3 - 1)");
        verificar("prefijo == '* + 4 5 - 3 1'", "* + 4 5 - 3 1", r1.prefijo);
        verificar("postorden == '4 5 + 3 1 - *'", "4 5 + 3 1 - *", r1.postfijo);
        verificar("infijo == '((4 + 5) * (3 - 1))'", "((4 + 5) * (3 - 1))", r1.infijoConParentesis);
        verificar("resultado == 18.0", 18.0, r1.resultado);

        System.out.println("\n--- Caso 6: expresion con parentesis anidados ---");
        ResultadoExpresion r2 = arbol.procesarExpresion("((2 + 3) * 4) - (6 / 2)");
        verificar("postfija == '2 3 + 4 * 6 2 / -'", "2 3 + 4 * 6 2 / -", r2.expresionPostfija);
        verificar("resultado == 17.0  ((2+3)*4=20, 6/2=3, 20-3=17)", 17.0, r2.resultado);

        System.out.println("\n--- Caso 7: numeros decimales ---");
        ResultadoExpresion r3 = arbol.procesarExpresion("1.5 + 2.5");
        verificar("resultado == 4.0", 4.0, r3.resultado);

        System.out.println("\n--- Caso 8: cargar y evaluar formulas de prueba desde JSON ---");
        try {
            String[] expresiones = LectorFormulasJSON.leer("datos/formulas_prueba.json");
            verificar("se leyeron expresiones de prueba (cantidad > 0)", true, expresiones.length > 0);
            System.out.println("   Expresiones leidas: " + expresiones.length);
            arbol.cargarFormulasDesdeJSON(expresiones);
        } catch (Exception e) {
            System.out.println("   FALLA (excepcion al leer/evaluar JSON): " + e.getMessage());
            total++;
        }

        System.out.println("\n========================================");
        System.out.println("RESULTADO FINAL: " + exitosos + " / " + total + " casos exitosos");
        System.out.println("========================================");
    }

    /**
     * Devuelve el dato de un nodo de forma segura, evitando que un
     * NullPointerException (por ejemplo, si construirArbol() aun no
     * esta implementado y devuelve null) detenga toda la ejecucion de
     * las pruebas.
     */
    private static String datoSeguro(NodoArbol nodo) {
        return (nodo == null) ? null : nodo.getDato();
    }

    private static void verificar(String descripcion, Object esperado, Object obtenido) {
        total++;
        boolean pasa;
        try {
            if (esperado instanceof Double && obtenido instanceof Double) {
                pasa = Math.abs((Double) esperado - (Double) obtenido) < 0.0001;
            } else {
                pasa = esperado.equals(obtenido);
            }
        } catch (Exception e) {
            pasa = false;
        }
        String estado = pasa ? "PASA" : "FALLA";
        if (pasa) exitosos++;
        System.out.println("[" + estado + "] " + descripcion
                + "  (esperado=" + esperado + ", obtenido=" + obtenido + ")");
    }
}
