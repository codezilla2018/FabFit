package lk.paradox.kekayan.fabfit.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.widget.RemoteViews;

import lk.paradox.kekayan.fabfit.MainActivity;
import lk.paradox.kekayan.fabfit.R;

public class Widget extends AppWidgetProvider {

    static RemoteViews updateWidget(final int appWidgetId, final Context context, final int steps) {
        final SharedPreferences prefs = context.getSharedPreferences("Widgets",
                Context.MODE_PRIVATE);
        final PendingIntent pendingIntent = PendingIntent.getActivity(context, appWidgetId,
                new Intent(context, MainActivity.class), 0);

        final RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget);
        views.setOnClickPendingIntent(R.id.widget, pendingIntent);
        views.setTextColor(R.id.widgetsteps, prefs.getInt("color_" + appWidgetId, Color.WHITE));
        views.setTextViewText(R.id.widgetsteps, String.valueOf(steps));
        views.setTextColor(R.id.widgettext, prefs.getInt("color_" + appWidgetId, Color.WHITE));
        views.setInt(R.id.widget, "setBackgroundColor",
                prefs.getInt("background_" + appWidgetId, Color.TRANSPARENT));
        return views;
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        context.startService(new Intent(context, WidgetUpdateService.class));
    }

}
