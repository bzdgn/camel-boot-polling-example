package io.github.bzdgn.sru.service;

import io.github.bzdgn.sru.entity.FileMeta;
import io.github.bzdgn.sru.repo.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentServiceImpl implements DocumentService {

    @Autowired
    private DocumentRepository documentRepository;

    public List<FileMeta> getAllFileMeta() {
        return documentRepository.findAll();
    }
}
