/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amis.dao;

import entite.Amis;
import entite.User;
import java.util.List;

/**
 * Interface DAO pour l'entité Amis.
 *
 * @author quangminhnguyen
 */
public interface IAmisDao {

    /**
     * Créé un lien ami entre deux utilisateurs.
     *
     * @param amis
     */
    void createAmis(Amis amis);

    /**
     * Supprime un lien ami.
     *
     * @param amis
     */
    void deleteAmis(Amis amis);

    /**
     * Récupère les invitations envoyées par l'utilisateur.
     *
     * @param user
     * @return
     */
    List<Object> findSentInvitByUser(User user);

    /**
     * Récupère les invitations reçues par l'utilisateur.
     *
     * @param user
     * @return
     */
    List<Object> findReceivedInvitByUser(User user);

    /**
     * Récupère les invitations acceptées par l'utilisateur.
     *
     * @param user
     * @return
     */
    List<Object> findAcceptedInvitByUser(User user);
    
    /**
     * Récupère la relation entre deux utilisateurs.
     *
     * @param user1
     * @param user2
     * @return
     */
    Amis findAmisByIds(User user1, User user2);
    
    /**
     * Met à jour la relation entre deux utilisateurs.
     *
     * @param amis
     */
    void updateAmis(Amis amis);
}
