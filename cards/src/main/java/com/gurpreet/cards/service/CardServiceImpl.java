package com.gurpreet.cards.service;

import com.gurpreet.cards.constants.CardsConstants;
import com.gurpreet.cards.dto.CardsDto;
import com.gurpreet.cards.entity.Cards;
import com.gurpreet.cards.exception.CardAlreadyExistsException;
import com.gurpreet.cards.exception.ResourceNotFoundException;
import com.gurpreet.cards.mapper.CardsMapper;
import com.gurpreet.cards.repository.CardsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class CardServiceImpl implements  ICardsService{

    private CardsRepository cardsRepository;

    /**
     * Creates a new card for the customer associated with the given mobile number.
     *
     * This method checks if a card already exists for the provided mobile number.
     * If a card exists, a CardAlreadyExistsException is thrown. Otherwise, a new
     * card is created with default settings and associated with the specified mobile
     * number.
     *
     * @param mobileNumber the mobile number of the customer for whom the card is to be created
     * @throws CardAlreadyExistsException if a card already exists for the given mobile number
     */
    @Override
    public void createCard(String mobileNumber) {
        Optional<Cards> optionalCards = cardsRepository.findByMobileNumber(mobileNumber);
        if(optionalCards.isPresent()){
            throw new CardAlreadyExistsException("Card already registered with given mobileNumber " + mobileNumber);
        }
        cardsRepository.save(createNewCard(mobileNumber));
    }
    /**
     * Creates a new card for the given mobile number with default settings.
     *
     * This method generates a new card object, assigns a random card number,
     * and sets default values for card type, total limit, amount used, and available amount.
     *
     * @param mobileNumber the mobile number to associate with the new card
     * @return a newly created Cards object with default settings
     */
    private Cards createNewCard(String mobileNumber){
        Cards newCard = new Cards();
        long randomCardNumber = 100000000000L + new Random().nextInt(900000000);
        newCard.setCardNumber(Long.toString(randomCardNumber));
        newCard.setMobileNumber((mobileNumber));
        newCard.setCardType(CardsConstants.CREDIT_CARD);
        newCard.setTotalLimit(CardsConstants.NEW_CARD_LIMIT);
        newCard.setAmountUsed(0);
        newCard.setAvailableAmount(CardsConstants.NEW_CARD_LIMIT);
        return newCard;
    }

    /**
     * Fetches the card details associated with the given mobile number.
     *
     * If a card is associated with the given mobile number, the method returns a CardsDto object containing the card details.
     * If no card is associated with the given mobile number, the method throws a ResourceNotFoundException.
     *
     * @param mobileNumber the mobile number of the customer whose card is to be retrieved
     * @return a CardsDto object containing the card details
     * @throws ResourceNotFoundException if no card is associated with the given mobile number
     */
    @Override
    public CardsDto fetchCard(String mobileNumber) {
        Cards cards = cardsRepository.findByMobileNumber(mobileNumber).orElseThrow(
                ()-> new ResourceNotFoundException("Card", "mobileNumber", mobileNumber)
        );
        return CardsMapper.mapToCardsDto(cards, new CardsDto());
    }

    /**
     * Updates the card details with the information provided in the given {@link CardsDto} object.
     *
     * This method attempts to find and update the card associated with the specified card number.
     * If no card is found with the given card number, a ResourceNotFoundException is thrown.
     *
     * @param cardsDto the object containing the updated card details
     * @return true if the card is updated successfully, false otherwise
     * @throws ResourceNotFoundException if no card is associated with the given card number
     */
    @Override
    public boolean updateCard(CardsDto cardsDto) {
        Cards cards = cardsRepository.findByCardNumber(cardsDto.getCardNumber()).orElseThrow(
                () -> new ResourceNotFoundException("Card", "CardNumber", cardsDto.getCardNumber())
        );
        CardsMapper.mapToCards(cardsDto, cards);
        cardsRepository.save(cards);
        return true;
    }

    /**
     * Deletes the card associated with the given mobile number.
     *
     * This method first retrieves the card associated with the given mobile number.
     * If no card is found with the given mobile number, a ResourceNotFoundException is thrown.
     * If a card is found, the method deletes the card and returns true.
     *
     * @param mobileNumber the mobile number of the customer whose card is to be deleted
     * @return true if the card is deleted successfully, false otherwise
     * @throws ResourceNotFoundException if no card is associated with the given mobile number
     */
    @Override
    public boolean deleteCard(String mobileNumber) {
        Cards cards = cardsRepository.findByMobileNumber(mobileNumber).orElseThrow(
                ()-> new ResourceNotFoundException("Card", "mobileNumber", mobileNumber)
        );
        cardsRepository.deleteById(cards.getCardId());
        return true;
    }
}
