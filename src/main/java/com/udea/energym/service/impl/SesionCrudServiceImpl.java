package com.udea.energym.service.impl;

import java.sql.Date;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.udea.energym.dto.Fisioterapia;
import com.udea.energym.dto.Nutricional;
import com.udea.energym.dto.Persona;
import com.udea.energym.dto.Personalizado;
import com.udea.energym.dto.Profesional;
import com.udea.energym.dto.Sesion;
import com.udea.energym.entity.FisioterapiaEntity;
import com.udea.energym.repository.ISesionRepository;
import com.udea.energym.service.ISesionCrudService;

@Service
public class SesionCrudServiceImpl implements ISesionCrudService{

    @Autowired
    ISesionRepository sesionRepository;

    @Override
    public Fisioterapia agregarFisioterapia(Fisioterapia fisioterapia) {
        FisioterapiaEntity fisioterapiaEntity = sesionRepository.save(dtoToEntity(fisioterapia));
        fisioterapia.setHora(LocalDateTime.parse(fisioterapiaEntity.getHora().toString()));
        return fisioterapia;
    }

    public Sesion[] buscarSesiones(){
        Sesion[] sesiones = new Sesion[3];
        LocalDateTime fecha = LocalDateTime.of(2022, 7, 21, 0, 0);
        LocalDateTime horaFisioterapia = LocalDateTime.of(2022, 7, 21, 10, 0);
        LocalDateTime horaNutricional = LocalDateTime.of(2022, 7, 21, 11, 0);
        LocalDateTime horaEntrenamiento = LocalDateTime.of(2022, 7, 21, 9, 0);
        Persona fisioterapeuta = new Profesional();
        fisioterapeuta.setCedula("1128269010");
        fisioterapeuta.setNombre("Nombre del fisioterapeuta");
        Persona nutricionista = new Profesional();
        nutricionista.setCedula("1000289778");
        nutricionista.setNombre("Nombre del nutricionista");
        Persona entrenador = new Profesional();
        entrenador.setCedula("42983421");
        entrenador.setNombre("Nombre del entrenador");
        sesiones[0] = new Fisioterapia(fecha, horaFisioterapia, (Profesional) fisioterapeuta);
        sesiones[1] = new Nutricional(fecha, horaNutricional, (Profesional) nutricionista);
        sesiones[2] = new Personalizado(fecha, horaEntrenamiento, (Profesional) nutricionista);
        return sesiones;
    }

    private FisioterapiaEntity dtoToEntity(Fisioterapia fisioterapia) {
        FisioterapiaEntity fEntity = new FisioterapiaEntity();
        fEntity.setFecha(Date.valueOf(fisioterapia.getFecha().toLocalDate()));
        fEntity.setHora(Date.valueOf(fisioterapia.getHora().toLocalDate()));
        fEntity.setDuracion(fisioterapia.getDuracion());
        fEntity.setNumPersonas(fisioterapia.getNumPersonas());
        fEntity.setProfesional(fisioterapia.getProfesional().getCedula());
        return fEntity;
    }
}