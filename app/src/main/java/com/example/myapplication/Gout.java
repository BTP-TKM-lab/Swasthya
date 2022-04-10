package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Gout extends AppCompatActivity {
    TextView question;
    Button yes,no;
    Binarytree binarytree1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gout);
        question=findViewById(R.id.question);
        yes=findViewById(R.id.yes);
        no=findViewById(R.id.no);

        Binarytree binarytree=new Binarytree("Joint pain ?");
        binarytree.left =new Binarytree("Possibility of Gout or Arthritis ?") ;
        binarytree.right =new Binarytree("Absence of primary symptom for arthritic Diseases ?") ;
        binarytree.left.left= new Binarytree("Visible Lumps");
        binarytree.left.right=new Binarytree("Pain start at MTP joint?");
        binarytree.left.left.left= new Binarytree("Tophaceous Gout");
        binarytree.left.left.right= new Binarytree("false");

        binarytree.left.right.left=new Binarytree("true");
        binarytree.left.right.right=new Binarytree("Visible Lumps");
        binarytree.left.right.right.left=new Binarytree("Tophaceous Gout");
        binarytree.left.right.right.right=new Binarytree("false");


        binarytree1=binarytree;

        reload();
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                binarytree1=binarytree1.left;
                reload();

            }
        });
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                binarytree1=binarytree1.right;
                reload();

            }
        });


    }

    private void reload() {
        if(binarytree1.question=="true"){

        }
        else if(binarytree1.question=="false"){

        }
        else{
            question.setText(binarytree1.question);
        }
    }
}