package com.techprimers.kafka.springbootkafkaproducerexample.resource;

import com.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("kafka")
public class UserResource {

    @Autowired
    private KafkaTemplate<String, User> kafkaTemplate;

    private static final String TOPIC = "Kafka_Example";

    @GetMapping("/publish/{name}")
    public String post(@PathVariable("name") final String name) {

        kafkaTemplate.send(TOPIC,new User(name, "Technology", 12000L));
        kafkaTemplate.send("Kafka_Example_json", 0,"1", new User(name + "0", "Technology0", 13000L));
        kafkaTemplate.send("Kafka_Example_json", 1,"2", new User(name+"1", "Technology1", 13000L));

        return "Published successfully";
    }
}
