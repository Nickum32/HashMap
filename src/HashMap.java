public class HashMap<K,V> implements Map<K,V> {
    Entry[] map;
    int buckets;
    public HashMap(int buckets) {
        this.buckets = buckets;
        this.map = new Entry[buckets];
    }
    public HashMap() {
        this.map = new Entry[64];
    }
    public void clear() {
        map = new Entry[map.length];
    }
    public boolean containsKey(K key) {
        for (int i = 0; i < map.length; i++) {
            if (map[i].getKey().equals(key)) {
                return true;
            }
        }
        return false;
    }
    public boolean containsValue(V value) {
        for (int i=0; i<map.length; i++) {
            if (map[i].getValue().equals(value)) {
                return true;
            }
        }
        return false;
    }
    public V get(K key) {
        int hashKey = this.hash(key);
        return (V) map[hashKey].getValue();
    }
    public boolean isEmpty() {
        for (int i=0; i<map.length; i++) {
            if (map[i] != null) { return false; }
        }
        return true;
    }
    public V put(K key, V value) {
        int hashKey = this.hash(key);
        Entry ptr = map[hashKey];
        if (ptr == null) {
            map[hashKey] = new Entry(key, value, null);
            ptr = map[hashKey];
        }
        else {
            while (ptr.getNext() != null) {
                ptr = ptr.getNext();
            }
            ptr.setNext(new Entry(key, value, null));
            ptr = ptr.getNext();
        }
        return (V) ptr.getValue();
    }
    public V remove(K key) {
        int hashKey = this.hash(key);
        Entry trl = map[hashKey];
        if (trl == null) {
            return null;
        }
        else {
            Entry ptr = trl.getNext();
            if (trl.getKey().equals(key)) {
                map[hashKey] = ptr;
                return (V) trl.getValue();
            }
            else {
                while (ptr != null) {
                    if (ptr.getKey().equals(key)) {
                        trl.setNext(ptr.getNext());
                        return (V) ptr.getValue();
                    }
                    trl = ptr;
                    ptr = ptr.getNext();
                }
                return null;
            }
        }
    }
    public V replace(K key, V value) {
        int hashKey = this.hash(key);
        if (map[hashKey] == null) {
            return null;
        }
        else {
            V ret = (V) map[hashKey];
            map[hashKey].setValue(value);
            return ret;
        }
    }
    public int size() {
        int count = 0;
        for (int i=0; i<map.length; i++) {
            if (map[i] != null) {
                count++;
            }
        }
        return count;
    }
    public void display() {
        String ret = "";
        ret += " {";
        for (int i=0; i<map.length; i++) {
            Entry ptr = map[i];
            while (ptr != null) {
                String key = (String) ptr.getKey();
                String value = (String) ptr.getValue();
                ret += key + ": " + value;
                ptr = ptr.getNext();
                if (ptr != null) {
                    ret += ", ";
                }
            }
        }
        ret += "}";
        System.out.println(ret);
    }
    private int hash(K key) {
        String string = (String) key;
        char[] characters = string.toCharArray();
        int hashValue = 0;
        double val = 0;
        for (int i=0; i<characters.length; i++) {
            val += characters[i]*(i+1);
        }
        val = Math.pow(val, Math.PI);
        val %= 1;
        val = (val*10e+08);
        hashValue = (int) val % map.length;
        return hashValue;
    }
    private void efficiencyTest() {
        for (int i=0; i<map.length; i++) {
            String bucket = "Bucket "+i+": ";
            Entry ptr = map[i];
            while (ptr != null) {
                bucket += "X  ";
                ptr = ptr.getNext();
            }
            System.out.println(bucket);
        }
    }

    public static void main (String[] args) {
        HashMap test = new HashMap(101);
//        int testHash = test.hash("Gorilla");
//        System.out.println(testHash);
//        test.put("Gorilla", 2);
//        test.put("Ape", 2);
//        test.put("Monkey", 2);
//        test.put("Chimp", 2);
//        test.put("Guacamole", 2);
        TextScan text = new TextScan("C:/Users/Nick/IdeaProjects/Lab5/src/proverbs.txt");
        String[] textArray = text.getWords();
        int count = 0;
        while (textArray[count] != null) {
            test.put(textArray[count], 1);
            count++;
        }
        test.efficiencyTest();
    }
}
