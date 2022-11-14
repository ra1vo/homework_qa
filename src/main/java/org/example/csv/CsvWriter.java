package org.example.csv;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CsvWriter<T> extends CsvConverter{


    public String beansToCsv(List<T> beans, Class<T> clazz){
        String header = generateHeader(clazz);
        return header + "\n" + beans.stream()
                .map(this::beanFieldstoCsvRow)
                .collect(Collectors.joining("\n"));
    }

    private String beanFieldstoCsvRow(T t){
        return Arrays.stream(t.getClass().getDeclaredFields())
                .map(field -> {
                    field.setAccessible(true);
                    try {
                        if (field.getGenericType() == Integer.TYPE){
                            return Integer.toString(field.getInt(t));
                        } else {
                            Object o = field.get(t);
                            if (o == null)
                                return "null";
                            return field.get(t).toString();
                        }
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                }).collect(Collectors.joining(DELIMITER));
    }

    public void writeToFile(String filename, String data) throws IOException {
        File csvFile = new File(filename);
        try (PrintWriter pw = new PrintWriter(csvFile)) {
            pw.print(data);
        }
    }

    public String generateHeader(Class<T> clazz){
        return Arrays.stream(clazz.getDeclaredFields())
                .map(Field::getName).collect(Collectors.joining(DELIMITER));
    }
}
