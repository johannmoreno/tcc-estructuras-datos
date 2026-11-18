# Proyecto de Fin de Curso — Componente B
## Motor de Evaluación de Fórmulas (Árbol Binario de Expresión)

**Curso:** Estructuras de Datos\
**Unidad:** Árboles y Grafos (estructuras no lineales)\
**Tipo de actividad:** Trabajo Colaborativo Contextualizado (TCC) — proyecto de fin de curso\
**Lenguaje:** Java (sin colecciones de `java.util`)\
**Modalidad:** Distancia — entrega y sustentación según calendario del curso

> **El TCC de fin de curso consta de DOS componentes obligatorios: este documento (Componente B — Árbol de Expresión) y el de Grafos y Dijkstra (Componente A).** Cada CIPA debe desarrollar y entregar **ambos** proyectos completos; no es una elección entre uno u otro. Ver el documento **"Condiciones del Trabajo Colaborativo Contextualizado (TCC)"** para los entregables comunes a ambos componentes.

---

## 1. Contexto del problema

Una empresa necesita un pequeño **motor de cálculo de fórmulas** para evaluar expresiones matemáticas que digitan sus usuarios (por ejemplo, fórmulas para liquidar una nota ponderada, o calcular un valor a pagar), sin tener que programar cada fórmula de forma independiente en el sistema.

El usuario debe poder escribir una expresión matemática común, con paréntesis, como:

```
(4 + 5) * (3 - 1)
```

y el sistema debe:

- Entender correctamente la precedencia de operadores y el uso de paréntesis.
- Calcular el resultado numérico.
- Mostrar la expresión reorganizada de tres formas distintas (prefija, infija y postfija), útiles para depurar o auditar cómo el sistema interpretó la fórmula.
- Mostrar gráficamente la estructura de la expresión como un árbol.

---

## 2. Relación con el curso y el resultado de aprendizaje

Este proyecto aporta evidencia al resultado de aprendizaje:

> *"Analiza, especifica e implementa las estructuras de datos y las colecciones no lineales desde la perspectiva de los TAD."*

A diferencia de la actividad inicial de unidad (digiturno, que trabajó estructuras **lineales**), este proyecto aborda la otra estructura no lineal central del curso: el **árbol binario**, en su aplicación clásica de árbol de expresión.

El proyecto exige además **integración de conocimientos previos**: tanto la conversión de infijo a postfijo como la construcción del árbol a partir de la expresión postfija se resuelven usando la clase `Pila<T>` construida en la actividad de digiturno — la misma estructura, aplicada dos veces, a dos tipos de datos distintos (caracteres primero, nodos de árbol después), lo que refuerza el concepto de TAD genérico.

**Importante:** este proyecto agrega dos componentes que no estaban presentes en la actividad inicial de unidad y que elevan su dificultad a nivel medio-alto:

- **Interfaz gráfica de escritorio** (Java Swing), para digitar la expresión y visualizar el árbol resultante.
- **Persistencia mixta**: lectura de expresiones de prueba desde **JSON** y escritura de historial en **archivo binario**.
- **Recursividad**: a diferencia de los recorridos iterativos del Componente A (BFS/DFS sobre el grafo), aquí los recorridos y la evaluación del árbol se resuelven de forma recursiva, lo cual representa un salto de complejidad conceptual respecto a las estructuras lineales ya vistas.

---

## 3. Objetivo del proyecto

Implementar un árbol binario de expresión propio (sin `java.util`), capaz de construirse a partir de una expresión infija con paréntesis, evaluarse numéricamente, generar sus tres recorridos clásicos, y presentar sus resultados —incluyendo una representación gráfica del árbol— en una interfaz de escritorio.

---

## 4. Descripción funcional del sistema a construir

El sistema debe permitir:

1. **Cargar un banco de expresiones de prueba** desde un archivo `formulas_prueba.json`, al iniciar la aplicación, evaluando automáticamente cada una para que el estudiante pueda verificar el funcionamiento sin digitar nada manualmente.
2. **Digitar una expresión matemática infija con paréntesis** en la interfaz gráfica, usando los operadores `+`, `-`, `*`, `/` y operandos numéricos enteros o decimales.
3. **Convertir la expresión infija a postfija**, manejando correctamente la precedencia de operadores (`*` y `/` antes que `+` y `-`) y el uso de paréntesis para forzar un orden distinto.
4. **Construir el árbol binario de expresión** a partir de la expresión postfija: cada operando queda como nodo hoja, cada operador queda como nodo interno con sus dos operandos (o subexpresiones) como hijos izquierdo y derecho.
5. **Evaluar el árbol** y mostrar el resultado numérico de la expresión.
6. **Mostrar los tres recorridos del árbol:**
   - **Preorden** (prefijo): operador antes que sus operandos.
   - **Inorden** (infijo): operando/subexpresión izquierda, operador, operando/subexpresión derecha — **agregando paréntesis** alrededor de cada subexpresión para que el resultado sea una expresión infija válida y sin ambigüedad.
   - **Postorden** (postfijo): operandos antes que el operador.
7. **Dibujar el árbol** en un panel gráfico, mostrando los nodos (operadores y operandos) y las conexiones entre ellos, organizados por niveles.
8. **Guardar cada expresión evaluada por el usuario** (expresión original, resultado, recorridos generados, fecha y hora) en un archivo binario de historial (`historial_expresiones.dat`).

---

## 5. Estructuras de datos a utilizar y su justificación

**No se permite el uso de ninguna colección de `java.util`** (`ArrayList`, `LinkedList`, `Stack`, etc.). Todas las estructuras deben ser propias del proyecto.

| Necesidad del sistema | TAD / mecanismo | Justificación |
|---|---|---|
| Convertir la expresión infija a postfija | **Pila\<Character\>** (o de `String`, según cómo se tokenice la expresión) | Algoritmo clásico (shunting-yard simplificado): se necesita "recordar" el último operador u paréntesis abierto pendiente de resolver, que es exactamente el comportamiento LIFO de la pila. |
| Construir el árbol de expresión a partir de la expresión postfija | **Pila\<NodoArbol\>** | Cada operando se apila como hoja; cada operador retira dos nodos de la pila (los hijos) y apila el nuevo subárbol formado — el mismo TAD Pila, ahora con un tipo de dato distinto. |
| La expresión estructurada en sí | **Árbol binario propio** (`NodoArbol`: dato, hijo izquierdo, hijo derecho) | Es la estructura no lineal central de este proyecto. No existe una clase de árbol de propósito general en `java.util` que resuelva esto de todas formas, por lo que se construye desde cero. |
| Recorridos y evaluación del árbol | **Recursividad** sobre `NodoArbol` | Es la forma natural y estándar de recorrer un árbol binario; no requiere una estructura de datos adicional, sino aplicar correctamente la llamada recursiva sobre el hijo izquierdo y el hijo derecho. |

---

## 6. Persistencia de datos

Este proyecto usa **dos formatos de persistencia distintos**, cada uno donde tiene más sentido:

| Dato | Formato | Dirección | Justificación |
|---|---|---|---|
| Banco de expresiones de prueba (`formulas_prueba.json`) | **JSON** | Lectura (disco → programa) | Es información de entrada/configuración; permite al docente preparar fácilmente expresiones de distinta complejidad (con y sin paréntesis anidados) sin recompilar el programa. |
| Historial de expresiones evaluadas (`historial_expresiones.dat`) | **Binario** (serialización de objetos) | Escritura (programa → disco) | Es un registro generado por la ejecución real del programa; se usa el mismo mecanismo de `ObjectOutputStream`/`ObjectInputStream` trabajado en la actividad inicial de unidad. |

El archivo `formulas_prueba.json` se entrega ya elaborado por el docente, con al menos 5 expresiones de prueba de dificultad creciente (con al menos un caso de paréntesis anidados, por ejemplo `((2 + 3) * 4) - (6 / 2)`).

---

## 7. Interfaz gráfica (Java Swing)

La aplicación debe tener una interfaz gráfica de escritorio, no de consola, con al menos:

- Un **campo de texto** donde el usuario digite la expresión infija.
- Un botón **"Evaluar"** que dispare la conversión, construcción del árbol y evaluación.
- Un **área de resultados** (texto) donde se muestren: la expresión postfija generada, el resultado numérico, y los tres recorridos (prefijo, infijo con paréntesis, postfijo).
- Un **panel de dibujo** que muestre el árbol de expresión construido, organizado visualmente por niveles (la raíz arriba, las hojas abajo).
- Un botón o menú **"Ver historial"** que muestre las expresiones evaluadas previamente (leídas desde `historial_expresiones.dat`).

---

## 8. Qué se entrega al estudiante (código base)

| Clase | Estado al entregar | Contiene |
|---|---|---|
| `Pila<T>` | Completa — no modificar | Reutilizada tal cual de la actividad de digiturno. |
| `NodoArbol` | Completa | Contenedor simple: un dato (`String`, que puede ser un operador o un operando), referencia a `izquierdo` y a `derecho`. |
| `LectorFormulasJSON` | Completa | Realiza el parseo del archivo `formulas_prueba.json` y entrega la lista de expresiones ya leídas en un arreglo de `String`. El estudiante no debe implementar el parseo de JSON. |
| `PanelArbol` | Completa | Componente Swing que recorre un árbol ya construido y lo dibuja por niveles con `Graphics2D`. |
| `ResultadoExpresion` | Completa | Contenedor simple del resultado de `procesarExpresion`: expresión original, postfija, prefijo, infijo con paréntesis, resultado numérico y la raíz del árbol construido. |
| `RegistroExpresion` | Completa | Contenedor simple para el historial: expresión evaluada, resultado y fecha/hora, listo para guardarse en binario. |
| `ArbolExpresion` | **Incompleta (con TODO)** | Contiene la raíz del árbol; el estudiante debe completar los métodos indicados en la sección 9. Incluye ya resuelto el método `procesarExpresion(String infija)`, que orquesta las llamadas a los métodos TODO en el orden correcto (postfija → árbol → recorridos → evaluación). |
| `VentanaPrincipal` / `Main` | Completa — no modificar | Arma la interfaz gráfica completa (campo de texto, botones, panel de resultados y de dibujo) y conecta cada botón con el método correspondiente de `ArbolExpresion`. Es la clase que se ejecuta para **probar** el trabajo. |
| `Pruebas` | Completa — no modificar | Clase de consola con **14 casos de prueba** (conversión a postfija sin y con paréntesis, precedencia de operadores, construcción del árbol, los tres recorridos, evaluación con decimales y con paréntesis anidados, y carga de las expresiones de prueba desde JSON). Reporta `PASA`/`FALLA` por cada caso, de forma que el estudiante pueda verificar su avance método por método sin necesidad de abrir la interfaz gráfica. |

Se entrega, además, el archivo `datos/formulas_prueba.json` con las expresiones de ejemplo.

**Cómo usar la clase `Pruebas`:** se recomienda ejecutar `Pruebas.java` después de completar cada método (o cada dos o tres), no solo al final. Mientras los métodos sigan sin implementar, todos los casos relacionados mostrarán `FALLA` de forma controlada (el programa no se detiene ni lanza excepciones); a medida que el estudiante complete los métodos correctamente, los casos correspondientes deben empezar a mostrar `PASA`. Llegar a **14/14 casos exitosos** es la señal de que la clase `ArbolExpresion` quedó correctamente implementada, antes de pasar a probar la interfaz gráfica.

---

## 9. Qué debe completar el estudiante

Dentro de la clase `ArbolExpresion`, el estudiante debe implementar el cuerpo de los siguientes métodos:

1. `convertirAPostfija(String infija)` — recorrer la expresión carácter por carácter, usando una `Pila` para resolver la precedencia de operadores y el efecto de los paréntesis, y retornar la expresión equivalente en notación postfija.
2. `construirArbol(String postfija)` — recorrer la expresión postfija usando una `Pila<NodoArbol>`: apilar cada operando como nodo hoja; al encontrar un operador, retirar dos nodos de la pila para colgarlos como hijo izquierdo y derecho del nuevo nodo, y apilar este nuevo subárbol. Al finalizar, la pila debe contener un único nodo: la raíz del árbol completo.
3. `recorridoPreorden(NodoArbol nodo)` — recorrido recursivo: procesar el nodo, luego el hijo izquierdo, luego el hijo derecho.
4. `recorridoInorden(NodoArbol nodo)` — recorrido recursivo con paréntesis: procesar el hijo izquierdo, luego el nodo, luego el hijo derecho, agregando un paréntesis de apertura antes de la primera llamada recursiva y uno de cierre después de la segunda, cuando el nodo sea un operador.
5. `recorridoPostorden(NodoArbol nodo)` — recorrido recursivo: procesar el hijo izquierdo, luego el hijo derecho, luego el nodo.
6. `evaluar(NodoArbol nodo)` — evaluación recursiva: si el nodo es una hoja (operando), retornar su valor numérico; si es un operador, evaluar recursivamente ambos hijos y aplicar la operación correspondiente sobre esos dos resultados.
7. `cargarFormulasDesdeJSON(String[] expresiones)` — a partir del arreglo de expresiones ya entregado por `LectorFormulasJSON`, ejecutar automáticamente sobre cada una `procesarExpresion` (pasos 1 a 6), mostrando sus resultados al iniciar la aplicación.
8. `guardarHistorialBinario()` — guardar el historial completo de expresiones evaluadas manualmente por el usuario (`getHistorial()`, no las de prueba cargadas desde JSON) en el archivo binario `datos/historial_expresiones.dat`, siguiendo el mismo patrón de `ObjectOutputStream` usado en la actividad inicial de unidad.

Cada método debe incluir un comentario Javadoc donde el estudiante explique qué hizo y por qué, como evidencia de análisis desde la perspectiva del TAD.

---

## 10. Entregables

Este componente (Motor de Evaluación de Fórmulas) **no se entrega solo**: es una de las dos partes obligatorias del TCC de fin de curso, junto con el Componente A (Grafos y Dijkstra). Ver el documento **"Condiciones del Trabajo Colaborativo Contextualizado (TCC)"** para el detalle completo de entregables obligatorios comunes a ambos componentes (código de honor, documento de proceso, bitácora de IA, evidencias de prueba, repositorio Git/GitHub — de uso obligatorio, etc.).

---

## 11. Qué NO se evalúa en este proyecto

- Soporte para funciones matemáticas (seno, coseno, raíz, potencia) — solo los cuatro operadores básicos, salvo que el docente indique lo contrario como reto opcional.
- Validación exhaustiva de expresiones mal formadas con mensajes de error detallados (se espera un manejo básico que evite que el programa se caiga, no un validador sintáctico completo).
- Balanceo del árbol (no aplica: un árbol de expresión no se balancea, su forma la determina la expresión misma).
- Persistencia del árbol completo en JSON — el JSON solo se usa para las expresiones de prueba de entrada; el árbol se reconstruye en memoria cada vez que se evalúa una expresión.

---

*Documento de especificación del Proyecto de Fin de Curso — Componente B (Árbol Binario de Expresión), curso de Estructuras de Datos. Debe entregarse junto con el Componente A (Grafos y Dijkstra); ambos son obligatorios.*
