package task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class Group {
	
	private String groupName;
	private ArrayList<Student> students;
	
	public Group(String groupName) {
		super();
		this.groupName = groupName;
		students = new ArrayList<>();
	}

	public Group() {
		super();
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
			
	public ArrayList<Student> getStudents() {
		return students;
	}

	public void setStudents(ArrayList<Student> students) {
		this.students = students;
	}

	public void addStudent(Student student) throws GroupOverflowException {
		
		if (students.size() < 10) {
			students.add(student);
		} else {
			throw new GroupOverflowException();	
		}
			
	}
	
	public Student searchStudentByLastName(String lastName) throws StudentNotFoundException {
				
		for (Student student : students) {
			if (student.getLastName().equals(lastName)) {
				return student;
			}
		}
		
		throw new StudentNotFoundException();
		
	}
	
	public boolean removeStudentByID(int id) {
		
		for (Student student : students) {
			
			if (student.getId() == id) {
				students.remove(student);
				return true;
			}
			
		}
				
		return false;
		
	}
	
	public void sortStudentsByLastName() {
		Collections.sort(students, Comparator.nullsFirst(new LastNameComparator()));
	}
	
	public boolean chekEqualStudentsInGroup() {
		
		boolean duplicatesOfStudentsInAGroup = false;
		
		for (Student student : students) {
			
			Student studentForCheking = student;
			int numberOfMatches = 0;
			
			for (Student studentInList : students) {
				if (studentForCheking.equals(studentInList)) {
					numberOfMatches += 1;
				}
			}
			
			if (numberOfMatches > 1) {
				duplicatesOfStudentsInAGroup = true;
			}
			
		}
		
		return duplicatesOfStudentsInAGroup;
		
	}

	@Override
	public String toString() {
		
		String result = getGroupName() + System.lineSeparator();
		
		for (Student student : students) {
			result += student + System.lineSeparator();	
		}

		return result; 
	
	}

	@Override
	public int hashCode() {
		return Objects.hash(groupName, students);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Group other = (Group) obj;
		return Objects.equals(groupName, other.groupName) && Objects.equals(students, other.students);
	}
	
}
