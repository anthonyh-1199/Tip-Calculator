package edu.jsu.mcis.cs408.tipcalculator;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;

public class MainFragment extends Fragment {

    private TextView totalBill, tipPercentage, peopleNumber;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        output = (TextView) view.findViewById(R.id.output);
        view.findViewById(R.id.input).setOnClickListener(this::onClick);
    }

    public void onClick(View v) {
        output.setText("Hello, World!");
    }
}
