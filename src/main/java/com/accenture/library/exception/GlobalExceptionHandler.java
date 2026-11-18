package com.accenture.library.exception;

import java.time.LocalDateTime;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import com.accenture.library.dto.error.ErrorResponse;

import jakarta.servlet.http.HttpServletRequest;

// Crea GlobalExceptionHandler como clase anotada con @RestControllerAdvice que centralice el tratamiento de excepciones de la API; incluye métodos para gestionar ResourceNotFoundException devolviendo HTTP 404 y ResourceConflictException devolviendo HTTP 409, construyendo en ambos casos un ErrorResponse con timestamp actual, código HTTP, descripción estándar del estado, mensaje de la excepción y URI de la petición sin parámetros de consulta; utiliza ResponseEntity, HttpStatus y HttpServletRequest, añade los imports necesarios y Javadoc en español a la clase y todos sus métodos, no utilices lambdas, referencias a métodos ni Streams. -> generado por IA
// Corrige GlobalExceptionHandler para que ambos métodos construyan ErrorResponse mediante su constructor sin arguementos y setters, sin utilizar Builder; conserva los dos @ExceptionHandler, los códigos HTTP 404 y 409, timestamp actual, descripción estándar, mensaje de la excepción y request.getRequestURI(); utiliza ResponseEntity y mantén el Javadoc en español de todos los métodos, sin lambdas ni Streams. -> generado por IA
/**
 * Clase que centraliza el tratamiento de excepciones en la API.
 * 
 * @author Javier Roldán Pomareta
 * @version 1.0
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Maneja la excepción ResourceNotFoundException.
     * 
     * @param ex      La excepción lanzada.
     * @param request La solicitud HTTP.
     * @return La respuesta con el error correspondiente.
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex,
            HttpServletRequest request) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setTimestamp(LocalDateTime.now());
        errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        errorResponse.setError(HttpStatus.NOT_FOUND.getReasonPhrase());
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setPath(request.getRequestURI());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    /**
     * Maneja la excepción ResourceConflictException.
     * 
     * @param ex      La excepción lanzada.
     * @param request La solicitud HTTP.
     * @return La respuesta con el error correspondiente.
     */
    @ExceptionHandler(ResourceConflictException.class)
    public ResponseEntity<ErrorResponse> handleResourceConflictException(ResourceConflictException ex,
            HttpServletRequest request) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setTimestamp(LocalDateTime.now());
        errorResponse.setStatus(HttpStatus.CONFLICT.value());
        errorResponse.setError(HttpStatus.CONFLICT.getReasonPhrase());
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setPath(request.getRequestURI());
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    // Añade un método para gestionar MethodArgumentNotValidException y devolver
    // HTTP 400; construye un ErrorResponse mediante constructor sin argumentos y
    // setters, incluyendo timestamp actual, código 400, descripción estándar Bad
    // Request, todos los mensajes de validación de campos y reglas personalizadas
    // concatenados en message, y request.getRequestURI() en path; recorre los
    // errores con un bucle clásico sin lambdas ni Streams, utiliza StringBuilder
    // para construir el mensaje, añade los imports necesarios y Javadoc en español.
    // -> generado por IA
    // Modifica handleMethodArgumentNotValidException para que, además de recorrer
    // getFieldErrors(), recorra también getGlobalErrors() mediante un segundo bucle
    // clásico; añade al StringBuilder el mensaje de cada error global, conserva el
    // formato actual de los errores de campo y la eliminación del separador final;
    // no modifiques los otros métodos ni utilices lambdas o Streams. -> generado
    // por IA
    /**
     * Maneja la excepción MethodArgumentNotValidException.
     * 
     * @param ex      La excepción lanzada.
     * @param request La solicitud HTTP.
     * @return La respuesta con el error correspondiente.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex,
            HttpServletRequest request) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setTimestamp(LocalDateTime.now());
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        errorResponse.setError(HttpStatus.BAD_REQUEST.getReasonPhrase());

        StringBuilder messageBuilder = new StringBuilder();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            messageBuilder.append(fieldError.getField())
                    .append(": ")
                    .append(fieldError.getDefaultMessage())
                    .append("; ");
        }

        // Recorre los errores globales
        for (ObjectError globalError : ex.getBindingResult().getGlobalErrors()) {
            messageBuilder.append(globalError.getObjectName())
                    .append(": ")
                    .append(globalError.getDefaultMessage())
                    .append("; ");
        }
        // Elimina el último "; " si hay mensajes
        if (!messageBuilder.isEmpty()) {
            messageBuilder.setLength(messageBuilder.length() - 2);
        }

        errorResponse.setMessage(messageBuilder.toString());
        errorResponse.setPath(request.getRequestURI());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    // Añade un método para gestionar HttpMessageNotReadableException y devolver
    // HTTP 400; construye un ErrorResponse mediante constructor sin argumentos y
    // setters, con timestamp actual, código 400, descripción estándar Bad Request,
    // un mensaje claro en español indicando que el JSON enviado contiene datos
    // inválidos o mal formados y request.getRequestURI() como path; utiliza
    // ResponseEntity y HttpStatus, añade el import necesario y Javadoc en español,
    // conserva todos los métodos existentes y no emplees lambdas ni Streams. ->
    // generado por IA
    /**
     * Maneja la excepción HttpMessageNotReadableException.
     * 
     * @param ex      La excepción lanzada.
     * @param request La solicitud HTTP.
     * @return La respuesta con el error correspondiente.
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex,
            HttpServletRequest request) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setTimestamp(LocalDateTime.now());
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        errorResponse.setError(HttpStatus.BAD_REQUEST.getReasonPhrase());
        errorResponse.setMessage("El JSON enviado contiene datos inválidos o mal formados.");
        errorResponse.setPath(request.getRequestURI());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    // Añade un método para gestionar NoResourceFoundException y devolver HTTP 404;
    // construye ErrorResponse mediante constructor sin argumentos y setters, con
    // timestamp actual, código 404, descripción estándar Not Found, mensaje en
    // español indicando que la ruta solicitada no existe y request.getRequestURI()
    // como path; utiliza ResponseEntity y HttpStatus, añade los imports necesarios
    // y Javadoc en español, conserva los métodos existentes y no emplees lambdas ni
    // Streams. -> generado por IA
    /**
     * Maneja la excepción NoResourceFoundException.
     * 
     * @param ex      La excepción lanzada.
     * @param request La solicitud HTTP.
     * @return La respuesta con el error correspondiente.
     */
    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ErrorResponse> handleNoResourceFoundException(NoResourceFoundException ex,
            HttpServletRequest request) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setTimestamp(LocalDateTime.now());
        errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        errorResponse.setError(HttpStatus.NOT_FOUND.getReasonPhrase());
        errorResponse.setMessage("La ruta solicitada no existe.");
        errorResponse.setPath(request.getRequestURI());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    // Añade un método para gestionar NoHandlerFoundException y devolver HTTP 404;
    // construye ErrorResponse mediante constructor sin argumentos y setters, con
    // timestamp actual, código 404, descripción estándar Not Found, mensaje en
    // español indicando que no existe un controlador para la ruta solicitada y
    // request.getRequestURI() como path; utiliza ResponseEntity y HttpStatus, añade
    // los imports necesarios y Javadoc en español, conserva los métodos existentes
    // y no emplees lambdas ni Streams. -> generado por IA
    /**
     * Maneja la excepción NoHandlerFoundException.
     * 
     * @param ex      La excepción lanzada.
     * @param request La solicitud HTTP.
     * @return La respuesta con el error correspondiente.
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ErrorResponse> handleNoHandlerFoundException(NoHandlerFoundException ex,
            HttpServletRequest request) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setTimestamp(LocalDateTime.now());
        errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        errorResponse.setError(HttpStatus.NOT_FOUND.getReasonPhrase());
        errorResponse.setMessage("No existe un controlador para la ruta solicitada.");
        errorResponse.setPath(request.getRequestURI());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    // Añade un método para gestionar MethodArgumentTypeMismatchException y devolver
    // HTTP 400; construye ErrorResponse mediante constructor sin argumentos y
    // setters, con timestamp actual, código 400, descripción estándar Bad Request,
    // mensaje en español indicando que el parámetro recibido tiene un tipo o
    // formato incorrecto e incluyendo el nombre del parámetro y el valor recibido,
    // y request.getRequestURI() como path; utiliza ResponseEntity y HttpStatus,
    // añade los imports necesarios y Javadoc en español, conserva los métodos
    // existentes y no emplees lambdas ni Streams. -> generado por IA
    // Corrige handleMethodArgumentTypeMismatchException para que el mensaje incluya
    // el nombre del parámetro mediante ex.getName() y el valor recibido mediante
    // ex.getValue(), utilizando concatenación de cadenas; conserva el resto del
    // método, el código HTTP 400, los cinco campos de ErrorResponse y el Javadoc en
    // español. -> generado por IA
    /**
     * Maneja la excepción MethodArgumentTypeMismatchException.
     * 
     * @param ex      La excepción lanzada.
     * @param request La solicitud HTTP.
     * @return La respuesta con el error correspondiente.
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentTypeMismatchException(
            MethodArgumentTypeMismatchException ex, HttpServletRequest request) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setTimestamp(LocalDateTime.now());
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        errorResponse.setError(HttpStatus.BAD_REQUEST.getReasonPhrase());
        errorResponse.setMessage("El parámetro " + ex.getName()
                + " tiene un tipo o formato incorrecto. Valor recibido: " + ex.getValue());
        errorResponse.setPath(request.getRequestURI());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    // Añade un método para gestionar MissingServletRequestParameterException y
    // devolver HTTP 400; construye ErrorResponse mediante constructor sin
    // argumentos y setters, con timestamp actual, código 400, descripción estándar
    // Bad Request, mensaje en español indicando el nombre del parámetro obligatorio
    // que falta mediante ex.getParameterName(), y request.getRequestURI() como
    // path; utiliza ResponseEntity y HttpStatus, añade los imports necesarios y
    // Javadoc en español, conserva los métodos existentes y no emplees lambdas ni
    // Streams. -> generado por IA
    /**
     * Maneja la excepción MissingServletRequestParameterException.
     * 
     * @param ex      La excepción lanzada.
     * @param request La solicitud HTTP.
     * @return La respuesta con el error correspondiente.
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorResponse> handleMissingServletRequestParameterException(
            MissingServletRequestParameterException ex, HttpServletRequest request) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setTimestamp(LocalDateTime.now());
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        errorResponse.setError(HttpStatus.BAD_REQUEST.getReasonPhrase());
        errorResponse
                .setMessage("El parámetro obligatorio " + ex.getParameterName() + " no está presente en la solicitud.");
        errorResponse.setPath(request.getRequestURI());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    // Añade un método para gestionar HttpRequestMethodNotSupportedException y
    // devolver HTTP 405; construye ErrorResponse mediante constructor sin
    // argumentos y setters, con timestamp actual, código 405, descripción estándar
    // Method Not Allowed, mensaje en español indicando el método HTTP no permitido
    // mediante ex.getMethod(), y request.getRequestURI() como path; utiliza
    // ResponseEntity y HttpStatus, añade los imports necesarios y Javadoc en
    // español, conserva los métodos existentes y no emplees lambdas ni Streams. ->
    // generado por IA
    /**
     * Maneja la excepción HttpRequestMethodNotSupportedException.
     * 
     * @param ex      La excepción lanzada.
     * @param request La solicitud HTTP.
     * @return La respuesta con el error correspondiente.
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponse> handleHttpRequestMethodNotSupportedException(
            HttpRequestMethodNotSupportedException ex, HttpServletRequest request) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setTimestamp(LocalDateTime.now());
        errorResponse.setStatus(HttpStatus.METHOD_NOT_ALLOWED.value());
        errorResponse.setError(HttpStatus.METHOD_NOT_ALLOWED.getReasonPhrase());
        errorResponse.setMessage("El método HTTP " + ex.getMethod() + " no está permitido para esta solicitud.");
        errorResponse.setPath(request.getRequestURI());
        return new ResponseEntity<>(errorResponse, HttpStatus.METHOD_NOT_ALLOWED);
    }

    // Añade un método para gestionar HttpMediaTypeNotSupportedException y devolver
    // HTTP 415; construye ErrorResponse mediante constructor sin argumentos y
    // setters, con timestamp actual, código 415, descripción estándar Unsupported
    // Media Type, mensaje en español indicando el Content-Type no admitido mediante
    // ex.getContentType(), y request.getRequestURI() como path; utiliza
    // ResponseEntity y HttpStatus, añade los imports necesarios y Javadoc en
    // español, conserva los métodos existentes y no emplees lambdas ni Streams. ->
    // generado por IA
    /**
     * Maneja la excepción HttpMediaTypeNotSupportedException.
     * 
     * @param ex      La excepción lanzada.
     * @param request La solicitud HTTP.
     * @return La respuesta con el error correspondiente.
     */
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<ErrorResponse> handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException ex,
            HttpServletRequest request) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setTimestamp(LocalDateTime.now());
        errorResponse.setStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value());
        errorResponse.setError(HttpStatus.UNSUPPORTED_MEDIA_TYPE.getReasonPhrase());
        errorResponse.setMessage("El Content-Type " + ex.getContentType() + " no está admitido para esta solicitud.");
        errorResponse.setPath(request.getRequestURI());
        return new ResponseEntity<>(errorResponse, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

    // Añade un método para gestionar HttpMediaTypeNotAcceptableException y devolver
    // HTTP 406; construye ErrorResponse mediante constructor sin argumentos y
    // setters, con timestamp actual, código 406, descripción estándar Not
    // Acceptable, mensaje en español indicando que el servidor no puede generar una
    // respuesta en un formato aceptable para el cliente, y request.getRequestURI()
    // como path; utiliza ResponseEntity y HttpStatus, añade los imports necesarios
    // y Javadoc en español, conserva los métodos existentes y no emplees lambdas ni
    // Streams. -> generado por IA
    /**
     * Maneja la excepción HttpMediaTypeNotAcceptableException.
     * 
     * @param ex      La excepción lanzada.
     * @param request La solicitud HTTP.
     * @return La respuesta con el error correspondiente.
     */
    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    public ResponseEntity<ErrorResponse> handleHttpMediaTypeNotAcceptableException(
            HttpMediaTypeNotAcceptableException ex, HttpServletRequest request) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setTimestamp(LocalDateTime.now());
        errorResponse.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
        errorResponse.setError(HttpStatus.NOT_ACCEPTABLE.getReasonPhrase());
        errorResponse.setMessage("El servidor no puede generar una respuesta en un formato aceptable para el cliente.");
        errorResponse.setPath(request.getRequestURI());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_ACCEPTABLE);
    }

    // Añade un método para gestionar DataIntegrityViolationException y devolver
    // HTTP 409; construye ErrorResponse mediante constructor sin argumentos y
    // setters, con timestamp actual, código 409, descripción estándar Conflict,
    // mensaje en español indicando que la operación no puede completarse porque los
    // datos incumplen una restricción de integridad de la base de datos, y
    // request.getRequestURI() como path; utiliza ResponseEntity y HttpStatus, añade
    // los imports necesarios y Javadoc en español, conserva los métodos existentes
    // y no emplees lambdas ni Streams. -> generado por IA
    /**
     * Maneja la excepción DataIntegrityViolationException.
     * 
     * @param ex      La excepción lanzada.
     * @param request La solicitud HTTP.
     * @return La respuesta con el error correspondiente.
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleDataIntegrityViolationException(DataIntegrityViolationException ex,
            HttpServletRequest request) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setTimestamp(LocalDateTime.now());
        errorResponse.setStatus(HttpStatus.CONFLICT.value());
        errorResponse.setError(HttpStatus.CONFLICT.getReasonPhrase());
        errorResponse.setMessage(
                "La operación no puede completarse porque los datos incumplen una restricción de integridad de la base de datos.");
        errorResponse.setPath(request.getRequestURI());
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    // Añade un método para gestionar cualquier excepción genérica Exception y
    // devolver HTTP 500; construye ErrorResponse mediante constructor sin
    // argumentos y setters, con timestamp actual, código 500, descripción estándar
    // Internal Server Error, mensaje genérico en español indicando que se ha
    // producido un error interno inesperado, sin exponer detalles técnicos ni
    // información interna de la excepción, y request.getRequestURI() como path;
    // utiliza ResponseEntity y HttpStatus, añade los imports necesarios y Javadoc
    // en español, conserva los métodos existentes y no emplees lambdas ni Streams.
    // -> generado por IA
    /**
     * Maneja cualquier excepción genérica Exception.
     * 
     * @param ex      La excepción lanzada.
     * @param request La solicitud HTTP.
     * @return La respuesta con el error correspondiente.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex, HttpServletRequest request) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setTimestamp(LocalDateTime.now());
        errorResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorResponse.setError(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        errorResponse.setMessage("Se ha producido un error interno inesperado.");
        errorResponse.setPath(request.getRequestURI());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Añade un método para gestionar BadCredentialsException y devolver HTTP 401
    // Unauthorized. Construye un ErrorResponse mediante constructor sin argumentos
    // y setters, incluyendo timestamp actual, código 401, descripción estándar
    // Unauthorized, mensaje "Usuario o contraseña incorrectos." y
    // request.getRequestURI() como path. Utiliza ResponseEntity y HttpStatus. Añade
    // el import necesario y Javadoc en español. Conserva todos los métodos
    // existentes y no utilices lambdas, referencias a métodos ni Streams. ->
    // generado por IA
    /**
     * Maneja la excepción BadCredentialsException.
     * 
     * @param ex      La excepción lanzada.
     * @param request La solicitud HTTP.
     * @return La respuesta con el error correspondiente.
     */
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleBadCredentialsException(BadCredentialsException ex,
            HttpServletRequest request) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setTimestamp(LocalDateTime.now());
        errorResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
        errorResponse.setError(HttpStatus.UNAUTHORIZED.getReasonPhrase());
        errorResponse.setMessage("Usuario o contraseña incorrectos.");
        errorResponse.setPath(request.getRequestURI());
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

    // Añade un método para gestionar IllegalArgumentException mediante
    // @ExceptionHandler. Debe devolver ResponseEntity<ErrorResponse> con HTTP 400
    // BAD_REQUEST. Recibe IllegalArgumentException y HttpServletRequest como
    // parámetros. Construye ErrorResponse utilizando LocalDateTime.now(), el código
    // 400, la descripción BAD_REQUEST, el mensaje de la excepción y
    // request.getRequestURI(). Devuelve la respuesta mediante
    // ResponseEntity.badRequest().body(errorResponse). Incluye Javadoc completo en
    // español y sigue el estilo de los demás métodos de esta clase. No modifiques
    // ningún método existente. -> generado por IA
    // Corrige el método handleIllegalArgumentException para construir ErrorResponse
    // utilizando su constructor vacío y sus setters existentes. Establece timestamp
    // con LocalDateTime.now(), status con HttpStatus.BAD_REQUEST.value(), error con
    // HttpStatus.BAD_REQUEST.getReasonPhrase(), message con exception.getMessage()
    // y path con request.getRequestURI(). Devuelve
    // ResponseEntity.badRequest().body(errorResponse). Mantén la firma del método,
    // su anotación @ExceptionHandler y su Javadoc. No modifiques ErrorResponse ni
    // ningún otro método. -> generado por IA
    /**
     * Gestiona los errores de argumentos inválidos.
     *
     * @param exception Excepción producida por un argumento inválido.
     * @param request   Solicitud HTTP que ha provocado el error.
     * @return Respuesta HTTP 400 con los detalles del error.
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(
            IllegalArgumentException exception,
            HttpServletRequest request) {

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setTimestamp(LocalDateTime.now());
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        errorResponse.setError(HttpStatus.BAD_REQUEST.getReasonPhrase());
        errorResponse.setMessage(exception.getMessage());
        errorResponse.setPath(request.getRequestURI());

        return ResponseEntity.badRequest().body(errorResponse);
    }

}