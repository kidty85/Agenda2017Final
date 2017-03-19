/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amis.metier;

import amis.dao.IAmisDao;
import entite.Amis;
import entite.User;
import enums.AmisEnum;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Classe métier pour l'entité Amis.
 *
 * @author quangminhnguyen
 */
public class AmisMetier implements IAmisMetier {

    IAmisDao amisDao;

    public AmisMetier() {
    }

    public void setAmisDao(IAmisDao amisDao) {
        this.amisDao = amisDao;
    }

    @Override
    public void createAmis(User user1, User user2, AmisEnum statut) {
        Amis amis = new Amis();
        amis.setUserByIdUser1(user1);
        amis.setUserByIdUser2(user2);
        amis.setStatut(statut.name());
        this.amisDao.createAmis(amis);
    }

    @Override
    public void deleteAmis(Amis amis) {
        this.amisDao.deleteAmis(amis);
    }

    @Override
    public Map<Integer, User> findSentInvitByUser(User user) {
        List<Object> listObject = this.amisDao.findSentInvitByUser(user);
        Map<Integer, User> listAmis = new HashMap<>();
        for (Object object : listObject) {
            User sentTo = ((Amis) object).getUserByIdUser2();
            listAmis.put(sentTo.getIdUser(), sentTo);
        }
        return listAmis;
    }

    @Override
    public Map<Integer, User> findReceivedInvitByUser(User user) {
        List<Object> listObject = this.amisDao.findReceivedInvitByUser(user);
        Map<Integer, User> listAmis = new HashMap<>();
        for (Object object : listObject) {
            User sentFrom = ((Amis) object).getUserByIdUser1();
            listAmis.put(sentFrom.getIdUser(), sentFrom);
        }
        return listAmis;
    }

    @Override
    public Map<Integer, User> findAcceptedInvitByUser(User user) {
        List<Object> listObject = this.amisDao.findAcceptedInvitByUser(user);
        Map<Integer, User> listAmis = new HashMap<>();
        for (Object object : listObject) {
            User sentFrom = ((Amis) object).getUserByIdUser1();
            User sentTo = ((Amis) object).getUserByIdUser2();
            if (Objects.equals(sentFrom.getIdUser(), user.getIdUser())) {
                listAmis.put(sentTo.getIdUser(), sentTo);
            } else {
                listAmis.put(sentFrom.getIdUser(), sentFrom);
            }
        }
        return listAmis;
    }

    @Override
    public Amis findAmisByIds(User user1, User user2) {
        return this.amisDao.findAmisByIds(user1, user2);
    }

    @Override
    public void updateAmis(Amis amis, AmisEnum statut) {
        amis.setStatut(statut.name());
        this.amisDao.updateAmis(amis);
    }

}
