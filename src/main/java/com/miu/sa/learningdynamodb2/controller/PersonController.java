package com.miu.sa.learningdynamodb2.controller;

import com.miu.sa.learningdynamodb2.domain.Person;
import com.miu.sa.learningdynamodb2.domain.PersonKey;
import com.miu.sa.learningdynamodb2.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/person")
public class PersonController {

    @Autowired
    PersonRepository personRepository;

    @GetMapping
    public ResponseEntity findAll() {
        List<Person> people = new ArrayList<>();
        personRepository.findAll().forEach(p -> people.add(p));
        if (people.size() > 0)
            return ResponseEntity.ok(people);
        else
            return ResponseEntity.notFound().build();


    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable("id") String id,
                                   @RequestParam(value = "creationDate", required = false)
                                   @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate creationDate) {

        if (creationDate == null) {
            List<Person> people = personRepository.findById(id);
            return ResponseEntity.ok(people);
        }

        return getByPrimaryKey(id, creationDate);
    }

    @PostMapping
    public ResponseEntity save(@RequestBody Person person) {
        return ResponseEntity.ok(personRepository.save(person));
    }

    private ResponseEntity<?> getByPrimaryKey(String id, LocalDate creationDate) {
        PersonKey personKey = new PersonKey();
        personKey.setId(id);
        personKey.setCreationDate(creationDate);

        Optional<Person> mayBePerson = personRepository.findById(personKey);

        if (mayBePerson.isPresent()) {
            return ResponseEntity.ok(mayBePerson.get());
        }
        return ResponseEntity.notFound()
                .build();
    }
}
