import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class FileApp {

    public static void main(String[] args) throws IOException {
        Path path1 = Paths.get("C:\\Users\\file.txt");
        Path path2 = Path.of("Users\\file.txt");
        Path path3 = Path.of("./tmp/file");


        //==================================================
        Files.createDirectories(Path.of("dir"));

        Files.copy(path1, path2, StandardCopyOption.REPLACE_EXISTING);

        //==================================================
        Files.walkFileTree(Path.of("dir"), new FileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                if (dir.endsWith("d")) {
                    return FileVisitResult.CONTINUE;
                } else {
                    return FileVisitResult.TERMINATE;
                }
            }
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                Files.delete(file);
                return FileVisitResult.TERMINATE;
            }
            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                return null;
            }
            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                return null;
            }
        });

        //==================================================
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024 * 102 * 10);
        FileChannel fileChannel = FileChannel.open(Path.of("file.txt"));
        fileChannel.read(byteBuffer);

        byte[] bytes = Files.readAllBytes(Path.of("file.txt"));



    }


}