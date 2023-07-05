package com.umaraliev.springkafkaproducer.model;

import java.util.Random;

public enum OsEnum{
    IOS, ANDROID, WINDOWS, LINUX, MAC;

    public OsEnum getRamdomOs() {
        Random random = new Random();
        return values()[random.nextInt(values().length)];
    }
}
