package com.diagorn.turingbot.service;

/**
 * Implements the scheduling pattern
 *
 * @author beginnerproga
 */
public interface SchedulingService {

    /**
     * Send algorithm task to discord channel in message
     *
     * @param taskId - id of the task we want to receive
     */
    void sendAlgorithmTask(int taskId);

    /**
     * Release scheduling with scheduling annotation
     */
    void schedulingMethod();

}
