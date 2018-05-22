package org.home.mazi.data;

import org.home.mazi.model.Person;

import java.util.Optional;

public class PersonServiceImpl implements PersonService {
    @Override
    public Optional<Person> getPerson(long id) {
        return Optional.of(new Person(id, "Daniel", "Mazur"));
    }
}
