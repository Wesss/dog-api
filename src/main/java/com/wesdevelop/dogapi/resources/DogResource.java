package com.wesdevelop.dogapi.resources;

import com.wesdevelop.dogapi.api.Dog;
import com.codahale.metrics.annotation.Timed;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.Optional;

@Path("/dogs")
@Produces(MediaType.APPLICATION_JSON)
public class DogResource {
    public DogResource() {
    }

    @GET
    @Timed
    public ArrayList<Dog> getDogs() {
        ArrayList<Dog> dogs = new ArrayList<>();
        dogs.add(new Dog(1, "Rufus", "Max", "Generic Dog!"));
        dogs.add(new Dog(2, "Bone", "Sarah", "Moar Dog!"));
        return dogs;
    }

    @POST
    @Consumes("application/json")
    @Timed
    public Dog addDog(Dog dog) {
        return dog;
    }

    @GET
    @Path("/{id}")
    @Consumes("application/json")
    @Timed
    public Dog getDog(@PathParam("id") long id) {
        return new Dog(1, "Rufus", "Max", "Generic Dog!");
    }
    // @GET
    // @Timed
    // public Dog sayHello(@QueryParam("name") Optional<String> name) {
    //     final String value = String.format(template, name.orElse(defaultName));
    //     return new Dog(counter.incrementAndGet(), value);
    // }
}