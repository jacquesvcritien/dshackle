package io.emeraldpay.dshackle.test

import com.fasterxml.jackson.core.Version
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.module.SimpleModule
import io.emeraldpay.dshackle.upstream.Upstream
import io.emeraldpay.grpc.Chain
import io.infinitape.etherjar.rpc.Batch
import io.infinitape.etherjar.rpc.ExecutableBatch
import io.infinitape.etherjar.rpc.JacksonRpcConverter
import io.infinitape.etherjar.rpc.RpcCall
import io.infinitape.etherjar.rpc.RpcClient
import io.infinitape.etherjar.rpc.transport.BatchStatus
import spock.mock.MockingApi

import java.text.SimpleDateFormat
import java.util.concurrent.CompletableFuture

class TestingCommons {

    static ObjectMapper objectMapper() {
        def module = new SimpleModule("EmeraldDShackle", new Version(1, 0, 0, null, null, null))

        def objectMapper = new ObjectMapper()
        objectMapper.registerModule(module)
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        objectMapper
                .setDateFormat(new SimpleDateFormat("yyyy-MM-dd\'T\'HH:mm:ss.SSS"))
                .setTimeZone(TimeZone.getTimeZone("UTC"))

        return objectMapper
    }

    static EthereumApiMock api(RpcClient rpcClient, Upstream upstream) {
        return new EthereumApiMock(rpcClient, objectMapper(), Chain.ETHEREUM, upstream)
    }

    static JacksonRpcConverter rpcConverter() {
        return new JacksonRpcConverter(objectMapper())
    }
}