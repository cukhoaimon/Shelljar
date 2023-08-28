import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        // prepare for emulator
        ProcessRunner emulator = new ProcessRunner("./scripts/run_emulator.sh Android-11-hehe");

        (new ProcessRunner("./scripts/dump_memory.sh")).waitFor();
        emulator.waitFor();
        // script for shutdown emulator
        /*
        ProcessRunner shutdown = new ProcessRunner("adb -s emulator-5554 emu kill");
        shutdown.waitFor();
        */
    }
}