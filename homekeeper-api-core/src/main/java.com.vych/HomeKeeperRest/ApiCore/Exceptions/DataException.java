package HomeKeeperRest.ApiCore.Exceptions;

/**
 * Исключение при работе с данными. Например, если не было найдено искомого результата при поиске
 */
public class DataException extends Exception {
    public DataException(String errorMessage) {
        super(errorMessage);
    }
}
