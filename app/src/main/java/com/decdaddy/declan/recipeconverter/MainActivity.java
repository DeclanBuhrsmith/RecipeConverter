
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
    public String setConversions(int position){
        String cU ="";
        switch (position) {
            case 0:
                cU = "Cups";
                cRate = 1;
                break;
            case 1:
                cU = "Cups";
                cRate = (3.0/4.0);
                break;
            case 2:
                cU = "Cups";
                cRate = (2.0/3.0);
                break;
            case 3:
                cU = "Cups";
                cRate = (1.0/2.0);
                break;
            case 4:
                cU = "Cups";
                cRate = (1.0/4.0);
                break;

            //Table Spoons
            case 5:
                cU = "TableSpoons";
                cRate = 1;
                break;
            case 6:
                cU = "TableSpoons";
                cRate = (3.0/4.0);
                break;
            case 7:
                cU = "TableSpoons";
                cRate = (2.0/3.0);
                break;
            case 8:
                cU = "TableSpoons";
                cRate = (1.0/2.0);
                break;
            case 9:
                cU = "TableSpoons";
                cRate = (1.0/4.0);
                break;
            //Teaspoons!
            case 10:
                cU = "Teaspoons";
                cRate = 1;
                break;
            case 11:
                cU = "Teaspoons";
                cRate = (3.0/4.0);
                break;
            case 12:
                cU = "Teaspoons";
                cRate = (2.0/3.0);
                break;
            case 13:
                cU = "Teaspoons";
                cRate = (1.0/2.0);
                break;
            case 14:
                cU = "Teaspoons";
                cRate = (1.0/4.0);
                break;


        }
        return  cU;
    }
}
