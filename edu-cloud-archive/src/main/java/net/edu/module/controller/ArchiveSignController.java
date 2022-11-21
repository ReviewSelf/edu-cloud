package net.edu.module.controller;


import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import net.edu.framework.common.utils.Result;
import net.edu.module.service.ArchiveExamService;
import net.edu.module.service.ArchiveSignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("sign")
@Tag(name="记分册签到表")
@AllArgsConstructor
public class ArchiveSignController {

    @Autowired
    private ArchiveSignService archiveSignService;

    @PostMapping("/import")
    public Result<String> scoreImportExcel(@RequestParam("bookId") String bookId,@RequestParam("file") MultipartFile file) {

        archiveSignService.signImportExcel(file, Long.valueOf(bookId));
        return Result.ok();
    }
}
