/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user.dao;

import entite.User;
import java.util.List;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * Classe DAO pour l'entité User.
 *
 * @author quangminhnguyen
 */
public class UserDao extends HibernateDaoSupport implements IUserDao {

    @Override
    public void createUser(User user) {
        this.getHibernateTemplate().save(user);
    }

    @Override
    public boolean verifyUser(String email, String password) {
        String query = "FROM entite.User u WHERE u.email=? AND u.pwd=?";
        return !this.getHibernateTemplate().find(query, email, password).isEmpty();
    }

    @Override
    public User findUser(String email) {
        String query = "FROM entite.User u WHERE u.email=?";
        return (User) this.getHibernateTemplate().find(query, email).get(0);
    }

    @Override
    public boolean verifyEmail(String email) {
        String query = "FROM entite.User u WHERE u.email=?";
        return this.getHibernateTemplate().find(query, email).isEmpty();
    }

    @Override
    public List<Object> findAllUsersButCurrent(User user) {
        String query = "FROM entite.User u WHERE u.id !=?";
        return this.getHibernateTemplate().find(query, user.getIdUser());
    }

    @Override
    public User findUserById(Integer idUser) {
        String query = "FROM entite.User u WHERE u.idUser=?";
        return (User) this.getHibernateTemplate().find(query, idUser).get(0);
    }
    
    @Override
    public User findAdmin(){
        String query = "FROM entite.User u WHERE u.isAdmin=true";
        return (User) this.getHibernateTemplate().find(query).get(0);
    }
}
