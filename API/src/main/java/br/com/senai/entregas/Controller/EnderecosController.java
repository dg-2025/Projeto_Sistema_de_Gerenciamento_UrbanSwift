package br.com.senai.entregas.Controller;

import br.com.senai.entregas.Service.EnderecosService;
import br.com.senai.entregas.model.Enderecos;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enderecos")
public class EnderecosController {

    //injeção de dependencias
    private final EnderecosService enderecosService;
    public EnderecosController(EnderecosService enderecosService) {
        this.enderecosService = enderecosService;
    }

    //listar
    @GetMapping
    public ResponseEntity<List<Enderecos>> ListarEnderecos() {
        List<Enderecos> enderecos = enderecosService.listarTodos();
        return ResponseEntity.ok().body(enderecos);
    }

    //buscar po Id
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarEnderecoPorId(@PathVariable int id) {
        Enderecos enderecoExistente = enderecosService.buscarPorId(id);
        if (enderecoExistente == null) {
            return ResponseEntity.badRequest().body("endereco não encotrado");
        }
        return ResponseEntity.ok(enderecoExistente);
    }

    //Adicionar
    @PostMapping("/{id}")
    public ResponseEntity<?> adicionarEndereco(@RequestBody Enderecos newEndereco) {
        enderecosService.adicionarEndereco(newEndereco);
        return ResponseEntity.badRequest().body("endereco adicionado com sucesso");
    }

    //Atualizar
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarEndereco(@RequestBody Enderecos updateEndereco, @PathVariable int id) {
        Enderecos enderecoExistente = enderecosService.buscarPorId(id);
        if (enderecoExistente == null) {
            return ResponseEntity.badRequest().body("endereco não encotrado");
        }
        return ResponseEntity.ok().body("endereco Atualizado com sucesso");
    }

    //deletar
    @DeleteMapping("/{id}")
    public ResponseEntity<?> removerEndereco(@PathVariable int id) {
        Enderecos  enderecoExistente = enderecosService.buscarPorId(id);
        if (enderecoExistente == null) {
            return ResponseEntity.badRequest().body("endereco não encotrado");
        }
        enderecosService.removerEndereco(id);
        return ResponseEntity.ok().body("endereco removido com sucesso");
    }


}
