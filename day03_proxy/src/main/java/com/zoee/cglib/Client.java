package com.zoee.cglib;

/**
 * @Author huangSir
 * @Date 2021/7/9 23:25
 */

import com.zoee.proxy.IProducer;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

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
         * 基于子类的代理对象
         * 涉及的类：Enhancer
         * 类的提供者：第三方jar包
         * 创建代理对象使用方法：create
         * 被代理对象不能是最终类
         *
         * create方法有三个参数：
         *      class:这个类是被代理对象的字节码
         *      callback：这个是写增强的代码，一般使用该接口的实现类
         *
         */
        Producer o = (Producer) Enhancer.create(producer.getClass(), new MethodInterceptor() {
            /**
             * 执行被代理对象的的任何方法都会经过该方法
             * @param o
             * @param method
             * @param objects
             * 以上的三个参数和基于接口的动态代理的invoke方法的参数是一样的
             * @param methodProxy       当前执行方法的代理对象
             * @return
             * @throws Throwable
             */
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                Float money = (Float) objects[0];
                Object re = null;
                if ("saleProducer".equals(method.getName())) {
                    re = method.invoke(producer, money * 0.8f);
                }
                return re;
            }
        });
        o.saleProducer(10000F);
    }
}
