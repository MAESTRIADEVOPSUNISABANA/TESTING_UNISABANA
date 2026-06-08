# Registro de defectos

## Defecto 01
- Caso: ingeniero inactivo con experiencia valida
- Esperado: INACTIVE
- Obtenido (iteracion RED inicial): VALID
- Causa probable: faltaba validacion de estado activo en el servicio
- Estado: Cerrado
- Evidencia: cubierto por shouldRejectInactiveEngineer
