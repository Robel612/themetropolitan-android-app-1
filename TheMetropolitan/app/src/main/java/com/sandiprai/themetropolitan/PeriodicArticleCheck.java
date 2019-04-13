package com.sandiprai.themetropolitan;

import android.app.IntentService;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.JobIntentService;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions and extra parameters.
 */
public class PeriodicArticleCheck extends JobIntentService {
    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    private static final String TAG = "PeriodicArticleCheck";
    public static final String ACTION_FOO = "com.sandiprai.themetropolitan.action.FOO";
    public static final String ACTION_BAZ = "com.sandiprai.themetropolitan.action.BAZ";

    // TODO: Rename parameters
    public static final int JOB_ID = 1;
    public static final int ONE_MIN = 60*1000;
    public static final int ONE_DAY = 86400*1000;

    public PeriodicArticleCheck() {
        super();
    }

    //starts service in from BootReciever that starts on startup
    public static void enqueueWork(Context context, Intent work) {
        enqueueWork(context, PeriodicArticleCheck.class, JOB_ID, work);
    }

    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate"); //log the string message
    }

    //main method called that actually runs the code
    @Override //equivalent to onHandleIntent
    protected void onHandleWork(@NonNull Intent intent) {
        Log.d(TAG,"onHandleWork"); //log the string message

        String input = "Will be checking for new articles here!";

        for (int i = 0; i < 10; i++) {
            Log.d(TAG, input + " - " + i);
            SystemClock.sleep(60000); //in milliseconds
        }
        //intent.setClass(getApplicationContext(),MainActivity.class);
        //Toast.makeText(context,"Will be checking for new article", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy"); //log the string message
    }

    @Override
    public boolean onStopCurrentWork(){
        Log.d(TAG,"onStopCurrentWork"); //log the string message
        return super.onStopCurrentWork();
    }

    public static void schedule(Context context) {
        ComponentName component = new ComponentName(context,PeriodicArticleCheck.class);
        JobInfo.Builder builder = new JobInfo.Builder(JOB_ID, component)
                .setMinimumLatency(ONE_MIN)
                .setOverrideDeadline(5*ONE_MIN);

        JobScheduler jobScheduler = (JobScheduler) context.getSystemService(Context.JOB_SCHEDULER_SERVICE);
        jobScheduler.schedule(builder.build());
    }


}