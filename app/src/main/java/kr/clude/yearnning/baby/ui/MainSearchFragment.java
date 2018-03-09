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


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainSearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainSearchFragment extends Fragment {

    /**
     * the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
     */
    private static final String ARG_PARAM1 = "param1";

    /**
     *
     */
    private String mParam1;

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
     * @return A new instance of fragment MainFragment.
     */
    public static MainSearchFragment newInstance(String param1) {
        MainSearchFragment fragment = new MainSearchFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    public MainSearchFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.main_search_fragment, container, false);

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
        ArrayList<PersonSitter> sitters = new ArrayList<>();
        for (int i = 1; i < 4; i++) {
            sitters.add(PersonSitter.newInstanceById(i));
        }
        mLvAdapter = new LvAdapter(getActivity(), R.layout.main_search_fragment_lv, sitters);
        mLv.setAdapter(mLvAdapter);


        return rootView;
    }

    /**
     * ListView Apdater Setting
     */
    private class LvAdapter extends ArrayAdapter<PersonSitter> {
        private static final String TAG = "MainFragment LvAdapter";
        public ArrayList<PersonSitter> stores;
        private ViewHolder viewHolder = null;
        private int textViewResourceId;


        private Resources r;
        private MainActivity activity;

        public LvAdapter(Activity context, int textViewResourceId,
                         ArrayList<PersonSitter> stores) {
            super(context, textViewResourceId, stores);

            this.textViewResourceId = textViewResourceId;
            this.stores = stores;
            this.activity = (MainActivity) getActivity();
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
        public PersonSitter getItem(int position) {
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
                viewHolder.mProfileBgIv = (ImageView) convertView.findViewById(R.id.profile_bg_iv);
                viewHolder.mProfileIv = (ImageView) convertView.findViewById(R.id.profile_iv);
                viewHolder.mExplainTv = (TextView) convertView.findViewById(R.id.explain_tv);
                viewHolder.mCardBtn = convertView.findViewById(R.id.card_btn);

                convertView.setTag(viewHolder);

            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            final PersonSitter sitter = this.getItem(position);

			/*
             * Data Import and export
			 */
            viewHolder.mNameTv.setText(sitter.name + "(" + sitter.age + ")");
            viewHolder.mProfileIv.setImageResource(sitter.img_resource);
            viewHolder.mProfileBgIv.setImageResource(sitter.img_resource_bg);
            viewHolder.mExplainTv.setText(String.format("경력 %d회, %dkm 이내, %d원/시간", sitter.reviews_parent_cnt, sitter.distance_km, sitter.pay_per_hour));

            /**
             *
             */
            viewHolder.mCardBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DetailActivity.startActivity(getActivity(), sitter.id);
                }
            });

            return convertView;
        }

        @Override
        public boolean isEnabled(int position) {

            return false;
        }

        private class ViewHolder {

            TextView mNameTv;
            ImageView mProfileIv;
            ImageView mProfileBgIv;
            TextView mExplainTv;
            View mCardBtn;
        }

    }
}
