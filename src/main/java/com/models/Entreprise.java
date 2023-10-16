package com.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Data
public class Entreprise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Getter
    private String companyname;

    public Entreprise(Long id, String companyname) {
        this.id = id;
        this.companyname = companyname;
    }

    public Entreprise(Long id) {
        this.id = id;
    }

    public Entreprise() {
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyname() {
        return companyname;
    }
}
