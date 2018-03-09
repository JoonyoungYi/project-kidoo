package kr.clude.yearnning.baby.ui;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import kr.clude.yearnning.baby.R;
import kr.clude.yearnning.baby.model.Chat;
import kr.clude.yearnning.baby.model.PersonSitter;

public class ContactFragment extends Fragment {

    /**
     *
     */
    private static final String ARG_PARAM1 = "param1";

    /**
     *
     */
    private String mParam1;
    private int chat_sitter_id = 1;
    /**
     *
     */
    private EditText mCommentEt;
    private ListView mLv;
    private LvAdapter mLvAdapter;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment DetailFragment.
     */
    public static ContactFragment newInstance(String param1) {
        ContactFragment fragment = new ContactFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    public ContactFragment() {
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
        View rootView = inflater.inflate(R.layout.contact_fragment, container, false);

        /**
         *
         */
        mLv = (ListView) rootView.findViewById(R.id.lv);
        mCommentEt = (EditText) rootView.findViewById(R.id.comment_et);

        /**
         *
         */
        mLv.addHeaderView(new View(getActivity()));
        mLv.addFooterView(new View(getActivity()));

        /*
         * ListView Setting
		 */
        ArrayList<Chat> chats = new ArrayList<>();
        mLvAdapter = new LvAdapter(getActivity(), R.layout.contact_fragment_lv, chats);
        mLv.setAdapter(mLvAdapter);

        /**
         *
         */
        rootView.findViewById(R.id.comment_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = mCommentEt.getText().toString();
                mLvAdapter.stores.add(Chat.newInstance(content));
                mCommentEt.setText("");
                mLvAdapter.notifyDataSetChanged();
                mLv.smoothScrollToPosition(mLvAdapter.stores.size());

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        if (getActivity() != null && mLv != null) {
                            mLvAdapter.stores.add(Chat.newInstanceById(chat_sitter_id++));
                            mLvAdapter.notifyDataSetChanged();
                            mLv.smoothScrollToPosition(mLvAdapter.stores.size());
                        }

                    }
                }, 1000);


            }
        });


        return rootView;
    }

    /**
     * ListView Apdater Setting
     */
    private class LvAdapter extends ArrayAdapter<Chat> {
        private static final String TAG = "MainFragment LvAdapter";
        public ArrayList<Chat> stores;
        private ViewHolder viewHolder = null;
        private int textViewResourceId;


        private Resources r;
        private MainActivity activity;

        public LvAdapter(Activity context, int textViewResourceId,
                         ArrayList<Chat> stores) {
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
        public Chat getItem(int position) {
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

                viewHolder.mParentView = convertView.findViewById(R.id.parent_view);
                viewHolder.mSitterView = convertView.findViewById(R.id.sitter_view);

                viewHolder.mParentProfileIv = (ImageView) convertView.findViewById(R.id.parent_profile_iv);
                viewHolder.mSitterProfileIv = (ImageView) convertView.findViewById(R.id.sitter_profile_iv);

                viewHolder.mParentContentTv = (TextView) convertView.findViewById(R.id.parent_content_tv);
                viewHolder.mSitterContentTv = (TextView) convertView.findViewById(R.id.sitter_content_tv);

                convertView.setTag(viewHolder);

            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            final Chat chat = this.getItem(position);

			/*
             * Data Import and export
			 */
            viewHolder.mParentView.setVisibility(chat.type == Chat.Type.PARENT ? View.VISIBLE : View.GONE);
            viewHolder.mSitterView.setVisibility(chat.type == Chat.Type.PARENT ? View.GONE : View.VISIBLE);

            if (chat.type == Chat.Type.PARENT) {
                viewHolder.mParentProfileIv.setImageResource(chat.person.img_resource);
                viewHolder.mParentContentTv.setText(chat.content);

            } else {
                viewHolder.mSitterProfileIv.setImageResource(chat.person.img_resource);
                viewHolder.mSitterContentTv.setText(chat.content);
            }

            /**
             *
             */
            return convertView;
        }

        @Override
        public boolean isEnabled(int position) {

            return false;
        }

        private class ViewHolder {

            View mSitterView;
            ImageView mSitterProfileIv;
            TextView mSitterContentTv;

            View mParentView;
            ImageView mParentProfileIv;
            TextView mParentContentTv;
        }

    }

}
