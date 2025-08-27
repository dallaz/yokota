package com.sistema.yokota.controllers;

import com.sistema.yokota.model.Usuario;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.sistema.yokota.service.UsuarioService;

import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

   private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // Listar todos
//    @GetMapping
//    public ResponseEntity<List<Usuario>> listarUsuarios() {
//        return ResponseEntity.ok(usuarios);
//    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarUsuario(@PathVariable Long id) {
        Optional<Usuario> usuario = usuarioService.buscarPorId(id);

        return usuarioService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Criar novo usuário
    @PostMapping
    public ResponseEntity<Usuario> criarUsuario(@RequestBody Usuario usuario) {
        usuarioService.salvar(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
    }

    // Atualizar usuário
//    @PutMapping("/{id}")
//    public ResponseEntity<Usuario> atualizarUsuario(@PathVariable Long id,
//                                                    @RequestBody Usuario usuarioAtualizado) {
//        Optional<Usuario> usuarioExistente = usuarios.stream()
//                .filter(u -> u.getId().equals(id))
//                .findFirst();
//
//        if (usuarioExistente.isPresent()) {
//            Usuario u = usuarioExistente.get();
//            u.setNome(usuarioAtualizado.getNome());
//            u.setEmail(usuarioAtualizado.getEmail());
//            return ResponseEntity.ok(u);
//        }
//
//        return ResponseEntity.notFound().build();
//    }

    // Deletar usuário
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deletarUsuario(@PathVariable Long id) {
//        boolean removido = usuarios.removeIf(u -> u.getId().equals(id));
//        return removido ? ResponseEntity.noContent().build()
//                : ResponseEntity.notFound().build();
//    }
}
