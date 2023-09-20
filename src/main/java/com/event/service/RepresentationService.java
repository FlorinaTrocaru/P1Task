package com.event.service;

import com.event.dto.RepresentationDTO;
import com.event.entity.Representation;
import com.event.exception.EventException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface RepresentationService {
    public Representation createRepresentation(RepresentationDTO representationDTO, Integer id) throws EventException;
    public List<RepresentationDTO> getRepresentations();
    public RepresentationDTO getRepresentationById(Integer id) throws EventException;
    public List<Representation> getRepresentationByDate(String date);
    public Representation updateRepresentation(Integer id, RepresentationDTO representationDTO) throws EventException;
    public Representation deleteRepresentation(Integer id) throws EventException;
}
