/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.camp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author nnao9_000
 */
@Entity
public class Camp implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String campName;
    private String location;
    @Enumerated
    private CampType campType;
    private double price;
    private double extraEquipment;

    public CampType getCampType() {
        return campType;
    }

    public void setCampType(CampType campType) {
        this.campType = campType;
    }

    public double getExtraEquipment() {
        return extraEquipment;
    }

    public void setExtraEquipment(double extraEquipment) {
        this.extraEquipment = extraEquipment;
    }
    private int daysPeriod;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCampName() {
        return campName;
    }

    public void setCampName(String campName) {
        this.campName = campName;
    }
    
    public int getDaysPeriod() {
        return daysPeriod;
    }

    public void setDaysPeriod(int daysPeriod) {
        this.daysPeriod = daysPeriod;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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
        if (!(object instanceof Camp)) {
            return false;
        }
        Camp other = (Camp) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domain.Camp[ id=" + id + " ]";
    }
    
}
