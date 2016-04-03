/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import domain.customer.Booking;
import domain.customer.Customer;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.ejb.EJB;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import sessions.CustomerSessionBeanLocal;

/**
 *
 * @author nnao9_000
 */
@WebService(serviceName = "CustomerWebService")
@Stateless()
public class CustomerWebService {

    @EJB
    private CustomerSessionBeanLocal customerSessionBean;

    @WebMethod(operationName = "addCustomer")
    public void addCustomer(String name, String email, String password) throws Exception {
        customerSessionBean.addCustomer(name, email, password);
    }
    
    @WebMethod(operationName = "findCustomer")
    public Customer findCustomer(String email, String password)
    {
        return customerSessionBean.findCustomer(email, password);
    }
    
    @WebMethod(operationName = "getBookingHistory")
    public List<Booking> getBookingHistory(Customer c)
    {
        return customerSessionBean.getBookingHistory(c);
    }
    
    @WebMethod(operationName = "sendEmails")
    public void sendEmails()
    {
        customerSessionBean.sendEmails();
    }
}
