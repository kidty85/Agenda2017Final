package entite;
// Generated 18 mars 2017 23:47:54 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Groupe generated by hbm2java
 */
@Entity
@Table(name="groupe"
    ,catalog="agenda2017"
)
public class Groupe  implements java.io.Serializable {


     private Integer idGroupe;
     private User user;
     private String nom;
     private Set<LienUserGroupe> lienUserGroupes = new HashSet<LienUserGroupe>(0);

    public Groupe() {
    }

	
    public Groupe(User user, String nom) {
        this.user = user;
        this.nom = nom;
    }
    public Groupe(User user, String nom, Set<LienUserGroupe> lienUserGroupes) {
       this.user = user;
       this.nom = nom;
       this.lienUserGroupes = lienUserGroupes;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="id_groupe", unique=true, nullable=false)
    public Integer getIdGroupe() {
        return this.idGroupe;
    }
    
    public void setIdGroupe(Integer idGroupe) {
        this.idGroupe = idGroupe;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_admin", nullable=false)
    public User getUser() {
        return this.user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }

    
    @Column(name="nom", nullable=false, length=50)
    public String getNom() {
        return this.nom;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="groupe")
    public Set<LienUserGroupe> getLienUserGroupes() {
        return this.lienUserGroupes;
    }
    
    public void setLienUserGroupes(Set<LienUserGroupe> lienUserGroupes) {
        this.lienUserGroupes = lienUserGroupes;
    }




}


