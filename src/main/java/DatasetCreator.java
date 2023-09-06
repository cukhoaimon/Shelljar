import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public final class DatasetCreator {
    public static void createDataset(
            String malwarePath,
            String scriptsPath,
            String outputPath
    ) throws IOException, InterruptedException {
        /*File PKGS = new File(outputPath + "PKGS");
        if (PKGS.createNewFile()) {
            System.out.println("File created: " + PKGS.getName());
        }
        else {
            System.out.println("File already exists.");
        }*/

        File malwareSource = new File(malwarePath);
        List<String> malwares = Arrays.asList(Objects.requireNonNull(malwareSource.list()));

        System.out.println("[INFO] Found " + malwares.toArray().length + " malwares file.");

        malwares.forEach(file -> {
            try {
                // create AVD
                (new ProcessRunner(scriptsPath + "create_emulator.sh")).run();

                // start emulator
                ProcessRunner process = new ProcessRunner(scriptsPath + "run_emulator.sh");

                // wait for emulator successfully boot
                (new ProcessRunner(scriptsPath + "wait_for.sh "
                        + (new File(malwarePath + file)).getAbsolutePath())).waitFor();

                // prepare environment
                List<String> packages = getPackages(outputPath + "PKGS");
                packages.forEach(pkg -> {
                    try {
                        (new ProcessRunner(scriptsPath + "prepare_env.sh " + pkg)).run();
                    } catch (InterruptedException | IOException e) {
                        throw new RuntimeException(e);
                    }
                });

                // wait 2.25 min and dump memory
                (new ProcessRunner(
                        scriptsPath + "dump_memory.sh 135 " + outputPath + file + ".2.25m.bin")).run();

                // wait 7 min and dump memory again
                (new ProcessRunner(
                        scriptsPath + "dump_memory.sh 420 " + outputPath + file + ".7m.bin")).run();

                // stop, quit monitor
                // delete AVD
                (new ProcessRunner(scriptsPath + "clean_emulator.sh")).run();

            } catch (InterruptedException | IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public static void convertDataset(String datasetPath, String outputPath) {
        File dataset = new File(datasetPath);
        File outPath = new File(outputPath);
        if (!outPath.exists()) {
            boolean mkdirs = outPath.mkdirs();
            if (mkdirs) {
                System.out.println("Output path is created.");
            }
        }

        List<String> binaryFiles = Arrays.asList(Objects.requireNonNull(dataset.list()));
        System.out.println("[INFO] Found " + binaryFiles.toArray().length + " file ready to convert.");

        binaryFiles.forEach(file -> {
            if (file.endsWith(".bin")) {
                try {
                    System.out.println("Converting " + file);
                    PNGConverter.convert(datasetPath + file, outputPath + file + ".png");
                    System.out.println("Done.");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
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
