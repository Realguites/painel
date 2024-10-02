package com.tasktime.springboot.controller;

import com.tasktime.springboot.dto.UpdatePasswordRequest;
import com.tasktime.springboot.dto.UsuarioDTO;
import com.tasktime.springboot.enums.TipoUsuario;
import com.tasktime.springboot.model.Usuario;
import com.tasktime.springboot.service.UsuarioService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    @PreAuthorize("hasAuthority('Manager') or hasAuthority('Gerente')")
    public ResponseEntity<List<UsuarioDTO>> getAllModels() {
        Usuario usuarioLogado = getUsuarioLogado();
        if (usuarioLogado.getTipoUsuario().equals(TipoUsuario.GERENTE)) {
            return ResponseEntity.ok(usuarioService.getAllUsuarios(usuarioLogado.getEmpresa()));
            
        }else if(usuarioLogado.getTipoUsuario().equals(TipoUsuario.MANAGER)){
            return ResponseEntity.ok(usuarioService.getAllUsuarios());
        }   
        return null;
    }

    private Usuario getUsuarioLogado() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Jwt jwt = (Jwt) authentication.getPrincipal();
        String emailUsuario = jwt.getClaim("sub");
        return usuarioService.findByEmail(emailUsuario);
    }

    //@GetMapping("/{id}")
    public ResponseEntity<Optional<Usuario>> getUsuarioById(@PathVariable Long id) {
        Optional<Usuario> usuario = usuarioService.getUsuarioById(id);
        return usuario != null ? ResponseEntity.ok(usuario) : ResponseEntity.notFound().build();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('Manager') or hasAuthority('Gerente')")
    public ResponseEntity<Usuario> saveUsuario(@RequestBody Usuario usuario) {
        Usuario usuarioLogado = getUsuarioLogado();
        usuario.setIdUsuarioQueCadastrou(usuarioLogado.getIdUsuario());
        if(usuarioService.findByEmail(usuario.getEmail()) != null)
            return ResponseEntity.status(HttpStatus.CONFLICT).build();//usuario ja cadastrado
        if(usuarioLogado.getTipoUsuario().equals(TipoUsuario.FUNCIONARIO))
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();//se ele for funcionario
        if (usuarioLogado.getTipoUsuario().equals(TipoUsuario.GERENTE)) {
            usuario.setEmpresa(usuarioLogado.getEmpresa());
            if(usuario.getTipoUsuario().equals(TipoUsuario.MANAGER)){//gerente não pode cadastrar um manager
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        }   
        
        Usuario savedUsuario = usuarioService.saveUsuario(usuario);
            
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUsuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> updateUsuario(@PathVariable("id") Long id, @RequestBody Usuario newUsuario) {
        Usuario updatedUsuario = usuarioService.updateUsuario(id, newUsuario);
        return updatedUsuario != null ? ResponseEntity.ok(updatedUsuario) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable("id") Long id) {
        usuarioService.deleteUsuario(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/updatePassword")
    public ResponseEntity<?> updatePasswordUsuario(@RequestBody UpdatePasswordRequest data) {
        Usuario usuarioLogado = getUsuarioLogado();
        if (!passwordEncoder.matches(data.getCurrentPassword(), usuarioLogado.getSenha())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Senha atual incorreta");
        }
        usuarioLogado.setSenha(passwordEncoder.encode(data.getNewPassword()));
        usuarioService.atualizarSenha(usuarioLogado);
        
        return ResponseEntity.ok("Senha atualizada com sucesso");
    }
    
   
}