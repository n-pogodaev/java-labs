import java.io.*;

public class Translator {
    private static String readFile(String filename) {
        StringBuilder result = new StringBuilder();
        Reader rd = null;
        try {
            rd = new InputStreamReader(new FileInputStream(filename));
            int next = 0;
            while ((next = rd.read()) != -1) {
                result.append(Character.toString(next));
            }
        }
        catch(IOException e) {
            System.err.println("Reading file error: " + e.getLocalizedMessage());
        }
        finally {
            if (rd != null) {
                try {
                    rd.close();
                }
                catch (IOException e) {
                    e.printStackTrace(System.err);
                }
            }
        }
        return result.toString();
    }
    private static void WriteStat(StatisticsCounter stat, String filename) {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename)));
            writer.write(stat.toString());
        }
        catch(IOException e) {
            System.err.println("Reading file error: " + e.getLocalizedMessage());
        }
        finally {
            if (writer != null) {
                try {
                    writer.close();
                }
                catch (IOException e) {
                    e.printStackTrace(System.err);
                }
            }
        }
    }
    public static void main(String[] args) {
        if (args.length != 3) {
            System.err.println("not enough or too many arguments, try again");
            return;
        }
        String in = readFile(args[1]);
        if (in.isEmpty()) {
            return;
        }
        Coder cd;
        if (args[0].equals("code")) {
            cd = new Encoder(in);
        }
        else if (args[0].equals("decode")) {
            cd = new Decoder(in);
        }
        else {
            System.err.println("wrong command, try code or decode");
            return;
        }
        Alphabet abc = new FileAlphabet(args[2]);
        StatisticsCounter stat = new StatisticsCounter();
        System.out.println(cd.translate(abc, stat));
        WriteStat(stat, "C:\\Users\\nik18\\IdeaProjects\\lab1\\src\\stat.txt");
    }
}
