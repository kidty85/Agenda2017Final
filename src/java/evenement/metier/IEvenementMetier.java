/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evenement.metier;

import entite.Agenda;
import entite.Evenement;
import java.util.List;

/**
 * Interface métier pour l'entité Evenement.
 * 
 * @author quangminhnguyen
 */
public interface IEvenementMetier {
    
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
    List<Evenement> findEventByAgenda(Agenda agenda);
    
    /**
     * Supprime un événement.
     * 
     * @param evenement 
     */
    void deleteEvenement(Evenement evenement);
}
