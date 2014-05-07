package com.jbacon.passwordstorage.swing.list;

import java.util.Collection;

import javax.swing.DefaultListModel;

import com.jbacon.passwordstorage.password.GenericPassword;

@SuppressWarnings("unchecked")
public class CustomListModel<T extends GenericPassword> extends DefaultListModel {
    
    private static final long serialVersionUID = -8353267016939700533L;
    
    public void add(final int index, final T element) {
        super.add(index, element);
    }
    
    public void add(final T masterPassword) {
        addElement(masterPassword);
    }
    
    public void addAll(final Collection<T> masterPasswords) {
        for (final T masterPassword : masterPasswords) {
            add(masterPassword);
        }
    }
    
    public void addAll(final T... masterPasswords) {
        for (final T masterPassword : masterPasswords) {
            add(masterPassword);
        }
    }
    
    public void addElement(final T obj) {
        super.addElement(obj);
    }
    
    public boolean contains(final T elem) {
        return super.contains(elem);
    }
    
    @Override
    public T elementAt(final int index) {
        return (T) super.elementAt(index);
    }
    
    @Override
    public T firstElement() {
        return (T) super.firstElement();
    }
    
    @Override
    public T get(final int index) {
        return (T) super.elementAt(index);
    }
    
    @Override
    public T getElementAt(final int index) {
        return (T) super.elementAt(index);
    }
    
    public int indexOf(final T elem) {
        return super.indexOf(elem);
    }
    
    public int indexOf(final T elem, final int index) {
        return super.indexOf(elem, index);
    }
    
    public void insertElementAt(final T obj, final int index) {
        super.insertElementAt(obj, index);
    }
    
    @Override
    public T lastElement() {
        return (T) super.lastElement();
    }
    
    public int lastIndexOf(final T elem) {
        return super.lastIndexOf(elem);
    }
    
    public int lastIndexOf(final T elem, final int index) {
        return super.lastIndexOf(elem, index);
    }
    
    @Override
    public T remove(final int index) {
        return (T) super.remove(index);
    }
    
    public boolean removeElement(final T obj) {
        return super.removeElement(obj);
    }
    
    public T set(final int index, final T element) {
        return (T) super.set(index, element);
    }
    
    public void setElementAt(final T obj, final int index) {
        super.setElementAt(obj, index);
    }
    
    @Override
    public T[] toArray() {
        return (T[]) super.toArray();
    }
    
    @Override
    public void add(final int index, final Object element) {
        super.add(index, element);
    }
    
    @Override
    @Deprecated
    public void addElement(final Object obj) {
        super.addElement(obj);
    }
    
    @Override
    @Deprecated
    public boolean contains(final Object elem) {
        return super.contains(elem);
    }
}