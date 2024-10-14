package com.pjf.core;

import lombok.Data;

/**
 * @Author: pjf
 * @CreateTime: 2024-10-14  17:02
 * @Description: TODO
 * @Version: 1.0
 */
@Data
public abstract  class Packet {
    /**
     * 协议版本
     */
    private Byte version = 1;

    /**
     * 指令
     */
    public abstract Byte getCommand();
}
