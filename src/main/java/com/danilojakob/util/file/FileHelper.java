package com.danilojakob.util.file;

import java.io.*;

/**
 * Class for file util methods
 */
public class FileHelper {
    /**
     * Write a text in a file
     * @param file {@link File} file to create
     * @param text {@link String} text that has to be in the file
     */
    public void writeFile(File file, String text) {
        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter(file);
            fileWriter.write(text);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Read the content from a file
     * @param file {@link File} the file that has to be read
     * @return {@link String} content of the file
     */
    public String readFile(File file) {
        //Create data char array based on the length of the file
        char[] data = new char[(int) file.length()];
        try {
            FileReader fileReader = new FileReader(file);
            fileReader.read(data);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return new String(data);
    }

    /**
     * Get a serialized Object from a file
     * @param file {@link File} where the object is stored
     * @param objectType type in which the object has to be casted
     * @param <T> Type of the object
     * @return The object
     */
    public <T> T readSerializedObject(File file, T objectType) {
        T object = null;
        try {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            object = (T) ois.readObject();
            fis.close();
            ois.close();
        } catch (Exception ex) {
            System.err.println("Error while reading object from file");
        }
        return object;
    }

    /**
     * Serialize an object
     * @param file {@link File} where the file has to be stored
     * @param object Object that has to be serialized
     * @param <T> Type of the object
     */
    public <T> void serializeObject(File file, T object) {
        try {
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(object);
            fos.close();
            oos.close();
        } catch (Exception ex) {
            System.err.println("Error while creating serialized object");
        }
    }
}
