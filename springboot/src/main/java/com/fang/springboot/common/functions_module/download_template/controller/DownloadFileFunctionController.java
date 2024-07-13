package com.fang.springboot.common.functions_module.download_template.controller;

import com.fang.springboot.common.builder.BaseRespBuilder;
import com.fang.springboot.common.functions_module.download_template.constant.TemplateConstant;
import com.fang.springboot.common.pojo.BaseResp;
import com.fang.springboot.common.functions_module.download_template.service.DownloadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.codec.CharEncoding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * @author shaobin
 * @date 2022/11/18 11:19
 */
@RestController
@RequestMapping("/download")
@Api(tags = "下载控制器")
public class DownloadFileFunctionController {

    @Autowired
    private DownloadService downloadService;

    /**
     * 压缩模板文件夹下载功能实现
     * @param response
     * @return
     */
    @GetMapping("/compress")
    @ApiOperation("压缩模板文件夹下载")
    public BaseResp downloadContractExcelFileTemplate(HttpServletResponse response) {
        File templateFile = downloadService.createTemplateCompressFile(TemplateConstant.TEMPLATE_PATH);
        String downloadFileName = "模板.zip";
        response.setCharacterEncoding(CharEncoding.UTF_8);
        response.setContentType("application/json");
        try {
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(downloadFileName, CharEncoding.UTF_8));
            FileCopyUtils.copy(new FileInputStream(templateFile), response.getOutputStream());
            templateFile.delete();
        } catch (IOException e) {
            throw new RuntimeException("创建压缩文件失败！\n" + e);
        }
        return BaseRespBuilder.success().build();
    }
}
