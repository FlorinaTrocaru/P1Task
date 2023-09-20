package com.event.service.impl;

import com.event.dto.RepresentationDTO;
import com.event.entity.Event;
import com.event.entity.Representation;
import com.event.exception.EventException;
import com.event.repository.EventRepository;
import com.event.repository.RepresentationRepository;
import com.event.service.RepresentationService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RepresentationServiceImpl implements RepresentationService {

    RepresentationRepository representationRepository;
    EventRepository eventRepository;
    ModelMapper modelMapper;

    public RepresentationServiceImpl(RepresentationRepository representationRepository, EventRepository eventRepository, ModelMapper modelMapper) {
        this.representationRepository = representationRepository;
        this.eventRepository = eventRepository;
        this.modelMapper = modelMapper;
    }
    @Override
    public Representation createRepresentation(RepresentationDTO representationDTO, Integer id) throws EventException {

        Optional<Event> optionalEvent = eventRepository.findById(id);
        Event event = optionalEvent.orElseThrow(() -> new EventException("Nu exista acest eveniment"));




        Representation representation = modelMapper.map(representationDTO, Representation.class);
        representation.setEvent(event);

        representationRepository.save(representation);
        return representation;
    }

    @Override
    public List<RepresentationDTO> getRepresentations() {

        Iterable<Representation> list = representationRepository.findAll();
        List<RepresentationDTO> listDTO = new ArrayList<>();
        for(Representation r : list) {
            RepresentationDTO representationDTO = modelMapper.map(r, RepresentationDTO.class);
            listDTO.add(representationDTO);
        }
        return listDTO;
    }

    @Override
    public RepresentationDTO getRepresentationById(Integer id) throws EventException {
        Optional<Representation> representationOptional = representationRepository.findById(id);
        Representation representation = representationOptional.orElseThrow(() -> new EventException("Aceasta reprezentare nu exista!"));

        RepresentationDTO representationDTO = modelMapper.map(representation, RepresentationDTO.class);

        return representationDTO;
    }

    @Override
    public List<Representation> getRepresentationByDate(String date) {
        List<Representation> list = representationRepository.findByDate(date);

        return list;
    }

    @Override
    public Representation updateRepresentation(Integer id, RepresentationDTO representationDTO) throws EventException {
        Optional<Representation> representationOptional = representationRepository.findById(id);
        Representation representation = representationOptional.orElseThrow(() -> new EventException("Aceasta reprezentatie nu exista!"));

        if(representationDTO.getDateTime() != null) representation.setDateTime(representationDTO.getDateTime());
        if(representationDTO.getPrice() != null) representation.setPrice(representationDTO.getPrice());

        return representation;
    }

    @Override
    public Representation deleteRepresentation(Integer id) throws EventException {
        Optional<Representation> representationOptional = representationRepository.findById(id);
        Representation representation = representationOptional.orElseThrow(() -> new EventException("Aceasta reprezentatie nu exista!"));

        representationRepository.delete(representation);
        return representation;
    }

}
