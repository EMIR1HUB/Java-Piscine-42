public class IllegalTransactionException extends RuntimeException {
    public String toString(){
        return "Недостаточно средств для перевода";
    }
}
