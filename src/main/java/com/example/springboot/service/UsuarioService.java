package com.example.springboot.service;

import com.example.springboot.model.Usuario;
import com.example.springboot.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> getUsuarioById(Long id) {
        return usuarioRepository.findById(id);
    }

    public Usuario saveUsuario(Usuario usuario) {
        usuario.setSenha(getMd5Hash(usuario.getSenha()));
        return usuarioRepository.save(usuario);
    }

    public void teste(String teste){
        System.out.println("TESTEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEe " + getMd5Hash(teste));
    }

    public void saveAllUsuarios(List<Usuario> usuarios) {
        usuarios.forEach(usuario -> {
            usuario.setSenha(getMd5Hash(usuario.getSenha()));
        });
        usuarioRepository.saveAll(usuarios);
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

    public List<Usuario> buscarPorNome(String nome) {
        return usuarioRepository.findByNomeLikeIgnoreCase(nome);
    }

    public void deleteUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    public static String getMd5Hash(String input){  
        try{  
            //static getInstance() method is called with hashing MD5  
            MessageDigest md = MessageDigest.getInstance("MD5");  
            //calculating message digest of an input that return array of byte  
            byte[] messageDigest = md.digest(input.getBytes());  
            //converting byte array into signum representation  
            BigInteger no = new BigInteger(1, messageDigest);  
            //converting message digest into hex value  
            String hashtext = no.toString(16);  
            while (hashtext.length() < 32) {  
hashtext = "0" + hashtext;  
}  
                return hashtext;  
        }catch(Exception e){
            e.printStackTrace();
        }
        return input;
    }     

}