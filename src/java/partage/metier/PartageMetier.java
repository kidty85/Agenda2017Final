/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package partage.metier;

import entite.Agenda;
import entite.Partage;
import entite.User;
import partage.dao.IPartageDao;

/**
 * Classe métier pour l'entité Partage.
 *
 * @author quangminhnguyen
 */
public class PartageMetier implements IPartageMetier {

    IPartageDao partageDao;

    public PartageMetier() {
    }

    public void setPartageDao(IPartageDao partageDao) {
        this.partageDao = partageDao;
    }

    @Override
    public void createPartage(User user, Agenda agenda) {
        Partage partage = new Partage();
        partage.setUser(user);
        partage.setAgenda(agenda);
        this.partageDao.createPartage(partage);
    }

}
