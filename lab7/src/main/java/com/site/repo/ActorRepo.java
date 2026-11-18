package com.site.repo;

import com.site.model.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActorRepo extends JpaRepository<Actor, Long> {

}

