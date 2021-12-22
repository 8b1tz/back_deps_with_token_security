package br.com.ifpb.depsback.services;

import br.com.ifpb.depsback.model.Event;
import br.com.ifpb.depsback.model.User;
import br.com.ifpb.depsback.repository.EventRepository;
import br.com.ifpb.depsback.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserRepository userRepository;

    public Event create(Event event, long idUser) {
        Event newEvent = eventRepository.save(event);

        User user = userRepository.findById(idUser).get();
        newEvent.setUser(user);
        user.addEvent(newEvent);
        eventRepository.save(newEvent);
        userRepository.save(user);
        return newEvent;

    }

    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    public Event findById(long idEvent) {
        return eventRepository.findById(idEvent).get();
    }

    public Event update(long idEvent, Event event) {
        Event eventDb = this.findById(idEvent);
        eventDb.setContratante(event.getContratante());
        eventDb.setDescription(event.getDescription());
        eventDb.setStatus(event.getStatus());
        eventDb.setTitle(event.getTitle());
        eventDb.setDate(event.getDate());
        Event eventAtualizado = eventRepository.save(eventDb);
        return eventAtualizado;
    }

    public String delete(long idEvent, long idUser) {
        try {

            User user = userRepository.findById(idUser).get();
            Event event = eventRepository.findById(idEvent).get();

            if (user.getEmail().equals(event.getUser().getEmail())){
                user.remEvent(event);
                userRepository.save(user);
                eventRepository.deleteById(idEvent);
                return "Deletado";
            }else{
                return "Apenas quem criou, pode deletar ! ";
            }
        }catch (Exception e){
            return "Ocorreu um problema :" + e.getMessage();
        }
    }

}
