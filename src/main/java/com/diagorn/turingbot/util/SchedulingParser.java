package com.diagorn.turingbot.util;

import java.util.Map;

/**
 * class, which parse html page in string, which contains algorithm task
 *
 * @author beginnerproga
 */
public class SchedulingParser {

    /**
     * Removes role from user if user and role are valid
     *
     * @param task         - html page of task in string
     * @param inputValues  - string, which contains the list of input values in algorithm task
     * @param outputValues - string, which contains the list of output values in algorithm task
     * @return formatted string, contains algorithm task
     */
    public static String parseAlgorithmTask(String task, String inputValues, String outputValues) {
        StringBuilder sb = new StringBuilder(task);

        //Delete string, contains "Правила"
        sb.delete(0, sb.indexOf("Правила") + "Правила".length());

        //Delete string, contains "Автор задачи", if it exists
        if (sb.indexOf("Автор задачи") != -1)
            sb.delete(sb.indexOf("Автор задачи"), sb.indexOf("Сложность"));

        //Delete string, contains "Пример исходные данные результат" to string "Сложность", if it exists
        if (sb.lastIndexOf("Пример исходные данные результат") != -1)
            sb.delete(sb.indexOf("Пример исходные данные результат"), sb.indexOf("Сложность"));

        //add to string indent character
        sb.insert(sb.indexOf("Исходные данные") - 1, "\n").insert(sb.indexOf("Исходные данные") + "Исходные данные".length(), ":");
        sb.insert(sb.indexOf("Результат") - 1, "\n").insert(sb.indexOf("Результат") + "Результат".length(), ":");
        sb.insert(sb.indexOf("Сложность") - 1, "\n");

        //Delete string, contains "Версия" to end of string
        sb.delete(sb.indexOf("Версия"), sb.length());

        //add to string of input and output values indent character
        String[] arrayInputValues = inputValues.split("\n");
        String[] arrayOutputValues = outputValues.split("\n");
        sb.append("\n Входные значения:");
        for (int i = 0; i < arrayInputValues.length; i++) {
            sb.append("\n" + (i + 1) + ") " + arrayInputValues[i]);

        }
        sb.append("\n Выходные значения:");
        for (int i = 0; i < arrayOutputValues.length; i++) {
            sb.append("\n" + (i + 1) + ") " + arrayOutputValues[i]);
        }

        //add to string indent character
        if (sb.indexOf("Ограничение времени") != -1)
            sb.insert(sb.indexOf("Ограничение времени") - 1, "\n");

        //add to string indent character
        if (sb.indexOf("Ограничение памяти") != -1) {
            sb.insert(sb.indexOf("Ограничение памяти") + "Ограничение памяти".length(), "\n");
            sb.insert(sb.indexOf("Ограничение памяти") - 1, "\n");
        }
        return sb.toString();
    }

    /**
     * formatted query parameters In one string
     *
     * @param data - hashmap, which contains query parameter
     * @return return query parameters In one string
     */
    public static String toRequestParam(Map<String, String> data) {
        StringBuilder sb = new StringBuilder("?");
        for (Map.Entry<String, String> a : data.entrySet()) {
            sb.append(a.getKey()).append("=").append(a.getValue()).append("&");
        }
        sb.delete(sb.length() - 1, sb.length());
        return sb.toString();
    }
}
