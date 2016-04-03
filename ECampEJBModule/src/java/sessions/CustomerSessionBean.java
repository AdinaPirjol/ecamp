/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import domain.customer.Booking;
import domain.customer.Customer;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import ws.CustomerWebService;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Properties;
import javax.annotation.Resource;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author nnao9_000
 */
@Stateless
public class CustomerSessionBean implements CustomerSessionBeanLocal {

    @Resource(mappedName = "myQueue")
    private Queue myQueue;

    @Inject
    @JMSConnectionFactory("java:comp/DefaultJMSConnectionFactory")
    private JMSContext context;

    @PersistenceContext(unitName = "ECampEJBModulePU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public void addCustomer(String name, String email, String password) {
    
        if(this.findExistingCustomer(email) != null) {
            try {
                throw new Exception();
            } catch (Exception ex) {
                Logger.getLogger(CustomerSessionBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        Customer c = new Customer();
        c.setName(name);
        c.setEmail(email);
        c.setPassword(CustomerSessionBean.getHash(password));
        
        this.persist(c);
    }
    
    private Customer findExistingCustomer(String email)
    {
        Query query = em
            .createQuery("SELECT c from Customer c where c.email=:email")
            .setParameter("email", email);
        try {
            Customer c = (Customer) query.getSingleResult();
            return c;
        } catch(NoResultException e) {
            e.printStackTrace();
            return null;
        } 
    }
    
    private static String getHash(String password) {
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("SHA-1");
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
        digest.reset();
        try {
            digest.update(password.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
        }
        return new BigInteger(1, digest.digest()).toString(16);
    }

    @Override
    public Customer findCustomer(String email, String password) {
        password = this.getHash(password);
        
        Query query = em
            .createQuery("SELECT c from Customer c where c.email=:email and c.password=:password")
            .setParameter("email", email)
            .setParameter("password", password);
        try {
            Customer c = (Customer) query.getSingleResult();
            return c;
        } catch(NoResultException e) {
            return null;
        } 
    }

    @Override
    public java.util.List<domain.customer.Booking> getBookingHistory(Customer c) {
        Query query = em
            .createQuery("SELECT b from Booking b JOIN b.customer c where c.id=:id")
            .setParameter("id", c.getId());
        try {
            List<Booking> bookings = (List<Booking>) query.getResultList();
            return bookings;
        } catch(NoResultException e) {
            return null;
        } 
    }

    @Override
    public void sendEmails() {
        Date today = new Date();
        
        Query query = em
            .createQuery("SELECT c from Booking b join b.campOffer co join b.customer c where co.startDate = :today")
            .setParameter("today", today);
        List<Customer> customers = (List<Customer>) query.getResultList();
        
        BufferedReader brConsoleReader = null; 
        Properties props;
        InitialContext ctx;
//        props = new Properties();
//        try {
//           props.load(new FileInputStream("jndi.properties"));
//        } catch (IOException ex) {
//           ex.printStackTrace();
//        }
        try {
              ctx = new InitialContext();            

              brConsoleReader = 
              new BufferedReader(new InputStreamReader(System.in));


            Queue queue = (Queue) ctx.lookup("myQueue");
           QueueConnectionFactory factory =
           (QueueConnectionFactory) ctx.lookup("myQueueConnectionFactory");
           QueueConnection connection =  factory.createQueueConnection();
           QueueSession session = 
           connection.createQueueSession(false, QueueSession.AUTO_ACKNOWLEDGE);
           QueueSender sender = session.createSender(queue);

           
            for(Customer c:customers){
                  ObjectMessage objectMessage = 
                    session.createObjectMessage(c);
                 sender.send(objectMessage); 
            }

           MyMessageBean libraryBean = 
           (MyMessageBean)
           ctx.lookup("MyMessageBean/remote");

        } catch (Exception e) {
           System.out.println(e.getMessage());
           e.printStackTrace();
        }finally {
           try {
                if(brConsoleReader !=null){
                   brConsoleReader.close();
                }
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }

        }
    }

    private void sendJMSMessageToMyQueue(String messageData) {
        context.createProducer().send(myQueue, messageData);
    }
}
