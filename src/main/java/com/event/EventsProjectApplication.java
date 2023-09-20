package com.event;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EventsProjectApplication implements CommandLineRunner {


	public static final Log LOGGER = LogFactory.getLog(EventsProjectApplication.class);


	public static void main(String[] args) {
		SpringApplication.run(EventsProjectApplication.class, args);
	}
	@Bean
	public ModelMapper getModelMapper() {
		return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {
		//addRoom();
		//addEvent();
		//getEvents();
		//getEventById(1);
		//updateEvent(1,2);
		//deleteEvent(1);
	}

//	public void addRoom() {
//		try{
//			RoomDTO roomDTO = new RoomDTO();
//			roomDTO.setRoomName("Sala 3");
//			roomDTO.setNumberOfSeats(700);
//
//			eventRoomService.addRoom(roomDTO);
//		} catch (Exception e){
//			LOGGER.info(e.getMessage());
//		}
//
//	}
//	public void addEvent(){
//		try{
//			EventDTO eventDTO = new EventDTO();
//			eventDTO.setEventName("Concert Vama Veche");
//
//			eventRoomService.addEvent(eventDTO,2);
//		} catch (Exception e){
//			LOGGER.info(e.getMessage());
//
//		}
//	}
//	public void getEvents(){
//		try{
//			System.out.println(eventRoomService.getEvents());
//		} catch (Exception e) {
//			LOGGER.info(e.getMessage());
//		}
//	}
//
//	public void getEventById(Integer id){
//		try{
//			System.out.println(eventRoomService.getEventById(id).toString());
//		} catch (Exception e) {
//			LOGGER.info(e.getMessage());
//		}
//	}
//	public void updateEvent(Integer eventId, Integer roomId){
//		try{
//			eventRoomService.updateEvent(eventId, roomId);
//		} catch (Exception e) {
//			LOGGER.info(e.getMessage());
//
//		}
//	}
//
//	public void deleteEvent(Integer eventId){
//		try{
//			eventRoomService.deleteEvent(eventId);
//		} catch (Exception e) {
//			LOGGER.info(e.getMessage());
//		}
//	}
//	public void addReservation() throws EventException {
//		ReservationDTO reservationDTO = new ReservationDTO();
//		reservationPersonService.addReservation(reservationDTO, 1,2);
//	}
}
