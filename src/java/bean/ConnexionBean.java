/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import entite.User;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import user.metier.IUserMetier;

/**
 * Bean pour la connexion.
 * 
 * @author quangminhnguyen
 */
@ManagedBean
@SessionScoped
public class ConnexionBean implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private String email;
    private String password;
    private User user;
    
    private static final String PAGE_ACCUEIL = "home";

    public String connexion(){
        // On récupère l'utilisateur courant
        this.findUser();
        return PAGE_ACCUEIL;
    }
    
    public ConnexionBean() {
    }
    
    public void findUser(){
        ListableBeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("applicationContext.xml"));
        IUserMetier userMetier = (IUserMetier) beanFactory.getBean("userMetier");
        this.user = userMetier.findUser(this.email);
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
}
