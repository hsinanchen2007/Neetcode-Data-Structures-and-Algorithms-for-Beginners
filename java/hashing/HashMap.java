/* 
public class Pair {
    int key;
    int val;

    public Pair(int key, int val) {
        this.key = key;
        this.val = val;
    }
}
*/


public class HashMap {
    int size;
    int capacity;  
    Pair[] map;

    public HashMap() {
        this.size = 0;
        this.capacity = 2;
        this.map = new Pair[2];

    }

    public int hash(String key) {
        int index = 0;
        char c;
        for (int i = 0; i < key.length(); i++) {
            index+= (int) key.charAt(i);
        }
        return index % this.capacity;
    }

    public String get(String key) {
        int index = this.hash(key);
        while (this.map[index] != null) {
            if (this.map[index].key == key) {
                return this.map[index].val;
            }  
            index += 1;
            index = index % this.capacity;
        }    
        return null;
    }

    public void put(String key, String val) {
        int index = this.hash(key);

        while (true) {
            if (this.map[index] == null) {
                this.map[index] = new Pair(key, val);
                this.size += 1;
                if (this.size >= this.capacity / 2) {
                    this.rehash();
                }
                return;       
            } else if (this.map[index].key == key) {
                this.map[index].val = val;
                return;
            }
            index += 1;
            index = index % this.capacity;
        }    
    }

    public void remove(String key) {
        if (this.get(key) == null) {
            return;
        }
        
        int index = this.hash(key);
        while (true) {
            if (this.map[index].key == key) {
                // Removing an element using open-addressing actually causes a bug,
                // because we may create a hole in the list, and our get() may 
                // stop searching early when it reaches this hole.
                this.map[index] = null;
                this.size -= 1;
                return;
            }    
            index += 1;
            index = index % this.capacity;
        }
    }

    public void rehash() {
        this.capacity = 2 * this.capacity;
        Pair[] newMap = new Pair[this.capacity];

        Pair[] oldMap = this.map;
        this.map = newMap;
        this.size = 0;
        for (Pair p: oldMap) {
            if (p != null) {
                this.put(p.key, p.val);
            }
        }
    }

    public void print() {
        for (Pair p : this.map) {
            if (p != null) {
                System.out.println(p.key + " " + p.val);
            }
        }
    }

    public static void main(String[] args) {
        HashMap hashmap = new HashMap();
        hashmap.put("Alice", "NYC");
        hashmap.put("Brad", "Chicago");
        hashmap.put("Collin", "Seattle");

        System.out.println(hashmap.get("Alice"));
        System.out.println(hashmap.get("Brad"));
        System.out.println(hashmap.get("Collin"));
        System.out.println(hashmap.get("Daniel"));

        hashmap.put("Collin", "Vancouver");
        System.out.println(hashmap.get("Collin"));
        hashmap.print();

        hashmap.remove("Collin");
        System.out.println("after remove collin");
        hashmap.print();

        hashmap.remove("Alice");
        System.out.println("after remove alice");
        hashmap.print();

        hashmap.remove("Alice");
        System.out.println("after remove alice");
        hashmap.print();

        hashmap.remove("Brad");
        System.out.println("after remove brad");
        hashmap.print();
    }
}
