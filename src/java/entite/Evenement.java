package entite;
// Generated 18 mars 2017 23:47:54 by Hibernate Tools 4.3.1


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Evenement generated by hbm2java
 */
@Entity
@Table(name="evenement"
    ,catalog="agenda2017"
)
public class Evenement  implements java.io.Serializable {


     private Integer idEvenement;
     private Agenda agenda;
     private String titre;
     private String lieu;
     private Date dateCreation;
     private Date dateMaj;
     private Date dateDebut;
     private Date dateFin;
     private String description;

    public Evenement() {
    }

	
    public Evenement(Agenda agenda, String titre, String lieu, Date dateCreation, Date dateMaj, Date dateDebut, Date dateFin) {
        this.agenda = agenda;
        this.titre = titre;
        this.lieu = lieu;
        this.dateCreation = dateCreation;
        this.dateMaj = dateMaj;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }
    public Evenement(Agenda agenda, String titre, String lieu, Date dateCreation, Date dateMaj, Date dateDebut, Date dateFin, String description) {
       this.agenda = agenda;
       this.titre = titre;
       this.lieu = lieu;
       this.dateCreation = dateCreation;
       this.dateMaj = dateMaj;
       this.dateDebut = dateDebut;
       this.dateFin = dateFin;
       this.description = description;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="id_evenement", unique=true, nullable=false)
    public Integer getIdEvenement() {
        return this.idEvenement;
    }
    
    public void setIdEvenement(Integer idEvenement) {
        this.idEvenement = idEvenement;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_agenda", nullable=false)
    public Agenda getAgenda() {
        return this.agenda;
    }
    
    public void setAgenda(Agenda agenda) {
        this.agenda = agenda;
    }

    
    @Column(name="titre", nullable=false, length=50)
    public String getTitre() {
        return this.titre;
    }
    
    public void setTitre(String titre) {
        this.titre = titre;
    }

    
    @Column(name="lieu", nullable=false, length=50)
    public String getLieu() {
        return this.lieu;
    }
    
    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="date_creation", nullable=false, length=19)
    public Date getDateCreation() {
        return this.dateCreation;
    }
    
    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="date_maj", nullable=false, length=19)
    public Date getDateMaj() {
        return this.dateMaj;
    }
    
    public void setDateMaj(Date dateMaj) {
        this.dateMaj = dateMaj;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="date_debut", nullable=false, length=19)
    public Date getDateDebut() {
        return this.dateDebut;
    }
    
    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="date_fin", nullable=false, length=19)
    public Date getDateFin() {
        return this.dateFin;
    }
    
    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    
    @Column(name="description")
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }




}

