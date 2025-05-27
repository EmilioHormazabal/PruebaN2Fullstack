package com.duoc.hospital.config;

import com.duoc.hospital.model.Especialidad;
import com.duoc.hospital.model.Estado;
import com.duoc.hospital.model.Prevision;
import com.duoc.hospital.repository.EspecialidadRepository;
import com.duoc.hospital.repository.EstadoRepository;
import com.duoc.hospital.repository.PrevisionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializar implements CommandLineRunner {

    @Autowired
    private EspecialidadRepository especialidadRepository;

    @Autowired
    private PrevisionRepository previsionRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    @Override
    public void run(String... args) throws Exception {
        // Especialidad base
        if (!especialidadRepository.existsByNombre("MEDICINA GENERAL")) {
            especialidadRepository.save(new Especialidad(0, "MEDICINA GENERAL", "Medicina General", null));
        }
        // Previsiones base
        if (!previsionRepository.existsByNombre("FONASA")) {
            previsionRepository.save(new Prevision(0, "FONASA", "50%", null));
        }
        if (!previsionRepository.existsByNombre("ISAPRE")) {
            previsionRepository.save(new Prevision(0, "ISAPRE", "60%", null));
        }
        // Estados base
        if (!estadoRepository.existsByNombre("Alta")) {
            estadoRepository.save(new Estado(0, "Alta", "Paciente en estado libre", null));
        }
        if (!estadoRepository.existsByNombre("Pendiente")) {
            estadoRepository.save(new Estado(0, "Pendiente", "Paciente a espera de ser atendido", null));
        }
        if (!estadoRepository.existsByNombre("Hospitalizado")) {
            estadoRepository.save(new Estado(0, "Hospitalizado", "Paciente hospitalizado", null));
        }
    }
}