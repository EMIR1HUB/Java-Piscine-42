public class TransactionNotFoundException extends RuntimeException {
    public String toString(){
        return "Транкзация с текущим идентификатором не существует";
    }
}

