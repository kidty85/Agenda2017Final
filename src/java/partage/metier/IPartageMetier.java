/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package partage.metier;

import entite.Agenda;
import entite.User;

/**
 * Interface métier pour l'entité Partage.
 *
 * @author quangminhnguyen
 */
public interface IPartageMetier {

    /**
     * Créé un partage.
     *
     * @param user
     * @param agenda
     */
    void createPartage(User user, Agenda agenda);
}
