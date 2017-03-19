/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user.metier;

import entite.User;
import java.util.ArrayList;
import java.util.List;
import user.dao.IUserDao;

/**
 * Classe métier pour l'entité User.
 *
 * @author quangminhnguyen
 */
public class UserMetier implements IUserMetier {

    IUserDao userDao;

    public UserMetier() {
    }

    public void setUserDao(IUserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void createUser(User user) {
        this.userDao.createUser(user);
    }

    @Override
    public boolean verifyUser(String email, String password) {
        return this.userDao.verifyUser(email, password);
    }

    @Override
    public User findUser(String email) {
        return this.userDao.findUser(email);
    }

    @Override
    public boolean verifyEmail(String email) {
        return this.userDao.verifyEmail(email);
    }

    @Override
    public List<User> findAllUsersButCurrent(User user) {
        List<Object> listObject = this.userDao.findAllUsersButCurrent(user);
        List<User> listUser = new ArrayList<>();
        for (Object object : listObject) {
            User aUser = (User) object;
            listUser.add(aUser);
        }
        return listUser;
    }

    @Override
    public User findUserById(Integer idUser) {
        return this.userDao.findUserById(idUser);
    }

    @Override
    public User findAdmin() {
        return this.userDao.findAdmin();
    }

}
