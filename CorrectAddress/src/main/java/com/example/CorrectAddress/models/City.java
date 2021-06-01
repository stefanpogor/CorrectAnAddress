package com.example.CorrectAddress.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table
public class City implements Serializable {

    @Id
    @SequenceGenerator(
            name = "city_sequence",
            sequenceName = "city_sequence",
            initialValue = 31111,
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "city_sequence"
    )
    private Long id;
    private String name;
    private Long stateId;

    public City() {
    }

    public City(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public City(String name, Long stateId) {
        this.name = name;
        this.stateId = stateId;
    }

    public City(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getStateId() {
        return stateId;
    }

    public void setStateId(Long stateId) {
        this.stateId = stateId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", stateId=" + stateId +
                '}';
    }
}
