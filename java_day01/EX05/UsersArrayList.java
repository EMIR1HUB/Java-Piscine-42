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
                return dataUser[i];
            }
        }
        throw new UserNotFoundException();
    }

    @Override
    public User findUserIndex(int indexUser) {
        if (indexUser > countUser) {
            throw new UserNotFoundException();
        }
        return dataUser[indexUser];
    }

    @Override
    public int getAmountUser() {
        return countUser;
    }


}
