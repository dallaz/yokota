package service;

import com.sistema.yokota.model.Usuario;
import org.springframework.stereotype.Service;
import repository.UsuarioRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public List<Usuario> listarTodos() {
        return repository.findAll();
    }

    public Optional<Usuario> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public Usuario salvar(Usuario usuario) {
        return repository.save(usuario);
    }

    public Optional<Usuario> atualizar(Long id, Usuario usuarioAtualizado) {
        return repository.findById(id).map(u -> {
            u.setNome(usuarioAtualizado.getNome());
            u.setEmail(usuarioAtualizado.getEmail());
            return repository.save(u);
        });
    }

    public boolean deletar(Long id) {
        return repository.findById(id).map(u -> {
            repository.delete(u);
            return true;
        }).orElse(false);
    }
}

