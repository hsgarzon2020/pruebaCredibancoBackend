package com.novatec.pruebacredibanco.app.service;

import com.novatec.pruebacredibanco.app.dtos.CardDto;
import com.novatec.pruebacredibanco.app.dtos.request.*;
import com.novatec.pruebacredibanco.app.dtos.response.*;
import com.novatec.pruebacredibanco.app.mapper.CardAppMapper;
import com.novatec.pruebacredibanco.domain.service.CardServiceDomain;
import com.novatec.pruebacredibanco.domain.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardAppService {

    @Autowired
    CardServiceDomain cardServiceDomain;

    @Autowired
    CardAppMapper cardAppMapper;

    public CreateCardResponse createCard(CreateCardRequest createCardRequest)
            throws Exception {

        CreateCardResponse createCardResponse = new CreateCardResponse();
        CardDto cardDto = cardAppMapper.mappingCardDomain(
                cardServiceDomain.createCard(cardAppMapper.mappingCardDomain(cardAppMapper.toCardDto(createCardRequest))));
        createCardResponse.setValidationNumber(cardDto.getValidationNumber());
        createCardResponse.setResponseCode(Util.CODE_SUCCESS);
        createCardResponse.setResponseMessage(Util.MESSAGE_SUCCESS);
        createCardResponse.setMaskedPan(Util.maskedPan(cardDto.getPan()));
        createCardResponse.setHashId(cardDto.getHashId());
        return createCardResponse;
    }

    public ActivateCardResponse activateCard(ActivateCardRequest activateCardRequest)
            throws Exception {
        ActivateCardResponse activateCardResponse = new ActivateCardResponse();
        CardDto cardDto = cardAppMapper.mappingCardDomain(
                cardServiceDomain.activateCard(cardAppMapper.mappingCardDomain(
                        cardAppMapper.toCardDto(activateCardRequest))));
        activateCardResponse.setResponseCode(Util.CODE_SUCCESS);
        activateCardResponse.setResponseMessage(Util.MESSAGE_SUCCESS);
        activateCardResponse.setMaskedPan(Util.maskedPan(cardDto.getPan()));
        return activateCardResponse;
    }

    public CardResponse getCard(GetCardRequest getCardRequest)
            throws Exception {
        CardResponse cardResponse = cardAppMapper.mappingCardDtoToResponse(
                cardAppMapper.mappingCardDomain(
                cardServiceDomain.getCard(cardAppMapper.mappingCardDomain(
                cardAppMapper.toCardDto(getCardRequest)))));
        cardResponse.setResponseCode(Util.CODE_SUCCESS);
        cardResponse.setResponseMessage(Util.MESSAGE_SUCCESS);
        return cardResponse;
    }

    public GetAllCardsResponse getCards()
            throws Exception {
        GetAllCardsResponse getAllCardsResponse = new GetAllCardsResponse();
        getAllCardsResponse.setData(cardAppMapper.mappingCardDtoToResponse(
                cardAppMapper.mappingCardDomain(cardServiceDomain.getCards())));
        getAllCardsResponse.setResponseCode(Util.CODE_SUCCESS);
        getAllCardsResponse.setResponseMessage(Util.MESSAGE_SUCCESS);

        return getAllCardsResponse;
    }


    public DeleteCardResponse deleteCard(DeleteCardRequest deleteCardRequest)
            throws Exception {
        DeleteCardResponse deleteCardResponse = new DeleteCardResponse();
        CardDto cardDto = cardAppMapper.mappingCardDomain(
                cardServiceDomain.deleteCard(cardAppMapper.mappingCardDomain(cardAppMapper.toCardDto(deleteCardRequest))));
        deleteCardResponse.setResponseCode(Util.CODE_SUCCESS);
        deleteCardResponse.setResponseMessage(Util.MESSAGE_SUCCESS);
        return deleteCardResponse;
    }

}
