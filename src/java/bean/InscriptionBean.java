/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import agenda.metier.IAgendaMetier;
import amis.metier.IAmisMetier;
import entite.Agenda;
import entite.User;
import enums.AgendaTypeEnum;
import enums.AmisEnum;
import java.io.Serializable;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import partage.metier.IPartageMetier;
import user.metier.IUserMetier;

/**
 * Bean pour l'inscription.
 *
 * @author quangminhnguyen
 */
@ManagedBean
@RequestScoped
public class InscriptionBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private String email;
    private String password;
    private String passwordConfirm;
    private String nom;
    private String prenom;
    private String sexe;
    private Date dateNaissance;

    private static final String PAGE_CONNEXION = "index";

    public String inscription(ActionEvent actionEvent) {
        System.out.println(nom);
        System.out.println(prenom);
        System.out.println(sexe);
        System.out.println(dateNaissance);
        System.out.println(email);
        System.out.println(password);
        System.out.println(passwordConfirm);

        ListableBeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("applicationContext.xml"));
        IUserMetier userMetier = (IUserMetier) beanFactory.getBean("userMetier");
        IAgendaMetier agendaMetier = (IAgendaMetier) beanFactory.getBean("agendaMetier");
        IPartageMetier partageMetier = (IPartageMetier) beanFactory.getBean("partageMetier");
        IAmisMetier amisMetier = (IAmisMetier) beanFactory.getBean("amisMetier");

        User user = new User();
        user.setNom(this.nom);
        user.setPrenom(this.prenom);
        user.setSexe(this.sexe);
        user.setDateNaissance(this.dateNaissance);
        user.setEmail(this.email);
        user.setPwd(this.password);

        userMetier.createUser(user);
        Agenda agendaPrive = agendaMetier.createAgenda(user, AgendaTypeEnum.PRI);
        Agenda agendaPartage = agendaMetier.createAgenda(user, AgendaTypeEnum.PAR);
        
        User userAdmin = userMetier.findAdmin();
        Agenda agendaPartageAdmin = agendaMetier.findAgenda(userAdmin, AgendaTypeEnum.PAR);
        
        partageMetier.createPartage(user, agendaPartageAdmin);
        partageMetier.createPartage(userAdmin, agendaPartage);

        amisMetier.createAmis(user, userAdmin, AmisEnum.ACC);
        
        this.initParams();

        return PAGE_CONNEXION;
    }

     
     
    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    public void initParams() {
        this.setNom(null);
        this.setPrenom(null);
        this.setSexe(null);
        this.setDateNaissance(null);
        this.setEmail(null);
        this.setPassword(null);
        this.setPasswordConfirm(null);
        this.addMessage("Inscription réussie !!");
    }

    public InscriptionBean() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

}
