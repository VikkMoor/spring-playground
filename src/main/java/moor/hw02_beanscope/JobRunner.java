package moor.hw02_beanscope;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Component;

@Component
public class JobRunner {
    // Это просто посмотреть, как не работает prototype в singleton:
    //
    // private final TaskContext taskContext;
    // public JobRunner(TaskContext taskContext) {
    //     this.taskContext = taskContext;
    // }
    // public void runOnce() {
    //     System.out.println(taskContext.getID());

    // А теперь как правильно его внедрить, чтоб он заработал:
    private final ObjectProvider<TaskContext> taskContextProvider;

    public JobRunner(ObjectProvider<TaskContext> taskContextProvider) {
        this.taskContextProvider = taskContextProvider;
    }

    public void runOnce() {
        TaskContext taskContext =
                taskContextProvider.getObject();
        System.out.println(taskContext.getID());
    }
}
