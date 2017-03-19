/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import amis.metier.IAmisMetier;
import entite.User;
import java.util.Map;
import junit.framework.Assert;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import user.metier.IUserMetier;

/**
 * Classe de test pour l'entité Amis.
 *
 * @author quangminhnguyen
 */
public class AmisDaoTest {

    public AmisDaoTest() {
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
    public void findSentInvitByUserTest() {
        ListableBeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("applicationContext.xml"));
        IUserMetier userMetier = (IUserMetier) beanFactory.getBean("userMetier");
        IAmisMetier amisMetier = (IAmisMetier) beanFactory.getBean("amisMetier");

        User user = userMetier.findUser("test@test");

        Map<Integer, User> mapSentInvit = amisMetier.findSentInvitByUser(user);

        Assert.assertEquals(1, mapSentInvit.size());
    }
    
    @Test
    public void findReceivedInvitByUserTest() {
        ListableBeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("applicationContext.xml"));
        IUserMetier userMetier = (IUserMetier) beanFactory.getBean("userMetier");
        IAmisMetier amisMetier = (IAmisMetier) beanFactory.getBean("amisMetier");

        User user = userMetier.findUser("test@test");

        Map<Integer, User> mapSentInvit = amisMetier.findReceivedInvitByUser(user);

        Assert.assertEquals(1, mapSentInvit.size());
    }
    
    @Test
    public void findAcceptedInvitByUserTest() {
        ListableBeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("applicationContext.xml"));
        IUserMetier userMetier = (IUserMetier) beanFactory.getBean("userMetier");
        IAmisMetier amisMetier = (IAmisMetier) beanFactory.getBean("amisMetier");

        User user = userMetier.findUser("test@test");

        Map<Integer, User> mapSentInvit = amisMetier.findAcceptedInvitByUser(user);

        User uu = mapSentInvit.get(10);
        User uuu = mapSentInvit.get(11);
        
        Assert.assertEquals(2, mapSentInvit.size());
    }
}
