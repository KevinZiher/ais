package Clani;
public interface IIterator <T> {
	
	public T next()throws IteratorFinishedException;
	public boolean hasFinished();
	
}
