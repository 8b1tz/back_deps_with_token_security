package br.com.ifpb.depsback.controller;

import java.util.List;
import br.com.ifpb.depsback.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ifpb.depsback.model.Event;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/event")
public class EventController {

	@Autowired
	private EventService eventService;

	@PostMapping("create/{idUser}")
	public Event create(@RequestBody Event event, @PathVariable long idUser) {
		return eventService.create(event, idUser);
	}

	@GetMapping("readall")
	public List<Event> findAll() {
		return eventService.findAll();
	}

	@GetMapping(path = { "read/{idEvent}" })
	public Event findById(@PathVariable long idEvent) {
		return eventService.findById(idEvent);
	}

	@PutMapping(value = "update/{idEvent}")
	public Event update(@PathVariable long idEvent, @RequestBody Event event) {
		return eventService.update(idEvent, event);
	}

	@DeleteMapping(path = { "delete/{idEvent}/{idUser}" })
	public String delete(@PathVariable long idEvent, @PathVariable long idUser) {
		return eventService.delete(idEvent, idUser);
	}
	
}
