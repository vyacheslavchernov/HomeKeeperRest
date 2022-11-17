package com.vych.HomeKeeperRest.ApiCore.Exceptions;

/**
 * Исключение пробрасываемое в ответы API при возникновении ошибок с входными данными
 */
public class IncorrectData extends Exception {
    public IncorrectData(String errorMessage) {
        super(errorMessage);
    }
}
