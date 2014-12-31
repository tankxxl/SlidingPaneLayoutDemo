package com.umpay.slidingpanelayoutdemo;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class BookMarkerFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private BookMarkListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BookMarkerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BookMarkerFragment newInstance(String param1, String param2) {
        BookMarkerFragment fragment = new BookMarkerFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public BookMarkerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_book_marker, container, false);
        initListView(view);
        return view;
    }

    private void initListView(View view) {
        ListView lv = (ListView) view.findViewById(R.id.listview);
        List<String> list = new ArrayList<String>();
        list.add("网易");
        list.add("腾讯");
        list.add("新浪");
        list.add("搜狐");
        ArrayAdapter adapter = new ArrayAdapter(BookMarkerFragment.this.getActivity(), R.layout.listitem_bookmarker,
                R.id.text, list);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        mListener.onChangeBookmark("http://www.163.com");
                        break;
                    case 1:
                        mListener.onChangeBookmark("http://www.qq.com");
                        break;
                    case 2:
                        mListener.onChangeBookmark("http://sina.com");
                        break;
                    case 3:
                        mListener.onChangeBookmark("http://sohu.com");
                        break;
                }
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        MenuItem item1 = menu.add(1, 1, 1, "分享");
        item1.setShowAsAction(MenuItem.SHOW_AS_ACTION_WITH_TEXT | MenuItem.SHOW_AS_ACTION_ALWAYS);
        ImageView imageView = new ImageView(BookMarkerFragment.this.getActivity());
        imageView.setBackgroundResource(R.drawable.ic_launcher);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(50, 50));
        item1.setActionView(imageView);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (BookMarkListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface BookMarkListener {
        public void onChangeBookmark(String bookmark);
    }

}
