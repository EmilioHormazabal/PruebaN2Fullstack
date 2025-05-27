package com.duoc.hospital.service;

import com.duoc.hospital.model.Atencion;
import com.duoc.hospital.repository.AtencionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AtencionService {

    @Autowired
    private AtencionRepository atencionRepository;

    public List<Atencion> getAllAtenciones() {
        return atencionRepository.findAll();
    }

    public Optional<Atencion> findById(int id) {
        return atencionRepository.findById(id);
    }

    public Atencion save(Atencion atencion){
        return atencionRepository.save(atencion);
    }

    public void deleteById(int id) {
        atencionRepository.deleteById(id);
    }

    public List<Atencion> findAll() {
        return atencionRepository.findAll();
    }

    // Buscar atenciones por fecha exacta
    public List<Atencion> findByFecha(Date fecha) {
        return atencionRepository.findByFecha(fecha);
    }

    // Buscar atenciones entre dos fechas
    public List<Atencion> findByFechaBetween(Date desde, Date hasta) {
        return atencionRepository.findByFechaAtencionBetween(desde, hasta);
    }

    // Buscar atenciones con costo menor a X
    public List<Atencion> findByCostoMenorA(int costo) {
        return atencionRepository.findByCostoLessThan(costo);
    }

    // Buscar atenciones con costo mayor a X
    public List<Atencion> findByCostoMayorA(int costo) {
        return atencionRepository.findByCostoGreaterThan(costo);
    }

    // Buscar atenciones por ID de médico
    public List<Atencion> findByMedicoId(int idMedico) {
        return atencionRepository.findByMedicoId(idMedico);
    }

    // Buscar atenciones por ID de paciente
    public List<Atencion> findByPacienteId(int idPaciente) {
        return atencionRepository.findByPacienteId(idPaciente);
    }

    // Buscar atenciones por estado
    public List<Atencion> findByEstado(String estado) {
        return atencionRepository.findByEstado(estado);
    }

    // Ganancia total por atenciones con estado "Alta"
    public int calcularGananciaTotalAlta() {
        List<Atencion> atenciones = atencionRepository.findByEstado("Alta");
        return atenciones.stream().mapToInt(Atencion::getCosto).sum();
    }

    // Metodo para calcular el costo total de atención de un paciente considerando la cobertura de previsión
    public int calcularCostoTotalPaciente(int idPaciente) {
        List<Atencion> atenciones = atencionRepository.findAtencionesByPacienteId(idPaciente);
        return (int) Math.round(atenciones.stream()
                .mapToDouble(a -> {
                    double cobertura = 0.0;
                    try {
                        cobertura = Double.parseDouble(a.getPaciente().getPrevision().getCobertura().replace("%", "")) / 100.0;
                    } catch (Exception e) {
                        cobertura = 0.0;
                    }
                    return a.getCosto() * (1 - cobertura);
                })
                .sum());
    }
}