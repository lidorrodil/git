
public class Msin {

	public static void main(String[] args) {
		Person[] persons = new Person[2];
		persons[0] = new Person();
		persons[1] = new Person();
		
		Person[] persons2 = new Person[2];
		persons2[0] = new Person();
		persons2[1] = new Person();
		BubbleSortSorter sorter = new BubbleSortSorter(new AgeComperator());
		sorter.sort(persons);
		sorter.sort(persons2);
		
	}

}
