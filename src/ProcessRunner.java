import java.io.IOException;

public class ProcessRunner {
    Process process;
    StreamEater stdout;
    StreamEater stderr;
    int exitValue;

    ProcessRunner(String scripts) throws IOException {
        this.process = Runtime.getRuntime().exec(
                new String[]{"sh", "-c", scripts}
        );

        this.stdout = new StreamEater(this.process.getInputStream());
        this.stdout.start();

        this.stderr = new StreamEater(this.process.getErrorStream());
        this.stderr.start();
    }

    public void waitFor() throws InterruptedException {
        this.process.waitFor();
        this.exitValue = process.exitValue();
    }

    public String getOutput() { return this.stdout.getOutput(); }

    public String getError() { return this.stderr.getOutput(); }

    public int getExitValue() { return this.exitValue; }

    public void showResult() throws InterruptedException {
        this.waitFor();
        System.out.println("Output: \n" + this.getOutput());
        System.out.println("Error: \n" + this.getError());
        System.out.println("Exit code: \n" + this.getExitValue());
    }
}
