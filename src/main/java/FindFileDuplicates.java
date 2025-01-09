import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;
import java.util.stream.Stream;

public class FindFileDuplicates {
    private static final int SAMPLE_SIZE = 4000;
    public static void main(String[] args) throws IOException {
        String rootDirName = "src/codeforces.round995.codeforces.round995.codeforces.round995.codeforces.round995.main/resources/find_file_duplicates";
        File rootDir = new File(rootDirName);
        for (File file : Objects.requireNonNull(rootDir.listFiles())) {
            System.out.println(file);
        }
        try (Stream<Path> stream = Files.list(rootDir.toPath())) {
            stream.forEach(System.out::println);
        }

        try (Stream<Path> stream = Files.list(rootDir.toPath())) {
            stream.forEach(path -> {
                try {
                    System.out.println(Files.readAllLines(path));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }

        for (File file : Objects.requireNonNull(rootDir.listFiles())) {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            System.out.println(bufferedReader.readLine());
            bufferedReader.close();
        }

        try (Stream<Path> stream = Files.list(rootDir.toPath())) {
            stream.forEach(file -> {
                try (InputStream reader = new FileInputStream(file.toFile())) {
                    byte[] bytes = new byte[5];
                    reader.read(bytes);
                    MessageDigest digest = MessageDigest.getInstance("SHA-512");
                    DigestInputStream digestInputStream = new DigestInputStream(reader, digest);
                    digestInputStream.read(bytes, SAMPLE_SIZE, SAMPLE_SIZE);
                    digestInputStream.skip(10);
                } catch (IOException | NoSuchAlgorithmException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }
}
