package org.webonise.springmvc.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.webonise.springmvc.Entities.Team;

import java.util.List;

public interface TeamRepository extends CrudRepository<Team, Integer> {
    public List<Team> findByLabel(String team);
}
