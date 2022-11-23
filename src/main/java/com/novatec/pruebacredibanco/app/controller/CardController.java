package com.novatec.pruebacredibanco.app.controller;

import com.novatec.pruebacredibanco.app.dtos.request.*;
import com.novatec.pruebacredibanco.app.dtos.response.*;
import com.novatec.pruebacredibanco.app.service.CardAppService;
import com.novatec.pruebacredibanco.domain.util.CardNotFoundException;
import com.novatec.pruebacredibanco.domain.util.DuplicityException;
import com.novatec.pruebacredibanco.domain.util.Util;
import com.novatec.pruebacredibanco.domain.util.ValidateNumberInvalidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/cards")
public class CardController {

    @Autowired
    private CardAppService cardAppService;

    @PostMapping()
    public ResponseEntity<CreateCardResponse> createCard(@Valid @RequestBody CreateCardRequest createCardRequest) {
        CreateCardResponse createCardResponse = new CreateCardResponse();
        try {
            createCardResponse = cardAppService.createCard(createCardRequest);
            return new ResponseEntity<>(createCardResponse, HttpStatus.CREATED);
        } catch (DuplicityException e) {
            createCardResponse.setResponseCode(Util.CODE_FAILED);
            createCardResponse.setResponseMessage(e.getReason());
            return new ResponseEntity<>(createCardResponse, e.getCode());
        } catch (Exception e) {
            createCardResponse.setResponseCode(Util.CODE_FAILED);
            createCardResponse.setResponseMessage(Util.MESSAGE_FAILED);
            return new ResponseEntity<>(createCardResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping()
    public ResponseEntity<ActivateCardResponse> activateCard(@Valid @RequestBody ActivateCardRequest activateCardRequest) {
        ActivateCardResponse activateCardResponse = new ActivateCardResponse();
        try {
            activateCardResponse = cardAppService.activateCard(activateCardRequest);
            return new ResponseEntity<>(activateCardResponse, HttpStatus.OK);
        } catch (CardNotFoundException e) {
            activateCardResponse.setResponseCode(Util.CODE_FAILED);
            activateCardResponse.setResponseMessage(Util.MESSAGE_CARD_NOT_FOUND);
            return new ResponseEntity<>(activateCardResponse, e.getCode());
        } catch (ValidateNumberInvalidException e) {
            activateCardResponse.setResponseCode(Util.CODE_VALIDATION_NUMBER_INVALID);
            activateCardResponse.setResponseMessage(Util.MESSAGE_VALIDATION_NUMBER_INVALID);
            return new ResponseEntity<>(activateCardResponse, e.getCode());
        } catch (Exception e) {
            activateCardResponse.setResponseCode(Util.CODE_FAILED);
            activateCardResponse.setResponseMessage(Util.MESSAGE_FAILED);
            return new ResponseEntity<>(activateCardResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<CardResponse> getCard(@PathVariable(name = "id", required = true) String hashId) {
        CardResponse cardResponse = new CardResponse();
        try {
            GetCardRequest getCardRequest = new GetCardRequest(hashId);
            cardResponse = cardAppService.getCard(getCardRequest);
            return new ResponseEntity<>(cardResponse, HttpStatus.OK);
        } catch (CardNotFoundException e) {
            cardResponse.setResponseCode(Util.CODE_FAILED);
            cardResponse.setResponseMessage(Util.MESSAGE_CARD_NOT_FOUND);
            return new ResponseEntity<>(cardResponse, e.getCode());
        } catch (Exception e) {
            cardResponse.setResponseCode(Util.CODE_FAILED);
            cardResponse.setResponseMessage(Util.MESSAGE_FAILED);
            return new ResponseEntity<>(cardResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping()
    public ResponseEntity<GetAllCardsResponse> getAllCards() {
        GetAllCardsResponse getAllCardsResponse = new GetAllCardsResponse();
        try {
            getAllCardsResponse = cardAppService.getCards();
            return new ResponseEntity<>(getAllCardsResponse, HttpStatus.OK);
        } catch (Exception e) {
            getAllCardsResponse.setResponseCode(Util.CODE_FAILED);
            getAllCardsResponse.setResponseMessage(Util.MESSAGE_FAILED);
            return new ResponseEntity<>(getAllCardsResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<DeleteCardResponse> deleteCard(@PathVariable(name = "id", required = true) String hashId) {
        DeleteCardResponse deleteCardResponse = new DeleteCardResponse();
        try {
            DeleteCardRequest deleteCardRequest = new DeleteCardRequest(hashId);
            deleteCardResponse = cardAppService.deleteCard(deleteCardRequest);
            return new ResponseEntity<>(deleteCardResponse, HttpStatus.OK);
        } catch (CardNotFoundException e) {
            deleteCardResponse.setResponseCode(Util.CODE_FAILED);
            deleteCardResponse.setResponseMessage(Util.MESSAGE_DELETE_CARD_NOT_FOUND);
            return new ResponseEntity<>(deleteCardResponse, e.getCode());
        } catch (Exception e) {
            deleteCardResponse.setResponseCode(Util.CODE_FAILED);
            deleteCardResponse.setResponseMessage(Util.MESSAGE_FAILED);
            return new ResponseEntity<>(deleteCardResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
