package com.site.controller;

import com.site.model.Actor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ActorController {

    List<Actor> actors = new ArrayList<>();

    public ActorController() {

        actors.add(new Actor(
                1,
                "Feven Tamene",
                "Grey Anatomy"
        ));

        actors.add(new Actor(
                2,
                "Naomi Tilahun",
                "Weak Hero"
        ));
    }

    // GET ALL
    @GetMapping("/actor")
    public List<Actor> getActorDetails() {
        return actors;
    }

    // GET BY ID
    @GetMapping("/actor/{id}")
    public Actor getActorById(@PathVariable int id) {

        for (Actor actor : actors) {
            if (actor.getId() == id) {
                return actor;
            }
        }

        return null;
    }

    // POST
    @PostMapping("/actor")
    public Actor addActor(@RequestBody Actor actor) {

        actors.add(actor);

        return actor;
    }

    // PUT
    @PutMapping("/actor/{id}")
    public Actor updateActor(
            @PathVariable int id,
            @RequestBody Actor updatedActor) {

        for (int i = 0; i < actors.size(); i++) {

            if (actors.get(i).getId() == id) {

                actors.set(i, updatedActor);

                return updatedActor;
            }
        }

        return null;
    }

    // PATCH
    @PatchMapping("/actor/{id}")
    public Actor patchActor(
            @PathVariable int id,
            @RequestBody Actor updatedActor) {

        for (Actor actor : actors) {

            if (actor.getId() == id) {

                if (updatedActor.getName() != null) {
                    actor.setName(updatedActor.getName());
                }

                if (updatedActor.getMovieName() != null) {
                    actor.setMovieName(updatedActor.getMovieName());
                }

                return actor;
            }
        }

        return null;
    }

    // DELETE
    @DeleteMapping("/actor/{id}")
    public String deleteActor(@PathVariable int id) {

        for (Actor actor : actors) {

            if (actor.getId() == id) {

                actors.remove(actor);

                return "Actor deleted successfully";
            }
        }

        return "Actor not found";
    }
}