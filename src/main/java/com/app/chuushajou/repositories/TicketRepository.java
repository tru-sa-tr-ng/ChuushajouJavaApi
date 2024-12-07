package com.app.chuushajou.repositories;

import com.app.chuushajou.models.Ticket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    @Override
    Page<Ticket> findAll(Pageable pageable);
}
