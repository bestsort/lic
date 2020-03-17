package cn.bestsort.lic.model.enums;

/**
 * 文件系统
 * @author bestsort
 */
public enum FileStoreType {
    /**
     * 服务器本地做为文件系统
     */
    DEFAULT,

    /**
     * 阿里OSS作为文件系统
     */
    ALI_OSS,

    /**
     * 腾讯COS作为文件系统
     */
    TX_COS;
}
