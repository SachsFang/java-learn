package com.fang.springboot.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

/**
 * @author shaobin
 * @date 2022/10/20 10:29
 */
public class ResponseUtil {

    public static StringBuilder convertBody(InputStream bodyInputStream) {
        StringBuilder stringBuilder = new StringBuilder();
        if (Objects.isNull(bodyInputStream)) {
            return stringBuilder;
        }
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(bodyInputStream, "utf-8"));
            String line = bufferedReader.readLine();
            while (line != null) {
                stringBuilder.append(line + "\n");
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return stringBuilder;
    }
}
