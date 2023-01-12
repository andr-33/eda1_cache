import edai.cachedb.Cache;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

@Command(name = "runCache", description = "Esta clase ejecutara la cache desde la terminal")
public class AppCLI implements Runnable{
    Cache myCache = new Cache();
    public FileWriter fileWriter = new FileWriter("cacheStorage.txt");

    @Option(names = {"-ga", "-getAll"}, description = "Obtine todas las claves y los valores")
        public boolean getAll;
    @Option(names = {"-g", "-get"}, description = "Obtine el valor de una clave")
        public String getValueByKey = "";
    @Option(names = {"-e", "-exists"}, description = "Saber si una clave existe")
        public String existsKey = "";
    @Option(names = {"-r", "-remove"}, description = "Elimina la clave y el valor")
        public String removeKey = "";
    @Option(names = {"-s", "-size"}, description = "Obtine la cantidad de datos guardados")
        public boolean size;
    @Option(names = {"-ld", "-load"}, description = "Llena la cache con los datos del archivo")
        public boolean load;
    @Option(names = {"-p", "-put"}, description = "Ingresar o actualizar una nueva clave-valor")
        public boolean put;
    @Option(names = {"-n", "-new"}, description = "Ingresar un nuevo valor")
        public boolean addNew;
    @Option(names = {"-k", "-key"}, description = "-k seguido la llave")
        public String key;
    @Option(names = {"-v", "-value"}, description = "-v seguido el valor")
        public String value;

    public AppCLI() throws IOException {
    }

    @Override
    public void run(){
        System.out.println("");
        if(load){
            try {
                myCache.loadCache();
                System.out.print("Cache cargada desde los ultimos datos guardados");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        if(size){
            System.out.print(myCache.size());
        }

        if(removeKey != ""){
            try {
                myCache.remove(removeKey);
                System.out.print("Se elimino la clave y el valor");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        if(existsKey != ""){
            if(myCache.exists(existsKey)){
                System.out.print("La clave ya existe");
            }
            else{
                System.out.print("La clave aun no existe");
            }
        }

        if(getValueByKey != ""){
            System.out.print(myCache.get(getValueByKey));
        }

        if(getAll){
            System.out.print(Arrays.toString(myCache.getAll()));
        }

        if(put){
            try {
                myCache.put(key,value,fileWriter);
                fileWriter.close();
                System.out.print("Se ingresaron los valores siguientes: "+key+"-"+value);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        if(addNew){
            myCache.addNew(key,value);
            System.out.print("Se realizo el nuevo registro con los valores: "+key+"-"+value);
        }
    }

    public static void main(String[] args) throws IOException {
        int exitCode = new CommandLine(new AppCLI()).execute(args);
        System.exit(exitCode);
    }
}