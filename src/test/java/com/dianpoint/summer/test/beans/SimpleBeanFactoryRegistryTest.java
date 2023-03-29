package com.dianpoint.summer.test.beans;

import com.dianpoint.summer.beans.factory.config.SingletonBeanRegistry;
import com.dianpoint.summer.beans.factory.support.DefaultListableBeanFactory;
import org.junit.Test;

import java.util.Arrays;
import java.util.UUID;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * DefaultSingletonBeanRegistry对应的测试用例
 * 
 * @author wangyi
 * @date 2023/3/26
 */
public class SimpleBeanFactoryRegistryTest extends AbstractBeanRegistryTest {

    private final String LOCK = this.getClass().getName() + "_LOCK";

    DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();

    /**
     * 测试registerSingleton方法的synchronized声明是否生效
     */
    @Test
    public void registerSingleton_syncWaitCase() throws InterruptedException {
        final SimpleBeanFactoryRegistryTest test = new SimpleBeanFactoryRegistryTest();
        Thread producerThread = new Thread(test.new BeanRegistryProducer());
        Thread consumerThread = new Thread(test.new BeanRegistryConsumer());
        producerThread.start();
        consumerThread.start();

        producerThread.join();
        consumerThread.join();
    }

    @Override
    protected SingletonBeanRegistry getSingletonBeanRegistry() {
        return this.defaultListableBeanFactory;
    }

    // 创建信号量:
    // producer初始化0个产品，release之后才可以被使用。
    // consumer默认一个消费者，初始化进行消费
    // mutex用来保证同时只有一个消费者或者生产者
    final Semaphore producer = new Semaphore(0);
    final Semaphore consumer = new Semaphore(1);
    volatile String beanName;
    volatile AtomicInteger ai = new AtomicInteger();

    class BeanRegistryProducer implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
                    if (ai.get() > 100) {
                        break;
                    }
                    SimpleBeanFactoryRegistryTest.this.consumer.acquire();
                    // 删除后beanName不存在于beanDefinitions、beanDefinitionNames
                    if (SimpleBeanFactoryRegistryTest.this.beanName != null
                        && !SimpleBeanFactoryRegistryTest.this.beanName.equals("")) {
                        assertThat(Arrays
                            .asList(SimpleBeanFactoryRegistryTest.this.defaultListableBeanFactory.getSingletonNames())
                            .contains(SimpleBeanFactoryRegistryTest.this.beanName)).isFalse();
                        assertThat(SimpleBeanFactoryRegistryTest.this.defaultListableBeanFactory
                            .getSingleton(SimpleBeanFactoryRegistryTest.this.beanName)).isNull();
                    }
                    ai.getAndIncrement();
                    SimpleBeanFactoryRegistryTest.this.beanName = UUID.randomUUID().toString();
                    SimpleBeanFactoryRegistryTest.this.defaultListableBeanFactory.registerSingleton(
                        SimpleBeanFactoryRegistryTest.this.beanName, SimpleBeanFactoryRegistryTest.this.beanName);
                    System.out.println(Thread.currentThread().getName() + "创建Singleton："
                        + SimpleBeanFactoryRegistryTest.this.beanName);
                } catch (final InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    // 造出了产品
                    SimpleBeanFactoryRegistryTest.this.producer.release(1);
                }
            }
        }
    }

    class BeanRegistryConsumer implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
                    if (ai.get() > 100) {
                        break;
                    }
                    SimpleBeanFactoryRegistryTest.this.producer.acquire();
                    // 新增后beanName存在于beanDefinitions、beanDefinitionNames
                    if (SimpleBeanFactoryRegistryTest.this.beanName != null
                        && !SimpleBeanFactoryRegistryTest.this.beanName.equals("")) {
                        assertThat(Arrays
                            .asList(SimpleBeanFactoryRegistryTest.this.defaultListableBeanFactory.getSingletonNames())
                            .contains(SimpleBeanFactoryRegistryTest.this.beanName)).isTrue();
                        assertThat(SimpleBeanFactoryRegistryTest.this.defaultListableBeanFactory
                            .getSingleton(SimpleBeanFactoryRegistryTest.this.beanName)).isNotNull();
                    }
                    SimpleBeanFactoryRegistryTest.this.defaultListableBeanFactory
                        .removeBeanDefinition(SimpleBeanFactoryRegistryTest.this.beanName);
                    System.out.println(
                        Thread.currentThread().getName() + "消费了bean: " + SimpleBeanFactoryRegistryTest.this.beanName);
                } catch (final InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    SimpleBeanFactoryRegistryTest.this.consumer.release(1);
                }
            }
        }
    }

}
