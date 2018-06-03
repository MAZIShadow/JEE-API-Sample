package org.home.mazi.javasimplerest.data;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.home.mazi.javasimplerest.entity.Address;

/**
 *
 * @author User
 */

@Stateless
public class AddressService {
    
    @PersistenceContext(unitName = "prod")
    EntityManager em;

    public Address findById(Long id) {
        return this.em.find(Address.class, id);
    }
    
     public List<Address> findAll() {
        return this.em.createNamedQuery(Address.FIND_ALL).getResultList();
    }
     
    public void create(Address address) {
        this.em.merge(address);
    }

    public void remove(Address address) {
        this.em.remove(this.em.contains(address) ? address : this.em.merge(address));
    }
}
