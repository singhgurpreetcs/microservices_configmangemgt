package com.gurpreet.cards.mapper;

import com.gurpreet.cards.dto.CardsDto;
import com.gurpreet.cards.entity.Cards;

public class CardsMapper {

    /**
     * Maps a {@link Cards} object to a {@link CardsDto} object.
     *
     * @param cards the object to be mapped
     * @param cardsDto the object to map to
     * @return the mapped object
     */
    public static CardsDto mapToCardsDto(Cards cards, CardsDto cardsDto) {
        cardsDto.setCardNumber(cards.getCardNumber());
        cardsDto.setCardType(cards.getCardType());
        cardsDto.setMobileNumber(cards.getMobileNumber());
        cardsDto.setTotalLimit(cards.getTotalLimit());
        cardsDto.setAvailableAmount(cards.getAvailableAmount());
        cardsDto.setAmountUsed(cards.getAmountUsed());
        return cardsDto;
    }

    /**
     * Maps a {@link CardsDto} object to a {@link Cards} object.
     *
     * @param cardsDto the object to be mapped
     * @param cards the object to map to
     * @return the mapped object
     */
    public static Cards mapToCards(CardsDto cardsDto, Cards cards) {
        cards.setCardNumber(cardsDto.getCardNumber());
        cards.setCardType(cardsDto.getCardType());
        cards.setMobileNumber(cardsDto.getMobileNumber());
        cards.setTotalLimit(cardsDto.getTotalLimit());
        cards.setAvailableAmount(cardsDto.getAvailableAmount());
        cards.setAmountUsed(cardsDto.getAmountUsed());
        return cards;
    }

}