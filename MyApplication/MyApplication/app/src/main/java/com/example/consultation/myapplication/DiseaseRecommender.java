package com.example.consultation.myapplication;

        import android.app.Activity;
        import android.os.Bundle;
        import android.view.View;
        import android.view.View.OnClickListener;
        import android.widget.Button;
        import android.widget.RadioButton;
        import android.widget.TextView;
        import android.widget.Toast;

        import java.io.BufferedReader;
        import java.io.IOException;
        import java.io.InputStream;
        import java.io.InputStreamReader;
        import java.util.List;
        import java.util.ArrayList;

        public class DiseaseRecommender extends Activity implements View.OnClickListener
        {
            TextView question;
            Button start;
            Button btn_next;
            RadioButton yes, no;

            List<String> bemari = new ArrayList<String>();
            List<String> disease = new ArrayList<String>();
            int i = 0;
            int j = 0;
            public String a = "";
            String[] symtoms;

            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_disease_recommender);

                yes = (RadioButton) findViewById(R.id.radioButton);
                no = (RadioButton) findViewById(R.id.radioButton2);

                question = (TextView) findViewById(R.id.editText);

                start = (Button) findViewById(R.id.btn_start);
                btn_next = (Button) findViewById(R.id.btn_next);

                start.setOnClickListener((OnClickListener) this);
                btn_next.setOnClickListener((OnClickListener) this);

            }

            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.btn_start:
                        addNewSymtomsInArray();
                        break;

                    case R.id.btn_next:
                        checkDisease();
                        break;

                    default:
                        break;
                }
            }

            public void addNewSymtomsInArray() {
                bemari.clear();
                disease.clear();
                symtoms = null;

                disease.add("pneumonia");
                disease.add("typhoid");
                disease.add("malaria");
                disease.add("migraine");
                disease.add("Gastroentritis");
                disease.add("Asthama");

                BufferedReader reader;
                String str = "";
                try {
                    final InputStream file = getAssets().open("pneumonia.txt");
                    reader = reader = new BufferedReader(new InputStreamReader(file));
                    while ((str = reader.readLine()) != null) {
                        bemari.add(str);
                    }
                    addNewSymtoms();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            public void addNewSymtoms() {
                a = bemari.get(j);
                symtoms = a.split(",");
                question.setText(symtoms[i]);
            }

            public void checkDisease() {
                if (yes.isChecked()) {
                    if (i == symtoms.length - 2) {
                        if (new String(symtoms[i + 1]).equals(disease.get(j))) {
                            question.setText("You have the symptoms of: " + symtoms[symtoms.length - 1]);
                        }
                    } else {
                        i++;
                        question.setText(symtoms[i]);
                    }
                } else if (no.isChecked()) {
                    if (j == bemari.size() - 1) {
                        question.setText("Sorry!! Disease not found.");
                    } else {
                        j++;
                        i = 0;
                        addNewSymtoms();
                    }
                } else {
                    Toast.makeText(this, "Select First", Toast.LENGTH_SHORT).show();
                }
            }

        }