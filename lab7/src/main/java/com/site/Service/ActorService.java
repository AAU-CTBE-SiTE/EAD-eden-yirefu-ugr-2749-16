package com.site.Service;

import com.site.model.Actor;
import com.site.repo.ActorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActorService {

    @Autowired
    private ActorRepo actorRepo;

    public List<Actor> getAllActors() {
        return actorRepo.findAll();
    }

    public Actor getActorById(Long id) {
        return actorRepo.findById(id).orElse(null);
    }

    public Actor createActor(Actor actor) {
        actor.setId(null);
        return actorRepo.save(actor);
    }

    public Actor updateActor(Long id, Actor updatedActor) {
        Actor actor = actorRepo.findById(id).orElse(null);

        if (actor == null) {
            return null;
        }

        actor.setName(updatedActor.getName());
        actor.setMovieName(updatedActor.getMovieName());

        return actorRepo.save(actor);
    }

    public Actor patchActor(Long id, Actor updatedActor) {
        Actor actor = actorRepo.findById(id).orElse(null);

        if (actor == null) {
            return null;
        }

        if (updatedActor.getName() != null) {
            actor.setName(updatedActor.getName());
        }

        if (updatedActor.getMovieName() != null) {
            actor.setMovieName(updatedActor.getMovieName());
        }

        return actorRepo.save(actor);
    }

    public String deleteActor(Long id) {
        if (!actorRepo.existsById(id)) {
            return "Actor not found";
        }
        actorRepo.deleteById(id);
        return "Actor deleted successfully";
    }
}
