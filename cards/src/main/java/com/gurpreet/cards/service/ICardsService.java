package com.gurpreet.cards.service;

import com.gurpreet.cards.dto.CardsDto;

public interface ICardsService {

    /**
     * Creates a new card for the customer associated with the given mobile number.
     *
     * This method generates a new card with default settings and associates it with
     * the specified mobile number. If a card already exists for the given mobile number,
     * a CardAlreadyExistsException is thrown.
     *
     * @param mobileNumber the mobile number of the customer for whom the card is to be created
     * @throws CardAlreadyExistsException if a card already exists for the given mobile number
     */
    void createCard(String mobileNumber);

    /**
     * Fetches the card details associated with the given mobile number.
     *
     * @param mobileNumber
     *            the mobile number of the customer whose card is to be retrieved
     * @return a CardsDto object containing the card details if found, or null if
     *         no card is associated with the given mobile number
     */
    CardsDto fetchCard(String mobileNumber);

    /**
     * Updates the card details with the information provided in the given {@link CardsDto} object.
     *
     * @param cardsDto
     *            the {@link CardsDto} object containing the updated card details.
     * @return true if the card is updated successfully, false otherwise.
     */
    boolean updateCard(CardsDto cardsDto);

    /**
     * Deletes the card associated with the given mobile number.
     *
     * This method attempts to find and delete the card associated with the specified
     * mobile number. If no such card is found, a ResourceNotFoundException is thrown.
     *
     * @param mobileNumber
     * The mobile number of the customer whose card is to be deleted.
     *
     * @return true if the card is deleted successfully, false otherwise.
     * @throws ResourceNotFoundException if no card is associated with the given mobile number
     */
    boolean deleteCard(String mobileNumber);

}
