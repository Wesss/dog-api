package com.wesdevelop.dogapi.db;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wesdevelop.dogapi.api.Dog;

public class LocalJsonFileDao implements Dao<Dog> {

    private ObjectMapper mapper;
    private File jsonFile;

    public LocalJsonFileDao() {
        this.mapper = new ObjectMapper();
        this.jsonFile = new File(".\\dogs.json");
    }

    public Optional<Dog> get(long id) throws JsonParseException, JsonMappingException, IOException {
        Dog dog = mapper.readValue(jsonFile, Dog.class);
        return Optional.of(dog);
    }

    public List<Dog> getAll() throws JsonParseException, JsonMappingException, IOException {
        Dog dog = mapper.readValue(jsonFile, Dog.class);
        List<Dog> dogs = new ArrayList<>();
        dogs.add(dog);
        return dogs;
    }
 
    public void create(Dog dog) throws IOException {
        mapper.writeValue(jsonFile, dog);
    }

    public void update(long id, Dog dog) {
        throw new RuntimeException();
    }

    public void delete(long id) {
        throw new RuntimeException();
    }
}