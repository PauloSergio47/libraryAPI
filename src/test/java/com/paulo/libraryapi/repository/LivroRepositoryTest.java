package com.paulo.libraryapi.repository;

import com.paulo.libraryapi.model.Autor;
import com.paulo.libraryapi.model.GeneroLivro;
import com.paulo.libraryapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class LivroRepositoryTest {

    @Autowired
    LivroRepository repository;

    @Autowired
    AutorRepository autorRepository;

    @Test
    void salvarTest() {
        Livro livro = new Livro();
        livro.setIsbn("7587-8534");
        livro.setPreco(BigDecimal.valueOf(150,00));
        livro.setGenero(GeneroLivro.FANTASIA);
        livro.setTitulo("Outro Livro");
        livro.setDataPublicacao(LocalDate.of(1931, 7, 12));

        Autor autor = autorRepository
                .findById(UUID.fromString("0275165f-70f4-410f-867c-5b7edd65f62d"))
                .orElse(null);

        livro.setAutor(new Autor());

        repository.save(livro);
    }

    @Test
    void salvarCascadeTest() {
        Livro livro = new Livro();
        livro.setIsbn("7587-8534");
        livro.setPreco(BigDecimal.valueOf(150,00));
        livro.setGenero(GeneroLivro.FANTASIA);
        livro.setTitulo("Outro Livro");
        livro.setDataPublicacao(LocalDate.of(1931, 7, 12));

        Autor autor = new Autor();
        autor.setNome("João");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(3256, 4, 22));

        livro.setAutor(autor);

        repository.save(livro);
    }

    @Test
    void atualizarAutorDoLivroTest() {
        UUID id = UUID.fromString("44b4c794-6a30-45b7-90b0-0e92d9652711");
        var livroParaAtualizar = repository.findById(id).orElse(null);

        UUID idAutor = UUID.fromString("526174f2-4d81-414b-8062-cdc334c755e1");
        Autor autor = autorRepository.findById(idAutor).orElse(null);

        livroParaAtualizar.setAutor(autor);

        repository.save(livroParaAtualizar);
    }

    @Test
    void deletar() {
        UUID id = UUID.fromString("44b4c794-6a30-45b7-90b0-0e92d9652711");
        repository.deleteById(id);
    }

    @Test
    void pesquisarPorTituloTest() {
        List<Livro> lista = repository.findByTituloIgnoreCase("O picapau amarelo");
        if(lista.isEmpty()) {
            System.out.println("Nenhum livro encontrado com esse título.");
            return;
        }
        lista.forEach(System.out::println);

    }

    @Test
    void pesquisarPorIsbnTest() {
        List<Livro> lista = repository.findByIsbn("8569-8569");
        if(lista.isEmpty()) {
            System.out.println("Nenhum livro encontrado com esse ISBN.");
            return;
        }
        lista.forEach(System.out::println);
    }

    @Test
    void pesquisarPorTituloAndPrecoTest() {
        var preco = BigDecimal.valueOf(100,00);
        List<Livro> lista = repository.findByTituloIgnoreCaseAndPreco("O picapau amarelo", preco);
        if(lista.isEmpty()) {
            System.out.println("Nenhum livro encontrado com esse Titulo e Preço.");
            return;
        }
        lista.forEach(System.out::println);
    }

    @Test
    void pesquisarPorTituloOrPrecoTest() {
        var isbn = "8569-8569";
        List<Livro> lista = repository.findByTituloIgnoreCaseOrIsbn("O picapau amarelo", isbn);
        if(lista.isEmpty()) {
            System.out.println("Nenhum livro encontrado com esse Titulo ou ISBN.");
            return;
        }
        lista.forEach(System.out::println);
    }

    @Test
    void listarPorGeneroQueryParamTest() {
        var resultado = repository.findByGenero(GeneroLivro.FANTASIA, "dataPublicacao");
        resultado.forEach(System.out::println);
    }

}