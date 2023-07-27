package ru.aromat.aromatTerapevt.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "shablon")
public class Shablon {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "shablon_id")
    private Long id;
    private String name;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "shablon_maslo",
            joinColumns = @JoinColumn(name = "shablon_id", referencedColumnName = "shablon_id"),
            inverseJoinColumns = @JoinColumn(name = "maslo_id", referencedColumnName = "maslo_id")
              )
    private List<Maslo> maslos = new ArrayList<>();


    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, mappedBy = "shablon")
    private List<Client> clients;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn
    private User user;

    public  Shablon (Principal principal) {
    }
    public void setUsername(String username) {
    }

}
