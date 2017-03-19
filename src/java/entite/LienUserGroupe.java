package entite;
// Generated 18 mars 2017 23:47:54 by Hibernate Tools 4.3.1


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * LienUserGroupe generated by hbm2java
 */
@Entity
@Table(name="lien_user_groupe"
    ,catalog="agenda2017"
)
public class LienUserGroupe  implements java.io.Serializable {


     private Integer idLien;
     private Groupe groupe;
     private User user;

    public LienUserGroupe() {
    }

    public LienUserGroupe(Groupe groupe, User user) {
       this.groupe = groupe;
       this.user = user;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="id_lien", unique=true, nullable=false)
    public Integer getIdLien() {
        return this.idLien;
    }
    
    public void setIdLien(Integer idLien) {
        this.idLien = idLien;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_groupe", nullable=false)
    public Groupe getGroupe() {
        return this.groupe;
    }
    
    public void setGroupe(Groupe groupe) {
        this.groupe = groupe;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_user", nullable=false)
    public User getUser() {
        return this.user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }




}

