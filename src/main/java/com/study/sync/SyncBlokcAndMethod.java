package com.study.sync;

public class SyncBlokcAndMethod {

    public void syncsTask() {
        synchronized (this) {
            System.out.println("hello world");
        }
    }

    public synchronized void syncTask() {
        System.out.println("hello again");
    }
}
