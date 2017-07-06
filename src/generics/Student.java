package generics;

public class Student implements Comparable<Student>{
	String name = "";
	int ban = 0;
	int no = 0;
	int koreanScore = 0;
	int mathScore = 0;
	int englishScore = 0;
	int total = 0;
	
	Student(String name, int ban, int no, int koreanScore, int matchScore, int englishScore) {
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

	public int compareTo(Student o) {
		return o.total - this.total;
	}
}
