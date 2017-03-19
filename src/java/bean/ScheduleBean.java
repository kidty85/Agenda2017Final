/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import agenda.metier.IAgendaMetier;
import entite.Agenda;
import entite.Evenement;
import entite.Partage;
import entite.User;
import enums.AgendaTypeEnum;
import evenement.metier.IEvenementMetier;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

/**
 * Bean pour la gestion de l'agenda.
 *
 * @author quangminhnguyen
 */
@ManagedBean
@ApplicationScoped
public class ScheduleBean implements Serializable {
    
    private ScheduleModel eventModel;
    private ScheduleEvent event = new DefaultScheduleEvent();
    
    private Integer idUser;
    private String email;
    private User currentUser;
    private String typeAgenda;
    private Agenda agenda;
    private List<Evenement> listEvenements;
    private Map<String, Agenda> mapAgendas = new HashMap();
    private Map<String, Map<Integer, Evenement>> mapEvenements = new HashMap();
    //private Map<Integer, Evenement> mapEvenementsBis = new HashMap();

    //@PostConstruct
    public void initiate() {
        System.out.println("je suis dans le initiate");
        ListableBeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("applicationContext.xml"));
        IAgendaMetier agendaMetierBean = (IAgendaMetier) beanFactory.getBean("agendaMetier");
        IEvenementMetier evenementMetierBean = (IEvenementMetier) beanFactory.getBean("evenementMetier");
        
        if (Arrays.asList(AgendaTypeEnum.PRI.name(), AgendaTypeEnum.PAR.name()).contains(this.typeAgenda)) {
            this.agenda = agendaMetierBean.findAgenda(currentUser, AgendaTypeEnum.valueOf(this.typeAgenda));
            this.listEvenements = evenementMetierBean.findEventByAgenda(this.agenda);
            this.eventModel = new DefaultScheduleModel();
            
            for (Evenement evenement : this.listEvenements) {
                DefaultScheduleEvent defaultEvent = new DefaultScheduleEvent();
                defaultEvent.setStyleClass(evenement.getIdEvenement().toString());
                defaultEvent.setTitle(evenement.getTitre());
                defaultEvent.setStartDate(evenement.getDateDebut());
                defaultEvent.setEndDate(evenement.getDateFin());
                defaultEvent.setDescription(evenement.getLieu());
                eventModel.addEvent(defaultEvent);
                //this.mapEvenementsBis.put(evenement.getIdEvenement(), evenement);
            }
        } else {
            // TODO : mode partagé
        }
    }
    
    public void init() {
        System.out.println("Init des événements de type " + this.typeAgenda);
        this.eventModel = new DefaultScheduleModel();;
        for (Entry<Integer, Evenement> entry : mapEvenements.get(this.typeAgenda).entrySet()) {
            Evenement evenement = entry.getValue();
            DefaultScheduleEvent defaultEvent = new DefaultScheduleEvent();
            defaultEvent.setStyleClass(evenement.getIdEvenement().toString());
            defaultEvent.setTitle(evenement.getTitre());
            defaultEvent.setStartDate(evenement.getDateDebut());
            defaultEvent.setEndDate(evenement.getDateFin());
            defaultEvent.setDescription(evenement.getLieu());
            eventModel.addEvent(defaultEvent);
        }
    }
    
    public void initAgendaOld() {
        ListableBeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("applicationContext.xml"));
        IAgendaMetier agendaMetierBean = (IAgendaMetier) beanFactory.getBean("agendaMetier");
        IEvenementMetier evenementMetierBean = (IEvenementMetier) beanFactory.getBean("evenementMetier");
        
        Agenda agendaPrive = agendaMetierBean.findAgenda(currentUser, AgendaTypeEnum.PRI);
        Agenda agendaPartage = agendaMetierBean.findAgenda(currentUser, AgendaTypeEnum.PAR);
        
        this.mapAgendas.put(AgendaTypeEnum.PRI.name(), agendaPrive);
        this.mapAgendas.put(AgendaTypeEnum.PAR.name(), agendaPartage);
        
        List<Evenement> listEvenementPrive = evenementMetierBean.findEventByAgenda(agendaPrive);
        List<Evenement> listEvenementPartage = evenementMetierBean.findEventByAgenda(agendaPartage);
        
        Map<Integer, Evenement> mapIdEvenementPri = new HashMap<>();
        Map<Integer, Evenement> mapIdEvenementPar = new HashMap<>();
        
        for (Evenement evenement : listEvenementPrive) {
            mapIdEvenementPri.put(evenement.getIdEvenement(), evenement);
        }
        for (Evenement evenement : listEvenementPartage) {
            mapIdEvenementPar.put(evenement.getIdEvenement(), evenement);
        }
        this.mapEvenements.put(AgendaTypeEnum.PRI.name(), mapIdEvenementPri);
        this.mapEvenements.put(AgendaTypeEnum.PAR.name(), mapIdEvenementPar);
    }
    
    public void initAgenda() {
        Iterator<Agenda> iteratorAgenda = this.currentUser.getAgendas().iterator();
        while (iteratorAgenda.hasNext()) {
            Agenda currentAgenda = iteratorAgenda.next();
            this.mapAgendas.put(currentAgenda.getType(), currentAgenda);
            this.mapEvenements.put(currentAgenda.getType(), new HashMap<Integer, Evenement>());
        }
        System.out.println("devant le for");
        for (Entry<String, Agenda> entry : this.mapAgendas.entrySet()) {
            System.out.println("agenda : " + entry.getKey());
            Map<Integer, Evenement> evenements = new HashMap<>();
            Iterator<Evenement> iteratorEvenement = entry.getValue().getEvenements().iterator();
            while (iteratorEvenement.hasNext()) {
                Evenement currentEvenement = iteratorEvenement.next();
                System.out.println("titre event :" + currentEvenement.getTitre());
                //evenements.put(currentEvenement.getIdEvenement(), currentEvenement);
                this.mapEvenements.get(entry.getKey()).put(currentEvenement.getIdEvenement(), currentEvenement);
            }
            //this.mapEvenements.put(entry.getKey(), evenements);
            System.out.println("agenda : " + entry.getKey() + " size : " + this.mapEvenements.get(entry.getKey()).size());
        }
        Iterator<Partage> iteratorPartage = this.currentUser.getPartages().iterator();
        System.out.println("devant le iterator");
        while (iteratorPartage.hasNext()) {
            Agenda currentAgenda = iteratorPartage.next().getAgenda();
            System.out.println("id agenda " + currentAgenda.getIdAgenda());
            Iterator<Evenement> iteratorEvenement = currentAgenda.getEvenements().iterator();
            while (iteratorEvenement.hasNext()) {
                Evenement currentEvenement = iteratorEvenement.next();
                System.out.println("titre event " + currentEvenement.getTitre());
                // TODO : ajouter le nom des autres avant le titre de l'événement
                System.out.println(currentAgenda.getUser().getEmail());
                String nameEventOwner = currentAgenda.getUser().getNom() + " " + currentAgenda.getUser().getPrenom();
                String nameEvent = currentEvenement.getTitre();
                currentEvenement.setTitre(nameEventOwner + " : " + nameEvent);
                this.mapEvenements.get(AgendaTypeEnum.PAR.name()).put(currentEvenement.getIdEvenement(), currentEvenement);
            }
        }
    }
    
    public ScheduleModel getEventModel() {
        return eventModel;
    }
    
    public ScheduleEvent getEvent() {
        return event;
    }
    
    public void setEvent(ScheduleEvent event) {
        this.event = event;
    }
    
    public void addEvent(ActionEvent actionEvent) {
        ListableBeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("applicationContext.xml"));
        IEvenementMetier evenementMetier = (IEvenementMetier) beanFactory.getBean("evenementMetier");
        FacesMessage message = new FacesMessage();
        if (event.getEndDate().after(event.getStartDate())) {
            Evenement evenement = new Evenement();
            if (event.getId() != null) {
                evenement = this.mapEvenements.get(this.typeAgenda).get(Integer.parseInt(event.getStyleClass()));
            }
            evenement.setTitre(event.getTitle());
            evenement.setDateDebut(event.getStartDate());
            evenement.setDateFin(event.getEndDate());
            evenement.setLieu(event.getDescription());
            evenement.setAgenda(this.mapAgendas.get(this.typeAgenda));
            evenement.setDateMaj(new Date());
            if (event.getId() == null) {
                evenement.setDateCreation(new Date());
                evenementMetier.createEvenement(evenement);
                // réinitialisation de l'événement pour le remettre dans le modèle vue
                DefaultScheduleEvent addedEvent = new DefaultScheduleEvent();
                addedEvent.setStyleClass(evenement.getIdEvenement().toString());
                addedEvent.setTitle(evenement.getTitre());
                addedEvent.setStartDate(evenement.getDateDebut());
                addedEvent.setEndDate(evenement.getDateFin());
                addedEvent.setDescription(evenement.getLieu());
                this.eventModel.addEvent(event);
            } else {
                evenementMetier.updateEvenement(evenement);
                eventModel.updateEvent(event);
            }
            this.mapEvenements.get(this.typeAgenda).put(evenement.getIdEvenement(), evenement);
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Evénement enregistré", "");
        } else {
            message = new FacesMessage("La date de fin doit être postérieure à la date de début");
        }
        this.addMessage(message);
        this.event = new DefaultScheduleEvent();
        //this.init();

    }
    
    public void deleteEvent(ActionEvent actionEvent) {
        ListableBeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("applicationContext.xml"));
        IEvenementMetier evenementMetier = (IEvenementMetier) beanFactory.getBean("evenementMetier");
        if (event.getId() != null) {
            Evenement evenement = this.mapEvenements.get(this.typeAgenda).get(Integer.parseInt(event.getStyleClass()));
            evenementMetier.deleteEvenement(evenement);
            this.mapEvenements.get(this.typeAgenda).remove(Integer.parseInt(event.getStyleClass()));
            this.eventModel.deleteEvent(event);
        }
    }
    
    public User getCurrentUser() {
        return currentUser;
    }
    
    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
    
    public String getTypeAgenda() {
        return typeAgenda;
    }
    
    public void setTypeAgenda(String typeAgenda) {
        this.typeAgenda = typeAgenda;
    }
    
    public void onEventSelect(SelectEvent selectEvent) {
        event = (ScheduleEvent) selectEvent.getObject();
    }
    
    public void onDateSelect(SelectEvent selectEvent) {
        event = new DefaultScheduleEvent("", (Date) selectEvent.getObject(), (Date) selectEvent.getObject());
    }
    
    public void onEventMove(ScheduleEntryMoveEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event moved", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());
        
        addMessage(message);
    }
    
    public void onEventResize(ScheduleEntryResizeEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event resized", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());
        
        addMessage(message);
    }
    
    private void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    public Date getRandomDate(Date base) {
        Calendar date = Calendar.getInstance();
        date.setTime(base);
        date.add(Calendar.DATE, ((int) (Math.random() * 30)) + 1);    //set random day of month

        return date.getTime();
    }
    
    public Date getInitialDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), Calendar.FEBRUARY, calendar.get(Calendar.DATE), 0, 0, 0);
        
        return calendar.getTime();
    }
    
}
