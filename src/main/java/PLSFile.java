import java.io.*;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Branimir on 30.1.2015..
 */
public class PLSFile implements Iterable<PLSEntry>{

    private int numberOfEntries;
    private int version;

    private List<PLSEntry> entries;

    private File inputFile;

    private PLSFile(){}

    public int getNumberOfEntries(){return this.numberOfEntries;}
    public int getVersion(){return this.version;}

    public static PLSFile fromFile(File inputFile) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFile));
        PLSFile tempFile = new PLSFile();

        String line;
        /** [playlist] */
        line = bufferedReader.readLine();
        if(!line.equalsIgnoreCase("[playlist]")){
            throw new IOException("Not valid PLS file");
        }

        /** numberofentries=n */
        line = bufferedReader.readLine();
        try {
            tempFile.numberOfEntries = Integer.parseInt(line.split("=")[1].trim());
        } catch (NumberFormatException e){
            throw new IOException("Not valid PLS file, 'numberofentries' line corrupt");
        }

        /** Entries... */
        String fileName;
        String title;
        int length;
        int i = tempFile.numberOfEntries;
        while(i > 0){
            line = bufferedReader.readLine();
            fileName = line.split("=")[1].trim();

            line = bufferedReader.readLine();
            title = line.split("=")[1].trim();

            line = bufferedReader.readLine();
            try {
                length = Integer.parseInt(line.split("=")[1].trim());
            } catch (NumberFormatException e){
                length = -1;
            }

            tempFile.entries.add(new PLSEntry(
                    fileName, title, length
            ));

            i--;
        }

        line = bufferedReader.readLine();
        try {
            tempFile.version = Integer.parseInt(line.split("=")[1].trim());
        } catch (NumberFormatException e){
            tempFile.version = 2;
        }

        return tempFile;
    }

    @Override
    public Iterator<PLSEntry> iterator() {
        return this.entries.iterator();
    }
}
