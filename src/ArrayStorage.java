import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int size = 0;

    void clear() {
        IntStream.range(0, size).forEach(i -> storage[i] = null);
        size = 0;
    }

    void save(Resume r) {
        storage[size++] = r;
    }

    Resume get(String uuid) {
        return Arrays.stream(storage)
                .limit(size)
                .filter(e -> e.uuid.equals(uuid))
                .findFirst()
                .orElse(null);
    }

    void delete(String uuid) {
        int index = IntStream.range(0, size)
                .filter(i -> storage[i].uuid.equals(uuid))
                .findFirst().orElse(-1);
        System.arraycopy(storage, index + 1, storage, index, size - index - 1);
        storage[--size] = null;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.stream(storage)
                .limit(size)
                .toArray(Resume[]::new);
    }

    int size() {
        return size;
    }
}
