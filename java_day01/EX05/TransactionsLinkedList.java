import java.util.UUID;

public class TransactionsLinkedList implements TransactionsList {
    private Integer length = 0;
    private TransactionNode head;

    @Override
    public void addTransaction(Transaction data) {
        TransactionNode newNode = new TransactionNode(data);
        if (head != null) {
            head.setPrevious(newNode);
            newNode.setNext(head);
        }
        head = newNode;
        length++;
    }

    @Override
    public void removeTransaction(UUID id) {
        TransactionNode currentTransaction = head;
        while (currentTransaction != null) {
            if (currentTransaction.getData().getUID().equals(id)) {
                if (currentTransaction.getPrevious() != null) {
                    currentTransaction.getPrevious().setNext(currentTransaction.getNext());
                }
                if (currentTransaction.getNext() != null) {
                    if (currentTransaction.getPrevious() == null) {
                        head = currentTransaction.getNext();
                    }
                    currentTransaction.getNext().setPrevious(currentTransaction.getPrevious());
                }
                length--;
                return;
            }
            currentTransaction = currentTransaction.getNext();
        }
        throw new TransactionNotFoundException();
    }

    @Override
    public Transaction[] toArray() {
        Transaction[] retArray = new Transaction[length];
        TransactionNode currentTransaction = head;

        if(currentTransaction.getData() == null){
            throw new TransactionListEmptyException();
        }
        for (int i = 0; i < length; i++) {
            retArray[i] = currentTransaction.getData();
            currentTransaction = currentTransaction.getNext();
        }
        return retArray;
    }

    public void print() {
        Transaction[] arr = toArray();
        for (Transaction el : arr ) {
            System.out.println(el);
        }
    }
}
