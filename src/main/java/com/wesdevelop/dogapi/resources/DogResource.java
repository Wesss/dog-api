package com.wesdevelop.dogapi.resources;

import com.wesdevelop.dogapi.api.Dog;
import com.wesdevelop.dogapi.db.Dao;
import com.codahale.metrics.annotation.Timed;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import java.io.IOException;
import java.util.ArrayList;

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
    public Dog getDog(@PathParam("id") long id) throws Exception {
        Dog dog;
        try {
            dog = dao.get(id).get();
        } catch (Exception e) {
            throw e;
        }
        return dog;
    }

    @GET
    @Timed
    public ArrayList<Dog> getDogs() {
        ArrayList<Dog> dogs = new ArrayList<>();
        dogs.add(new Dog(1, "Rufus", "Max", "Generic Dog!"));
        dogs.add(new Dog(2, "Bone", "Sarah", "Moar Dog!"));
        return dogs;
    }

    // TODO VALIDATION?
    @POST
    @Consumes("application/json")
    @Timed
    public Dog addDog(Dog dog) throws Exception {
        try {
            dao.create(dog);
        } catch (Exception e) {
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
        return dog;
    }

    // TODO deleted http return?
    @DELETE
    @Path("/{id}")
    @Timed
    public Dog deleteDog(@PathParam("id") long id) {
        return new Dog(1, "Rufus", "Max", "Generic Dog!");
    }
}