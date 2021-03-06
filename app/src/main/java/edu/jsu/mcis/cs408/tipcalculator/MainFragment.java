package edu.jsu.mcis.cs408.tipcalculator;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputLayout;

public class MainFragment extends Fragment {

    private TextInputLayout totalBill, tipPercentage, numberPeople, textOutput;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        totalBill = (TextInputLayout) view.findViewById(R.id.totalBill);
        tipPercentage = (TextInputLayout) view.findViewById(R.id.tipPercentage);
        numberPeople = (TextInputLayout) view.findViewById(R.id.numberPeople);
        view.findViewById(R.id.buttonCalculate).setOnClickListener(this::onClick);
    }

    public void onClick(View v) {
        checkValid(totalBill);
    }

    public boolean checkValid(TextInputLayout t){
        //Check if the input is blank, not a number, or 0
        String s = t.getEditText().toString();

        if (s == ""){
            Toast.makeText(getActivity(), "Values cannot be blank!", Toast.LENGTH_LONG).show();
            return false;
        }

        if (s != null && s.matches("[-+]?\\d*\\.?\\d+") == false){
            Toast.makeText(getActivity(), "Values must be numeric!", Toast.LENGTH_LONG).show();
            return false;
        }

        if (Integer.parseInt(s) == 0){
            Toast.makeText(getActivity(), "Values cannot be zero!", Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }
}
