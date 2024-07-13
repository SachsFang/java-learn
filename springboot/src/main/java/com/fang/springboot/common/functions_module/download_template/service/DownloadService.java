package com.fang.springboot.common.functions_module.download_template.service;

import java.io.File;

/**
 * @author shaobin
 * @date 2022/11/18 11:28
 */
public interface DownloadService  {

    /**
     * 创建压缩文件
     *
     * @param sourcePath
     * @return
     */
    public File createTemplateCompressFile(String sourcePath);

}
