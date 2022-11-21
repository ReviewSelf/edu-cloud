package net.edu.module.service;

import org.springframework.web.multipart.MultipartFile;

public interface ArchiveSignService {

    void signImportExcel(MultipartFile file,Long bookId);
}
