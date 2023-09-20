package com.event;

import com.event.dto.EventDTO;
import com.event.entity.Event;
import com.event.exception.EventException;
import com.event.repository.EventRepository;
import com.event.repository.RoomRepository;
import com.event.service.EventService;
import com.event.service.impl.EventServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
class EventsProjectApplicationTests {
	@Autowired
	RoomRepository roomRepository;
	ModelMapper modelMapper = new ModelMapper();
	@Mock
	EventRepository eventRepository;
	@InjectMocks
	EventService eventService = new EventServiceImpl(eventRepository, roomRepository, modelMapper);

	@Test
	void getEventById() throws EventException {
		EventDTO eventDTO = new EventDTO();
		eventDTO.setName("Vocea Romaniei");
		eventDTO.setType("concurs");

		Event event = new Event();
		event.setType("concurs");
		event.setName("Vocea Romaniei");

		Mockito.when(eventRepository.findById(1)).thenReturn(Optional.of(event));

		EventDTO eventDTO1 = eventService.getEventById(1);

		Assertions.assertEquals(eventDTO1,eventDTO);
	}
	@Test
	void checkException(){
		Mockito.when(eventRepository.findById(4)).thenReturn(Optional.empty());
		Assertions.assertThrows(EventException.class, () -> eventService.getEventById(4));
	}

}
