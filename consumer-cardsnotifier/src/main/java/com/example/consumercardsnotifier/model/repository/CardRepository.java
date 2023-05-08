package com.example.consumercardsnotifier.model.repository;

import com.example.consumercardsnotifier.model.Card;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *  - This is a  CrudRepository  of the Card entity.
 *  - CrudRepository is a Spring Data interface for generic CRUD operations on a repository of a
 *    specific type. It provides several methods out of the box for interacting with a database.
 *
 */
@Repository
public interface CardRepository extends CrudRepository<Card, Long> {

    /**
     * - Get all Cards  finding by the field SentTo
     * @param sentTo Boolean
     * @return List<Card>
     */
    List<Card> findBySentTo(Boolean sentTo);
}