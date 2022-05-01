package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Mcq_questions extends AppCompatActivity {
    TextView question;
    TextView subquestion;
    Button yes,no;
    Binarytree binarytree1;
    TextView res1,res2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mcq);
        question=findViewById(R.id.question);
        subquestion=findViewById(R.id.subquestion);
        yes=findViewById(R.id.yes);
        no=findViewById(R.id.no);

        Binarytree binarytree=new Binarytree("Joint pain ?","Do you have joint pain ?");
        binarytree.left =new Binarytree("is your pain symmetric ?","Symmetric pain occurs on both sides of the body symmerically.") ;
        binarytree.right =new Binarytree("false1","") ;
        binarytree.left.left= new Binarytree("true1","");
        binarytree.left.right=new Binarytree("Joint Inflammation ?",
                "Do you feel inflammation in your joints? It is characterized by swelling, heat and tenderness. ");
        binarytree.left.right.left= new Binarytree("true2","");
        binarytree.left.right.right=new Binarytree("Do you experience morning stiffness ?",
                "Stiffness is a phenomenon of inability of move joints because they have become stiff or hard");

        binarytree.left.right.right.left=new Binarytree("Positive. DOes stiffness lasts for more than 30 min",
                "");

        binarytree.left.right.right.right=new Binarytree("false2","");
        binarytree.left.right.right.left.left=new Binarytree("Positive w/RA","");
        binarytree.left.right.right.left.right=new Binarytree("Positive w/OA","");
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

            setContentView(R.layout.activity_diagnosis);
            res1=findViewById(R.id.res1);
            res2=findViewById(R.id.res2);
            res1.setText("Since you are suffering from symmetric joint pain, which is a key defining symptom of RA.");
            res2.setText("You should consult your doctor and take a RA test to confirm the same.");
            Button treatment_btn=findViewById(R.id.treatment_management);
            treatment_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(getApplicationContext(),arthritis.class));
                }
            });
        }
        else if(binarytree1.question.equals("true2")){

            setContentView(R.layout.activity_diagnosis);
            res1=findViewById(R.id.res1);
            res2=findViewById(R.id.res2);
            res1.setText("Since you are suffering from inflammation, there is a high chance that you are suffering from Arthritis, particulary, Rheumatoid Arthritis.");
            res2.setText("You should take a test for the same at the earliest.");
            Button treatment_btn=findViewById(R.id.treatment_management);
            treatment_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(getApplicationContext(),arthritis.class));
                }
            });
        }
        else if(binarytree1.question.equals("false1")){

            setContentView(R.layout.activity_diagnosis);
            res1=findViewById(R.id.res1);
            res2=findViewById(R.id.res2);
            res1.setText("Good News! Joint pain is the preliminary symptom of Arthritis. Since, you are not experiencing joint pain, it is most certain that you are not having Arthritis.");
            res2.setText("");
            Button treatment_btn=findViewById(R.id.treatment_management);
            treatment_btn.setVisibility(View.GONE);
        }
        else if(binarytree1.question.equals("false2")){

            setContentView(R.layout.activity_diagnosis);
            res1=findViewById(R.id.res1);
            res2=findViewById(R.id.res2);
            res1.setText("Good News! Since, you are not experiencing symmetric pain, joint inflammation or morning stiffness which are the key features of arthritis, you  are most likely not suffering from Arthritis.");
            res2.setText("You can wait for some time before you take the test for Arthritis if the problem persists in the future.");
            Button treatment_btn=findViewById(R.id.treatment_management);
            treatment_btn.setVisibility(View.GONE);
        }
        else if(binarytree1.question.equals("Positive w/RA")){

        }
        else if(binarytree1.question.equals("Positive w/OA")){

        }
        else{

        }
        subquestion.setText(binarytree1.subquestion);
        question.setText(binarytree1.question);
    }
}