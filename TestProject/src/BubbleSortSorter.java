
public class BubbleSortSorter extends BaseSorter {

	public BubbleSortSorter(IWorkerCompartor comperator) {
		super(comperator);
	}

	@Override
	public void sort(Person[] persons) {
		for (int i = 0; i < persons.length; i++) {
			for (int j = 0; j < persons.length; j++) {
				if (comperator.compare(persons[i], persons[i + 1]) > 0) {
					swap(persons[i], persons[i + 1]);
				}
			}
		}
	}

}
