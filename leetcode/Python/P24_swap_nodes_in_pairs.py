# simul

# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def swapPairs(self, head: Optional[ListNode]) -> Optional[ListNode]:
        # 예외 처리: 빈 리스트 또는 하나만 있는 경우
        if not head or not head.next:
            return head

        dummy = ListNode(0) # 더미 시작 노드
        dummy.next = head
        prev = dummy # 이전 쌍의 마지막

        while head and head.next:
            first = head
            second = head.next

            # swap
            prev.next = second
            first.next = second.next
            second.next = first

            # 다음 쌍으로 이동
            prev = first
            head = first.next

        return dummy.next
