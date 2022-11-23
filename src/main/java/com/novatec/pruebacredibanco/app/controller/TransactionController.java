package com.novatec.pruebacredibanco.app.controller;

import com.novatec.pruebacredibanco.app.dtos.request.CancelTransactionRequest;
import com.novatec.pruebacredibanco.app.dtos.request.CreateTransactionRequest;
import com.novatec.pruebacredibanco.app.dtos.request.GetTransactionRequest;
import com.novatec.pruebacredibanco.app.dtos.response.CancelTransactionResponse;
import com.novatec.pruebacredibanco.app.dtos.response.CreateTransactionResponse;
import com.novatec.pruebacredibanco.app.dtos.response.GetAllTransactionResponse;
import com.novatec.pruebacredibanco.app.dtos.response.TransactionResponse;
import com.novatec.pruebacredibanco.app.service.TransactionAppService;
import com.novatec.pruebacredibanco.domain.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/transactions")
public class TransactionController {

    @Autowired
    private TransactionAppService transactionAppService;

    @PostMapping()
    public ResponseEntity<CreateTransactionResponse> createTransaction(@Valid @RequestBody CreateTransactionRequest createTransactionRequest) {
        CreateTransactionResponse createTransactionResponse = new CreateTransactionResponse();
        try {
            createTransactionResponse = transactionAppService.createTransaction(createTransactionRequest);
            return new ResponseEntity<>(createTransactionResponse, HttpStatus.CREATED);
        } catch (CardNotFoundException e) {
            createTransactionResponse.setResponseCode(Util.CODE_FAILED);
            createTransactionResponse.setResponseMessage(Util.MESSAGE_CARD_NOT_FOUND);
            return new ResponseEntity<>(createTransactionResponse, e.getCode());
        } catch (CardInactiveException e) {
            createTransactionResponse.setResponseCode(Util.CODE_STATE_CARD_INVALID);
            createTransactionResponse.setResponseMessage(Util.MESSAGE_STATE_CARD_INVALID);
            return new ResponseEntity<>(createTransactionResponse, e.getCode());
        } catch (Exception e) {
            createTransactionResponse.setResponseCode(Util.CODE_FAILED);
            createTransactionResponse.setResponseMessage(Util.MESSAGE_FAILED);
            return new ResponseEntity<>(createTransactionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping()
    public ResponseEntity<CancelTransactionResponse> cancelTransaction(@Valid @RequestBody CancelTransactionRequest cancelTransactionRequest) {
        CancelTransactionResponse cancelTransactionResponse = new CancelTransactionResponse();
        try {
            cancelTransactionResponse = transactionAppService.cancelTransaction(cancelTransactionRequest);
            return new ResponseEntity<>(cancelTransactionResponse, HttpStatus.CREATED);
        }catch (CannotCancelTransactionException e){
            cancelTransactionResponse.setResponseCode(Util.CODE_CANNOT_CANCEL_TRANSACTION);
            cancelTransactionResponse.setResponseMessage(Util.MESSAGE_CANNOT_CANCEL_TRANSACTION);
            return new ResponseEntity<>(cancelTransactionResponse, e.getCode());
        }
        catch (ReferenceNumberInvalid e){
            cancelTransactionResponse.setResponseCode(Util.CODE_FAILED);
            cancelTransactionResponse.setResponseMessage(Util.MESSAGE_INVALID_REFERENCE);
            return new ResponseEntity<>(cancelTransactionResponse, e.getCode());
        }
        catch (CardNotFoundException e){
            cancelTransactionResponse.setResponseCode(Util.CODE_CANNOT_CANCEL_TRANSACTION);
            cancelTransactionResponse.setResponseMessage(Util.MESSAGE_CANNOT_CANCEL_TRANSACTION);
            return new ResponseEntity<>(cancelTransactionResponse, e.getCode());
        }
        catch (Exception e) {
            cancelTransactionResponse.setResponseCode(Util.CODE_CANNOT_CANCEL_TRANSACTION);
            cancelTransactionResponse.setResponseMessage(Util.MESSAGE_CANNOT_CANCEL_TRANSACTION);
            return new ResponseEntity<>(cancelTransactionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<GetAllTransactionResponse> getTransactionByCardId(@PathVariable(name = "id",required = true) String hashId) {
        GetAllTransactionResponse getAllTransactionResponse = new GetAllTransactionResponse();
        try {
            GetTransactionRequest getTransactionRequest = new GetTransactionRequest(hashId);
            getAllTransactionResponse = transactionAppService.getTransaction(getTransactionRequest);
            return new ResponseEntity<>(getAllTransactionResponse, HttpStatus.OK);
        } catch (CardNotFoundException e) {
            getAllTransactionResponse.setResponseCode(Util.CODE_FAILED);
            getAllTransactionResponse.setResponseMessage(Util.MESSAGE_CARD_NOT_FOUND);
            return new ResponseEntity<>(getAllTransactionResponse, e.getCode());
        } catch (Exception e) {
            getAllTransactionResponse.setResponseCode(Util.CODE_FAILED);
            getAllTransactionResponse.setResponseMessage(Util.MESSAGE_FAILED);
            return new ResponseEntity<>(getAllTransactionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
