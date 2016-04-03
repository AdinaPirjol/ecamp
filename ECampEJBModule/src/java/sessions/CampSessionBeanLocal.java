/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import domain.camp.CampOffer;
import domain.customer.Booking;
import javax.ejb.Local;

/**
 *
 * @author nnao9_000
 */
@Local
public interface CampSessionBeanLocal {

    java.util.List<domain.camp.CampOffer> getCampOffers();

    CampOffer getCampOfferById(int id);

    void createBooking(Booking b);
    
}