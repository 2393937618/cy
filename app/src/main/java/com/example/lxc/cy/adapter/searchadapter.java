//package com.example.lxc.cy.adapter;
//
//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentManager;
//import android.support.v4.app.FragmentPagerAdapter;
//import android.view.ViewGroup;
//
//import com.example.lxc.cy.main.search_result;
//
//public class searchadapter extends FragmentPagerAdapter {
//
//    private final int count=5;
//    private fragment_destination zhFragement=null;
//    private fragment_travel yjFragement=null;
//    private fragment_strategy glFragement=null;
//    private fragment_answers wdFragement=null;
//    private fragment_user yhFragement=null;
//
//
//    public searchadapter(FragmentManager fm) {
//        super(fm);
//
//        zhFragement=new fragment_destination();
//        yjFragement=new fragment_travel();
//        glFragement=new fragment_strategy();
//        wdFragement=new fragment_answers();
//        yhFragement=new fragment_user();
//    }
//
//    @Override
//    public Fragment getItem(int position) {
//        Fragment fragment=null;
//
//        switch (position){
//            case search_result.page_one:
//                fragment=zhFragement;
//                break;
//            case search_result.page_two:
//                fragment=yjFragement;
//                break;
//            case search_result.page_three:
//                fragment=glFragement;
//                break;
//            case search_result.page_four:
//                fragment=wdFragement;
//                break;
//            case search_result.page_five:
//                fragment=yhFragement;
//                break;
//        }
//        return fragment;
//    }
//
//    @Override
//    public int getCount() {
//        return count;
//    }
//
//    public Object instantiateItem(ViewGroup vg, int position) {
//        return super.instantiateItem(vg, position);
//    }
//    public void destroyItem(ViewGroup container, int position, Object object) {
//        System.out.println("position Destory" + position);
//        super.destroyItem(container, position, object);
//    }
//
//}
