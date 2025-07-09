package com.paulo.libraryapi.repository;

import com.paulo.libraryapi.model.Autor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AutorRepositoryTest {

    @Autowired
    AutorRepository repository;

    @Test
    public void salvarTest() {
        Autor autor = new Autor();
        autor.setNome("irei remover");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(3256, 4, 22));

        var autorSalvo = repository.save(autor);
        System.out.println("Autor salvo: " + autorSalvo);
    }

    @Test
    public void atualizarTest() {
        var id = UUID.fromString("8f432bc7-16af-4070-bdc7-a22e0680df5f");

        Optional<Autor> possivelAutor = repository.findById(id);

        if(possivelAutor.isPresent()) {
            Autor autorEncontrado = possivelAutor.get();
            System.out.println("Dados do Autor:");
            System.out.println(autorEncontrado);

            autorEncontrado.setDataNascimento(LocalDate.of(1839, 6, 21));

            repository.save(autorEncontrado);
        }
    }

    @Test
    public void removerTest() {

        var id = UUID.fromString("d1abd6fc-5aad-493b-8d91-91d5518a8852");
        repository.deleteById(id);
        System.out.println("Autor Removido");
    }
}
