# Entrega Unidad 4 - Gestion de Defectos y Pruebas de Integracion

## Datos del estudiante

**Maestria en Arquitectura de Software**  
**Asignatura: Testing y Validacion de Software**

**Entrega:** Solucion Unidad 4  
**Estudiante:** Andres Felipe Rodriguez  
**Fecha:** 2026-06-04

## Resumen de mi entrega

En esta entrega implementé y documenté la solucion de la Unidad 4 enfocada en pruebas de integracion, pruebas de sistema, automatizacion CI/CD y control de cobertura.

### Lo que realicé

1. Estructuré pruebas de integracion con H2 y pruebas con Mockito para validar reglas de negocio.
2. Incluí pruebas de sistema sobre el endpoint REST para validar respuestas del servicio.
3. Configuré pipeline CI en GitHub Actions para ejecutar build, pruebas y reporte de cobertura.
4. Activé verificacion de cobertura con JaCoCo en Maven para control de calidad automatico.
5. Consolidé toda la evidencia tecnica directamente en este README para que funcione como entregable final.

## Evidencias para revision del profesor

### 1. Solucion de la actividad

#### Contexto de mi trabajo
En esta actividad de Gestion de Defectos, yo tome como base los repositorios solicitados en la guia:

- TYVS-Taller_Pruebas_Integracion
- TYVS-Proyecto_Pruebas_Integracion

Mi objetivo fue dejar evidencia de pruebas automatizadas, pipeline CI/CD y control de calidad, de acuerdo con la rubrica.

#### 1) Estructuracion e implementacion de pruebas
Yo estructure y valide tres niveles de pruebas en el proyecto de Registraduria:

1. Pruebas de integracion con base en memoria H2 para validar colaboracion entre caso de uso y persistencia.
2. Pruebas con mocks (Mockito) para aislar dependencias y validar reglas de negocio.
3. Pruebas de sistema sobre endpoint REST para validar comportamiento de caja negra.

Evidencia tecnica usada:

- RegistryTest.java (integracion con H2)
- RegistryWithMockTest.java (integracion con mocks)
- RegistryControllerIT.java (sistema via HTTP)

#### 2) Pipeline CI/CD funcional
Yo configure un pipeline en GitHub Actions para ejecutar pruebas automaticamente en cada push y pull request.
Este workflow de la Unidad 4 se ejecuta unicamente cuando el destino es la rama main_unidad4, para no mezclar validaciones con entregas de otras unidades.

Archivo principal del workflow de entrega: [.github/workflows/UNIDAD4_CI.yml](.github/workflows/UNIDAD4_CI.yml)

Que hace mi pipeline:

1. Descarga el codigo.
2. Configura Java 17.
3. Ejecuta mvn clean verify dentro del modulo registraduria.
4. Publica el reporte de cobertura JaCoCo como artefacto.

Con esto garantizo que las pruebas se ejecuten antes de integrar cambios.

#### 3) Metricas de cubrimiento y regla de calidad
Yo actualice la configuracion de Maven para que JaCoCo no este desactivado y para exigir una cobertura minima de lineas.

Ajustes aplicados en pom.xml:

1. Cambie jacoco.skip a false.
2. Agregue jacoco:check en fase verify.
3. Defini una regla minima de cobertura de lineas de 0.70.

Esto permite bloquear builds si la cobertura cae por debajo del umbral definido.

#### 4) Registro de defectos y trazabilidad
Yo utilice el formato de defectos como evidencia de gestion:

- Defectos funcionales en reglas de negocio.
- Defectos de integracion en persistencia y mocks.
- Defectos de sistema en manejo HTTP.

Mi analisis se centro en priorizar defectos de impacto alto y asegurar su trazabilidad (caso de prueba, esperado, obtenido, causa probable, estado, prioridad).

#### 5) Resultado frente a la rubrica
Frente a los criterios de evaluacion, yo considero que mi avance cumple de la siguiente forma:

1. Implementacion de pruebas automatizadas: Cumplo, porque hay pruebas de integracion y sistema estructuradas.
2. Configuracion de pipeline CI/CD: Cumplo, porque el pipeline ejecuta pruebas en push/PR.
3. Metricas y control de calidad: Cumplo parcialmente-alto, porque habilite JaCoCo y regla minima de cobertura.
4. Restriccion de integracion: Cumplo tecnicamente por chequeo automatico; pendiente habilitar branch protection en GitHub para exigir status checks antes de merge.

#### 6) Dificultad encontrada y decision tecnica
Durante la validacion local, yo identifique que en mi entorno no estaba instalado Maven (mvn: command not found).
Por esa razon:

1. Deje la validacion automatizada en CI (GitHub Actions), que no depende de mi entorno local.
2. Mantuve comandos estandar Maven para ejecucion reproducible por cualquier integrante del equipo.

#### 7) Comandos que yo ejecutaria para evidencias finales

- cd TYVS-Taller_Pruebas_Integracion/registraduria
- mvn clean verify
- mvn jacoco:report

#### 8) Reflexion tecnica
En esta actividad, yo confirme que las pruebas de integracion me permiten detectar fallos que una prueba unitaria aislada no ve, especialmente en la colaboracion entre capas. Tambien entendi que automatizar pruebas en pipeline no es solo un requisito tecnico: es una barrera de calidad que previene defectos antes del merge. Finalmente, al activar cobertura y reglas minimas, yo convierto la calidad en un criterio verificable y no en una percepcion subjetiva.

#### 9) Pendiente para cerrar al 100%
Me falta habilitar en GitHub la proteccion de rama main_unidad4 para bloquear merges cuando falle el workflow de CI.

### 2. Plan de entrega del proyecto final

#### Mi enfoque para el proyecto final
En este repositorio yo voy a consolidar la evidencia del proyecto integrador de pruebas de integracion y sistema. Mi plan de trabajo es el siguiente:

1. Definir el alcance funcional del sistema y sus endpoints criticos.
2. Implementar pruebas de integracion por capas (aplicacion + persistencia).
3. Implementar pruebas de sistema sobre API REST.
4. Configurar pipeline CI/CD para ejecutar pruebas en cada push y pull request.
5. Generar reporte de cobertura y registrar defectos detectados.

#### Evidencias que voy a entregar
1. Script de pruebas automatizadas con Maven (mvn clean verify).
2. Pipeline CI/CD funcional en GitHub Actions.
3. Reporte de cobertura (JaCoCo) con analisis de resultados.
4. Registro de defectos con estado, prioridad y trazabilidad.
5. Reflexion tecnica final sobre hallazgos y mejoras.

#### Criterios de calidad que yo voy a cumplir
1. Cobertura global minima de 80% (objetivo).
2. Cobertura de integracion minima de 70%.
3. Al menos 3 pruebas de integracion y 2 pruebas de sistema.
4. Cero errores HTTP 500 no manejados en endpoints principales.

#### Restriccion de integracion
Yo voy a proteger la rama main_unidad4 para impedir merges cuando falle el pipeline de pruebas.

#### Nota de ejecucion
En mi entorno local actual no tengo Maven instalado, por lo que la validacion oficial la realizare desde CI mientras instalo el entorno local.

### Otras evidencias tecnicas

1. Pruebas de integracion y sistema: [registraduria/src/test/java/edu/unisabana/tyvs/registry/application/usecase/RegistryTest.java](registraduria/src/test/java/edu/unisabana/tyvs/registry/application/usecase/RegistryTest.java), [registraduria/src/test/java/edu/unisabana/tyvs/registry/application/usecase/RegistryWithMockTest.java](registraduria/src/test/java/edu/unisabana/tyvs/registry/application/usecase/RegistryWithMockTest.java), [registraduria/src/test/java/edu/unisabana/tyvs/registry/delivery/rest/RegistryControllerIT.java](registraduria/src/test/java/edu/unisabana/tyvs/registry/delivery/rest/RegistryControllerIT.java)
2. Workflow de unidad: [.github/workflows/UNIDAD4_CI.yml](.github/workflows/UNIDAD4_CI.yml)
3. Configuracion de cobertura JaCoCo: [registraduria/pom.xml](registraduria/pom.xml)

## Ejecucion

Comando de validacion del proyecto:

```bash
cd registraduria
mvn clean verify
```

Con esto dejo trazabilidad completa de la solucion y de la evidencia tecnica solicitada para la evaluacion de la Unidad 4.
