package com.lite.litemusic.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lite.litemusic.R;
import com.lite.litemusic.model.Audio;

import java.util.List;

public class ListViewAdapter extends BaseAdapter {

    private List<Audio> mAudioList;
    private Context mContext;
    private Listener mListener;

    public ListViewAdapter(Context context, List<Audio> audioList, Listener listener) {
        mContext = context;
        mAudioList = audioList;
        mListener = listener;
    }

    @Override
    public int getCount() {
        return mAudioList.size();
    }

    @Override
    public Object getItem(int i) {
        return mAudioList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.list_item_music_info, viewGroup, false);
            viewHolder = new ViewHolder();
            viewHolder.TextViewMusicInfoTitle = (TextView) view.findViewById(R.id.tv_music_info_title);
            viewHolder.TextViewMusicInfoSummary = (TextView) view.findViewById(R.id.tv_music_info_summary);
            viewHolder.ImageViewMusicInfoLocation = (ImageView) view.findViewById(R.id.iv_music_info_location);
            viewHolder.ImageViewMusicInfoQuality = (ImageView) view.findViewById(R.id.iv_music_info_quality);
            viewHolder.ImageViewMusicInfoOperate = (ImageView) view.findViewById(R.id.iv_music_info_operate);
            viewHolder.linearLayoutMusicInfo = (LinearLayout) view.findViewById(R.id.ll_music_info);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        Audio audio = mAudioList.get(i);
        viewHolder.TextViewMusicInfoTitle.setText(audio.getTitle());
        viewHolder.TextViewMusicInfoSummary.setText(audio.getSinger() + "-" + audio.getAlbum());
        viewHolder.linearLayoutMusicInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.OnMusicItemClick(i);
            }
        });
        return view;
    }

    private final class ViewHolder {
        TextView TextViewMusicInfoTitle;
        TextView TextViewMusicInfoSummary;
        ImageView ImageViewMusicInfoLocation;
        ImageView ImageViewMusicInfoQuality;
        ImageView ImageViewMusicInfoOperate;
        LinearLayout linearLayoutMusicInfo;
    }

    public interface Listener {
        void OnMusicItemClick(int position);
    }
}
