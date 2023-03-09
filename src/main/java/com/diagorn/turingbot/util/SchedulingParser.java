package com.diagorn.turingbot.util;

import java.util.Map;

public class SchedulingParser {
    public static String parseAlgorithmTask(String task, String inputValues, String outputValues) {
        StringBuilder string = new StringBuilder(task);
        string.delete(0, string.indexOf("Правила") + "Правила".length());
        if (string.indexOf("Автор задачи") != -1)
            string.delete(string.indexOf("Автор задачи"), string.indexOf("Сложность"));
        if (string.lastIndexOf("Пример исходные данные результат") != -1)
            string.delete(string.indexOf("Пример исходные данные результат"), string.indexOf("Сложность"));
        string.insert(string.indexOf("Исходные данные") - 1, "\n").insert(string.indexOf("Исходные данные") + "Исходные данные".length(), ":");
        string.insert(string.indexOf("Результат") - 1, "\n").insert(string.indexOf("Результат") + "Результат".length(), ":");
        string.insert(string.indexOf("Сложность") - 1, "\n");
        string.delete(string.indexOf("Версия"), string.length());
        String[] arrayInputValues = inputValues.split("\n");
        String[] arrayOutputValues = outputValues.split("\n");
        string.append("\n Входные значения:");
        for (int i = 0; i < arrayInputValues.length; i++) {
            string.append("\n" + (i + 1) + ") " + arrayInputValues[i]);

        }
        string.append("\n Выходные значения:");
        for (int i = 0; i < arrayOutputValues.length; i++) {
            string.append("\n" + (i + 1) + ") " + arrayOutputValues[i]);
        }
        if (string.indexOf("Ограничение времени") != -1)
            string.insert(string.indexOf("Ограничение времени") - 1, "\n");
        if (string.indexOf("Ограничение памяти") != -1) {
            string.insert(string.indexOf("Ограничение памяти") + "Ограничение памяти".length(), "\n");
            string.insert(string.indexOf("Ограничение памяти") - 1, "\n");
        }
        return string.toString();
    }
    public static String toRequestParam(Map<String, String> data) {
        StringBuilder string = new StringBuilder("?");
        for (Map.Entry<String, String> a : data.entrySet()) {
            string.append(a.getKey()).append("=").append(a.getValue()).append("&");
        }
        string.delete(string.length() - 1, string.length());
        return string.toString();
    }
}
