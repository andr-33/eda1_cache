package edai.cachedb;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Cache implements ICache{

    MyTreeMap<String, String> treeStorage = new MyTreeMap<>();

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
        try{
            return treeStorage.getValueByKey(key);
        }
        catch (KeyNotFoundException e){
            return defaultValue;
        }
    }

    @Override
    public boolean exists(String key) {
        return treeStorage.exist(key);
    }

    @Override
    public void put(String key, String value, FileWriter file) throws IOException {
        if(key.contains("-")||value.contains("-")){
            throw new KeyInvalidName();
        }
        else{
            treeStorage.saveEntry(key,value,file);
        }
    }

    public void loadEntry(String key, String value) throws KeyInvalidName{
        if(key.contains("-") || value.contains("-")){
            throw new KeyInvalidName();
        }
        else{
            treeStorage.put(key,value);
        }
    }

    @Override
    public void addNew(String key, String value) {
        if(key.contains("-")||value.contains("-")){
            throw new KeyInvalidName();
        }
        else {
            treeStorage.addNew(key, value);
        }
    }

    @Override
    public void remove(String key) throws IOException {
        FileWriter fileWriter = new FileWriter("cacheStorage.txt");
        treeStorage.removeOneByKey(key);
        updateFile(fileWriter, treeStorage);
        fileWriter.close();
    }

    private void updateFile(FileWriter file, MyTreeMap<String, String> treeUpdated) throws IOException {
        String[] data = treeUpdated.listData();
        for(int i=0; i < data.length; i++){
            file.write(data[i]);
            file.write('\n');
        }
    }

    @Override
    public int size() {
        return treeStorage.size();
    }

    public void loadCache() throws IOException {
        FileReader cacheFile = new FileReader("cacheStorage.txt");
        BufferedReader br = new BufferedReader(cacheFile);
        String currentLine = "";

        while((currentLine = br.readLine()) != null){
            String[] entryData = currentLine.split("-");
            String key = entryData[0];
            String value = entryData[1];
            loadEntry(key,value);
        }

        cacheFile.close();
    }

}
