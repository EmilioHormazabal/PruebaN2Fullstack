package com.duoc.hospital.repository;

import com.duoc.hospital.model.Atencion;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AtencionRepository extends JpaRepository<Atencion, Integer> {
    @Query("SELECT a FROM Atencion a WHERE a.fechaAtencion = :fecha")
    List<Atencion> findByFecha(@Param("fecha") Date fecha);

    List<Atencion> findByFechaAtencionBetween(Date desde, Date hasta);

    @Query("SELECT a FROM Atencion a WHERE a.fechaAtencion BETWEEN :fechaInicio AND :fechaFin")
    List<Atencion> findBetweenFechas(@Param("fechaInicio") Date fechaInicio, @Param("fechaFin") Date fechaFin);

    List<Atencion> findByCostoLessThan(int costo);

    @Query("SELECT a FROM Atencion a WHERE a.costo < :costo")
    List<Atencion> findByCostoMenor(@Param("costo") int costo);

    List<Atencion> findByCostoGreaterThan(int costo);

    @Query("SELECT a FROM Atencion a WHERE a.costo > :costo")
    List<Atencion> findByCostoMayor(@Param("costo") int costo);

    @Query("SELECT a FROM Atencion a WHERE a.medico.id = :idMedico")
    List<Atencion> findByMedicoId(@Param("idMedico") int idMedico);

    @Query("SELECT a FROM Atencion a WHERE a.paciente.id = :idPaciente")
    List<Atencion> findByPacienteId(@Param("idPaciente") int idPaciente);

    //@Query("SELECT SUM(a.costo) FROM Atencion a WHERE a.estado = 'Alta'")
    //int findGananciaTotal();
    // Revisar metodo en clases mañana

    @Query("SELECT a FROM Atencion a WHERE a.estado = :estado")
    List<Atencion> findByEstado(@Param("estado") String estado);

    @Query("SELECT a FROM Atencion a WHERE a.paciente.id = :idPaciente")
    List<Atencion> findAtencionesByPacienteId(@Param("idPaciente") int idPaciente);

    // Sueldo total de un médico
    @Query("SELECT SUM(a.costo * 0.2) + m.sueldoBase FROM Atencion a JOIN a.medico m WHERE m.id = :idMedico")
    Double calcularSueldoTotalMedico(@Param("idMedico") int idMedico);
}