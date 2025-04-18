package br.eng.eaa.app_user.dto;

import br.eng.eaa.app_user.entity.Endereco;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public record UsuarioDTO(
        @Schema(description = "ID do usuário")
        Long id,
        @Schema(description = "Nome do usuário")
        String nome,
        @Schema(description = "Email do usuário")
        String email,
        @Schema(description = "Data de atualização do usuário")
        String dataAtualizacao,
        @Schema(description = "Endereco do usuário")
        Endereco endereco,
        @Schema(description = "Perfil do usuário")
        String perfil
) {}
