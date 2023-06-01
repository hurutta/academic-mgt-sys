package com.example.restclient.config;

import com.example.grpcproto.v1.StudentServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChannelConfig {

    @Bean("name=javaChannel")
    public ManagedChannel getChannelForJavaServer() {
        return ManagedChannelBuilder
                .forAddress("localhost", 9090)
                .usePlaintext()
                .build();
    }

    @Bean("name=pythonChannel")
    public ManagedChannel getChannelForPythonServer() {
        return ManagedChannelBuilder
                .forAddress("localhost", 50051)
                .usePlaintext()
                .build();
    }

    @Bean("name=javaStub")
    public StudentServiceGrpc.StudentServiceBlockingStub getBlockingStubForJava(@Qualifier("name=javaChannel") ManagedChannel channel) {
        return StudentServiceGrpc.newBlockingStub(channel);
    }

    @Bean("name=pythonStub")
    public StudentServiceGrpc.StudentServiceBlockingStub getBlockingStubForPython(@Qualifier("name=pythonChannel") ManagedChannel channel) {
        return StudentServiceGrpc.newBlockingStub(channel);
    }

}
