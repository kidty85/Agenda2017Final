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
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * Classe DAO pour l'entité Agenda.
 *
 * @author quangminhnguyen
 */
public class AgendaDao extends HibernateDaoSupport implements IAgendaDao {

    @Override
    public Agenda createAgenda(Agenda agenda) {
        this.getHibernateTemplate().save(agenda);
        return agenda;
    }

    @Override
    public Agenda findAgenda(User user, AgendaTypeEnum type) {
        String query = "FROM entite.Agenda a WHERE a.user=? AND a.type=?";
        return (Agenda) this.getHibernateTemplate().find(query, user, type.name()).get(0);
    }
}
