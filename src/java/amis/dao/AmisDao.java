/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amis.dao;

import entite.Amis;
import entite.User;
import enums.AmisEnum;
import java.util.List;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * Classe DAO pour l'entité Amis.
 *
 * @author quangminhnguyen
 */
public class AmisDao extends HibernateDaoSupport implements IAmisDao {

    @Override
    public void createAmis(Amis amis) {
        this.getHibernateTemplate().save(amis);
    }

    @Override
    public void deleteAmis(Amis amis) {
        this.getHibernateTemplate().delete(amis);
    }

    @Override
    public List<Object> findSentInvitByUser(User user) {
        String query = "FROM entite.Amis a WHERE a.userByIdUser1=? AND a.statut=?";
        return this.getHibernateTemplate().find(query, user, AmisEnum.ENV.name());
    }

    @Override
    public List<Object> findReceivedInvitByUser(User user) {
        String query = "FROM entite.Amis a WHERE a.userByIdUser2=? AND a.statut=?";
        return this.getHibernateTemplate().find(query, user, AmisEnum.ENV.name());
    }

    @Override
    public List<Object> findAcceptedInvitByUser(User user) {
        String query = "FROM entite.Amis a WHERE (a.userByIdUser1=? OR a.userByIdUser2=?) AND a.statut=?";
        return this.getHibernateTemplate().find(query, user, user, AmisEnum.ACC.name());
    }

    @Override
    public Amis findAmisByIds(User user1, User user2) {
        String query = "FROM entite.Amis a WHERE a.userByIdUser1=? AND a.userByIdUser2=?";
        return (Amis) this.getHibernateTemplate().find(query, user1, user2).get(0);
    }
    
    @Override
    public void updateAmis(Amis amis){
        this.getHibernateTemplate().merge(amis);
    }

}
