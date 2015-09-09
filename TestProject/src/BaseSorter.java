
public abstract class BaseSorter {

	protected IWorkerCompartor comperator;

	public BaseSorter(IWorkerCompartor comperator) {
		this.comperator = comperator;
	}

	public abstract void sort(Person[] persons);

	protected void swap(Person p1, Person p2) {

	}
}
