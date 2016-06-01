
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
    String cUnit = "Cups";
    String cUnit2 = "Cups";
    double cRate = 1;
    double conversionRate = 1;
    double amount = 1;
    double convertedAmount = 1;
    private RadioGroup group;
    private RadioButton b1,b2,b3,b4,b5;
    int postion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinner = (Spinner) findViewById(R.id.conversion_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.conversions_array,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        Spinner spinner2 = (Spinner) findViewById(R.id.conversion_spinner2);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,R.array.conversions_array,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);

//        RadioGroup rg = (RadioGroup) findViewById(R.id.radioGroup);
//        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                switch (checkedId) {
//                    case R.id.radioButton:
//                        //
//                        break;
//                    case R.id.radioButton2:
//                        // TODO Something
//                        break;
//                    case R.id.radioButton3:
//                        // TODO Something
//                        break;
//                    case R.id.radioButton4:
//                        // TODO Something
//                        break;
//                    case R.id.radioButton5:
//                        // TODO Something
//                        break;
//                }
//            }
//        });


//        b1 = (RadioButton) findViewById(R.id.radioButton);
//        b2 = (RadioButton) findViewById(R.id.radioButton2);
//        b3 = (RadioButton) findViewById(R.id.radioButton3);
//        b4 = (RadioButton) findViewById(R.id.radioButton4);
//        b5 = (RadioButton) findViewById(R.id.radioButton5);


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
                unit1 = parent.getItemAtPosition(position).toString();
                cUnit = setConversions(position);
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
                unit2 = parent.getItemAtPosition(position).toString();
                cUnit2 = setConversions(position);
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
                break;
            case R.id.radioButton2:
                if(checked)
                    postion = 1;
                break;
            case R.id.radioButton3:
                if(checked)
                    postion = 2;
                    break;
            case R.id.radioButton4:
                if(checked)
                    postion = 3;
                    break;
            case R.id.radioButton5:
                if(checked)
                    postion = 4;
                    break;
            case R.id.radioButton6:
                if(checked)
                    postion = 5;
                    break;
        }
    }
    public void selectTwo(View view){
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId())
        {
            case R.id.radioButton7:
                if(checked)
                    //TODO
                    break;
            case R.id.radioButton8:
                if(checked)
                    //TODO
                    break;
            case R.id.radioButton9:
                if(checked)
                    //TODO
                    break;
            case R.id.radioButton10:
                if(checked)
                    //TODO
                    break;
            case R.id.radioButton11:
                if(checked)
                    //TODO
                    break;
            case R.id.radioButton12:
                if(checked)
                    //TODO
                    break;
        }
    }

    public void updateConversion(){
        conversionRate = convertAmount(cUnit,cUnit2,cRate);
        convertedAmount = amount * conversionRate;
        mText.setText(amount + " " + unit1 + " is " + convertedAmount + " " + unit2);
    }
    public double convertAmount(String convertFrom, String convertTo, double rate) {
        if (convertFrom.equalsIgnoreCase("Cups")) {
            if (convertTo.equalsIgnoreCase("TableSpoons")) {
                return (16.0 / rate);
            } else if (convertTo.equalsIgnoreCase("TeaSpoons")) {
                return (48.0 / rate);
            } else {
                return rate;
            }
        } else if (convertFrom.equalsIgnoreCase("TableSpoons")) {
            if (convertTo.equalsIgnoreCase("Cups")) {
                return (.0625 / rate);
            } else if (convertTo.equalsIgnoreCase("TeaSpoons")) {
                return (3.0 / rate);
            } else {
                return rate;
            }
        } else {
            if (convertTo.equalsIgnoreCase("TableSpoons")) {
                return (.333333 / rate);
            } else if (convertTo.equalsIgnoreCase("Cups")) {
                return (.0208333 / rate);
            } else {
                return rate;
            }
        }
    }

    public String setConversions(int pos){
        String cU ="";
        pos = postion;
        switch (pos) {
            //cup to cup
            case 0:
                cU = "Cups";
                cRate = 1;
                break;
            //cup to 3/4cup
            case 1:
                cU = "Cups";
                cRate = (4.0/3.0);
                break;
            //cup to 2/3 cup
            case 2:
                cU = "Cups";
                cRate = (3.0/2.0);
                break;
            //cup to 1/2cup
            case 3:
                cU = "Cups";
                cRate = (2.0/1.0);
                break;
            //cup to 1/3 cup
            case 4:
                cU = "Cups";
                cRate = (3.0/1.0);
                break;
            //cup to 1/4 cup
            case 5:
                cU = "Cups";
                cRate = (4.0/1.0);
                break;

            //TableSpoons
            //tablespoon to tablespoon
            case 6:
                cU = "TableSpoons";
                cRate = 1;
                break;
            //tablespoon to 3/4tablespoon
            case 7:
                cU = "TableSpoons";
                cRate = (4.0/3.0);
                break;
            //tablespoon to 2/3 tablespoon
            case 8:
                cU = "TableSpoons";
                cRate = (3.0/2.0);
                break;
            //tablespoon to 1/2tablespoon
            case 9:
                cU = "TableSpoons";
                cRate = (2.0/1.0);
                break;
            //tablespoon to 1/4tablespoon
            case 10:
                cU = "TableSpoons";
                cRate = (4.0/1.0);
                break;
            //Teaspoons!
            //teaspoon to teaspoon
            case 11:
                cU = "Teaspoons";
                cRate = 1;
                break;
            //teaspoon to 3/4teaspoon
            case 12:
                cU = "Teaspoons";
                cRate = (4.0/3.0);
                break;
            //teaspoon to 2/3 teaspoon
            case 13:
                cU = "Teaspoons";
                cRate = (3.0/2.0);
                break;
            //teaspoon to 1/2teaspoon
            case 14:
                cU = "Teaspoons";
                cRate = (2.0/1.0);
                break;
            //teaspoon to 1/4teaspoon
            case 15:
                cU = "Teaspoons";
                cRate = (4.0/1.0);
                break;


        }
        return  cU;
    }
}
