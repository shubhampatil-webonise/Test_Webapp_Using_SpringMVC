package org.webonise.springmvc.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;

    @ManyToMany
    private List<Team> teams;

    public Employee() {
        super();
    }

    public Employee(String name, List<Team> teams) {
        this.name = name;
        this.teams = teams;
    }

    public int getId() {
        return this.id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setTeamList(List<Team> teams) {
        this.teams = teams;
    }

    public List<Team> getTeamList() {
        return this.teams;
    }

    public boolean isMember(Team team) {
        for (Team currentTeam : this.getTeamList()) {
            if (currentTeam.getId() == team.getId()) {
                return true;
            }
        }

        return false;
    }
}
