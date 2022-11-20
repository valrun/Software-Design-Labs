
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author vryndina
 */

public class LRUCache<K, V> implements Map<K, V> {
    private final HashMap<K, Node> hashMap = new HashMap();
    private Node head;
    private Node end;
    public final int hashSize;
    // Immutable: 0 <= size <= HASH_SIZE

    // Pre: hashSize >= 0
    LRUCache(int hashSize) {
        assert hashSize > 0;
        this.hashSize = hashSize;
    }

    // Post: result = size, Immutable
    @Override
    public int size() {
        return hashMap.size();
    }

    // Post: result = (size == 0), Immutable
    @Override
    public boolean isEmpty() {
        return hashMap.isEmpty();
    }

    // Pre: key != null
    // Post: result = (∃ value: (key, value) in LRUCache), Immutable
    @Override
    public boolean containsKey(Object key) {
        assert key != null;
        return hashMap.containsKey(key);
    }

    // Pre: value != null
    // Post: result = (∃ key: (key, value) in LRUCache), Immutable
    @Override
    public boolean containsValue(Object value) {
        assert value != null;
        return hashMap.values().stream().anyMatch(it -> it.getValue() == value);
    }

    // Pre: size > 0 && key != null && ∃ value: (key, value) in LRUCache
    // Post: result = value && (key, value) in LRUCache && Immutable
    @Override
    public V get(Object key) {
        assert !isEmpty() && key != null && containsKey(key);
        return hashMap.get(key).getValue();
    }

    // Pre: key != null && value != null
    // Post: result = value'
    // && (size = size' + 1 || size == HASH_SIZE)
    // && (key, value) in LRUCache
    // && ∀i = 1 .. min(size', HASH_SIZE - 1): (key_i', value_i') in LRUCache
    @Nullable
    @Override
    public V put(K key, V value) {
        assert key != null && value != null;

        if (containsKey(key)) {
            Node node = hashMap.get(key);
            V oldValue = node.getValue();
            node.setValue(value);
            return oldValue;
        }

        if (hashMap.size() >= hashSize) {
            hashMap.remove(end.getKey());
            end = end.getPrevious();
            end.setNext(null);
        }
        final Node node = new Node(key, value, head);
        if (size() == 0) {
            head = node;
            end = node;
        } else {
            head.setPrevious(node);
            head = node;
        }

        final Node result = hashMap.put(key, node);
        return result == null ? null : result.getValue();
    }

    // Pre: size > 0 && key != null && ∃ value: (key, value) in LRUCache
    // Post: result = value' && (key', value') in LRUCache' && size = size' - 1
    @Override
    public V remove(Object key) {
        assert !isEmpty() && key != null && containsKey(key);
        final Node node = hashMap.get(key);
        if (node == head) {
            head = head.getNext();
            if (head != null) {
                head.setPrevious(null);
            }
        }
        if (node == end) {
            end = end.getPrevious();
            if (end != null) {
                end.setNext(null);
            }
        }
        return hashMap.remove(key).getValue();
    }

    // Pre: size_m > 0 && ∀i = 1 .. size_m: (key_m_i != null && value_m_i != null)
    // Post: (size = size' + size_m || size == HASH_SIZE)
    // && ∀i = min(1, HASH_SIZE - size_m + 1) .. size_m: (key_m_i, value_m_i) in LRUCache
    // && ∀i = 1 .. min(size' - size_m, HASH_SIZE - 1): (key_i', value_i') in LRUCache
    @Override
    public void putAll(@NotNull Map<? extends K, ? extends V> m) {
        m.forEach(this::put);
    }

    // Post: size = 0
    @Override
    public void clear() {
        head = null;
        end = null;
        hashMap.clear();
    }

    // Post: set of key: (∃ value: (key, value) in LRUCache)
    // && Immutable
    @NotNull
    @Override
    public Set<K> keySet() {
        return hashMap.keySet();
    }

    // Post: set of value: (∃ set: (key, value) in LRUCache)
    // && Immutable
    @NotNull
    @Override
    public Collection<V> values() {
        return hashMap.values()
                .stream()
                .map(Node::getValue)
                .collect(Collectors.toList());
    }

    // Post: set of (key, value): (key, value) in LRUCache
    // && Immutable
    @NotNull
    @Override
    public Set<Entry<K, V>> entrySet() {
        return hashMap.entrySet()
                .stream()
                .collect(Collectors.toMap(Entry::getKey, e -> e.getValue().getValue()))
                .entrySet();
    }

    private class Node {
        private final K key;
        private V value;
        private Node previous;
        private Node next;

        //Pre: key != null && value != null
        public Node(K key, V value, Node next, Node previous) {
            assert key != null && value != null;
            this.key = key;
            this.value = value;
            this.previous = previous;
            this.next = next;
        }

        //Pre: key != null && value != null
        public Node(K key, V value, Node next) {
            this(key, value, next, null);
        }

        //Pre: key != null && value != null
        public Node(K key, V value) {
            this(key, value, null, null);
        }

        // Post: result = key && Immutable
        public K getKey() {
            return key;
        }

        // Post: result = value && Immutable
        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        // Post: result = previous && Immutable
        public Node getPrevious() {
            return previous;
        }

        public void setPrevious(Node previous) {
            this.previous = previous;
        }

        // Post: result = next && Immutable
        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }
}


