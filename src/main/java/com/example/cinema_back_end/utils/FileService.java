package com.example.cinema_back_end.utils;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @author tritcse00526x
 */
@Service
public class FileService {
    private final ResourceLoader resourceLoader;


    public FileService(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public String readFile(String filePath) throws IOException {
        //load resource using ResourceLoader
        Resource resource = resourceLoader.getResource("classpath:" + filePath);

        //read message from file
        byte[] bytes = Files.readAllBytes(Path.of(resource.getURI()));
        return new String(bytes, StandardCharsets.UTF_8);
    }

    public String format(String template, Object... args) {
        if (template == null || template.isEmpty()) {
            return template;
        }

        StringBuilder formattedString = new StringBuilder();
        int argIndex = 0;

        for (int i = 0; i < template.length(); i++) {
            char currentChar = template.charAt(i);

            if (currentChar == '%') {
                // if enough arguments, then replace the placeholder
                if (argIndex < args.length) {
                    Object arg = args[argIndex++];
                    formattedString.append(arg);
                } else {
                    // if no more arguments, then append the '%'
                    formattedString.append(currentChar);
                }
            } else {
                formattedString.append(currentChar);
            }
        }

        return formattedString.toString();
    }


}
