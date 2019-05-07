package com.wesdevelop.dogapi.db;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import com.wesdevelop.dogapi.api.Dog;

public class LocalJsonFileDao implements Dao<Dog> {

    private ObjectMapper mapper;
    private File jsonFile;
    private Logger logger;

    public LocalJsonFileDao(Logger logger) {
        this.mapper = new ObjectMapper();
        this.jsonFile = new File(".\\dogs.json");
        this.logger = logger;
    }

    public Optional<Dog> get(long id) {
        Map<Long, Dog> dogs = this.readDogs();
        logger.info(dogs.toString());
        Dog dog = dogs.get(id);
        if (dog == null) {
            return Optional.empty();
        } else {
            return Optional.of(dogs.get(id));
        }
    }

    public Collection<Dog> getAll() {
        Map<Long, Dog> dogs = this.readDogs();
        return dogs.values();
    }
 
    public void upsert(Dog dog) {
        Map<Long, Dog> dogs = this.readDogs();
        dogs.put(dog.getId(), dog);
        this.saveDogs(dogs);
    }

    public void delete(long id) {
        Map<Long, Dog> dogs = this.readDogs();
        dogs.remove(id);
        this.saveDogs(dogs);
    }

    private Map<Long, Dog> readDogs() throws DaoException {
        if (!jsonFile.exists()) {
            return new HashMap<>();
        }
        try {
            return mapper.readValue(jsonFile, new TypeReference<Map<Long, Dog>>(){});
        } catch (IOException e) {
            this.logger.error("Unable to read dog json file", e);
            throw new DaoException("Unable to read dog json fil", e);
        }
    }

    private void saveDogs(Map<Long, Dog> dogs) throws DaoException {
        try {
            mapper.writeValue(jsonFile, dogs);
        } catch (IOException e) {
            this.logger.error("Unable to save dog json file", e);
            throw new DaoException("Unable to save dog json fil", e);
        }
    }
}