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
@Table(name = "top_masel")
public class TopMasel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "top_masel_id")
    private Long id;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "top_masel_maslo",
            joinColumns = @JoinColumn(name = "top_masel_id"),
            inverseJoinColumns = @JoinColumn(name = "maslo_id")
    )
    private List<Maslo> maslos = new ArrayList<>();


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;


    public void setUsername(String username) {
    }
    public TopMasel(List<Maslo> maslos) {
        this.maslos = maslos;
    }

}
