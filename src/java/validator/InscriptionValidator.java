/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validator;

import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import user.metier.IUserMetier;

/**
 * Classe de validation pour les données saisies en inscription.
 *
 * @author quangminhnguyen
 */
@FacesValidator("InscriptionValidator")
public class InscriptionValidator implements Validator {

    private String nom;
    private String prenom;
    private String sexe;
    private Date dateNaissance;
    private String email;
    private String password;
    private String passwordConfirm;

    @Override
    public void validate(FacesContext fc, UIComponent uic, Object o) throws ValidatorException {
        ListableBeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("applicationContext.xml"));
        IUserMetier userMetier = (IUserMetier) beanFactory.getBean("userMetier");

        this.email = o.toString();
        this.nom = ((UIInput) uic.getAttributes().get("inscNom")).getSubmittedValue().toString();
        this.prenom = ((UIInput) uic.getAttributes().get("inscPrenom")).getSubmittedValue().toString();
        this.sexe = ((UIInput) uic.getAttributes().get("inscSexe")).getSubmittedValue().toString();
        this.password = ((UIInput) uic.getAttributes().get("inscMdp")).getSubmittedValue().toString();

        if (this.email == null || this.email.isEmpty() || !userMetier.verifyEmail(this.email)) {
            FacesMessage msg = new FacesMessage("Erreur", "Email existant");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        } else if (this.nom.isEmpty() || this.prenom.isEmpty() || this.sexe.isEmpty() || this.password.isEmpty()) {
            FacesMessage msg = new FacesMessage("Erreur", "Champ(s) manquant(s)");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        } else if (this.password != null && !this.password.isEmpty() && this.passwordConfirm != null && !this.passwordConfirm.isEmpty()
                && !this.password.equals(this.passwordConfirm)) {
             FacesMessage msg = new FacesMessage("Erreur", "Mots de passe différents");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        } else {
            FacesMessage msg = new FacesMessage("Enregistrement effectué", "OK");
            msg.setSeverity(FacesMessage.SEVERITY_INFO);
        }
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
}
