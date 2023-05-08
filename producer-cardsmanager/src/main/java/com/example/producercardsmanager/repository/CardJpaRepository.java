package com.example.producercardsmanager.repository;

import com.example.producercardsmanager.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Date;
import java.util.List;

/**
 *  - This is a  JpaRepository  of the Card entity.
 *  - JpaRepository is a Spring Data interface for generic CRUD operations on a repository of a
 *    specific type. It provides several methods out of the box for interacting with a database.
 */
@Repository
public interface CardJpaRepository extends JpaRepository<Card, Long> {

    /**
     * - Find all cards with a expiry Date time Before current date.
     *
     * @param expiryDate Boolean
     * @return  List<Card>
     */
    @Query("select a from card a where a.expiryDate <= :expiryDate and blocked = false")
    List<Card> findAllWithExpiryDateTimeBefore(@Param("expiryDate") Date expiryDate);

    /**
     * find all the cards by the fields blocked and sentTo as a parameters
     *
     * @param blocked Boolean
     * @param sentTo Boolean
     * @return List<Card>
     */
    List<Card> findByBlockedAndSentTo(Boolean blocked,Boolean sentTo);

    /**
     * Get the card by card as a parameter
     *
     * @param card Boolean
     * @return Card
     */
    Card findByCard(String card);
}
