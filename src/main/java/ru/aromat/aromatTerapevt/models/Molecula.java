package ru.aromat.aromatTerapevt.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "molecules")
public class Molecula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "molecule_id")
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "molecules")
    private List<Maslo> maslos = new ArrayList<>();
}