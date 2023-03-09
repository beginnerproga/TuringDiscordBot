package com.diagorn.turingbot.client;

import java.util.List;

public interface SchedulingClient {
    List<Integer> getIds(String task);
    String getAlgorithm(Integer id);

}
