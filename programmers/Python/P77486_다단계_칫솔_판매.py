# Simul

def solution(enroll, referral, seller, amount):
    answer = []
    
    # enroll : referral
    parent = dict(zip(enroll,referral))    
    total = {name: 0 for name in enroll}
    
    for i in range(len(seller)):
        money = amount[i] * 100 # 판매 이익
        cur_name = seller[i]
        
        # 차례대로 상위 노드로 이동하며 이익 분배
        while money > 0 and cur_name != "-":
            # 판매자가 받을 금액 계산
            total[cur_name] += money - money//10
            money//=10
            cur_name=parent[cur_name]
    return [total[name] for name in enroll]
    
    return answer
