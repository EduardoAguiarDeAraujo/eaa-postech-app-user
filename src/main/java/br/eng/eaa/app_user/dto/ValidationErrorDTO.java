package br.eng.eaa.app_user.dto;

import java.util.List;

public record ValidationErrorDTO(List<String> errors, int status) {
}
