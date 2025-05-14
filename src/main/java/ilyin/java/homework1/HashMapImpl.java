package ilyin.java.homework1;



public class HashMapImpl<K, V> {


    public HashMapImpl() {
        array = new Entry[defaultCapacity];
    }


    private Entry<K,V> [] array;
    private int defaultCapacity = 20;

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


    public void put(K newKey, V data) {
        if (newKey == null)
            return;


        int hash = hash(newKey);

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
    }

    public V get(K key) {
        int hash = hash(key);
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

        int hash = hash(deleteKey);

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
                        return true;
                    }
                }
                previous = current;
                current = current.next;
            }
            return false;
        }

    }

    public void outPut() {

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

    private int hash(K key) {
        return Math.abs(key.hashCode()) % defaultCapacity;
    }



}


