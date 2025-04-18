package br.eng.eaa.app_user.controller;

import br.eng.eaa.app_user.dto.LoginDTO;
import br.eng.eaa.app_user.dto.UsuarioDTO;
import br.eng.eaa.app_user.entity.Usuario;
import br.eng.eaa.app_user.service.UsuarioService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/v1/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

    private static final Logger logger = Logger.getLogger(UsuarioController.class.getName());

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Tag(name = "Listar Usuários")
    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> findAllUsuarios(@RequestParam("page") int page, @RequestParam("size") int size) {
        logger.info("Acessando o endpoint /api/v1/usuarios");
        List<UsuarioDTO> usuarios = usuarioService.findAllUsuarios(page, size);
        return ResponseEntity.ok(usuarios);
    }

    @Tag(name = "Buscar Usuário por ID")
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> getByIdUsuarios(@PathVariable Long id) {
        logger.info("Acessando o endpoint /api/v1/usuarios/" + id);
        var usuario = usuarioService.getByIdUsuario(id);
        return ResponseEntity.ok(usuario);
    }

    @Tag(name = "Salvar Usuário")
    @PostMapping
    public ResponseEntity<Void> saveUsuario(@Valid @RequestBody Usuario usuario) {
        logger.info("POST -> /api/v1/usuarios");
        usuarioService.saveUsuario(usuario);
        var status = HttpStatus.CREATED;
        return ResponseEntity.status(status).build();
    }

    @Tag(name = "Login")
    @PostMapping("/login")
    public ResponseEntity<Void> validarSenhaUsuario(@Valid @RequestBody LoginDTO loginDTO) {
        logger.info("POST -> /api/v1/usuarios/login" + loginDTO.login());
        usuarioService.validarSenhaUsuario(loginDTO);
        var status = HttpStatus.ACCEPTED;
        return ResponseEntity.status(status.value()).build();
    }

    @Tag(name = "Deletar Usuário")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long id) {
        logger.info("DELETE -> /api/v1/usuarios/" + id);
        usuarioService.deleteUsuario(id);
        var status = HttpStatus.NO_CONTENT;
        return ResponseEntity.status(status.value()).build();
    }

    @Tag(name = "Atualizar Usuário")
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        logger.info("PUT -> /api/v1/usuarios/" + id);
        usuarioService.updateUsuario(id, usuario);
        var status = HttpStatus.NO_CONTENT;
        return ResponseEntity.status(status.value()).build();
    }
}
