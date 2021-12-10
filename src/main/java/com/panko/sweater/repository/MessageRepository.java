package com.panko.sweater.repository;

import com.panko.sweater.entity.Message;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends CrudRepository<Message, Long> {
    @Override
    Iterable<Message> findAll();

    Iterable<Message> findByTag(String tag);
}
