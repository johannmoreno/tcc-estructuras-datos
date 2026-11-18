# Condiciones del Trabajo Colaborativo Contextualizado (TCC)
## Proyecto de Fin de Curso — Estructuras de Datos

**Curso:** Estructuras de Datos\
**Unidad:** Árboles y Grafos (estructuras no lineales)\
**Aplica a:** Los DOS componentes obligatorios del proyecto de fin de curso —
**Componente A** (Sistema de Rutas de Entrega Urbana: Grafos y Dijkstra) y
**Componente B** (Motor de Evaluación de Fórmulas: Árbol Binario de Expresión)

---

## 1. Alcance de este documento

Este documento establece las condiciones de entrega, honestidad académica y evidencia de trabajo que rigen el Trabajo Colaborativo Contextualizado (TCC) de fin de curso. **El TCC no es una elección entre dos opciones: cada CIPA debe desarrollar y entregar ambos componentes (A y B) de forma completa.** Los entregables descritos aquí son **obligatorios y no exhaustivos**: el docente puede solicitar evidencia adicional durante la sustentación si lo considera necesario para verificar la autoría y comprensión real del trabajo.

Este documento debe leerse junto con los dos enunciados específicos (Componente A y Componente B), que contienen el detalle técnico de qué se entrega y qué debe completar el estudiante en cada uno.

---

## 2. Modalidad de trabajo

El proyecto se desarrolla en **CIPA** (Colectivo de Interaprendizaje), consistente con la política de trabajo colaborativo de la modalidad a distancia. No se otorgan concesiones especiales en tiempo, alcance o rúbrica a los estudiantes que decidan trabajar de forma individual.

Cada CIPA debe desarrollar y entregar **ambos componentes, A y B, completos y funcionales**. No se acepta entregar solo uno de los dos como si constituyera el TCC completo, ni justificar la ausencia de uno de ellos alegando que "se eligió el otro" — ambos hacen parte de la misma evaluación y ambos deben aprobarse para aprobar el TCC. La única excepción es una autorización expresa y previa del docente, por una razón debidamente justificada (por ejemplo, ajuste razonable documentado por la Universidad).

---

## 3. Entregables obligatorios

A continuación se detallan los seis entregables mínimos del proyecto. El incumplimiento de cualquiera de ellos afecta la calificación y, en los casos señalados explícitamente, constituye una violación al Código de Honor institucional.

### 3.1 Código Java completo, comentado con Javadoc

- **Los dos proyectos** (Componente A y Componente B) deben entregarse **completamente funcionales**: todos los métodos TODO señalados en el enunciado de cada componente deben estar resueltos, cada proyecto debe compilar sin errores y ejecutarse tal como se entrega.
- **Todas las clases** de ambos proyectos deben incluir comentarios **Javadoc** explicando su propósito; **todos los métodos** implementados por el estudiante (los que estaban marcados como TODO) deben incluir Javadoc explicando qué hace el método, qué estructura o algoritmo utiliza, y por qué esa es la decisión correcta de diseño — no un comentario genérico ni una copia del enunciado.
- El código de **cada uno de los dos proyectos** debe incluir los **nombres completos y códigos estudiantiles** de todos los integrantes del CIPA, en la cabecera del archivo principal.

### 3.2 Código de Honor

**Todo archivo de código fuente entregado, en AMBOS proyectos (Componente A y Componente B)**, debe incluir, en su cabecera, la siguiente declaración textual como comentario de bloque, sin modificar su contenido:

```java
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
 * <Nombres y Apellidos – Código> (integrante 1)
 * <Nombres y Apellidos – Código> (integrante 2)
 *
 ****************************************************************************/
```

**Nota de formato:** el texto anterior conserva íntegramente el juramento tal como fue establecido por el docente; únicamente se ajustó la sintaxis del bloque de comentario (`/**` … `*/`) para que sea un comentario Java válido y compilable, y se reorganizó levemente la redacción de las líneas para que cada punto quede como una afirmación independiente y clara. **El contenido y el compromiso declarado no fueron alterados.**

**La NO inclusión de esta declaración en el código entregado es una violación al Código de Honor**, independientemente de la calidad técnica del resto del proyecto. Esto aplica de forma independiente a cada proyecto: si el Componente A la incluye pero el Componente B no (o viceversa), se considera una violación al Código de Honor en el componente donde falte.

### 3.3 Documento de integrantes y proceso de solución

Se debe entregar un documento (Word o PDF) que incluya:

- Nombres completos, códigos estudiantiles y correo institucional de cada integrante del CIPA.
- Una narrativa **paso a paso**, para **cada uno de los dos componentes (A y B)**, de cómo el equipo resolvió el proyecto: cómo entendieron el problema, qué decisiones de diseño tomaron (por ejemplo, cómo decidieron representar el grafo, o cómo resolvieron la conversión infijo-postfijo), qué dificultades encontraron y cómo las resolvieron, y cómo dividieron el trabajo entre los integrantes del CIPA. Puede organizarse como un documento único con una sección por componente, o como dos documentos separados claramente identificados.
- Este documento es la evidencia principal de **comprensión real** de ambos problemas, más allá de que el código funcione: un CIPA que solo entrega código sin poder explicar su propio proceso de construcción (en cualquiera de los dos componentes) está en riesgo de que se le pida sustentar individualmente esa parte del programa.

### 3.4 Bitácora de uso de IA generativa (obligatoria)

A diferencia de la actividad inicial de unidad (digiturno), donde el uso de IA generativa era opcional y debía declararse, **en este proyecto el uso de IA generativa es obligatorio**, pero únicamente en su rol de **tutor o asesor técnico** — nunca como quien resuelve el problema por el estudiante.

Esto significa:

- **No se puede declarar "no usé IA"**: se espera que cada CIPA use una herramienta de IA generativa como parte legítima de su proceso de aprendizaje (para resolver dudas conceptuales, entender un algoritmo, depurar errores propios, revisar sintaxis), y que documente esa interacción.
- **Debe haber evidencia de uso de IA en el desarrollo de CADA UNO de los dos componentes** (A y B), no únicamente en uno de ellos. Una bitácora que solo documente interacciones sobre uno de los dos proyectos se considera incompleta.
- **Sí se puede y se debe cuestionar, corregir y adaptar** lo que la IA responda. La IA generativa puede resolver un problema de este tipo con relativa facilidad si se le entrega el enunciado completo; por eso la bitácora exige evidencia de que el estudiante **analizó, cuestionó y hasta corrigió** lo que la herramienta propuso, no que lo transcribió.
- La bitácora debe incluir, como mínimo, para cada componente:
  - Herramienta(s) de IA utilizada(s).
  - Registro de interacciones relevantes: **prompt textual utilizado**, resumen de la respuesta recibida, y **qué análisis o modificación hizo el estudiante** sobre esa respuesta (qué corrigió, qué adaptó, qué descartó y por qué).
  - Una reflexión final breve sobre qué aprendió el equipo gracias al uso de la IA como tutor, y qué error o limitación de la IA tuvieron que identificar por cuenta propia.
- Todo fragmento de código o documentación que **no sea de autoría propia** del CIPA (incluyendo el que provenga de una respuesta de IA generativa reutilizada tal cual, cuando esto esté justificado) debe señalarse explícitamente con una cita en los comentarios del programa, conforme a lo declarado en el Código de Honor (sección 3.2).

### 3.5 Evidencias de casos de prueba con sus respuestas

Se debe entregar evidencia (capturas de pantalla de la interfaz gráfica en ejecución, o registros de consola cuando aplique) de al menos los siguientes casos de prueba, **para AMBOS componentes** (no solo uno):

**Para el Componente A (Grafos y Dijkstra):**
- Carga del mapa inicial desde JSON al iniciar la aplicación.
- Al menos un caso de agregar/eliminar un punto y una calle.
- Consulta de grado de al menos dos puntos distintos.
- Un recorrido BFS y un recorrido DFS desde el mismo punto de partida, mostrando que producen órdenes distintos.
- Al menos dos cálculos de ruta más corta entre pares de puntos distintos, incluyendo uno donde exista más de un camino posible (para evidenciar que Dijkstra realmente compara distancias).
- Verificación del archivo `historial_rutas.dat` generado.
- Además, se recomienda dejar constancia de la ejecución de la clase de pruebas de consola entregada (`Pruebas.java`, 16 casos), evidenciando que los 16 casos reportan `PASA` en la versión final entregada.

**Para el Componente B (Árbol de Expresión):**
- Carga y evaluación automática de las expresiones de prueba desde JSON al iniciar la aplicación.
- Al menos tres expresiones evaluadas manualmente por el usuario, de dificultad creciente (sin paréntesis, con un nivel de paréntesis, con paréntesis anidados).
- Para cada una: la expresión postfija generada, el resultado numérico, y los tres recorridos (prefijo, infijo con paréntesis, postfijo).
- El árbol dibujado gráficamente para al menos una expresión con paréntesis anidados.
- Verificación del archivo `historial_expresiones.dat` generado.
- Además, se recomienda dejar constancia de la ejecución de la clase de pruebas de consola entregada (`Pruebas.java`, 14 casos), evidenciando que los 14 casos reportan `PASA` en la versión final entregada.

Cada caso de prueba debe presentarse junto con la respuesta que el programa produjo, de forma que se pueda verificar que el resultado es correcto (el docente puede calcular manualmente el resultado esperado y contrastarlo).

### 3.6 Acceso al repositorio (obligatorio)

**El uso de un sistema de control de versiones (Git) con repositorio remoto en GitHub, GitLab o una herramienta similar es obligatorio para el desarrollo de este TCC, para ambos componentes.** No es una recomendación ni un entregable condicionado a que el CIPA "decida usarlo": todo CIPA debe versionar su trabajo desde el inicio del proyecto y entregar acceso al repositorio como parte de los entregables mínimos.

Debe entregarse:

- El enlace de acceso al repositorio (público, o privado con los colaboradores agregados: docente y/o monitor del curso), que contenga el código de **ambos componentes** (por ejemplo, en dos carpetas o dos repositorios claramente identificados: `componente-a-grafo/` y `componente-b-arbol/`).
- El historial de commits debe reflejar un desarrollo incremental real a lo largo del tiempo asignado al proyecto, para cada uno de los dos componentes, no una única subida masiva de código el día de la entrega — un historial de commits concentrado en las últimas horas antes del plazo, o un repositorio creado con un solo commit inicial que ya contiene todo el proyecto terminado, es una señal de alerta que el docente puede usar para pedir sustentación adicional.
- La ausencia de repositorio, o un repositorio que no permita verificar un desarrollo incremental (por ejemplo, un único commit), se trata como el incumplimiento de un entregable obligatorio (ver tabla de consecuencias en la sección 5), no como la simple pérdida de una recomendación.

---

## 4. Formato y medio de entrega

Todos los entregables (código fuente de **ambos componentes**, documento de proceso, bitácora de IA, evidencias de prueba y el enlace al repositorio, que es obligatorio) deben comprimirse en un único archivo `.zip`, organizado en carpetas separadas para cada componente (por ejemplo, `componente-a-grafo/`, `componente-b-arbol/`, `documentos/`), y subirse por el medio que indique el LMS del curso, salvo que el docente autorice explícitamente un medio distinto (por ejemplo, el enlace al repositorio si este contiene todo lo anterior organizado en carpetas).

---

## 5. Consecuencias del incumplimiento

| Situación | Tratamiento sugerido |
|---|---|
| Solo se entrega uno de los dos componentes (A o B) | El TCC se considera **incompleto**; no se califica como si fuera el trabajo completo. Se notifica al CIPA y se aplica la política de entregas incompletas/tardías del curso al componente faltante. |
| Falta el Código de Honor en el código fuente (en cualquiera de los dos componentes) | Violación directa al Código de Honor institucional, independiente de la calidad del código, y aplicable de forma independiente al componente donde falte. |
| Bitácora de IA ausente, declara explícitamente "no se usó IA", o solo documenta uno de los dos componentes | Incumplimiento de un entregable obligatorio; afecta la calificación del componente correspondiente. |
| Bitácora de IA presente pero sin evidencia de análisis o corrección propia (transcripción literal) | Se trata como evidencia insuficiente de comprensión propia; puede derivar en sustentación oral adicional del CIPA. |
| Código que coincide de forma evidente con una solución típica generada por IA, sin que la bitácora lo refleje con el nivel de detalle esperado | Inconsistencia entre la evidencia declarada y el producto entregado; se recomienda remitir el caso al procedimiento de integridad académica del programa, no como juicio automático de deshonestidad sino como alerta a verificar con el CIPA. |
| Ausencia de evidencias de casos de prueba (de cualquiera de los dos componentes) | El docente puede solicitar una demostración en vivo durante la sustentación como condición para calificar ese componente. |
| Ausencia de repositorio Git/GitHub (o similar), o repositorio sin historial de commits que evidencie desarrollo incremental | Incumplimiento de un entregable obligatorio (ya no es opcional); afecta la calificación del componente de repositorio y puede motivar sustentación oral adicional para verificar autoría. |

---

## 6. Sustentación

Todo proyecto de fin de curso incluye una **sustentación oral** (presencial) donde cada integrante del CIPA deba responder preguntas sobre **cualquier parte** del código entregado, no solo la parte que dice haber desarrollado. 
Esto es consistente con el propósito del documento de proceso (sección 3.3): los integrantes deben poder explicar la totalidad del proyecto.

---

## 7. Relación con la rúbrica de evaluación

Los criterios de calificación detallados (peso de cada entregable, niveles de desempeño) se establecen en la rúbrica de evaluación específica del proyecto de fin de curso, que se entrega como documento aparte, siguiendo el mismo formato usado en la actividad inicial de unidad (digiturno). A alto nivel, se sugiere la siguiente distribución de peso entre los componentes descritos en este documento:

| Componente de la evaluación | Peso orientativo |
|---|---|
| Funcionalidad técnica del Componente A (Grafos y Dijkstra) | 25% |
| Funcionalidad técnica del Componente B (Árbol de Expresión) | 25% |
| Código de Honor incluido correctamente en ambos componentes | Requisito habilitante (no otorga puntos; su ausencia en cualquiera de los dos componentes sí penaliza) |
| Documento de proceso (comprensión propia de ambos componentes) | 15% |
| Bitácora de uso de IA generativa (evidencia sobre ambos componentes) | 15% |
| Evidencias de casos de prueba (de ambos componentes) | 10% |
| Repositorio Git/GitHub y desarrollo incremental (obligatorio, ambos componentes) | 10% |

---


*Documento de condiciones del Trabajo Colaborativo Contextualizado (TCC), aplicable a los dos componentes obligatorios del proyecto de fin de curso de Estructuras de Datos (Componente A: Grafos y Dijkstra; Componente B: Árbol Binario de Expresión). Ambos componentes deben desarrollarse y entregarse; el TCC no se considera completo con uno solo de ellos.*
