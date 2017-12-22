package com.gmsj.core.conf.security;


import java.util.HashMap;
import java.util.Map;

/**
 * Created Ovrille on 9/21/16.
 */
public class Session {

    private static final ThreadLocal<Session> threadSession = new ThreadLocal<Session>();
    private static final ThreadLocal<HashMap> threadParam = new ThreadLocal<HashMap>();

    private Long userId;


    private String username;

    public static Session buildSession() {
        Session session = new Session();
        return session;
    }

    public static Session buildSession(Long  userId,String userName) {
        Session session = new Session();
        session.userId = userId;
        session.username = userName;
        return session;
    }

    public static ThreadLocal<Session> getThreadSession(){
        return threadSession;
    }

    public Session withUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public Session withUsername(String username) {
        this.username = username;
        return this;
    }

    public static void persistenceCurrentSession(Session session) {
        Session profile = threadSession.get();
        if (profile == null) {
        	threadSession.set(session);
        }
    }

    public static Object get(String key) {
        if (threadParam.get()==null)
            return null;
        return threadParam.get().get(key);
    }

    public static void put(String key, Object value) {
        HashMap paramMap;
        if (threadParam.get()==null){
            paramMap = new HashMap();
        }else{
            paramMap = threadParam.get();
        }
        paramMap.put(key, value);
        threadParam.set(paramMap);
    }

    public static Session get() {    
        return threadSession.get();    
    }    
    
    public static Long getUserId() {
        return threadSession.get().userId;
    }
    
    public String getUserIdToString() {
        return userId.toString();
    }


    
    public static String getUsername() {
        return threadSession.get().username;
    }



    public Map<String, Object> getOtherMaps() {
        Map<String, Object> map = new HashMap<>();
        map.put("username", this.username);
        return map;
    }

    public static void remove(){  
    	if (threadSession!=null) {
    		threadSession.remove();  
		}
        if (threadParam!=null) {
            threadParam.remove();
        }
    	
    }  
}
