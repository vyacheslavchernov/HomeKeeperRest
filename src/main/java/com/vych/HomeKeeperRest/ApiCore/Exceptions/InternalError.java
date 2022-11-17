package com.vych.HomeKeeperRest.ApiCore.Exceptions;

/**
 * Исключение пробрасываемое в ответы API при возникновении внутренних ошибок приложения
 */
public class InternalError extends Exception {
    public InternalError(String errorMessage) {
        super(errorMessage);
    }
}
