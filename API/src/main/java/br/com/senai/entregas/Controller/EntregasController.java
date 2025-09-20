package br.com.senai.entregas.Controller;


import br.com.senai.entregas.Service.EntregasService;
import br.com.senai.entregas.model.Entregas;
import br.com.senai.entregas.model.Usuario;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Entregas")
public class EntregasController {

    //injeção de depencencias
    private final EntregasService entregasService;
    public EntregasController(EntregasService entregasService) {
        this.entregasService = entregasService;
    }

    //Listar
    @GetMapping
    public ResponseEntity<List<Entregas>> listarEntregas() {
        List<Entregas> entregas = entregasService.listarTudo();
        return ResponseEntity.ok().body(entregas);
    }

    //Buscar
    @GetMapping("/{id}")
    public ResponseEntity<Entregas> buscarEntrega(@PathVariable int id) {
        Entregas EntregaExistente = entregasService.BuscarEntrega(id);
        return ResponseEntity.ok().body(EntregaExistente);
    }

    //adicionar
    @PostMapping ("/{id}")
    public  ResponseEntity<Entregas> adicionarEntrega(@RequestBody Entregas newEntregas) {
        entregasService.adicionarEntrega(newEntregas);
        return ResponseEntity.ok().body(newEntregas);
    }

    //Atulizar / editar
    @PutMapping("/{id}")
    public ResponseEntity<?> editarEntrega(@RequestBody Entregas editEntregas, @PathVariable int id) {
        Entregas entregaExistente = entregasService.atualizarEntrega(id, editEntregas);
        if  (entregaExistente == null) {
            return ResponseEntity.badRequest().body("erro, etrega nao encontrada");
        }
        return ResponseEntity.ok().body(entregaExistente);
    }

    //Delatar
    @DeleteMapping("/{id}")
    public ResponseEntity<?> removerEntrega(@PathVariable int id) {
        Entregas entregasexistente = entregasService.removerEntrega(id);
        if (entregasexistente == null) {
            return ResponseEntity.badRequest().body("entrega nao encontrada");
        }
        entregasService.removerEntrega(id);
        return ResponseEntity.ok().body(entregasexistente);
    }


}
