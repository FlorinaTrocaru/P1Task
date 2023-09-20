package com.event.api;

import com.event.dto.RepresentationDTO;
import com.event.entity.Representation;
import com.event.exception.EventException;
import com.event.service.RepresentationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(value = "/representation")

public class RepresentationController {
    RepresentationService representationService;

    public RepresentationController(RepresentationService representationService) {
        this.representationService = representationService;
    }

    @PostMapping(value = "/event/{eventId}")
    public ResponseEntity<Representation> createRepresentation(@RequestBody RepresentationDTO representationDTO, @PathVariable Integer eventId) throws EventException {
        Representation representation = representationService.createRepresentation(representationDTO, eventId);
        return new ResponseEntity<>(representation, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<RepresentationDTO>> getRepresentations(){
        List<RepresentationDTO> list = representationService.getRepresentations();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @GetMapping(value = "/rep")
    @CrossOrigin(origins = "*")
    public ResponseEntity<RepresentationDTO> getRepresentation(@RequestParam(name = "id") Integer id) throws EventException {
        RepresentationDTO representationDTO = representationService.getRepresentationById(id);
        return new ResponseEntity<>(representationDTO, HttpStatus.OK);
    }
    @GetMapping(value = "/date/{date}")
    public ResponseEntity<List<Representation>> getRepresentationByDate(@PathVariable String date){
        List<Representation> list = representationService.getRepresentationByDate(date);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @PatchMapping(value = "/{id}")
    public ResponseEntity<Representation> updateRepresentation(@PathVariable Integer id, @RequestBody RepresentationDTO representationDTO) throws EventException {
        Representation representation = representationService.updateRepresentation(id, representationDTO);
        return new ResponseEntity<>(representation, HttpStatus.OK);
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Representation> deleteRepresentation(@PathVariable Integer id) throws EventException {
        Representation representation = representationService.deleteRepresentation(id);
        return new ResponseEntity<>(representation, HttpStatus.OK);
    }
}
