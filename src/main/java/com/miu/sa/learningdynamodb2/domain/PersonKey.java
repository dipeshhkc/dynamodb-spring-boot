package com.miu.sa.learningdynamodb2.domain;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PersonKey {
    @DynamoDBHashKey
    private String id;
    @DynamoDBRangeKey
    private LocalDate creationDate;
}
