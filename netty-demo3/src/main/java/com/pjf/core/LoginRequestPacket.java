package com.pjf.core;

/**
 * @Author: pjf
 * @CreateTime: 2024-10-14  17:06
 * @Description: TODO
 * @Version: 1.0
 */
public class LoginRequestPacket extends Packet{
    private Integer userId;

    private String username;

    private String password;

    @Override
    public Byte getCommand() {
        return Command.LOGIN_REQUEST;
    }
}
