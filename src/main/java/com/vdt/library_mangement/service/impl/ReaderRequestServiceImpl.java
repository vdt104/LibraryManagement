package com.vdt.library_mangement.service.impl;

import com.vdt.library_mangement.dto.ReaderRequestDto;
import com.vdt.library_mangement.dto.DocumentCopyDto;
import com.vdt.library_mangement.entity.DocumentCopy;
import com.vdt.library_mangement.entity.Reader;
import com.vdt.library_mangement.entity.ReaderCard;
import com.vdt.library_mangement.entity.ReaderRequest;
import com.vdt.library_mangement.exception.DocumentCopyNotAvailableException;
import com.vdt.library_mangement.exception.ReaderCardNotActiveException;
import com.vdt.library_mangement.exception.ResourceNotFoundException;
import com.vdt.library_mangement.mapper.ReaderRequestMapper;
import com.vdt.library_mangement.repository.DocumentCopyRepository;
import com.vdt.library_mangement.repository.ReaderRepository;
import com.vdt.library_mangement.repository.ReaderRequestRepository;
import com.vdt.library_mangement.service.ReaderRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ReaderRequestServiceImpl implements ReaderRequestService {

    private final ReaderRepository readerRepository;
    private final DocumentCopyRepository documentCopyRepository;
    private final ReaderRequestRepository readerRequestRepository;

    @Override
    public ReaderRequestDto createReaderRequest(ReaderRequestDto readerRequestDto) {
        Reader reader = readerRepository.findById(readerRequestDto.getUserId())
            .orElseThrow(() -> new ResourceNotFoundException("Reader", "id", readerRequestDto.getUserId()));

        ReaderCard readerCard = reader.getReaderCard();
        if (readerCard.getStatus() != ReaderCard.Status.ACTIVE) {
            throw new ReaderCardNotActiveException("Reader card is not active");
        }

        Set<DocumentCopy> documentCopies = new HashSet<>();
        for (DocumentCopyDto documentCopyDto : readerRequestDto.getDocumentCopies()) {
            DocumentCopy documentCopy = documentCopyRepository.findById(documentCopyDto.getCode())
                .orElseThrow(() -> new ResourceNotFoundException("DocumentCopy", "code", documentCopyDto.getCode()));

            if (documentCopy.getStatus() != DocumentCopy.Status.AVAILABLE) {
                throw new DocumentCopyNotAvailableException("Document copy " + documentCopyDto.getCode() + " is not available");
            }

            documentCopies.add(documentCopy);
        }

        ReaderRequest readerRequest = new ReaderRequest();
        readerRequest.setReader(reader);
        readerRequest.setStatus(ReaderRequest.Status.REQUESTED);
        readerRequest.setDocumentCopies(documentCopies);

        ReaderRequest savedReaderRequest = readerRequestRepository.save(readerRequest);

        savedReaderRequest.getReader().setUserId(null);

        return ReaderRequestMapper.toDTO(savedReaderRequest);
    }
}