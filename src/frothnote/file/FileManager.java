package frothnote.file;

import java.io.File;

public class FileManager
{
    public static String getFileExtension(File file) {
        String fileName = file.getName();
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
        return fileName.substring(fileName.lastIndexOf(".")+1);
        else return "";
    }
	
    public static void listFilesForFolder(final File folder)
    {
        for(final File fileEntry : folder.listFiles())
        {
            if(fileEntry.isDirectory())
            {
                listFilesForFolder(fileEntry);
            }
            else
            {
                System.out.println(fileEntry.getName());
            }
        }
    }
    
}
