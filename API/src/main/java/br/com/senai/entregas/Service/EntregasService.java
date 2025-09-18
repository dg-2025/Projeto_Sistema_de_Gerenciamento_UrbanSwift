package br.com.senai.entregas.Service;

import br.com.senai.entregas.Repository.EntregasRepository;
import br.com.senai.entregas.model.Entregas;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntregasService {
    private final EntregasRepository entregasRepository;

    public EntregasService(EntregasRepository entregasRepository) {
        this.entregasRepository = entregasRepository;
    }

    //listar
    public List<Entregas> listarTudo() {
        return entregasRepository.findAll();
    }

    //Buscar por Id
    public  Entregas BuscarEntrega(int id) {
        return entregasRepository.findById(id).orElse(null);
    }

    //Adicionar
    public Entregas adicionarEntrega(Entregas novaEntrega) {
        return entregasRepository.save(novaEntrega);
    }

    //Atualizar
    public Entregas atualizarEntrega(int id, Entregas entregaAtualizada) {
        Entregas entregaExitente = this.BuscarEntrega(id);
        if (entregaExitente == null) {
            return null;
        }
        return entregasRepository.save(entregaAtualizada);
    }

    //Excluir
    public Entregas removerEntrega(int id) {
        Entregas entregaExitente = this.BuscarEntrega(id);
        if (entregaExitente == null) {
            return null;
        }
        entregasRepository.delete(entregaExitente);
        return entregaExitente;
    }
}
