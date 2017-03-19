/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amis.metier;

import entite.Amis;
import entite.User;
import enums.AmisEnum;
import java.util.Map;

/**
 * Interface métier pour l'entité Amis.
 *
 * @author quangminhnguyen
 */
public interface IAmisMetier {

    /**
     * Créé un lien ami entre deux utilisateurs.
     *
     * @param user1
     * @param user2
     * @param statut
     */
    void createAmis(User user1, User user2, AmisEnum statut);

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
    Map<Integer, User> findSentInvitByUser(User user);

    /**
     * Récupère les invitations reçues par l'utilisateur.
     *
     * @param user
     * @return
     */
    Map<Integer, User> findReceivedInvitByUser(User user);

    /**
     * Récupère les invitations acceptées par l'utilisateur.
     *
     * @param user
     * @return
     */
    Map<Integer, User> findAcceptedInvitByUser(User user);

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
     * @param statut
     */
    void updateAmis(Amis amis, AmisEnum statut);
}
