package com.wesdevelop.dogapi.db;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.wesdevelop.dogapi.api.Dog;

public class LocalJsonFileDao implements Dao<Dog> {

    private ObjectMapper mapper;
    private File jsonFile;
    private Logger logger;

    public LocalJsonFileDao() {
        this.mapper = new ObjectMapper();
        this.jsonFile = new File(".\\dogs.json");
        this.logger = LoggerFactory.getLogger("LocalJsonFileDao");
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
        try {
            return mapper.readValue(jsonFile, new TypeReference<Map<Long, Dog>>(){});
        } catch (IOException e) {
            // TODO log?
            throw new DaoException("Unable to access Dog json", e);
        }
    }

    private void saveDogs(Map<Long, Dog> dogs) throws DaoException {
        try {
            mapper.writeValue(jsonFile, dogs);
        } catch (IOException e) {
            // TODO log?
            throw new DaoException("Unable to save Dog json", e);
        }
    }
}