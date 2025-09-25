package br.com.senai.entregas.Service;

import br.com.senai.entregas.Repository.TipoUsuarioRepository;
import br.com.senai.entregas.model.TipoUsuario;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoUsuarioService {




    //injeção de dependencias
    private final TipoUsuarioRepository tipoUsuarioRepository;
    public TipoUsuarioService( TipoUsuarioRepository tipoUsuarioRepository) {
        this.tipoUsuarioRepository = tipoUsuarioRepository;
    }

    //listar todos
    public List<TipoUsuario> listarTipoUsuario()
    {
        return tipoUsuarioRepository.findAll();
    }

    //buscar por id
    public TipoUsuario buscarTipoUsuario(int id){
        return tipoUsuarioRepository.findById(id).orElse(null);
    }

    //adicionar
    public TipoUsuario adicionarTipoUsuario(TipoUsuario tipoUsuario){
        return tipoUsuarioRepository.save(tipoUsuario);
    }

    //atualizar
    public TipoUsuario atualizarTipoUsuario(int id, TipoUsuario editTipoUsuario){
        TipoUsuario tipoUsuarioExistente = buscarTipoUsuario(id);
        if(tipoUsuarioExistente == null){
            return null;
        }
        tipoUsuarioExistente.setDescricao(editTipoUsuario.getDescricao());
        return tipoUsuarioRepository.save(tipoUsuarioExistente);
    }

    //deletar
    public TipoUsuario DeletarTipoUsuario(int id){
        TipoUsuario tipoUsuarioExistente = buscarTipoUsuario(id);
        if(tipoUsuarioExistente == null){
            return null;
        }
        tipoUsuarioRepository.deleteById(id);
        return tipoUsuarioExistente;
    }
}
