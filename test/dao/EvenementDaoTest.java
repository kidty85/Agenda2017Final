/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import agenda.metier.IAgendaMetier;
import entite.Agenda;
import entite.Evenement;
import entite.User;
import enums.AgendaTypeEnum;
import evenement.metier.IEvenementMetier;
import java.util.Date;
import java.util.List;
import junit.framework.Assert;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import user.metier.IUserMetier;

/**
 * Classe de test pour l'entité Evenement.
 *
 * @author quangminhnguyen
 */
public class EvenementDaoTest {

    public EvenementDaoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void findEventByAgendaTest() {
        ListableBeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("applicationContext.xml"));
        IUserMetier userMetier = (IUserMetier) beanFactory.getBean("userMetier");
        IAgendaMetier agendaMetier = (IAgendaMetier) beanFactory.getBean("agendaMetier");
        IEvenementMetier evenementMetier = (IEvenementMetier) beanFactory.getBean("evenementMetier");

        User user = userMetier.findUser("test@test");
        Agenda listAgendaPrive = agendaMetier.findAgenda(user, AgendaTypeEnum.PRI);
        Agenda listAgendaPartage = agendaMetier.findAgenda(user, AgendaTypeEnum.PAR);

        List<Evenement> listEvenementPrive = evenementMetier.findEventByAgenda(listAgendaPrive);
        List<Evenement> listEvenementPartage = evenementMetier.findEventByAgenda(listAgendaPartage);
            
        Assert.assertEquals(1, listEvenementPrive.size());
        Assert.assertEquals(0, listEvenementPartage.size());

    }

    @Test
    public void createEvenementTest() {
        ListableBeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("applicationContext.xml"));
        IUserMetier userMetier = (IUserMetier) beanFactory.getBean("userMetier");
        IAgendaMetier agendaMetier = (IAgendaMetier) beanFactory.getBean("agendaMetier");
        IEvenementMetier evenementMetier = (IEvenementMetier) beanFactory.getBean("evenementMetier");

        User user = userMetier.findUser("test@test");
        Agenda agendaPrive= agendaMetier.findAgenda(user, AgendaTypeEnum.PRI);

        Evenement evenement = new Evenement();
        evenement.setAgenda(agendaPrive);
        evenement.setDateCreation(new Date());
        evenement.setDateMaj(new Date());
        evenement.setDateDebut(new Date());
        evenement.setDateFin(new Date());
        evenement.setTitre("TEST");
        evenement.setLieu("TEST");
        evenement.setDescription("TEST");

        evenementMetier.createEvenement(evenement);

        Assert.assertNotNull(evenement.getIdEvenement());
    }

    @Test
    public void deleteEvenementTest() {
        ListableBeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("applicationContext.xml"));
        IUserMetier userMetier = (IUserMetier) beanFactory.getBean("userMetier");
        IAgendaMetier agendaMetier = (IAgendaMetier) beanFactory.getBean("agendaMetier");
        IEvenementMetier evenementMetier = (IEvenementMetier) beanFactory.getBean("evenementMetier");

        User user = userMetier.findUser("test@test");
        Agenda agendaPrive = agendaMetier.findAgenda(user, AgendaTypeEnum.PRI);
        List<Evenement> listEvenement = evenementMetier.findEventByAgenda(agendaPrive);

        Assert.assertNotNull(listEvenement);
        Evenement evenementToDelete = listEvenement.get(0);
        evenementMetier.deleteEvenement(evenementToDelete);

        Assert.assertNull(evenementToDelete.getIdEvenement());

    }
}
