/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import domain.camp.Camp;
import domain.camp.CampOffer;
import domain.customer.Booking;
import domain.customer.Customer;
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
import sessions.CampSessionBeanLocal;

/**
 *
 * @author nnao9_000
 */
@WebService(serviceName = "CampWebService")
@Stateless()
public class CampWebService {

    @EJB
    private CampSessionBeanLocal campSessionBean;
    
    @WebMethod(operationName = "getCampOffers")
    public List<CampOffer> getCampOffers()
    {
        return campSessionBean.getCampOffers();
    }
    
    @WebMethod(operationName = "getCampOfferById")
    public CampOffer getCampOfferById(int id)
    {
        return campSessionBean.getCampOfferById(id);
    }
    
    @WebMethod(operationName = "createBooking")
    public void createBooking(Booking b)
    {
        campSessionBean.createBooking(b);
    }
}
