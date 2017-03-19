/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evenement.metier;

import entite.Agenda;
import entite.Evenement;
import evenement.dao.IEvenementDao;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe métier pour l'entité Evenement.
 *
 * @author quangminhnguyen
 */
public class EvenementMetier implements IEvenementMetier {

    IEvenementDao evenementDao;

    public EvenementMetier() {
    }

    public void setEvenementDao(IEvenementDao evenementDao) {
        this.evenementDao = evenementDao;
    }

    @Override
    public void createEvenement(Evenement evenement) {
        this.evenementDao.createEvenement(evenement);
    }

    @Override
    public void updateEvenement(Evenement evenement) {
        this.evenementDao.updateEvenement(evenement);
    }

    @Override
    public List<Evenement> findEventByAgenda(Agenda agenda) {
        List<Object> listObject = this.evenementDao.findEventByAgenda(agenda);
        List<Evenement> listEvenement = new ArrayList<>();
        for (Object object : listObject) {
            Evenement evenement = (Evenement) object;
            listEvenement.add(evenement);
        }
        return listEvenement;
    }
    
    @Override
    public void deleteEvenement(Evenement evenement){
        this.evenementDao.deleteEvenement(evenement);
    }

}
