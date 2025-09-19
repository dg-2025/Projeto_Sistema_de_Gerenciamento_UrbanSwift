package br.com.senai.entregas.Controller;

import br.com.senai.entregas.Service.TipoUsuarioService;
import br.com.senai.entregas.model.TipoUsuario;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/tipousuario")
public class TipoUsuarioController {
    //injeção de dependencias
    private final TipoUsuarioService tipoUsuarioService;
    public TipoUsuarioController(TipoUsuarioService tipoUsuarioService) {
        this.tipoUsuarioService = tipoUsuarioService;
    }

    //listar
    @GetMapping
    public ResponseEntity<List<TipoUsuario>> listarTods(){
        List<TipoUsuario> tipoUsuarios = tipoUsuarioService.listarTipoUsuario();
        return ResponseEntity.ok(tipoUsuarios);
    }

    //buscar por id
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarTipoUsuario(@PathVariable int id){
        TipoUsuario tipoUsuarioExistente = tipoUsuarioService.buscarTipoUsuario(id);
        if(tipoUsuarioExistente == null){
            return ResponseEntity.badRequest().body("Tipo não encotrado");
        }
        return ResponseEntity.ok(tipoUsuarioExistente);
    }

    @PostMapping
    public ResponseEntity<?> cadastrarTipoUsuario(@RequestBody TipoUsuario novoTipoUsuario){
        tipoUsuarioService.adicionarTipoUsuario(novoTipoUsuario);
        return ResponseEntity.ok().body("tipo adicionado com sucesso");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarTipoUsuario(@PathVariable int id, @RequestBody TipoUsuario novoTipoUsuario){
        TipoUsuario tipoUsuarioExistente = tipoUsuarioService.atualizarTipoUsuario(id, novoTipoUsuario);
        if(tipoUsuarioExistente == null){
            return ResponseEntity.badRequest().body("tipo não encotrado");
        }
        return ResponseEntity.ok(tipoUsuarioExistente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removerTipoUsuario(@PathVariable int id){
        TipoUsuario tipoUsuarioExistente = tipoUsuarioService.DeletarTipoUsuario(id);
        if(tipoUsuarioExistente == null){
            return ResponseEntity.badRequest().body("tipo não encotrado");
        }
        tipoUsuarioService.DeletarTipoUsuario(id);
        return ResponseEntity.ok().body("Tipo removido com sucesso");
    }
}
