package com.skeleton.mvp.ui.base.baserecycler.adapter;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.skeleton.mvp.R;
import com.skeleton.mvp.ui.base.baserecycler.ActionItemListener;
import com.skeleton.mvp.ui.base.baserecycler.model.DataClass;

import java.util.ArrayList;
import java.util.List;

import static com.skeleton.mvp.ui.base.baserecycler.BaseRecyclerConstants.VIEW_TYPE_DATA;
import static com.skeleton.mvp.ui.base.baserecycler.BaseRecyclerConstants.VIEW_TYPE_ERROR;
import static com.skeleton.mvp.ui.base.baserecycler.BaseRecyclerConstants.VIEW_TYPE_LOADER;

/**
 * Created by soc-lap-18k.n on 1/29/18.
 */
public abstract class BaseRecyclerAdapter extends RecyclerView.Adapter {
    private ArrayList<DataClass> dataList;
    private ActionItemListener listener;
    private Context context;

    /**
     * Instantiates a new Base recycler adapter.
     *
     * @param listener the listener
     */
    public BaseRecyclerAdapter(final ActionItemListener listener) {
        this.listener = listener;
        this.dataList = new ArrayList<>();
        //addItem("Loader", VIEW_TYPE_LOADER);
    }

    /**
     * Add data.
     *
     * @param incomingDataList the incoming data list
     */
    protected void addData(final ArrayList incomingDataList) {
        switch (dataList.get(dataList.size() - 1).getItemType()) {
            case VIEW_TYPE_LOADER:
                removeLoaderView();
                break;
            case VIEW_TYPE_ERROR:
                removeErrorView();
                break;
            default:
        }
        int initialDataListCount = dataList.size() - 1;
        this.dataList.addAll(createDataList(incomingDataList));
        notifyItemRangeChanged(initialDataListCount, incomingDataList.size());

    }

    /**
     * Update data.
     *
     * @param incomingDataList the incoming data list
     */
    protected void updateData(final List incomingDataList) {
        this.dataList = createDataList(incomingDataList);
        notifyDataSetChanged();

    }

    /**
     * Gets view type.
     *
     * @param position the position
     * @return the view type
     */
    protected int getViewType(final int position) {
        return dataList.get(position).getItemType();
    }

    private ArrayList<DataClass> createDataList(final List objectList) {
        ArrayList<DataClass> datList = new ArrayList<>();
        for (Object object : objectList) {
            datList.add(new DataClass(object, VIEW_TYPE_DATA));
        }
        return datList;
    }
//
//    private static ArrayList<DataClass> createDataList1(ArrayList objectList) {
//        ArrayList<DataClass> dataList = new ArrayList<>();
//        for (Object object : objectList) {
//            dataList.add(new DataClass(object, BaseRecyclerConstants.VIEW_TYPE_NO_DATA));
//        }
//        return dataList;
//    }
//
//    public void addData(ArrayList dataList) {
//        this.dataList.addAll(createDataList1(dataList));
//        notifyDataSetChanged();
//
//    }

    /**
     * Add loader view.
     */
    public void addLoaderView() {
        if (dataList.get(dataList.size() - 1).getItemType() != VIEW_TYPE_LOADER) {
            new Handler().post(new Runnable() {
                @Override
                public void run() {
                    addItem("Loader", VIEW_TYPE_LOADER);
                    notifyItemInserted(dataList.size() - 1);
                    listener.onLoadMore();
                }
            });
        }
    }

    /**
     * Remove loader view.
     */
    public void removeLoaderView() {
        if (dataList.get(dataList.size() - 1).getItemType() == VIEW_TYPE_LOADER) {
            this.dataList.remove(dataList.get(dataList.size() - 1));

            notifyItemRemoved(dataList.size() - 1);
        }
    }

    /**
     * Add error view.
     */
    public void addErrorView() {
        if (dataList.get(dataList.size() - 1).getItemType() != VIEW_TYPE_ERROR) {
            addItem("Error", VIEW_TYPE_ERROR);

            notifyItemInserted(dataList.size() - 1);
        }
    }

    /**
     * Remove error view.
     */
    public void removeErrorView() {
        if (dataList.get(dataList.size() - 1).getItemType() == VIEW_TYPE_ERROR) {
            this.dataList.remove(dataList.get(dataList.size() - 1));

            notifyItemRemoved(dataList.size() - 1);
        }
    }

    private void addItem(final String text, final int viewType) {
        this.dataList.add(new DataClass(text, viewType));
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        View view;
        if (viewType == VIEW_TYPE_LOADER) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_layout_loader_adapter, parent, false);
            return new LoaderViewHolder(view);
        } else {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_layout_error_adapter, parent, false);
            return new ErrorViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof ErrorViewHolder) {
            ErrorViewHolder myHolder = (ErrorViewHolder) holder;
            String text = (String) dataList.get(holder.getAdapterPosition()).toResponseModel(String.class);
            myHolder.tvText.setText(text);
            myHolder.tvText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    if (listener != null) {
                        listener.onRetry();
                    }
                }
            });
        } else if (holder instanceof LoaderViewHolder) {
            LoaderViewHolder myHolder = (LoaderViewHolder) holder;

        }
    }

    @Override
    public int getItemCount() {
        return dataList != null ? dataList.size() : 0;
    }

    @Override
    public int getItemViewType(final int position) {
        return dataList.get(position).getItemType();
    }

    /**
     * The type Error view holder.
     */
    public class ErrorViewHolder extends RecyclerView.ViewHolder {
        private TextView tvText;

        /**
         * Instantiates a new Error view holder.
         *
         * @param itemView the item view
         */
        ErrorViewHolder(final View itemView) {
            super(itemView);
            tvText = itemView.findViewById(R.id.tv_text);
        }
    }

    /**
     * The type Loader view holder.
     */
    public class LoaderViewHolder extends RecyclerView.ViewHolder {
        private TextView tvText;

        /**
         * Instantiates a new Loader view holder.
         *
         * @param itemView the item view
         */
        LoaderViewHolder(final View itemView) {
            super(itemView);
            tvText = itemView.findViewById(R.id.tv_text);
        }
    }

}
