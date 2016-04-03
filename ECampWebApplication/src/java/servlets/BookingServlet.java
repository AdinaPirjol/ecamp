/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebServiceRef;
import ws.CampOffer;
import ws.CampWebService_Service;
import ws.Customer;
import ws.Booking;
import ws.Participant;

/**
 *
 * @author nnao9_000
 */
@WebServlet(name = "BookingServlet", urlPatterns = {"/booking"})
public class BookingServlet extends HttpServlet {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_31108/CampWebService/CampWebService.wsdl")
    private CampWebService_Service service;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String childrenNames = request.getParameter("childrenNames");
        
        int childrenNumber = Integer.parseInt(request.getParameter("childrenNumber"));
        int campOfferId = Integer.parseInt(request.getParameter("campOfferId"));
        CampOffer campOffer = this.getCampOfferById(campOfferId);
        Customer c = (Customer) request.getSession().getAttribute("customer");
        String checkbox = request.getParameter("tripCheckbox");
        double totalPrice;
        
        if (checkbox != null) {
            totalPrice = campOffer.getCamp().getPrice() + (double) request.getSession().getAttribute("optionalPrice");
        } else {
            totalPrice = campOffer.getCamp().getPrice();
        }
        request.getSession().setAttribute("totalPrice", totalPrice);
        
        String[] children = childrenNames.split(",");
        List<Participant> participants =  new ArrayList<Participant>();
        for(String child:children) {
            Participant p = new Participant();
            p.setName(child.trim());
            participants.add(p);
        }
        Booking b = new Booking();
        b.setCampOffer(campOffer);
        b.setParticipants(participants);
        b.setChildrenNumber(childrenNumber);
        b.setCustomer(c);
        b.setTotalPrice(totalPrice);
        
        this.createBooking(b);
        
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void createBooking(Booking b) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        ws.CampWebService port = service.getCampWebServicePort();
        port.createBooking(b);
    }

    private CampOffer getCampOfferById(int arg0) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        ws.CampWebService port = service.getCampWebServicePort();
        return port.getCampOfferById(arg0);
    }
}
