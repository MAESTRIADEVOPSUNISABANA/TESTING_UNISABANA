# Unidad 3 - Solucion Caso Libre (Ingenieria de Software)

## Caso de negocio
Esta solucion implementa un caso libre de un equipo de ingenieria de software: registro de ingenieros para turnos on-call de plataforma.

### Reglas del dominio
- Solo se registra un ingeniero valido para guardia.
- Solo puede existir una inscripcion por employeeId.
- Ingeniero inactivo no puede ser asignado.
- Debe tener experiencia valida entre 0 y 40 anios.
- Debe tener nivel de certificacion minimo 2.

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
1. RED: prueba de ingeniero nulo devuelve INVALID.
2. GREEN: validacion defensiva para null en OnCallRegistry.
3. REFACTOR: extraccion de constantes de limites y reglas.

4. RED: prueba de ingeniero inactivo devuelve INACTIVE.
5. GREEN: se agrega validacion de estado activo.
6. REFACTOR: orden de validaciones para reglas prioritarias.

7. RED: prueba de duplicado por employeeId.
8. GREEN: almacenamiento de ids en un Set.
9. REFACTOR: limpieza y consolidacion de condiciones.

## Patron AAA y BDD
Todas las pruebas en OnCallRegistryTest siguen:
- Arrange: creacion del registry y datos de prueba.
- Act: llamada a registerEngineerForOnCall.
- Assert: verificacion del RegisterResult.

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
- Plugin JaCoCo configurado en pom.xml.
- Umbral minimo obligatorio: 80% de lineas.
- Pipeline CI en GitHub Actions ejecuta `mvn -B clean verify`.

## Comandos locales
```bash
mvn clean test
mvn clean verify
```

Reporte de cobertura generado en:
- target/site/jacoco/index.html
