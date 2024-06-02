package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    private int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume r) {
        int index = getIndex(r.getUuid());
        if (index < 0) {
            System.out.println("ERROR");
            return;
        }
        storage[index] = r;
    }
    public void save(Resume r) {
        if (getIndex(r.getUuid()) >= 0) {
            System.out.println("ERROR");
            return;
        }
        storage[size++] = r;
    }

    public Resume get(String uuid) {
        return Arrays.stream(storage)
                .limit(size)
                .filter(e -> uuid.equals(e.getUuid()))
                .findFirst()
                .orElse(null);
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if(index < 0) {
            System.out.println("ERROR");
            return;
        }
        System.arraycopy(storage, index + 1, storage, index, size - index - 1);
        storage[--size] = null;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    private int getIndex(String uuid) {
        return IntStream.range(0, size)
                .filter(i -> uuid.equals(storage[i].getUuid()))
                .findFirst().orElse(-1);
    }

    public int size() {
        return size;
    }
}
