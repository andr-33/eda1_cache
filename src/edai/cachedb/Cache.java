package edai.cachedb;

public class Cache implements ICache{

    @Override
    public String[] getAll() {
        return new String[0];
    }

    @Override
    public String get(String key) {
        return null;
    }

    @Override
    public String getOrDefault(String key, String defaultValue) {
        return null;
    }

    @Override
    public boolean exists(String key) {
        return false;
    }

    @Override
    public void put(String key, String value) {

    }

    @Override
    public void addNew(String key, String value) {

    }

    @Override
    public void remove(String key) {

    }

    @Override
    public int size() {
        return 0;
    }
}
