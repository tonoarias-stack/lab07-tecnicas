# Tarea: Mi prompt avanzado

## Tarea elegida

Generar casos de prueba para un módulo de **registro de usuarios** (formulario web con nombre, correo, contraseña y confirmación de contraseña).

Herramienta de IA usada: Claude

## Version 1: prompt basico

**Técnica agregada:** ninguna (prompt básico, zero-shot).

**Por qué:** es el punto de partida para poder comparar cuánto mejora la respuesta con cada técnica.

```text
Dame casos de prueba para un registro de usuarios.
```

**Qué pasó en la respuesta:** la IA devolvió un análisis largo y general (campos obligatorios, reglas de validación, vectores de ataque), pero sin un formato fijo, sin datos de entrada concretos y sin resultados esperados por caso. Servía como lectura, pero no como una lista de casos de prueba lista para usar.

## Version 2

**Técnica agregada:** role prompting + prompt estructurado (etiquetas).

**Por qué:** el rol enfoca la respuesta en pruebas de software, y las etiquetas separan rol, contexto, tarea y formato para que la IA no mezcle las partes ni ignore alguna.

```text
<rol>Actua como analista de pruebas de software (QA) con experiencia en aplicaciones web.</rol>
<contexto>Modulo de registro con nombre, correo, contrasena y confirmacion de contrasena. La contrasena exige minimo 8 caracteres, una mayuscula, un numero y un caracter especial. El correo debe ser unico.</contexto>
<tarea>Escribe 8 casos de prueba para este modulo.</tarea>
<formato>Tabla con las columnas: ID, escenario, datos de entrada, resultado esperado.</formato>
```

**Qué mejoró:** la respuesta pasó a ser una tabla con las 4 columnas pedidas y con exactamente 8 casos, cada uno con datos de entrada y resultado esperado. Lo que seguía faltando: el estilo de las filas variaba (algunas muy cortas, otras muy largas) y se omitían casos límite como campos vacíos, espacios o correo duplicado.

## Version 3: prompt final

**Técnica agregada:** few-shot + chain of thought + autocrítica (sobre lo anterior).

**Por qué:** los ejemplos fijan el estilo exacto de cada fila; pedir que piense paso a paso qué puede fallar mejora la cobertura de casos; y la autocrítica obliga a buscar los casos límite que faltaron en la primera tabla.

```text
<rol>Actua como analista de pruebas de software (QA) senior que disena casos de prueba para aplicaciones web y se los entrega a un equipo de desarrollo junior.</rol>
<contexto>Modulo de registro con nombre, correo, contrasena y confirmacion de contrasena. La contrasena exige minimo 8 caracteres, una mayuscula, un numero y un caracter especial. El correo debe ser unico.</contexto>
<ejemplos>
RG-01 | Registro exitoso | Nombre: Ana, correo: ana@mail.com, contrasena: Clave#2026, confirmacion: Clave#2026 | Cuenta creada y mensaje de exito
RG-02 | Correo sin arroba | correo: anamail.com | Mensaje de error de formato de correo
</ejemplos>
<tarea>Piensa paso a paso que puede fallar y escribe 8 casos de prueba para este modulo. Despues revisa tu propia tabla: agrega los casos limite que falten (campos vacios, espacios, correo duplicado, contrasenas que no coinciden, inyeccion de texto) e indica cuales agregaste.</tarea>
<formato>Tabla con las columnas: ID, escenario, datos de entrada, resultado esperado. Al final, una lista corta con los IDs agregados en la revision. Responde en espanol.</formato>
```

**Qué mejoró:** todas las filas siguieron el mismo formato del ejemplo (ID tipo RG-xx, datos de entrada concretos, resultado esperado claro). La autocrítica agregó casos que antes no aparecían (campos vacíos, correo duplicado, contraseñas que no coinciden, inyección de texto) y listó los IDs agregados al final.

## Tecnicas usadas en el prompt final

| Técnica | Parte del prompt final |
|---|---|
| Role prompting | `<rol>`: analista QA senior que entrega a un equipo junior |
| Prompt estructurado | Etiquetas `<rol>`, `<contexto>`, `<ejemplos>`, `<tarea>`, `<formato>` |
| Few-shot | `<ejemplos>`: dos filas modelo (RG-01 y RG-02) |
| Chain of Thought | "Piensa paso a paso que puede fallar..." dentro de `<tarea>` |
| Autocrítica | "Despues revisa tu propia tabla: agrega los casos limite que falten..." dentro de `<tarea>` |

## Evaluacion del resultado

| Criterio | Cumple (Sí / No) |
|---|---|
| ¿Tiene las 4 columnas pedidas (ID, escenario, datos de entrada, resultado esperado)? | Sí |
| ¿Todas las filas siguen el mismo formato del ejemplo? | Sí |
| ¿Incluye casos con campos vacíos y correo duplicado? | Sí |
| ¿Indica qué casos agregó en la autocrítica? | Sí |
| ¿Hay algún caso repetido o que no tenga sentido? | No |

**Observación:** aunque la tabla final cumple los criterios, la IA puede equivocarse incluso al revisarse a sí misma, por eso revisé yo la tabla final caso por caso antes de darla por buena.

## Por que elegi estas tecnicas

Elegí **role prompting** porque quería la mirada de un analista de pruebas y no una explicación general de qué es un registro de usuarios. Usé **prompt estructurado** porque el prompt tiene varias partes (rol, contexto, ejemplos, tarea y formato) y las etiquetas evitan que se mezclen. Elegí **few-shot** porque necesitaba que todas las filas tuvieran el mismo formato, de modo que se puedan copiar a una hoja de cálculo sin corregirlas una por una. Agregué **Chain of Thought** como una instrucción corta ("piensa qué puede fallar") para mejorar la cobertura, y **autocrítica** porque la primera tabla casi siempre omite casos límite como campos vacíos o duplicados. No usé **descomposición** porque esta tarea cabe en un solo pedido; dividirla en varios mensajes solo habría hecho el proceso más largo sin mejorar el resultado.