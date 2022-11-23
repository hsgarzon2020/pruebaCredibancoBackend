package com.novatec.pruebacredibanco.app.service;

import com.novatec.pruebacredibanco.app.dtos.TransactionDto;
import com.novatec.pruebacredibanco.app.dtos.request.CancelTransactionRequest;
import com.novatec.pruebacredibanco.app.dtos.request.CreateTransactionRequest;
import com.novatec.pruebacredibanco.app.dtos.request.GetTransactionRequest;
import com.novatec.pruebacredibanco.app.dtos.response.CancelTransactionResponse;
import com.novatec.pruebacredibanco.app.dtos.response.CreateTransactionResponse;
import com.novatec.pruebacredibanco.app.dtos.response.GetAllTransactionResponse;
import com.novatec.pruebacredibanco.app.dtos.response.TransactionResponse;
import com.novatec.pruebacredibanco.app.mapper.TransactionAppMapper;
import com.novatec.pruebacredibanco.domain.entity.TransactionDomain;
import com.novatec.pruebacredibanco.domain.service.TransactionServiceDomain;
import com.novatec.pruebacredibanco.domain.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionAppService {

    @Autowired
    TransactionServiceDomain transactionServiceDomain;

    @Autowired
    TransactionAppMapper transactionAppMapper;

    public CreateTransactionResponse createTransaction(CreateTransactionRequest createTransactionRequest)
            throws Exception {

        CreateTransactionResponse createTransactionResponse = new CreateTransactionResponse();
        TransactionDto transactionDto = transactionAppMapper.mappingTransactionDomain(
                transactionServiceDomain.createTransaction(transactionAppMapper.mappingTransactionDomain(transactionAppMapper.toTransactionDto(createTransactionRequest))));
        createTransactionResponse.setResponseCode(Util.CODE_SUCCESS);
        createTransactionResponse.setResponseMessage(Util.MESSAGE_SUCCESS_TRANSACTION);
        createTransactionResponse.setState(transactionDto.getState());
        createTransactionResponse.setReferenceNumber(transactionDto.getReferenceNumber());
        return createTransactionResponse;
    }

    public CancelTransactionResponse cancelTransaction(CancelTransactionRequest cancelTransactionRequest)
    throws Exception{
       CancelTransactionResponse cancelTransactionResponse = new CancelTransactionResponse();
        TransactionDto transactionDto = transactionAppMapper.mappingTransactionDomain(
                transactionServiceDomain.cancelTransaction(transactionAppMapper.mappingTransactionDomain(
                        transactionAppMapper.toTransactionDto(cancelTransactionRequest))));
        cancelTransactionResponse.setResponseCode(Util.CODE_SUCCESS);
        cancelTransactionResponse.setResponseMessage(Util.MESSAGE_SUCCESS_CANCEL_TRANSACTION);
        cancelTransactionResponse.setReferenceNumber(transactionDto.getReferenceNumber());
        return cancelTransactionResponse;
    }

    public GetAllTransactionResponse getTransaction(GetTransactionRequest getTransactionRequest)
            throws Exception{
        GetAllTransactionResponse transactionResponse = new GetAllTransactionResponse();
        transactionResponse.setData(transactionAppMapper.mappingTransactionDtoToResponse(
                transactionAppMapper.mappingTransactionDomain(
                        transactionServiceDomain.getTransactionByHashId(getTransactionRequest.getHashId()))));

        transactionResponse.setResponseCode(Util.CODE_SUCCESS);
        transactionResponse.setResponseMessage(Util.MESSAGE_SUCCESS);

        return transactionResponse;
    }

}
