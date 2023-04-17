package net.edu.module.service;

/**
 * @author weng
 * @date 2023/4/10 - 16:34
 **/
public interface Observable {

    //添加观察者
    void addObserver(Observer observer);

    //移除观察者
    void removeObserver(Observer observer);

    //通知观察者
    void notice(String content);
}
