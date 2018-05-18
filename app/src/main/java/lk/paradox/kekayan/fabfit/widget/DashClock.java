package lk.paradox.kekayan.fabfit.widget;

import android.content.Intent;

import com.google.android.apps.dashclock.api.DashClockExtension;
import com.google.android.apps.dashclock.api.ExtensionData;

import lk.paradox.kekayan.fabfit.MainActivity;
import lk.paradox.kekayan.fabfit.R;
import lk.paradox.kekayan.fabfit.db.Database;
import lk.paradox.kekayan.fabfit.fragments.StepsFragment;
import lk.paradox.kekayan.fabfit.helpers.Util;

/**
 * Class for providing a DashClock (https://code.google.com/p/dashclock)
 * extension
 */
public class DashClock extends DashClockExtension {

    @Override
    protected void onUpdateData(int reason) {
        ExtensionData data = new ExtensionData();
        Database db = Database.getInstance(this);
        int steps = Math.max(db.getCurrentSteps() + db.getSteps(Util.getToday()), 0);
        data.visible(true).status(StepsFragment.formatter.format(steps))
                .icon(R.drawable.ic_dashclock)
                .clickIntent(new Intent(DashClock.this, MainActivity.class));
        db.close();
        publishUpdate(data);
    }

}
