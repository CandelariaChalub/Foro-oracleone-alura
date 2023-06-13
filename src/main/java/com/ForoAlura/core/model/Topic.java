package com.ForoAlura.core.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "topicos", uniqueConstraints = {@UniqueConstraint(columnNames = {"titulo","mensaje"})})
@Entity
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    private LocalDateTime fechaCreacion;

    @Enumerated(EnumType.STRING)
    private StatusTopico status;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JsonIgnore
    private Author autor;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "topico", cascade = CascadeType.ALL)
    private List<Response> respuestas;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JsonIgnore
    private Course curso;

    @PrePersist
    public void prePersist() {
        if (fechaCreacion == null) {
            fechaCreacion = LocalDateTime.now();
        }

        if (status == null) {
            status = StatusTopico.NO_RESPONDIDO;
        }
    }


}
