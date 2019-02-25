void rehash() {
	HashEntry<K,V>[] oldTable = table;
	int oldCapacity = oldTable.length;
	if (oldCapacity >= MAXIMUM_CAPACITY)
		return;
 
	/*
	 * Reclassify nodes in each list to new Map.  Because we are
	 * using power-of-two expansion, the elements from each bin
	 * must either stay at same index, or move with a power of two
	 * offset. We eliminate unnecessary node creation by catching
	 * cases where old nodes can be reused because their next
	 * fields won't change. Statistically, at the default
	 * threshold, only about one-sixth of them need cloning when
	 * a table doubles. The nodes they replace will be garbage
	 * collectable as soon as they are no longer referenced by any
	 * reader thread that may be in the midst of traversing table
	 * right now.
	 */
	 /*
	 * 其实这个注释已经解释的很清楚了，主要就是因为扩展是按照2的幂次方
	 * 进行扩展的，所以扩展前在同一个桶中的元素，现在要么还是在原来的
	 * 序号的桶里，或者就是原来的序号再加上一个2的幂次方，就这两种选择。
	 * 所以原桶里的元素只有一部分需要移动，其余的都不要移动。该函数为了
	 * 提高效率，就是找到最后一个不在原桶序号的元素，那么连接到该元素后面
	 * 的子链表中的元素的序号都是与找到的这个不在原序号的元素的序号是一样的
	 * 那么就只需要把最后一个不在原序号的元素移到新桶里，那么后面跟的一串
	 * 子元素自然也就连接上了，而且序号还是相同的。在找到的最后一个不在
	 * 原桶序号的元素之前的元素就需要逐个的去遍历，加到和原桶序号相同的新桶上
	 * 或者加到偏移2的幂次方的序号的新桶上。这个都是新创建的元素，因为
	 * 只能在表头插入元素。这个原因可以参考
	 * 《探索 ConcurrentHashMap 高并发性的实现机制》中的讲解
	 */
 
	HashEntry<K,V>[] newTable = HashEntry.newArray(oldCapacity<<1);
	threshold = (int)(newTable.length * loadFactor);
	int sizeMask = newTable.length - 1;
	for (int i = 0; i < oldCapacity ; i++) {
		// We need to guarantee that any existing reads of old Map can
		//  proceed. So we cannot yet null out each bin.
		HashEntry<K,V> e = oldTable[i];
 
		if (e != null) {
			HashEntry<K,V> next = e.next;
			int idx = e.hash & sizeMask;
 
			//  Single node on list
			if (next == null)
				newTable[idx] = e;
 
			else {
				// Reuse trailing consecutive sequence at same slot
				HashEntry<K,V> lastRun = e;
				int lastIdx = idx;
				for (HashEntry<K,V> last = next;
					 last != null;
					 last = last.next) {
					int k = last.hash & sizeMask;
					// 这里就是遍历找到最后一个不在原桶序号处的元素
					if (k != lastIdx) {
						lastIdx = k;
						lastRun = last;
					}
				}
				// 把最后一个不在原桶序号处的元素赋值到新桶中
				// 由于链表本身的特性，那么该元素后面的元素也都能连接过来
				// 并且能保证后面的这些元素在新桶中的序号都是和该元素是相等的
				// 因为上面的遍历就是确保了该元素后面的元素的序号都是和这个元素
				// 的序号是相等的。不然遍历中还会重新赋值lastIdx
				newTable[lastIdx] = lastRun;
 
				// Clone all remaining nodes
				// 这个就是把上面找到的最后一个不在原桶序号处的元素之前的元素赋值到
				// 新桶上，注意都是把元素添加到新桶的表头处
				for (HashEntry<K,V> p = e; p != lastRun; p = p.next) {
					int k = p.hash & sizeMask;
					HashEntry<K,V> n = newTable[k];
					newTable[k] = new HashEntry<K,V>(p.key, p.hash,
													 n, p.value);
				}
			}
		}
	}
	table = newTable;
}