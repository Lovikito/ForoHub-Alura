package com.ForoAPI.ForoHub.domain.usuarios.foro;

public record DatosUserForo(
        Long id,
        String email,
        String userName,
        String password
) {
}
