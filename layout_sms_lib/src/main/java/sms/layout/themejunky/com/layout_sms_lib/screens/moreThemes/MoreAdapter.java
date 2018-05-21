package sms.layout.themejunky.com.layout_sms_lib.screens.moreThemes;

//ThemeConfig_internalAds

import android.app.Activity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.lang.ref.WeakReference;
import java.util.List;

import sms.layout.themejunky.com.layout_sms_lib.R;
import sms.layout.themejunky.com.layout_sms_lib.retrofit.model.InternalAds;
import sms.layout.themejunky.com.layout_sms_lib.utils.PerformClickAnimation;

public class MoreAdapter extends RecyclerView.Adapter<MoreAdapter.DealsViewHolder> {

    private List<InternalAds> model;
    private WeakReference<Activity> activity;
    private MoreContract.View listener;

    protected MoreAdapter(WeakReference<Activity> activity, List<InternalAds> model, MoreContract.View listener) {
        this.model = model;
        this.activity = activity;
        this.listener = listener;
    }

    public void setNewData(List<InternalAds> model) {
        this.model = model;
        notifyDataSetChanged();
    }

    @Override
    public DealsViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        return new DealsViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_wallpapers, viewGroup, false));
    }

    @Override
    public int getItemCount() {
        return model.size();
    }

    protected static class DealsViewHolder extends RecyclerView.ViewHolder {
        public ImageView wallpaper;
        public CardView cardView;

        DealsViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.cardView);
            wallpaper = (ImageView) itemView.findViewById(R.id.wallpaper);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return 2;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView)
    {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public void onBindViewHolder(final DealsViewHolder mHolder, final int i) {

        final InternalAds item = model.get(i);

        Picasso.with(activity.get().getApplicationContext()).load(item.getImage()).placeholder(R.drawable.placeholder_category_tm).into(mHolder.wallpaper);

        mHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new PerformClickAnimation(mHolder.wallpaper,listener,model.get(i));
                Log.d("dasdas",item.getStoreUrl());
            }
        });
    }

}
