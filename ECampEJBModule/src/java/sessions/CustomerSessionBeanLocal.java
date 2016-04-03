/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import domain.customer.Customer;
import javax.ejb.Local;

/**
 *
 * @author nnao9_000
 */
@Local
public interface CustomerSessionBeanLocal {

    void addCustomer(String name, String email, String password);

    Customer findCustomer(String email, String password);

    java.util.List<domain.customer.Booking> getBookingHistory(Customer c);

    void sendEmails();
    
}
