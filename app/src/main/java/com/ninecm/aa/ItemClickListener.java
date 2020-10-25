package com.ninecm.aa;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

public class ItemClickListener implements View.OnClickListener {
    private Activity activity;
    private int itemId;
    private int startNum;

    public ItemClickListener(Activity activity, int itemId, int startNum) {
        this.activity = activity;
        this.itemId = itemId;
        this.startNum = startNum;
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(activity, DetailActivity.class);

        intent.putExtra("itemId", itemId);
        intent.putExtra("starNum", startNum);

        activity.startActivity(intent);
    }
}
