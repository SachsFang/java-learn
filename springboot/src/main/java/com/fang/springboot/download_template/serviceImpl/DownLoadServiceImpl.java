package com.fang.springboot.download_template.serviceImpl;

import com.fang.springboot.download_template.constant.TemplateConstant;
import com.fang.springboot.download_template.service.DownloadService;
import com.fang.springboot.download_template.util.CompressUtil;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

/**
 * @author shaobin
 * @date 2022/11/18 11:27
 */
@Service
public class DownLoadServiceImpl implements DownloadService {
    @Override
    public File createTemplateCompressFile(String sourcePath) {
        String templatePath = TemplateConstant.TEMPLATE_PATH;
        File templateFile = new File(templatePath);
        if (!templateFile.exists()) {
            throw new RuntimeException("模板路径不存在！");
        }
        String compressFileTargetPath = templateFile.getParent() + "/temp.zip";
        File compressTemplateFile = null;
        try {
            CompressUtil.compress(templatePath, compressFileTargetPath, "模板");
            compressTemplateFile = new File(compressFileTargetPath);
        } catch (IOException e) {
            throw new RuntimeException("创建压缩文件失败！\n" + e);
        }
        return compressTemplateFile;
    }
}
