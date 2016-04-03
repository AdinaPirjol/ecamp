/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.camp;

import domain.customer.Booking;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.Transient;

/**
 *
 * @author nnao9_000
 */
@Entity
public class CampOffer implements Serializable {

    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private int totalSeats;
    @Transient
    private int remainingSeats;

    public int getRemainingSeats() {
        return remainingSeats;
    }

    public void setRemainingSeats(int remainingSeats) {
        this.remainingSeats = remainingSeats;
    }
    @OneToMany
    private List<DailyProgram> dailyPrograms = new ArrayList<>();
    @OneToMany
    private List<OptionalTrip> optionalTrips = new ArrayList<>();
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date startDate;
    @OneToOne
    private DailyProgram dailyProgram;
    @ManyToOne
    private Camp camp;

    public Camp getCamp() {
        return camp;
    }

    public void setCamp(Camp camp) {
        this.camp = camp;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public DailyProgram getDailyProgram() {
        return dailyProgram;
    }

    public void setDailyProgram(DailyProgram dailyProgram) {
        this.dailyProgram = dailyProgram;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    public List<DailyProgram> getDailyPrograms() {
        return dailyPrograms;
    }

    public void setDailyPrograms(List<DailyProgram> dailyPrograms) {
        this.dailyPrograms = dailyPrograms;
    }

    public List<OptionalTrip> getOptionalTrips() {
        return optionalTrips;
    }

    public void setOptionalTrips(List<OptionalTrip> optionalTrips) {
        this.optionalTrips = optionalTrips;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CampOffer)) {
            return false;
        }
        CampOffer other = (CampOffer) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domain.CampOffer[ id=" + id + " ]";
    }
    
}
