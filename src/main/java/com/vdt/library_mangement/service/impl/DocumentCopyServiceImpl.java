package com.vdt.library_mangement.service.impl;

import com.vdt.library_mangement.dto.DocumentCopyDto;
import com.vdt.library_mangement.entity.Document;
import com.vdt.library_mangement.entity.DocumentCopy;
import com.vdt.library_mangement.exception.ResourceNotFoundException;
import com.vdt.library_mangement.repository.DocumentCopyRepository;
import com.vdt.library_mangement.repository.DocumentRepository;
import com.vdt.library_mangement.service.DocumentCopyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DocumentCopyServiceImpl implements DocumentCopyService {

    private final DocumentRepository documentRepository;
    private final DocumentCopyRepository documentCopyRepository;

    @Override
    public List<DocumentCopyDto> getAllDocumentCopiesOfDocument(String documentCode) {
        // Kiểm tra xem documentId có tồn tại hay không
        Optional<Document> documentOptional = documentRepository.findByDocumentCode(documentCode);
        if (!documentOptional.isPresent()) {
            throw new ResourceNotFoundException("Document Code", "document_code", documentCode);
        }

        Document document = documentOptional.get();

        // Lấy ra tất cả DocumentCopy của Document
        List<DocumentCopy> documentCopies = documentCopyRepository.findByDocument(document);

        // Chuyển đổi DocumentCopy sang DocumentCopyDto
        return documentCopies.stream()
            .map(documentCopy -> {
                DocumentCopyDto documentCopyDto = new DocumentCopyDto();
                documentCopyDto.setCode(documentCopy.getDocumentCopyCode());
                documentCopyDto.setDocumentId(documentCode);
                documentCopyDto.setLocation(documentCopy.getLocation());
                documentCopyDto.setStatus(documentCopy.getStatus().name());
                return documentCopyDto;
            })
            .toList();
    }

    @Override
    public DocumentCopyDto createDocumentCopy(String documentId, DocumentCopyDto documentCopyDto) {
        // Kiểm tra xem documentId có tồn tại hay không
        Optional<Document> documentOptional = documentRepository.findById(documentId);
        if (!documentOptional.isPresent()) {
            throw new IllegalArgumentException("Document not found");
        }
        Document document = documentOptional.get();

        // Tạo DocumentCopyCode
        String documentCopyCode = generateDocumentCopyCode(document);

        // Tạo DocumentCopy mới
        DocumentCopy documentCopy = new DocumentCopy();
        documentCopy.setDocumentCopyCode(documentCopyCode);
        documentCopy.setDocument(document);
        documentCopy.setLocation(documentCopyDto.getLocation());
        documentCopy.setStatus(DocumentCopy.Status.AVAILABLE);

        // Lưu DocumentCopy vào cơ sở dữ liệu
        documentCopyRepository.save(documentCopy);

        // Tăng quantity trong Document
        document.setQuantity(document.getQuantity() + 1);
        documentRepository.save(document);

        // Trả về DocumentCopyDto
        documentCopyDto.setCode(documentCopyCode);
        documentCopyDto.setDocumentId(documentId);
        documentCopyDto.setStatus(DocumentCopy.Status.AVAILABLE.name());
        
        return documentCopyDto;
    }

    @Override
    public DocumentCopyDto updateDocumentCopy(String documentCode, DocumentCopyDto documentCopyDto) {
        // Kiểm tra xem documentCode có tồn tại hay không
        Optional<DocumentCopy> documentCopyOptional = documentCopyRepository.findByDocumentCopyCode(documentCode);
        if (!documentCopyOptional.isPresent()) {
            throw new ResourceNotFoundException("Document Copy", "document_copy", documentCode);
        }
        DocumentCopy documentCopy = documentCopyOptional.get();

        // Cập nhật thông tin DocumentCopy
        documentCopy.setLocation(documentCopyDto.getLocation());
        documentCopy.setStatus(DocumentCopy.Status.valueOf(documentCopyDto.getStatus()));

        // Lưu DocumentCopy vào cơ sở dữ liệu
        documentCopyRepository.save(documentCopy);

        // Trả về DocumentCopyDto
        documentCopyDto.setCode(documentCode);
        documentCopyDto.setDocumentId(documentCopy.getDocument().getDocumentCode());
        return documentCopyDto;
    }

    private String generateDocumentCopyCode(Document document) {
        String documentCode = document.getDocumentCode();
        int copyCount = document.getQuantity() + 1;
        if (copyCount == 1) {
            return documentCode;
        } else {
            return documentCode + ".C" + copyCount;
        }
    }
}