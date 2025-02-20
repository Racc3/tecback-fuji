package br.com.fujideia.iesp.tecback.controller;

import br.com.fujideia.iesp.tecback.Client.ViaCepClient;
import br.com.fujideia.iesp.tecback.model.dto.EnderecoDTO;
import br.com.fujideia.iesp.tecback.model.dto.FilmeDTO;
import br.com.fujideia.iesp.tecback.service.FilmeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/filmes")
@RequiredArgsConstructor
@Slf4j
public class FilmeController {

    private final FilmeService filmeService;
    private final ViaCepClient viaCepClient;

    @GetMapping("/cep/{cep}")
    public ResponseEntity<EnderecoDTO>
            getEndereco(@PathVariable String cep){
        var endereco = viaCepClient.buscarEnderecoPorCep(cep);
        return ResponseEntity.ok(endereco);
    }
    @GetMapping
    public ResponseEntity<List<FilmeDTO>> listarTodos() {
        log.info("Chamando listarTodos no FilmeController");
        List<FilmeDTO> filmes = filmeService.listarTodos();
        return ResponseEntity.ok(filmes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FilmeDTO> buscarPorId(@PathVariable Long id) {
        log.info("Chamando buscarPorId no FilmeController com id: {}", id);
        Optional<FilmeDTO> filme = filmeService.buscarPorId(id);
        return filme.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<FilmeDTO> criarFilme(@RequestBody FilmeDTO filmeDTO) {
        log.info("Chamando criarFilme no FilmeController com dados: {}", filmeDTO);
        FilmeDTO filmeCriado = filmeService.criarFilme(filmeDTO);
        return ResponseEntity.status(201).body(filmeCriado); // 201 Created
    }

    @PutMapping("/{id}")
    public ResponseEntity<FilmeDTO> atualizarFilme(@PathVariable Long id, @RequestBody FilmeDTO filmeDTO) {
        log.info("Chamando atualizarFilme no FilmeController com id: {} e dados: {}", id, filmeDTO);
        Optional<FilmeDTO> filmeAtualizado = filmeService.atualizarFilme(id, filmeDTO);
        return filmeAtualizado.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarFilme(@PathVariable Long id) {
        log.info("Chamando deletarFilme no FilmeController com id: {}", id);
        boolean deletado = filmeService.deletarFilme(id);
        if (deletado) {
            log.info("Filme com id: {} deletado com sucesso", id);
            return ResponseEntity.noContent().build();
        } else {
            log.warn("Tentativa de deletar filme com id: {} não encontrado", id);
            return ResponseEntity.notFound().build();
        }
    }
}
