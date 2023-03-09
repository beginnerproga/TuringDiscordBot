package com.diagorn.turingbot.service;

public interface SchedulingService {
    void sendAlgorithmTask(int taskId);
    void  sendEmptyTasksMessage();
    void schedulingMethod();

}
