/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agenda.metier;

import entite.Agenda;
import entite.User;
import enums.AgendaTypeEnum;

/**
 * Interface métier pour l'entité Agenda.
 *
 * @author quangminhnguyen
 */
public interface IAgendaMetier {

    /**
     * Créé un agenda selon un type.
     *
     * @param user
     * @param type
     * @return 
     */
    Agenda createAgenda(User user, AgendaTypeEnum type);

    /**
     * Renvoie l'agenda de l'utilisateur selon le type.
     *
     * @param user
     * @param type
     * @return
     */
    Agenda findAgenda(User user, AgendaTypeEnum type);
}
