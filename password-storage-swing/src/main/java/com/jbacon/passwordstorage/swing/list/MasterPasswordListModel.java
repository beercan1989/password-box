package com.jbacon.passwordstorage.swing.list;

import java.util.Collection;

import javax.swing.DefaultListModel;

import com.jbacon.passwordstorage.password.MasterPassword;

public class MasterPasswordListModel extends DefaultListModel {

	private static final long serialVersionUID = -8353267016939700533L;

	public void add(final int index, final MasterPassword element) {
		super.add(index, element);
	}

	public void add(final MasterPassword masterPassword) {
		addElement(masterPassword);
	}

	public void addAll(final Collection<MasterPassword> masterPasswords) {
		for (MasterPassword masterPassword : masterPasswords) {
			add(masterPassword);
		}
	}

	public void addAll(final MasterPassword... masterPasswords) {
		for (MasterPassword masterPassword : masterPasswords) {
			add(masterPassword);
		}
	}

	public void addElement(final MasterPassword obj) {
		super.addElement(obj);
	}

	public boolean contains(final MasterPassword elem) {
		return super.contains(elem);
	}

	@Override
	public MasterPassword elementAt(final int index) {
		return (MasterPassword) super.elementAt(index);
	}

	@Override
	public MasterPassword firstElement() {
		return (MasterPassword) super.firstElement();
	}

	@Override
	public MasterPassword get(final int index) {
		return (MasterPassword) super.elementAt(index);
	}

	@Override
	public MasterPassword getElementAt(final int index) {
		return (MasterPassword) super.elementAt(index);
	}

	public int indexOf(final MasterPassword elem) {
		return super.indexOf(elem);
	}

	public int indexOf(final MasterPassword elem, final int index) {
		return super.indexOf(elem, index);
	}

	public void insertElementAt(final MasterPassword obj, final int index) {
		super.insertElementAt(obj, index);
	}

	@Override
	public MasterPassword lastElement() {
		return (MasterPassword) super.lastElement();
	}

	public int lastIndexOf(final MasterPassword elem) {
		return super.lastIndexOf(elem);
	}

	public int lastIndexOf(final MasterPassword elem, final int index) {
		return super.lastIndexOf(elem, index);
	}

	@Override
	public MasterPassword remove(final int index) {
		return (MasterPassword) super.remove(index);
	}

	public boolean removeElement(final MasterPassword obj) {
		return super.removeElement(obj);
	}

	public MasterPassword set(final int index, final MasterPassword element) {
		return (MasterPassword) super.set(index, element);
	}

	public void setElementAt(final MasterPassword obj, final int index) {
		super.setElementAt(obj, index);
	}

	@Override
	public MasterPassword[] toArray() {
		return (MasterPassword[]) super.toArray();
	}

}
