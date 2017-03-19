/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validator;

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
 * Classe de validation pour les données saisies en connexion.
 * 
 * @author quangminhnguyen
 */
@FacesValidator("ConnexionValidator")
public class ConnexionValidator implements Validator{

    private String email;
    private String password;
    
    @Override
    public void validate(FacesContext fc, UIComponent uic, Object o) throws ValidatorException {
        ListableBeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("applicationContext.xml"));
        IUserMetier userMetier = (IUserMetier) beanFactory.getBean("userMetier");
        
        this.email = o.toString();   
        this.password = ((UIInput) uic.getAttributes().get("cnxPwd")).getSubmittedValue().toString();

        if (!this.email.isEmpty() && !this.password.isEmpty() && 
                !userMetier.verifyUser(this.email, this.password)) {
            FacesMessage msg = new FacesMessage("Probleme", "Membre inconnu");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
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
    
}