# Bitácora de uso de IA generativa — Componente B
## Motor de Evaluación de Fórmulas (Árbol Binario de Expresión)

**Curso:** Estructuras de Datos — TCC de fin de curso
**Herramienta utilizada:** opencode (agente de código en el editor)
**Modelo:** DeepSeek V4.1 Flash Free
**Rol asignado a la IA:** tutor/asesor técnico. La IA leyó el código base, propuso la implementación, revisó a mano sintaxis/tipos/imports y explicó cada decisión. **No compiló, no ejecutó `Pruebas.java` y no usó Git**, porque el PowerShell del equipo está dañado. La compilación, la ejecución y los commits los realiza el CIPA desde IntelliJ IDEA.

**Integrantes del CIPA:**
- SAID ALEJANDRO MARTELO DIAZ
- JOHANN DAVID MORENO CASSIANI
- JUAN DAVID ALZATE GALVIS
- DAVID SANTIAGO ESCAMILLA CASTRO
- MOISES BANDA PEREZ

---

## Interacción — 2026-11-18 — Implementación completa del Componente B

### Prompt textual del estudiante (literal)

```
Eres un ingeniero Java experto. Trabaja en la carpeta
C:\Proyectos_UDC\TCC Estructura datos\ (confirma que es tu directorio de
trabajo; si no lo es, dímelo y no escribas nada). Allí están dos proyectos
ya extraídos (Grafo y Árbol) y los enunciados Grafos.md, Arbol.md y
Condiciones.md. Somos un CIPA de 5 estudiantes en IntelliJ IDEA.

ENTORNO: mi PowerShell está dañado. NO ejecutes comandos de shell, no compiles
ni uses git. Usa solo tus herramientas de leer, escribir, editar y buscar
archivos. Como no puedes compilar, antes de guardar cada método verifica a
mano sintaxis, tipos, imports y firmas, y traza mentalmente cada caso de
Pruebas.java contra tu código.

HAZ TODO EN UNA SOLA PASADA, SIN PEDIR CONFIRMACIÓN ENTRE PASOS:

1. LEE todos los .java y .json de ambos proyectos y los 3 enunciados.
2. COMPONENTE A: implementa directamente en Grafo.java los 11 TODO:
   agregarVertice, eliminarVertice, agregarArista, eliminarArista,
   sonAdyacentes, grado, recorridoBFS (con Cola), recorridoDFS (con Pila),
   dijkstra (arreglos dist/visitado/predecesor, retorna ResultadoRuta),
   cargarDesdeJSON (usa DatosMapa), guardarHistorialBinario
   (ObjectOutputStream en datos/historial_rutas.dat). Meta: 16/16 PASA en
   Pruebas.java.
3. COMPONENTE B: implementa directamente en ArbolExpresion.java los 8 TODO:
   convertirAPostfija (Pila, precedencia, paréntesis, decimales y números de
   varios dígitos), construirArbol (Pila<NodoArbol>), recorridoPreorden,
   recorridoInorden (con paréntesis), recorridoPostorden, evaluar (recursivo),
   cargarFormulasDesdeJSON, guardarHistorialBinario
   (datos/historial_expresiones.dat). Meta: 14/14 PASA. Respeta el formato
   exacto de postfija que esperan los casos de Pruebas.java. El "-" unario
   no está en alcance.
4. CABECERA DE HONOR: agrega al inicio de TODOS los .java de ambos proyectos
   (incluidos los entregados; en Cola, Pila, VentanaPrincipal, Main y Pruebas
   solo la cabecera, sin tocar nada más) este bloque, sin modificar el texto:

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

   En el Main de cada proyecto deja además los nombres y códigos. Los
   <CÓDIGO> los reemplazo yo después.
5. JAVADOC: todas las clases con su propósito. Cada método TODO con Javadoc
   propio (no genérico): qué hace, qué estructura/algoritmo usa, por qué es
   la decisión correcta de diseño, y complejidad.
6. Marca el código que generes con: // Generado con IA (opencode) – analizado
   y adaptado por el CIPA
7. REGLAS: PROHIBIDO java.util (ArrayList, HashMap, Stack, Queue, LinkedList,
   Arrays, Collections, etc.); solo arreglos propios y las clases Pila y Cola
   entregadas. Se permite java.io y java.time si el código base ya los usa.
   No modifiques Cola, Pila, VentanaPrincipal, Main ni Pruebas (salvo la
   cabecera). Maneja errores básicos (vértice inexistente, expresión vacía,
   división por cero, paréntesis desbalanceados) sin que el programa se caiga.
8. BITÁCORA (honesta): crea documentos/bitacora_ia_componente_a.md y
   documentos/bitacora_ia_componente_b.md (crea la carpeta si no existe).
   Registra: herramienta/modelo usado, mi prompt textual literal, resumen de
   lo que hiciste método por método, decisiones y limitaciones o casos borde
   dudosos de tu propia solución, y "Análisis del estudiante: (pendiente)" por
   cada método, más "Reflexión final: (pendiente)". No inventes análisis del
   estudiante: debe quedar pendiente para que lo completemos nosotros.
9. VERIFICACIÓN FINAL con búsqueda de archivos: (a) ningún import de java.util
   de colecciones,
```

### Resumen de lo que hizo la IA

1. Confirmó el directorio de trabajo y leyó los 22 `.java`, los 2 `.json` y los tres enunciados, sin ejecutar comandos de shell.
2. Implementó los 8 TODO de `ArbolExpresion.java` con la `Pila` propia, recursión sobre `NodoArbol` y sin colecciones de `java.util`.
3. Añadió la cabecera de honor al inicio de los 10 `.java` del proyecto Árbol; en `VentanaPrincipal` (que contiene el `main`) agregó además un comentario de cabecera con los nombres y códigos.
4. Reemplazó los Javadoc de los TODO por explicaciones de algoritmo, decisión de diseño y complejidad, y marcó el código generado con `// Generado con IA (opencode) – analizado y adaptado por el CIPA`.
5. Hizo una verificación manual de firmas, imports y de los 14 casos de `Pruebas.java`, sin compilar.

---

## Detalle método por método (Componente B)

### 1. `convertirAPostfija(String infija)`

**Qué se hizo:** se recorre la cadena carácter por carácter. Los dígitos y el punto se acumulan en `numeroActual`; al terminar un número se vuelca a la salida con un espacio. Los operadores se apilan con la regla de precedencia (shunting-yard); `(` se apila y `)` desapila hasta el `(`. Al final se vacía la pila. Se añadió el auxiliar privado `agregarToken` para separar tokens sin espacios dobles ni espacio final.

**Decisión:** la `Pila<Character>` es la estructura natural porque el último operador pendiente se resuelve primero. Se usa `StringBuilder` para evitar concatenaciones O(n²). Los paréntesis desbalanceados no lanzan excepción: se informa por consola y se continúa con una postfija parcial.

**Trazado:** `3 + 4` → `3 4 +`; `2 + 3 * 4` → `2 3 4 * +`; `(4 + 5) * (3 - 1)` → `4 5 + 3 1 - *`; `((2 + 3) * 4) - (6 / 2)` → `2 3 + 4 * 6 2 / -`.

**Análisis del estudiante:** (pendiente)

### 2. `construirArbol(String postfija)`

**Qué se hizo:** se separa la postfija con `trim().split("\\s+")`; cada operando se apila como hoja; cada operador desapila primero el hijo derecho y luego el izquierdo, crea un `NodoArbol` con ese operador, cuelga ambos hijos y apila el subárbol. Si la expresión está vacía, faltan operandos o sobran nodos, retorna `null`.

**Decisión:** el orden de desapilado es crítico para `-` y `/`, que no son conmutativos. Devolver `null` en lugar de lanzar excepciones permite que los recorridos y la evaluación manejen el error sin cerrar la aplicación.

**Trazado:** `3 4 +` → raíz `+`, izquierdo `3`, derecho `4`.

**Análisis del estudiante:** (pendiente)

### 3. `recorridoPreorden(NodoArbol nodo)`

**Qué se hizo:** caso base `null → ""`; se agrega el dato del nodo y luego, si no están vacíos, los resultados recursivos del izquierdo y del derecho, separados por un espacio.

**Decisión:** la recursión sigue la definición del árbol y no necesita pila explícita; `StringBuilder` evita concatenaciones repetidas. Complejidad O(n) en tiempo y O(h) en recursión.

**Trazado:** árbol de `(4 + 5) * (3 - 1)` → `* + 4 5 - 3 1`.

**Análisis del estudiante:** (pendiente)

### 4. `recorridoInorden(NodoArbol nodo)`

**Qué se hizo:** si el nodo es hoja devuelve su dato; si es operador devuelve `"(" + inorden(izquierdo) + " " + dato + " " + inorden(derecho) + ")"`.

**Decisión:** se parentiza cada nodo interno, no las hojas, para que la infija sea válida y sin ambigüedad. Complejidad O(n) y O(h).

**Trazado:** árbol de `(4 + 5) * (3 - 1)` → `((4 + 5) * (3 - 1))`.

**Análisis del estudiante:** (pendiente)

### 5. `recorridoPostorden(NodoArbol nodo)`

**Qué se hizo:** se recorre izquierdo, derecho y al final el nodo, uniendo las partes con un espacio y omitiendo las vacías.

**Decisión:** es el recorrido simétrico al preorden y debe coincidir con la postfija original para una expresión bien formada; sirve de autoverificación. Complejidad O(n) y O(h).

**Trazado:** `(4 + 5) * (3 - 1)` → `4 5 + 3 1 - *`.

**Análisis del estudiante:** (pendiente)

### 6. `evaluar(NodoArbol nodo)`

**Qué se hizo:** si el nodo es nulo retorna `0.0`; si es hoja convierte el dato con `Double.parseDouble`; si es operador evalúa ambos hijos y aplica `+`, `-`, `*` o `/` mediante un `switch`. Se captura `NumberFormatException` en operandos inválidos y se detecta la división por cero antes de dividir, informando y retornando `0.0`.

**Decisión:** la evaluación postorden recursiva resuelve primero los subárboles y luego combina en la raíz. El manejo defensivo evita que un dato mal formado o una división por cero cierren la GUI. Complejidad O(n) y O(h).

**Trazado:** `(4 + 5) * (3 - 1)` → `9 * 2 = 18.0`; `((2 + 3) * 4) - (6 / 2)` → `20 - 3 = 17.0`; `1.5 + 2.5` → `4.0`.

**Análisis del estudiante:** (pendiente)

### 7. `cargarFormulasDesdeJSON(String[] expresiones)`

**Qué se hizo:** se recorre el arreglo con un for-each, se omiten expresiones nulas o vacías, se llama a `procesarExpresion` y se imprime el `ResultadoExpresion` completo. Se capturan errores de ejecución para continuar con las demás expresiones. No se agregan al historial.

**Decisión:** reutilizar el pipeline ya resuelto evita duplicar la conversión, construcción y evaluación; respetar que el historial es solo para las expresiones manuales. Complejidad O(k·n).

**Análisis del estudiante:** (pendiente)

### 8. `guardarHistorialBinario()`

**Qué se hizo:** se crea la carpeta `datos` si no existe; se abre un `ObjectOutputStream` sobre `datos/historial_expresiones.dat` con try-with-resources y se serializa la `Lista<RegistroExpresion>` completa. Se informa cuántos registros se guardaron y se captura `IOException`.

**Decisión:** serialización binaria de objetos, igual que en la actividad de Digiturno; `Lista` y `RegistroExpresion` ya son `Serializable`. Solo se guardan las expresiones manuales. Complejidad O(H).

**Análisis del estudiante:** (pendiente)

---

## Decisiones, limitaciones y casos borde dudosos

1. **Menos unario fuera de alcance:** `-3 + 4` se interpreta como si el `-` fuera binario; el algoritmo no distingue un signo unario. Es una limitación declarada por el enunciado, no un error de la implementación.
2. **Paréntesis desbalanceados:** no se lanza excepción. Un `)` sin `(` se ignora; un `(` sin cerrar se descarta al vaciar la pila y se emite una advertencia. La postfija resultante puede ser parcial y `construirArbol` puede devolver `null`; los recorridos devuelven `""` y `evaluar` devuelve `0.0`.
3. **Caracteres no reconocidos:** se marcan como desbalanceados y se ignoran. No hay validación sintáctica completa, tal como permite el enunciado.
4. **Números mal formados:** `1.2.3` llega a `Double.parseDouble` y lanza `NumberFormatException`, que se captura en `evaluar` devolviendo `0.0` con una advertencia. No se valida el formato durante la conversión.
5. **División por cero:** se detecta con `derecho == 0.0` y se retorna `0.0`; es una decisión de robustez, no el resultado matemático (que sería infinito o indefinido).
6. **Expresión vacía:** `convertirAPostfija` devuelve `""`, `construirArbol` devuelve `null`, los recorridos devuelven `""` y `evaluar` devuelve `0.0`. La GUI no se cae, pero el resultado no es un error explícito.
7. **Formato de postfija:** se usa un espacio simple entre tokens y `trim()` al final, que es exactamente lo que esperan los casos de `Pruebas.java` (`"3 4 +"`, `"2 3 4 * +"`, etc.).
8. **Inorden con paréntesis en cada operador:** produce doble paréntesis en la raíz, por ejemplo `((4 + 5) * (3 - 1))`, que es el formato esperado por la prueba.
9. **Verificación:** no se compiló ni se ejecutó nada por la limitación del entorno. La comprobación de los 14 casos fue manual; la evidencia real de `PASA` debe generarla el CIPA en IntelliJ.
10. **Cabecera de honor:** se insertó el bloque textual en los 10 archivos. En `VentanaPrincipal` se añadió, además, un comentario de cabecera con los nombres y códigos, sin tocar la lógica de la interfaz ni el `main`.

---

## Reflexión final

(pendiente)
