public class UsersArrayList implements UsersList {
    private User[] dataUser = new User[10];
    private int countUser = 0;

    @Override
    public void addUser(User newUser) {     // способ гибкого массива
        if (countUser >= dataUser.length) {
            User[] temp = new User[dataUser.length * 2];
            for (int i = 0; i < dataUser.length; i++) {
                temp[i] = dataUser[i];
            }
            dataUser = temp;
        }
        dataUser[countUser++] = newUser;
    }

    @Override
    public User findUserID(int idUser) {
        for (int i = 0; i < countUser; i++) {
            if (idUser == dataUser[i].getID()) {
                System.out.print("\nНайден по ID");
                return dataUser[i];
            }
        }
        throw new UserNotFoundException();
    }

    @Override
    public User findUsertIndex(int indexUser) {
        if (indexUser > countUser) {
            throw new UserNotFoundException();
        }
        System.out.print("\nНайден по индексу");
        return dataUser[indexUser];
    }

    @Override
    public int getAmountUser() {
        System.out.print("\nКоличество пользователей = ");
        return countUser;
    }


}
