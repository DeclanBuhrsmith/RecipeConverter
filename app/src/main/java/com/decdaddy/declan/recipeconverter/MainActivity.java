
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
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.conversions_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        final Spinner spinner2 = (Spinner) findViewById(R.id.conversion_spinner2);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.conversions_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);

        mText = (TextView) findViewById(R.id.convertedNum);

        mEdit = (EditText) findViewById(R.id.editText);
        mEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (mEdit.getText().length() > 0) {
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

    public void selectOne(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.radioButton:
                if (checked)
                    name1 = "Cup(s)";
                break;
            case R.id.radioButton2:
                if (checked)
                    name1 = "Tablespoon(s)";
                break;
            case R.id.radioButton3:
                if (checked)
                    name1 = "Teaspoon(s)";
                break;
        }
        //setConversions(postion);
        updateConversion();
    }

    public void selectTwo(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.radioButton4:
                if (checked)
                    name2 = "Cup(s)";
                //postion2 = 0;
                break;
            case R.id.radioButton5:
                if (checked)
                    name2 = "Tablespoon(s)";
                //postion2 = 1;
                break;
            case R.id.radioButton6:
                if (checked)
                    name2 = "Teaspoon(s)";
                //postion2 = 2;
                break;

        }
        updateConversion();
    }

    public void updateConversion() {
        conversionRate = convertAmount();
        convertedAmount = amount * conversionRate;
        if (cUnit.equalsIgnoreCase("1")) {
            mText.setText(amount + " " + name1 + " is " + convertedAmount + " " + cUnit2 + " " + name2);
        } else if (cUnit2.equalsIgnoreCase("1")) {
            mText.setText(amount + " " + cUnit + " " + name1 + " is " + convertedAmount + " " + name2);
        } else if (cUnit2.equalsIgnoreCase("1") && cUnit.equalsIgnoreCase("1")) {
            mText.setText(amount + " " + name1 + " is " + convertedAmount + " " + name2);
        } else
            mText.setText(amount + " " + cUnit + " " + name1 + " is " + convertedAmount + " " + cUnit2 + " " + name2);
    }

    //    public double convertAmount() {
//        //cups to other units
//        if (name1.equalsIgnoreCase("Cup(s)")) {
//            if (name2.equalsIgnoreCase("Tablespoon(s)")) {
//                return (16.0 / cRate);
//            } else if (name2.equalsIgnoreCase("Teaspoon(s)")) {
//                return (48.0 / cRate);
//            } else {
//                return cRate;
//            }
//            //tablespoons to other units
//        } else if (name1.equalsIgnoreCase("Tablespoon(s)")) {
//            if (name2.equalsIgnoreCase("Cup(s)")) {
//                return (.0625 / cRate);
//            } else if (name2.equalsIgnoreCase("Teaspoon(s)")) {
//                return (3.0 / cRate);
//            } else {
//                return cRate;
//            }
//        } else {
//            //teaspoons to other units
//            if(name1.equalsIgnoreCase("Teaspoon(s)") && name2.equalsIgnoreCase("Cup(s)"))
//                return (.0280333 / cRate);
//            if (name2.equalsIgnoreCase("Tablespoon(s)")) {
//                return (.333333 / cRate);
//            } else if (name2.equalsIgnoreCase("Cup(s)")) {
//                return (.0208333 / cRate);
//            } else {
//                return cRate;
//            }
//        }
//    }
//}
    public double convertAmount() {
        //cups to other units
        if (name1.equalsIgnoreCase(name2) && cUnit.equalsIgnoreCase(cUnit2)) {
            return 1;
        } else if (name1.equalsIgnoreCase(name2)) {
            if (cUnit.equalsIgnoreCase("3/4th")) {
                return (0.75);
            }
            if (cUnit.equalsIgnoreCase("2/3rd")) {
                return (2.0 / 3.0);
            }
            if (cUnit.equalsIgnoreCase("1/2th")) {
                return (0.5);
            }
            if (cUnit.equalsIgnoreCase("1/3rd")) {
                return (1.0 / 3.0);
            }
            if (cUnit.equalsIgnoreCase("1/4th")) {
                return (0.25);
            }
        }
        if (name1.equalsIgnoreCase("Cup(s)") && name2.equalsIgnoreCase("Tablespoon(s)")) {
            switch (cUnit2) {
                case "1":
                    if (cUnit.equalsIgnoreCase("1")) {
                        return (16.0);
                    }

                    if (cUnit.equalsIgnoreCase("3/4th")) {
                        return (16.0 * 0.75);
                    }
                    if (cUnit.equalsIgnoreCase("2/3rd")) {
                        return (16.0 * (2.0 / 3.0));
                    }
                    if (cUnit.equalsIgnoreCase("1/2th")) {
                        return (16.0 * 0.5);
                    }
                    if (cUnit.equalsIgnoreCase("1/3rd")) {
                        return (16.0 * (1.0 / 3.0));
                    }
                    if (cUnit.equalsIgnoreCase("1/4th")) {
                        return (16.0 * 0.25);
                    }
                    break;
                case "3/4th":
                    if (cUnit.equalsIgnoreCase("1")) {
                        return (16 / 0.75);
                    }
                    if (cUnit.equalsIgnoreCase("3/4th")) {
                        return (16.0);
                    }
                    if (cUnit.equalsIgnoreCase("2/3rd")) {
                        return ((16.0 * (2.0 / 3.0)) / 0.75);
                    }
                    if (cUnit.equalsIgnoreCase("1/2th")) {
                        return ((16.0 * 0.5) / 0.75);
                    }
                    if (cUnit.equalsIgnoreCase("1/3rd")) {
                        return ((16.0 * (1.0 / 3.0)) / 0.75);
                    }
                    if (cUnit.equalsIgnoreCase("1/4th")) {
                        return ((16.0 * 0.25) / 0.75);
                    }
                    break;
                case "2/3rd":
                    if (cUnit.equalsIgnoreCase("1")) {
                        return (16 / (2.0 / 3.0));
                    }
                    if (cUnit.equalsIgnoreCase("3/4th")) {
                        return (16.0 * (3.0 / 4.0)) / (2.0 / 3.0);
                    }
                    if (cUnit.equalsIgnoreCase("2/3rd")) {
                        return ((16.0 * (2.0 / 3.0)) / (2.0 / 3.0));
                    }
                    if (cUnit.equalsIgnoreCase("1/2th")) {
                        return (16.0 * (1.0 / 2.0) / (2.0 / 3.0));
                    }
                    if (cUnit.equalsIgnoreCase("1/3rd")) {
                        return ((16.0 * (1.0 / 3.0)) / (2.0 / 3.0));
                    }
                    if (cUnit.equalsIgnoreCase("1/4th")) {
                        return ((16.0 * 0.25) / (2.0 / 3.0));
                    }
                    break;
                case "1/2th":
                    if (cUnit.equalsIgnoreCase("1")) {
                        return (16 / (1.0 / 2.0));
                    }
                    if (cUnit.equalsIgnoreCase("3/4th")) {
                        return (16.0 * (3.0 / 4.0)) / (1.0 / 2.0);
                    }
                    if (cUnit.equalsIgnoreCase("2/3rd")) {
                        return ((16.0 * (2.0 / 3.0)) / (1.0 / 2.0));
                    }
                    if (cUnit.equalsIgnoreCase("1/2th")) {
                        return ((16.0 * (1.0 / 2.0)) / (1.0 / 2.0));
                    }
                    if (cUnit.equalsIgnoreCase("1/3rd")) {
                        return ((16.0 * (1.0 / 3.0)) / (1.0 / 2.0));
                    }
                    if (cUnit.equalsIgnoreCase("1/4th")) {
                        return ((16.0 * (1.0 / 4.0)) / (1.0 / 2.0));
                    }
                    break;
                case "1/3rd":
                    if (cUnit.equalsIgnoreCase("1")) {
                        return (16 / (1.0 / 3.0));
                    }
                    if (cUnit.equalsIgnoreCase("3/4th")) {
                        return (16.0 * (3.0 / 4.0)) / (1.0 / 3.0);
                    }
                    if (cUnit.equalsIgnoreCase("2/3rd")) {
                        return ((16.0 * (2.0 / 3.0)) / (1.0 / 3.0));
                    }
                    if (cUnit.equalsIgnoreCase("1/2th")) {
                        return ((16.0 * (1.0 / 2.0)) / (1.0 / 3.0));
                    }
                    if (cUnit.equalsIgnoreCase("1/3rd")) {
                        return ((16.0 * (1.0 / 3.0)) / (1.0 / 3.0));
                    }
                    if (cUnit.equalsIgnoreCase("1/4th")) {
                        return ((16.0 * (1.0 / 4.0)) / (1.0 / 3.0));
                    }
                    break;
                case "1/4th":
                    if (cUnit.equalsIgnoreCase("1")) {
                        return (16 / (1.0 / 4.0));
                    }
                    if (cUnit.equalsIgnoreCase("3/4th")) {
                        return (16.0 * (3.0 / 4.0)) / (1.0 / 4.0);
                    }
                    if (cUnit.equalsIgnoreCase("2/3rd")) {
                        return ((16.0 * (2.0 / 3.0)) / (1.0 / 4.0));
                    }
                    if (cUnit.equalsIgnoreCase("1/2th")) {
                        return ((16.0 * (1.0 / 2.0)) / (1.0 / 4.0));
                    }
                    if (cUnit.equalsIgnoreCase("1/3rd")) {
                        return ((16.0 * (1.0 / 3.0)) / (1.0 / 4.0));
                    }
                    if (cUnit.equalsIgnoreCase("1/4th")) {
                        return ((16.0 * (1.0 / 4.0)) / (1.0 / 4.0));
                    }
                    break;
            }
        }
            if (name1.equalsIgnoreCase("Cup(s)") && name2.equalsIgnoreCase("Teaspoon(s)")) {
                switch (cUnit2) {
                    case "1":
                        if (cUnit.equalsIgnoreCase("1")) {
                            return (48.0);
                        }

                        if (cUnit.equalsIgnoreCase("3/4th")) {
                            return (48.0 * 0.75);
                        }
                        if (cUnit.equalsIgnoreCase("2/3rd")) {
                            return (48.0 * (2.0 / 3.0));
                        }
                        if (cUnit.equalsIgnoreCase("1/2th")) {
                            return (48.0 * 0.5);
                        }
                        if (cUnit.equalsIgnoreCase("1/3rd")) {
                            return (48.0 * (1.0 / 3.0));
                        }
                        if (cUnit.equalsIgnoreCase("1/4th")) {
                            return (48.0 * 0.25);
                        }
                        break;
                    case "3/4th":
                        if (cUnit.equalsIgnoreCase("1")) {
                            return (48 / 0.75);
                        }
                        if (cUnit.equalsIgnoreCase("3/4th")) {
                            return (48.0);
                        }
                        if (cUnit.equalsIgnoreCase("2/3rd")) {
                            return ((48.0 * (2.0 / 3.0)) / 0.75);
                        }
                        if (cUnit.equalsIgnoreCase("1/2th")) {
                            return ((48.0 * 0.5) / 0.75);
                        }
                        if (cUnit.equalsIgnoreCase("1/3rd")) {
                            return ((48.0 * (1.0 / 3.0)) / 0.75);
                        }
                        if (cUnit.equalsIgnoreCase("1/4th")) {
                            return ((48.0 * 0.25) / 0.75);
                        }
                        break;
                    case "2/3rd":
                        if (cUnit.equalsIgnoreCase("1")) {
                            return (48 / (2.0 / 3.0));
                        }
                        if (cUnit.equalsIgnoreCase("3/4th")) {
                            return (48.0 * (3.0 / 4.0)) / (2.0 / 3.0);
                        }
                        if (cUnit.equalsIgnoreCase("2/3rd")) {
                            return ((48.0 * (2.0 / 3.0)) / (2.0 / 3.0));
                        }
                        if (cUnit.equalsIgnoreCase("1/2th")) {
                            return (48.0 * (1.0 / 2.0) / (2.0 / 3.0));
                        }
                        if (cUnit.equalsIgnoreCase("1/3rd")) {
                            return ((48.0 * (1.0 / 3.0)) / (2.0 / 3.0));
                        }
                        if (cUnit.equalsIgnoreCase("1/4th")) {
                            return ((48.0 * 0.25) / (2.0 / 3.0));
                        }
                        break;
                    case "1/2th":
                        if (cUnit.equalsIgnoreCase("1")) {
                            return (48 / (1.0 / 2.0));
                        }
                        if (cUnit.equalsIgnoreCase("3/4th")) {
                            return (48.0 * (3.0 / 4.0)) / (1.0 / 2.0);
                        }
                        if (cUnit.equalsIgnoreCase("2/3rd")) {
                            return ((48.0 * (2.0 / 3.0)) / (1.0 / 2.0));
                        }
                        if (cUnit.equalsIgnoreCase("1/2th")) {
                            return ((48.0 * (1.0 / 2.0)) / (1.0 / 2.0));
                        }
                        if (cUnit.equalsIgnoreCase("1/3rd")) {
                            return ((48.0 * (1.0 / 3.0)) / (1.0 / 2.0));
                        }
                        if (cUnit.equalsIgnoreCase("1/4th")) {
                            return ((48.0 * (1.0 / 4.0)) / (1.0 / 2.0));
                        }
                        break;
                    case "1/3rd":
                        if (cUnit.equalsIgnoreCase("1")) {
                            return (48 / (1.0 / 3.0));
                        }
                        if (cUnit.equalsIgnoreCase("3/4th")) {
                            return (48.0 * (3.0 / 4.0)) / (1.0 / 3.0);
                        }
                        if (cUnit.equalsIgnoreCase("2/3rd")) {
                            return ((48.0 * (2.0 / 3.0)) / (1.0 / 3.0));
                        }
                        if (cUnit.equalsIgnoreCase("1/2th")) {
                            return ((48.0 * (1.0 / 2.0)) / (1.0 / 3.0));
                        }
                        if (cUnit.equalsIgnoreCase("1/3rd")) {
                            return ((48.0 * (1.0 / 3.0)) / (1.0 / 3.0));
                        }
                        if (cUnit.equalsIgnoreCase("1/4th")) {
                            return ((48.0 * (1.0 / 4.0)) / (1.0 / 3.0));
                        }
                        break;
                    case "1/4th":
                        if (cUnit.equalsIgnoreCase("1")) {
                            return (48 / (1.0 / 4.0));
                        }
                        if (cUnit.equalsIgnoreCase("3/4th")) {
                            return (48.0 * (3.0 / 4.0)) / (1.0 / 4.0);
                        }
                        if (cUnit.equalsIgnoreCase("2/3rd")) {
                            return ((48.0 * (2.0 / 3.0)) / (1.0 / 4.0));
                        }
                        if (cUnit.equalsIgnoreCase("1/2th")) {
                            return ((48.0 * (1.0 / 2.0)) / (1.0 / 4.0));
                        }
                        if (cUnit.equalsIgnoreCase("1/3rd")) {
                            return ((48.0 * (1.0 / 3.0)) / (1.0 / 4.0));
                        }
                        if (cUnit.equalsIgnoreCase("1/4th")) {
                            return ((48.0 * (1.0 / 4.0)) / (1.0 / 4.0));
                        }
                }


            }
            if (name1.equalsIgnoreCase("Teaspoon(s)") && name2.equalsIgnoreCase("Tablespoon(s)")) {
                switch (cUnit2) {
                    case "1":
                        if (cUnit.equalsIgnoreCase("1")) {
                            return (1.0/3.0);
                        }

                        if (cUnit.equalsIgnoreCase("3/4th")) {
                            return ((1.0/3.0) * 0.75);
                        }
                        if (cUnit.equalsIgnoreCase("2/3rd")) {
                            return ((1.0/3.0) * (2.0 / 3.0));
                        }
                        if (cUnit.equalsIgnoreCase("1/2th")) {
                            return ((1.0/3.0) * 0.5);
                        }
                        if (cUnit.equalsIgnoreCase("1/3rd")) {
                            return ((1.0/3.0) * (1.0 / 3.0));
                        }
                        if (cUnit.equalsIgnoreCase("1/4th")) {
                            return ((1.0/3.0) * 0.25);
                        }
                        break;
                    case "3/4th":
                        if (cUnit.equalsIgnoreCase("1")) {
                            return ((1.0/3.0) / 0.75);
                        }
                        if (cUnit.equalsIgnoreCase("3/4th")) {
                            return ((1.0/3.0));
                        }
                        if (cUnit.equalsIgnoreCase("2/3rd")) {
                            return (((1.0/3.0) * (2.0 / 3.0)) / 0.75);
                        }
                        if (cUnit.equalsIgnoreCase("1/2th")) {
                            return (((1.0/3.0) * 0.5) / 0.75);
                        }
                        if (cUnit.equalsIgnoreCase("1/3rd")) {
                            return (((1.0/3.0) * (1.0 / 3.0)) / 0.75);
                        }
                        if (cUnit.equalsIgnoreCase("1/4th")) {
                            return (((1.0/3.0) * 0.25) / 0.75);
                        }
                        break;
                    case "2/3rd":
                        if (cUnit.equalsIgnoreCase("1")) {
                            return ((1.0/3.0) / (2.0 / 3.0));
                        }
                        if (cUnit.equalsIgnoreCase("3/4th")) {
                            return ((1.0/3.0) * (3.0 / 4.0)) / (2.0 / 3.0);
                        }
                        if (cUnit.equalsIgnoreCase("2/3rd")) {
                            return (((1.0/3.0) * (2.0 / 3.0)) / (2.0 / 3.0));
                        }
                        if (cUnit.equalsIgnoreCase("1/2th")) {
                            return ((1.0/3.0) * (1.0 / 2.0) / (2.0 / 3.0));
                        }
                        if (cUnit.equalsIgnoreCase("1/3rd")) {
                            return (((1.0/3.0) * (1.0 / 3.0)) / (2.0 / 3.0));
                        }
                        if (cUnit.equalsIgnoreCase("1/4th")) {
                            return (((1.0/3.0) * 0.25) / (2.0 / 3.0));
                        }
                        break;
                    case "1/2th":
                        if (cUnit.equalsIgnoreCase("1")) {
                            return ((1.0/3.0) / (1.0 / 2.0));
                        }
                        if (cUnit.equalsIgnoreCase("3/4th")) {
                            return ((1.0/3.0) * (3.0 / 4.0)) / (1.0 / 2.0);
                        }
                        if (cUnit.equalsIgnoreCase("2/3rd")) {
                            return (((1.0/3.0) * (2.0 / 3.0)) / (1.0 / 2.0));
                        }
                        if (cUnit.equalsIgnoreCase("1/2th")) {
                            return (((1.0/3.0) * (1.0 / 2.0)) / (1.0 / 2.0));
                        }
                        if (cUnit.equalsIgnoreCase("1/3rd")) {
                            return (((1.0/3.0) * (1.0 / 3.0)) / (1.0 / 2.0));
                        }
                        if (cUnit.equalsIgnoreCase("1/4th")) {
                            return (((1.0/3.0) * (1.0 / 4.0)) / (1.0 / 2.0));
                        }
                        break;
                    case "1/3rd":
                        if (cUnit.equalsIgnoreCase("1")) {
                            return ((1.0/3.0) / (1.0 / 3.0));
                        }
                        if (cUnit.equalsIgnoreCase("3/4th")) {
                            return ((1.0/3.0) * (3.0 / 4.0)) / (1.0 / 3.0);
                        }
                        if (cUnit.equalsIgnoreCase("2/3rd")) {
                            return (((1.0/3.0) * (2.0 / 3.0)) / (1.0 / 3.0));
                        }
                        if (cUnit.equalsIgnoreCase("1/2th")) {
                            return (((1.0/3.0) * (1.0 / 2.0)) / (1.0 / 3.0));
                        }
                        if (cUnit.equalsIgnoreCase("1/3rd")) {
                            return (((1.0/3.0) * (1.0 / 3.0)) / (1.0 / 3.0));
                        }
                        if (cUnit.equalsIgnoreCase("1/4th")) {
                            return (((1.0/3.0) * (1.0 / 4.0)) / (1.0 / 3.0));
                        }
                        break;
                    case "1/4th":
                        if (cUnit.equalsIgnoreCase("1")) {
                            return ((1.0/3.0) / (1.0 / 4.0));
                        }
                        if (cUnit.equalsIgnoreCase("3/4th")) {
                            return ((1.0/3.0) * (3.0 / 4.0)) / (1.0 / 4.0);
                        }
                        if (cUnit.equalsIgnoreCase("2/3rd")) {
                            return (((1.0/3.0) * (2.0 / 3.0)) / (1.0 / 4.0));
                        }
                        if (cUnit.equalsIgnoreCase("1/2th")) {
                            return (((1.0/3.0) * (1.0 / 2.0)) / (1.0 / 4.0));
                        }
                        if (cUnit.equalsIgnoreCase("1/3rd")) {
                            return (((1.0/3.0) * (1.0 / 3.0)) / (1.0 / 4.0));
                        }
                        if (cUnit.equalsIgnoreCase("1/4th")) {
                            return (((1.0/3.0) * (1.0 / 4.0)) / (1.0 / 4.0));
                        }
                }


            }
            if (name1.equalsIgnoreCase("Tablespoon(s)") && name2.equalsIgnoreCase("Teaspoon(s)")) {
                switch (cUnit2) {
                    case "1":
                        if (cUnit.equalsIgnoreCase("1")) {
                            return (3.0);
                        }

                        if (cUnit.equalsIgnoreCase("3/4th")) {
                            return (3.0 * 0.75);
                        }
                        if (cUnit.equalsIgnoreCase("2/3rd")) {
                            return (3.0 * (2.0 / 3.0));
                        }
                        if (cUnit.equalsIgnoreCase("1/2th")) {
                            return (3.0 * 0.5);
                        }
                        if (cUnit.equalsIgnoreCase("1/3rd")) {
                            return (3.0 * (1.0 / 3.0));
                        }
                        if (cUnit.equalsIgnoreCase("1/4th")) {
                            return (3.0 * 0.25);
                        }
                        break;
                    case "3/4th":
                        if (cUnit.equalsIgnoreCase("1")) {
                            return (3.0 / 0.75);
                        }
                        if (cUnit.equalsIgnoreCase("3/4th")) {
                            return (3.0);
                        }
                        if (cUnit.equalsIgnoreCase("2/3rd")) {
                            return ((3.0 * (2.0 / 3.0)) / 0.75);
                        }
                        if (cUnit.equalsIgnoreCase("1/2th")) {
                            return ((3.0 * 0.5) / 0.75);
                        }
                        if (cUnit.equalsIgnoreCase("1/3rd")) {
                            return ((3.0 * (1.0 / 3.0)) / 0.75);
                        }
                        if (cUnit.equalsIgnoreCase("1/4th")) {
                            return ((3.0 * 0.25) / 0.75);
                        }
                        break;
                    case "2/3rd":
                        if (cUnit.equalsIgnoreCase("1")) {
                            return (3.0 / (2.0 / 3.0));
                        }
                        if (cUnit.equalsIgnoreCase("3/4th")) {
                            return (3.0 * (3.0 / 4.0)) / (2.0 / 3.0);
                        }
                        if (cUnit.equalsIgnoreCase("2/3rd")) {
                            return ((3.0 * (2.0 / 3.0)) / (2.0 / 3.0));
                        }
                        if (cUnit.equalsIgnoreCase("1/2th")) {
                            return (3.0 * (1.0 / 2.0) / (2.0 / 3.0));
                        }
                        if (cUnit.equalsIgnoreCase("1/3rd")) {
                            return ((3.0 * (1.0 / 3.0)) / (2.0 / 3.0));
                        }
                        if (cUnit.equalsIgnoreCase("1/4th")) {
                            return ((3.0 * 0.25) / (2.0 / 3.0));
                        }
                        break;
                    case "1/2th":
                        if (cUnit.equalsIgnoreCase("1")) {
                            return (3.0 / (1.0 / 2.0));
                        }
                        if (cUnit.equalsIgnoreCase("3/4th")) {
                            return (3.0 * (3.0 / 4.0)) / (1.0 / 2.0);
                        }
                        if (cUnit.equalsIgnoreCase("2/3rd")) {
                            return ((3.0 * (2.0 / 3.0)) / (1.0 / 2.0));
                        }
                        if (cUnit.equalsIgnoreCase("1/2th")) {
                            return ((3.0 * (1.0 / 2.0)) / (1.0 / 2.0));
                        }
                        if (cUnit.equalsIgnoreCase("1/3rd")) {
                            return ((3.0 * (1.0 / 3.0)) / (1.0 / 2.0));
                        }
                        if (cUnit.equalsIgnoreCase("1/4th")) {
                            return ((3.0 * (1.0 / 4.0)) / (1.0 / 2.0));
                        }
                        break;
                    case "1/3rd":
                        if (cUnit.equalsIgnoreCase("1")) {
                            return (3.0 / (1.0 / 3.0));
                        }
                        if (cUnit.equalsIgnoreCase("3/4th")) {
                            return (3.0 * (3.0 / 4.0)) / (1.0 / 3.0);
                        }
                        if (cUnit.equalsIgnoreCase("2/3rd")) {
                            return ((3.0 * (2.0 / 3.0)) / (1.0 / 3.0));
                        }
                        if (cUnit.equalsIgnoreCase("1/2th")) {
                            return ((3.0 * (1.0 / 2.0)) / (1.0 / 3.0));
                        }
                        if (cUnit.equalsIgnoreCase("1/3rd")) {
                            return ((3.0 * (1.0 / 3.0)) / (1.0 / 3.0));
                        }
                        if (cUnit.equalsIgnoreCase("1/4th")) {
                            return ((3.0 * (1.0 / 4.0)) / (1.0 / 3.0));
                        }
                        break;
                    case "1/4th":
                        if (cUnit.equalsIgnoreCase("1")) {
                            return (3.0 / (1.0 / 4.0));
                        }
                        if (cUnit.equalsIgnoreCase("3/4th")) {
                            return (3.0 * (3.0 / 4.0)) / (1.0 / 4.0);
                        }
                        if (cUnit.equalsIgnoreCase("2/3rd")) {
                            return ((3.0 * (2.0 / 3.0)) / (1.0 / 4.0));
                        }
                        if (cUnit.equalsIgnoreCase("1/2th")) {
                            return ((3.0 * (1.0 / 2.0)) / (1.0 / 4.0));
                        }
                        if (cUnit.equalsIgnoreCase("1/3rd")) {
                            return ((3.0 * (1.0 / 3.0)) / (1.0 / 4.0));
                        }
                        if (cUnit.equalsIgnoreCase("1/4th")) {
                            return ((3.0 * (1.0 / 4.0)) / (1.0 / 4.0));
                        }
                }


            }
            if (name1.equalsIgnoreCase("Tablespoon(s)") && name2.equalsIgnoreCase("Cup(s)")) {
            switch (cUnit2) {
                case "1":
                    if (cUnit.equalsIgnoreCase("1")) {
                        return (0.0625);
                    }

                    if (cUnit.equalsIgnoreCase("3/4th")) {
                        return (0.0625 * 0.75);
                    }
                    if (cUnit.equalsIgnoreCase("2/3rd")) {
                        return (0.0625 * (2.0 / 3.0));
                    }
                    if (cUnit.equalsIgnoreCase("1/2th")) {
                        return (0.0625 * 0.5);
                    }
                    if (cUnit.equalsIgnoreCase("1/3rd")) {
                        return (0.0625 * (1.0 / 3.0));
                    }
                    if (cUnit.equalsIgnoreCase("1/4th")) {
                        return (0.0625 * 0.25);
                    }
                    break;
                case "3/4th":
                    if (cUnit.equalsIgnoreCase("1")) {
                        return (0.0625 / 0.75);
                    }
                    if (cUnit.equalsIgnoreCase("3/4th")) {
                        return (0.0625);
                    }
                    if (cUnit.equalsIgnoreCase("2/3rd")) {
                        return ((0.0625 * (2.0 / 3.0)) / 0.75);
                    }
                    if (cUnit.equalsIgnoreCase("1/2th")) {
                        return ((0.0625 * 0.5) / 0.75);
                    }
                    if (cUnit.equalsIgnoreCase("1/3rd")) {
                        return ((0.0625 * (1.0 / 3.0)) / 0.75);
                    }
                    if (cUnit.equalsIgnoreCase("1/4th")) {
                        return ((0.0625 * 0.25) / 0.75);
                    }
                    break;
                case "2/3rd":
                    if (cUnit.equalsIgnoreCase("1")) {
                        return (0.0625 / (2.0 / 3.0));
                    }
                    if (cUnit.equalsIgnoreCase("3/4th")) {
                        return (0.0625 * (3.0 / 4.0)) / (2.0 / 3.0);
                    }
                    if (cUnit.equalsIgnoreCase("2/3rd")) {
                        return ((0.0625 * (2.0 / 3.0)) / (2.0 / 3.0));
                    }
                    if (cUnit.equalsIgnoreCase("1/2th")) {
                        return (0.0625 * (1.0 / 2.0) / (2.0 / 3.0));
                    }
                    if (cUnit.equalsIgnoreCase("1/3rd")) {
                        return ((0.0625 * (1.0 / 3.0)) / (2.0 / 3.0));
                    }
                    if (cUnit.equalsIgnoreCase("1/4th")) {
                        return ((0.0625 * 0.25) / (2.0 / 3.0));
                    }
                    break;
                case "1/2th":
                    if (cUnit.equalsIgnoreCase("1")) {
                        return (0.0625 / (1.0 / 2.0));
                    }
                    if (cUnit.equalsIgnoreCase("3/4th")) {
                        return (0.0625 * (3.0 / 4.0)) / (1.0 / 2.0);
                    }
                    if (cUnit.equalsIgnoreCase("2/3rd")) {
                        return ((0.0625 * (2.0 / 3.0)) / (1.0 / 2.0));
                    }
                    if (cUnit.equalsIgnoreCase("1/2th")) {
                        return ((0.0625 * (1.0 / 2.0)) / (1.0 / 2.0));
                    }
                    if (cUnit.equalsIgnoreCase("1/3rd")) {
                        return ((0.0625 * (1.0 / 3.0)) / (1.0 / 2.0));
                    }
                    if (cUnit.equalsIgnoreCase("1/4th")) {
                        return ((0.0625 * (1.0 / 4.0)) / (1.0 / 2.0));
                    }
                    break;
                case "1/3rd":
                    if (cUnit.equalsIgnoreCase("1")) {
                        return (0.0625 / (1.0 / 3.0));
                    }
                    if (cUnit.equalsIgnoreCase("3/4th")) {
                        return (0.0625 * (3.0 / 4.0)) / (1.0 / 3.0);
                    }
                    if (cUnit.equalsIgnoreCase("2/3rd")) {
                        return ((0.0625 * (2.0 / 3.0)) / (1.0 / 3.0));
                    }
                    if (cUnit.equalsIgnoreCase("1/2th")) {
                        return ((0.0625 * (1.0 / 2.0)) / (1.0 / 3.0));
                    }
                    if (cUnit.equalsIgnoreCase("1/3rd")) {
                        return ((0.0625 * (1.0 / 3.0)) / (1.0 / 3.0));
                    }
                    if (cUnit.equalsIgnoreCase("1/4th")) {
                        return ((0.0625 * (1.0 / 4.0)) / (1.0 / 3.0));
                    }
                    break;
                case "1/4th":
                    if (cUnit.equalsIgnoreCase("1")) {
                        return (0.0625 / (1.0 / 4.0));
                    }
                    if (cUnit.equalsIgnoreCase("3/4th")) {
                        return (0.0625 * (3.0 / 4.0)) / (1.0 / 4.0);
                    }
                    if (cUnit.equalsIgnoreCase("2/3rd")) {
                        return ((0.0625 * (2.0 / 3.0)) / (1.0 / 4.0));
                    }
                    if (cUnit.equalsIgnoreCase("1/2th")) {
                        return ((0.0625 * (1.0 / 2.0)) / (1.0 / 4.0));
                    }
                    if (cUnit.equalsIgnoreCase("1/3rd")) {
                        return ((0.0625 * (1.0 / 3.0)) / (1.0 / 4.0));
                    }
                    if (cUnit.equalsIgnoreCase("1/4th")) {
                        return ((0.0625 * (1.0 / 4.0)) / (1.0 / 4.0));
                    }
            }


            }
            if (name1.equalsIgnoreCase("Teaspoon(s)") && name2.equalsIgnoreCase("Cup(s)")) {
            switch (cUnit2) {
                case "1":
                    if (cUnit.equalsIgnoreCase("1")) {
                        return (0.0208333);
                    }

                    if (cUnit.equalsIgnoreCase("3/4th")) {
                        return (0.0208333 * 0.75);
                    }
                    if (cUnit.equalsIgnoreCase("2/3rd")) {
                        return (0.0208333 * (2.0 / 3.0));
                    }
                    if (cUnit.equalsIgnoreCase("1/2th")) {
                        return (0.0208333 * 0.5);
                    }
                    if (cUnit.equalsIgnoreCase("1/3rd")) {
                        return (0.0208333 * (1.0 / 3.0));
                    }
                    if (cUnit.equalsIgnoreCase("1/4th")) {
                        return (0.0208333 * 0.25);
                    }
                    break;
                case "3/4th":
                    if (cUnit.equalsIgnoreCase("1")) {
                        return (0.0208333 / 0.75);
                    }
                    if (cUnit.equalsIgnoreCase("3/4th")) {
                        return (0.0208333);
                    }
                    if (cUnit.equalsIgnoreCase("2/3rd")) {
                        return ((0.0208333 * (2.0 / 3.0)) / 0.75);
                    }
                    if (cUnit.equalsIgnoreCase("1/2th")) {
                        return ((0.0208333 * 0.5) / 0.75);
                    }
                    if (cUnit.equalsIgnoreCase("1/3rd")) {
                        return ((0.0208333 * (1.0 / 3.0)) / 0.75);
                    }
                    if (cUnit.equalsIgnoreCase("1/4th")) {
                        return ((0.0208333 * 0.25) / 0.75);
                    }
                    break;
                case "2/3rd":
                    if (cUnit.equalsIgnoreCase("1")) {
                        return (0.0208333 / (2.0 / 3.0));
                    }
                    if (cUnit.equalsIgnoreCase("3/4th")) {
                        return (0.0208333 * (3.0 / 4.0)) / (2.0 / 3.0);
                    }
                    if (cUnit.equalsIgnoreCase("2/3rd")) {
                        return ((0.0208333 * (2.0 / 3.0)) / (2.0 / 3.0));
                    }
                    if (cUnit.equalsIgnoreCase("1/2th")) {
                        return (0.0208333 * (1.0 / 2.0) / (2.0 / 3.0));
                    }
                    if (cUnit.equalsIgnoreCase("1/3rd")) {
                        return ((0.0208333 * (1.0 / 3.0)) / (2.0 / 3.0));
                    }
                    if (cUnit.equalsIgnoreCase("1/4th")) {
                        return ((0.0208333 * 0.25) / (2.0 / 3.0));
                    }
                    break;
                case "1/2th":
                    if (cUnit.equalsIgnoreCase("1")) {
                        return (0.0208333 / (1.0 / 2.0));
                    }
                    if (cUnit.equalsIgnoreCase("3/4th")) {
                        return (0.0208333 * (3.0 / 4.0)) / (1.0 / 2.0);
                    }
                    if (cUnit.equalsIgnoreCase("2/3rd")) {
                        return ((0.0208333 * (2.0 / 3.0)) / (1.0 / 2.0));
                    }
                    if (cUnit.equalsIgnoreCase("1/2th")) {
                        return ((0.0208333 * (1.0 / 2.0)) / (1.0 / 2.0));
                    }
                    if (cUnit.equalsIgnoreCase("1/3rd")) {
                        return ((0.0208333 * (1.0 / 3.0)) / (1.0 / 2.0));
                    }
                    if (cUnit.equalsIgnoreCase("1/4th")) {
                        return ((0.0208333 * (1.0 / 4.0)) / (1.0 / 2.0));
                    }
                    break;
                case "1/3rd":
                    if (cUnit.equalsIgnoreCase("1")) {
                        return (0.0208333 / (1.0 / 3.0));
                    }
                    if (cUnit.equalsIgnoreCase("3/4th")) {
                        return (0.0208333 * (3.0 / 4.0)) / (1.0 / 3.0);
                    }
                    if (cUnit.equalsIgnoreCase("2/3rd")) {
                        return ((0.0208333 * (2.0 / 3.0)) / (1.0 / 3.0));
                    }
                    if (cUnit.equalsIgnoreCase("1/2th")) {
                        return ((0.0208333 * (1.0 / 2.0)) / (1.0 / 3.0));
                    }
                    if (cUnit.equalsIgnoreCase("1/3rd")) {
                        return ((0.0208333 * (1.0 / 3.0)) / (1.0 / 3.0));
                    }
                    if (cUnit.equalsIgnoreCase("1/4th")) {
                        return ((0.0208333 * (1.0 / 4.0)) / (1.0 / 3.0));
                    }
                    break;
                case "1/4th":
                    if (cUnit.equalsIgnoreCase("1")) {
                        return (0.0208333 / (1.0 / 4.0));
                    }
                    if (cUnit.equalsIgnoreCase("3/4th")) {
                        return (0.0208333 * (3.0 / 4.0)) / (1.0 / 4.0);
                    }
                    if (cUnit.equalsIgnoreCase("2/3rd")) {
                        return ((0.0208333 * (2.0 / 3.0)) / (1.0 / 4.0));
                    }
                    if (cUnit.equalsIgnoreCase("1/2th")) {
                        return ((0.0208333 * (1.0 / 2.0)) / (1.0 / 4.0));
                    }
                    if (cUnit.equalsIgnoreCase("1/3rd")) {
                        return ((0.0208333 * (1.0 / 3.0)) / (1.0 / 4.0));
                    }
                    if (cUnit.equalsIgnoreCase("1/4th")) {
                        return ((0.0208333 * (1.0 / 4.0)) / (1.0 / 4.0));
                    }
            }


        }

        return 420;
        }

    }





