package entite;
// Generated 18 mars 2017 23:47:54 by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/**
 * User generated by hbm2java
 */
@Entity
@Table(name="user"
    ,catalog="agenda2017"
    , uniqueConstraints = @UniqueConstraint(columnNames="email") 
)
public class User  implements java.io.Serializable {


     private Integer idUser;
     private String nom;
     private String prenom;
     private Date dateNaissance;
     private String sexe;
     private String email;
     private String pwd;
     private boolean isAdmin;
     private Set<Partage> partages = new HashSet<Partage>(0);
     private Set<Amis> amisesForIdUser1 = new HashSet<Amis>(0);
     private Set<Amis> amisesForIdUser2 = new HashSet<Amis>(0);
     private Set<Agenda> agendas = new HashSet<Agenda>(0);
     private Set<LienUserGroupe> lienUserGroupes = new HashSet<LienUserGroupe>(0);
     private Set<Groupe> groupes = new HashSet<Groupe>(0);

    public User() {
    }

	
    public User(String nom, String prenom, Date dateNaissance, String sexe, String email, String pwd, boolean isAdmin) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.sexe = sexe;
        this.email = email;
        this.pwd = pwd;
        this.isAdmin = isAdmin;
    }
    public User(String nom, String prenom, Date dateNaissance, String sexe, String email, String pwd, boolean isAdmin, Set<Partage> partages, Set<Amis> amisesForIdUser1, Set<Amis> amisesForIdUser2, Set<Agenda> agendas, Set<LienUserGroupe> lienUserGroupes, Set<Groupe> groupes) {
       this.nom = nom;
       this.prenom = prenom;
       this.dateNaissance = dateNaissance;
       this.sexe = sexe;
       this.email = email;
       this.pwd = pwd;
       this.isAdmin = isAdmin;
       this.partages = partages;
       this.amisesForIdUser1 = amisesForIdUser1;
       this.amisesForIdUser2 = amisesForIdUser2;
       this.agendas = agendas;
       this.lienUserGroupes = lienUserGroupes;
       this.groupes = groupes;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="id_user", unique=true, nullable=false)
    public Integer getIdUser() {
        return this.idUser;
    }
    
    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    
    @Column(name="nom", nullable=false, length=30)
    public String getNom() {
        return this.nom;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }

    
    @Column(name="prenom", nullable=false, length=30)
    public String getPrenom() {
        return this.prenom;
    }
    
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="date_naissance", nullable=false, length=10)
    public Date getDateNaissance() {
        return this.dateNaissance;
    }
    
    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    
    @Column(name="sexe", nullable=false, length=2)
    public String getSexe() {
        return this.sexe;
    }
    
    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    
    @Column(name="email", unique=true, nullable=false, length=50)
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    
    @Column(name="pwd", nullable=false, length=50)
    public String getPwd() {
        return this.pwd;
    }
    
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    
    @Column(name="is_admin", nullable=false)
    public boolean isIsAdmin() {
        return this.isAdmin;
    }
    
    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="user")
    public Set<Partage> getPartages() {
        return this.partages;
    }
    
    public void setPartages(Set<Partage> partages) {
        this.partages = partages;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="userByIdUser1")
    public Set<Amis> getAmisesForIdUser1() {
        return this.amisesForIdUser1;
    }
    
    public void setAmisesForIdUser1(Set<Amis> amisesForIdUser1) {
        this.amisesForIdUser1 = amisesForIdUser1;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="userByIdUser2")
    public Set<Amis> getAmisesForIdUser2() {
        return this.amisesForIdUser2;
    }
    
    public void setAmisesForIdUser2(Set<Amis> amisesForIdUser2) {
        this.amisesForIdUser2 = amisesForIdUser2;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="user")
    public Set<Agenda> getAgendas() {
        return this.agendas;
    }
    
    public void setAgendas(Set<Agenda> agendas) {
        this.agendas = agendas;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="user")
    public Set<LienUserGroupe> getLienUserGroupes() {
        return this.lienUserGroupes;
    }
    
    public void setLienUserGroupes(Set<LienUserGroupe> lienUserGroupes) {
        this.lienUserGroupes = lienUserGroupes;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="user")
    public Set<Groupe> getGroupes() {
        return this.groupes;
    }
    
    public void setGroupes(Set<Groupe> groupes) {
        this.groupes = groupes;
    }




}


