package com.umaraliev.springkafkaproducer.utils;

import com.umaraliev.springkafkaproducer.dto.MessageDto;
import com.umaraliev.springkafkaproducer.model.OsEnum;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SmsGenerator {
    private static final int THREAD_COUNT = 4;
    private static final ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);

    private static final String TOPIC = "sms-topic";

    public static List<CompletableFuture<MessageDto>> generateSmsTextsAsync(int count) {
        List<CompletableFuture<MessageDto>> futures = new ArrayList<>();
        MessageDto messageDto = new MessageDto();
        for (int i = 0; i < count; i++) {
            CompletableFuture<MessageDto> future = CompletableFuture.supplyAsync(() -> {
                messageDto.manufacturer = RandomStringUtils.randomAlphabetic(16);
                messageDto.os =
                ProducerRecord<String, MessageDto> record = new ProducerRecord<>(TOPIC, messageDto);
                return messageDto;
            }, executor);
            futures.add(future);
        }
        return futures;
    }

}