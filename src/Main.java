import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static java.nio.file.StandardOpenOption.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Path tmpfile = Paths.get("tmp.txt");
        try (FileChannel channel = FileChannel.open(tmpfile, READ, WRITE)) {
            MappedByteBuffer mappedByteBuffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, 100);
            mappedByteBuffer.put("HelloWorld\n".getBytes());
        }

        //The tmpfile indeed start with "HelloWorld",we can find "HelloWorld" at first line with notepad or cat command too.
        //But if we open "tmp.txt" with idea,the content is not changed at all.
        List<String> list = Files.readAllLines(tmpfile);
        assertTrue(list.get(0).equals("HelloWorld"));
    }

    public static void assertTrue(boolean b) {
        if (!b) {
            throw new RuntimeException();
        }
    }
}
