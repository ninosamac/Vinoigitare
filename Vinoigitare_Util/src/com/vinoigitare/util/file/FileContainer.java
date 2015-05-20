package com.vinoigitare.util.file;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface FileContainer {

    void createFolder(String folderName) throws IOException;

    void storeTextual(String fileName, String content) throws IOException;

    void storeBinary(String fileName, byte[] content) throws IOException;

    void delete(String fileName) throws FileNotFoundException;

    String loadTextual(String fileName) throws IOException;

    byte[] loadBinary(String fileName) throws IOException;

    boolean fileExists(String fileName);

    boolean isFile(String fileName);

    boolean isFolder(String folderName);

    String getAbsolutePath(String fileName);

    List<String> listFileNames();

    List<String> listSubfolders();

    String getRootFolder();

    long getLastModifiedAt(String fileName) throws FileNotFoundException;

    long getSize(String fileName) throws FileNotFoundException;

    void setLastModifiedAt(String path, long lastModifiedAt) throws FileNotFoundException;

    void removeFile(String path) throws FileNotFoundException;
}