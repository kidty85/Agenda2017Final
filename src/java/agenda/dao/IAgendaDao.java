/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agenda.dao;

import entite.Agenda;
import entite.User;
import enums.AgendaTypeEnum;
import java.util.List;

/**
 * Interface DAO pour l'entité Agenda.
 * 
 * @author quangminhnguyen
 */
public interface IAgendaDao {
    
    /**
     * Créé un agenda selon un type.
     * 
     * @param agenda 
     * @return 
     */
    Agenda createAgenda(Agenda agenda);
    
    /**
     * Renvoie l'agenda de l'utilisateur selon le type.
     * @param user
     * @param type
     * @return 
     */
    Agenda findAgenda(User user, AgendaTypeEnum type);
}
