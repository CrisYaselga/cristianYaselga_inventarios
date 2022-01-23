package com.inventarios.util;

/**
 * Generic clas for to wrap two elements
 * 
 * @author CRIS
 *
 */
public class Duplex<E, T> {
	private E firstElement;
	private T secondElement;

	
	public Duplex() {
		super();
	}

	public Duplex(E firstElement, T secondElement) {
		super();
		this.firstElement = firstElement;
		this.secondElement = secondElement;
	}

	public E getFirstElement() {
		return firstElement;
	}

	public void setFirstElement(E firstElement) {
		this.firstElement = firstElement;
	}

	public T getSecondElement() {
		return secondElement;
	}

	public void setSecondElement(T secondElement) {
		this.secondElement = secondElement;
	}

}
