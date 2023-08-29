import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        File malwarePath = new File("./malware");
        List<String> malwares = Arrays.asList(Objects.requireNonNull(malwarePath.list()));
        malwares.forEach(file -> {
            try {
                // create AVD
                (new ProcessRunner("./scripts/create_emulator.sh")).run();

                // start emulator
                ProcessRunner process = new ProcessRunner("./scripts/run_emulator.sh");

                // wait for emulator successfully boot
                (new ProcessRunner("./scripts/wait_for.sh "
                        + (new File("./malware/" + file)).getAbsolutePath())).waitFor();

                // prepare environment
                List<String> packages = getPackages("./PKGS");
                packages.forEach(pkg -> {
                    try {
                        (new ProcessRunner("./scripts/prepare_env.sh " + pkg)).run();
                    } catch (InterruptedException | IOException e) {
                        throw new RuntimeException(e);
                    }
                });

                // wait 2.25 min and dump memory
                (new ProcessRunner("./scripts/dump_memory.sh 135 /dataset/first/"
                        + file + ".2.25m.bin")).run();

                // wait 7 min and dump memory again
                (new ProcessRunner("./scripts/dump_memory.sh 420 /dataset/second/"
                        + file + ".7m.bin")).run();

                // stop emulator
                // quit monitor
                // delete AVD
                (new ProcessRunner("./scripts/clean_emulator.sh")).run();

            } catch (InterruptedException | IOException e) {
                throw new RuntimeException(e);
            }
        });
    }



    private static List<String> getPackages(String fileName) {
        List<String> packages = new java.util.ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line = br.readLine();

            while (line != null) {
                packages.add(line.substring(8));
                line = br.readLine();
            }
            return packages;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
