package com.fang.backend.集合.map.hashMap.核心功能注释;

/**
 * @author shaobin
 * @date 2023/3/8 11:45
 */
public class MyHashMap<K, V> {

//    static class Node<K,V> implements Map.Entry<K,V> {
//        final int hash;
//        final K key;
//        V value;
//        HashMap.Node<K,V> next;
//
//        Node(int hash, K key, V value, HashMap.Node<K,V> next) {
//            this.hash = hash;
//            this.key = key;
//            this.value = value;
//            this.next = next;
//        }
//
//        public final K getKey()        { return key; }
//        public final V getValue()      { return value; }
//        public final String toString() { return key + "=" + value; }
//
//        public final int hashCode() {
//            return Objects.hashCode(key) ^ Objects.hashCode(value);
//        }
//
//        public final V setValue(V newValue) {
//            V oldValue = value;
//            value = newValue;
//            return oldValue;
//        }
//
//        public final boolean equals(Object o) {
//            if (o == this)
//                return true;
//            if (o instanceof Map.Entry) {
//                Map.Entry<?,?> e = (Map.Entry<?,?>)o;
//                if (Objects.equals(key, e.getKey()) &&
//                        Objects.equals(value, e.getValue()))
//                    return true;
//            }
//            return false;
//        }
//    }
//
//    static final int hash(Object key) {//默认的hash算法，将key的哈希值，进行高16位和低16位异或运算，增加低16位的随机性，降低哈希冲突的可能性
//        int h;
//        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);//key的hashCode 异或 key的hashCode右移16位的值
//    }
//    public V put(K key, V value) {//插入元素的put方法
//        return putVal(hash(key), key, value, false, true);
//    }
//    final V putVal(int hash, K key, V value, boolean onlyIfAbsent,
//                   boolean evict) {
//        Node<K,V>[] tab; Node<K,V> p; int n, i;
//        //第一次插入，table为null，需要初始化table
//        if ((tab = table) == null || (n = tab.length) == 0)
//            n = (tab = resize()).length;
//        //计算要放入的value应该在的索引位置，index = (n-1) & hash，这里采用与运算
//        if ((p = tab[i = (n - 1) & hash]) == null)//这个位置上没有元素(key)
//            tab[i] = newNode(hash, key, value, null);//创建key阶段，并放入table对应的index位置
//        else {//这个位置上已经存在元素
//            Node<K,V> e; K k;
//            if (p.hash == hash &&
//                    ((k = p.key) == key || (key != null && key.equals(k))))//存在的元素的key和要插入的元素的key一样，直接进行value覆盖
//                e = p;
//            else if (p instanceof TreeNode)//存在的元素的key和要插入的元素的key不一样，且该节点是对应的是红黑树，将新节点的插入到红黑树中
//                e = ((TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);
//            else {//该节点是链表，往链表中添加
//                for (int binCount = 0; ; ++binCount) {//扫描链表，
//                    if ((e = p.next) == null) {//添加到链表尾端
//                        p.next = newNode(hash, key, value, null);
//                        if (binCount >= TREEIFY_THRESHOLD - 1) // 插入之后的链表是否转换为红黑树
//                            treeifyBin(tab, hash);
//                        break;
//                    }
//                    if (e.hash == hash &&
//                            ((k = e.key) == key || (key != null && key.equals(k))))//扫描发现，链表中存在key一致的元素，结束循环
//                        break;
//                    p = e;
//                }
//            }
//            if (e != null) { // 链表中存在key一样的元素，替换value
//                V oldValue = e.value;
//                if (!onlyIfAbsent || oldValue == null)
//                    e.value = value;
//                afterNodeAccess(e);
//                return oldValue;
//            }
//        }
//        ++modCount;
//        if (++size > threshold)//新增之后，元素个数是否大于阈值，进行扩容
//            resize();
//        afterNodeInsertion(evict);
//        return null;
//    }
}
