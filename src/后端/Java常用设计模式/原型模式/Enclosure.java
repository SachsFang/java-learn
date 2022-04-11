package 后端.Java常用设计模式.原型模式;

import java.io.Serializable;

/**
 * 附件类
 * @author shaobin
 * @date 2022/4/9 13:12
 */
public class Enclosure implements Serializable {

    private String fileName;
    private String FileType;

    public Enclosure(String fileName, String fileType) {
        this.fileName = fileName;
        FileType = fileType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return FileType;
    }

    public void setFileType(String fileType) {
        FileType = fileType;
    }

    @Override
    public String toString() {
        return "Enclosure{" +
                "fileName='" + fileName + '\'' +
                ", FileType='" + FileType + '\'' +
                '}';
    }
}
