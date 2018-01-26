package com.human.basic.entity;

import java.io.InputStream;

public class AttachMent {
    
    private String name;//附件名称
    
    private String attachmentPath;//附件路径
    
    private String attachmentPathType;//附件路径类型 1：远程服务器文件，2：本地文件
    
    private InputStream contentStream;//文件流

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAttachmentPath() {
        return attachmentPath;
    }

    public void setAttachmentPath(String attachmentPath) {
        this.attachmentPath = attachmentPath;
    }

    public String getAttachmentPathType() {
        return attachmentPathType;
    }

    public void setAttachmentPathType(String attachmentPathType) {
        this.attachmentPathType = attachmentPathType;
    }

    public InputStream getContentStream() {
        return contentStream;
    }

    public void setContentStream(InputStream contentStream) {
        this.contentStream = contentStream;
    }
    
    
    
    
}
