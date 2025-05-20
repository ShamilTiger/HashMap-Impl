package ilyin.java.homework1;


import org.jetbrains.annotations.Nullable;


public class HashMapImpl<K, V> {


    private Entry<K,V> [] array;
    private int defaultCapacity = 20;
    static final float LOAD_FACTOR = 0.75f;
    private int size;


    public HashMapImpl() {
        array = new Entry[defaultCapacity];
        size = 0;
    }


    static class Entry<K, V>{
        K key;
        V value;
        Entry<K, V> next;

        public Entry(K key, V value, Entry<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    @Nullable
    public void put(K newKey, V data) {
        if (size >= array.length * LOAD_FACTOR) {
            resize();
        }


        int hash = hash(newKey, array.length);

        Entry<K, V> newEntry = new Entry<K, V>(newKey, data, null);


        if (array[hash] == null) {
            array[hash] = newEntry;
        } else {
            Entry<K, V> previous = null;
            Entry<K, V> current = array[hash];

            while (current != null) {
                if (current.key.equals(newKey)) {
                    if (previous == null) {
                        newEntry.next = current.next;
                        array[hash] = newEntry;
                        return;
                    } else {
                        newEntry.next = current.next;
                        previous.next = newEntry;
                        return;
                    }
                }
                previous = current;
                current = current.next;
            }
            previous.next = newEntry;
        }
        size++;
    }


    @Nullable
    public V get(K key) {
        int hash = hash(key, array.length);
        if (array[hash] == null) {
            return null;
        } else {
            Entry<K, V> temp = array[hash];
            while (temp != null) {
                if (temp.key.equals(key))
                    return temp.value;
                temp = temp.next;
            }
            return null;
        }
    }

    public boolean remove(K deleteKey) {

        int hash = hash(deleteKey, array.length);

        if (array[hash] == null) {
            return false;
        } else {
            Entry<K, V> previous = null;
            Entry<K, V> current = array[hash];

            while (current != null) {
                if (current.key.equals(deleteKey)) {
                    if (previous == null) {
                        array[hash] = array[hash].next;
                        return true;
                    } else {
                        previous.next = current.next;
                        size--;
                        return true;
                    }
                }
                previous = current;
                current = current.next;
            }
            return false;
        }
    }

    public void print() {

        for (int i = 0; i < defaultCapacity; i++) {
            if (array[i] != null) {
                Entry<K, V> entry = array[i];
                while (entry != null) {
                    System.out.print("{" + entry.key + "=" + entry.value + "}" + " ");
                    entry = entry.next;
                }
            }
        }

    }

    public boolean containsKey(K key) {
        return get(key) != null;
    }

    public boolean containsValue(V value) {
        for (Entry<K, V> entry : array) {
            while (entry != null) {
                if (valuesEqual(entry.value, value)) {
                    return true;
                }
                entry = entry.next;
            }
        }
        return false;
        }


    private boolean valuesEqual(V value1, V value2) {
        if (value1 == null) {
            return value2 == null;
        }
        return value1.equals(value2);
    }


    public void clear() {
        array = new Entry[defaultCapacity];
        size = 0;
    }

    private int hash(K key, int length) {
        if (key == null) {
            return 0;}
        {
            return Math.abs(key.hashCode()) % length;
        }
    }

    private void resize() {

        Entry<K, V>[] newArray = new Entry[array.length * 2];

        for (Entry<K, V> entry : array) {
            while (entry != null) {
                Entry<K, V> next = entry.next;
                int newIndex = hash(entry.key, newArray.length);
                entry.next = newArray[newIndex];
                newArray[newIndex] = entry;
                entry = next;
            }
        }

        array = newArray;
    }

}


