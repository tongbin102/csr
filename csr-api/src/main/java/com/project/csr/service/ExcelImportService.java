package com.project.csr.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author: bin.tong
 * @date: 2020/11/24 9:53
 **/
public interface ExcelImportService {

    /**
     * 导入用户及店信息
     *
     * @param file
     * @return
     */
    void importUsersAndStores(MultipartFile file) throws IOException;

    /**
     * 导入因子要素及对应题目详情
     *
     * @param file
     * @return
     */
    void importRegulationsAndQuestions(MultipartFile file) throws IOException;

    /**
     * 导入全国及各店分因子成绩
     *
     * @param file
     * @param period
     * @throws IOException
     */
    void importScoresFactor(MultipartFile file, String period) throws IOException;

    /**
     * 导入全国及各店分渠道成绩
     *
     * @param file
     * @param period
     * @throws IOException
     */
    void importScoresChannel(MultipartFile file, String period) throws IOException;

    /**
     * 导入经销商分项成绩
     *
     * @param file
     * @param period
     * @throws IOException
     */
    void importScoresQuestion(MultipartFile file, String period) throws IOException;

    /**
     * 导入经销商投诉单
     *
     * @param file
     * @param period
     * @throws IOException
     */
    void importQuestionComplain(MultipartFile file, String period) throws IOException;

    /**
     * 导入经销商因子要素成绩单
     *
     * @param file
     * @param period
     * @throws IOException
     */
    void importRegulationScore(MultipartFile file, String period) throws IOException;
}
