class Solution:
    def reverseString(self, s: List[str]) -> None:
        """
        Do not return anything, modify s in-place instead.
        """
        # 재귀 time, space O(n)
        def reverse(l,r):
            if l<r:
                s[l],s[r] = s[r],s[l]
                reverse(l+1,r-1)
        reverse(0,len(s)-1)

        """
        # 스택 이용 time, space O(n)
        st = []
        for c in s:
            st.append(c)
        i = 0
        while st:
            s[i] = st.pop()
            i += 1 
        """
        """
        # swap 이용
        l,r = 0, len(s)-1
        while l<r:
            s[l],s[r] = s[r],s[l]
            l, r = l + 1, r - 1
        """
