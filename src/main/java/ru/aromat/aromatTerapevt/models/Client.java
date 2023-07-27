package ru.aromat.aromatTerapevt.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Formula;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String firstname;
	private String lastname;
	private String othername;

	@Formula(value = " concat(firstname, ' ', lastname, ' ', othername) ")
	private String fullName;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateOfBirth;
	@CreationTimestamp
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date dateIn;
	private String mobile;
	private String email;
	private String photo;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn
	private Shablon shablon;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "client_maslo",
			joinColumns = @JoinColumn(name = "client_id"),
			inverseJoinColumns = @JoinColumn(name = "maslo_id"))
	private List<Maslo> topMasels = new ArrayList<>();
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn
	private User user;
	@OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, mappedBy = "client")
	private List<Vizit> vizits = new ArrayList<>();

	@OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
	private List<Comment> comments = new ArrayList<>();

	@OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
	private List<Task> tasks = new ArrayList<>();

	public Client(Principal principal) {
	}

	public void setUsername(String username) {
	}

}
