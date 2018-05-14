import com.demo.util.FileProcessor;

public class FileProcessorTest {

    public static void main(String[] args) {
        FileProcessor process = new FileProcessor("path to file");
        process.printWordCounts();
    }
}
