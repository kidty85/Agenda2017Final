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
 * Amis generated by hbm2java
 */
@Entity
@Table(name="amis"
    ,catalog="agenda2017"
)
public class Amis  implements java.io.Serializable {


     private Integer idAmis;
     private User userByIdUser1;
     private User userByIdUser2;
     private String statut;

    public Amis() {
    }

    public Amis(User userByIdUser1, User userByIdUser2, String statut) {
       this.userByIdUser1 = userByIdUser1;
       this.userByIdUser2 = userByIdUser2;
       this.statut = statut;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="id_amis", unique=true, nullable=false)
    public Integer getIdAmis() {
        return this.idAmis;
    }
    
    public void setIdAmis(Integer idAmis) {
        this.idAmis = idAmis;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_user1", nullable=false)
    public User getUserByIdUser1() {
        return this.userByIdUser1;
    }
    
    public void setUserByIdUser1(User userByIdUser1) {
        this.userByIdUser1 = userByIdUser1;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_user2", nullable=false)
    public User getUserByIdUser2() {
        return this.userByIdUser2;
    }
    
    public void setUserByIdUser2(User userByIdUser2) {
        this.userByIdUser2 = userByIdUser2;
    }

    
    @Column(name="statut", nullable=false, length=4)
    public String getStatut() {
        return this.statut;
    }
    
    public void setStatut(String statut) {
        this.statut = statut;
    }




}

