import java.io.*;

import ar.com.hjg.pngj.FilterType;
import ar.com.hjg.pngj.ImageInfo;
import ar.com.hjg.pngj.PngWriter;

public final class PNGConverter {
    public static void convert(String input, String output) throws IOException {
        final int CHUNK_SIZE = 33554432; // 32mb per chunk
        // Dimensions hard coded to square
        final int SIDE = (int)((Math.sqrt(new File(input).length()) / 3));
        //final int SIDE = 1024;
        byte[] buffer = new byte[CHUNK_SIZE];
        int[] imgData = new int[SIDE * 3];// 3 Bytes per pixel

        FileInputStream fileInputStream = new FileInputStream(input);
        OutputStream os = new FileOutputStream(output);

        ImageInfo imi = new ImageInfo(SIDE, SIDE, 8, false);
        PngWriter pngw = new PngWriter(os, imi);
        // pngw.setCompLevel(9);// maximum compression
        pngw.setFilterType(FilterType.FILTER_ADAPTIVE_FAST);
        //Please Refer https://github.com/leonbloy/pngj/blob/master/src/main/java/ar/com/hjg/pngj/FilterType.java

        int row = 0;
        int bytesRead = fileInputStream.read(buffer);
        int j = 0;
        int rowSize = SIDE * 3;

        while (bytesRead > 0) {
            for (int i = 0; i < bytesRead; i++) {
                imgData[j++] = buffer[i] & 0xFF;

                if (j == rowSize) {
                    pngw.writeRowInt(imgData);
                    j = 0;
                    row++;
                    if (row == SIDE) break;
                }
            }

            if (row == SIDE) break;

            bytesRead = fileInputStream.read(buffer);
            if (bytesRead == -1) break;
        }

        pngw.end();
        fileInputStream.close();
    }
}