package com.spencer.ItemApp.repository;

import com.spencer.ItemApp.models.FileUpload;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;
public interface FileUploadRepository extends CrudRepository<FileUpload, Long> {
    List<FileUpload> findAll();
    List<FileUpload> findByUploadDate(LocalDate date);
    long count();
}
