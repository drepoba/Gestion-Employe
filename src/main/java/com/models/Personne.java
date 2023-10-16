package com.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;

@Entity
@Data
@AllArgsConstructor
public class Personne {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;

    @Column(nullable = true)
    private String posteActuel;

    @OneToOne
    @JoinColumn(name = "userId")
    private User user;

    public Personne(Personne personne) {
    }
    public Personne() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPosteActuel() {
        return posteActuel;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    // Ajoutez le getter pour la date de naissance
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setPosteActuel(String posteActuel) {
        this.posteActuel = posteActuel;
    }
    

    public void setId(Long id) {
        this.id = id;
    }
}
