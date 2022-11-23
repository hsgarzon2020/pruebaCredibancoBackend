package com.novatec.pruebacredibanco.app.mapper;

import com.novatec.pruebacredibanco.app.dtos.CardDto;
import com.novatec.pruebacredibanco.app.dtos.TransactionDto;
import com.novatec.pruebacredibanco.app.dtos.request.CancelTransactionRequest;
import com.novatec.pruebacredibanco.app.dtos.request.CreateTransactionRequest;
import com.novatec.pruebacredibanco.app.dtos.request.GetTransactionRequest;
import com.novatec.pruebacredibanco.app.dtos.response.TransactionResponse;
import com.novatec.pruebacredibanco.domain.entity.TransactionDomain;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TransactionAppMapper {

    TransactionDomain mappingTransactionDomain(TransactionDto transactionDto);

    TransactionDto mappingTransactionDomain(TransactionDomain transactionDomain);

    List<TransactionDto> mappingTransactionDomain(List<TransactionDomain> transactionDomain);

    @Mapping(target = "hashId", source = "cardId")
    List<TransactionResponse> mappingTransactionDtoToResponse(List<TransactionDto> transactions);

    default TransactionDto toTransactionDto(CreateTransactionRequest createTransactionRequest) {
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setHashId(createTransactionRequest.getHashId());
        transactionDto.setReferenceNumber(createTransactionRequest.getReferenceNumber());
        transactionDto.setAddressPurchase(createTransactionRequest.getAddressPurchase());
        transactionDto.setTotalPurchase(createTransactionRequest.getTotalPurchase());
        return transactionDto;
    }

    default TransactionDto toTransactionDto(CancelTransactionRequest cancelTransactionRequest) {
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setHashId(cancelTransactionRequest.getHashId());
        transactionDto.setReferenceNumber(cancelTransactionRequest.getReferenceNumber());
        transactionDto.setTotalPurchase(cancelTransactionRequest.getTotalPurchase());
        return transactionDto;
    }

    default TransactionDto toTransactionDto(GetTransactionRequest getTransactionRequest){
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setHashId(getTransactionRequest.getHashId());
        return transactionDto;
    }
}
