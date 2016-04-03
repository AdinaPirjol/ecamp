/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import domain.camp.CampOffer;
import domain.customer.Booking;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author nnao9_000
 */
@Stateless
public class CampSessionBean implements CampSessionBeanLocal {

    @PersistenceContext(unitName = "ECampEJBModulePU")
    private EntityManager em;

    @Override
    public java.util.List<domain.camp.CampOffer> getCampOffers() {
        Query query = em
            .createQuery("SELECT c from CampOffer c");
        try {
            List<CampOffer> c = (List<CampOffer>)query.getResultList();
            return c;
        } catch(NoResultException e) {
            e.printStackTrace();
            return null;
        }  
    }

    public void persist(Object object) {
        em.persist(object);
    }

    @Override
    public CampOffer getCampOfferById(int id) {
        try {
        Query query = em
            .createQuery("SELECT c from CampOffer c where c.id = :id")
            .setParameter("id", id);
        
            CampOffer c = (CampOffer)query.getSingleResult();
            
            query = em
                .createQuery("SELECT b from Booking b join b.campOffer co where co.id=:id")
                .setParameter("id", id);   

            List<Booking> bookings = (List<Booking>)query.getResultList();
            
            int reserved = 0;
            for(Booking b:bookings) {
                reserved += b.getParticipants().size();
            }
            
            c.setRemainingSeats(c.getTotalSeats()-reserved);
            return c;
        } catch(NoResultException e) {
            e.printStackTrace();
            return null;
        } 
    }

    @Override
    public void createBooking(Booking b) {
        this.persist(b);
    }
}
