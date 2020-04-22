package com.bignerdranch.android.friends.fragment;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bignerdranch.android.friends.R;
import com.bignerdranch.android.friends.ui.SearchContactActivity;

import java.util.HashMap;
import java.util.Map;

public class DialFragment extends BaseFragment implements View.OnClickListener {

    private EditText mNumber;
    private CardView mCall;
    private ImageView mDelete, mAdd;
    private Button mNo0;
    private View mPickContact, mMatchedName, mAddToContact,
            mDialMatchedNumber, mSendSmsToMatchedNumber;

    private Map<Integer, Integer> map = new HashMap<>();
    private SoundPool mSoundPool;
    private AudioManager mAudioManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAudioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

        mSoundPool = new SoundPool(11, AudioManager.STREAM_SYSTEM, 5);
        map.put(0, mSoundPool.load(getActivity(), R.raw.dtmf0, 0));
        map.put(1, mSoundPool.load(getActivity(), R.raw.dtmf1, 0));
        map.put(2, mSoundPool.load(getActivity(), R.raw.dtmf2, 0));
        map.put(3, mSoundPool.load(getActivity(), R.raw.dtmf3, 0));
        map.put(4, mSoundPool.load(getActivity(), R.raw.dtmf4, 0));
        map.put(5, mSoundPool.load(getActivity(), R.raw.dtmf5, 0));
        map.put(6, mSoundPool.load(getActivity(), R.raw.dtmf6, 0));
        map.put(7, mSoundPool.load(getActivity(), R.raw.dtmf7, 0));
        map.put(8, mSoundPool.load(getActivity(), R.raw.dtmf8, 0));
        map.put(9, mSoundPool.load(getActivity(), R.raw.dtmf9, 0));
        map.put(11, mSoundPool.load(getActivity(), R.raw.dtmf11, 0));
        map.put(12, mSoundPool.load(getActivity(), R.raw.dtmf12, 0));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_dial, container, false);
        mNumber = (EditText) v.findViewById(R.id.fragment_dial_number_edit_text);
        mNumber.setInputType(InputType.TYPE_NULL);
        mNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int after, int count) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (! mNumber.getText().toString().equals("")) {
                    mDelete.setVisibility(View.VISIBLE);
                    mAdd.setVisibility(View.VISIBLE);
                } else {
                    mDelete.setVisibility(View.INVISIBLE);
                    mAdd.setVisibility(View.INVISIBLE);
                }
                // TODO
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        for (int i = 0; i < 12; i++) {
            View view = v.findViewById(R.id.dial_num_1 + i);
            view.setOnClickListener(this);
        }
        mNo0 = (Button) v.findViewById(R.id.dial_num_0);
        mNo0.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                play(1);
                input("+");
                return true;
            }
        });

        mAdd = (ImageView) v.findViewById(R.id.fragment_dial_add_to_contact_image_view);
        mAdd.setOnClickListener(this);

        mDelete = (ImageView) v.findViewById(R.id.fragment_dial_delete_image_view);
        mDelete.setOnClickListener(this);
        mDelete.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                mNumber.setText("");
                return false;
            }
        });

        mCall = (CardView) v.findViewById(R.id.fragment_dial_call_card_view);
        mCall.setOnClickListener(this);

        mPickContact = v.findViewById(R.id.fragment_dial_pick_contact);
        mPickContact.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dial_num_0:
                play(1);
                input(v.getTag().toString());
                break;
            case R.id.dial_num_1:
                play(1);
                input(v.getTag().toString());
                break;
            case R.id.dial_num_2:
                play(2);
                input(v.getTag().toString());
                break;
            case R.id.dial_num_3:
                play(3);
                input(v.getTag().toString());
                break;
            case R.id.dial_num_4:
                play(4);
                input(v.getTag().toString());
                break;
            case R.id.dial_num_5:
                play(5);
                input(v.getTag().toString());
                break;
            case R.id.dial_num_6:
                play(6);
                input(v.getTag().toString());
                break;
            case R.id.dial_num_7:
                play(7);
                input(v.getTag().toString());
                break;
            case R.id.dial_num_8:
                play(8);
                input(v.getTag().toString());
                break;
            case R.id.dial_num_9:
                play(9);
                input(v.getTag().toString());
                break;
            case R.id.dial_x:
                play(11);
                input(v.getTag().toString());
                break;
            case R.id.dial_j:
                play(12);
                input(v.getTag().toString());
                break;
            case R.id.fragment_dial_add_to_contact_image_view:
                addToContact();
                break;
            case R.id.fragment_dial_delete_image_view:
                delete();
                break;
            case R.id.fragment_dial_call_card_view:
                call(mNumber.getText().toString());
                break;
            case R.id.fragment_dial_pick_contact:
                jump(SearchContactActivity.class, false);
                break;
            default:
                break;
        }
    }

    private void play(int id) {
        int max = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int current = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);

        float value = (float) (0.7 / max * current);
        mSoundPool.setVolume(mSoundPool.play(id, value, value, 0, 0, 1f), value, value);
    }

    private void input(String str) {
        int c = mNumber.getSelectionStart();
        String p = mNumber.getText().toString();
        mNumber.setText(p.substring(0, c) + str + p.substring(mNumber.getSelectionStart(), p.length()));
        mNumber.setSelection(c + 1, c + 1);
    }

    private void addToContact() {
        // TODO
    }

    private void delete() {
        int c = mNumber.getSelectionStart();
        if (c > 0) {
            String p = mNumber.getText().toString();
            mNumber.setText(p.substring(0, c - 1) + p.substring(mNumber.getSelectionStart(), p.length()));
            mNumber.setSelection(c - 1, c - 1);
        }
    }

    private void call(String phoneNumber) {
        Intent i = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber));
        startActivity(i);
    }
}
