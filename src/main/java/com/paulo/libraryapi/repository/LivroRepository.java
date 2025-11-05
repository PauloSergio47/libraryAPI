package com.paulo.libraryapi.repository;

import com.paulo.libraryapi.model.Autor;
import com.paulo.libraryapi.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface LivroRepository extends JpaRepository<Livro, UUID> {

    //Query methods

    List<Livro> findByAutor(Autor autor);
}
