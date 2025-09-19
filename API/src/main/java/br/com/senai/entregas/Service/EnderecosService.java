package br.com.senai.entregas.Service;

import br.com.senai.entregas.Repository.EnderecosRepository;
import br.com.senai.entregas.model.Enderecos;
import br.com.senai.entregas.model.Usuario;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnderecosService {

    //injeção de dependencias
    private final EnderecosRepository enderecosRepository;
    public EnderecosService(EnderecosRepository enderecosRepository) {
        this.enderecosRepository = enderecosRepository;
    }

    //listar
    public List<Enderecos> listarTodos() {
        return enderecosRepository.findAll();
    }

    //Buscar por ID
    public Enderecos buscarPorId(int id) {
        return enderecosRepository.findById(id).orElse(null);
    }

    //Adicionar
    public Enderecos adicionarEndereco(Enderecos endereco) {
        return enderecosRepository.save(endereco);
    }

    //Atualizar
    public Enderecos atualizarEndereco(int id, Enderecos endereco) {
        Enderecos enderecoExistente = this.buscarPorId(id);
        if (enderecoExistente == null) {
            return null;
        }
        enderecoExistente.setLogradouro(endereco.getLogradouro());
        return enderecosRepository.save(enderecoExistente);
    }

    //Remover
    public Enderecos removerEndereco(int id) {
        Enderecos enderecoExistente = this.buscarPorId(id);
        if (enderecoExistente == null) {
            return null;
        }
        enderecosRepository.delete(enderecoExistente);
        return enderecoExistente;
    }
}