package com.wesdevelop.dogapi.resources;

import com.wesdevelop.dogapi.api.Dog;
import com.wesdevelop.dogapi.db.Dao;
import com.wesdevelop.dogapi.db.DaoException;
import com.codahale.metrics.annotation.Timed;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import java.util.Collection;
import java.util.Optional;

@Path("/dogs")
@Produces(MediaType.APPLICATION_JSON)
public class DogResource {

    private Dao<Dog> dao;

    public DogResource(Dao<Dog> dao) {
        this.dao = dao;
    }

    @GET
    @Path("/{id}")
    @Timed
    public Dog getDog(@PathParam("id") long id) {
        Optional<Dog> dog;
        try {
            dog = dao.get(id);
        } catch (DaoException e) {
            // TODO
            throw e;
        }
        if (dog.isPresent()) {
            return dog.get();
        } else {
            // TODO 404
            return null;
        }
    }

    @GET
    @Timed
    public Collection<Dog> getDogs() {
        try {
            return dao.getAll();
        } catch (DaoException e) {
            // TODO
            throw e;
        }
    }

    // TODO VALIDATION?
    @POST
    @Consumes("application/json")
    @Timed
    public Dog addDog(Dog dog) throws Exception {
        try {
            // TODO create ID
            dao.upsert(dog);
        } catch (DaoException e) {
            // TODO
            throw e;
        }
        return dog;
    }

    // TODO updated http return?
    @PUT
    @Path("/{id}")
    @Consumes("application/json")
    @Timed
    public Dog updateDog(@PathParam("id") long id, Dog dog) {
        Dog dogWithId = dog.withId(id);
        try {
            dao.upsert(dogWithId);
        } catch (DaoException e) {
            // TODO
            throw e;
        }
        return dogWithId;
    }

    // TODO deleted http return?
    @DELETE
    @Path("/{id}")
    @Timed
    public void deleteDog(@PathParam("id") long id) {
        try {
            dao.delete(id);
        } catch (DaoException e) {
            // TODO
            throw e;
        }
        // TODO return deleted
    }
}