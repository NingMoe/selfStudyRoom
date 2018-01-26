package com.ls.spt.basic.entity;

import java.util.TreeMap;

public class SmsDictionary<T1, T2> extends TreeMap<T1,T2> {

    /**
     * 
     */
    private static final long serialVersionUID = 7587471769132631021L;
    
    public int Count(){
        return size();
    }

    
    public void Add(T1 key, T2 value) {
        this.put(key,  value);
    }

}
