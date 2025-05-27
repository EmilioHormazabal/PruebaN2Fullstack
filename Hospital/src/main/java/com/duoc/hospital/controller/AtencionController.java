package com.duoc.hospital.controller;

import com.duoc.hospital.model.Atencion;
import com.duoc.hospital.service.AtencionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/atenciones")
public class AtencionController {

    @Autowired
    private AtencionService atencionservice;

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @GetMapping
    public ResponseEntity<List<Atencion>> listar() {
        List<Atencion> atenciones = atencionservice.findAll();
        if (atenciones.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(atenciones);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Atencion> buscar(@PathVariable Integer id) {
        Optional<Atencion> atencion = atencionservice.findById(id);
        return atencion.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Atencion> guardar(@RequestBody Atencion atencion) {
        Atencion newAtencion = atencionservice.save(atencion);
        return ResponseEntity.status(HttpStatus.CREATED).body(newAtencion);
    }

    // Buscar atenciones por fecha exacta
    @GetMapping("/fecha/{fecha}")
    public ResponseEntity<List<Atencion>> buscarPorFecha(@PathVariable String fecha) {
        try {
            Date fechaDate = dateFormat.parse(fecha);
            List<Atencion> atenciones = atencionservice.findByFecha(fechaDate);
            if (atenciones.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return ResponseEntity.ok(atenciones);
        } catch (ParseException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // Buscar atenciones entre dos fechas
    @GetMapping("/fecha")
    public ResponseEntity<List<Atencion>> buscarEntreFechas(
            @RequestParam String desde,
            @RequestParam String hasta) {
        try {
            Date desdeDate = dateFormat.parse(desde);
            Date hastaDate = dateFormat.parse(hasta);
            List<Atencion> atenciones = atencionservice.findByFechaBetween(desdeDate, hastaDate);
            if (atenciones.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return ResponseEntity.ok(atenciones);
        } catch (ParseException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // Buscar atenciones con costo menor a X
    @GetMapping("/costo/menor/{costo}")
    public ResponseEntity<List<Atencion>> buscarCostoMenor(@PathVariable int costo) {
        List<Atencion> atenciones = atencionservice.findByCostoMenorA(costo);
        if (atenciones.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(atenciones);
    }

    // Buscar atenciones con costo mayor a X
    @GetMapping("/costo/mayor/{costo}")
    public ResponseEntity<List<Atencion>> buscarCostoMayor(@PathVariable int costo) {
        List<Atencion> atenciones = atencionservice.findByCostoMayorA(costo);
        if (atenciones.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(atenciones);
    }

    // Buscar atenciones por ID de médico
    @GetMapping("/medico/{idMedico}")
    public ResponseEntity<List<Atencion>> buscarPorMedico(@PathVariable int idMedico) {
        List<Atencion> atenciones = atencionservice.findByMedicoId(idMedico);
        if (atenciones.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(atenciones);
    }

    // Buscar atenciones por ID de paciente
    @GetMapping("/paciente/{idPaciente}")
    public ResponseEntity<List<Atencion>> buscarPorPaciente(@PathVariable int idPaciente) {
        List<Atencion> atenciones = atencionservice.findByPacienteId(idPaciente);
        if (atenciones.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(atenciones);
    }

    // Buscar atenciones por estado
    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<Atencion>> buscarPorEstado(@PathVariable String estado) {
        List<Atencion> atenciones = atencionservice.findByEstado(estado);
        if (atenciones.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(atenciones);
    }

    // Ganancia total por atenciones con estado "Alta"
    @GetMapping("/ganancia/alta")
    public ResponseEntity<Integer> gananciaTotalAlta() {
        int ganancia = atencionservice.calcularGananciaTotalAlta();
        return ResponseEntity.ok(ganancia);
    }
}