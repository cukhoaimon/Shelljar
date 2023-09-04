import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import ar.com.hjg.pngj.FilterType;
import ar.com.hjg.pngj.ImageInfo;
import ar.com.hjg.pngj.PngWriter;

public class PNGConverter {

    public static void main(String[] args) throws IOException {

        final int CHUNK_SIZE = 134217728;
        final int SIDE = 5; // Dimensions hard coded to square 5 X 5, please change as needed

        byte[] buf = new byte[CHUNK_SIZE];

        int br, i,j,row=0;
        int[] arr = new int[SIDE*3];// 3 Bytes per pixel

        FileInputStream fileInputStream=new FileInputStream("C:\\Don\\source.bin");
        OutputStream os = new FileOutputStream("C:\\Don\\dest.png");

        ImageInfo imi = new ImageInfo(SIDE, SIDE, 8, false);

        PngWriter pngw = new PngWriter(os, imi);
        pngw.setCompLevel(9);// maximum compression
        pngw.setFilterType(FilterType.FILTER_ADAPTIVE_FAST);
        //Please Refer https://github.com/leonbloy/pngj/blob/master/src/main/java/ar/com/hjg/pngj/FilterType.java

        br = fileInputStream.read(buf);
        j=0;
        System.out.println("Bytes Read: " + br);
        while(br>0)
        {
            for(i=0;i<br;i++)
            {
                arr[j++] = buf[i] & 0xFF;

                if(j==SIDE*3)
                {
                    pngw.writeRowInt(arr);

                    j=0;
                    row++;
                    if(row==SIDE) break;
                }

            }
            if(row==SIDE) break;
            br = fileInputStream.read(buf);
            System.out.println("Bytes Read: " + br);
        }

        pngw.end();

        System.out.println("Done.");
        fileInputStream.close();

    }

}