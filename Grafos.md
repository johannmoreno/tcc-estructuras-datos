# Proyecto de Fin de Curso — Componente A
## Sistema de Rutas de Entrega Urbana (Grafos y Algoritmo de Dijkstra)

**Curso:** Estructuras de Datos\
**Unidad:** Árboles y Grafos (estructuras no lineales)\
**Tipo de actividad:** Trabajo Colaborativo Contextualizado (TCC) — proyecto de fin de curso\
**Lenguaje:** Java (sin colecciones de `java.util`)\
**Modalidad:** Distancia — entrega y sustentación según calendario del curso

> **El TCC de fin de curso consta de DOS componentes obligatorios: este documento (Componente A — Grafos y Dijkstra) y el de Árbol de Expresión (Componente B).** Cada CIPA debe desarrollar y entregar **ambos** proyectos completos; no es una elección entre uno u otro. Ver el documento **"Condiciones del Trabajo Colaborativo Contextualizado (TCC)"** para los entregables comunes a ambos componentes.

---

## 1. Contexto del problema

Una empresa de domicilios que opera en Cartagena necesita optimizar sus entregas. Actualmente los mensajeros escogen la ruta "a ojo", lo que genera recorridos más largos de lo necesario, retrasos y sobrecostos de combustible.

La empresa solicita un **sistema de cálculo de rutas** que, a partir de un mapa de la ciudad representado como un conjunto de puntos (bodegas, cruces, zonas de entrega) conectados por calles con una distancia asociada, permita:

- Consultar qué tan conectado está un punto del mapa.
- Explorar el mapa a partir de un punto de partida.
- Calcular la **ruta de menor distancia total** entre dos puntos cualesquiera.

Este es el problema clásico que resuelven aplicaciones de navegación real (Waze, Google Maps) en su forma más simplificada: un **grafo ponderado**, sin curvas ni tráfico en tiempo real, donde el peso de cada calle es una distancia fija.

---

## 2. Relación con el curso y el resultado de aprendizaje

Este proyecto aporta evidencia al resultado de aprendizaje:

> *"Analiza, especifica e implementa las estructuras de datos y las colecciones no lineales desde la perspectiva de los TAD."*

A diferencia de la actividad inicial de unidad (digiturno, que trabajó estructuras **lineales**: cola, pila y lista), este proyecto aborda la estructura **no lineal** central del curso: el **grafo**, junto con los algoritmos que se construyen sobre él (recorridos y camino más corto).

El proyecto además exige **integración de conocimientos previos**: los recorridos BFS y DFS se implementan reutilizando las clases `Cola<T>` y `Pila<T>` construidas en la actividad de digiturno, evidenciando que el estudiante reconoce el mismo TAD funcionando en un contexto distinto.

**Importante:** este proyecto agrega dos componentes que no estaban presentes en la actividad inicial de unidad y que elevan su dificultad a nivel medio-alto:

- **Interfaz gráfica de escritorio** (Java Swing), para visualizar el mapa y la ruta calculada.
- **Persistencia mixta**: lectura de datos desde **JSON** y escritura de historial en **archivo binario**.

---

## 3. Objetivo del proyecto

Implementar un grafo ponderado no dirigido, representado mediante **matriz de adyacencia propia** (sin usar `java.util`), que permita gestionar puntos y calles de un mapa urbano, ejecutar recorridos BFS y DFS, calcular la ruta más corta entre dos puntos con el **algoritmo de Dijkstra**, y presentar los resultados en una interfaz gráfica que dibuje el mapa y resalte la ruta encontrada.

---

## 4. Descripción funcional del sistema a construir

El sistema debe permitir:

1. **Cargar un mapa inicial** de prueba (puntos y calles con su distancia) desde un archivo `mapa_prueba.json`, al iniciar la aplicación, de forma que quede disponible para practicar sin digitar nada manualmente.
2. **Agregar un punto** al mapa (vértice), indicando su nombre y su posición (coordenadas x, y para poder dibujarlo).
3. **Eliminar un punto** del mapa, junto con todas las calles que llegaban o salían de él.
4. **Agregar una calle** entre dos puntos existentes, indicando su distancia en kilómetros (arista con peso).
5. **Eliminar una calle** entre dos puntos.
6. **Consultar si dos puntos son adyacentes** (existe una calle directa entre ellos).
7. **Consultar el grado de un punto**: cuántas calles lo conectan (útil para identificar cruces con más tráfico potencial).
8. **Ejecutar un recorrido en anchura (BFS)** a partir de un punto de partida, mostrando el orden en que se visitan los demás puntos alcanzables.
9. **Ejecutar un recorrido en profundidad (DFS)** a partir de un punto de partida, mostrando el orden en que se visitan los demás puntos alcanzables.
10. **Calcular la ruta más corta entre dos puntos** usando el algoritmo de **Dijkstra**, mostrando la secuencia de puntos que conforma la ruta y la distancia total acumulada.
11. **Visualizar el mapa gráficamente**: los puntos como nodos y las calles como líneas etiquetadas con su distancia; al calcular una ruta, esta debe resaltarse visualmente sobre el mapa (por ejemplo, con un color distinto).
12. **Guardar cada consulta de ruta calculada** (origen, destino, ruta encontrada, distancia total, fecha y hora) en un archivo binario de historial (`historial_rutas.dat`), para conservar un registro incluso después de cerrar la aplicación.

---

## 5. Estructuras de datos a utilizar y su justificación

**No se permite el uso de ninguna colección de `java.util`** (`ArrayList`, `LinkedList`, `HashMap`, `Queue`, `Stack`, etc.). Todas las estructuras deben ser propias del proyecto.

| Necesidad del sistema | TAD / mecanismo | Justificación |
|---|---|---|
| Representar el mapa completo (puntos y calles con distancia) | **Grafo con matriz de adyacencia propia** | Permite verificar en tiempo O(1) si dos puntos están conectados y facilita aplicar Dijkstra al tener acceso directo al peso de cualquier conexión. |
| Recorrido en anchura (BFS) | **Cola\<T\>** | Mismo comportamiento FIFO trabajado en la actividad de digiturno; se reutiliza la clase ya construida. |
| Recorrido en profundidad (DFS) | **Pila\<T\>** | Mismo comportamiento LIFO trabajado en la actividad de digiturno; se reutiliza la clase ya construida. |
| Camino más corto entre dos puntos | **Algoritmo de Dijkstra** sobre la matriz de adyacencia, con arreglos propios auxiliares (distancias, visitados, predecesores) | Es el algoritmo estándar para este problema; no requiere una estructura adicional distinta a la matriz y a arreglos simples. |

Se recomienda que la clase `Grafo` mantenga, además de la matriz de distancias, un arreglo (no `java.util`) con los nombres y coordenadas de cada vértice, indexado en la misma posición que la matriz.

---

## 6. Persistencia de datos

Este proyecto usa **dos formatos de persistencia distintos**, cada uno donde tiene más sentido:

| Dato | Formato | Dirección | Justificación |
|---|---|---|---|
| Mapa inicial de prueba (`mapa_prueba.json`) | **JSON** | Lectura (disco → programa) | Es información de entrada/configuración; el formato de texto permite que el docente o el estudiante edite el mapa de prueba fácilmente sin recompilar el programa. |
| Historial de rutas consultadas (`historial_rutas.dat`) | **Binario** (serialización de objetos) | Escritura (programa → disco) | Es un registro generado por la ejecución real del programa; se usa el mismo mecanismo de `ObjectOutputStream`/`ObjectInputStream` trabajado en la actividad inicial de unidad. |

El archivo `mapa_prueba.json` se entrega ya elaborado por el docente, con al menos 8 puntos y 10 calles, formando un mapa donde existan múltiples rutas posibles entre varios pares de puntos (para que Dijkstra tenga sentido de evaluar).

---

## 7. Interfaz gráfica (Java Swing)

La aplicación debe tener una interfaz gráfica de escritorio, no de consola, con al menos:

- Un **panel de dibujo** donde se visualicen los puntos (como círculos con su nombre) y las calles (como líneas con su distancia).
- Un **panel de controles** con:
  - Campos para agregar un punto (nombre, coordenadas).
  - Campos para agregar/eliminar una calle (los dos puntos y la distancia).
  - Selectores (listas desplegables) para elegir el punto de origen y de destino.
  - Botones: "Agregar punto", "Eliminar punto", "Agregar calle", "Eliminar calle", "Ver grado", "Recorrido BFS", "Recorrido DFS", "Calcular ruta más corta".
- Un área de resultados (texto) donde se muestre el resultado de cada operación (grado de un punto, orden del recorrido, ruta calculada y su distancia total).
- Al calcular una ruta, el panel de dibujo debe resaltar visualmente la secuencia de puntos y calles que la componen.

---

## 8. Qué se entrega al estudiante (código base)

| Clase | Estado al entregar | Contiene |
|---|---|---|
| `Cola<T>` | Completa — no modificar | Reutilizada tal cual de la actividad de digiturno. |
| `Pila<T>` | Completa — no modificar | Reutilizada tal cual de la actividad de digiturno. |
| `Vertice` | Completa | Contenedor simple: nombre y coordenadas (x, y) para el dibujo. |
| `LectorMapaJSON` | Completa | Realiza el parseo del archivo `mapa_prueba.json` y entrega los datos ya leídos (nombres, coordenadas, calles y distancias) en estructuras simples (arreglos). El estudiante no debe implementar el parseo de JSON: su trabajo empieza una vez los datos ya están disponibles en memoria. |
| `PanelMapa` | Completa | Componente Swing que dibuja los puntos y las calles a partir de los datos del grafo, y resalta una ruta cuando se le indica. |
| `ResultadoRuta` | Completa | Contenedor simple del resultado de `dijkstra`: la secuencia de puntos de la ruta, la distancia total y si existe o no una ruta posible. |
| `ConsultaRuta` | Completa | Contenedor simple para el historial: origen, destino, ruta, distancia y fecha/hora de la consulta, listo para guardarse en binario. |
| `DatosMapa` | Completa | Estructura simple donde `LectorMapaJSON` entrega los datos ya leídos del JSON (vértices y aristas), para que el estudiante los use en `cargarDesdeJSON`. |
| `Grafo` | **Incompleta (con TODO)** | Contiene la matriz de adyacencia y los arreglos de apoyo; el estudiante debe completar los métodos indicados en la sección 9. |
| `VentanaPrincipal` / `Main` | Completa — no modificar | Arma la interfaz gráfica completa (paneles, campos, botones) y conecta cada botón con el método correspondiente de la clase `Grafo`. Es la clase que se ejecuta para **probar** el trabajo. |
| `Pruebas` | Completa — no modificar | Clase de consola con **16 casos de prueba** (adyacencia, grado, BFS, DFS, Dijkstra, eliminación de vértices/aristas y carga desde JSON) construidos sobre un grafo pequeño y trazado a mano. Reporta `PASA`/`FALLA` por cada caso, de forma que el estudiante pueda verificar su avance método por método sin necesidad de abrir la interfaz gráfica. |

Se entrega, además, el archivo `datos/mapa_prueba.json` con el mapa urbano de ejemplo.

**Cómo usar la clase `Pruebas`:** se recomienda ejecutar `Pruebas.java` después de completar cada método (o cada dos o tres), no solo al final. Mientras los métodos sigan sin implementar, todos los casos relacionados mostrarán `FALLA` de forma controlada (el programa no se detiene ni lanza excepciones); a medida que el estudiante complete los métodos correctamente, los casos correspondientes deben empezar a mostrar `PASA`. Llegar a **16/16 casos exitosos** es la señal de que la clase `Grafo` quedó correctamente implementada, antes de pasar a probar la interfaz gráfica.

---

## 9. Qué debe completar el estudiante

Dentro de la clase `Grafo`, el estudiante debe implementar el cuerpo de los siguientes métodos:

1. `agregarVertice(String nombre, int x, int y)` — agregar un nuevo punto al grafo, ampliando la matriz de adyacencia y el arreglo de vértices según sea necesario.
2. `eliminarVertice(String nombre)` — quitar un punto del grafo junto con todas sus conexiones.
3. `agregarArista(String v1, String v2, double distancia)` — registrar una calle (conexión) entre dos puntos existentes, con su distancia, en ambas direcciones de la matriz (grafo no dirigido).
4. `eliminarArista(String v1, String v2)` — quitar la conexión entre dos puntos.
5. `sonAdyacentes(String v1, String v2)` — consultar si existe una calle directa entre dos puntos.
6. `grado(String v)` — contar cuántas calles conectan a un punto.
7. `recorridoBFS(String origen)` — recorrido en anchura a partir de un punto, usando la clase `Cola` entregada.
8. `recorridoDFS(String origen)` — recorrido en profundidad a partir de un punto, usando la clase `Pila` entregada.
9. `dijkstra(String origen, String destino)` — calcular la ruta de menor distancia total entre dos puntos y retornar tanto la secuencia de puntos como la distancia acumulada.
10. `cargarDesdeJSON(DatosMapa datos)` — a partir del objeto `DatosMapa` ya entregado por `LectorMapaJSON` (vértices y aristas leídos del archivo), poblar el grafo llamando a `agregarVertice` y `agregarArista` para cada elemento.
11. `guardarHistorialBinario()` — guardar el historial completo de consultas de ruta realizadas (`getHistorialConsultas()`) en el archivo binario `datos/historial_rutas.dat`, siguiendo el mismo patrón de `ObjectOutputStream` usado en la actividad inicial de unidad.

Cada método debe incluir un comentario Javadoc donde el estudiante explique qué hizo y por qué, como evidencia de análisis desde la perspectiva del TAD.

---

## 10. Entregables

Este componente (Sistema de Rutas de Entrega Urbana) **no se entrega solo**: es una de las dos partes obligatorias del TCC de fin de curso, junto con el Componente B (Árbol de Expresión). Ver el documento **"Condiciones del Trabajo Colaborativo Contextualizado (TCC)"** para el detalle completo de entregables obligatorios comunes a ambos componentes (código de honor, documento de proceso, bitácora de IA, evidencias de prueba, repositorio Git/GitHub — de uso obligatorio, etc.).

---

## 11. Qué NO se evalúa en este proyecto

- Tráfico en tiempo real, curvas o mapas geográficos reales (coordenadas GPS).
- Otros algoritmos de camino más corto distintos a Dijkstra (A*, Bellman-Ford, Floyd-Warshall) — quedan fuera del alcance, salvo que el docente indique lo contrario como reto opcional.
- Persistencia del grafo completo en JSON después de modificarlo (solo se lee el mapa inicial desde JSON; los cambios que el usuario haga en tiempo de ejecución no necesitan guardarse de vuelta en JSON).
- Uso de mapas o librerías de geolocalización externas.

---

*Documento de especificación del Proyecto de Fin de Curso — Componente A (Grafos y Dijkstra), curso de Estructuras de Datos. Debe entregarse junto con el Componente B (Árbol de Expresión); ambos son obligatorios.*
