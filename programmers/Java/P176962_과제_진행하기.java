import java.util.*;

class Solution {
    public String[] solution(String[][] plans) {
        List<Plan> li = new ArrayList<>();
        Deque<PausedTask> tmp = new ArrayDeque<>();
        List<String> ans = new ArrayList<>();
        for (String[] plan : plans) {
            String[] split = plan[1].split(":");
            int start = Integer.parseInt(split[0]) * 60 + Integer.parseInt(split[1]);
            int playTime = Integer.parseInt(plan[2]);
            li.add(new Plan(plan[0], start, playTime));
        }
        
        li.sort((o1,o2) -> o1.start - o2.start); // 오름차순 정렬
        
        for (int i = 0; i < li.size() - 1; i++) {
            String currentName = li.get(i).name;
            int start = li.get(i).start;
            int playTime = li.get(i).playTime;
            int nextStartTime = li.get(i + 1).start;
            int currentEndTime = start + playTime;
            
            if (currentEndTime > nextStartTime) {
                // 현재 과제가 다음 과제 시작 전에 끝나지 않으면 작업 보류
                int remainingTime = currentEndTime - nextStartTime;
                tmp.push(new PausedTask(currentName, remainingTime));
            } else { // 현재 과제가 다음 과제 시작 전 끝날 때
                ans.add(currentName);
                
                int gap = nextStartTime - currentEndTime;
                while (!tmp.isEmpty() && gap > 0) {
                    PausedTask pausedTask = tmp.pop(); // 가장 최근 멈춘 작업
                    if (pausedTask.remainingTime <= gap) { // 남은시간 내 작업완료
                        gap -= pausedTask.remainingTime;
                        ans.add(pausedTask.name);
                    } else { // 남은 시간이 gap보다 크면 일부만 진행
                        pausedTask.remainingTime -= gap;
                        tmp.push(pausedTask);
                        break;
                    }
                }
            }
            
        }
        
        // 마지막 과제처리
        ans.add(li.get(li.size()-1).name);
        
        // 남은 과제들 처리
        while (!tmp.isEmpty()) {
            PausedTask pausedTask = tmp.pop();
            ans.add(pausedTask.name);
        }
        
        return ans.toArray(new String[0]);
    }
    static class Plan {
        String name;
        int start;
        int playTime;
        
        Plan(String name, int start, int playTime) {
            this.name = name;
            this.start = start;
            this.playTime = playTime;
        }
    }
    static class PausedTask {
        String name;
        int remainingTime;
        
        public PausedTask(String name, int remainingTime) {
            this.name = name;
            this.remainingTime = remainingTime;
        }
    }
}
