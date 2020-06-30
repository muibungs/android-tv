package com.example.myapplication.ui.presenter;

import androidx.leanback.widget.ListRowPresenter;
import androidx.leanback.widget.RowPresenter;


public class CustomListRowPresenter extends ListRowPresenter {

    private static final String TAG = CustomListRowPresenter.class.getSimpleName();

    public CustomListRowPresenter() {
        super();
    }

    @Override
    protected void onBindRowViewHolder(RowPresenter.ViewHolder holder, Object item) {
        /* This two line codes changes the number of rows of ListRow */
        int numRows = ((CustomListRow) item).getNumRows();
        ((ListRowPresenter.ViewHolder) holder).getGridView().setNumRows(numRows);

        super.onBindRowViewHolder(holder, item);
    }

    @Override
    protected void initializeRowViewHolder(RowPresenter.ViewHolder holder) {
        super.initializeRowViewHolder(holder);

        /* Disable Shadow */
        // setShadowEnabled(false);
    }
}