package com.accenture.library.enums;

// Regenera esta clase completa en el paquete com.accenture.library.enums manteniendo exactamente las constantes ACTIVE, RETURNED y OVERDUE, añade Javadoc de clase en español explicando que OVERDUE es un estado calculado para las respuestas y nunca se persiste en la base de datos, sin añadir métodos ni constantes adicionales. -> generado por IA

/**
 * Enumeración que representa los posibles estados de un préstamo.
 * <p>
 * La constante {@code OVERDUE} es un estado calculado para las respuestas y nunca se persiste en la base de datos.
 * </p>
 * 
 * @author Javier Roldán Pomareta
 * @version 1.0
 */
public enum LoanStatus {
    ACTIVE,
    RETURNED,
    OVERDUE
}