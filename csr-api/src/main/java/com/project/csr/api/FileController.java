package com.project.csr.api;

import com.project.csr.service.ExcelImportService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author: bin.tong
 * @date: 2020/11/23 17:41
 **/
@Slf4j
@Api(tags = {"FileApi"}, value = "文件相关")
@RestController
@RequestMapping(value = "/fileApi")
public class FileController {

    @Autowired
    private ExcelImportService importService;

    @ApiOperation("导入用户及店信息")
    @PostMapping("/importUsersAndStores")
    public String importUsersAndStores(@RequestParam("file") MultipartFile file) throws IOException {
        importService.importUsersAndStores(file);
        return "success";
    }

    @ApiOperation("导入因子要素及对应题目详情")
    @PostMapping("/importRegulationsAndQuestions")
    public String importRegulationsAndQuestions(@RequestParam("file") MultipartFile file) throws IOException {
        importService.importRegulationsAndQuestions(file);
        return "success";
    }

    @ApiOperation("导入全国及各店分因子成绩")
    @PostMapping("/importScoresFactor")
    public String importScoresFactor(@RequestParam("file") MultipartFile file, @RequestParam("period") String period) throws IOException {
        importService.importScoresFactor(file, period);
        return "success";
    }

    @ApiOperation("导入全国及各店分渠道成绩")
    @PostMapping("/importScoresChannel")
    public String importScoresChannel(@RequestParam("file") MultipartFile file, @RequestParam("period") String period) throws IOException {
        importService.importScoresChannel(file, period);
        return "success";
    }

    @ApiOperation("导入经销商分项成绩")
    @PostMapping("/importScoresQuestion")
    public String importScoresQuestion(@RequestParam("file") MultipartFile file, @RequestParam("period") String period) throws IOException {
        importService.importScoresQuestion(file, period);
        return "success";
    }

    @ApiOperation("导入经销商投诉单")
    @PostMapping("/importQuestionComplain")
    public String importQuestionComplain(@RequestParam("file") MultipartFile file, @RequestParam("period") String period) throws IOException {
        importService.importQuestionComplain(file, period);
        return "success";
    }

    @ApiOperation("导入经销商因子要素成绩单")
    @PostMapping("/importRegulationScore")
    public String importRegulationScore(@RequestParam("file") MultipartFile file, @RequestParam("period") String period) throws IOException {
        importService.importRegulationScore(file, period);
        return "success";
    }


}
