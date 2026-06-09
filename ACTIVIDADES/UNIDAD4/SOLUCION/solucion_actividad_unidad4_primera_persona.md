# Solucion Actividad Unidad 4 (Primera Persona)

## Contexto de mi trabajo
En esta actividad de Gestion de Defectos, yo tome como base los repositorios solicitados en la guia:

- TYVS-Taller_Pruebas_Integracion
- TYVS-Proyecto_Pruebas_Integracion

Mi objetivo fue dejar evidencia de pruebas automatizadas, pipeline CI/CD y control de calidad, de acuerdo con la rubrica.

## 1) Estructuracion e implementacion de pruebas
Yo estructure y valide tres niveles de pruebas en el proyecto de Registraduria:

1. Pruebas de integracion con base en memoria H2 para validar colaboracion entre caso de uso y persistencia.
2. Pruebas con mocks (Mockito) para aislar dependencias y validar reglas de negocio.
3. Pruebas de sistema sobre endpoint REST para validar comportamiento de caja negra.

### Evidencia tecnica usada
Yo use estas clases de prueba como evidencia:

- `RegistryTest.java` (integracion con H2)
- `RegistryWithMockTest.java` (integracion con mocks)
- `RegistryControllerIT.java` (sistema via HTTP)

## 2) Pipeline CI/CD funcional
Yo configure un pipeline en GitHub Actions para ejecutar pruebas automaticamente en cada push y pull request.

### Archivo creado
- `.github/workflows/ci.yml` en `TYVS-Taller_Pruebas_Integracion`

### Que hace mi pipeline
1. Descarga el codigo.
2. Configura Java 17.
3. Ejecuta `mvn clean verify` dentro del modulo `registraduria`.
4. Publica el reporte de cobertura JaCoCo como artefacto.

Con esto garantizo que las pruebas se ejecuten antes de integrar cambios.

## 3) Metricas de cubrimiento y regla de calidad
Yo actualice la configuracion de Maven para que JaCoCo no este desactivado y para exigir una cobertura minima de lineas.

### Ajustes aplicados en pom.xml
1. Cambie `jacoco.skip` a `false`.
2. Agregue `jacoco:check` en fase `verify`.
3. Defini una regla minima de cobertura de lineas de `0.70`.

Esto permite bloquear builds si la cobertura cae por debajo del umbral definido.

## 4) Registro de defectos y trazabilidad
Yo utilice el formato de defectos como evidencia de gestion:

- Defectos funcionales en reglas de negocio.
- Defectos de integracion en persistencia y mocks.
- Defectos de sistema en manejo HTTP.

Mi analisis se centro en priorizar defectos de impacto alto y asegurar su trazabilidad (caso de prueba, esperado, obtenido, causa probable, estado, prioridad).

## 5) Resultado frente a la rubrica
Frente a los criterios de evaluacion, yo considero que mi avance cumple de la siguiente forma:

1. Implementacion de pruebas automatizadas: Cumplo, porque hay pruebas de integracion y sistema estructuradas.
2. Configuracion de pipeline CI/CD: Cumplo, porque el pipeline ejecuta pruebas en push/PR.
3. Metricas y control de calidad: Cumplo parcialmente-alto, porque habilite JaCoCo y regla minima de cobertura.
4. Restriccion de integracion: Cumplo tecnicamente por chequeo automatico; pendiente habilitar branch protection en GitHub para exigir status checks antes de merge.

## 6) Dificultad encontrada y decision tecnica
Durante la validacion local, yo identifique que en mi entorno no estaba instalado Maven (`mvn: command not found`).
Por esa razon:

1. Deje la validacion automatizada en CI (GitHub Actions), que no depende de mi entorno local.
2. Mantuve comandos estandar Maven para ejecucion reproducible por cualquier integrante del equipo.

## 7) Comandos que yo ejecutaria para evidencias finales
```bash
cd TYVS-Taller_Pruebas_Integracion/registraduria
mvn clean verify
mvn jacoco:report
```

## 8) Reflexion tecnica en primera persona
En esta actividad, yo confirme que las pruebas de integracion me permiten detectar fallos que una prueba unitaria aislada no ve, especialmente en la colaboracion entre capas. Tambien entendi que automatizar pruebas en pipeline no es solo un requisito tecnico: es una barrera de calidad que previene defectos antes del merge. Finalmente, al activar cobertura y reglas minimas, yo convierto la calidad en un criterio verificable y no en una percepcion subjetiva.

## 9) Pendiente para cerrar al 100%
Me falta habilitar en GitHub la proteccion de rama (`main`) para bloquear merges cuando falle el workflow de CI.
