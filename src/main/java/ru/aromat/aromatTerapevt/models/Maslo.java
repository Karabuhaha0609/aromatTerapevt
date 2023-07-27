package ru.aromat.aromatTerapevt.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode
public class Maslo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "maslo_id")
    private Long id;
    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "gruppa_id")
    private Gruppa gruppa;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "maslos")
    private List<Shablon> shablonList = new ArrayList<>();


    @ManyToMany(mappedBy = "maslos", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TopMasel> topMasels = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "maslo_molecule",
            joinColumns = {@JoinColumn(name = "maslo_id")},
            inverseJoinColumns = {@JoinColumn(name = "molecule_id")}
    )
    private List<Molecula> molecules = new ArrayList<>();


}
