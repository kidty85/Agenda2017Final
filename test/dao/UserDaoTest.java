/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entite.User;
import enums.SexeEnum;
import java.util.Date;
import java.util.List;
import junit.framework.TestCase;
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
 * Classe de test pour l'entité User.
 *
 * @author quangminhnguyen
 */
public class UserDaoTest {
    
    public UserDaoTest() {
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
    public void createUserTest() {
        ListableBeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("applicationContext.xml"));
        IUserMetier userMetier = (IUserMetier) beanFactory.getBean("userMetier");
        
        User user = new User();
        user.setNom("Nom_test");
        user.setPrenom("Prenom_test");
        user.setDateNaissance(new Date());
        user.setSexe(SexeEnum.H.name());
        user.setEmail("test@test");
        user.setPwd("password");
        
        userMetier.createUser(user);
        
        Assert.assertNotNull(user.getIdUser());
        //Assert.assertEquals(2, user.getAgendas().size());
        
    }
    
    @Test
    public void findUserTest() {
        ListableBeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("applicationContext.xml"));
        IUserMetier userMetier = (IUserMetier) beanFactory.getBean("userMetier");
        
        User user = userMetier.findUser("test@test");
        
        TestCase.assertNotNull(user);
        //Assert.assertEquals(2, user.getAgendas().size());
    }
    
    @Test
    public void findAllUsersTest() {
        ListableBeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("applicationContext.xml"));
        IUserMetier userMetier = (IUserMetier) beanFactory.getBean("userMetier");
        
        User userAdmin = userMetier.findAdmin();
        
        List<User> listUser = userMetier.findAllUsersButCurrent(userAdmin);
        
        Assert.assertTrue(listUser.size() > 0);
    }
    
    @Test
    public void findAdminTest(){
        ListableBeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("applicationContext.xml"));
        IUserMetier userMetier = (IUserMetier) beanFactory.getBean("userMetier");
        
        User userAdmin = userMetier.findAdmin();
        
        Assert.assertNotNull(userAdmin.getIdUser());
    }
}
