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

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ReaderRequestServiceImpl implements ReaderRequestService {

    private final ReaderRepository readerRepository;
    private final DocumentCopyRepository documentCopyRepository;
    private final ReaderRequestRepository readerRequestRepository;

    @Override
    public ReaderRequestDto getReaderRequestById(String id) {
        ReaderRequest readerRequest = readerRequestRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("ReaderRequest", "id", id));

        return ReaderRequestMapper.toDTO(readerRequest);
    }

    @Override
    public ReaderRequestDto acceptReaderRequest(String id, ReaderRequestDto readerRequestDto) {
        ReaderRequest readerRequest = readerRequestRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("ReaderRequest", "id", id));

        Set<DocumentCopy> documentCopies = readerRequest.getDocumentCopies();
        for (DocumentCopy documentCopy : documentCopies) {
            if (documentCopy.getStatus() != DocumentCopy.Status.AVAILABLE) {
                throw new DocumentCopyNotAvailableException("Document copy " + documentCopy.getDocumentCopyCode() + " is not available");
            }
        }

        for (DocumentCopy documentCopy : documentCopies) {
            documentCopy.setStatus(DocumentCopy.Status.BORROWED);
            documentCopyRepository.save(documentCopy);
        }

        readerRequest.setStatus(ReaderRequest.Status.ACCEPTED);

        ReaderRequest savedReaderRequest = readerRequestRepository.save(readerRequest);

        if (readerRequestDto.getNotes() != null) {
            readerRequest.setNotes(readerRequestDto.getNotes());
        }

        return ReaderRequestMapper.toDTO(savedReaderRequest);
    }

    @Override
    public ReaderRequestDto rejectReaderRequest(String id, ReaderRequestDto readerRequestDto) {
        ReaderRequest readerRequest = readerRequestRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("ReaderRequest", "id", id));

        readerRequest.setStatus(ReaderRequest.Status.REJECTED);

        ReaderRequest savedReaderRequest = readerRequestRepository.save(readerRequest);

        if (readerRequestDto.getNotes() != null) {
            readerRequest.setNotes(readerRequestDto.getNotes());
        }

        return ReaderRequestMapper.toDTO(savedReaderRequest);
    }

    @Override
    public ReaderRequestDto borrowReaderRequest(String id, ReaderRequestDto readerRequestDto) {
        ReaderRequest readerRequest = readerRequestRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("ReaderRequest", "id", id));

        readerRequest.setStatus(ReaderRequest.Status.BORROWED);
        readerRequest.setDateBorrowed(new Date());

        if (readerRequestDto.getNotes() != null) {
            readerRequest.setNotes(readerRequestDto.getNotes());
        }

        ReaderRequest savedReaderRequest = readerRequestRepository.save(readerRequest);

        return ReaderRequestMapper.toDTO(savedReaderRequest);
    }

    @Override
    public ReaderRequestDto returnReaderRequest(String id, ReaderRequestDto readerRequestDto) {
        ReaderRequest readerRequest = readerRequestRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("ReaderRequest", "id", id));

        readerRequest.setStatus(ReaderRequest.Status.RETURNED);
        readerRequest.setDateReturned(new Date());

        if (readerRequestDto.getPenaltyFee() > 0.0) {
            readerRequest.setPenaltyFee(readerRequestDto.getPenaltyFee());
        }

        if (readerRequestDto.getNotes() != null) {
            readerRequest.setNotes(readerRequestDto.getNotes());
        }

        for (DocumentCopy documentCopy : readerRequest.getDocumentCopies()) {
            documentCopy.setStatus(DocumentCopy.Status.AVAILABLE);
            documentCopyRepository.save(documentCopy);
        }

        return ReaderRequestMapper.toDTO(readerRequestRepository.save(readerRequest));
    }

    @Override
    public ReaderRequestDto cancelReaderRequest(String id, ReaderRequestDto readerRequestDto) {
        ReaderRequest readerRequest = readerRequestRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("ReaderRequest", "id", id));

        readerRequest.setStatus(ReaderRequest.Status.CANCELLED);

        return ReaderRequestMapper.toDTO(readerRequestRepository.save(readerRequest));
    }

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
        readerRequest.setBorrowingPeriod(readerRequestDto.getBorrowingPeriod());
        readerRequest.setDocumentCopies(documentCopies);
        readerRequest.setPenaltyFee(0.0);
        readerRequest.setDocumentCopies(documentCopies);

        ReaderRequest savedReaderRequest = readerRequestRepository.save(readerRequest);

        savedReaderRequest.getReader().setUserId(null);

        return ReaderRequestMapper.toDTO(savedReaderRequest);
    }
}