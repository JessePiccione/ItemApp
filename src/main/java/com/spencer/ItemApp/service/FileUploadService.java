package com.spencer.ItemApp.service;

import com.spencer.ItemApp.models.FileUpload;
import com.spencer.ItemApp.repository.FileUploadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class FileUploadService {
    @Autowired
    private FileUploadRepository fileUploadRepository;
    public List<FileUpload> findAll() {
        return fileUploadRepository.findAll();
    }
    public FileUpload save(FileUpload fileUpload) {
        return fileUploadRepository.save(fileUpload);
    }
    public long count() {
        return fileUploadRepository.count();
    }
}
