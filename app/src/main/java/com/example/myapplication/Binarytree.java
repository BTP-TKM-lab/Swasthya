package com.example.myapplication;

public class Binarytree {
    String question;
    String subquestion;
    Binarytree left, right;

    public Binarytree(String item,String subquestion)
    {
        question = item;
        this.subquestion=subquestion;
        left = right = null;
    }
}
