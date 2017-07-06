package generics;

import java.util.*;

public class Generics {
	public static void main(String[] args) {
		ArrayList<Student> list = new ArrayList<>();
		list.add(new Student("자바왕", 1, 1, 100, 100, 100));
		list.add(new Student("자바짱", 1, 2, 90, 80, 70));
		list.add(new Student("홍길동", 2, 1, 70, 70, 70));
		list.add(new Student("전우치", 2, 2, 90, 90, 90));
		
		Collections.sort(list);
		
		Iterator<Student> it = list.iterator();
		
		while(it.hasNext()) {
			Student s = it.next();
			System.out.println(s);
		}
	}
	
}

class Student extends Persion implements Comparable<Persion>{
	String name = "";
	int ban = 0;
	int no = 0;
	int koreanScore = 0;
	int mathScore = 0;
	int englishScore = 0;
	int total = 0;
	
	Student(String name, int ban, int no, int koreanScore, int matchScore, int englishScore) {
		super(ban + "-" + no, name);
		this.name = name;
		this.ban = ban;
		this.no = no;
		this.koreanScore = koreanScore;
		this.mathScore = matchScore;
		this.englishScore = englishScore;
		
		total = koreanScore + matchScore + englishScore;
	}

	@Override
	public String toString() {
		return "Student [name=" + name + ", ban=" + ban + ", no=" + no + ", koreanScore=" + koreanScore + ", mathScore="
				+ mathScore + ", englishScore=" + englishScore + ", total=" + total + "]";
	}

	public int compareTo(Persion o) {
		return id.compareTo(o.id);
	}
}

class Persion {
	String id;
	String name;
	
	Persion(String id, String name) {
		this.id = id;
		this.name = name;
	}
}
