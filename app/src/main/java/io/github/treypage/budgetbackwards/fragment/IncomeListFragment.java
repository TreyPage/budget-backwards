//    MIT License
//
//    Copyright (c) 2019 Terrell Page
//
//    Permission is hereby granted, free of charge, to any person obtaining a copy
//    of this software and associated documentation files (the "Software"), to deal
//    in the Software without restriction, including without limitation the rights
//    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
//    copies of the Software, and to permit persons to whom the Software is
//    furnished to do so, subject to the following conditions:
//
//    The above copyright notice and this permission notice shall be included in all
//    copies or substantial portions of the Software.
//
//    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
//    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
//    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
//    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
//    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
//    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
//    SOFTWARE.
package io.github.treypage.budgetbackwards.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import com.google.android.material.snackbar.Snackbar;
import io.github.treypage.budgetbackwards.R;
import io.github.treypage.budgetbackwards.model.entity.Income;
import io.github.treypage.budgetbackwards.viewModel.MainViewModel;

import java.util.Objects;

public class IncomeListFragment extends Fragment {

    private Context context;

    public static IncomeListFragment newInstance() {
        return new IncomeListFragment();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    /***
     * This class and method does nothing more than displaying a list of all user income.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        final View view = inflater.inflate(R.layout.income_list, container, false);
        MainViewModel viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        viewModel.getIncome().observe(this, incomes -> {
            ArrayAdapter<Income> adapter = new ArrayAdapter<>(context,
                    android.R.layout.simple_list_item_1, incomes);
            ListView incomeListView = view.findViewById(R.id.income_list);
            incomeListView.setDividerHeight(20);
            incomeListView.setOnItemClickListener((arg0, arg1, position, arg3) -> {
                Income income = incomes.get(position);
                if (adapter.getCount() > 1) {
                    Snackbar snackbar = Snackbar
                            .make(Objects.requireNonNull(getView()), income.toString(), Snackbar.LENGTH_LONG);
                    snackbar.setAction("Delete", v -> viewModel.deleteIncome(income));
                    snackbar.show();
                } else {
                    Toast toast = Toast
                            .makeText(getContext(), "At least 1 Income is required, dividing by 0 hurts.", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
            });
            incomeListView.setAdapter(adapter);
        });
        return view;
    }
}
