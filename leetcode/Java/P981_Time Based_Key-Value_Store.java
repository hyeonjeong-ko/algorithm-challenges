/*
@Binary Search

문제요구:
- get(key, timestamp): 주어진 타임스탬프 이하의 가장 최근 값을 반환.
핵심 구현:
- 각 키에 대해 (timestamp, value) 리스트 저장, get에서 이진 탐색으로 최근 값 빠르게 찾기. O(N)->O(NlogN)
*/
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TimeMap {

	private Map<String, List<Pair<Integer, String>>> m;

	public TimeMap() {
		m = new HashMap<>();
	}

	public void set(String key, String value, int timestamp) {
		m.computeIfAbsent(key, k -> new ArrayList<>()).add(new Pair<>(timestamp, value));
	}

	public String get(String key, int timestamp) {
		List<Pair<Integer, String>> values = m.getOrDefault(key, new ArrayList<>());
		int l = 0, r = values.size() - 1;
		String result = "";

		while (l <= r){
			int m = (l + r) / 2;
			if (values.get(m).getKey() <= timestamp) {
				result = values.get(m).getValue();
				l = m + 1;
			} else {
				r = m - 1;
			}
		}
		return result;
	}

	private static class Pair<K,V> {
		private final K key;
		private final V value;

		private Pair(K key, V value) {
			this.key = key;
			this.value = value;
		}

		public K getKey() {
			return key;
		}
		public V getValue() {
			return value;
		}
	}
}

/**
 * Your TimeMap object will be instantiated and called as such:
 * TimeMap obj = new TimeMap();
 * obj.set(key,value,timestamp);
 * String param_2 = obj.get(key,timestamp);
 */
