package services;

import dao.OrmManager;
import models.User;

public class ManagerService {
    private OrmManager ormManager = new OrmManager();

    public ManagerService(){}

    public User findUser(Long id){
        return ormManager.findById(id);
    }

    public void saveUser(User user){
        ormManager.save(user);
    }

    public void updateUser(User user){
        ormManager.update(user);
    }
}
