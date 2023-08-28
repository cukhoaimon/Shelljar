import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

class StreamEater extends Thread {
    BufferedReader br;
    String output;

    /**
     * Construct a StreamEater on an InputStream.
     */
    public StreamEater(InputStream is) {
        this.br = new BufferedReader(new InputStreamReader(is));
    }

    public void run() {
        StringBuilder builder = new StringBuilder();

        try {
            String line;
            while ((line = br.readLine()) != null) {
                // System.out.println(line);
                builder.append(line);
                builder.append("\n");
            }

        } catch (IOException e) {
            // Do something to handle exception
        } finally {
            try {
                br.close();
            } catch (Exception ignored) {}

            output = !builder.isEmpty() ? builder.toString() : "empty";
        }
    }

    public String getOutput() { return this.output; }
}
