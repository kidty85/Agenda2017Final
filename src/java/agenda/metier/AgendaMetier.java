/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agenda.metier;

import agenda.dao.IAgendaDao;
import entite.Agenda;
import entite.User;
import enums.AgendaTypeEnum;

/**
 * Classe métier pour l'entité Agenda.
 * 
 * @author quangminhnguyen
 */
public class AgendaMetier implements IAgendaMetier {
    IAgendaDao agendaDao;

    public AgendaMetier() {
    }

    public void setAgendaDao(IAgendaDao agendaDao) {
        this.agendaDao = agendaDao;
    }

    @Override
    public Agenda createAgenda(User user, AgendaTypeEnum type) {
        Agenda agenda = new Agenda();
        agenda.setUser(user);
        agenda.setType(type.name());
        return this.agendaDao.createAgenda(agenda);
    }
    
    @Override
    public Agenda findAgenda(User user, AgendaTypeEnum type) {
        return this.agendaDao.findAgenda(user, type);
    }    
    
}
