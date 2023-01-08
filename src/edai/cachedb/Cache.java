package edai.cachedb;

public class Cache implements ICache{

    MyTreeMap<String, String> treeStorage;

    @Override
    public String[] getAll() {
        return treeStorage.listData();
    }

    @Override
    public String get(String key) {
        return treeStorage.getValueByKey(key);
    }

    @Override
    public String getOrDefault(String key, String defaultValue) {
        return null;
    }

    @Override
    public boolean exists(String key) {
        return treeStorage.exist(key);
    }

    @Override
    public void put(String key, String value) {

    }

    @Override
    public void addNew(String key, String value) {

    }

    @Override
    public void remove(String key) {
        treeStorage.removeOneByKey(key);
    }

    @Override
    public int size() {
        return treeStorage.size();
    }
}
