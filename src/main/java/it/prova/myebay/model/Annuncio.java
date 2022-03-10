package it.prova.myebay.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "annuncio")
public class Annuncio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "testoannuncio")
	private String testoAnnuncio;
	@Column(name = "prezzo")
	private Integer prezzo;
	@Column(name = "datepost")
	private Date datePost;
	@Column(name = "aperto")
	private boolean aperto;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "utente_id", nullable = false)
	private Utente utenteInserimento;

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinTable(name = "annucio_categoria", joinColumns = @JoinColumn(name = "annuncio_id", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "categoria_id", referencedColumnName = "ID"))
	private Set<Categoria> categorie = new HashSet<>();

	public Annuncio() {
		super();
	}

	public Annuncio(String testoAnnuncio, Integer prezzo, Utente utenteInserimento) {
		super();
		this.testoAnnuncio = testoAnnuncio;
		this.prezzo = prezzo;
		this.utenteInserimento = utenteInserimento;
	}

	public Annuncio(String testoAnnuncio, Integer prezzo, Date datePost, boolean aperto, Utente utenteInserimento) {
		super();
		this.testoAnnuncio = testoAnnuncio;
		this.prezzo = prezzo;
		this.datePost = datePost;
		this.aperto = aperto;
		this.utenteInserimento = utenteInserimento;
	}

	public Annuncio(Long id, String testoAnnuncio, Integer prezzo, Date datePost, boolean aperto,
			Utente utenteInserimento) {
		super();
		this.id = id;
		this.testoAnnuncio = testoAnnuncio;
		this.prezzo = prezzo;
		this.datePost = datePost;
		this.aperto = aperto;
		this.utenteInserimento = utenteInserimento;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTestoAnnuncio() {
		return testoAnnuncio;
	}

	public void setTestoAnnuncio(String testoAnnuncio) {
		this.testoAnnuncio = testoAnnuncio;
	}

	public Integer getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(Integer prezzo) {
		this.prezzo = prezzo;
	}

	public Date getDatePost() {
		return datePost;
	}

	public void setDatePost(Date datePost) {
		this.datePost = datePost;
	}

	public boolean isAperto() {
		return aperto;
	}

	public void setAperto(boolean aperto) {
		this.aperto = aperto;
	}

	public Utente getUtenteInserimento() {
		return utenteInserimento;
	}

	public void setUtenteInserimento(Utente utenteInserimento) {
		this.utenteInserimento = utenteInserimento;
	}

	public void addToCategorie(Categoria categoriaInstance) {
		this.categorie.add(categoriaInstance);
		categoriaInstance.getAnnunci().add(this);
	}

	public void removeFromCategorie(Categoria categoriaInstance) {
		this.categorie.remove(categoriaInstance);
		categoriaInstance.getAnnunci().remove(this);
	}
}
