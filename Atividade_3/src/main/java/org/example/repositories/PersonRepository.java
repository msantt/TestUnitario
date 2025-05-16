package org.example.repositories;

import org.example.entities.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PersonRepository {

    private List<Person> persons;

    public PersonRepository() {
        this.persons = new ArrayList<>();
    }

    public void addPerson(Person person) {
        persons.add(person);
    }

    ;

    public List<Person> searchForId(int id) {
        List<Person> result = new ArrayList<>();
        for (Person p : persons) {
            if (p.getId() == id) {
                result.add(p);
            }
        }
        return result;
    }

    public void removePerson(int id) {
        List<Person> searchPerson = searchForId(id);
            persons.remove(id);
        }

    public List<Person> searchAll() {
        return persons;
    }
}
