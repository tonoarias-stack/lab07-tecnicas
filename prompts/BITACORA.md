# Bitacora de tecnicas avanzadas

Laboratorio 07: Tecnicas Avanzadas de Prompting.
Herramienta de IA usada: Claude

## Ejercicio 2: Zero-shot, one-shot y few-shot

| Tipo      | Aciertos (de 5) | Formato de la respuesta                                                         | Todas con el mismo formato (Si/No)                          |
| --------- | --------------- | ------------------------------------------------------------------------------- | ----------------------------------------------------------- |
| Zero-shot | 5               | Tabla con columnas Comentario y Clasificación; usó "Neutro" en vez de "Neutral" | Si (dentro de la tabla), pero formato libre y sin la flecha |
| One-shot  | 5               | Lista numerada con el formato "texto" -> etiqueta                               | Si, aunque agregó numeración                                |
| Few-shot  | 5               | Una línea por comentario con el formato "texto" -> etiqueta, sin numeración     | Si, idéntico al de los ejemplos                             |

## Ejercicio 3: Chain of Thought

| Pedido      | Respuesta de la IA | Muestra los pasos (Si/No) | Correcta (Si/No) |
| ----------- | ------------------ | ------------------------- | ---------------- |
| Directo     | 318.6              | No                        | Si               |
| Paso a paso | S/ 318.60          | Si                        | Si               |

Aunque la respuesta directa fue correcta, solo veo un número y no puedo saber si la IA razonó bien o acertó por casualidad.
Con los pasos puedo comparar cada cálculo con mi calculadora y, si hay un error, saber exactamente en cuál paso ocurrió.

## Ejercicio 4: Role prompting

| Version        | Vocabulario (sencillo/tecnico)                                                       | Usa ejemplos o codigo                                            | A quien le sirve mas                                 |
| -------------- | ------------------------------------------------------------------------------------ | ---------------------------------------------------------------- | ---------------------------------------------------- |
| A. Sin rol     | Intermedio: sencillo con la analogia de la caja, pero agrega tabla de tipos de datos | Si: analogia de la caja con etiqueta y codigo en Python          | A un principiante que quiere una explicacion general |
| B. Rol docente | Sencillo: caja de zapatos, videojuego de puntos, sin jerga                           | Si: analogia de la caja de zapatos y un pequeno codigo en Python | A estudiantes que nunca han programado               |
| C. Rol senior  | Tecnico: JVM, RAM, stack, heap, tipado fuerte, referencia, scope, final              | Si: codigo en Java (int, String, final, new Usuario())           | A un companero desarrollador con experiencia         |

## Ejercicio 5: Descomposicion

- **Paso 1:** La IA listó los 5 requisitos principales (gestión de productos, control de stock, categorías, registro de ventas y alertas de stock bajo).
- **Paso 2:** La IA diseñó las clases necesarias (`Producto`, `Categoria`, `Inventario`) especificando sus atributos y tipos de datos.
- **Paso 3:** La IA entregó el código Java de la clase `Producto` con constructor, getters y setters.
- **Paso 4:** La IA propuso 3 mejoras al código (validación de valores negativos en precio/stock, método `toString` para impresión y uso de `BigDecimal` para precisión de moneda).
- **Comparación:** El pedido de una sola vez produjo una respuesta superficial y resumida; en cambio, el pedido por pasos generó una estructura clara, detallada y un código robusto alineado a los requisitos.

## Ejercicio 6: Prompt estructurado y autocritica

| Qué revisar                                      | Cumple (Sí / No) |
| ------------------------------------------------ | ---------------- |
| ¿Tiene las 4 columnas pedidas?                   | Sí               |
| ¿Incluye el bloqueo después de 3 intentos?       | Sí               |
| ¿Incluye casos con campos vacíos?                | Sí               |
| ¿Indica qué casos agregó en la autocrítica?      | Sí               |
| ¿Hay algún caso repetido o que no tenga sentido? | No               |

```text
PROMPT ESTRUCTURADO:

Eres un QA Engineer Senior especializado en pruebas de software.



Genera los casos de prueba para un módulo de login.



Presenta la respuesta en una tabla Markdown con las siguientes columnas: ID, Descripción, Tipo (Positiva/Negativa/Borde), Resultado Esperado.


MENSAJE DE AUTOCRÍTICA:
Revisa los casos de prueba que acabas de generar. Realiza una autocrítica indicando si falta evaluar algún escenario crítico de seguridad o rendimiento y cómo los mejorarías.
```
