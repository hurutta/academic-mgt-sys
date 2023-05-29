package com.example.restclient.config;

import com.example.grpcproto.v1.StudentServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChannelConfig {

    @Bean
    public ManagedChannel getChannel() {
        return ManagedChannelBuilder
                .forAddress("localhost", 9090)
                .usePlaintext()
                .build();
    }

    @Bean
    public StudentServiceGrpc.StudentServiceBlockingStub getBlockingStub(ManagedChannel channel) {
        return StudentServiceGrpc.newBlockingStub(channel);
    }

}
