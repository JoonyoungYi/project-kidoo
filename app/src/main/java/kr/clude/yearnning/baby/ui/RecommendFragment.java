package kr.clude.yearnning.baby.ui;


import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import kr.clude.yearnning.baby.R;
import kr.clude.yearnning.baby.model.PersonSitter;
import kr.clude.yearnning.baby.model.Recommend;
import kr.clude.yearnning.baby.model.Review;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RecommendFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecommendFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    /**
     *
     */
    private ListView mLv;
    private LvAdapter mLvAdapter;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Recommend_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RecommendFragment newInstance(String param1, String param2) {
        RecommendFragment fragment = new RecommendFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public RecommendFragment() {
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

        View rootView = inflater.inflate(R.layout.detail_fragment, container, false);

        /**
         *
         */
        mLv = (ListView) rootView.findViewById(R.id.lv);

        /**
         *
         */
        mLv.addHeaderView(new View(getActivity()));
        mLv.addFooterView(new View(getActivity()));

        /*
         * ListView Setting
		 */
        ArrayList<Recommend> sitters = new ArrayList<>();
        for (int i = 1; i < 3; i++) {
            sitters.add(Recommend.newInstanceById(i));
        }
        mLvAdapter = new LvAdapter(getActivity(), R.layout.recommend_fragment_lv, sitters);
        mLv.setAdapter(mLvAdapter);

        return rootView;
    }


    /**
     * ListView Apdater Setting
     */
    private class LvAdapter extends ArrayAdapter<Recommend> {
        private static final String TAG = "MainFragment LvAdapter";
        public ArrayList<Recommend> stores;
        private ViewHolder viewHolder = null;
        private int textViewResourceId;


        private Resources r;
        private MainActivity activity;

        public LvAdapter(Activity context, int textViewResourceId,
                         ArrayList<Recommend> stores) {
            super(context, textViewResourceId, stores);

            this.textViewResourceId = textViewResourceId;
            this.stores = stores;
            this.r = getResources();
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public int getCount() {
            return stores.size();
        }

        @Override
        public Recommend getItem(int position) {
            return stores.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

			/*
             * UI Initiailizing : View Holder
			 */

            if (convertView == null) {
                convertView = getActivity().getLayoutInflater()
                        .inflate(textViewResourceId, null);

                viewHolder = new ViewHolder();

                viewHolder.mNameTv = (TextView) convertView.findViewById(R.id.name_tv);
                viewHolder.mProfileIv = (ImageView) convertView.findViewById(R.id.profile_iv);
                viewHolder.mImgIv = (ImageView) convertView.findViewById(R.id.img_iv);

                convertView.setTag(viewHolder);

            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            final Recommend sitter = this.getItem(position);

			/*
             * Data Import and export
			 */
            viewHolder.mNameTv.setText(sitter.name);
            viewHolder.mImgIv.setImageResource(sitter.img_resource);
            viewHolder.mProfileIv.setImageResource(sitter.profile_img_resource);

            return convertView;
        }

        @Override
        public boolean isEnabled(int position) {

            return false;
        }

        private class ViewHolder {

            TextView mNameTv;
            ImageView mProfileIv;
            ImageView mImgIv;

        }

    }


}
