package br.com.ifpb.depsback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ifpb.depsback.model.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {}
