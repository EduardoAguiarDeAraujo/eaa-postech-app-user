package br.eng.eaa.app_user.controller;

import br.eng.eaa.app_user.service.UsuarioService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UsuarioService usuarioService;

    @Test
    @DisplayName("Deve retornar todos os usuários")
    public void findAllUsuarios() throws Exception {
        mockMvc.perform(get("/api/v1/usuarios?page=0&size=10"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("Deve incluir um usuários")
    public void saveUsuario() throws Exception {
        String json = """
                {
                    "nome":"Katia",
                    "email":"katia@gmail.com",
                    "login":"katia.ea",
                    "senha":"304050"
                }
                """;
        mockMvc.perform(post("/api/v1/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                        .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("Deve retornar um usuário específico")
    public void findByIdUsuario() throws Exception {
        mockMvc.perform(get("/api/v1/usuarios/1"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Deve excluir um usuário")
    public void deleteUsuario() throws Exception {
        mockMvc.perform(delete("/api/v1/usuarios/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("Deve alterar um usuário")
    public void updateUsuario() throws Exception {
        String json = """
                {
                    "nome":"Eduardo",
                    "email":"katia@gmail.com",
                    "login":"katia.ea",
                    "senha":"304050"
                }
                """;
        mockMvc.perform(put("/api/v1/usuarios/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                        .andExpect(status().isNoContent());
    }

}
