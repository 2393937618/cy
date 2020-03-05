package com.example.lxc.cy.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.lxc.cy.R;
import com.example.lxc.cy.adapter.SR_answerAdapter;
import com.example.lxc.cy.adapter.SR_destinationAdapter;
import com.example.lxc.cy.adapter.SR_strategyAdapter;
import com.example.lxc.cy.adapter.SR_travelAdapter;
import com.example.lxc.cy.adapter.SR_userAdapter;
import com.example.lxc.cy.bean.AnswersBean;
import com.example.lxc.cy.bean.DestinationBean;
import com.example.lxc.cy.bean.StrategyBean;
import com.example.lxc.cy.bean.TravelBean;
import com.example.lxc.cy.bean.UserBean;

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
        final List<DestinationBean> destination = new LinkedList<DestinationBean>();
        final List<TravelBean> travel = new LinkedList<TravelBean>();
        final List<StrategyBean> strategy = new LinkedList<StrategyBean>();
        final List<AnswersBean> answer = new LinkedList<AnswersBean>();
        final List<UserBean> user = new LinkedList<UserBean>();




        //Fragment内容填充
        Bundle bundle = getArguments();
        if(bundle!=null){
            int arg = bundle.getInt("arg");
            switch (arg){
                case 0:
                    SR_destinationAdapter destinationAdapter = new SR_destinationAdapter(getActivity(),(LinkedList<DestinationBean>) destination);
                    listView.setAdapter(destinationAdapter);
                    //数据填充



                    destinationAdapter.notifyDataSetChanged();
                    break;
                case 1:
                    SR_travelAdapter travelAdapter = new SR_travelAdapter(getActivity(),(LinkedList<TravelBean>)travel);
                    listView.setAdapter(travelAdapter);



                    travelAdapter.notifyDataSetChanged();
                    break;
                case 2:
                    SR_strategyAdapter strategyAdapter = new SR_strategyAdapter(getActivity(),(LinkedList<StrategyBean>)strategy);
                    listView.setAdapter(strategyAdapter);



                    strategyAdapter.notifyDataSetChanged();

                    break;
                case 3:
                    SR_answerAdapter answerAdapter = new SR_answerAdapter(getActivity(),(LinkedList<AnswersBean>)answer);
                    listView.setAdapter(answerAdapter);




                    answerAdapter.notifyDataSetChanged();
                    break;
                case 4:
                    SR_userAdapter userAdapter = new SR_userAdapter(getActivity(),(LinkedList<UserBean>)user);
                    listView.setAdapter(userAdapter);




                    userAdapter.notifyDataSetChanged();
                    break;
                 default:break;
            }
        }

    }
}