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

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Utilidad de lectura del archivo de expresiones de prueba en formato
 * JSON.
 *
 * IMPORTANTE: esta clase NO es un parser JSON de propósito general.
 * Está escrita específicamente para el esquema fijo del archivo
 * "datos/formulas_prueba.json" de este proyecto:
 *
 * <pre>
 * {
 *   "expresiones": [
 *     "3 + 4",
 *     "(4 + 5) * (3 - 1)",
 *     "((2 + 3) * 4) - (6 / 2)"
 *   ]
 * }
 * </pre>
 *
 * Se entrega completamente resuelta: el objetivo de aprendizaje de
 * este proyecto es el TAD Árbol y la conversión infijo-postfijo con
 * pilas, no la escritura de un parser de JSON.
 *
 * Nota técnica: se usan expresiones regulares (java.util.regex), que
 * son una utilidad de procesamiento de texto, no una colección de
 * datos — su uso aquí no contradice la restricción de no usar
 * colecciones de java.util que aplica al resto del proyecto.
 */
public class LectorFormulasJSON {

    private static final Pattern PATRON_BLOQUE = Pattern.compile(
        "\"expresiones\"\\s*:\\s*\\[(.*?)\\]", Pattern.DOTALL);

    private static final Pattern PATRON_STRING = Pattern.compile("\"([^\"]*)\"");

    /**
     * Lee el archivo indicado y retorna el arreglo de expresiones de
     * prueba encontradas.
     *
     * @param rutaArchivo ruta del archivo JSON a leer.
     * @return las expresiones leídas, en el mismo orden del archivo.
     * @throws IOException si el archivo no existe o no puede leerse.
     */
    public static String[] leer(String rutaArchivo) throws IOException {
        String contenido = new String(Files.readAllBytes(Paths.get(rutaArchivo)));

        Lista<String> expresiones = new Lista<>();
        Matcher mBloque = PATRON_BLOQUE.matcher(contenido);
        if (mBloque.find()) {
            String bloque = mBloque.group(1);
            Matcher mString = PATRON_STRING.matcher(bloque);
            while (mString.find()) {
                expresiones.agregar(mString.group(1));
            }
        }

        String[] resultado = new String[expresiones.tamano()];
        for (int i = 0; i < expresiones.tamano(); i++) {
            resultado[i] = expresiones.obtener(i);
        }
        return resultado;
    }
}
