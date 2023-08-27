import java.io.Console;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        ProcessRunner first = new ProcessRunner("./test.sh");
        first.showResult();
    }
}