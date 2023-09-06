import java.io.IOException;
import java.time.Duration;
import java.time.Instant;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
/*        try {
            DatasetCreator.createDataset(args[0], args[1], args[2]);
        }
        catch (IOException ignored) {}*/


        Instant start = Instant.now();
        DatasetCreator.convertDataset("./src/resources/dataset/", "./src/resources/dataset/output/");
        Instant end = Instant.now();
        System.out.println("Time execution: " + Duration.between(start, end).toSeconds());
    }
}
