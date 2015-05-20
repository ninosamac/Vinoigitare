package com.vinoigitare.util.file;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

/**
 * 
 * Convenience class to provide as a folder managing service. This class is instantiated with the parameter of folder
 * path and will use this path as a root folder. All file and folder operations will be done relative to this folder.
 * 
 * @author nino.samac
 * 
 */
public class FolderUtil implements FileContainer {

    private final File rootFolder;

    /**
     * 
     * @param folder
     *            the root folder for this service.
     */
    public FolderUtil(String folder) {
        if (folder == null) {
            throw new NullPointerException("Argument can not be null");
        }

        rootFolder = getFile(folder);
        try {
            if (!rootFolder.exists()) {
                FileUtils.forceMkdir(rootFolder);
            }

            if (!rootFolder.isDirectory()) {
                throw new FileNotFoundException("FolderUtil is not a directory: " + folder);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.err.println("Could not instantiate FileService for folder: " + folder);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("FolderUtil '" + folder + "' could not be created. ");
        }
    }

    @Override
    public void createFolder(String folderName) throws IOException {
        File folder = new File(rootFolder + "/" + folderName);
        FileUtils.forceMkdir(folder);
    }

    @Override
    public synchronized String loadTextual(String fileName) throws IOException {
        String filePath = getAbsolutePathInternal(fileName);
        checkFileExists(filePath);
        checkIsFile(filePath);
        File file = getFile(filePath);

        int numBytes = (int) file.length();
        char[] data = new char[numBytes];
        FileReader fis = new FileReader(file);
        fis.read(data);
        fis.close();
        return String.valueOf(data);
    }

    @Override
    public synchronized void storeTextual(String fileName, String content) throws IOException {
        String filePath = getAbsolutePathInternal(fileName);
        File file = getFile(filePath);
        if (!file.exists()) {
            file.createNewFile();
        }
        writeTextualContentToFile(file, content);
    }

    private void writeTextualContentToFile(File file, String content) throws IOException {
        FileWriter fw = new FileWriter(file);
        fw.append(content);
        fw.close();
    }

    @Override
    public void storeBinary(String fileName, byte[] content) throws IOException {
        String filePath = getAbsolutePathInternal(fileName);
        File file = getFile(filePath);
        if (!file.exists()) {
            file.createNewFile();
        }
        writeBinaryContentToFile(file, content);
    }

    private void writeBinaryContentToFile(File file, byte[] content) throws IOException {
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(content);
        fos.close();
    }

    @Override
    public byte[] loadBinary(String fileName) throws IOException {
        String filePath = getAbsolutePathInternal(fileName);
        checkFileExists(filePath);
        checkIsFile(filePath);
        File file = getFile(filePath);

        FileInputStream in = null;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            in = new FileInputStream(file);

            int c;

            while ((c = in.read()) != -1) {
                out.write(c);
            }
        } finally {
            if (in != null) {
                in.close();
            }

        }
        out.close();
        return out.toByteArray();

    }

    @Override
    public synchronized void delete(String fileName) throws FileNotFoundException, SecurityException {
        String filePath = getAbsolutePathInternal(fileName);
        File file = new File(filePath);
        checkFileExists(filePath);
        checkIsFile(filePath);
        if (!file.delete()) {
            throw new SecurityException("Could not delete file: " + filePath);
        }
    }

    @Override
    public synchronized boolean fileExists(String fileName) {
        String filePath = getAbsolutePathInternal(fileName);
        File file = getFile(filePath);
        return file.exists();
    }

    @Override
    public synchronized boolean isFile(String fileName) {
        String filePath = getAbsolutePathInternal(fileName);
        File file = getFile(filePath);
        return file.isFile();
    }

    @Override
    public synchronized boolean isFolder(String folderName) {
        String folderPath = getAbsolutePathInternal(folderName);
        File folder = getFile(folderPath);
        return folder.isDirectory();
    }

    private File getFile(String filePath) {
        return new File(filePath);
    }

    @Override
    public String getAbsolutePath(String fileName) {
        String absolutePath = getAbsolutePathInternal(fileName);
        return absolutePath;
    }

    private String getAbsolutePathInternal(String fileName) {
        return rootFolder.getPath() + "/" + fileName;
    }

    private void checkIsFile(String filePath) throws FileNotFoundException {
        File file = getFile(filePath);
        if (!file.isFile()) {
            throw new FileNotFoundException("Not a file: " + filePath);
        }
    }

    private void checkFileExists(String filePath) throws FileNotFoundException {
        File file = getFile(filePath);
        if (!file.exists()) {
            throw new FileNotFoundException("File does not exist: " + filePath);
        }
    }

    @Override
    public List<String> listFileNames() {
        File[] listOfFiles = rootFolder.listFiles();
        ArrayList<String> result = new ArrayList<String>();

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                result.add(listOfFiles[i].getName());
            }
        }
        return result;
    }

    @Override
    public List<String> listSubfolders() {
        File[] listOfFiles = rootFolder.listFiles();
        ArrayList<String> result = new ArrayList<String>();

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isDirectory()) {
                result.add(listOfFiles[i].getName());
            }
        }
        return result;
    }

    @Override
    public String getRootFolder() {
        return rootFolder.getAbsolutePath();
    }

    @Override
    public long getLastModifiedAt(String fileName) throws FileNotFoundException {
        String filePath = getAbsolutePathInternal(fileName);
        checkFileExists(filePath);
        File file = getFile(filePath);
        return file.lastModified();
    }

    @Override
    public long getSize(String fileName) throws FileNotFoundException {
        String filePath = getAbsolutePathInternal(fileName);
        checkFileExists(filePath);
        checkIsFile(filePath);
        File file = getFile(filePath);
        return file.length();
    }

    @Override
    public void setLastModifiedAt(String fileName, long lastModifiedAt) throws FileNotFoundException {
        String filePath = getAbsolutePathInternal(fileName);
        checkFileExists(filePath);
        File file = getFile(filePath);
        file.setLastModified(lastModifiedAt);
    }

    @Override
    public void removeFile(String fileName) throws FileNotFoundException {
        String filePath = getAbsolutePathInternal(fileName);
        checkFileExists(filePath);
        checkIsFile(filePath);
        File file = getFile(filePath);
        file.delete();
    }

}
