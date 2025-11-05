package com.paulo.libraryapi.repository;

import com.paulo.libraryapi.model.Autor;
import com.paulo.libraryapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AutorRepositoryTest {

    @Autowired
    AutorRepository repository;

    @Autowired
    LivroRepository livroRepository;

    @Test
    public void salvarTest() {
        Autor autor = new Autor();
        autor.setNome("Machado de Assis");
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
    public void listarTest() {
        List<Autor> lista = repository.findAll();
        lista.forEach(System.out::println);
    }

    @Test
    public void countTest() {
        System.out.println("Contagem de autores: " + repository.count());
    }

    @Test
    public void deletePorIdTest() {

        var id = UUID.fromString("8f432bc7-16af-4070-bdc7-a22e0680df5f");
        repository.deleteById(id);
        System.out.println("Autor Removido");
    }

    @Test
    void listarLivroAutor() {
        try {
            var id = UUID.fromString("526174f2-4d81-414b-8062-cdc334c755e1");
            var autor = repository.findById(id).orElse(null);
            if (autor != null) {
                List<Livro> livrosLista = livroRepository.findByAutor(autor);
                autor.setLivros(livrosLista);

                if (autor.getLivros() != null && !autor.getLivros().isEmpty()) {
                    autor.getLivros().forEach(livro -> System.out.println(livro.getTitulo()));
                } else {
                    System.out.println("Nenhum livro encontrado para este autor.");
                }
            } else {
                System.out.println("Autor não encontrado.");
            }
        }
        catch (IllegalArgumentException e) {
            System.out.println("ID inválido fornecido.");
        } catch (Exception e) {
            System.out.println("Ocorreu um erro ao listar os livros do autor: " + e.getMessage());
        }
    }
}
