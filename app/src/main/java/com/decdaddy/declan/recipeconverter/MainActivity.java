
        package com.decdaddy.declan.recipeconverter;

        import android.app.Activity;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.text.Editable;
        import android.text.TextWatcher;
        import android.util.Log;
        import android.view.KeyEvent;
        import android.view.View;
        import android.view.inputmethod.EditorInfo;
        import android.widget.AdapterView;
        import android.widget.ArrayAdapter;
        import android.widget.EditText;
        import android.widget.Spinner;
        import android.widget.TextView;
        import android.widget.RadioButton;
        import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    EditText mEdit;
    TextView mText;
    String unit1 = "Cup(s)";
    String unit2 = "Cup(s)";
    String cUnit = "";
    String cUnit2 = "";
    String name1 = "Cup(s)";
    String name2 = "Cup(s)";
    double cRate = 1;
    double cRate2 = 1;
    double conversionRate = 1;
    double amount = 1;
    double convertedAmount = 1;
    int postion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Spinner spinner = (Spinner) findViewById(R.id.conversion_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.conversions_array,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        final Spinner spinner2 = (Spinner) findViewById(R.id.conversion_spinner2);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,R.array.conversions_array,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);

        mText = (TextView)findViewById(R.id.convertedNum);

        mEdit = (EditText)findViewById(R.id.editText);
        mEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(mEdit.getText().length() > 0) {
                    amount = Double.parseDouble(mEdit.getText().toString());
                    updateConversion();
                }

            }
        });
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cUnit = spinner.getSelectedItem().toString();
                updateConversion();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                updateConversion();
            }
        });
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cUnit2 = spinner2.getSelectedItem().toString();
                updateConversion();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                updateConversion();
            }
        });

    }

    public void selectOne(View view){
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId())
        {
            case R.id.radioButton:
                if(checked)
                    postion = 0;
                    name1 = "Cup(s)";
                break;
            case R.id.radioButton2:
                if(checked)
                    postion = 1;
                name1 = "Tablespoon(s)";
                break;
            case R.id.radioButton3:
                if(checked)
                    postion = 2;
                    name1 = "Teaspoon(s)";
                break;
        }
        setConversions(postion);
        updateConversion();
    }
    public void selectTwo(View view){
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId())
        {
            case R.id.radioButton4:
                if(checked)
                    name2 = "Cup(s)";
                //postion2 = 0;
                    break;
            case R.id.radioButton5:
                if(checked)
                    name2 = "Tablespoon(s)";
                //postion2 = 1;
                    break;
            case R.id.radioButton6:
                if(checked)
                    name2 = "Teaspoon(s)";
                //postion2 = 2;
                    break;

        }
        updateConversion();
    }

    public void setConversions(int pos) {
        pos = postion;

        switch (pos) {
            //cup to cup
            case 0:
                if (cUnit.equalsIgnoreCase("1")) {
                    switch (cUnit2) {
                        case "3/4th":
                            cRate = (0.75);
                            break;
                        case "2/3rd":
                            cRate = (0.66);
                            break;
                        case "1/2th":
                            cRate = (0.5);
                            break;
                        case "1/3rd":
                            cRate = (0.33);
                            break;
                        case "1/4th":
                            cRate = (0.25);
                            break;
                    cUnit= "";
                }
                if (cUnit.equalsIgnoreCase("3/4th")) {
                    cUnit = "3/4th";
                    cRate = (3.0 / 4.0);
                }
                if (cUnit.equalsIgnoreCase("2/3rd")) {
                    cUnit = "2/3rd";
                    cRate = (2.0 / 3.0);
                }
                if (cUnit.equalsIgnoreCase("1/2th")) {
                    cUnit = "1/2th";
                    cRate = (1.0 / 2.0);
                }
                if (cUnit.equalsIgnoreCase("1/3rd")) {
                    cUnit = "1/3rd";
                    cRate = (1.0 / 3.0);
                }
                if (cUnit.equalsIgnoreCase("1/4th")) {
                    cUnit = "1/4th";
                    cRate = (1.0 / 4.0);
                }
                break;
            //cup to 3/4cup
            case 1:
                if (cUnit.equalsIgnoreCase("1")) {
                    switch (cUnit2) {
                        case "3/4th":
                            cRate = (0.75);
                            break;
                        case "2/3rd":
                            cRate = (0.66);
                            break;
                        case "1/2th":
                            cRate = (0.5);
                            break;
                        case "1/3rd":
                            cRate = (0.33);
                            break;
                        case "1/4th":
                            cRate = (0.25);
                            break;
                    }
                    cUnit = "";

                }
                if (cUnit.equalsIgnoreCase("3/4th")) {
                    switch (cUnit2) {
                        case "3/4th":
                            cRate = (1.0);
                            break;
                        case "2/3rd":
                            cRate = (0.66);
                            break;
                        case "1/2th":
                            cRate = (0.5);
                            break;
                        case "1/3rd":
                            cRate = (0.33);
                            break;
                        case "1/4th":
                            cRate = (0.25);
                            break;
                        cUnit = "3/4th";

                    }
                    if (cUnit.equalsIgnoreCase("2/3rd")) {
                        cUnit = "2/3rd";
                        cRate = (2.0 / 3.0);
                    }
                    if (cUnit.equalsIgnoreCase("1/2th")) {
                        cUnit = "1/2th";
                        cRate = (1.0 / 2.0);
                    }
                    if (cUnit.equalsIgnoreCase("1/3rd")) {
                        cUnit = "1/3rd";
                        cRate = (1.0 / 3.0);
                    }
                    if (cUnit.equalsIgnoreCase("1/4th")) {
                        cUnit = "1/4th";
                        cRate = (1.0 / 4.0);
                    }
                    break;
                    //cup to 2/3 cup
                    case 2:
                        if (cUnit.equalsIgnoreCase("1")) {
                            cUnit = "";
                            cRate = 1;
                        }
                        if (cUnit.equalsIgnoreCase("3/4th")) {
                            cUnit = "3/4th";
                            cRate = (3.0 / 4.0);
                        }
                        if (cUnit.equalsIgnoreCase("2/3rd")) {
                            cUnit = "2/3rd";
                            cRate = (2.0 / 3.0);
                        }
                        if (cUnit.equalsIgnoreCase("1/2th")) {
                            cUnit = "1/2th";
                            cRate = (1.0 / 2.0);
                        }
                        if (cUnit.equalsIgnoreCase("1/3rd")) {
                            cUnit = "1/3rd";
                            cRate = (1.0 / 3.0);
                        }
                        if (cUnit.equalsIgnoreCase("1/4th")) {
                            cUnit = "1/4th";
                            cRate = (1.0 / 4.0);
                        }
                        break;
                }}}
        convertAmount();
        }
//    public void setConversions(int pos){
//        pos = postion;
//
//        switch (pos) {
//            //cup to cup
//            case 0:
//                if (cUnit.equalsIgnoreCase("1")) {
//                    cUnit = "";
//                    cRate = 1;
//                }
//                if (cUnit.equalsIgnoreCase("3/4th")) {
//                    cUnit = "3/4th";
//                    cRate = (3.0 / 4.0);
//                }
//                if (cUnit.equalsIgnoreCase("2/3rd")) {
//                    cUnit = "2/3rd";
//                    cRate = (2.0 / 3.0);
//                }
//                if (cUnit.equalsIgnoreCase("1/2th")) {
//                    cUnit = "1/2th";
//                    cRate = (1.0 / 2.0);
//                }
//                if (cUnit.equalsIgnoreCase("1/3rd")) {
//                    cUnit = "1/3rd";
//                    cRate = (1.0 / 3.0);
//                }
//                if (cUnit.equalsIgnoreCase("1/4th")) {
//                    cUnit = "1/4th";
//                    cRate = (1.0 / 4.0);
//                }
//                break;
//            //cup to 3/4cup
//            case 1:
//                if (cUnit.equalsIgnoreCase("1")) {
//                    switch (cUnit2){
//                        case "3/4th":
//                            cRate = (0.75)
//                    }
//                    cUnit = "";
//                    cRate = 1;
//                }
//                if (cUnit.equalsIgnoreCase("3/4th")) {
//                    cUnit = "3/4th";
//                    cRate = (3.0 / 4.0);
//                }
//                if (cUnit.equalsIgnoreCase("2/3rd")) {
//                    cUnit = "2/3rd";
//                    cRate = (2.0 / 3.0);
//                }
//                if (cUnit.equalsIgnoreCase("1/2th")) {
//                    cUnit = "1/2th";
//                    cRate = (1.0 / 2.0);
//                }
//                if (cUnit.equalsIgnoreCase("1/3rd")) {
//                    cUnit = "1/3rd";
//                    cRate = (1.0 / 3.0);
//                }
//                if (cUnit.equalsIgnoreCase("1/4th")) {
//                    cUnit = "1/4th";
//                    cRate = (1.0 / 4.0);
//                }
//                break;
//            //cup to 2/3 cup
//            case 2:
//                if (cUnit.equalsIgnoreCase("1")) {
//                    cUnit = "";
//                    cRate = 1;
//                }
//                if (cUnit.equalsIgnoreCase("3/4th")) {
//                    cUnit = "3/4th";
//                    cRate = (3.0 / 4.0);
//                }
//                if (cUnit.equalsIgnoreCase("2/3rd")) {
//                    cUnit = "2/3rd";
//                    cRate = (2.0 / 3.0);
//                }
//                if (cUnit.equalsIgnoreCase("1/2th")) {
//                    cUnit = "1/2th";
//                    cRate = (1.0 / 2.0);
//                }
//                if (cUnit.equalsIgnoreCase("1/3rd")) {
//                    cUnit = "1/3rd";
//                    cRate = (1.0 / 3.0);
//                }
//                if (cUnit.equalsIgnoreCase("1/4th")) {
//                    cUnit = "1/4th";
//                    cRate = (1.0 / 4.0);
//                }
//                break;
//        }
//
//        convertAmount();
//   }
    public void updateConversion(){
        conversionRate = convertAmount();
        convertedAmount = amount * conversionRate;
        mText.setText(amount + " " + cUnit + " " + name1 + " is " + convertedAmount + " " + cUnit2 + " " + name2);
    }
    public double convertAmount() {
        //cups to other units
        if (name1.equalsIgnoreCase("Cup(s)")) {
            if (name2.equalsIgnoreCase("Tablespoon(s)")) {
                return (16.0 / cRate);
            } else if (name2.equalsIgnoreCase("Teaspoon(s)")) {
                return (48.0 / cRate);
            } else {
                return cRate;
            }
            //tablespoons to other units
        } else if (name1.equalsIgnoreCase("Tablespoon(s)")) {
            if (name2.equalsIgnoreCase("Cup(s)")) {
                return (.0625 / cRate);
            } else if (name2.equalsIgnoreCase("Teaspoon(s)")) {
                return (3.0 / cRate);
            } else {
                return cRate;
            }
        } else {
            //teaspoons to other units
            if(name1.equalsIgnoreCase("Teaspoon(s)") && name2.equalsIgnoreCase("Cup(s)"))
                return (.0280333 / cRate);
            if (name2.equalsIgnoreCase("Tablespoon(s)")) {
                return (.333333 / cRate);
            } else if (name2.equalsIgnoreCase("Cup(s)")) {
                return (.0208333 / cRate);
            } else {
                return cRate;
            }
        }
    }
}



