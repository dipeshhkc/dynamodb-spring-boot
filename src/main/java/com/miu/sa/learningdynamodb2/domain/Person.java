package com.miu.sa.learningdynamodb2.domain;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

@DynamoDBTable(tableName = "Person")
public class Person {

    @Id
    private PersonKey key;

    @DynamoDBAttribute(attributeName = "name")
    @Getter
    @Setter
    private String name;

    @DynamoDBAttribute(attributeName = "address")
    @Getter
    @Setter
    private Address address;

    @DynamoDBHashKey(attributeName = "id")
    public String getId() {
        return key.getId();
    }

    public void setId(String id) {
        if (key == null) {
            key = new PersonKey();
        }
        key.setId(id);
    }

    @DynamoDBRangeKey(attributeName = "creationDate")
    //dynamo db mapper doesnot know LocalDate
    @DynamoDBTypeConverted(converter = LocalDateConverter.class)
    public LocalDate getCreationDate() {
        return key.getCreationDate();
    }

    public void setCreationDate(LocalDate creationDate) {
        if (key == null) {
            key = new PersonKey();
        }
        key.setCreationDate(creationDate);
    }


}
