/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package groupe.metier;

import groupe.dao.IGroupeDao;

/**
 * Classe métier pour l'entité Groupe.
 * 
 * @author quangminhnguyen
 */
public class GroupeMetier implements IGroupeMetier {
    IGroupeDao groupeDao;

    public GroupeMetier() {
    }

    public void setGroupeDao(IGroupeDao groupeDao) {
        this.groupeDao = groupeDao;
    }    
    
}
