package ru.aromat.aromatTerapevt.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "grupps")
public class Gruppa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "gruppa_id")
    private Long id;
    private String name;

    @OneToMany(mappedBy = "gruppa", cascade = CascadeType.ALL)
    private List<Maslo> maslos = new ArrayList<>();


}
