import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        // prepare for emulator
/*
        ProcessRunner emulator = new ProcessRunner("./scripts/run_emulator.sh Android-11-hehe");

        (new ProcessRunner("./scripts/dump_memory.sh")).waitFor();
        emulator.waitFor();
*/
        File malwarePath = new File("./malware");
        List<String> malwares = Arrays.asList(Objects.requireNonNull(malwarePath.list()));
        malwares.forEach(file -> {
            // create AVD
            // start emulator
            // wait for emulator successfully boot
            // prepare environment
            // wait 2.25 min
            // start dump memory
            // wait 7 min
            // start dump memory again
            // stop emulator
            // quit monitor
            // delete AVD
        });
    }
}