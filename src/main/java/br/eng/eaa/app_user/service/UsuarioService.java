package br.eng.eaa.app_user.service;

import br.eng.eaa.app_user.dto.LoginDTO;
import br.eng.eaa.app_user.dto.UsuarioDTO;
import br.eng.eaa.app_user.entity.Usuario;
import br.eng.eaa.app_user.exception.ResourceNotFoundException;
import br.eng.eaa.app_user.exception.UnauthorizedException;
import br.eng.eaa.app_user.repository.UsuarioRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<UsuarioDTO> findAllUsuarios(int page, int size) {

        Pageable pageRequest = PageRequest.of(page, size);
        List<UsuarioDTO> usuarios = usuarioRepository.findAll(pageRequest)
                .stream()
                .map(usuario -> new UsuarioDTO(
                        usuario.getId(),
                        usuario.getNome(),
                        usuario.getEmail(),
                        usuario.getDataAtualizacao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")),
                        usuario.getEndereco(),
                        usuario.getPerfil().name()))
                .collect(Collectors.toList());
        return usuarios;
    }

    public void saveUsuario(Usuario usuario) {
        usuario.setDataAtualizacao(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));
        usuarioRepository.save(usuario);
    }

    public UsuarioDTO getByIdUsuario(Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));
        UsuarioDTO usuarioDTO = new UsuarioDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getDataAtualizacao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")),
                usuario.getEndereco(),
                usuario.getPerfil().name()
        );
        return usuarioDTO;
    }

    public void deleteUsuario(Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));
        usuarioRepository.delete(usuario);
    }

    public void updateUsuario(Long id, Usuario usuario) {
        Usuario usuarioExistente = usuarioRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));
        usuarioExistente.setId(id);
        usuarioExistente.setNome(usuario.getNome());
        usuarioExistente.setEmail(usuario.getEmail());
        usuarioExistente.setLogin(usuario.getLogin());
        usuarioExistente.setSenha(usuario.getSenha());
        usuarioExistente.setDataAtualizacao(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));
        usuarioExistente.setEndereco(usuario.getEndereco());
        usuarioExistente.setPerfil(usuario.getPerfil());
        usuarioRepository.save(usuarioExistente);
    }

    public void validarSenhaUsuario(LoginDTO loginDTO) {
        Usuario usuario = usuarioRepository.findByLogin(loginDTO.login()).orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));
        if (usuario.getSenha().equals(loginDTO.senha())) {
            // Senha válida
        } else {
            throw new UnauthorizedException("Senha inválida");
        }
    }
}
