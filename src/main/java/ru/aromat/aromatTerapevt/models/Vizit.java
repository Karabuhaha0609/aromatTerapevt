package ru.aromat.aromatTerapevt.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode
@Table(name = "vizit")
public class Vizit {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "vizit_id")
	private Long id;

	@ElementCollection
	@CollectionTable(name = "vizit_reakcia",
			joinColumns = {@JoinColumn(name = "vizit_id")})
	@MapKeyJoinColumn(name = "maslo_id")
	@Column(name = "reakcia")
	private Map<Maslo, String> reakciaMaslo = new HashMap<>();

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataVstrech;

	@ManyToOne
	@JoinColumn(name = "client_id")
	private Client client;
}

