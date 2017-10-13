package cn.skq.test.Executors;

/**
 * Created by admin on 2017/10/12.
 */
public class MyTask implements Runnable{

    private int id;
    private String taskName;

    public MyTask(){}

    public MyTask(int id,String taskName){
        this.id = id;
        this.taskName = taskName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public void run(){
        System.out.println("当前任务id："+this.id);
    }

}
