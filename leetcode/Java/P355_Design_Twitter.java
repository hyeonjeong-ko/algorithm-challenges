import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
/*
algo: heapq
level: medium

* 최신 10개 추출:
각 팔로이의 가장 마지막(최신) 트윗만 후보 큐에 넣고,
최소힙에서 하나씩 꺼낼 때마다 그 유저의 다음 최신 트윗을 다시 삽입 → 마치 K-way merge sort 처럼!

타임스탬프 관리:
postTweet 시 time-- 로 저장 → 작을수록 최신 → 최소힙 사용 가능

핵심 Java 문법:
map.computeIfAbsent(userId, k->new ArrayList<>())
new PriorityQueue<>(Comparator.comparingInt(x->x[0]))
* */

public class P355_Design_Twitter {

    class Twitter {
        /**
         * 전역 타임스탬프. post할 때마다 1씩 감소(가장 최근일수록 더 작은 값).
         */
        private int time;
        /**
         * userId → List of {time, tweetId}
         */
        private Map<Integer, List<int[]>> tweetMap;
        /**
         * userId → Set of followeeIds
         */
        private Map<Integer, Set<Integer>> followMap;

        public Twitter() {
            this.time = 0;
            this.tweetMap = new HashMap<>();
            this.followMap = new HashMap<>();
        }


        public void postTweet(int userId, int tweetId) {
            tweetMap.computeIfAbsent(userId, k -> new ArrayList<>())
                    .add(new int[]{time--, tweetId});
        }

        /**
         * Retrieve the 10 most recent tweet ids in the user's news feed.
         */
        public List<Integer> getNewsFeed(int userId) {
            // 1) 결과와 (time, tweetId, userId, nextIndex) 를 담을 최소 힙 준비
            List<Integer> res = new ArrayList<>();
            PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));

            // 2) 자신도 팔로우한 것으로 처리
            followMap.computeIfAbsent(userId, k -> new HashSet<>()).add(userId);

            // 3) 각 followee의 가장 최신 트윗 한 개씩 힙에 넣기
            for (int f : followMap.get(userId)) {
                List<int[]> tweets = tweetMap.get(f);
                if (tweets == null || tweets.isEmpty()) {
                    continue;
                }
                int lastIndex = tweets.size() - 1;
                int t = tweets.get(lastIndex)[0];
                int id = tweets.get(lastIndex)[1];
                // 배열 원소: [time, tweetId, followeeId, nextIndex]
                pq.offer(new int[]{t, id, f, lastIndex - 1});
            }

            // 4) 힙에서 가장 "time이 작은"(가장 최근) 것부터 최대 10개 뽑기
            while (!pq.isEmpty() && res.size() < 10) {
                int[] top = pq.poll();
                res.add(top[1]);  // tweetId
                int f = top[2];
                int next = top[3];
                if (next >= 0) {
                    int[] tw = tweetMap.get(f).get(next);
                    pq.offer(new int[]{tw[0], tw[1], f, next - 1});
                }
            }
            return res;
        }

        /**
         * Follower follows a followee. If the operation is invalid, it should be a no-op.
         */
        public void follow(int followerId, int followeeId) {
            followMap.computeIfAbsent(followerId, k -> new HashSet<>()).add(followeeId);
        }

        /**
         * Follower unfollows a followee. If the operation is invalid, it should be a no-op.
         */
        public void unfollow(int followerId, int followeeId) {
            Set<Integer> set = followMap.get(followerId);
            if (set != null && followeeId != followerId) {
                set.remove(followeeId);
            }
        }
    }

    /**
     * Your Twitter object will be instantiated and called as such:
     * Twitter obj = new Twitter();
     * obj.postTweet(userId,tweetId);
     * List<Integer> param_2 = obj.getNewsFeed(userId);
     * obj.follow(followerId,followeeId);
     * obj.unfollow(followerId,followeeId);
     */
}
