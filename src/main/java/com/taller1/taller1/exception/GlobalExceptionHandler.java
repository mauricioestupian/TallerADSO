package com.taller1.taller1.exception;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
// clase para manejar errores
public class GlobalExceptionHandler {

    /*
     * Esto permite que cualquier activacioón de excepcion de entidad no encontrada
     * se traduzca automáticamente en una respuesta HTTP 404
     * con cuerpo JSON.
     */
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Map<String, Object>> manejarNoEncontrado(EntityNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                Map.of(
                        "error", ex.getMessage(),
                        "codigo", 404,
                        "timestamp", LocalDateTime.now()));
    }

    /*
     * Esto permite que cualquier activacioón de excepcion de servicio o controlador
     * se traduzca automáticamente en una respuesta HTTP 400
     * con cuerpo JSON.
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> manejarArgumentoInvalido(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                Map.of(
                        "error", ex.getMessage(),
                        "codigo", 400,
                        "timestamp", LocalDateTime.now()));
    }

    // Este método captura cualquier excepción del tipo IllegalStateException
    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<String> handleConflict(IllegalStateException ex) {

        // Devuelve una respuesta HTTP 409 Conflict
        // Esto indica que la solicitud no puede completarse debido a un conflicto
        // lógico,
        // como una asignación duplicada o una regla de negocio violada
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

}
