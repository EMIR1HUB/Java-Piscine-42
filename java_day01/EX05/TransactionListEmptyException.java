public class TransactionListEmptyException extends RuntimeException {
    @Override
    public String toString() {
        return "Список транзакций пуст";
    }
}
