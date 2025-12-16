/*
package com.spr.reactivedemo;


import java.util.*;

public class ImutableList{


    public static void main(String[] args) {


      */
/*  final int a =5;
        a++;
        System.out.printf("a"+a);*//*


        final List<String> alist = new ArrayList<>();
        System.out.printf(String.valueOf(alist.size()));
        alist.add("As");
        System.out.printf(String.valueOf(alist));

    }


}

class unmbList ex implements List<String > {

    String elements;
    int index =0;
    final Object [] listObject;

    public unmbList (int size){
        listObject = new Object[size];

    };




    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<String> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(String s) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends String> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends String> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        unmbList strings = (unmbList) o;
        return Objects.equals(elements, strings.elements);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(elements);
    }

    @Override
    public String get(int index) {
        return "";
    }

    @Override
    public String set(int index, String element) {
        return "";
    }

    @Override
    public void add(int index, String element) {

    }

    @Override
    public String remove(int index) {
        return "";
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<String> listIterator() {
        return null;
    }

    @Override
    public ListIterator<String> listIterator(int index) {
        return null;
    }

    @Override
    public List<String> subList(int fromIndex, int toIndex) {
        return List.of();
    }

}*/
