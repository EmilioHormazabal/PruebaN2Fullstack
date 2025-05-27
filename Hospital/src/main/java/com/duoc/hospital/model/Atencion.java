package com.duoc.hospital.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.duoc.hospital.model.Estado;
import java.time.LocalTime;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Atencion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "fecha_atencion", nullable = false)
    private Date fechaAtencion;

    @Column(name = "hora_inicio", nullable = false)
    private LocalTime horaInicio;

    @Column(columnDefinition = "integer default 0", length = 10)
    private int costo;

    @Column(nullable = true, length = 300)
    private String comentario;

    @ManyToOne
    @JoinColumn(name = "id_estado")
    private Estado estado;

    @ManyToOne
    @JoinColumn(name = "id_paciente")
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "id_medico")
    private Medico medico;
}