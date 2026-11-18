# Bitácora de uso de IA generativa — Componente A
## Sistema de Rutas de Entrega Urbana (Grafo, BFS, DFS y Dijkstra)

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

## Interacción — 2026-11-18 — Implementación completa del Componente A

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

1. Confirmó que el directorio de trabajo era `C:\Proyectos_UDC\TCC Estructura datos\` y leyó los 22 `.java`, los 2 `.json` y los tres enunciados (sin ejecutar comandos de shell).
2. Implementó los 11 TODO de `Grafo.java` respetando la matriz de adyacencia, la clase `Lista`, la `Cola` y la `Pila` entregadas.
3. Añadió la cabecera de honor al inicio de los 12 `.java` del proyecto Grafo; en `VentanaPrincipal` (que contiene el `main`) agregó además un comentario de cabecera con los nombres y códigos del CIPA.
4. Reemplazó los Javadoc de los TODO por explicaciones de algoritmo, decisión de diseño y complejidad, y marcó el código generado con `// Generado con IA (opencode) – analizado y adaptado por el CIPA`.
5. Hizo una verificación manual de firmas, imports y de los 16 casos de `Pruebas.java`, sin compilar.

---

## Detalle método por método (Componente A)

### 1. `agregarVertice(String nombre, int x, int y)`

**Qué se hizo:** se valida que el nombre no sea nulo ni vacío, se comprueba con `indiceDe` que no esté repetido, se llama a `asegurarCapacidad()` y se agrega `new Vertice(nombre, x, y)` a la `Lista`. La matriz no se toca porque las celdas nuevas ya nacen en `SIN_CONEXION`.

**Decisión:** mantener la correspondencia exacta entre el índice de la `Lista` y el índice de la matriz. Complejidad O(V), con crecimiento amortizado O(V²) cuando se duplica la capacidad.

**Análisis del estudiante:** (pendiente)

### 2. `eliminarVertice(String nombre)`

**Qué se hizo:** se localiza el índice; si no existe se retorna `false`. Se construye una nueva `Lista<Vertice>` sin ese elemento y una nueva matriz del mismo tamaño de capacidad, copiando filas y columnas con dos contadores independientes para "cerrar el hueco". Se reemplazan los campos `vertices` y `distancias`.

**Decisión:** reconstruir en vez de dejar índices muertos; así todos los demás métodos siguen usando índices contiguos. Complejidad O(V²).

**Análisis del estudiante:** (pendiente)

### 3. `agregarArista(String v1, String v2, double distancia)`

**Qué se hizo:** se obtienen ambos índices; si alguno no existe se retorna `false`. Se rechazan distancias `NaN` o negativas para no confundirlas con `SIN_CONEXION`; luego se escribe la distancia en `distancias[i][j]` y `distancias[j][i]`.

**Decisión:** escritura simétrica propia de un grafo no dirigido. La distancia cero se acepta; el lazo (v1 == v2) no se rechaza explícitamente, pero el grado ignora la diagonal. Complejidad O(V) por las búsquedas.

**Análisis del estudiante:** (pendiente)

### 4. `eliminarArista(String v1, String v2)`

**Qué se hizo:** se obtienen los índices y se escribe `SIN_CONEXION` en ambas posiciones simétricas, sin eliminar vértices ni redimensionar la matriz.

**Decisión:** en una matriz, eliminar una arista es restaurar el centinela; no hay que desplazar datos. Complejidad O(V) por las búsquedas y O(1) para las asignaciones.

**Análisis del estudiante:** (pendiente)

### 5. `sonAdyacentes(String v1, String v2)`

**Qué se hizo:** se traducen los nombres a índices y se compara la celda con `SIN_CONEXION`. Si algún nombre no existe, retorna `false` en lugar de lanzar una excepción.

**Decisión:** es la consulta que aprovecha la matriz; una vez conocidos los índices la respuesta es O(1). Complejidad total O(V) por las búsquedas lineales.

**Análisis del estudiante:** (pendiente)

### 6. `grado(String v)`

**Qué se hizo:** se recorre la fila del vértice y se cuentan las celdas distintas de `SIN_CONEXION`, excluyendo la diagonal. Si el vértice no existe retorna `-1`.

**Decisión:** en un grafo no dirigido basta una fila; la diagonal se excluye para no contar un punto consigo mismo. Complejidad O(V).

**Análisis del estudiante:** (pendiente)

### 7. `recorridoBFS(String origen)`

**Qué se hizo:** se marca el origen, se encola su índice y se repite: desencolar, agregar el nombre al resultado y encolar todos los vecinos no marcados en orden de índice.

**Decisión:** la `Cola` entregada garantiza el orden FIFO por niveles. Se encola el índice, no el nombre, para evitar búsquedas repetidas. El arreglo `boolean[]` evita ciclos. Complejidad O(V²) con matriz; espacio O(V).

**Trazado de la prueba:** A se marca y se encola; se procesan B y C; luego D. Resultado esperado `[A, B, C, D]`.

**Análisis del estudiante:** (pendiente)

### 8. `recorridoDFS(String origen)`

**Qué se hizo:** el mismo esquema de BFS, pero con `Pila<Integer>`: se marca al apilar para que un vértice no quede duplicado y se desapila el último agregado.

**Decisión:** cambiar FIFO por LIFO es lo que convierte el recorrido por niveles en recorrido en profundidad. Se reutiliza la `Pila` de la actividad de Digiturno. Complejidad O(V²); espacio O(V).

**Trazado de la prueba:** se apilan B y C; sale C, luego se apila D; salen D y finalmente B. Resultado esperado `[A, C, D, B]`.

**Análisis del estudiante:** (pendiente)

### 9. `dijkstra(String origen, String destino)`

**Qué se hizo:** se crean `dist`, `visitado` y `predecesor`; se inicializa `dist[origen]=0` y el resto en infinito. En cada iteración se selecciona el no visitado con menor distancia, se marca y se relajan sus vecinos. Si el destino queda inalcanzable se retorna un `ResultadoRuta` sin ruta. Si hay ruta, se reconstruye con una `Pila<String>` para invertir el orden.

**Decisión:** variante O(V²) con arreglos, adecuada para matriz densa y sin cola de prioridad de `java.util`. La relajación estricta evita ciclos en los predecesores. Complejidad O(V²); espacio O(V).

**Trazado de la prueba:** A→B=2, A→C=10; al procesar B se mejora C a 5; al procesar C se mejora D a 6. Ruta `[A, B, C, D]`, distancia `6.0`.

**Análisis del estudiante:** (pendiente)

### 10. `cargarDesdeJSON(DatosMapa datos)`

**Qué se hizo:** se recorre `datos.vertices` llamando a `agregarVertice` y luego los arreglos paralelos de aristas llamando a `agregarArista`. Se usa `Math.min` de los tres tamaños para tolerar arreglos incompletos y se ignoran vértices nulos.

**Decisión:** delegar en los métodos públicos evita duplicar validaciones y mantiene el grafo independiente del parser JSON. Complejidad O(V² + E·V) por las búsquedas de nombres; espacio O(V²).

**Análisis del estudiante:** (pendiente)

### 11. `guardarHistorialBinario()`

**Qué se hizo:** se crea la carpeta `datos` con `File.mkdirs()` si no existe; se abre un `ObjectOutputStream` sobre `datos/historial_rutas.dat` con try-with-resources y se serializa la `Lista<ConsultaRuta>` completa. Se imprime el número de registros y se captura `IOException`.

**Decisión:** serialización binaria de objetos, igual que en la actividad de Digiturno; `Lista` y `ConsultaRuta` ya implementan `Serializable`. Complejidad O(H) en tiempo y espacio de escritura.

**Análisis del estudiante:** (pendiente)

---

## Decisiones, limitaciones y casos borde dudosos

1. **Nombres de vértices:** la comparación es sensible a mayúsculas/minúsculas y `Grafo` no hace `trim` interno; la interfaz sí recorta el texto. Si se llama directamente a `agregarVertice(" A ", ...)` y a `agregarVertice("A", ...)`, se consideran distintos. Es una decisión simple, pero puede sorprender.
2. **Distancias:** se rechazan `NaN` y valores negativos, pero se acepta `0.0`. No se rechaza explícitamente un lazo (`v1.equals(v2)`); si se agrega, el grado no lo cuenta por la exclusión de la diagonal, pero `sonAdyacentes(v, v)` devolvería `true`.
3. **`eliminarVertice` no reduce `capacidad`:** la matriz nueva conserva el tamaño de capacidad actual. Se prioriza no reasignar continuamente; el espacio sigue siendo O(capacidad²).
4. **Dijkstra y vértices inexistentes:** se devuelve un resultado sin ruta, no una excepción. Si origen y destino son el mismo, la ruta es `[origen]` con distancia `0.0` y `existeRuta = true`.
5. **Desempate de Dijkstra:** si dos caminos tienen la misma distancia, se conserva el primero que mejoró el predecesor, que depende del orden de índices de la matriz. La prueba no depende de un empate.
6. **Persistencia:** el método solo escribe; el código base no trae un lector de `historial_rutas.dat`, así que la lectura se haría con `ObjectInputStream` en una ampliación. No se guarda el grafo completo, solo el historial, tal como pide el enunciado.
7. **Verificación:** no se compiló ni se ejecutó nada por la limitación del entorno. La comprobación de los 16 casos fue manual; la evidencia real de `PASA` debe generarla el CIPA en IntelliJ.
8. **Cabecera de honor:** se insertó el bloque textual en los 12 archivos. En `VentanaPrincipal` se añadió, además, un comentario de cabecera con los nombres y códigos, sin tocar la lógica de la interfaz ni el `main`.

---

## Reflexión final

(pendiente)
