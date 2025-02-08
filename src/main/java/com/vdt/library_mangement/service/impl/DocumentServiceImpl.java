package com.vdt.library_mangement.service.impl;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.vdt.library_mangement.dto.DocumentDto;
import com.vdt.library_mangement.entity.Author;
import com.vdt.library_mangement.entity.Category;
import com.vdt.library_mangement.entity.Document;
import com.vdt.library_mangement.exception.DocumentCodeAlreadyExistsException;
import com.vdt.library_mangement.mapper.DocumentMapper;
import com.vdt.library_mangement.repository.AuthorRepository;
import com.vdt.library_mangement.repository.CategoryRepository;
import com.vdt.library_mangement.repository.DocumentRepository;
import com.vdt.library_mangement.service.DocumentService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DocumentServiceImpl implements DocumentService {
    private final DocumentRepository documentRepository;
    private final AuthorRepository authorRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public DocumentDto createDocument(DocumentDto documentDto) {
        Optional<Document> existingDocument = documentRepository.findByDocumentCode(documentDto.getDocumentCode());

        if (existingDocument.isPresent()) {
            throw new DocumentCodeAlreadyExistsException("Document code already exists");
        }

        // Find the category by name
        Optional<Category> categoryOptional = categoryRepository.findById(documentDto.getCategory());
        if (!categoryOptional.isPresent()) {
            throw new IllegalArgumentException("Category not found");
        }
        Category category = categoryOptional.get();

        // Handle authors
        Set<Author> authors = new HashSet<>();
        if (documentDto.getAuthors() != null) {
            authors = documentDto.getAuthors().stream()
                .map(authorDto -> {
                    Optional<Author> existingAuthor = authorRepository.findByName(authorDto.getName());
                    if (existingAuthor.isPresent()) {
                        return existingAuthor.get();
                    } else {
                        Author author = new Author();
                        author.setName(authorDto.getName());
                        return authorRepository.save(author);
                    }
                })
                .collect(Collectors.toSet());
        }

        Document document = DocumentMapper.toEntity(documentDto);
        document.setCategory(category);
        document.setAuthors(authors);
        document.setQuantity(0);
        Document savedDocument = documentRepository.save(document);

        return DocumentMapper.toDTO(savedDocument);
    }
}