package com.vych.HomeKeeperRest.ApiCore;

import com.vych.HomeKeeperRest.ApiCore.Payloads.StringPayload;

/**
 * Вспомогательный класс для генерации типовых ответов
 */
public  class ResponseUtil {
    /**
     * @param e Исключение пробрасываемое в ответ.
     *         Для специфичных исключений см. пакет {@link com.vych.HomeKeeperRest.ApiCore.Exceptions}
     * @param exceptionDescribe Описание возникшей проблемы
     * @return Инициализированный объект ответа содержащий ошибку
     */
    public static ApiResponse buildError(Exception e, String exceptionDescribe) {
        return new ApiResponse()
                .setStatus(new Status()
                        .setCode(StatusCode.ERROR)
                        .addError(
                                new Error()
                                        .setDescription(exceptionDescribe)
                                        .setTrace(e.toString())
                        ))
                .setPayload(new StringPayload().setMessage("Не удалось выполнить запрос"));
    }

    /**
     * @return Инициализированный объект ответа содержащий типовое сообщение об успешности выполнения запроса
     */
    public static ApiResponse buildSuccess() {
        return new ApiResponse()
                .setStatus(new Status()
                        .setCode(StatusCode.SUCCESS))
                .setPayload(new StringPayload().setMessage("Запрос успешно выполнен"));
    }
}
