/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user.metier;

import entite.User;
import java.util.List;

/**
 * Interface métier pour l'entité User.
 *
 * @author quangminhnguyen
 */
public interface IUserMetier {

    /**
     * Créé un utilisateur.
     *
     * @param user
     */
    void createUser(User user);

    /**
     * Renvoie true si les paramètres identifient un utilisateur.
     *
     * @param email
     * @param password
     * @return
     */
    boolean verifyUser(String email, String password);

    /**
     * Renvoie l'utilisateur correspondant.
     *
     * @param email
     * @return
     */
    User findUser(String email);

    /**
     * Renvoie true si un email existe déjà.
     *
     * @param email
     * @return
     */
    boolean verifyEmail(String email);

    /**
     * Renvoie la liste de tous les utilisateurs sauf l'actuel.
     *
     * @param user
     * @return
     */
    List<User> findAllUsersButCurrent(User user);

    /**
     * Renvoie l'utilisateur correspondant.
     *
     * @param idUser
     * @return
     */
    User findUserById(Integer idUser);

    /**
     * Renvoie l'utilisateur administrateur.
     *
     * @return
     */
    User findAdmin();
}
