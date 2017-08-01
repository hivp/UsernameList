package com.company.usercheck.domain;

/**
 * Class to create a Generic Pair type
 * @author hugo
 *
 * @param <K>
 * @param <V>
 */
public class Result<K, V> implements Pair<K, V>{

    private K key;
    private V value;
    
    public Result(K key, V value) {
	this.key = key;
	this.value = value;
    }    
    
    public K getBoolean()	{ return key; }
    public V getSuggestions() { return value; }

}
