package org.home.mazi.javasimplerest.data;

import org.home.mazi.javasimplerest.model.Person;

import java.util.Optional;

public class PersonServiceImpl implements PersonService {
    @Override
    public Optional<Person> getPerson(long id) {
        return Optional.of(new Person(id, "Daniel", "Mazur"));
    }
}
