package khosro.ceit.aut.ac.ir.utils;

import khosro.model.Note;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * handel file operations
 */
public class FileUtils {

    public static final String NOTES_PATH = "./notes/";

    //It's a static initializer. It's executed when the class is loaded.
    //It's similar to constructor
    static {
        boolean isSuccessful = new File(NOTES_PATH).mkdirs();
        System.out.println("Creating " + NOTES_PATH + " directory is successful: " + isSuccessful);
    }

    /**
     * get all files in saving directory
     * @return files saved.
     */
    public static File[] getFilesInDirectory() {
        return new File(NOTES_PATH).listFiles();
    }


    /**
     * reads context of saved note.
     * @param file file to be read
     * @return context of file
     * @throws IOException if file not found
     */
    public static String fileReader(File file) throws IOException {
        System.out.println(file.getAbsolutePath());
       //return readingInPut(file.getAbsolutePath());
        return readingInPutBuff(file.getAbsolutePath());
    }


    /**
     * save a note .
     * @param content context of note to be saved
     * @throws IOException if something went wrong
     */
    public static void fileWriter(String content) throws IOException {
        String fileName = getProperFileName(content);
       // writingOutPut(content,fileName);
        writingOutPutBuff(content,fileName);
    }


    /**
     * load a saved object of note class
     * @param fileName file that contains the note class
     * @return saved note
     * @throws IOException if something goes wrong
     * @throws ClassNotFoundException if saved note defers from current class
     */
    public static Note readingInputObj (String fileName) throws IOException, ClassNotFoundException
    {
        try (FileInputStream fileInputStream = new FileInputStream(  fileName);
        ObjectInputStream objectInputStream =  new ObjectInputStream(fileInputStream)){
            return (Note) objectInputStream.readObject();
        }
    }


    /**
     * load a saved note
     * @param fileName file to be load
     * @return context of file
     * @throws IOException if something goes wrong
     */
    public static String readingInPut(String fileName) throws IOException {
        StringBuilder text = new StringBuilder();
        try (FileInputStream inputStream = new FileInputStream(new File(fileName));){
            while (inputStream.available()!= 0) {
                text.append((char) inputStream.read());
            }
        }
        return text.toString();
    }

    /**
     * load a saved note
     * @param fileName file to be load
     * @return context of file
     * @throws IOException if something goes wrong
     */
    public static String readingInPutBuff(String fileName) throws IOException {
        StringBuilder text = new StringBuilder();
        try (FileInputStream inputStream = new FileInputStream(new File(fileName));
        BufferedInputStream buffInput = new BufferedInputStream(inputStream,512)){
            int countByte = buffInput.available();
            for(int i=0; i<countByte ; i++)
                text.append((char) buffInput.read());
        }
        return text.toString();
    }


    /**
     * save a object of note class
     * @param fileName file that this note should be saved to.
     * @param note note to be saved
     * @throws IOException if something goes wrong
     */
    public static void writingOutputObj (Note note,String fileName) throws IOException
    {
        String content = note.getContent();
        if(!infoSaved(content))
            note.setContent(makeInfoFile(fileName) + content );

        try (FileOutputStream fileInputStream = new FileOutputStream(NOTES_PATH + fileName);
             ObjectOutputStream objectInputStream =  new ObjectOutputStream(fileInputStream))
        {
            objectInputStream.writeObject(note);
        }
    }


    /**
     * save a note into file.
     * @param content context to be saved
     * @param fileName note would be saved into this file
     * @throws IOException  if something goes wrong
     */
    public static void writingOutPut(String content, String fileName) throws IOException {
        if(!infoSaved(content))
            content = makeInfoFile(fileName) + content;

        try (FileOutputStream fileOutputStream = new FileOutputStream(new File(NOTES_PATH + fileName));){
            for(Character character : content.toCharArray())
                fileOutputStream.write((int)character);
        }
    }


    /**
     * save a note into file.
     * @param content context to be saved
     * @param fileName note would be saved into this file
     * @throws IOException  if something goes wrong
     */
    public static void writingOutPutBuff(String content, String fileName) throws IOException {
        if(!infoSaved(content))
            content = makeInfoFile(fileName) + content;

        try (FileOutputStream fileOutputStream = new FileOutputStream(new File(NOTES_PATH + fileName));
        BufferedOutputStream outputStream = new BufferedOutputStream(fileOutputStream,512)){
            for(Character character : content.toCharArray())
                outputStream.write((int)character);
            outputStream.flush();
        }
    }


    /**
     * make a proper name for a note to be showed in list .
     * contains name of file and date of modifying
     * @param fileName name of file .
     * @return proper name for this note
     */
    public static String getNameList(String fileName)
    {
        String context = null;
        try {
            if(fileName.endsWith(".bin")) {
                Note note = readingInputObj(NOTES_PATH + fileName);
                context = note.getContent();
            }
            if(fileName.endsWith(".txt"))
                context = readingInPutBuff(NOTES_PATH + fileName);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        HashMap<String,String> info = getInfoFile(context);
        return info.get("Name") + " ; " + info.get("Date");
    }


    /**
     * make a name for a file to be saved.
     * @param content content of this note
     * @return name for this note
     */
    private static String getProperFileName(String content) {
             // this method is not used at all

        int loc = content.indexOf("\n");
        if (loc != -1) {
            return content.substring(0, loc) + ".txt" ;
        }
        if (!content.isEmpty()) {
            return content + ".txt" ;
        }
        return System.currentTimeMillis() + "_new file.txt";
    }


    /**
     * make the info saving includes name of file and current date.
     * @param fileName name of this file
     * @return info of this file to be saved
     */
    private static String makeInfoFile(String fileName)
    {
        SimpleDateFormat dateForm = new SimpleDateFormat();
        return "__________ info __________\n" +
                "Name: " + fileName + "\n" +
                "Date: " + dateForm.format(new Date()) + "\n" +
                "__________________________\n";
    }

    /**
     * checks if info are saved at begin of this file
     * @param context context of file
     * @return true if is saved else false
     */
    private static boolean infoSaved (String context)
    {
        return context.startsWith("__________ info __________");
    }


    /**
     * get date and name of of this context .
     * for date use the key of "Date" and for name "Name"
     * @param context context to be checked
     * @return name and date of this context. if no name and date are saved, returns a hashMap with null values
     */
    public static HashMap<String,String> getInfoFile (String context)
    {
        HashMap<String,String> info = new HashMap<>();
        if( ! infoSaved(context) )
        {
            info.put("Date",null);
            info.put("Name",null);
            return info;
        }
        Pattern pattern = Pattern.compile(
                "Name: ([a-zA-Z0-9 _.]*)\n"+
                "Date: (\\d\\d?/\\d\\d?/\\d\\d, \\d:\\d\\d PM|AM)\n");

        Matcher matcher = pattern.matcher(context);
        if(matcher.find()) {
            info.put("Date",matcher.group(2));
            info.put("Name",matcher.group(1));
        }
        return info;
    }

}
