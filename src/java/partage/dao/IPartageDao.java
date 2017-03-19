/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package partage.dao;

import entite.Partage;

/**
 * Interface DAO pour l'entité Partage.
 *
 * @author quangminhnguyen
 */
public interface IPartageDao {

    /**
     * Créé un partage.
     *
     * @param partage
     */
    void createPartage(Partage partage);
}
