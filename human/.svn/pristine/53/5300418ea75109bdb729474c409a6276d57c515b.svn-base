package com.human.jobs;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * 线程管理工具类
 * @author liuwei
 *
 */
public class TaskExecutorUtil {
    
    private ThreadPoolTaskExecutor taskExecutor;

    public ThreadPoolTaskExecutor getTaskExecutor() {
        return taskExecutor;
    }

    public void setTaskExecutor(ThreadPoolTaskExecutor taskExecutor) {
        this.taskExecutor = taskExecutor;
    }
    
    public void excuteInsertBatchRecommendClass(Runnable r) {       
        taskExecutor.execute(r);
    }
}
