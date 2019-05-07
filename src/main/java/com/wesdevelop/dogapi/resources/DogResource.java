package com.wesdevelop.dogapi.resources;

import com.wesdevelop.dogapi.api.Dog;
import com.wesdevelop.dogapi.db.Dao;
import com.wesdevelop.dogapi.db.DaoException;
import org.slf4j.Logger;
import com.codahale.metrics.annotation.Timed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;
import java.util.stream.Collectors;

@Path("/dogs")
@Produces(MediaType.APPLICATION_JSON)
public class DogResource {

    private Dao<Dog> dao;
    private Logger logger;

    public DogResource(Dao<Dog> dao, Logger logger) {
        this.dao = dao;
        this.logger = logger;
    }

    @GET
    @Path("/{id}")
    @Timed
    public Response getDog(@PathParam("id") long id) {
        Optional<Dog> dog;
        try {
            dog = dao.get(id);
        } catch (DaoException e) {
            logger.error("Unable to get specified dog", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        if (dog.isPresent()) {
            return Response.ok(dog.get()).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Timed
    public Response getDogs() {
        try {
            return Response.ok(dao.getAll()).build();
        } catch (DaoException e) {
            logger.error("Unable to get all dogs", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @POST
    @Consumes("application/json")
    @Timed
    public Response addDog(Dog dog) throws Exception {
        try {
            Optional<Long> maxId = dao.getAll()
                .stream()
                .map(Dog::getId)
                .collect(
                    Collectors.maxBy((a, b) -> {
                        if (a < b) {
                            return -1;
                        } else if (a > b) {
                            return 1;
                        } else {
                            return 0;
                        }
                    })
                );
            Long nextId = maxId.isPresent() ? maxId.get() + 1 : 0;
            
            Dog dogWithId = dog.withId(nextId);
            dao.upsert(dogWithId);
            return Response.ok(dogWithId).build();
        } catch (DaoException e) {
            logger.error("Unable to add dog", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes("application/json")
    @Timed
    public Response updateDog(@PathParam("id") long id, Dog dog) {
        try {
            Optional<Long> matchingId = dao.getAll()
                .stream()
                .map(Dog::getId)
                .filter(curId -> curId == id)
                .findAny();
            if (!matchingId.isPresent()) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            Dog dogWithId = dog.withId(id);
            dao.upsert(dogWithId);
            return Response.ok(dogWithId).build();
        } catch (DaoException e) {
            logger.error("Unable to update dog", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Timed
    public Response deleteDog(@PathParam("id") long id) {
        try {
            dao.delete(id);
        } catch (DaoException e) {
            logger.error("Unable to delete dog", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        return Response.ok().build();
    }
}