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

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * TAD Árbol binario de expresión para el Motor de Evaluación de Fórmulas.
 *
 * <p>La clase convierte una expresión infija digitada por el usuario a
 * notación postfija, construye con ella un árbol binario de expresión, genera
 * los recorridos preorden, inorden y postorden, y evalúa el resultado. La
 * conversión y la construcción usan la clase {@link Pila} entregada; los
 * recorridos y la evaluación son recursivos sobre {@link NodoArbol}.</p>
 *
 * <p>No se usa ninguna colección de {@code java.util}: la única estructura
 * auxiliar es la pila propia, reutilizada dos veces con tipos diferentes
 * ({@code Character} durante la conversión y {@code NodoArbol} durante la
 * construcción). El historial se guarda en una {@link Lista} propia y se
 * serializa a un archivo binario.</p>
 */
public class ArbolExpresion {

    private NodoArbol raiz;
    private Lista<RegistroExpresion> historial;

    public ArbolExpresion() {
        raiz = null;
        historial = new Lista<>();
    }

    // ----------------------------------------------------------------
    // Métodos de apoyo YA RESUELTOS (no son TODO, no los modifiques).
    // ----------------------------------------------------------------

    public NodoArbol getRaiz() { return raiz; }

    public void agregarRegistroAlHistorial(RegistroExpresion r) { historial.agregar(r); }

    public Lista<RegistroExpresion> getHistorial() { return historial; }

    /**
     * Método ya resuelto que orquesta todo el pipeline: convierte la
     * expresión a postfija, construye el árbol, genera los tres
     * recorridos y evalúa el resultado. Internamente llama a los
     * métodos TODO 1 a 6 que debes completar más abajo. No necesitas
     * (ni debes) modificar este método.
     */
    public ResultadoExpresion procesarExpresion(String infija) {
        String postfija = convertirAPostfija(infija);
        NodoArbol raizArbol = construirArbol(postfija);
        this.raiz = raizArbol;

        ResultadoExpresion r = new ResultadoExpresion();
        r.expresionOriginal = infija;
        r.expresionPostfija = postfija;
        r.raiz = raizArbol;
        r.prefijo = recorridoPreorden(raizArbol);
        r.infijoConParentesis = recorridoInorden(raizArbol);
        r.postfijo = recorridoPostorden(raizArbol);
        r.resultado = evaluar(raizArbol);
        return r;
    }

    /** Precedencia de un operador: 2 para * y /, 1 para + y -, 0 para cualquier otro carácter. */
    private int precedencia(char operador) {
        if (operador == '+' || operador == '-') return 1;
        if (operador == '*' || operador == '/') return 2;
        return 0;
    }

    private boolean esOperador(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    private boolean esOperadorToken(String token) {
        return token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/");
    }

    /**
     * Agrega un token a la salida postfija separándolo con un espacio.
     *
     * <p>Centralizar la separación evita espacios dobles o un espacio final y
     * garantiza el formato exacto que exige {@link Pruebas}. No se usa
     * {@code String.join} ni ninguna colección porque se trabaja directamente
     * sobre un {@link StringBuilder}.</p>
     *
     * @param salida acumulador de la expresión postfija.
     * @param token token que se quiere anexar; si es nulo o vacío se ignora.
     */
    private void agregarToken(StringBuilder salida, String token) {
        // Generado con IA (opencode) – analizado y adaptado por el CIPA
        if (token == null || token.isEmpty()) return;
        if (salida.length() > 0) salida.append(' ');
        salida.append(token);
    }

    // ----------------------------------------------------------------
    // TODO 1: convertirAPostfija
    // ----------------------------------------------------------------
    /**
     * Convierte una expresión infija con paréntesis a notación postfija.
     *
     * <p><b>Qué hace:</b> recorre la cadena carácter por carácter. Los dígitos y
     * el punto decimal se acumulan en {@code numeroActual} para formar operandos
     * de varios caracteres (por ejemplo {@code "12"} o {@code "3.5"}). Al
     * terminar un número se vuelca a la salida. Los operadores se apilan
     * respetando la precedencia (shunting-yard simplificado); el paréntesis de
     * cierre fuerza a desapilar hasta el paréntesis de apertura, que se
     * descarta. Al final se vacía la pila.</p>
     *
     * <p><b>Estructura y decisión de diseño:</b> la {@link Pila} es la
     * estructura natural porque el último operador pendiente es el primero que
     * debe resolverse (LIFO). Se usa {@link StringBuilder} en lugar de
     * concatenaciones repetidas para evitar O(n^2) por creación de cadenas
     * intermedias. Los paréntesis desbalanceados no lanzan excepción: un cierre
     * sin apertura se ignora y una apertura sin cierre se descarta al final,
     * dejando que el resto del pipeline informe una expresión no evaluable.</p>
     *
     * <p><b>Complejidad:</b> O(n) en tiempo, porque cada token entra y sale de
     * la pila a lo sumo una vez; O(n) en espacio para la pila y la salida.</p>
     *
     * @param infija expresión en notación infija; puede ser {@code null} o
     *               vacía.
     * @return la expresión postfija con tokens separados por un espacio, o
     *         cadena vacía si la entrada no contiene nada aprovechable.
     */
    public String convertirAPostfija(String infija) {
        // Generado con IA (opencode) – analizado y adaptado por el CIPA
        if (infija == null) return "";

        StringBuilder salida = new StringBuilder();
        StringBuilder numeroActual = new StringBuilder();
        Pila<Character> pila = new Pila<>();
        int parentesisAbiertos = 0;
        boolean parentesisDesbalanceados = false;

        for (int i = 0; i < infija.length(); i++) {
            char c = infija.charAt(i);

            if (Character.isWhitespace(c)) continue;

            if (Character.isDigit(c) || c == '.') {
                numeroActual.append(c);
                continue;
            }

            if (numeroActual.length() > 0) {
                agregarToken(salida, numeroActual.toString());
                numeroActual.setLength(0);
            }

            if (c == '(') {
                pila.apilar(c);
                parentesisAbiertos++;
            } else if (c == ')') {
                if (parentesisAbiertos == 0) {
                    parentesisDesbalanceados = true;
                    continue;
                }
                while (!pila.estaVacia()) {
                    char tope = pila.desapilar();
                    if (tope == '(') break;
                    agregarToken(salida, String.valueOf(tope));
                }
                parentesisAbiertos--;
            } else if (esOperador(c)) {
                while (!pila.estaVacia() && pila.verTope() != '('
                        && precedencia(pila.verTope()) >= precedencia(c)) {
                    agregarToken(salida, String.valueOf(pila.desapilar()));
                }
                pila.apilar(c);
            } else {
                parentesisDesbalanceados = true;
            }
        }

        if (numeroActual.length() > 0) {
            agregarToken(salida, numeroActual.toString());
        }

        while (!pila.estaVacia()) {
            char tope = pila.desapilar();
            if (tope == '(') {
                parentesisDesbalanceados = true;
            } else {
                agregarToken(salida, String.valueOf(tope));
            }
        }

        if (parentesisDesbalanceados) {
            System.out.println("Advertencia: la expresion contiene parentesis "
                    + "desbalanceados o caracteres no reconocidos.");
        }

        return salida.toString().trim();
    }

    // ----------------------------------------------------------------
    // TODO 2: construirArbol
    // ----------------------------------------------------------------
    /**
     * Construye el árbol binario de expresión a partir de la postfija.
     *
     * <p><b>Qué hace:</b> separa la cadena por espacios y procesa cada token.
     * Un operando se apila como hoja; un operador desapila dos nodos (primero el
     * derecho y luego el izquierdo, porque la resta y la división no son
     * conmutativas), los cuelga del nuevo nodo operador y apila el subárbol
     * resultante. Al final la pila debe contener exactamente la raíz.</p>
     *
     * <p><b>Estructura y decisión de diseño:</b> la {@link Pila} de
     * {@link NodoArbol} refleja la evaluación postfija: los dos últimos
     * operandos son los hijos de la operación actual. Invertir el orden de
     * desapilado produciría árboles incorrectos para {@code -} y {@code /}. Si
     * la expresión está vacía o mal formada (faltan operandos o sobran nodos),
     * se retorna {@code null} en lugar de lanzar una excepción, y los recorridos
     * y la evaluación saben manejar ese caso.</p>
     *
     * <p><b>Complejidad:</b> O(t) en tiempo y O(t) en espacio, donde t es el
     * número de tokens; cada token se apila y se desapila una vez.</p>
     *
     * @param postfija expresión postfija con tokens separados por espacios.
     * @return la raíz del árbol, o {@code null} si la expresión está vacía o es
     *         inválida.
     */
    public NodoArbol construirArbol(String postfija) {
        // Generado con IA (opencode) – analizado y adaptado por el CIPA
        if (postfija == null || postfija.trim().isEmpty()) return null;

        String[] tokens = postfija.trim().split("\\s+");
        Pila<NodoArbol> pila = new Pila<>();

        for (int i = 0; i < tokens.length; i++) {
            String token = tokens[i];
            if (token.isEmpty()) continue;

            if (esOperadorToken(token)) {
                if (pila.tamano() < 2) return null;
                NodoArbol derecho = pila.desapilar();
                NodoArbol izquierdo = pila.desapilar();
                NodoArbol operador = new NodoArbol(token);
                operador.setIzquierdo(izquierdo);
                operador.setDerecho(derecho);
                pila.apilar(operador);
            } else {
                pila.apilar(new NodoArbol(token));
            }
        }

        if (pila.tamano() != 1) return null;
        return pila.desapilar();
    }

    // ----------------------------------------------------------------
    // TODO 3: recorridoPreorden
    // ----------------------------------------------------------------
    /**
     * Genera el recorrido en preorden (prefijo) de un subárbol.
     *
     * <p><b>Qué hace:</b> visita el nodo, luego el hijo izquierdo y luego el
     * derecho, uniendo las partes con un espacio. El caso base es el nodo nulo,
     * que devuelve cadena vacía.</p>
     *
     * <p><b>Estructura y decisión de diseño:</b> la recursión sigue exactamente
     * la definición estructural del árbol binario y no necesita estructuras
     * auxiliares ni estado compartido. Se usa {@link StringBuilder} para
     * concatenar de forma eficiente y se omiten los espacios cuando un subárbol
     * está vacío, de modo que el resultado no tenga separadores sobrantes.</p>
     *
     * <p><b>Complejidad:</b> O(n) en tiempo, donde n es el número de nodos;
     * O(h) en espacio de pila de llamadas, con h la altura del árbol.</p>
     *
     * @param nodo raíz del subárbol a recorrer; puede ser {@code null}.
     * @return los datos en preorden separados por espacios.
     */
    public String recorridoPreorden(NodoArbol nodo) {
        // Generado con IA (opencode) – analizado y adaptado por el CIPA
        if (nodo == null) return "";

        StringBuilder resultado = new StringBuilder();
        resultado.append(nodo.getDato());

        String izquierdo = recorridoPreorden(nodo.getIzquierdo());
        if (!izquierdo.isEmpty()) resultado.append(' ').append(izquierdo);

        String derecho = recorridoPreorden(nodo.getDerecho());
        if (!derecho.isEmpty()) resultado.append(' ').append(derecho);

        return resultado.toString();
    }

    // ----------------------------------------------------------------
    // TODO 4: recorridoInorden
    // ----------------------------------------------------------------
    /**
     * Genera el recorrido en inorden (infijo) con paréntesis.
     *
     * <p><b>Qué hace:</b> si el nodo es una hoja, devuelve su operando sin
     * paréntesis; si es un operador, devuelve
     * {@code (izquierdo operador derecho)} usando las llamadas recursivas de
     * ambos hijos. Así cada subexpresión queda delimitada y la precedencia
     * original se conserva sin ambigüedad.</p>
     *
     * <p><b>Estructura y decisión de diseño:</b> los paréntesis se agregan
     * únicamente en los nodos internos, que son los operadores; envolver también
     * las hojas produciría ruido innecesario. El recorrido recursivo respeta el
     * orden izquierdo-nodo-derecho y refleja la estructura real del árbol, no
     * una reconstrucción textual de la entrada.</p>
     *
     * <p><b>Complejidad:</b> O(n) en tiempo y O(h) en espacio de recursión.</p>
     *
     * @param nodo raíz del subárbol a recorrer; puede ser {@code null}.
     * @return la expresión infija completamente parentizada.
     */
    public String recorridoInorden(NodoArbol nodo) {
        // Generado con IA (opencode) – analizado y adaptado por el CIPA
        if (nodo == null) return "";
        if (nodo.esHoja()) return nodo.getDato();

        String izquierdo = recorridoInorden(nodo.getIzquierdo());
        String derecho = recorridoInorden(nodo.getDerecho());
        return "(" + izquierdo + " " + nodo.getDato() + " " + derecho + ")";
    }

    // ----------------------------------------------------------------
    // TODO 5: recorridoPostorden
    // ----------------------------------------------------------------
    /**
     * Genera el recorrido en postorden (postfijo) de un subárbol.
     *
     * <p><b>Qué hace:</b> visita el hijo izquierdo, luego el derecho y al final
     * el nodo, separando las partes con un espacio. Para una expresión bien
     * formada, el resultado coincide exactamente con la postfija de entrada; por
     * eso sirve como autoverificación del árbol construido.</p>
     *
     * <p><b>Estructura y decisión de diseño:</b> es el recorrido simétrico al
     * preorden; la recursión evita una pila explícita y mantiene el código
     * alineado con la definición del TAD. Se omiten espacios cuando un subárbol
     * es nulo para no generar tokens vacíos.</p>
     *
     * <p><b>Complejidad:</b> O(n) en tiempo y O(h) en espacio de recursión.</p>
     *
     * @param nodo raíz del subárbol a recorrer; puede ser {@code null}.
     * @return los datos en postorden separados por espacios.
     */
    public String recorridoPostorden(NodoArbol nodo) {
        // Generado con IA (opencode) – analizado y adaptado por el CIPA
        if (nodo == null) return "";

        StringBuilder resultado = new StringBuilder();

        String izquierdo = recorridoPostorden(nodo.getIzquierdo());
        if (!izquierdo.isEmpty()) resultado.append(izquierdo);

        String derecho = recorridoPostorden(nodo.getDerecho());
        if (!derecho.isEmpty()) {
            if (resultado.length() > 0) resultado.append(' ');
            resultado.append(derecho);
        }

        if (resultado.length() > 0) resultado.append(' ');
        resultado.append(nodo.getDato());

        return resultado.toString();
    }

    // ----------------------------------------------------------------
    // TODO 6: evaluar
    // ----------------------------------------------------------------
    /**
     * Evalúa recursivamente el árbol y retorna el resultado numérico.
     *
     * <p><b>Qué hace:</b> si el nodo es una hoja, convierte su dato a
     * {@code double}; si es un operador, evalúa ambos hijos y aplica la
     * operación. Una hoja con texto no numérico y un nodo desconocido retornan
     * {@code 0.0} en lugar de lanzar una excepción. La división por cero se
     * detecta antes de dividir, se informa y retorna {@code 0.0} para que la
     * interfaz no se cierre.</p>
     *
     * <p><b>Estructura y decisión de diseño:</b> la evaluación postorden
     * recursiva es la forma natural de un árbol de expresión: primero se
     * resuelven los subárboles y luego se combinan en la raíz. Se usa
     * {@code switch} sobre el {@code String} del operador para que el código sea
     * legible y para centralizar las cuatro operaciones permitidas. No se usa
     * {@code java.util} ni conversiones innecesarias.</p>
     *
     * <p><b>Complejidad:</b> O(n) en tiempo y O(h) en espacio de recursión.</p>
     *
     * @param nodo raíz del subárbol a evaluar; puede ser {@code null}.
     * @return el valor numérico de la expresión, o {@code 0.0} si no se puede
     *         evaluar.
     */
    public double evaluar(NodoArbol nodo) {
        // Generado con IA (opencode) – analizado y adaptado por el CIPA
        if (nodo == null) return 0.0;

        if (nodo.esHoja()) {
            try {
                return Double.parseDouble(nodo.getDato());
            } catch (NumberFormatException e) {
                System.out.println("Advertencia: operando no numerico: " + nodo.getDato());
                return 0.0;
            }
        }

        String dato = nodo.getDato();
        if (dato == null) return 0.0;

        double izquierdo = evaluar(nodo.getIzquierdo());
        double derecho = evaluar(nodo.getDerecho());

        switch (dato) {
            case "+":
                return izquierdo + derecho;
            case "-":
                return izquierdo - derecho;
            case "*":
                return izquierdo * derecho;
            case "/":
                if (derecho == 0.0) {
                    System.out.println("Advertencia: division por cero; se retorna 0.0.");
                    return 0.0;
                }
                return izquierdo / derecho;
            default:
                System.out.println("Advertencia: operador no reconocido: " + dato);
                return 0.0;
        }
    }

    // ----------------------------------------------------------------
    // TODO 7: cargarFormulasDesdeJSON
    // ----------------------------------------------------------------
    /**
     * Evalúa automáticamente las expresiones de prueba leídas del JSON.
     *
     * <p><b>Qué hace:</b> recorre el arreglo con un for-each y, por cada
     * expresión, ejecuta {@link #procesarExpresion(String)} e imprime el
     * {@link ResultadoExpresion} completo. Las expresiones nulas o vacías se
     * omiten y cualquier error de ejecución se captura para no detener la carga
     * del resto del banco de pruebas.</p>
     *
     * <p><b>Estructura y decisión de diseño:</b> se reutiliza el pipeline ya
     * resuelto en lugar de duplicar la conversión, construcción y evaluación.
     * Estas expresiones no se agregan al historial, porque el enunciado reserva
     * el historial para las expresiones evaluadas manualmente por el usuario.</p>
     *
     * <p><b>Complejidad:</b> O(k*n) en tiempo, donde k es el número de
     * expresiones y n el tamaño promedio de cada una; O(1) adicional aparte de
     * los árboles temporales.</p>
     *
     * @param expresiones arreglo de expresiones leído del JSON; puede ser
     *                    {@code null}.
     */
    public void cargarFormulasDesdeJSON(String[] expresiones) {
        // Generado con IA (opencode) – analizado y adaptado por el CIPA
        if (expresiones == null) return;

        for (String expresion : expresiones) {
            if (expresion == null || expresion.trim().isEmpty()) continue;
            try {
                ResultadoExpresion resultado = procesarExpresion(expresion);
                System.out.println(resultado);
                System.out.println("----------------------------------------");
            } catch (RuntimeException e) {
                System.out.println("Error al procesar la expresion '" + expresion
                        + "': " + e.getMessage());
            }
        }
    }

    // ----------------------------------------------------------------
    // TODO 8: guardarHistorialBinario
    // ----------------------------------------------------------------
    /**
     * Guarda el historial de expresiones evaluadas por el usuario en
     * {@code datos/historial_expresiones.dat}.
     *
     * <p><b>Qué hace:</b> crea la carpeta {@code datos} si no existe, abre un
     * {@link ObjectOutputStream} con try-with-resources y serializa la
     * {@link Lista} completa del historial; luego informa cuántos registros se
     * guardaron.</p>
     *
     * <p><b>Estructura y decisión de diseño:</b> se serializa el objeto
     * {@link Lista} tal como se hizo en la actividad de Digiturno, lo que
     * conserva los objetos {@link RegistroExpresion} con todos sus campos. El
     * try-with-resources garantiza el cierre del flujo inclusive si ocurre un
     * error de entrada/salida. Solo se guardan las expresiones agregadas
     * manualmente, no las de prueba cargadas desde JSON.</p>
     *
     * <p><b>Complejidad:</b> O(H) en tiempo y espacio de escritura, donde H es
     * el número de registros del historial.</p>
     */
    public void guardarHistorialBinario() {
        // Generado con IA (opencode) – analizado y adaptado por el CIPA
        File carpeta = new File("datos");
        if (!carpeta.exists()) {
            carpeta.mkdirs();
        }

        try (ObjectOutputStream salida = new ObjectOutputStream(
                new FileOutputStream("datos/historial_expresiones.dat"))) {
            salida.writeObject(historial);
            System.out.println("Historial de expresiones guardado en "
                    + "datos/historial_expresiones.dat (" + historial.tamano()
                    + " registros).");
        } catch (IOException e) {
            System.out.println("Error al guardar el historial de expresiones: "
                    + e.getMessage());
        }
    }
}
