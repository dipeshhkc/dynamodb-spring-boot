package com.miu.sa.learningdynamodb2.repositories;


import com.miu.sa.learningdynamodb2.domain.Person;
import com.miu.sa.learningdynamodb2.domain.PersonKey;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@EnableScan
public interface PersonRepository extends CrudRepository<Person, PersonKey> {

    //showing usecase of finding only by partition key
    List<Person> findById(String id);

}