package com.pjf.seralizer;

public interface Serializer {

    /**
     * 序列化算法
     */
    byte getSerializerAlgorithm();

    /**
     * Java 对象转换成二进制数据
     */
    byte[] serialize(Object object);

    /**
     * 二进制数据转换成Java对象
     */
    <T> T deserialize(Class<T> clazz, byte[] bytes);
}