package com.paulo.libraryapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "autor", schema = "public")
@Getter
@Setter
@ToString
public class Autor {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull(message = "Nome é obrigatório!")
    @Column(name = "nome", length = 100, nullable = false)
    private String nome;

    @NotNull(message = "Data de nascimento é obrigatório!")
    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;

    @NotNull(message = "Nacionalidade é obrigatório!")
    @Column(name = "nacionalidade", length = 50, nullable = false)
    private String nacionalidade;

//    @OneToMany(mappedBy = "autor")
    @Transient
    private List<Livro> livros;
}
