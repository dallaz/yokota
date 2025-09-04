package com.sistema.yokota.controllers;

import com.sistema.yokota.model.Usuario;
import com.sistema.yokota.repository.UsuarioRepository;
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

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarUsuario(@PathVariable Long id) {
        Optional<Usuario> usuario = usuarioService.buscarPorId(id);

        return usuarioService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Usuario> criarUsuario(@RequestBody Usuario usuario) {
        usuarioService.salvar(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizarUsuario(@PathVariable Long id,
                                                    @RequestBody Usuario usuarioAtualizado) {

            try {
                Optional<Usuario> usuarioExistente = usuarioService.atualizar(id, usuarioAtualizado);
                if (usuarioExistente.isPresent()) {
                    return ResponseEntity.ok(usuarioExistente.get());
                } else {
                    return ResponseEntity.notFound().build();
                }

            } catch(Exception exception) {
                throw new RuntimeException(exception.getMessage(), exception.getCause());
            }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Long id) {
        boolean removido = usuarioService.deletar(id);
        return removido ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
   }
}
