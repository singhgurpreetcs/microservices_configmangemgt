package com.gurpreet.cards.repository;

import com.gurpreet.cards.entity.Cards;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardsRepository extends JpaRepository<Cards, Long> {

    /**
     * Finds a card by the given mobile number.
     *
     * @param mobileNumber
     *            the mobile number of the customer whose card is to be retrieved
     * @return an Optional containing the card if found, or an empty Optional if
     *         no card is associated with the given mobile number
     */
    Optional<Cards> findByMobileNumber(String mobileNumber);

    /**
     * Finds a card by the given card number.
     *
     * @param cardNumber
     *            the card number of the customer whose card is to be retrieved
     * @return an Optional containing the card if found, or an empty Optional if
     *         no card is associated with the given card number
     */
    Optional<Cards> findByCardNumber(String cardNumber);

}