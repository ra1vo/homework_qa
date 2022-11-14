package org.example.csv;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class CsvReader<T> extends CsvConverter{

    Class<T> type;
    public CsvReader(Class<T> clazz){
        this.type = clazz;
    }

    public List<T> csvToBean(String filename){
        Path path = Paths.get(filename);
        try {
            List<String> strings = Files.readAllLines(path);
            return parseCsv(strings);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private List<T> parseCsv(List<String> strings) {
        String[] headers = strings.get(0).split(Pattern.quote(DELIMITER));
        strings.remove(0); // remove header
        return strings.stream().map(row -> {
            String[] rowFields = row.split(Pattern.quote(DELIMITER));
            try {
                return createBeanFromCsvRow(headers, rowFields);
            } catch (NoSuchMethodException | InvocationTargetException | InstantiationException |
                     IllegalAccessException | NoSuchFieldException e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList());

    }

    private T createBeanFromCsvRow(String[] headers, String[] rowFields) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        T bean = type.getDeclaredConstructor().newInstance();

        for (int i = 0; i < headers.length; i++) {
            Field declaredField = bean.getClass().getDeclaredField(headers[i]);
            declaredField.setAccessible(true);
            if (declaredField.getGenericType() == Integer.TYPE){
                declaredField.setInt(bean, Integer.parseInt(rowFields[i]));
            }else {
                declaredField.set(bean, rowFields[i]);
            }
        }
        return bean;
    }
}
