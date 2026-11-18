package com.site.controller;

import com.site.model.Actor;
import com.site.Service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ActorController {

    @Autowired
    private ActorService actorService;

    @GetMapping("/actors")
    public List<Actor> getAllActors() {
        return actorService.getAllActors();
    }

    @GetMapping("/actor/{id}")
    public Actor getActorById(@PathVariable Long id) {
        return actorService.getActorById(id);
    }

    @PostMapping("/actor")
    public Actor createActor(@RequestBody Actor actor) {
        return actorService.createActor(actor);
    }

    @PutMapping("/actor/{id}")
    public Actor updateActor(
            @PathVariable Long id,
            @RequestBody Actor actor) {
        return actorService.updateActor(id, actor);
    }

    @PatchMapping("/actor/{id}")
    public Actor patchActor(
            @PathVariable Long id,
            @RequestBody Actor actor) {
        return actorService.patchActor(id, actor);
    }

    @DeleteMapping("/actor/{id}")
    public String deleteActor(@PathVariable Long id) {
        return actorService.deleteActor(id);
    }
}
