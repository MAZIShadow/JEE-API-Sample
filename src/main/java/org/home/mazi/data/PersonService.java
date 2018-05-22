package org.home.mazi.data;

import org.home.mazi.model.Person;

import java.util.Optional;

public interface PersonService {
    Optional<Person> getPerson(long id);
}
