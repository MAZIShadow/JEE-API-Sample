package org.home.mazi.javasimplerest.data;

import org.home.mazi.javasimplerest.model.Person;

import java.util.Optional;

public interface PersonService {
    Optional<Person> getPerson(long id);
}
