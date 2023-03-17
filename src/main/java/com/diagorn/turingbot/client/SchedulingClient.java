package com.diagorn.turingbot.client;

import java.util.List;

/**
 * Client, which send http - requests to site and getting html - pages
 *
 * @author beginnerproga
 */
public interface SchedulingClient {

    /**
     * Send algorithm task to discord channel in message
     *
     * @param task - one from the enum Chapter - task categories,
     * @return List of task's ids, that are in this category
     */
    List<Integer> getIds(String task);

    /**
     * Send algorithm task to discord channel in message
     *
     * @param id - task's id,
     * @return a formatted string containing an algorithmic task
     */
    String getAlgorithm(Integer id);

}
