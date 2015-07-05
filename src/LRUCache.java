import java.util.*;

/**
 * Created by Feiyu on 2015/7/4 0004.
 *
 * Design and implement a data structure for Least Recently Used (LRU) cache.
 * It should support the following operations: get and set.

 get(key) - Get the value (will always be positive) of the key if the key exists in the cache,
 otherwise return -1.
 set(key, value) - Set or insert the value if the key is not already present.
 When the cache reached its capacity,
 it should invalidate the least recently used item before inserting a new item.
 **/

/**
 * Time Out
 */
class LRUCache1 {

    private Map<Integer,Pair<Integer,Integer>> map;
    private int capacity;

    public LRUCache1(int capacity) {
        this.map = new HashMap<>();
        this.capacity=capacity;
    }

    public int get(int key) {
        if(map.containsKey(key)){
            Pair<Integer,Integer> entry = map.get(key);
            entry.setValue(entry.getValue() + 1);
            return map.get(key).getKey();
        }
        return -1;
    }

    public void set(int key, int value) {
        if(map.containsKey(key)){
            map.get(key).setValue(value);
        }else{
            if(map.size()<capacity){
                map.put(key,new Pair<Integer, Integer>(value,0));
            }else{
                Object[] array = map.keySet().toArray();
                Arrays.sort(array, new Comparator<Object>() {
                    @Override
                    public int compare(Object o1, Object o2) {
                        return map.get(o1).getValue() - map.get(o2).getValue();
                    }
                });
                map.remove(array[0]);
                map.put(key, new Pair<Integer, Integer>(value, 0));
            }
        }
    }

    private class Pair<K,V>{
        private K key;
        private V value;

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }
    }
}

/**
 * Time Out
 */
public class LRUCache {

    private Map<Integer,Integer> valueMap = new HashMap<>(),
            freqMap = new HashMap<>();
    private int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        if(valueMap.containsKey(key)){
            freqMap.put(key, freqMap.get(key) + 1);
            return valueMap.get(key);
        }else
            return -1;
    }

    public void set(int key, int value) {
        if(valueMap.containsKey(key)){
            valueMap.put(key, value);
        }else{
            if(valueMap.size()==capacity){
                Iterator<Integer> iter = freqMap.values().iterator();
                Iterator<Integer> keyIter = freqMap.keySet().iterator();
                int min=Integer.MAX_VALUE;
                int minKey = 0;
                int next ,nextKey;
                while(iter.hasNext()){
                    next = iter.next();
                    nextKey=keyIter.next();
                    if(next<min) {
                        min = next;
                        minKey = nextKey;
                    }
                }
                valueMap.remove(minKey);
                freqMap.remove(minKey);
            }
            freqMap.put(key,0);
            valueMap.put(key,value);
        }
    }

    static public void main(String... args){
        LRUCache lruCache = new LRUCache(1);
        lruCache.set(2,1);
        int x1=lruCache.get(2);
        lruCache.set(3,2);
        int x2=lruCache.get(2);
        int x3=lruCache.get(3);
    }
}

