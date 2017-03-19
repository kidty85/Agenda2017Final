/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evenement.dao;

import entite.Agenda;
import entite.Evenement;
import java.util.List;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * Classe DAO pour l'entité Evenement.
 *
 * @author quangminhnguyen
 */
public class EvenementDao extends HibernateDaoSupport implements IEvenementDao {

    @Override
    public void createEvenement(Evenement evenement) {
        this.getHibernateTemplate().save(evenement);
    }

    @Override
    public void updateEvenement(Evenement evenement) {
        this.getHibernateTemplate().merge(evenement);
    }

    @Override
    public List<Object> findEventByAgenda(Agenda agenda) {
        String query = "FROM entite.Evenement e WHERE e.agenda=?";
        return this.getHibernateTemplate().find(query, agenda);
    }
    
    @Override
    public void deleteEvenement(Evenement evenement){
        this.getHibernateTemplate().delete(evenement);
    }

}
