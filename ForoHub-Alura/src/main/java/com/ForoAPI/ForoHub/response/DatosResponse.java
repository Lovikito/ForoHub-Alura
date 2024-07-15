package com.ForoAPI.ForoHub.response;

import java.time.LocalDateTime;

public record DatosResponse(
        String responseTitle,
        String body,
        String author,
        LocalDateTime creationDate
) {


}
