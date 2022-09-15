import java.util.UUID;

public interface TransactionList {
    public void add(Transaction transaction);
    public void remove(UUID id);
    public void transformArray(Transaction[] transactions);
}
