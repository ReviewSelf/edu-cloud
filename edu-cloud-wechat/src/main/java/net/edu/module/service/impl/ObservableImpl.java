package net.edu.module.service.impl;

import net.edu.module.service.Observable;
import net.edu.module.service.Observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author weng
 * @date 2023/4/10 - 16:35
 **/
public class ObservableImpl implements Observable {

    //定义观察者集合
    private List<Observer> observerList = new ArrayList<>();

    @Override
    public void addObserver(Observer observer) {
        if (!observerList.contains(observer)) {
            observerList.add(observer);
        }
    }

    @Override
    public void removeObserver(Observer observer) {
        if (observerList.contains(observer)) {
            observerList.remove(observer);
        }
    }

    @Override
    public void notice(String content) {
        for (Observer observer : observerList) {
            //通知所有观察者--发送消息
            observer.sentMessage(content);
        }
    }

}