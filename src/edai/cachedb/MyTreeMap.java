package edai.cachedb;

import java.util.Arrays;

public class MyTreeMap<TKey extends Comparable<TKey>, TValue> {
    private MyBinaryTree<MapEntry<TKey, TValue>> tree = new MyBinaryTree<>();

    public boolean isEmpty(){
        return tree.isEmpty();
    }

    public int size(){
        return tree.size();
    }

    public String[] listData(){
        String[] outPuts = new String[tree.size()];
        Object[] values = listValues();
        Object[] keys =  listKeys();

        String currentValue = "";
        String currentKey = "";

        for(int i = 0; i< tree.size(); i++){
            currentValue = String.valueOf(values[i]);
            currentKey = String.valueOf(keys[i]);
            outPuts[i] = currentKey+" - "+currentValue;
        }
        return outPuts;
    }

    public Object[] listValues(){
        Object[] entries = tree.listData();
        Object[] outputs = new Object[entries.length];

        for(int i = 0; i< entries.length; i++){
            //Un entry es el conjunto clave-valor
            MapEntry<TKey, TValue> entry = (MapEntry<TKey, TValue>) entries[i];
            outputs[i] = entry.getValue();
        }
        return outputs;
    }

    public Object[] listKeys(){
        Object[] entries = tree.listData();
        Object[] outputs = new Object[entries.length];

        for(int i = 0; i< entries.length; i++){
            MapEntry<TKey, TValue> entry = (MapEntry<TKey, TValue>) entries[i];
            outputs[i] = entry.getKey();
        }
        return outputs;
    }

    public void put(TKey newKey, TValue newValue){
        MapEntry<TKey,TValue> newEntry = new MapEntry<>(newKey,newValue);
        tree.insert(newEntry);
    }

    public boolean exist(TKey key){
        if(tree.search(createEntryForSearch(key)) != null){
            return true;
        }
        else{
            return false;
        }
    }
    public TValue getValueByKey(TKey key){
        var treeNode = tree.search(createEntryForSearch(key));
        if(treeNode == null) throw new KeyNotFoundException();

        MapEntry<TKey,TValue> entry = treeNode.getDataNode();
        return entry.getValue();
    }

    public boolean removeOneByKey(TKey key){
        if(exist(key) == false) return false;

        else{
            tree.remove(createEntryForSearch(key));
            return true;
        }
    }
    private MapEntry<TKey,TValue> createEntryForSearch(TKey keyToSearch){
        return new MapEntry<TKey,TValue>(keyToSearch,null);
    }
}
