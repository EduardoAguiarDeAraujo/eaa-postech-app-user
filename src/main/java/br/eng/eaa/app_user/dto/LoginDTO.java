package br.eng.eaa.app_user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public record LoginDTO (
        @Schema(description = "Login do usuário", example = "eduardo")
        @NotNull(message = "Login não pode ser nulo")
        String login,
        @Schema(description = "Senha do usuário", example = "123456")
        @NotNull(message = "Senha não pode ser nula")
        String senha) {
}
