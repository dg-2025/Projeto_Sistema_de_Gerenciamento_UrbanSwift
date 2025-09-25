package br.com.senai.entregas.Service;

import br.com.senai.entregas.Repository.UsuarioRepository;
import br.com.senai.entregas.model.Usuario;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
public class UsuarioService {

    //injeção de dependencias
    private final PasswordEncoder passwordEncoder;
    private final UsuarioRepository usuarioRepository;
    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.usuarioRepository = usuarioRepository;
    }

    //listar
    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    //Buscar por id
    public Usuario buscarPorId(Integer id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    //Adicionar
    public Usuario addUsuario(Usuario newUser) {
        String senhaCriptografada = passwordEncoder.encode(newUser.getSenha());
        newUser.setSenha( senhaCriptografada );
        return usuarioRepository.save(newUser);
    }

    //Atualizar
    public Usuario editUsuario(int id, Usuario editUser) {
        Usuario usuarioExistente = this.buscarPorId(id);
        if  (usuarioExistente == null) {
            return null;
        }
        usuarioExistente.setEmail(editUser.getEmail());
        usuarioExistente.setSenha(editUser.getSenha());
        return usuarioRepository.save(editUser);
    }

    //Excluir
    public Usuario removeUsuario(int id) {
        Usuario usuarioExistente = this.buscarPorId(id);
        if (usuarioExistente == null) {
            return null;
        }
        usuarioRepository.delete(usuarioExistente);
        return usuarioExistente;
    }
}
