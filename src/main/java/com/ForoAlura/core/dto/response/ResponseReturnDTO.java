package com.ForoAlura.core.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseReturnDTO {
    private String mensaje;
    private LocalDateTime fechaCreacion;
    private Boolean solucion;
}
