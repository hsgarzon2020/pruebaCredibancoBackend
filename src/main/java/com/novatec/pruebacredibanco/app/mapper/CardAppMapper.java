package com.novatec.pruebacredibanco.app.mapper;

import com.novatec.pruebacredibanco.app.dtos.CardDto;
import com.novatec.pruebacredibanco.app.dtos.TransactionDto;
import com.novatec.pruebacredibanco.app.dtos.request.ActivateCardRequest;
import com.novatec.pruebacredibanco.app.dtos.request.CreateCardRequest;
import com.novatec.pruebacredibanco.app.dtos.request.DeleteCardRequest;
import com.novatec.pruebacredibanco.app.dtos.request.GetCardRequest;
import com.novatec.pruebacredibanco.app.dtos.response.CardResponse;
import com.novatec.pruebacredibanco.app.dtos.response.TransactionResponse;
import com.novatec.pruebacredibanco.domain.entity.CardDomain;
import com.novatec.pruebacredibanco.domain.entity.TransactionDomain;
import com.novatec.pruebacredibanco.domain.util.Util;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CardAppMapper {

    CardDomain mappingCardDomain(CardDto cardDto);

    CardDto mappingCardDomain(CardDomain cardDomain);

    List<CardDto> mappingCardDomain(List<CardDomain> cardDomain);
    @Mapping(target = "maskedPan", source = "pan", qualifiedByName = "maskedPan")
    List<CardResponse> mappingCardDtoToResponse(List<CardDto> cardDto);

    @Mapping(target = "maskedPan", source = "pan", qualifiedByName = "maskedPan")
    CardResponse mappingCardDtoToResponse(CardDto cardDto);

    @Named("maskedPan")
    static String panToMaskedPan(Long pan){
        return Util.maskedPan(pan);
    }

    default CardDto toCardDto(CreateCardRequest createCardRequest){
        CardDto cardDto = new CardDto();
        cardDto.setPan(createCardRequest.getPan());
        cardDto.setHolder(createCardRequest.getHolder());
        cardDto.setId(createCardRequest.getId());
        cardDto.setPhone(createCardRequest.getPhone());
        cardDto.setCardType(createCardRequest.getCardType());
        return cardDto;
    }

    default CardDto toCardDto(ActivateCardRequest activateCardRequest){
        CardDto cardDto = new CardDto();
        cardDto.setHashId(activateCardRequest.getHashId());
        cardDto.setValidationNumber(Integer.parseInt(activateCardRequest.getValidationNumber()));
        return cardDto;
    }

    default CardDto toCardDto(GetCardRequest getCardRequest){
        CardDto cardDto = new CardDto();
        cardDto.setHashId(getCardRequest.getHashId());
        return cardDto;
    }

    default CardDto toCardDto(DeleteCardRequest deleteCardRequest){
        CardDto cardDto = new CardDto();
        cardDto.setHashId(deleteCardRequest.getHashId());
        return cardDto;
    }

}
