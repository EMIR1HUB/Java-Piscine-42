public class UserNotTransferAmountException extends RuntimeException {
    @Override
    public String toString(){
        return "\nНе хватает средств для отправки денег";
    }
}
