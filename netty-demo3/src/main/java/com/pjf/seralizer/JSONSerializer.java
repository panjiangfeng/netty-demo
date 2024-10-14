package com.pjf.seralizer;

import com.alibaba.fastjson.JSON;

/**
 * @Author: pjf
 * @CreateTime: 2024-10-14  17:12
 * @Description: TODO
 * @Version: 1.0
 */
public class JSONSerializer implements Serializer {
    @Override
    public byte getSerializerAlgorithm() {
        return SerializerAlgorithm.JSON;
    }

    @Override
    public byte[] serialize(Object object) {
        return JSON.toJSONBytes(object);
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {
        return JSON.parseObject(bytes, clazz);
    }
}
