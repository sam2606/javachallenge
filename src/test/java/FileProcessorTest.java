import com.demo.util.FileProcessor;

public class FileProcessorTest {

    public static void main(String[] args) {
        FileProcessor process = new FileProcessor("/Users/sameer.gaherwar/Desktop/test");
        process.printWordCounts();
    }
}
