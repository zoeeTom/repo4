package com.zoee.proxy;

/**
 * @Author huangSir
 * @Date 2021/7/9 23:25
 */

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 该类用来模拟一个用户买产品
 */
public class Client {
    public static void main(String[] args) {
         final Producer producer = new Producer();
        producer.saleProducer(5555f);

        /**
         * 基于接口的代理对象
         * 涉及的类：Proxy
         * 类的提供者：jdk官方
         * 创建代理对象使用方法：newProxyInstance
         * 被代理对象至少应该实现一个接口
         *
         * newProxyInstance有三个参数：
         *      ClassLoader:这个是用来加载代理对象字节码的，和被代理对象使用同一个类加载器
         *          Producer.getClass().getClassloader()
         *      Class[]:字节码数组，它用来让代理对象和被代理对象有相同的方法的
         *      InvocationHandler:这个参数是表示如何去代理，通常是一个匿名内部类
         */
        IProducer proxyProducer = (IProducer) Proxy.newProxyInstance(producer.getClass().getClassLoader(), producer.getClass().getInterfaces(),
                new InvocationHandler() {
                    /**
                     * invoke方法的作用：
                     * 执行任何被代理对象的的任何接口方法都会经过该方法
                     * @param proxy
                     * @param method
                     * @param args
                     * @return
                     * @throws Throwable
                     */
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        Float money = (Float) args[0];
                        Object o=null;
                        if ("saleProducer".equals(method.getName())){
                            o = method.invoke(producer, money * 0.8f);
                        }
                        return o;
                    }
                });
        proxyProducer.saleProducer(10000);
    }
}
