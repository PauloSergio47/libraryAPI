package com.paulo.libraryapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "livros")
@Data

public class Livro {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull(message = "ISBN é obrigatório!")
    @Column(name = "isbn", length = 20, nullable = false)
    private String isbn;

    @NotNull(message = "Título é obrigatório!")
    @Column(name = "titulo", length = 200, nullable = false)
    private String titulo;

    @NotNull(message = "Data de publicação é obrigatório!")
    @Column(name = "data_publicacao", nullable = false)
    private LocalDate dataPublicacao;

    @Column(name = "preco", precision = 18, scale = 2)
    private BigDecimal preco;

    @NotNull(message = "Género é obrigatorio!")
    @Enumerated(EnumType.STRING)
    @Column(name = "genero", length = 30, nullable = false)
    private GeneroLivro genero;

    @ManyToOne
    @JoinColumn(name = "id_autor")
    private Autor autor;
}
