# Universidad de La Sabana

**Maestria en Arquitectura de Software**  
**Asignatura: Testing y Validacion de Software**

**Entrega:** Solucion Unidad 3
**Estudiante:** Andres Felipe Rodriguez  
**Fecha:** 2026-06-08

---

# Unidad 3 - Solucion Caso Libre (Ingenieria de Software)

## Resumen de entrega
En esta entrega presento mi solucion de pruebas para un caso libre de ingenieria de software orientado al registro de ingenieros en turnos on-call.
Desarrolle el dominio con reglas de negocio, implemente pruebas unitarias con enfoque TDD, AAA y BDD, y configure cobertura con JaCoCo con umbral minimo del 80%.
Adicionalmente, deje automatizada la ejecucion en GitHub Actions para validacion continua de build, pruebas y cobertura.

## Caso de negocio
En mi caso de negocio, gestiono el registro de ingenieros para turnos on-call de plataforma.

### Reglas del dominio
- Solo registro un ingeniero valido para guardia.
- Solo permito una inscripcion por employeeId.
- No permito asignar ingenieros inactivos.
- Valido experiencia entre 0 y 40 anios.
- Exijo nivel de certificacion minimo 2.

## Estructura
```text
src/main/java/edu/unisabana/tyvs/domain/
  model/Engineer.java
  model/RegisterResult.java
  service/OnCallRegistry.java

src/test/java/edu/unisabana/tyvs/domain/service/
  OnCallRegistryTest.java
```

## TDD (Red -> Green -> Refactor)
1. RED: escribi la prueba de ingeniero nulo para obtener INVALID.
2. GREEN: agregue validacion defensiva para null en OnCallRegistry.
3. REFACTOR: extraje constantes de limites y mejore nombres.

4. RED: escribi la prueba para ingeniero inactivo con resultado INACTIVE.
5. GREEN: implemente la validacion de estado activo.
6. REFACTOR: ordene validaciones para mayor claridad.

7. RED: escribi la prueba de duplicado por employeeId.
8. GREEN: implemente almacenamiento de ids en un Set.
9. REFACTOR: simplifique condiciones sin alterar comportamiento.

## Patron AAA y BDD
En mis pruebas de OnCallRegistryTest aplico el patron AAA:
- Arrange: preparo el registry y los datos de prueba.
- Act: ejecuto registerEngineerForOnCall.
- Assert: verifico el RegisterResult esperado.

Escenarios BDD ejemplo:
```gherkin
Escenario: Rechazar ingeniero inactivo
  Dado un ingeniero con employeeId valido y estado inactivo
  Cuando intento registrarlo al turno on-call
  Entonces el resultado debe ser INACTIVE
```

```gherkin
Escenario: Rechazar ingeniero con experiencia invalida
  Dado un ingeniero activo con 41 anios de experiencia
  Cuando intento registrarlo al turno on-call
  Entonces el resultado debe ser INVALID_EXPERIENCE
```

## Clases de equivalencia y valores limite
En esta tabla presento las clases de equivalencia y los valores limite que cubri con pruebas:

| Caso | Entrada representativa | Resultado esperado | Test que lo cubre |
|------|------------------------|--------------------|-------------------|
| Nulidad | engineer = null | INVALID | shouldReturnInvalidWhenEngineerIsNull |
| ID invalido | employeeId = 0 o -8 | INVALID | shouldRejectWhenEmployeeIdIsZeroOrNegative |
| Ingeniero inactivo | active = false | INACTIVE | shouldRejectInactiveEngineer |
| Experiencia invalida inferior | yearsExperience = -1 | INVALID_EXPERIENCE | shouldRejectInvalidExperienceBelowZero |
| Experiencia invalida superior | yearsExperience = 41 | INVALID_EXPERIENCE | shouldRejectInvalidExperienceOverForty |
| Ingeniero subcalificado | certificationLevel = 1 | UNDERQUALIFIED | shouldRejectUnderqualifiedEngineerAtLevelOne |
| Borde valido minimo certificacion | certificationLevel = 2 | VALID | shouldAcceptQualifiedEngineerAtLevelTwoWithValidExperience |
| Borde valido de experiencia | yearsExperience = 40 | VALID | shouldAcceptBoundaryExperienceAtForty |
| Duplicado | employeeId repetido | DUPLICATED | shouldRejectDuplicatedEngineerById |

## Cobertura y automatizacion
- Configure JaCoCo en pom.xml.
- Defini umbral minimo obligatorio de 80% de lineas.
- Configure pipeline CI en GitHub Actions para ejecutar mvn -B clean verify.

## Comandos locales
```bash
mvn clean test
mvn clean verify
```

El reporte de cobertura se genera en:
- target/site/jacoco/index.html
