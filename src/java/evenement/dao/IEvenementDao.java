/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evenement.dao;

import entite.Agenda;
import entite.Evenement;
import java.util.List;

/**
 * Interface DAO pour l'entité Evenement.
 * 
 * @author quangminhnguyen
 */
public interface IEvenementDao {
    
    /**
     * Créé un événement.
     * 
     * @param evenement 
     */
    void createEvenement(Evenement evenement);
    
    /**
     * Met à jour un événement.
     * 
     * @param evenement 
     */
    void updateEvenement(Evenement evenement);
    
    /**
     * Renvoie les événements d'un agenda.
     * 
     * @param agenda
     * @return 
     */
    List<Object> findEventByAgenda(Agenda agenda);
    
    /**
     * Supprime un événement.
     * 
     * @param evenement 
     */
    void deleteEvenement(Evenement evenement);
}
