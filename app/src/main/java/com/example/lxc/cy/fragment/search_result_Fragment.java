package com.example.lxc.cy.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.lxc.cy.R;
import com.example.lxc.cy.adapter.SR_destinationAdapter;
import com.example.lxc.cy.bean.DestinationBean;

import java.util.LinkedList;
import java.util.List;

public class search_result_Fragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_sz_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //获取listView
        ListView listView = (ListView) getView().findViewById(R.id.sz_list);
        final List<DestinationBean> list = new LinkedList<DestinationBean>();




        //Fragment内容填充
        Bundle bundle = getArguments();
        if(bundle!=null){
            int arg = bundle.getInt("arg");
            switch (arg){
                case 0:
                    SR_destinationAdapter destinationAdapter = new SR_destinationAdapter(getActivity(),(LinkedList<DestinationBean>) list);
                    listView.setAdapter(destinationAdapter);
                    //数据填充

                    destinationAdapter.notifyDataSetChanged();
                    break;
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                 default:break;
            }
        }

    }
}