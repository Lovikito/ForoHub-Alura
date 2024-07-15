package com.ForoAPI.ForoHub.domain;

import java.time.LocalDateTime;

public record DatosTopic(Long id,
                       String title,
                       String body,
                       String courseName,
                       String author,
                       LocalDateTime creationDate,
                       String estado ) {

}
