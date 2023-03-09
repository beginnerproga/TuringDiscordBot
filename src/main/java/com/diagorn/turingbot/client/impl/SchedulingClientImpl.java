package com.diagorn.turingbot.client.impl;

import com.diagorn.turingbot.client.SchedulingClient;
import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.diagorn.turingbot.util.SchedulingParser.parseAlgorithmTask;
import static com.diagorn.turingbot.util.SchedulingParser.toRequestParam;

@Component
@PropertySource("classpath:application-scheduling.properties")
public class SchedulingClientImpl implements SchedulingClient {
    @Value("${scheduling-site.url}")
    private String url;


    @Override
    @SneakyThrows
    public List<Integer> getIds(String task) {
        Map<String, String> data = new HashMap<>();
        data.put("space", "1");
        data.put("tag", task);
        data.put("skipac", "False");
        data.put("sort", "difficulty");
        String path = url + "problemset.aspx" + toRequestParam(data);
        Document doc = Jsoup.connect(path).get();
        List<Integer> ids = new ArrayList<>();
        for (var i : doc.select("tr.content").select("td:eq(1)")) {
            ids.add(Integer.parseInt(i.text()));
        }
        return ids;
    }

    @Override
    @SneakyThrows
    public String getAlgorithm(Integer id) {
        Map<String, String> data = new HashMap<>();
        data.put("space", "1");
        data.put("num", id.toString());
        data.put("locale", "ru");
        String path = url + "problem.aspx" + toRequestParam(data);
        Document doc = Jsoup.connect(path).get();
        String inputValues = doc.select("table.sample").select("tbody").select("tr").select("td:eq(0)").text();
        String outputValues = doc.select("table.sample").select("tbody").select("tr").select("td:eq(1)").text();
        return parseAlgorithmTask(doc.text(), inputValues, outputValues);
    }


}
