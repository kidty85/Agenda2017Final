/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import agenda.metier.IAgendaMetier;
import entite.Agenda;
import entite.User;
import enums.AgendaTypeEnum;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import user.metier.IUserMetier;

/**
 * Classe de test pour l'entité Agenda.
 * 
 * @author quangminhnguyen
 */
public class AgendaDaoTest {
    
    public AgendaDaoTest() {
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
    public void findAgendaTest(){
        ListableBeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("applicationContext.xml"));
        IUserMetier userMetier = (IUserMetier) beanFactory.getBean("userMetier");
        IAgendaMetier agendaMetier = (IAgendaMetier) beanFactory.getBean("agendaMetier");
        
        User user = userMetier.findUser("test@test");
        Agenda agendaPrive = agendaMetier.findAgenda(user, AgendaTypeEnum.PRI);
        Agenda agendaPartage = agendaMetier.findAgenda(user, AgendaTypeEnum.PAR);
        
        Assert.assertNotNull(agendaPrive.getIdAgenda());
        Assert.assertNotNull(agendaPartage.getIdAgenda());
    }
}
