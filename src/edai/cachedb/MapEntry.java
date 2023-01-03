package edai.cachedb;

public class MapEntry <TKey extends Comparable<TKey>,TValue> implements Comparable<MapEntry<TKey,TValue>> {
    private TKey key;
    private TValue value;

    public MapEntry(TKey key, TValue value){
        this.key = key;
        this.value = value;
    }

    public TKey getKey(){
        return this.key;
    }

    public TValue getValue(){
        return this.value;
    }

    public int compareTo(MapEntry<TKey,TValue> other){
        return this.key.compareTo(other.key);
    }
}
