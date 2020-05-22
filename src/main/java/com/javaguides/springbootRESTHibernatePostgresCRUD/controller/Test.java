package com.javaguides.springbootRESTHibernatePostgresCRUD.controller;

import java.util.ArrayList;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        List<Integer> list1=new ArrayList<>();
        list1.add(1);
        list1.add(2);
        list1.add(3);

        System.out.println("---LIST1--Before--"+list1);

        List<Integer> list2=new ArrayList<>();
        list2.add(1);
        list2.add(2);
        list2.add(4);
        list2.add(10);

        System.out.println("---LIST2----"+list2);



        list2.removeAll(list1);

        System.out.println("---LIST2--After--"+list2);



    }
}
