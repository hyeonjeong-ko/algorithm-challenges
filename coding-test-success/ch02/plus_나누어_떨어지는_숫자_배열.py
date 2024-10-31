def solution(arr, divisor):
    arr = [x for x in arr if x % divisor==0]
    arr.sort()
    return arr if arr else [-1]
