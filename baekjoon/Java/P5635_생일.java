// 실버문제.객체 정렬

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class P5635 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		ArrayList<Student> students=  new ArrayList<>();

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String name = st.nextToken();
			int day = Integer.parseInt(st.nextToken());
			int month = Integer.parseInt(st.nextToken());
			int year = Integer.parseInt(st.nextToken());
			students.add(new Student(name,day,month,year));
		}
		Collections.sort(students);
		System.out.println(students.get(students.size()-1).name);
		System.out.println(students.get(0).name);
	}
}

class Student implements Comparable<Student>{
	String name;
	int day;
	int month;
	int year;

	public Student(String name,int day,int month,int year){
		this.name = name;
		this.day = day;
		this.month = month;
		this.year = year;
	}

	@Override
	public int compareTo(Student o) {
		if(this.year == o.year){
			if(this.month == o.month){
				return this.day - o.day;
			}
			return  this.month - o.month;
		}
		return this.year - o.year;
	}
}
