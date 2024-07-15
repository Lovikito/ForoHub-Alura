package com.ForoAPI.ForoHub.domain.usuarios;

import jakarta.validation.constraints.NotBlank;

public record DatosAuthenticationUser(
        @NotBlank
        String login,
        @NotBlank
        String key) {
}
