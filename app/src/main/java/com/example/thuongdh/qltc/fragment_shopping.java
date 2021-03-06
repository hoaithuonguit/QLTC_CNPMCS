package com.example.thuongdh.qltc;

import com.example.thuongdh.adapter.outCustomAdapter;
import com.example.thuongdh.model.out_money_model;

import java.util.ArrayList;

import android.app.Activity;
import android.app.Fragment;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by thuongdh on 13/11/2017.
 */

public class fragment_shopping extends Fragment {
    private View myv;
    int id = 0;
    TextView tv1, tv2, tv3;
    EditText edtName, edtPrice;
    ImageButton imbtnAdd, imbtnDel;
    outCustomAdapter adapterlist;
    SQLiteDatabase database;
    String db_name = "QuanLyThuChiDb.sqlite";
    ArrayList<out_money_model> arrayList;
    ListView lv;
    public interface ValuesWithActivityShopping {
        public void SendToActivityShopping(String key);
    }
    fragment_shopping.ValuesWithActivityShopping sendKey;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myv = inflater.inflate(R.layout.fragment_shopping, container,false);
        tv1 = myv.findViewById(R.id.tvfrShoppingName);
        tv2 = myv.findViewById(R.id.tvfrShoppingPrice);
        tv3 = myv.findViewById(R.id.tvfrShoppingTitle);
        edtName = myv.findViewById(R.id.edtfrShoppingName);
        edtPrice = myv.findViewById(R.id.edtfrShoppingPrice);
        imbtnAdd = myv.findViewById(R.id.imbtnfrShoppingAdd);
        imbtnDel = myv.findViewById(R.id.imbtnfrShoppingDel);
        lv = myv.findViewById(R.id.lvfrShopping);
        imbtnAdd.setImageResource(R.drawable.add);
        arrayList = new ArrayList<out_money_model>();
        imbtnDel.setImageResource(R.drawable.clear);
        //database = openOrCreateDatabase(db_name, null);
        setEvent();
        return myv;
    }

    private void setEvent() {
        imbtnAdd.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                 try
                {
                    String name = edtName.getText().toString();
                    int money = Integer.parseInt(edtPrice.getText().toString());
                    id++;
                    int i=0;
                    arrayList.add(new out_money_model(id,name,money));
                    adapterlist = new outCustomAdapter(getActivity(),
                            R.layout.lv_custom,
                            arrayList);
                    lv.setAdapter(adapterlist);
                    sendKey.SendToActivityShopping("Shopping;"+ name + ";" + money);

                } catch (Exception e) {
                 Toast.makeText(getActivity(), "Vui lòng nhập đầy đủ thông tin ", Toast.LENGTH_LONG).show();
                }
            }
        });
        imbtnDel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                edtName.setText(null);
                edtPrice.setText(null);
            }
        });
    }
    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        sendKey = (fragment_shopping.ValuesWithActivityShopping) getActivity();
    }

}
