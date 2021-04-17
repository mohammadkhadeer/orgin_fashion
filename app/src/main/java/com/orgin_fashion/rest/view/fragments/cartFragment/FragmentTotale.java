package com.orgin_fashion.rest.view.fragments.cartFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.orgin_fashion.rest.R;
import com.orgin_fashion.rest.functions.Functions;
import com.orgin_fashion.rest.model.ItemInDB;
import java.util.ArrayList;
import static com.orgin_fashion.rest.database.Read.getAllItemFromCart;
import static com.orgin_fashion.rest.functions.Functions.roundTwoDecimals;

public class FragmentTotale extends Fragment {
    View view;
    public FragmentTotale(){}
    TextView total,tax,finalPrice,totalN,taxN,finalN,checkOut;
    double totalD=0.0,taxD=0.0,finalT=0.0;

    public ArrayList<ItemInDB> itemInCartArrayList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_cart_totale, container, false);
        inti();
        changeFont();
        itemInCartArrayList = getAllItemFromCart(getActivity());
        cal();
        fillTV();
        return view;
    }

    private void fillTV() {
        totalN.setText(String.valueOf(roundTwoDecimals(totalD)));
        taxN.setText(String.valueOf(roundTwoDecimals(taxD)));
        finalN.setText(String.valueOf(roundTwoDecimals(finalT)));
    }

    private void cal() {
        for (int i =0;i<itemInCartArrayList.size();i++)
        {
            double z = itemInCartArrayList.get(i).getPriceN() * itemInCartArrayList.get(i).getNumberOfItemNow();
            totalD = totalD + z;
            double tax =z * 0.05;
            double finalPW = totalD + tax;
            finalT = finalPW + finalT;
            taxD = taxD + tax;
        }
    }

    private void changeFont() {
        total.setTypeface(Functions.changeFontGeneral(getActivity()));
        tax.setTypeface(Functions.changeFontGeneral(getActivity()));
        finalPrice.setTypeface(Functions.changeFontGeneral(getActivity()));

        totalN.setTypeface(Functions.changeFontGeneral(getActivity()));
        taxN.setTypeface(Functions.changeFontGeneral(getActivity()));
        finalN.setTypeface(Functions.changeFontGeneral(getActivity()));
        checkOut.setTypeface(Functions.changeFontGeneral(getActivity()));
    }


    private void inti() {
        total = (TextView) view.findViewById(R.id.total);
        tax = (TextView) view.findViewById(R.id.tax);
        finalPrice = (TextView) view.findViewById(R.id.total_price);

        totalN = (TextView) view.findViewById(R.id.total_num);
        taxN = (TextView) view.findViewById(R.id.tax_num);
        finalN = (TextView) view.findViewById(R.id.total_price_num);
        checkOut = (TextView) view.findViewById(R.id.checkOut);
    }

    public void updateValue() {
        totalD=0;
        taxD=0;
        finalT=0;
        itemInCartArrayList = getAllItemFromCart(getActivity());
        cal();
        fillTV();
    }

}
