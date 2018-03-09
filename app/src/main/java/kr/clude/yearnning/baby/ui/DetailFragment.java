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
import kr.clude.yearnning.baby.model.Chat;
import kr.clude.yearnning.baby.model.PersonSitter;
import kr.clude.yearnning.baby.model.Review;

public class DetailFragment extends Fragment {

    /**
     *
     */
    private static final String ARG_PERSON_SITTER_ID = "arg_person_sitter_id";

    /**
     *
     */
    private int mPerson_sitter_id;
    private PersonSitter sitter;

    /**
     *
     */
    private ListView mLv;
    private LvAdapter mLvAdapter;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment DetailFragment.
     */
    public static DetailFragment newInstance(int person_sitter_id) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PERSON_SITTER_ID, person_sitter_id);
        fragment.setArguments(args);
        return fragment;
    }

    public DetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mPerson_sitter_id = getArguments().getInt(ARG_PERSON_SITTER_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.detail_fragment, container, false);
        View headerView = inflater.inflate(R.layout.detail_fragment_lv_header, null);

        /**
         *
         */
        mLv = (ListView) rootView.findViewById(R.id.lv);
        View mRecommendBtn = headerView.findViewById(R.id.recommend_btn);
        View mContactBtn = headerView.findViewById(R.id.contact_btn);
        TextView mNameTv = (TextView) headerView.findViewById(R.id.name_tv);
        ImageView mProfileBgIv = (ImageView) headerView.findViewById(R.id.profile_bg_iv);
        ImageView mProfileIv = (ImageView) headerView.findViewById(R.id.profile_iv);
        TextView mExplainTv = (TextView) headerView.findViewById(R.id.explain_tv);

        /**
         *
         */
        sitter = PersonSitter.newInstanceById(mPerson_sitter_id);
        mNameTv.setText(sitter.name + "(" + sitter.age + ")");
        mProfileIv.setImageResource(sitter.img_resource);
        mProfileBgIv.setImageResource(sitter.img_resource_bg);
        mExplainTv.setText(String.format("경력 %d회, %dkm 이내, %d원/시간", sitter.reviews_parent_cnt, sitter.distance_km, sitter.pay_per_hour));

        /**
         *
         */
        mLv.addHeaderView(headerView);
        mLv.addFooterView(new View(getActivity()));

        /*
         * ListView Setting
		 */
        ArrayList<Review> reviews_parent = new ArrayList<>();
        ArrayList<Review> reviews_sitter = new ArrayList<>();
        for (int i = 1; i < 5; i++) {
            reviews_parent.add(Review.newInstanceById(i, Chat.Type.PARENT));
            reviews_sitter.add(Review.newInstanceById(i, Chat.Type.SITTER));
        }
        mLvAdapter = new LvAdapter(getActivity(), R.layout.detail_fragment_lv, reviews_parent, reviews_sitter);
        mLv.setAdapter(mLvAdapter);

        /**
         *
         */
        mRecommendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecommendActivity.startActivity(getActivity(), sitter.name);
            }
        });

        mContactBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContactActivity.startActivity(getActivity(), sitter.name);
            }
        });

        return rootView;
    }

    /**
     * ListView Apdater Setting
     */
    private class LvAdapter extends ArrayAdapter<Review> {
        private static final String TAG = "MainFragment LvAdapter";
        public ArrayList<Review> reviews_parent;
        public ArrayList<Review> reviews_sitter;
        private ViewHolder viewHolder = null;
        private int textViewResourceId;

        private Resources r;
        private MainActivity activity;

        public LvAdapter(Activity context, int textViewResourceId,
                         ArrayList<Review> review_parent, ArrayList<Review> review_sitter) {
            super(context, textViewResourceId, review_parent);

            this.textViewResourceId = textViewResourceId;
            this.reviews_parent = review_parent;
            this.reviews_sitter = review_sitter;
            this.r = getResources();
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public int getCount() {
            return reviews_parent.size();
        }

        @Override
        public Review getItem(int position) {
            return reviews_parent.get(position);
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

                viewHolder.mParentProfileIv = (ImageView) convertView.findViewById(R.id.parent_profile_iv);
                viewHolder.mSitterProfileIv = (ImageView) convertView.findViewById(R.id.sitter_profile_iv);

                viewHolder.mSitterImageIv = (ImageView) convertView.findViewById(R.id.sitter_image_iv);

                viewHolder.mParentContentTv = (TextView) convertView.findViewById(R.id.parent_content_tv);
                viewHolder.mSitterContentTv = (TextView) convertView.findViewById(R.id.sitter_content_tv);

                viewHolder.mParentNameTv = (TextView) convertView.findViewById(R.id.parent_name_tv);
                viewHolder.mSitterNameTv = (TextView) convertView.findViewById(R.id.sitter_name_tv);

                convertView.setTag(viewHolder);

            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            final Review review_sitter = reviews_sitter.get(position);
            final Review review_parent = reviews_parent.get(position);

			/*
             * Data Import and export
			 */

            viewHolder.mParentProfileIv.setImageResource(review_parent.person.img_resource);
            viewHolder.mSitterProfileIv.setImageResource(review_sitter.person.img_resource);

            viewHolder.mParentContentTv.setText(review_parent.content);
            viewHolder.mSitterContentTv.setText(review_sitter.content);

            viewHolder.mParentNameTv.setText(review_parent.person.name);
            viewHolder.mSitterNameTv.setText(review_sitter.person.name);

            if (review_sitter.img_resource != -1) {
                viewHolder.mSitterImageIv.setImageResource(review_sitter.img_resource);
                viewHolder.mSitterImageIv.setVisibility(View.VISIBLE);
            } else {
                viewHolder.mSitterImageIv.setVisibility(View.GONE);
            }

            return convertView;
        }

        @Override
        public boolean isEnabled(int position) {

            return false;
        }

        private class ViewHolder {

            ImageView mSitterProfileIv;
            ImageView mSitterImageIv;
            TextView mSitterContentTv;
            TextView mSitterNameTv;

            ImageView mParentProfileIv;
            TextView mParentContentTv;
            TextView mParentNameTv;
        }

    }

}
