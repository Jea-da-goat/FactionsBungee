package com.itndev.factions.RedisConnecter;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import io.lettuce.core.api.sync.RedisStreamCommands;

public class Redis {

    private static RedisClient client = null;
    private static StatefulRedisConnection<String, String> connection = null;
    private static RedisStreamCommands<String, String> commands = null;
    private static RedisCommands<String, String> setcommands = null;
    private static String LastID_INPUT = "0-0";
    private static String LastID_INNER = "0-0";

    private static String redis_address = "220.118.89.72";
    private static Integer redis_port = 6614;
    private static String redis_password = "54rg46ujhy7ju57wujndt35ytgryeutwefer4rt34rd34rsfg6hdf43truhgfwgr348yfgcs";
    private static Boolean sslEnabled = true;

    private static Boolean isClosed = false;

    public static void setRedis_address(String address) {
        redis_address = address;
    }

    public static void setRedis_port(Integer port) {
        redis_port = port;
    }

    public static void setRedis_password(String password) {
        redis_password = password;
    }

    public static void setSslEnabled(Boolean enabled) {
        sslEnabled = enabled;
    }

    public static void setLastID_INPUT(String new_LastID_INPUT) {
        LastID_INPUT = new_LastID_INPUT;
    }


    public static void setLastID_INNER(String new_LastID_INNER) {
        LastID_INNER = new_LastID_INNER;
    }
    // GET METHOD
    public static String get_LastID_INPUT() {
        return LastID_INPUT;
    }

    public static String get_LastID_INNER() {
        return LastID_INNER;
    }

    public static RedisCommands<String, String> getSetcommands() {
        return setcommands;
    }

    public static Boolean get_isClosed() {
        return isClosed;
    }

    // CONNECT TO REDIS
    public static void RedisConnect() {
        RedisURI redisURI = RedisURI.Builder.redis(redis_address, redis_port).withPassword(redis_password.toCharArray()).build();
        client = RedisClient.create(redisURI);
        connection = client.connect();
        commands = connection.sync();
        setcommands = connection.sync();
    }

    public static void RedisDisConnect() {
        connection.close();
        isClosed = true;
    }

    public static StatefulRedisConnection<String, String> getRedisConnection() {
        if (connection == null || !connection.isOpen()) {
            connection = client.connect();
        }
        return connection;
    }

    public static RedisStreamCommands<String, String> getRedisCommands() {
        if(commands == null) {
            commands = getRedisConnection().sync();
        }
        return commands;
    }
}
