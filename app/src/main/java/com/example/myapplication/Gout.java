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
    //TextView subquestion;
    TextView res1,res2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gout);
        question=findViewById(R.id.question);
        yes=findViewById(R.id.yes);
        no=findViewById(R.id.no);

        Binarytree binarytree=new Binarytree("Do you have joint pain ?","");
        binarytree.left =new Binarytree("Is your Pain Symmetric i.e. in both the joints ?","") ;
        binarytree.right =new Binarytree("false1","") ;
        binarytree.left.left= new Binarytree("Do you have any visible lumps on the paining joints?","");
        binarytree.left.right=new Binarytree("Did your pain start at MTP joint i.e. big toe?","");
        binarytree.left.left.left= new Binarytree("Tophaceous Gout","");
        binarytree.left.left.right= new Binarytree("false2","");

        binarytree.left.right.left=new Binarytree("true1","");
        binarytree.left.right.right=new Binarytree("Visible Lumps","");
        binarytree.left.right.right.left=new Binarytree("true1","");
        binarytree.left.right.right.right=new Binarytree("false1","");


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
        if(binarytree1.question.equals("true1")){
            //subquestion.setText("");
            question.setText("");
            setContentView(R.layout.activity_diagnosis);
            res1=findViewById(R.id.res1);
            res2=findViewById(R.id.res2);
            setContentView(R.layout.activity_diagnosis);
            res1=findViewById(R.id.res1);
            res2=findViewById(R.id.res2);
            res1.setText("You have symptoms for Gout, Please contact your doctor to the earliest.");
            res2.setText("");
        }
        else if(binarytree1.question.equals("Tophaceous Gout")){
            //subquestion.setText("");
            question.setText("");
            setContentView(R.layout.activity_diagnosis);
            res1=findViewById(R.id.res1);
            res2=findViewById(R.id.res2);
            setContentView(R.layout.activity_diagnosis);
            res1=findViewById(R.id.res1);
            res2=findViewById(R.id.res2);
            res1.setText("You have symptoms for Gout, Please contact your doctor to the earliest");
            res2.setText("");

        }
        else if(binarytree1.question.equals("true2")){
            //subquestion.setText("");
            question.setText("");
            setContentView(R.layout.activity_diagnosis);
            res1=findViewById(R.id.res1);
            res2=findViewById(R.id.res2);
            res1.setText("You have symptoms for Gout, Please contact your doctor to the earliest.");
            res2.setText("");
        }
        else if(binarytree1.question.equals("false1")){
            //subquestion.setText("");
            question.setText("");
            setContentView(R.layout.activity_diagnosis);
            res1=findViewById(R.id.res1);
            res2=findViewById(R.id.res2);
            res1.setText("Good News! Joint pain is the preliminary symptom of Arthritis. Since, you are not experiencing joint pain, it is most certain that you are not having Arthritis.");
            res2.setText("");
        }
        else if(binarytree1.question.equals("false2")){
            //subquestion.setText("");
            question.setText("");
            setContentView(R.layout.activity_diagnosis);
            res1=findViewById(R.id.res1);
            res2=findViewById(R.id.res2);
            res1.setText("After assessing your responses, you don't seem to have necessary symptoms for Gout.");
            res2.setText("");
        }
        else if(binarytree1.question.equals("Positive w/RA")){
            //subquestion.setText("");
            question.setText("");
        }
        else if(binarytree1.question.equals("Positive w/OA")){
            //subquestion.setText("");
            question.setText("");
        }
        else{
            //subquestion.setText(binarytree1.subquestion);
            question.setText(binarytree1.question);
        }
    }
}