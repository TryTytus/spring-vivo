package com.example.demo.hooks;

import org.apache.coyote.BadRequestException;

public class FileExtension {
    public static String get(String filename)
    {
        try {
            int i = filename.lastIndexOf(".");
            if (i > 0)
                 return filename.substring(i+1);
            return null;
        } catch (Exception e) {
            return null;
        }
    }

}
