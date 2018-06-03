package org.home.mazi.javasimplerest.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;

/**
 *
 * @author User
 */
@Entity
@NamedQuery(name = Address.FIND_ALL, query = "select ad from Address ad")
public class Address implements Serializable {

    public static final String FIND_ALL = "findAll";
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Column(name = "street_name")
    private String streetName;
    @NotNull
    @Column(name = "house_number")
    private String houseNumber;
    @Column(name = "flat_number")
    private Long flatNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public Long getFlatNumber() {
        return flatNumber;
    }

    public void setFlatNumber(Long flatNumber) {
        this.flatNumber = flatNumber;
    }
}
