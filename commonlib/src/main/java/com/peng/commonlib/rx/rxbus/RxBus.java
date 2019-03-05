package com.peng.commonlib.rx.rxbus;

import com.jakewharton.rxrelay2.PublishRelay;
import com.jakewharton.rxrelay2.Relay;

import io.reactivex.Observable;


/**
 * Created by Mr.Q on 2019/2/20.
 * 描述：
 *      事件总线
 *          1、使用 RxRelay 中的 PublishRelay 进行实现，可避免因某个订阅者发生异常而导致的其他订阅者再也无法收到事件的问题
 *              RxRelay 有三个子类如下：
 *                  BehaviorRelay：他会接收订阅之前的最后一个事件和订阅之后的事件。
 *                  PublishRelay：只接受订阅后的事件。
 *                  ReplayRelay：接受订阅前和订阅后的所有事件。
 *          2、发送消息：调用 send 方法即可
 *          3、订阅消息：调用 toObservable 方法，然后执行订阅即可
 *      静态内部类实现单利模式：
 *          1、达到了懒加载的目的，即使用的使用才会去创建实例
 */
public class RxBus {

    private static class RxBusHolder{
        private final static RxBus instance = new RxBus();
    }

    public static RxBus getInstance(){
        return RxBusHolder.instance;
    }

    private Relay<Object> _bus = PublishRelay.create().toSerialized();

    /**
     * 发送消息
     *  eg、EventMsg 为自定义的消息类型
     *      RxBus.getInstance().send(new EventMsg());
     *
     * @author pq
     * create at 2019/2/20
     */
    public void send(Object o) {
        _bus.accept(o);
    }

    /**
     * 订阅消息，即接收消息
     *
     * @author pq
     * create at 2019/2/20
     */
    public <T> Observable<T> toObservable(Class<T> eventType) {
        return _bus.ofType(eventType);
    }
}
