
package com.communityratesgames.domain;

import java.io.Serializable;
import javax.persistence.*;

import java.util.List;
import java.util.ArrayList;

import com.communityratesgames.model.GameModel;
import com.communityratesgames.model.PlatformModel;

@Entity
@Table(name = "platform_entity")
public class Platform implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(length=80)
    private String name;

    private int releaseYear;

    @ManyToOne
    @JoinColumn
    private Company company;

    @ManyToMany(mappedBy="platforms")
    private List<Game> games;

    @ManyToMany(mappedBy="platforms")
    private List<UnverifiedGame> unverifiedGames;

    public Platform(String name, int releaseYear, Company company, List<Game> games) {
        this.name = name;
        this.releaseYear = releaseYear;
        this.company = company;
        this.games = games;
    }

    protected Platform() { }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public int getReleaseYear() {
        return this.releaseYear;
    }

    public Company getCompany() {
        return this.company;
    }

    public List<Game> getGames() {
        return this.games;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    public List<UnverifiedGame> getUnverifiedGames() {
        return unverifiedGames;
    }

    public void setUnverifiedGames(List<UnverifiedGame> unverifiedGames) {
        this.unverifiedGames = unverifiedGames;
    }
}
