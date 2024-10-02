package com.tasktime.springboot.service;

import com.tasktime.springboot.dto.UsuarioDTO;
import com.tasktime.springboot.model.Empresa;
import com.tasktime.springboot.model.Usuario;
import com.tasktime.springboot.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<UsuarioDTO> getAllUsuarios() {
        return fromEntityList(usuarioRepository.findAll());
    }

    public List<UsuarioDTO> getAllUsuarios(Empresa empresa) {
        return fromEntityList(usuarioRepository.findByEmpresa(empresa));
    }

    public List<Usuario> getUsuarioByEmail() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> getUsuarioById(Long id) {
        return usuarioRepository.findById(id);
    }

    public Usuario findByEmail(String email){
        return usuarioRepository.findByEmail(email);
    }


    public Usuario saveUsuario(Usuario usuario) {
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        usuario.setDataCadastro(new Date());
        return usuarioRepository.save(usuario);
    }

    public Usuario updateUsuario(Long id, Usuario updatedUsuario) {
        Optional<Usuario> existingUsuario = usuarioRepository.findById(id);
        if (existingUsuario.isPresent()) {

            existingUsuario.get().setNome(updatedUsuario.getNome());
            existingUsuario.get().setEmail(updatedUsuario.getEmail());

            return usuarioRepository.save(existingUsuario.get());
        }
        return null;
    }

    public Usuario atualizarSenha(Usuario usuario){
        usuario.setDataAlteracao(new Date());
        usuario.setNeedUpdatePass(false);
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> buscarPorNome(String nome) {
        return usuarioRepository.findByNomeLikeIgnoreCase(nome);
    }

    public void deleteUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    public static UsuarioDTO fromEntity(Usuario usuario) {
        if (usuario == null) {
            return null;
        }

        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setIdUsuario(usuario.getIdUsuario());
        usuarioDTO.setNome(usuario.getNome());
        usuarioDTO.setEmail(usuario.getEmail());
        usuarioDTO.setTipoUsuario(usuario.getTipoUsuario());
        usuarioDTO.setIdUsuarioQueCadastrou(usuario.getIdUsuarioQueCadastrou());
        usuarioDTO.setNeedUpdatePass(usuario.getNeedUpdatePass());
        usuarioDTO.setEmpresa(usuario.getEmpresa());
        usuarioDTO.setDataCadastro(usuario.getDataCadastro());
        usuarioDTO.setDataAlteracao(usuario.getDataAlteracao());

        return usuarioDTO;
    }

    public static List<UsuarioDTO> fromEntityList(List<Usuario> usuarios) {
        return usuarios == null ? List.of() : usuarios.stream()
                .map(UsuarioService::fromEntity)
                .collect(Collectors.toList());
    }

   

}