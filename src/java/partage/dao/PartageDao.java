/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package partage.dao;

import entite.Partage;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * Classe DAO pour l'entité Partage.
 * 
 * @author quangminhnguyen
 */
public class PartageDao extends HibernateDaoSupport implements IPartageDao {

    @Override
    public void createPartage(Partage partage) {
        this.getHibernateTemplate().save(partage);
    }
    
}
