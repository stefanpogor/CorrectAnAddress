package com.example.CorrectAddress.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table
public class State implements Serializable {

    @Id
    @SequenceGenerator(
            name = "state_sequence",
            sequenceName = "state_sequence",
            initialValue = 2111,
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "state_sequence"
    )
    private Long id;
    private String name;
    private Long countryId;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "stateId", referencedColumnName = "id")
    private List<City> cities;

    public State() {
    }

    public State(String name, Long countryId) {
        this.name = name;
        this.countryId = countryId;
    }

    public State(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public State(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    @Override
    public String toString() {
        return "State{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", countryId=" + countryId +
                '}';
    }
}
