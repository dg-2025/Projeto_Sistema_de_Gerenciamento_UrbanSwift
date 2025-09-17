package br.com.senai.entregas.Controller;

import br.com.senai.entregas.Service.UsuarioService;
import br.com.senai.entregas.model.Usuario;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    private final UsuarioService usuarioService;
    public UsuarioController(UsuarioService service) {
        this.usuarioService = service;
    }

    //listar
    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuarios(){
        List<Usuario> usuarios = usuarioService.listarTodos();
        return ResponseEntity.ok(usuarios);
    }

    //Buscar por id
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarUsuarioPorId(@PathVariable int id){
        Usuario usuarioExistente = usuarioService.buscarPorId(id);
        if(usuarioExistente == null){
            return ResponseEntity.badRequest().body("usuario não encotrado");
        }
        return ResponseEntity.ok(usuarioExistente);
    }

    //Adicionar
    @PostMapping("/{id}")
    public ResponseEntity<?> adicionarUsuario (@RequestBody Usuario novoUsuario){
        usuarioService.addUsuario(novoUsuario);
        return ResponseEntity.badRequest().body("Usuario adicionado com sucesso");
    }

    //Editar / Atualizar
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarUsuario(@RequestBody Usuario usuarioUpdate, @PathVariable int id){
        Usuario usuarioExistente = usuarioService.editUsuario(id, usuarioUpdate);
        if(usuarioExistente == null){
            return ResponseEntity.badRequest().body("usuario não encotrado");
        }
        return ResponseEntity.ok(usuarioExistente);
    }

    //deletar
    @DeleteMapping("/{id}")
    public ResponseEntity<?> removerUsuario(@PathVariable int id){
        Usuario usuarioExistente = usuarioService.buscarPorId(id);
        if(usuarioExistente == null){
            return ResponseEntity.badRequest().body("usuario não encotrado");
        }
        usuarioService.removeUsuario(id);
        return ResponseEntity.ok().body("Usuario removido com sucesso");
    }



}
