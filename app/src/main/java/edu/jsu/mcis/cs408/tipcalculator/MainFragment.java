package edu.jsu.mcis.cs408.tipcalculator;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;

import java.text.NumberFormat;

public class MainFragment extends Fragment {

    private TextInputEditText totalBill, tipPercentage, numberPeople, textOutput;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        totalBill = (TextInputEditText) view.findViewById(R.id.totalBill);
        tipPercentage = (TextInputEditText) view.findViewById(R.id.tipPercentage);
        numberPeople = (TextInputEditText) view.findViewById(R.id.numberPeople);
        view.findViewById(R.id.buttonCalculate).setOnClickListener(this::onClick);
    }

    public void onClick(View v) {
        //Check inputs for any errors
        if (checkValid(totalBill) == true && checkValid(tipPercentage) == true && checkValid(numberPeople) == true){
            //Calculate the total bill split
            double billDouble = Double.parseDouble(totalBill.getText().toString());
            double tipDouble = Double.parseDouble(tipPercentage.getText().toString());
            double peopleDouble = Double.parseDouble(numberPeople.getText().toString());
            long billSplit = (long)((billDouble + (billDouble * tipDouble / 100)) / peopleDouble);

            //Convert double to a currency-valid value
            int dollars = (int)billSplit;
            double cents = (Math.ceil((billSplit - dollars) * 100)) / 100;
            billSplit = (long)(dollars + cents);

            //Convert double to currency format
            NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
            String outputString = currencyFormat.format(billSplit);

            //Update the output text to show the calculate split
            TextView output = (TextView) getView().findViewById(R.id.textOutput);
            output.setText("Calculated split: " + billSplit);
        }

    }

    public boolean checkValid(TextInputEditText t){
        //Check if the input is blank, not a number, or 0
        String s = t.getText().toString();

        if (s.trim().isEmpty() || s == null){
            Toast.makeText(getActivity(), "Values cannot be blank!", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (s != null && !s.matches("[-+]?\\d*\\.?\\d+")){
            Toast.makeText(getActivity(), "Values must be numeric!", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (Double.parseDouble(s) == 0){
            Toast.makeText(getActivity(), "Values cannot be zero!", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
}
