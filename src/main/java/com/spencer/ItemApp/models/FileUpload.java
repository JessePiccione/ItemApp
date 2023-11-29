package com.spencer.ItemApp.models;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
public class FileUpload {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    @Getter
    @Column(length=255)
    private String fileName;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate uploadDate;

    protected FileUpload(){
    }
    public FileUpload(String fileName, String uploadDate){
        this.fileName = fileName;
        this.uploadDate = LocalDate.parse(uploadDate);
    }
    public String getUploadDate(){
        return this.uploadDate.toString();
    }
    public String toString(){
        return "FileName: "+this.fileName+" UploadDate: "+this.uploadDate;
    }
}
