package HashMap;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.ExecutionException;

public class Main {
    public static class HashMap<K,V>{
        private class HMNode{
            K key;
            V value;

            HMNode(K key,V value){
                this.key=key;
                this.value=value;
            }
        }

        private int size;
        private LinkedList<HMNode>[] buckets;

        public HashMap(){
            initBuckets(4);
            size=0;
        }

        private void initBuckets(int N){
            buckets = new LinkedList[N];
            for(int bi=0;bi<buckets.length;bi++){
                buckets[bi] =new LinkedList<>();
            }
        }

        public void put(K key,V value) throws Exception{
            int bi = hashfn(key);
            int di = getIndexWithinBucket(key,bi);

            if(di!=-1){
                HMNode node = buckets[bi].get(di);
                node.value=value;
            }else{
                //insert
                HMNode node=new HMNode(key,value);
                buckets[bi].add(node);
                size++;
            }

            double lamba = size * 1.0 / buckets.length;
            if(lamba>2.0){
                rehash();
            }
        }

        private void rehash() throws Exception {
            LinkedList<HMNode>[] oba = buckets;
            initBuckets(oba.length*2);
            size=0;
            for(int i=0;i< oba.length;i++){
                for(HMNode node: oba[i]){
                    put(node.key,node.value);
                }
            }
        }

        private int hashfn(K key){
            int hc = key.hashCode();
            return Math.abs(hc) % buckets.length;
        }

        private int getIndexWithinBucket(K key,int value){
            int di=0;
            for(HMNode node: buckets[di]){
                if(node.key.equals(key)){
                    return di;
                }
                di++;
            }
            return -1;
        }

        public V get(K key) throws Exception {
            int bi = hashfn(key);
            int di = getIndexWithinBucket(key,bi);

            if(di!=-1){
                HMNode node = buckets[bi].get(di);
                return node.value;
            }else {
                return null;
            }
        }

        public int size(){
            return size;
        }

        private V remove(K key){
            int bi = hashfn(key);
            int di = getIndexWithinBucket(key,bi);

            if(di!=-1){
                HMNode node = buckets[bi].remove(di);
                size--;
                return node.value;
            }else {
                return null;
            }
        }

        public boolean containsKey(K key){
            int bi = hashfn(key);
            int di = getIndexWithinBucket(key,bi);

            if(di!=-1){
                return true;
            }else {
                return false;
            }
        }

        private ArrayList<K> keySet() throws Exception{
            ArrayList<K> keys = new ArrayList<>();
            for(int i=0;i<buckets.length;i++){
                for(HMNode node: buckets[i]){
                    keys.add(node.key);
                }
            }
            return keys;
        }

    }
}
