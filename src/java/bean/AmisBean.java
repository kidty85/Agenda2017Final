/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import agenda.metier.IAgendaMetier;
import amis.metier.IAmisMetier;
import entite.Agenda;
import entite.Amis;
import entite.User;
import enums.AgendaTypeEnum;
import enums.AmisEnum;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import partage.metier.IPartageMetier;
import user.metier.IUserMetier;

/**
 * Bean pour la gestion des amis.
 *
 * @author quangminhnguyen
 */
@ManagedBean
@ApplicationScoped
public class AmisBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private User currentUser;
    private Integer idUserGestion;
    private String statut;
    private Map<Integer, User> mapUsers = new HashMap<>();
    private Map<Integer, User> mapUsersSent = new HashMap<>();
    private Map<Integer, User> mapUsersReceived = new HashMap<>();
    private Map<Integer, User> mapUsersAccepted = new HashMap<>();
    private List<User> listUsers;
    private List<User> listUsersSent;
    private List<User> listUsersReceived;
    private List<User> listUsersAccepted;

    /**
     * Méthode de récupération des utilisateurs depuis la base une fois la
     * connexion réussie.
     */
    public void init() {
        ListableBeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("applicationContext.xml"));
        IUserMetier userMetier = (IUserMetier) beanFactory.getBean("userMetier");
        IAmisMetier amisMetier = (IAmisMetier) beanFactory.getBean("amisMetier");

        this.listUsers = userMetier.findAllUsersButCurrent(this.currentUser);
        this.mapUsersSent = amisMetier.findSentInvitByUser(this.currentUser);
        this.mapUsersReceived = amisMetier.findReceivedInvitByUser(this.currentUser);
        this.mapUsersAccepted = amisMetier.findAcceptedInvitByUser(this.currentUser);

        for (User user : this.listUsers) {
            if (user.getIdUser() != this.currentUser.getIdUser()) {
                if (this.mapUsersSent.containsKey(user.getIdUser())) {
                    this.mapUsersSent.put(user.getIdUser(), user);
                } else if (this.mapUsersReceived.containsKey(user.getIdUser())) {
                    this.mapUsersReceived.put(user.getIdUser(), user);
                } else if (this.mapUsersAccepted.containsKey(user.getIdUser())) {
                    this.mapUsersAccepted.put(user.getIdUser(), user);
                } else {
                    this.mapUsers.put(user.getIdUser(), user);
                }
            }
        }

        this.setListUsers(this.copyFromMapToList(this.mapUsers));
        this.setListUsersSent(this.copyFromMapToList(this.mapUsersSent));
        this.setListUsersReceived(this.copyFromMapToList(this.mapUsersReceived));
        this.setListUsersAccepted(this.copyFromMapToList(this.mapUsersAccepted));

        System.out.println("***MAP***");
        System.out.println(this.mapUsers.size());
        System.out.println(this.mapUsersAccepted.size());
        System.out.println(this.mapUsersReceived.size());
        System.out.println(this.mapUsersSent.size());

        System.out.println("***LIST***");
        System.out.println(this.listUsers.size());
        System.out.println(this.listUsersAccepted.size());
        System.out.println(this.listUsersReceived.size());
        System.out.println(this.listUsersSent.size());

        for (User uu : this.listUsersAccepted) {
            System.out.println(uu.getEmail());
        }
    }

    /**
     * Copie les utilisateurs de la map vers la liste.
     *
     * @param map
     * @param list
     */
    public void copyFromMapToList(Map<Integer, User> map, List<User> list) {
        list = new ArrayList<>();
        for (Entry<Integer, User> entry : map.entrySet()) {
            list.add(entry.getValue());
        }
    }

    public List<User> copyFromMapToList(Map<Integer, User> map) {
        List<User> list = new ArrayList<>();
        for (Entry<Integer, User> entry : map.entrySet()) {
            list.add(entry.getValue());
        }
        return list;
    }

    public String gestionDemande() {
        ListableBeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("applicationContext.xml"));
        IUserMetier userMetier = (IUserMetier) beanFactory.getBean("userMetier");
        IAgendaMetier agendaMetier = (IAgendaMetier) beanFactory.getBean("agendaMetier");
        IPartageMetier partageMetier = (IPartageMetier) beanFactory.getBean("partageMetier");
        IAmisMetier amisMetier = (IAmisMetier) beanFactory.getBean("amisMetier");

        String message = "";

        if (this.statut.equals(AmisEnum.ACC.name())) {
            // Creation amis
            User userGestion = this.mapUsersReceived.get(this.idUserGestion);
            Amis amis = amisMetier.findAmisByIds(userGestion, this.currentUser);
            amisMetier.updateAmis(amis, AmisEnum.ACC);
            //amisMetier.createAmis(this.currentUser, userGestion, AmisEnum.ACC);
            // Partage agenda
            Iterator<Agenda> iteratorAgenda = this.currentUser.getAgendas().iterator();
            while (iteratorAgenda.hasNext()) {
                Agenda agendaPartage = iteratorAgenda.next();
                if (agendaPartage.getType().equals(AgendaTypeEnum.PAR.name())) {
                    partageMetier.createPartage(userGestion, agendaPartage);
                }
            }
            iteratorAgenda = userGestion.getAgendas().iterator();
            while (iteratorAgenda.hasNext()) {
                Agenda agendaPartage = iteratorAgenda.next();
                if (agendaPartage.getType().equals(AgendaTypeEnum.PAR.name())) {
                    partageMetier.createPartage(this.currentUser, agendaPartage);
                }
            }
            // Mise à jour des listes et maps
            this.mapUsersReceived.remove(this.idUserGestion);
            this.mapUsersAccepted.put(this.idUserGestion, userGestion);
            this.setListUsersReceived(this.copyFromMapToList(this.mapUsersReceived));
            this.setListUsersAccepted(this.copyFromMapToList(this.mapUsersAccepted));

            message = "Invitation acceptée";
        } else if (this.statut.equals(AmisEnum.ENV.name())) {
            User userGestion = this.mapUsers.get(this.idUserGestion);
            amisMetier.createAmis(this.currentUser, userGestion, AmisEnum.ENV);
            // Mise à jour des listes et maps
            this.mapUsers.remove(this.idUserGestion);
            this.mapUsersSent.put(this.idUserGestion, userGestion);
            this.setListUsers(this.copyFromMapToList(this.mapUsers));
            this.setListUsersSent(this.copyFromMapToList(this.mapUsersSent));

            message = "Invitation envoyée";
        } else if (this.statut.equals(AmisEnum.REF.name())) {
            User userGestion = this.mapUsersReceived.get(this.idUserGestion);
            Amis amis = amisMetier.findAmisByIds(userGestion, this.currentUser);
            amisMetier.deleteAmis(amis);
            // Mise à jour des listes et maps
            this.mapUsersReceived.remove(this.idUserGestion);
            this.mapUsers.put(this.idUserGestion, userGestion);
            this.setListUsersReceived(this.copyFromMapToList(this.mapUsersReceived));
            this.setListUsers(this.copyFromMapToList(this.mapUsers));

            message = "Invitation refusée";
        } else {

        }
        return message;
    }

    public AmisBean() {
    }

    public User getCurrentUser() {
        return this.currentUser;
    }

    public void setCurrentUser(User user) {
        this.currentUser = user;
    }

    public Integer getIdUserGestion() {
        return idUserGestion;
    }

    public void setIdUserGestion(Integer idUserGestion) {
        this.idUserGestion = idUserGestion;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public Map<Integer, User> getMapUsers() {
        return this.mapUsers;
    }

    public void setMapUsers(Map<Integer, User> mapUsers) {
        this.mapUsers = mapUsers;
    }

    public Map<Integer, User> getMapUsersSent() {
        return this.mapUsersSent;
    }

    public void setMapUsersSent(Map<Integer, User> mapUsersSent) {
        this.mapUsersSent = mapUsersSent;
    }

    public Map<Integer, User> getMapUsersReceived() {
        return this.mapUsersReceived;
    }

    public void setMapUsersReceived(Map<Integer, User> mapUsersReceived) {
        this.mapUsersReceived = mapUsersReceived;
    }

    public Map<Integer, User> getMapUsersAccepted() {
        return mapUsersAccepted;
    }

    public void setMapUsersAccepted(Map<Integer, User> mapUsersAccepted) {
        this.mapUsersAccepted = mapUsersAccepted;
    }

    public List<User> getListUsers() {
        System.out.println(this.listUsers.size());
        return this.listUsers;
    }

    public void setListUsers(List<User> listUsers) {
        this.listUsers = listUsers;
    }

    public List<User> getListUsersSent() {
        return this.listUsersSent;
    }

    public void setListUsersSent(List<User> listUsersSent) {
        this.listUsersSent = listUsersSent;
    }

    public List<User> getListUsersReceived() {
        return this.listUsersReceived;
    }

    public void setListUsersReceived(List<User> listUsersReceived) {
        this.listUsersReceived = listUsersReceived;
    }

    public List<User> getListUsersAccepted() {
        return listUsersAccepted;
    }

    public void setListUsersAccepted(List<User> listUsersAccepted) {
        this.listUsersAccepted = listUsersAccepted;
    }

}
