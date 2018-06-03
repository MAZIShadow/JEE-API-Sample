package org.home.mazi.javasimplerest.data;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.home.mazi.javasimplerest.entity.Person;

/**
 *
 * @author User
 */
@Stateless
public class PersonService {
    
    @PersistenceContext(unitName = "prod")
    EntityManager em;

    public Person findById(Long id) {
        return this.em.find(Person.class, id);
    }
    
    public List<Person> findAll() {
        return this.em.createNamedQuery(Person.FIND_ALL).getResultList();
    }

    public void create(Person person) {
        this.em.merge(person);
    }

    public void remove(Person person) {
        this.em.remove(this.em.contains(person) ? person : this.em.merge(person));
    }
}
