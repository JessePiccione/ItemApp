package com.spencer.ItemApp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.spencer.ItemApp.models.UploadedFile;

@Repository
public interface UploadedFileRepository extends CrudRepository<UploadedFile, Long>{

}
